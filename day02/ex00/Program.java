import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Program {

    private static final int FILE_HEADER_SIZE = 8;
    public static void main (String[] args) {

        // open file
        File file = new File("./signatures.txt");
        Map<ArrayList<String>, String> signature = new HashMap<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String tmp = sc.nextLine();
                signature.put(getValue(tmp), getKey(tmp));        // save info to map
            }
            System.out.println(signature);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // check file from stdin
        byte[] buf = new byte[FILE_HEADER_SIZE];
        ArrayList<String> fileSign = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String fileNameToCheck = sc.nextLine();
                if (fileNameToCheck.equals("42"))
                    break;
                File fileToCheck = new File(fileNameToCheck);
                FileInputStream in = new FileInputStream(fileToCheck);
                in.read(buf);
                for (byte b : buf) {
                    fileSign.add(String.format("%02X", b));
                }
                System.out.println(fileSign);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }



            // compare our file.Sign among signatures
            System.out.println(signature.get(fileSign));
        }
    }

    private static String getKey (String line) {
        String[] tmp = line.split(",");
        System.out.println(tmp[0]);
        return tmp[0];
    }

    private static ArrayList<String> getValue (String line) {
        String[] tmp = line.split(",");

        return new ArrayList<String>(Arrays.asList(tmp[1].trim().split("\\s+")));
    }
}
