import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;

class OwnBash {
    private final File homeDir;
    private File currentDir;
    private final String COMMANDS = "Available commands:\nls - to display current folder content\ncd FOLDER_NAME - to change current directory\nmv WHAT WHERE - to transfer or rename a file\nexit - exit program";

    OwnBash(String path) throws IOException {
        homeDir = new File(Paths.get(path).toAbsolutePath().toString());
        if (!homeDir.isDirectory()) {
            throw new IOException("Provided path is not a valid directory. Please try again.");
        }
        currentDir = homeDir;
        System.out.println(currentDir.getCanonicalPath());
        System.out.println(COMMANDS);
    }

    void execCommand(String[] args) {
        if (args.length < 1) {
            return;
        }
        try {
            switch (args[0]) {
                case "ls" -> execLS();
                case "cd" -> execCD(args);
                case "mv" -> execMV(args);
                default -> System.out.println(COMMANDS);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void execLS () throws IOException {
        String[] tmp = currentDir.list();
        if (tmp == null) {
            return;
        }
        for (String x : tmp) {
            System.out.printf("%-40s ", x);
            long size = Files.size(Paths.get(currentDir + "/" + x));
            System.out.println(formatSize(size));
        }
    }

    public String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.2f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }

    private void execCD(String[] args) throws IOException {
        String newPath;
        if (args.length < 2 || "~".equals(args[1])) {
            newPath = homeDir.getPath();
        } else {
            newPath = currentDir.getPath().concat("/" + args[1]);
        }
        File newDir = new File(Paths.get(newPath).normalize().toString());
        if (newDir.isDirectory()) {
            currentDir = newDir;
        } else {
            throw new IOException("cd: not a directory: " + args[1]);
        }
        System.out.println(currentDir);
    }

    private void execMV(String[] args) throws NullPointerException, IOException {
        if (args.length < 3) {
            throw new IOException("mv: missing arguments. Usage: mv WHAT WHERE");
        }
        File source = new File(currentDir.getPath(), "/" + args[1]);
        Path dest = Paths.get(currentDir.getPath() + "/" + args[2] + "/" + args[1]).normalize();

        try {
            if (dest.toFile().getParentFile().isDirectory()) {
                Files.move(source.toPath(), dest, REPLACE_EXISTING, ATOMIC_MOVE);
            } else {
                Files.move(source.toPath(), source.toPath().resolveSibling(args[2]));
            }
        } catch (Exception e){
            throw new IOException("mv: invalid arguments, no such file or directory");
        }
    }
}
