//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : OrganismFactory.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//

import java.awt.Color;



public class OrganismFactory extends FoodFactory
{
    @Override
    public Food createFood(Enviroment env)
    {
	return new Organism(0,0,0,Color.BLACK);
    }
}
