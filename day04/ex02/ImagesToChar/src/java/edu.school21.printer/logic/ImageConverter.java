package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConverter {
    private final String imgFile;
    private char[][] imageArray;
    private final String black;
    private final String white;
    private int height;
    private int width;


    public ImageConverter(String imgFile, String black, String white) {
        this.imgFile = imgFile;
        this.black = black;
        this.white = white;
    }
    public void read() {
        try {
            BufferedImage img = ImageIO.read(new File(imgFile));

            height = img.getHeight();
            width = img.getWidth();

            imageArray = new char[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = img.getRGB(j, i);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    imageArray[i][j] = (char)(((r + g + b) / 3) == 0 ? 0 : 255);

                }
            }
            img.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void printImage() {
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();
        Ansi.BColor blackColour = Ansi.BColor.valueOf(black);
        Ansi.BColor whiteColour = Ansi.BColor.valueOf(white);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (imageArray[i][j]) {
                    case 0 -> cp.print("   ", Ansi.Attribute.NONE, Ansi.FColor.NONE, blackColour);
                    case 255 -> cp.print("   ", Ansi.Attribute.NONE, Ansi.FColor.NONE, whiteColour);
                }
            }
            cp.clear();
            System.out.println();
        }
        cp.clear();
    }

    public char[][] getImageArray() {
        return imageArray;
    }
}
