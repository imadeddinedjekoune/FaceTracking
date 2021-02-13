/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.util.ArrayList;

/**
 *
 * @author Imad
 */
public class Classifier 
{
    public ArrayList<Feature> features ;
    float threshold;

    public Classifier(ArrayList<Feature> features, float threshold) {
        this.features = features;
        this.threshold = threshold;
    }
    
    public boolean pass(int[][] IntImg, int[][] squares, int i, int j, float scale) {
	float sum=0;
	for(Feature t : features)
	{
            sum+=t.getVal(IntImg, squares,i, j, scale);
	}
        return sum>threshold;
    }
    
}
