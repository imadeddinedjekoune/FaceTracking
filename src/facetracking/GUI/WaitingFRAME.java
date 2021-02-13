/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking.GUI;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Imad
 */
public class WaitingFRAME extends JFrame implements ActionListener
{
    private static final int width = 380 , height = 160;
    private CercleDeProccess c = new CercleDeProccess();
    private Timer t = new Timer(100, this);
    public WaitingFRAME ()
    {
        setTitle("Traitement");
	setResizable(false);
        setSize(width,height);
	setVisible(true);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
        setLayout(null);
        c.setBounds(150,10,80,80);
        add(c);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        c.update();
    }
    
    public void exit()
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}