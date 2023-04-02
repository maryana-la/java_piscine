import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class MyThread extends Thread {

    private final int threadNumber;
    private final String downloadDir;
    private final ArrayList<String> URLs;
    static int filesDone = 0;
    static final Semaphore controller = new Semaphore(1);
    static final Semaphore print = new Semaphore(1);

    MyThread(int threadNumber, ArrayList<String> URL, String downloadDir) {
        this.threadNumber = threadNumber + 1;
        this.URLs = URL;
        this.downloadDir = downloadDir;
    }

    @Override
    public void run() {
        while (filesDone < URLs.size()) {
            try {
                controller.acquire();
                String toProceed = URLs.get(filesDone);
                filesDone++;
                controller.release();
                String fileToDownload = toProceed.substring(toProceed.indexOf(' ') + 1).trim();
                String nickName =  toProceed.substring(0, toProceed.indexOf(' '));
                download(fileToDownload, downloadDir.concat(fileToDownload.substring((fileToDownload.lastIndexOf('/')))), nickName);
            } catch (Exception e) {
                safePrint("Thread run error. " + e.getLocalizedMessage());
                e.printStackTrace();
                controller.release();
            }
        }
    }

    void download(String url, String fileName, String nickName) {
        safePrint("Thread %2d starts   downloading file %2s".formatted(getThreadNumber(), nickName));
        try {
            InputStream in = URI.create(url).toURL().openStream();
            Files.copy(in, Paths.get(fileName), REPLACE_EXISTING);
            in.close();
        } catch (Exception e) {
            safePrint("Download error. " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        safePrint("Thread %2d finished downloading file %2s".formatted(getThreadNumber(), nickName));
    }

    private void safePrint(String s) {
        try {
            print.acquire();
            System.out.println(s);
            print.release();
        } catch (Exception e) {
            System.out.println("Safe print error " + e.getLocalizedMessage());
            print.release();
        }

    }

    int getThreadNumber() {
        return threadNumber;
    }
}
