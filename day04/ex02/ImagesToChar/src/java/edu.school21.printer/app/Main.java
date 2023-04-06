package edu.school21.printer.app;

import edu.school21.printer.logic.ImageConverter;
import edu.school21.printer.logic.Args;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.*;

public class Main {
    private final static String ERROR_USAGE = "Please provide correct arguments. Example: --white= --black= --path=";
    public static void main(String[] args) {
//        if (!checkArgsValidity(args)) {
//            System.out.println(ERROR_USAGE);
//            System.exit(-1);
//        }
        Args arg = new Args();

        JCommander jc = new JCommander(arg);
        try {
            jc.parse(args);
            Args arguments = new Args();

        } catch (Exception e) {
            jc.usage();
            System.out.println(e.getLocalizedMessage());
        }
//    addObject(args)
//                .build()
//                .parse(argv);





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
