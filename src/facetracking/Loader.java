/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Imad
 */
public class Loader 
{
    public static Model loadModel ()
    {
        ArrayList<Classifier> classifiers = new ArrayList<>();
        try {
            File myObj = new File("./res/openCV_Classifier_Default.bin");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                String data = myReader.nextLine();
                String split_0 [] = data.split("f ");
                
                ArrayList <Feature> features = new ArrayList<>();
                
                for (int i = 1 ; i < split_0.length ; i++ )
                {
                    // Getting Quadtants //
                    String split_1[] = split_0[i].split("R ");
                    Quadrant quadrant[] = new Quadrant[3];
                    
                    for (int j = 1 ; j < split_1.length-1 ; j++ )
                    {
                        String count[] = split_1[j].split(" ") ;
                        int x1 = Integer.parseInt(count[0]) ;
                        int x2 = Integer.parseInt(count[1]) ;
                        int y1 = Integer.parseInt(count[2]) ;
                        int y2 = Integer.parseInt(count[3]) ;
                        int weight = (int)(Float.parseFloat(count[4])) ;
                        
                        quadrant[j-1] = new Quadrant(x1, x2, y1, y2, weight);
                    }
                    
                    // Getting Other Fields //
                    String count[] = split_1[split_1.length-1].split(" ");
                    
                    features.add(new Feature(quadrant, Integer.parseInt(count[0]) , 
                            Float.parseFloat(count[1]), Float.parseFloat(count[2]), Float.parseFloat(count[3]),
                            new Point(Integer.parseInt(count[4]),Integer.parseInt(count[5])),
                            Integer.parseInt(count[6]), Integer.parseInt(count[7]), Boolean.parseBoolean(count[8]),
                            Boolean.parseBoolean(count[9])));
                }
                classifiers.add(new Classifier(features, Float.parseFloat(split_0[0])));
            }
        myReader.close();
        } catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new Model(classifiers, new Point(24,24));
    }
}
