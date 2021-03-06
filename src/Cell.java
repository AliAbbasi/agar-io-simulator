//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : Cell.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//



public abstract class Cell extends Entity
{
    public String name;
    public int foodEaten;
    public int cellsSwallowed;
    
    public void addMass( double additionalMass )
    {
        mass += additionalMass;
    }

    public void removeMass( double reductionalMass )
    {
        mass -= reductionalMass;
    }

}
