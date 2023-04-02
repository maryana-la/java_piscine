import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

public class Program {

    private static final String ERROR = "Please provide valid arguments. Usage: java Program --threadsCount=NUM2";
    private static final String URLs_FILE_NAME = "files_urls.txt";
    private static final ArrayList<String> URLs = new ArrayList<>();

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
                    URLs.add(tmp);
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


/*
import java.io.*;
import java.net.URL;
import java.util.concurrent.*;

public class Program {
    private static final String FILE_URLS = "files_urls.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        int threadsCount = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        BlockingQueue<String> filesQueue = new LinkedBlockingQueue<>();

        // read file URLs from the text file and add them to the queue
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_URLS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                filesQueue.offer(line);
            }
        }

        // download files using threads from the pool
        for (int i = 1; i <= filesQueue.size(); i++) {
            String fileUrl = filesQueue.take();
            executorService.submit(new DownloadThread(i, fileUrl, filesQueue));
        }

        executorService.shutdown();
    }
}

class DownloadThread implements Runnable {
    private final int threadNumber;
    private final String fileUrl;
    private final BlockingQueue<String> filesQueue;

    public DownloadThread(int threadNumber, String fileUrl, BlockingQueue<String> filesQueue) {
        this.threadNumber = threadNumber;
        this.fileUrl = fileUrl;
        this.filesQueue = filesQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread-" + threadNumber + " start download file number " + getFileNumber(fileUrl));

            URL url = new URL(fileUrl);
            String fileName = getFileName(url);
            File file = new File(fileName);

            try (BufferedInputStream in = new BufferedInputStream(url.openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }

            System.out.println("Thread-" + threadNumber + " finish download file number " + getFileNumber(fileUrl));

            String nextFileUrl = filesQueue.poll();
            if (nextFileUrl != null) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.submit(new DownloadThread(threadNumber, nextFileUrl, filesQueue));
                executorService.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(URL url) {
        String[] segments = url.getPath().split("/");
        return segments[segments.length - 1];
    }

    private int getFileNumber(String fileUrl) {
        String[] segments = fileUrl.split(" ");
        return Integer.parseInt(segments[0]);
    }
}
 */



//
//import java.io.BufferedInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Program {
//    private static final String FILE_URLS = "files_urls.txt";
//    private static int threadsCount = 1; // default number of threads
//
//    public static void main(String[] args) {
//        parseArgs(args);
//        List<String> fileUrls = readUrlsFromFile();
//        downloadFiles(fileUrls);
//    }
//
//    private static void parseArgs(String[] args) {
//        for (String arg : args) {
//            if (arg.startsWith("--threadsCount=")) {
//                threadsCount = Integer.parseInt(arg.substring("--threadsCount=".length()));
//            }
//        }
//    }
//
//    private static List<String> readUrlsFromFile() {
//        List<String> urls = new ArrayList<>();
//        try (Scanner scanner = new Scanner(Program.class.getResourceAsStream(FILE_URLS))) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (!line.trim().isEmpty()) {
//                    urls.add(line.trim());
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Error reading file URLs: " + e.getMessage());
//        }
//        return urls;
//    }
//
//    private static void downloadFiles(List<String> urls) {
//        int filesCount = urls.size();
//        int currentFileIndex = 0;
//        List<DownloadThread> threads = new ArrayList<>(threadsCount);
//        while (currentFileIndex < filesCount || !threads.isEmpty()) {
//            if (threads.size() < threadsCount && currentFileIndex < filesCount) {
//                String url = urls.get(currentFileIndex++);
//                DownloadThread thread = new DownloadThread(url);
//                thread.start();
//                threads.add(thread);
//            } else {
//                for (int i = 0; i < threads.size(); i++) {
//                    DownloadThread thread = threads.get(i);
//                    if (!thread.isAlive()) {
//                        threads.remove(i--);
//                        System.out.printf("%s finish download file number %d%n",
//                                thread.getName(), thread.getFileNumber());
//                        if (currentFileIndex < filesCount) {
//                            String url = urls.get(currentFileIndex++);
//                            thread = new DownloadThread(url);
//                            thread.start();
//                            threads.add(thread);
//                            System.out.printf("%s start download file number %d%n",
//                                    thread.getName(), thread.getFileNumber());
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private static class DownloadThread extends Thread {
//        private final String url;
//        private final int fileNumber;
//
//        public DownloadThread(String url) {
//            this.url = url;
//            this.fileNumber = ++lastFileNumber;
//        }
//
//        public int getFileNumber() {
//            return fileNumber;
//        }
//
//        @Override
//        public void run() {
//            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
//                 FileOutputStream out = new FileOutputStream("file" + fileNumber + ".dat")) {
//                byte[] dataBuffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//                    out.write(dataBuffer, 0, bytesRead);
//                }
//            } catch (IOException e) {
//                System.err.printf("Error downloading file %d from %s: %s%n", fileNumber, url, e.getMessage());
//            }
//        }
//
//        private static int lastFileNumber = 0;
//    }
//}
