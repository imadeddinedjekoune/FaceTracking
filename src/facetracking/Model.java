/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Imad
 */
public class Model 
{
    private ArrayList<Classifier> classifiers ;
    private Point size;
    private static float start              = 1 ;
    private static float inc_val            = 1.25f ;
    private static float s_factor           = 0.1f ;
    private static int   closes_face_factor = 50 ;
    
    
    public Model(ArrayList<Classifier> classifiers, Point size) {
        this.classifiers = classifiers;
        this.size = size;
    }
    
    public ArrayList<java.awt.Rectangle> getFaces(BufferedImage image)
    {
        ArrayList<Rectangle> ret=new ArrayList<Rectangle>();
        int width=image.getWidth();
        int height=image.getHeight();
        float maxScale = (Math.min((width)/size.x,(height)/size.y));
        
        int final_rs[][][] = ImageUtils.imageOperation_MinimizedImage_IttergralImage_SquaredImage(image);
        int[][] integralImage  = final_rs[1];
        int[][] MinimizedImage = final_rs[0];
        int[][] SquaredImage   = final_rs[2];
        
        
        for(float scale=start;scale<maxScale;scale*=inc_val)
        {
            int step=(int) (scale*24*s_factor);
            int size=(int) (scale*24);
            
            for(int i=0;i<width-size;i+=step)
            {
                for(int j=0;j<height-size;j+=step)
                {
                    boolean pass = true;
                    
                    for(Classifier s:classifiers)
                    {
                        if(!s.pass(integralImage,SquaredImage,i,j,scale))
                        {
                            pass=false;
                            break;
                        }
                    }
                    
                    if(pass) 
                    {
                        ret.add(new Rectangle(i,j,size,size));
                    }
                }
            }
        }
        
        return Utils.getRectPlusProche(Utils.mix(Utils.inclusion(ret)), closes_face_factor);
        
    }
    public static void setParameters (float s , float i , float sf , int cf)
    {
        start       = s ;      
        inc_val          = i ;
        s_factor          = sf;
        closes_face_factor = cf;
    }
}
