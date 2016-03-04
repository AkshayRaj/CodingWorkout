package ark.coding.brainteaser.bitmap_fill_color;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Bitmap {
        private int[][] mColors;

        public Bitmap(int width, int height) {
            mColors = new int[width][height];
        }

        public int getPixel(int x, int y) {
            return mColors[x][y];
        }

        public void setPixel(int x, int y, int color) {
            mColors[x][y] = color;
        }

        public int getWidth() {
            return mColors.length;
        }

        public int getHeight() {
            if (mColors.length == 0) {
                return 0;
            }

            return mColors[0].length;
        }

        public void printBitmap() {
            int width = getWidth();
            int height = getHeight();
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    System.out.print(getPixel(i, j) + " ");
                }

                System.out.println("");
            }
        }
    }
    // Implementation Start (Feel free to use helper methods)

    public static Bitmap fillWithColor(Bitmap source, int x, int y, int color) {
        System.out.println("fillWithColor");
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap coloredBitMap = new Bitmap(width, height);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                coloredBitMap.setPixel(i, j, source.getPixel(i, j));
                System.out.print(coloredBitMap.getPixel(i, j) + " ");
            }
            System.out.println("");
        }
        return fillContiguous(coloredBitMap, x, y, color, width, height);
    }

    private static Bitmap fillContiguous(Bitmap coloredBitMap, int x, int y, int color, int width, int height){
        System.out.println("fillContiguous");
        int currentPixel = coloredBitMap.getPixel(x,y);
        coloredBitMap.setPixel(x,y, color);
        if(x+1 < width) {
            if (coloredBitMap.getPixel(x + 1, y) == currentPixel) {
                fillContiguous(coloredBitMap, x + 1, y, color, width, height);
            }
        }
        if(x-1 >= 0) {
            if (coloredBitMap.getPixel(x - 1, y) == currentPixel) {
                fillContiguous(coloredBitMap, x - 1, y, color, width, height);
            }
        }
        if(y+1 < height) {
            if (coloredBitMap.getPixel(x, y + 1) == currentPixel) {
                fillContiguous(coloredBitMap, x, y + 1, color, width, height);
            }
        }
        if(y-1 >= 0) {
            if (coloredBitMap.getPixel(x, y - 1) == currentPixel) {
                fillContiguous(coloredBitMap, x, y - 1, color, width, height);
            }
        }
        return coloredBitMap;
    }

// Implementation End

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter width of bitmap: ");
        int width = sc.nextInt();
        System.out.println("Enter height of bitmap: ");
        int height = sc.nextInt();

        System.out.println("Enter x,y and color for the pixel ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int color = sc.nextInt();
        System.out.println("Filling color: " + color + " at " + x + "," + y);
        Bitmap input = new Bitmap(width, height);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.println("Enter color for " + i + ", " + j + ":");
                input.setPixel(i, j, sc.nextInt());
            }
        }

        System.out.println("--Input--");
        input.printBitmap();

        Bitmap output = fillWithColor(input, x, y, color);

        System.out.println("--Output--");
        output.printBitmap();

        if (input == output) {
            System.out.println("Error: Same bitmap used!");
        }
    }

}
