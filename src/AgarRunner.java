//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : AgarRunner.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class AgarRunner 
{
    public JFrame frame;
    public Enviroment enviroment;
    public Display display;
   
            
  
    
  
    public static void main(String[ ] args) throws IOException, InterruptedException
    {
       
        int windowWidth = 1050;
        int windowHeight = 720;
        int numberOfCells = 8;
        int numberOfFood = 100;
        BufferedImage logoFile;
        logoFile = ImageIO.read(new File("Logo.bmp"));
        
        int leftPanelWidth = 1;
        ////////////////////////
        Enviroment enviroment = new Enviroment( windowWidth ,  windowHeight,  numberOfCells,  numberOfFood,  logoFile, leftPanelWidth);
       
        JFrame frame = new JFrame ( "Agarion" ) ;
        Display display ;
        display = new Display( enviroment );

        

        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( display );
        frame.pack();
        frame.setVisible( true ); 
        
       
        while ( true )
        {
            enviroment.stepAll(50) ;
            display.repaint()      ;
            Thread.sleep( 50 )     ;
        }

         
    }
}
