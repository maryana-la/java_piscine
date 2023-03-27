//import java.io.InputStream;
//import java.net.URI;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
//
//class MyThread extends Thread {
//
//    private final int threadNumber;
////    private static final ArrayList<String> URLs;
//    private final HashMap<String, Integer> URLs;
//    static final Object lock = new Object();
//    private final String folderToDownload = "/mnt/nfs/homes/rchelsea/Desktop/java_piscine/day03/ex03/Downloads";
//
//    MyThread(int threadNumber, HashMap<String, Integer> URL) {
//        this.threadNumber = threadNumber + 1;
//        URLs = URL;
//    }
//
//    @Override
//    public void run() {
//        synchronized (URLs) {
//            for (Map.Entry<String, Integer> x : URLs.entrySet()) {
//                if (x.getValue().equals(1)) {
//                    continue;
//                }
//                x.setValue(1);
//                String tmp = x.getKey();
//                String URL = tmp.substring(tmp.indexOf(' ') + 1).trim();
//                download(URL, folderToDownload.concat(URL.substring(URL.lastIndexOf('/'))));
//                notify();
//            }
//        }
//    }
//
//    void download(String url, String fileName) {
//        System.out.printf("Thread %d starts %s\n", threadNumber, fileName.substring(fileName.lastIndexOf('/')));
//        try {
//            InputStream in = URI.create(url).toURL().openStream();
//            Files.copy(in, Paths.get(fileName), REPLACE_EXISTING);
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//        System.out.printf("Thread %d finished %s\n", threadNumber, fileName.substring(fileName.lastIndexOf('/')));
//    }
//
////    private String getNextLink() {
////        synchronized (lock) {
////
////        }
////    }
//
//    int getThreadNumber() {
//        return threadNumber;
//    }
//}
