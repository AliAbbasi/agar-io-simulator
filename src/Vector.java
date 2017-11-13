//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : Vector.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//


public class Vector
{
    public double x;
    public double y;
    
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Vector normalize() 
    {
        Vector location = new Vector(this.x , this.y);
    	location.x = x/Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    	location.y = y/Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        return location;
    }
    
    public double distanceTo(Vector other)
    {
        return Math.sqrt(  (other.x - this.x )  *  (other.x - this.x )+
                           (other.y - this.y )  *   (other.y - this.y));
    }
    
    
    
}