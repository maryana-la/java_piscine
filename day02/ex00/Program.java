import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Program {

    private static final byte[] PNG_HEADER = {
            (byte) 0x89,
            (byte) 0x50, (byte) 0x4E, (byte) 0x47,
            (byte) 0x0D, (byte) 0x0A,
            (byte) 0x1A,
            (byte) 0x0A
    };

    private static final int FILE_HEADER_SIZE = PNG_HEADER.length;
    public static void main (String[] args) {

        // open file
        File file = new File("./signatures.txt");
        Map <String, ArrayList<String>> signature = new HashMap<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String tmp = sc.nextLine();
                signature.put(getKey(tmp), getValue(tmp));
            }
            System.out.println(signature);
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        }

        // save info to map







        // check file from stdin
        byte[] buf = new byte[FILE_HEADER_SIZE];


        Scanner sc = new Scanner(System.in);
        try {
            String fileNameToCheck = sc.nextLine();
            File fileToCheck = new File(fileNameToCheck);
            FileInputStream in = new FileInputStream(fileToCheck);
            System.out.println(in.read(buf));
            System.out.println(Arrays.toString(buf));
            for (byte b : buf) {
                String st = String.format("%02X", b);
                System.out.print(st + " ");
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getKey (String line) {
        String[] tmp = line.split(",");
        System.out.println(tmp[0]);
        return tmp[0];
    }

    private static ArrayList<String> getValue (String line) {
        String[] tmp = line.split(",");
        String[] tmp1 = tmp[1].split(" ");

        return null;
    }
}
