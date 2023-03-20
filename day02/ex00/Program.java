import java.io.*;
import java.util.*;
public class Program {

    private static final int FILE_HEADER_SIZE = 8;
    private static final String SIGN_FILE_NAME = "signatures.txt";
    private static final String RESULT_FILE_NAME = "result.txt";
    private static FileOutputStream result;
    private static Map<String, String> signature;

    public static void main (String[] args) {
        signature= new HashMap<>();
        try {
            result = new FileOutputStream(RESULT_FILE_NAME);
            readFile();
            checkFilesSignatureFromSTDIN();
            result.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readFile() throws IOException {
        FileInputStream signFile = new FileInputStream(SIGN_FILE_NAME);
        Scanner sc = new Scanner(signFile);
        while (sc.hasNextLine()) {
            String tmp = sc.nextLine();
            if ("".equals(tmp))
                continue;
            signature.put(getKey(tmp), getValue(tmp));
        }
        signFile.close();
    }

    private static void checkFilesSignatureFromSTDIN () {
        byte[] buf = new byte[FILE_HEADER_SIZE];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String fileToCheckSign = "";
            String fileNameToCheck = sc.nextLine();
            if (fileNameToCheck.equals("42"))
                break;

            try {
                FileInputStream in = new FileInputStream(fileNameToCheck);
                in.read(buf);
                for (byte b : buf) {
                    fileToCheckSign += String.format("%02X", b) + " ";
                }
                if (!checkIfSignatureExist(fileToCheckSign)) {
                    System.out.println("UNDEFINED");
                }
            System.out.println(fileToCheckSign);
            } catch (Exception e) {
                System.out.println("UNDEFINED");
            }
        }
    }

    private static String getKey(String line) {
        return line.substring(line.indexOf(',') + 1).trim();
    }

    private static String getValue(String line) {
        return line.substring(0,line.indexOf(',')).trim();
    }

    private static Boolean checkIfSignatureExist(String fileToCheckSign) throws IOException {
        for (Map.Entry<String, String> node : signature.entrySet()) {
            if (fileToCheckSign.startsWith(node.getKey())) {
                result.write(node.getValue().getBytes());
                result.write('\n');
                System.out.println("PROCESSED");
                return true;
            }
        }
        return false;
    }
}
