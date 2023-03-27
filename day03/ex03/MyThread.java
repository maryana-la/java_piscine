import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class MyThread extends Thread {

    private final int threadNumber;
    private final HashMap<String, String> URLs;
    static final Object lock = new Object();
    private final String folderToDownload = "/mnt/nfs/homes/rchelsea/Desktop/java_piscine/day03/ex03/Downloads";

    MyThread(int threadNumber, HashMap<String, String> URLs) {
        this.threadNumber = threadNumber + 1;
        this.URLs = URLs;
    }

    @Override
    public void run() {
        synchronized (URLs) {
            for (Map.Entry<String, String> x : URLs.entrySet()) {
                String URL = x.getValue();
                download(URL, folderToDownload.concat(URL.substring(URL.lastIndexOf('/'))));
            }
        }
    }

    void download(String url, String fileName) {
        System.out.printf("Thread %d starts %s\n", threadNumber, fileName);
        try {
            InputStream in = URI.create(url).toURL().openStream();
            Files.copy(in, Paths.get(fileName), REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.printf("Thread %d finished %s\n", threadNumber, fileName);
    }

//    private String getNextLink() {
//        synchronized (lock) {
//
//        }
//    }

    int getThreadNumber() {
        return threadNumber;
    }
}
