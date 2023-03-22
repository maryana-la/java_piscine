import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Arrays;

import java.math.*;

public class Program {
    private static BufferedReader file1;
    private static BufferedReader file2;

    private static HashMap<String, Integer> words1 = new HashMap<>();
    private static HashMap<String, Integer> words2 = new HashMap<>();
    private static Set<String> dictionary = new TreeSet<>();

    public static void main (String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments. Please provide 2 files");
            System.exit(-1);
        }

        //open files to read
        try {
            file1 = new BufferedReader(new FileReader(args[0]));
            file2 = new BufferedReader(new FileReader(args[1]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //read files to dictionary and save info to HashMap
        try {
            readFileToDictionary(file1, words1);
            readFileToDictionary(file2, words2);
            file1.close();
            file2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //fill frequency array
        int[] freq1 = new int[dictionary.size()];
        int[] freq2 = new int[dictionary.size()];

        int i = 0;
        for(String str : dictionary) {
            if (words1.containsKey(str)) {
                freq1[i] = words1.get(str);
            }
            if (words2.containsKey(str)) {
                freq2[i] = words2.get(str);
            }
            i++;
        }

        System.out.print("freq1 ");
        for (int k = 0; k < freq1.length; k++)
            System.out.print(freq1[k]);

        System.out.print("\nfreq2 ");
        for (int k = 0; k < freq2.length; k++)
            System.out.print(freq2[k]);
        System.out.println();
        System.out.println("dictionary " + dictionary);
        System.out.println("words1 " + words1);
        System.out.println("words2 " + words2);

        System.out.print("Similarity = ");
        System.out.println(calculateSimilarity(freq1, freq2));
    }

    private static void readFileToDictionary (BufferedReader file, HashMap<String, Integer> words) throws IOException {
        String tmp;
        while (file.ready()) {
            tmp = file.readLine();
            if (tmp != null && !tmp.isBlank()) {
                List<String> temp = Arrays.asList(tmp.trim().split("\\s+"));
                dictionary.addAll(temp);
                for (String str : temp) {
                    Integer tmpInt = words.put(str, 1);
//                    System.out.println("tmpInt" + tmpInt);
                    if (tmpInt != null) {
                        words.put(str, tmpInt + 1);
                    }
                }
            }
        }
    }

    private static float calculateSimilarity(int[] freq1,int[] freq2) {
        float numerator = 0;
        float mult1 = 0;
        float mult2 = 0;
        float denominator = 0;

        for (int i = 0; i < freq1.length; i++) {
            numerator += freq1[i] * freq2[i];
            mult1 += Math.pow(freq1[i], 2);
            mult2 += Math.pow(freq2[i],2);
        }
        denominator = (float) (Math.sqrt(mult1) * Math.sqrt(mult2));
        return numerator / denominator;
    }



}
