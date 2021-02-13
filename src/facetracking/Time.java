/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetracking;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Imad
 */
public class Time 
{
    static Instant start , end ;
    public static void start ()
    {
        start = Instant.now();
    }
    
    public static void reset ()
    {
        
    }
    
    public static void aff ()
    {
        end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
    }
    
    public static String aff_ ()
    {
        end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        return "Time taken: "+ timeElapsed.toMillis() +" milliseconds";
    }
    
}
