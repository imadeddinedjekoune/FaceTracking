/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Imad
 */
public class Utils 
{
    public static ArrayList<ArrayList<Rectangle>> inclusion (ArrayList <Rectangle> rect)
    {
        int i = 0 , j ;
        boolean deja_traiter[] = new boolean[rect.size()] ;
        ArrayList<ArrayList<Rectangle>> total = new ArrayList<ArrayList<Rectangle>>();
        
        while (i < rect.size())
        {
            total.add(new ArrayList<>());
            total.get(i).add(rect.get(i));
            j = i+1 ;
            
            while (j < rect.size())
            {
                if (deja_traiter[i])
                {
                    total.get(i).clear();
                    break ;
                }
                
                if (!(etatRect(rect.get(i), rect.get(j)) == 0)) 
                {
                    total.get(i).add(rect.get(j));
                    deja_traiter[j] = true ;
                }
                j++;
            }
            i++;
        }
        
        return total;
    }
    
    public static boolean inside (Rectangle r1 , Rectangle r2)
    {
        if ( (r1.x <= r2.x) && ((r1.x+r1.width) >= (r2.x+r2.width)) &&
             (r1.y <= r2.y) && ((r1.y+r1.height) >= (r2.y+r2.height)) )
            return true ;
        else
            return false ;
        
    }
    
    public static int etatRect (Rectangle r1 , Rectangle r2)
    {
        if (inside(r1, r2))
            return 1 ;
        else
            if (inside(r2, r1))
                return -1 ;
            else
                return 0 ;
    }
    
    public static ArrayList<Rectangle> mix (ArrayList<ArrayList<Rectangle>> total)
    {
        ArrayList <Rectangle> ret = new ArrayList<>();
        int i , j ;
        
        for ( i = 0 ; i < total.size() ; i++ )
        {
            if (total.get(i).size() > 1)
            {
                
                Rectangle r_mix = total.get(i).get(0);
                for (j = 1 ; j < total.get(i).size() ; j++)
                {
                    r_mix.x += total.get(i).get(j).x ;
                    r_mix.y += total.get(i).get(j).y ;
                    r_mix.width += total.get(i).get(j).width ;
                    r_mix.height += total.get(i).get(j).height ;
                }
                r_mix.x /= j ;
                r_mix.y /= j ;
                r_mix.width /= j ;
                r_mix.height /= j ;
                ret.add(r_mix);
                
            }else
            {
                if (total.get(i).size() != 0)
                {
                    ret.add(total.get(i).get(0));
                }
            }
        }
       
        return ret ;
    }
    
    
    public static boolean RectPlusProche (Rectangle r1 , Rectangle r2 , int amt )
    {
        if ((Math.abs(r1.x - r2.x) < amt) &&
            (Math.abs(r1.y - r2.y) < amt) &&
            (Math.abs(r1.width - r2.width) < amt) &&
            (Math.abs(r1.height - r2.height) < amt) 
            )
        {
            return true ;
        }else
        {
            return false; 
        }
    }
    
    public static ArrayList<Rectangle> getRectPlusProche (ArrayList<Rectangle> rectangles , int amt)
    {
        int i , j , nbIter = 0;
        for ( i = 0 ; i < rectangles.size()-1 ; i++ )
        {
            Rectangle r = rectangles.get(i);
            for ( j = i+1 ; j < rectangles.size() ; j++ )
            {
                if (RectPlusProche(r, rectangles.get(j), amt))
                {
                    rectangles.remove(j);
                    j--;
                }
            }
        }
        
        return rectangles ;
    }
    
}
