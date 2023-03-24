import java.util.Scanner;

public class Program {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Please enter an absolute folder path. Example of usage: java Program FOLDER_PATH");
            System.exit(-1);
        }
        Scanner sc = new Scanner(System.in);

        try {
            OwnBash unix = new OwnBash(args[0]);
            System.out.print("-->>> ");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitArgs = line.trim().split("\\s+");
                if ("exit".equals(splitArgs[0])) {
                    sc.close();
                    System.exit(0);
                }
                unix.execCommand(splitArgs);
                System.out.print("-->>> ");
            }
        } catch (Exception e) {
            System.out.println("ERROR. " + e.getMessage());
            sc.close();
            System.exit(-1);
        }
    }
}
