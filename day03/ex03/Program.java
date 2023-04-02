import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

public class Program {

    private static final String ERROR = "Please provide valid arguments. Usage: java Program --threadsCount=NUM2";
    private static final String URLs_FILE_NAME = "files_urls.txt";
    private static final String DOWNLOAD_DIR = "./Downloads";
    private static final ArrayList<String> URLs = new ArrayList<>();

    public static void main(String[] args) {
        int threadCount = 0;
        try {
            checkArgsValidity(args);
            threadCount = getNumber(args[0]);
            readURLsFromFile();
            setDownloadDir();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i, URLs, DOWNLOAD_DIR);
            threads[i].start();
        }
    }

    private static void setDownloadDir() throws AccessDeniedException {
        File loadDir = new File(DOWNLOAD_DIR);
        if (!loadDir.mkdirs() && !loadDir.canWrite()) {
            throw  new AccessDeniedException("Download directory is not available, please check permissions");
        }
        System.out.println("Download directory is " + loadDir);
    }

    private static void checkArgsValidity(String[] args) throws Exception {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            throw new Exception(ERROR);
        }
    }

    private static int getNumber (String num) throws NumberFormatException {
        try {
            return Integer.parseInt(num.substring(num.indexOf('=') + 1));
        } catch (Exception e) {
            throw new NumberFormatException(ERROR);
        }
    }

    private static void readURLsFromFile() throws Exception {
        try (BufferedReader file = new BufferedReader(new FileReader(URLs_FILE_NAME))) {
            while (file.ready()) {
                String tmp = file.readLine();
                if (!tmp.isBlank() && tmp.contains(" ")) {
                    URLs.add(tmp);
                }
            }
        }
    }
}
