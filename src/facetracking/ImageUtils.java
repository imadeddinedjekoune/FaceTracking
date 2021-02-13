/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Imad
 */
public class ImageUtils 
{
    public static BufferedImage readImage (String path)
    {
        BufferedImage image = null ;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return image ;
    }
    
    public static void writeImage (BufferedImage bufferedImage , String path)
    {
        try {
            ImageIO.write(bufferedImage, "JPG", new File (path));
        } catch (Exception e) {
        }
    }
    
    public static BufferedImage createImage (int gridRGB[][])
    {
        BufferedImage image = new BufferedImage(gridRGB.length, gridRGB[0].length,  5);
        for (int i = 0 ; i < image.getWidth() ; i++ )
        {
            for (int j = 0 ; j < image.getHeight() ; j++ )
            {
                image.setRGB(i, j, gridRGB[i][j]);
            }
        }
        return image ;
    }
    
    public static int [][] createGrid (BufferedImage img)
    {
        int grid [][] = new int [img.getWidth()][img.getHeight()] ;
        for (int i = 0 ; i < img.getWidth() ; i++ )
        {
            for (int j = 0 ; j < img.getHeight() ; j++ )
            {
                grid[i][j] = img.getRGB(i, j);
            }
        }
        return grid; 
    }
    
    public static void printImage (int [][] img)
    {
        for (int i = 0 ; i < img.length ; i++ )
        {
            for (int j = 0 ; j < img[0].length ; j++ )
            {
                System.out.print(img[i][j]+" ");
            }
            System.out.println("");
        }
    }    
    
    public static int [][][] imageOperation_MinimizedImage_IttergralImage_SquaredImage (BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        
        int ret[][][] = new int [3][width][height];
        
        for(int i=0;i<width;i++)
        {
            int col=0;
            int col2=0;
            for(int j=0;j<height;j++)
            {
                int c = image.getRGB(i,j);
                int red = (c & 0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;
                int value=(30*red +59*green +11*blue)/100;
                ret[0][i][j] = value;
                ret[1][i][j]=(i>0?ret[1][i-1][j]:0)+col+value;
                ret[2][i][j]=(i>0?ret[2][i-1][j]:0)+col2+value*value;
                col += value;
                col2 += value*value;
            }
        }
        return ret ;
    }
    
}
