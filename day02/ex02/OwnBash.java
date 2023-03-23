import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;


class OwnBash {
    private final File homeDir;

    private File currentDir;

    OwnBash(String path) {
        homeDir = new File(Paths.get(path).toAbsolutePath().toString());
        currentDir = homeDir;
        System.out.println(currentDir.getPath());
    }

    void execCommand(String[] args) {
        if (args.length < 1) {
            return;
        }
        try {
            switch (args[0]) {
                case "ls" -> execLS();
                case "exit" -> execEXIT();
                case "cd" -> execCD(args);
                case "mv" -> execMV(args);
            }
        } catch (Exception e) {
            System.out.println("Error bash " + e.getMessage());
        }
    }

    private void execLS () throws IOException {
        String[] tmp = currentDir.list();
//        assert tmp != null; //todo check what is it
        for (String x : tmp) {
            System.out.print(x + " ");
            long size = Files.size(Paths.get(currentDir + "/" + x));
            System.out.println(formatSize(size));
        }
    }

    public String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }

    private void execEXIT() {
        System.exit(0);
    }

    private void execCD(String[] args) throws NullPointerException {
        if (args[1] == null) {
            throw new NullPointerException();
        }
        String newPath;
        if (args[1].equals("~")) {
            newPath = homeDir.getPath();
        } else {
            newPath = currentDir.getPath().concat("/" + args[1]);
        }

        File newDir = new File(Paths.get(newPath).normalize().toString());
        if (newDir.isDirectory()) {
            currentDir = newDir;
        }
        System.out.println(currentDir);
    }

    private void execMV(String[] args) throws NullPointerException, IOException {
        if (args[1] == null || args[2] == null) {
            throw new NullPointerException();
        }


        Path source = Paths.get(currentDir.getPath() + "/" + args[1]);
        Path dest = Paths.get(currentDir.getPath() + "/" + args[2] + "/" + args[1]).normalize();
        File destination = new File(currentDir.getPath() + "/" + args[2]);

        if (destination.isDirectory()) {
            Files.move(source, dest, REPLACE_EXISTING, ATOMIC_MOVE);
        } else {
            Files.move(source, source.resolveSibling(args[2]));
        }
    }
}
