package ark.coding.brainteaser.bitmap_fill_color;

import java.util.Scanner;

public class Solution {
    public static class Bitmap {
        private int[][] mColors;//2d array

        public Bitmap(int width, int height) {
            mColors = new int[width][height];
        }

        /**
         *
         * @param x x cordinate of pixel
         * @param y y coordinate of pixel
         * @return color stored in the pixel
         */
        public int getPixel(int x, int y) {
            return mColors[x][y];
        }

        /**
         *
         * @param x x cordinate of pixel
         * @param y y coordinate of pixel
         * @param color color
         */
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
                    System.out.print(getPixel(j, i) + " ");
                }

                System.out.println("");
            }
        }
    }
    // Implementation Start (Feel free to use helper methods)

    //BItmap source should not change
    public static Bitmap fillColorInBitmap(Bitmap sourceImage, int x, int y, int color) {
        System.out.println("fillColorInBitmap");
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        //create a bitmap to change, initialize with source
        Bitmap coloredBitMap = new Bitmap(width, height);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                coloredBitMap.setPixel(i, j, sourceImage.getPixel(i, j));
                System.out.print(coloredBitMap.getPixel(j,i) + " ");
            }
            System.out.println("");
        }
        return fillContiguous(coloredBitMap, x, y, color, width, height);
    }

    private static Bitmap fillContiguous(Bitmap coloredBitMap, int x, int y, int color, int width, int height){
        int currentPixel = coloredBitMap.getPixel(x,y);//save the color of current pixel
        //change color of currentPixel
        coloredBitMap.setPixel(x,y, color);
        if(x+1 < width) {//boundary x+1
            if (coloredBitMap.getPixel(x + 1, y) == currentPixel) {
                fillContiguous(coloredBitMap, x + 1, y, color, width, height);//recursive approach
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
        int width = 5;//sc.nextInt();
        int height = 5;//sc.nextInt();

        System.out.println("Enter x,y and color for the pixel ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int color = sc.nextInt();
        System.out.println("Filling color: " + color + " at " + x + "," + y);
        Bitmap input = new Bitmap(width, height);
        input.mColors = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        System.out.println("--Input--");
        input.printBitmap();

        Bitmap output = fillColorInBitmap(input, x, y, color);

        System.out.println("--Output--");
        output.printBitmap();

        if (input == output) {
            System.out.println("Error: Same bitmap used!");
        }
    }

}
