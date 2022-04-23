package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Logic {

    public static int[][] get2dArray(String fileName, char b, char w) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(fileName));
        int[][] array = new int[image.getHeight()][image.getWidth()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                array[y][x] = image.getRGB(x, y) == Color.BLACK.getRGB() ? b : w;
            }
        }


        return array;
    }
}
