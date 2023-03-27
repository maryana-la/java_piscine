import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.HashMap;
import java.net.URI;
import java.util.Map;


public class Program {

    private static final String ERROR = "Please provide valid arguments. Usage: java Program --threadsCount=NUM2";
    private static final String URLs_FILE_NAME = "files_urls.txt";
    private static final String folderToDownload = "/mnt/nfs/homes/rchelsea/Desktop/java_piscine/day03/ex03/Downloads";
    private static final HashMap<String, String> URLs = new HashMap<>();

    public static void main(String[] args) {
        checkArgsValidity(args);
        int threadCount = getNumber(args[0]);
        try {
            readURLsFromFile();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i, URLs);
            threads[i].start();
        }
    }


    private static void checkArgsValidity(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.err.println(ERROR);
            System.exit(-1);
        }
    }

    private static int getNumber (String num) {
        int number = 0;
        try {
            number = Integer.parseInt(num.substring(num.indexOf('=') + 1));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println(ERROR);
            System.exit(-1);
        }
        return number;
    }

    private static void readURLsFromFile() throws Exception {
        try (BufferedReader file = new BufferedReader(new FileReader(URLs_FILE_NAME))) {
            while (file.ready()) {
                String tmp = file.readLine();
                if (!tmp.isBlank() && tmp.contains(" ")) {
                    URLs.put(tmp.substring(0, tmp.indexOf(' ')).trim(), tmp.substring(tmp.indexOf(' ') + 1).trim());
                }
            }
        }
        System.out.println(URLs);
    }


//    static void startdownloading(Thread[] threads) {
//        for (Map.Entry<String, String> x : URLs.entrySet()) {
//            String URL = x.getValue();
//            download(URL, folderToDownload.concat(URL.substring(URL.lastIndexOf('/'))));
//        }
//    }

//    static void download(String url, String fileName) {
//        try {
//            InputStream in = URI.create(url).toURL().openStream();
//            Files.copy(in, Paths.get(fileName), REPLACE_EXISTING);
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//    }

}
