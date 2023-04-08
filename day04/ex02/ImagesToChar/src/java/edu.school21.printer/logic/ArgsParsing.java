package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ArgsParsing {

    @Parameter(names = "--white")
    private String white;

    @Parameter(names = "--black")
    private String black;

    @Parameter(names = "--path")
    private String path;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public String getPath() {
        return path;
    }
}