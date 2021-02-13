/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class DrawPanel extends JPanel
{
    private BufferedImage image ;
    private int xw , yh ;
    
    public DrawPanel(BufferedImage img , int width , int height)
    {
        image = img ;
        xw = width ;
        yh = height ;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image,0,0,xw,yh,null);
        
    };
    

    void setImage(BufferedImage image) {
        this.image = image ;
    }
}
