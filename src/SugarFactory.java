//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : SugarFactory.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//

import java.awt.Color;



public class SugarFactory extends FoodFactory
{

    @Override
    public Food createFood(Enviroment env)
    {
        return new Sugar(0,0,0,Color.BLACK,0);
    }
}