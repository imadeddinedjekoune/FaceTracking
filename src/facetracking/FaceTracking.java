/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import facetracking.GUI.MainFrame;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import static facetracking.ImageUtils.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Imad
 */
public class FaceTracking 
{

    /**
     * @param args the command line arguments
     */
    
    public static Model m = Loader.loadModel();
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
    
}
