package edu.school21.printer.app;


import edu.school21.printer.logic.ImageConverter;

public class Main {
    private final static String ERROR_USAGE = "Please provide correct arguments. Example: --white= --black= --path=";
    public static void main(String[] args) {
        if (!checkArgsValidity(args)) {
            System.out.println(ERROR_USAGE);
            System.exit(-1);
        }
        ImageConverter reader = new ImageConverter(args[2].substring(args[2].indexOf('=') + 1), args[1].charAt(8), args[0].charAt(8));
        reader.read();
        reader.printChars();
    }

    private static boolean checkArgsValidity(String[] args) {
        if (args.length != 3) {
            return false;
        }
        return args[0].startsWith("--white=") && args[1].startsWith("--black=") && args[2].startsWith("--path=");
    }
}
