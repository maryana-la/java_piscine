import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class MyThread extends Thread {

    private final int threadNumber;
    private final ArrayList<String> URLs;

    static final Semaphore controller = new Semaphore(1);
    static int filesDone = 0;
//    private final String folderToDownload = "/mnt/nfs/homes/rchelsea/Desktop/java_piscine/day03/ex03/Downloads";
    private final String folderToDownload = "/Users/maryana/Desktop/java_piscine/day03/ex03/Downloads";

    MyThread(int threadNumber, ArrayList<String> URL) {
        this.threadNumber = threadNumber + 1;
        URLs = URL;
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
                download(fileToDownload, folderToDownload.concat(fileToDownload.substring((fileToDownload.lastIndexOf('/')))), nickName);
            } catch (Exception e) {
                System.out.println("Err 1 " + e.getLocalizedMessage());
                e.printStackTrace();
                controller.release();
            }
        }
    }


    void download(String url, String fileName, String nickName) {
        System.out.printf("Thread-%d starts download file %s\n", getThreadNumber(), nickName);
        try {
            InputStream in = URI.create(url).toURL().openStream();
            Files.copy(in, Paths.get(fileName), REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Err 2 ");
            e.printStackTrace();
        }
        System.out.printf("Thread %d finished download file %s\n", getThreadNumber(), nickName);
    }

    int getThreadNumber() {
        return threadNumber;
    }
}
