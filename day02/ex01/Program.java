import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.Arrays;

import java.lang.Math;

public class Program {
    private static Words file1;
    private static Words file2;
    private static final String DICTIONARY_FILE = "dictionary.txt";
    private static final Set<String> dictionary = new TreeSet<>();

    public static void main (String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments. Usage: java Program file1 file2");
            System.exit(-1);
        }

        try {
            file1 = new Words(args[0]);
            file2 = new Words(args[1]);
            readFileToDictionary(file1);
            readFileToDictionary(file2);
            file1.close();
            file2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        file1.setFrequencyBuffer(dictionary);
        file2.setFrequencyBuffer(dictionary);

        writeDictionaryFile();

        System.out.print("Similarity = ");
        System.out.println(calculateSimilarity(file1.getFrequencyBuffer(), file2.getFrequencyBuffer()));
    }

    private static void readFileToDictionary (Words file) throws IOException {
        String tmp;
        while (file.hasToRead()) {
            tmp = file.readNextLine();
            if (tmp != null && !tmp.isBlank()) {
                List<String> temp = Arrays.asList(tmp.trim().split("[\\p{Punct}\\s]+"));
                dictionary.addAll(temp);
                for (String str : temp) {
                    Integer tmpInt = file.addWord(str, 1);
                    if (tmpInt != null) {
                        file.addWord(str, tmpInt + 1);
                    }
                }
            }
        }
    }

    private static float calculateSimilarity(int[] freq1,int[] freq2) {
        float numerator = 0;
        float denominator;
        float mult1 = 0;
        float mult2 = 0;

        for (int i = 0; i < freq1.length; i++) {
            numerator += freq1[i] * freq2[i];
            mult1 += Math.pow(freq1[i], 2);
            mult2 += Math.pow(freq2[i],2);
        }
        denominator = (float) (Math.sqrt(mult1) * Math.sqrt(mult2));
        if (denominator == 0)
            return 0;
        return numerator / denominator;
    }

    private static void writeDictionaryFile() {
        try {
            BufferedWriter dict = new BufferedWriter(new FileWriter(DICTIONARY_FILE));
            for (String str : dictionary) {
                dict.write(str);
                dict.newLine();
            }
            dict.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
