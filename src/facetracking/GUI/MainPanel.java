package facetracking.GUI;

import facetracking.ImageUtils;
import facetracking.Model;
import facetracking.Time;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Imad
 */
public class MainPanel extends JPanel implements KeyListener
{
    private Panel p ;
    private DrawPanel dp ;
    private JButton b_Track ;
    private JTextField t1 ,t2 ,t3 ,t4;
    private JLabel l1 , l2 , l3 , l4 ;
    private BufferedImage img ;
   
    private void init (BufferedImage image)
    {
        addKeyListener(this);
        setFocusable(true);
        img = image ;
        p = new Panel(img);
        
        int cap = (int)(MainFrame.width-MainFrame.width/1.015f) ;
        dp = new DrawPanel(image,MainFrame.width-MainFrame.width/4, MainFrame.height-MainFrame.height/8);
        dp.setBounds(cap,cap,MainFrame.width-MainFrame.width/4, MainFrame.height-MainFrame.height/8);
        dp.setBorder(BorderFactory.createEtchedBorder());
        
        b_Track = new JButton("Detect");
        b_Track.setBounds(MainFrame.width-MainFrame.width/8, 300, 80, 30);
        
        t1 = new JTextField("3");
        t1.setBounds(MainFrame.width-MainFrame.width/7, 100, 120, 30);
        
        t2 = new JTextField("1.25");
        t2.setBounds(MainFrame.width-MainFrame.width/7, 150, 120, 30);
        
        t3 = new JTextField("0.1");
        t3.setBounds(MainFrame.width-MainFrame.width/7, 200, 120, 30);
        
        t4 = new JTextField("150");
        t4.setBounds(MainFrame.width-MainFrame.width/7, 250, 120, 30);
        
        l1 = new JLabel("Start Point");
        l1.setBounds(MainFrame.width-MainFrame.width/5, 100, 120, 30);
        
        l2 = new JLabel("Inc Value");
        l2.setBounds(MainFrame.width-MainFrame.width/5, 150, 120, 30);
        
        l3 = new JLabel("Scale Factor");
        l3.setBounds(MainFrame.width-MainFrame.width/5, 200, 120, 30);
        
        l4 = new JLabel("Closes Face");
        l4.setBounds(MainFrame.width-MainFrame.width/5, 250, 120, 30);
        
    }
    
    private void add()
    {
        setLayout(null);
        add(b_Track);
        add(dp);   
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
    }
    
    public MainPanel (BufferedImage image)
    {
        init(image); 
        listeners();
        add();
    }
    
    private void listeners ()
    {
        b_Track.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Thread t = new Thread(() ->{
                    try {
                        Model.setParameters(Float.parseFloat(t1.getText()), Float.parseFloat(t2.getText()), 
                            Float.parseFloat(t3.getText()), Integer.parseInt(t4.getText()));
                            p.detectFaces();
                            img = p.saveImage() ;
                            dp.setImage(img);
                            dp.repaint();
                            repaint_this();
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null,"Error : "+ee);
                    }
                });
                t.start();
                
                Thread t2 = new Thread(() ->{
                    WaitingFRAME wf = new WaitingFRAME();
                    while (t.isAlive())
                    {
                        
                    }
                    wf.exit();
                    JOptionPane.showMessageDialog(null,Time.aff_());
                });
                t2.start();
                
            }
        });
    }
    
    public void setImage (BufferedImage img)
    {
        this.img = img ;
    }
    
    public BufferedImage getImage ()
    {
        return img ;
    }
    
    public void repaint_this ()
    {
        removeAll();
        updateUI();
        add();
    }
    
    public void repaint_this_img ()
    {
        removeAll();
        updateUI();
        init(img);
        listeners();
        add();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a')
        {
            t4.setText("50");
        }
        if (e.getKeyChar() == 'z')
        {
            t1.setText("2");
            t4.setText("100");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }
}
