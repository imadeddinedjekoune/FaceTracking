/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.awt.Point;

/**
 *
 * @author Imad
 */
public class Feature 
{    
    Quadrant[] rects;
    int nb_rects;
    float threshold;
    float left_val;
    float right_val;
    Point size;
    int left_node;
    int right_node;
    boolean has_left_val;
    boolean has_right_val;

    public Feature(Quadrant[] rects, int nb_rects, float threshold, float left_val, float right_val, Point size, int left_node, int right_node, boolean has_left_val, boolean has_right_val) {
        this.rects = rects;
        this.nb_rects = nb_rects;
        this.threshold = threshold;
        this.left_val = left_val;
        this.right_val = right_val;
        this.size = size;
        this.left_node = left_node;
        this.right_node = right_node;
        this.has_left_val = has_left_val;
        this.has_right_val = has_right_val;
    }
    
    public int getLeftOrRight(int[][] IntImg, int[][] squares, int i, int j, float scale) 
    {
        int w = (int)(scale*size.x);
        int h = (int)(scale*size.y);
        double inverse_surf = 1.0f/(w*h);
        
        int total_x = IntImg[i+w][j+h]    + IntImg[i][j]   - IntImg[i][j+h]  - IntImg[i+w][j];
        int total_x2= squares[i+w][j+h]   + squares[i][j]  - squares[i][j+h] - squares[i+w][j];
        
        double moy = total_x*inverse_surf;
        double vnorm = total_x2*inverse_surf-moy*moy;
        vnorm = (vnorm>1)?Math.sqrt(vnorm):1;

        int rect_sum=0;
        for(int k=0;k<nb_rects;k++)
        {
                Quadrant r = rects[k];
                int rx1=i+(int) (scale*r.getX1());
                int rx2=i+(int) (scale*(r.getX1()+r.getY1()));
                int ry1=j+(int) (scale*r.getX2());
                int ry2=j+(int) (scale*(r.getX2()+r.getY2()));
                rect_sum+=(int)((IntImg[rx2][ry2]-IntImg[rx1][ry2]-
                    IntImg[rx2][ry1]+IntImg[rx1][ry1])*r.getWeight());
        }
        double rect_sum2=rect_sum*inverse_surf;

        return (rect_sum2<threshold*vnorm)?0:1;
    }
    
    public float getVal(int[][] IntImg, int[][] squares, int i, int j, float scale) {
	
	int val = this.getLeftOrRight(IntImg, squares, i, j, scale);
        if(val==0)
        {
            if(this.has_left_val)
            {
                return this.left_val;
            }
        }
        else
        {
            if(this.has_right_val)
            {
                return this.right_val;
            }
        }
        return 0 ;
    }

    @Override
    public String toString() {
        return nb_rects + " " + threshold + " " + left_val + " " + right_val + " " + size.x+" "+size.y + " " + left_node + " " + right_node + " " + has_left_val + " " + has_right_val + " ";
    } 
    
}
