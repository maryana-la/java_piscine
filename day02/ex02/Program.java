import java.util.Scanner;

public class Program {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Please enter a a folder path. Example of usage: java Program ~/Desktop/folder");
            System.exit(-1);
        }

        OwnBash unix = new OwnBash(args[0]);
        Scanner sc = new Scanner(System.in);
        System.out.print("-->>> ");
        try {
            while (sc.hasNext()) {

                String line = sc.nextLine();
                String[] splitArgs = line.trim().split("\\s+");
                unix.execCommand(splitArgs);
                System.out.print("-->>> ");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }




}
