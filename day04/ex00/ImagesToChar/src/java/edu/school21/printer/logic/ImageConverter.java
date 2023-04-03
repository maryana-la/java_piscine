package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConverter {
    private final String imgFile;
    private char[][] imageArray;
    private final char black;
    private final char white;
    private int height;
    private int width;


    public ImageConverter(String imgFile, char black, char white) {
        this.imgFile = imgFile;
        this.black = black;
        this.white = white;
    }
    public void read() {
        try {
            BufferedImage img = ImageIO.read(new File(imgFile));

            height = img.getHeight();
            width = img.getWidth();

            imageArray = new char[width][height];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    char color = (char)((r + g + b) / 3); // Convert the RGB color to a char value
                    imageArray[x][y] = color;
                }
            }
            img.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void printChars() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (imageArray[j][i]) {
                    case 0 -> System.out.print(black);
                    case 255 -> System.out.print(white);
                    default -> System.out.print('?');
                }
            }
            System.out.println();
        }
    }

    public char[][] getImageArray() {
        return imageArray;
    }
}