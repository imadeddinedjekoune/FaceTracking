/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class CercleDeProccess extends JPanel
{
    private JLabel label ;
    private BufferedImage[] icos = new BufferedImage[8];
    private int index = 1;
    
    
    public CercleDeProccess()
    {
        try {
            icos[0] = ImageIO.read(new File("./res/Labels/i"+0+".png"));
            icos[1] = ImageIO.read(new File("./res/Labels/i"+1+".png"));
            icos[2] = ImageIO.read(new File("./res/Labels/i"+2+".png"));
            icos[3] = ImageIO.read(new File("./res/Labels/i"+3+".png"));
            icos[4] = ImageIO.read(new File("./res/Labels/i"+4+".png"));
            icos[5] = ImageIO.read(new File("./res/Labels/i"+5+".png"));
            icos[6] = ImageIO.read(new File("./res/Labels/i"+6+".png"));
            icos[7] = ImageIO.read(new File("./res/Labels/i"+7+".png"));
            
        } catch (Exception e) {
        }
        label = new JLabel();
        
        label.setIcon(new ImageIcon(icos[0]));
        add();
    }
    
    private void add()
    {
        setLayout(null);
        
        if (((index+1) % 2 == 0) || (index == 0))
        {
            label.setBounds(10,0,100,100);
        }else
        {
            if((index+1) != 6)
            {
                label.setBounds(0,0,100,100);
            }
        }
        
        add(label);
    }
    
    public void update ()
    {
        RepaintP();
        label.setIcon(new ImageIcon(icos[index++]));
        index = index %7 ;
        add();
    }
    
    private void RepaintP ()
    {
        removeAll();
        updateUI();
    }
    
    public void null_ ()
    {
        this.label.setIcon(null);
    }
    
}
