package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {
//    @Parameter
//    private String parameters = new String();

//    @Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
//    private Integer verbose = 1;

    @Parameter(names = "--white=")
    private String arg1;

    @Parameter(names = "--black=")
    private String arg2;
}
