//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : Entity.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//
import java.awt.*;



public abstract class Entity
{
    public Color color;
    public double mass;
    public double speed;
    public StepStrategy strategy;
    public Vector direction;
    public Vector location;
    public int stepNumber ;
   
    
    public void step(double deltaTilme)
    {
        
    }

    public abstract void draw(Graphics2D g2d);
}
