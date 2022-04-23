package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Logic {

    public static int[][] get2dArray(char b, char w) throws IOException {
        BufferedImage image = ImageIO.read(Objects.requireNonNull(Logic.class.getResource("/resources/it.bmp")));
        int[][] array = new int[image.getHeight()][image.getWidth()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                array[y][x] = image.getRGB(x, y) == Color.BLACK.getRGB() ? b : w;
            }
        }

        return array;
    }
}
