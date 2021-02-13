/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking.GUI;

import facetracking.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Imad
 */
public class MainFrame extends JFrame implements ActionListener
{
    public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width 
            , height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private MainPanel mp ;
    private JMenuBar mb = new JMenuBar();
    private JMenu m = new JMenu("Utilities");
    private JMenuItem i1 = new JMenuItem("Upload Picture");
    private JMenuItem i2 = new JMenuItem("Save Picture");
    
    
    public MainFrame() 
    {
        mp = new MainPanel(ImageUtils.readImage("./res/face2.jpg"));
        
        mb.add(m);
        m.add(i1);
        m.add(i2);
        
        setTitle("Face Recognition");
	setResizable(false);
        setSize(width,height);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
        listeners();
        setLayout(new BorderLayout());
        add(mp,BorderLayout.CENTER);
        add(mb,BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == i1)
        {
            JFileChooser j = new JFileChooser("./res/"); 
            int result = j.showSaveDialog(null); 
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                String path = j.getSelectedFile().getAbsolutePath();
                mp.setImage(ImageUtils.readImage(path));
                mp.repaint_this_img();
            }
        }
        
        if (e.getSource() == i2)
        {
            JFileChooser j = new JFileChooser("./res/"); 
            int result = j.showSaveDialog(null); 
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                String path = j.getSelectedFile().getAbsolutePath();
                ImageUtils.writeImage(mp.getImage(), path+".jpg");
            }
        }
    }

    private void listeners() 
    {
        i1.addActionListener(this);
        i2.addActionListener(this);
    }
    
}