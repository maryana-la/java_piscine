package edu.school21.printer.app;

import edu.school21.printer.logic.ArgsParsing;
import edu.school21.printer.logic.ImageConverter;

import com.beust.jcommander.JCommander;


public class Main {
    public static void main(String[] args) {
        ArgsParsing argv = new ArgsParsing();
        JCommander jc = new JCommander(argv);
        try {
            jc.parse(args);
            ImageConverter reader = new ImageConverter(argv.getPath(), argv.getBlack(), argv.getWhite());
            reader.read();
            reader.printImage();
        } catch (Exception e) {
            jc.usage();
            System.out.println(e.getLocalizedMessage());
        }
    }
}
