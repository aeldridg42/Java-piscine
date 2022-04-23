package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("wrong number of arguments");
            System.exit(-1);
        }

        char w = args[0].charAt(0);
        char b = args[1].charAt(0);

        int[][] image = Logic.get2dArray(b, w);

        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[0].length; x++) {
                System.out.print((char) image[y][x]);
            }
            System.out.println();
        }
    }
}
