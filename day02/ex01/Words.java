import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

class Words {
    private final BufferedReader file;
    private final HashMap<String, Integer> words = new HashMap<>();
    private int[] frequency;

    Words (String fileName) throws IOException {
        file = new BufferedReader(new FileReader(fileName));
    }

    boolean hasToRead() throws IOException {
        return file.ready();
    }

    String readNextLine() throws IOException {
        return file.readLine();
    }

    void close() throws IOException {
        file.close();
    }

    Integer addWord(String word, Integer frequency) {
        return words.put(word, frequency);
    }

    void setFrequencyBuffer(Set<String> dictionary) {
        frequency = new int[dictionary.size()];
        int i = 0;
        for(String str : dictionary) {
            if (words.containsKey(str)) {
                frequency[i] = words.get(str);
            }
            i++;
        }
    }

    int[] getFrequencyBuffer() {
        return frequency;
    }


}
