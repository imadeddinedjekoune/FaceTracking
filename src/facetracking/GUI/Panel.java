package facetracking.GUI;

import facetracking.Time;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class Panel extends JPanel{
    private BufferedImage image ;
    private int xw , yh ;
    private JButton detect = new JButton("Track");
    private ArrayList<Rectangle> res = new ArrayList<Rectangle>();
    
    public Panel(BufferedImage img )
    {
        setBackground(Color.gray);
        image = img ;
        xw = image.getWidth() ;
        yh = image.getHeight();
        setSize(xw,yh);
        add(detect);
        
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image,0,0,xw,yh,null);
        g.setColor(Color.red);
        for (int i = 0 ; i < res.size() ; i++ )
        {
            g.draw3DRect(res.get(i).x-1, res.get(i).y-1, res.get(i).width+2, res.get(i).height+2, false);
            g.draw3DRect(res.get(i).x, res.get(i).y, res.get(i).width, res.get(i).height, false);
            g.draw3DRect(res.get(i).x+1, res.get(i).y+1, res.get(i).width-2, res.get(i).height-2, false);
        }
        
    };
    
    public void detectFaces ()
    {
        res = facetracking.FaceTracking.m.getFaces(image);
        repaint();
    }
    
    public BufferedImage saveImage() 
    {
	BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = image.createGraphics();
	paint(g2);
	return image ;
    }
    
}
