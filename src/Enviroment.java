//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : Enviroment.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Enviroment 
{
    public int windowWidth;
    public int windowHeight;
    public int leftPanelWidth;
    public BufferedImage logo;
    public int numberOfSteps;
    public ArrayList<Entity> entities  = new ArrayList<>(); 
    public Random randNum; 
    public int numberOfFood;
    public int numberofCells;
    private String[] Names = { "Bellatrix", "Pallaton", "Quirina","Zandra",  "Calhoun", "Diomedes", "Eferhilda", "Finnegan", "Gualterio", "Harbin", "Itaghai", "Jelani", "Kimball", "Lothar", "Mordecai", "Nangwaya", "Ojore", "Ragnar", "Serilda", "Teutates", "Usbeorn", "Valasca", "Warenhari", "Xena", "Yakuza"  };
    


    public Enviroment(int windowWidth, int windowHeight, int numberOfCells, int numberOfFood, BufferedImage logoFile,int leftPanelWidth)
    {
       this.numberOfFood = numberOfFood;
       this.numberofCells = numberOfCells;
        try 
        { 
            logo = ImageIO.read(new File("Logo.bmp"));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int x;
        int y;
        randNum = new Random();
        Random rand = new Random();
      
        
        
        
        // a for loop for number of foods
        for (int i = 0; i < numberOfFood; i++) 
        {
            switch (randNum.nextInt(3))
            {
            case 0: //sugar
                x = randNum.nextInt(741) + 310; 
                y = randNum.nextInt(721)      ; 
                int rotate = randNum.nextInt(30) ;
                int sideHalfLength = rand.nextInt(5) + 8;
                Color color = (new Color((int)(Math.random() * 0x1000000)));
                entities.add(new Sugar(x,y,sideHalfLength,color,rotate));
                break;
            case 1: //organism
                x = randNum.nextInt(741) + 310;
                y = randNum.nextInt(721)      ; 
                int radius = rand.nextInt(5) + 10; 
                color = (new Color((int)(Math.random() * 0x1000000)));
                entities.add(new Organism(x,y,radius,color));
                break;
            case 2: //plasma
                x = randNum.nextInt(741) + 310; 
                y = randNum.nextInt(721)      ; 
                radius = rand.nextInt(6) + 11; 
                entities.add(new Plasma(x,y,radius));
                break;
            }
        }
        
        
        
        //a loop for creating cells
        for (int i = 0; i < numberOfCells; i++)
        {
            x = randNum.nextInt(741) + 310; 
            y = randNum.nextInt(721)      ; 
            entities.add(new BasicCell(x,y,Names[i]));
        }
        
        
    }

    
    
    
    public String generateName()
    {
        return "test";
    }

    public StepStrategy generateSugarStepStrategy(Sugar sugar)
    {
        StepStrategy strategy = new StandStill();
        strategy.step(sugar, randNum.nextInt(91) + 10);
        return strategy;
    }

    public StepStrategy generateOrganismStepStrategy(Organism organism)
    {
        StepStrategy strategy = null; 
        switch (randNum.nextInt(2))
        {
        case 0:
            strategy = new StandStill();
            break;
        case 1:
            strategy = new MoveRandom(this);
            break;
        }
        
        organism.strategy = strategy;
        organism.stepNumber = randNum.nextInt(91) + 10;
        strategy.step(organism,50);
        return strategy;
    }
    
    public StepStrategy generateplasmaStepStrategy(Plasma plasma) //plasma
    {
        StepStrategy strategy = new MoveLinear();
        plasma.strategy = strategy;
        plasma.stepNumber = randNum.nextInt(91) + 10;
        strategy.step(plasma,50);
        return strategy;
    }

    public StepStrategy generateCellStepStrategy(Cell cell)
    {
        StepStrategy strategy = null; 
        int identifier =0;
        if(cell.getClass() == BasicCell.class)
            identifier = 2;
        else if (cell.getClass() == Roamer.class)
            identifier = 4;
        else if (cell.getClass() == Evader.class)
            identifier = 6;
        else //Hunter
            identifier = 7;
        
        
        switch (randNum.nextInt(identifier))
        {
        case 0:
            strategy = new StandStill();
            break;
        case 1:
            strategy = new GrabFood(this);
            break;
        case 2:
            strategy = new MoveRandom(this); 
            break;
        case 3:
            strategy = new MoveLinear();
            break;  
        case 4:
            strategy = new LossMass();
            break;
        case 5:
            strategy = new AvoidLarger(this);
            break;
        case 6:
            strategy = new ChaseSmaller(this);
            break;
        }
       
        
        cell.strategy = strategy;
        cell.stepNumber = randNum.nextInt(91) + 10;
        strategy.step(cell,50);
        return strategy;
    }

    public Food createFood(FoodFactory factory)
    {
        Food f = factory.createFood(this);
        return f;
    }

    public Cell createCell()
    {
       return new BasicCell(0,0,"");
    }

    public void stepAll(double deltaTime)
    {
        
         //ask each entity to apply their strategies for one step
        for (int i = 0;i < entities.size();i++)
        {
            entities.get(i).step(deltaTime);
        }
       
        //assign strategy
        for (int i = 0;i < entities.size();i++)
        {
            if(entities.get(i).getClass() == BasicCell.class || entities.get(i).getClass() == Roamer.class || entities.get(i).getClass() == Evader.class || entities.get(i).getClass() == Hunter.class)
            {
                if(entities.get(i).stepNumber <= 0)
                    this.generateCellStepStrategy((Cell)entities.get(i));
            }
            else if(entities.get(i).getClass() == Sugar.class)
            {
                if(entities.get(i).stepNumber <= 0)
                    this.generateSugarStepStrategy((Sugar)entities.get(i));
            }
            else if(entities.get(i).getClass() == Organism.class)
            {
                if(entities.get(i).stepNumber <= 0)
                    this.generateOrganismStepStrategy((Organism)entities.get(i));
            }
            else //Plasma
            {
                if(entities.get(i).stepNumber <= 0)
                    this.generateplasmaStepStrategy((Plasma)entities.get(i));
            }
        }
        
        
        
        
        //check which food particles are consumed and spawns additional food
        int x;
        int y;
        Random rand = new Random();
        if(numberOfFood < 100)
        {
            for (int i = numberOfFood; i < 100; i++) 
            {
                switch (randNum.nextInt(3))
                {
                case 0: //sugar
                    x = randNum.nextInt(741) + 310; 
                    y = randNum.nextInt(721)      ; 
                    int sideHalfLength = rand.nextInt(5) + 8; 
                    Color color = (new Color((int)(Math.random() * 0x1000000)));
                    int rotate = randNum.nextInt(30) ;
                    entities.add(new Sugar(x,y,sideHalfLength,color,rotate));
                    break;
                case 1: //organism
                    x = randNum.nextInt(741) + 310; ;  
                    y = randNum.nextInt(721)      ; 
                    int radius = rand.nextInt(5) + 10; 
                    color = (new Color((int)(Math.random() * 0x1000000)));
                    entities.add(new Organism(x,y,radius,color));
                    break;
                case 2: //plasma
                    x = randNum.nextInt(741) + 310; 
                    y = randNum.nextInt(721)      ;
                    radius = rand.nextInt(6) + 11; 
                    entities.add(new Plasma(x,y,radius));
                    break;
                }
                numberOfFood++;
            }
        }
        
        //decorate the cells
        for (int i = 0;i < entities.size();i++)
        {
            
            //roamer
            if(entities.get(i).getClass() == BasicCell.class )
            {
                Entity temp = entities.get(i);
                if(temp.mass > 40  ) //Roamer
                {
                    entities.add(new Roamer((Cell)entities.get(i)));
                    entities.remove(i);
                }
            }
            //evader
            if(entities.get(i).getClass() == Roamer.class )
            {
                Entity temp = entities.get(i);
                if(temp.mass > 60  ) //Evader
                {
                    entities.add(new Evader(new Roamer((Cell)entities.get(i))));
                    entities.remove(i);
                }
            }
            //hunter
            if(entities.get(i).getClass() == Evader.class )
            {
                Entity temp = entities.get(i);
                if(temp.mass > 80  ) //Hunter
                {
                    entities.add(new Hunter (new Evader(new Roamer((Cell)entities.get(i)))));
                    entities.remove(i);
                }
            }
        }

        
        //Cells can swallows smaller cells
        for (int i = 0;i < entities.size();i++)
        {
            if(entities.get(i).getClass() == BasicCell.class || entities.get(i).getClass() == Roamer.class || entities.get(i).getClass() == Evader.class || entities.get(i).getClass() == Hunter.class)
            {
                Cell temp1 = (Cell) entities.get(i);
                Vector v1 = new Vector(temp1.location.x + temp1.mass , temp1.location.y + temp1.mass);
                for (int j = 0;j < entities.size();j++)
                {
                    if(entities.get(j).getClass() == BasicCell.class || entities.get(j).getClass() == Roamer.class || entities.get(j).getClass() == Evader.class || entities.get(j).getClass() == Hunter.class)
                    {
                        Cell temp2 = (Cell) entities.get(j);
                        Vector v2 = new Vector(temp2.location.x + temp2.mass , temp2.location.y + temp2.mass);
                        if(!temp1.name.equals(temp2.name) && v1.distanceTo(v2) < temp1.mass && temp1.mass > temp2.mass)
                        {
                            temp1.mass += (temp2.mass / 2);
                            temp1.cellsSwallowed += 1;
                            temp1.speed = 125 / temp1.mass;
                            entities.remove(j);
                            this.numberofCells -= 1;
                        }
                    }
                }
            }
        }
        
        
        //eat foods which corresponding inside cells 
        for (int i = 0;i < entities.size();i++)
        {
            if(entities.get(i).getClass() == BasicCell.class || entities.get(i).getClass() == Roamer.class || entities.get(i).getClass() == Evader.class || entities.get(i).getClass() == Hunter.class)
            {
                Cell temp1 = (Cell) entities.get(i);
                Vector v1 = new Vector(temp1.location.x + temp1.mass , temp1.location.y + temp1.mass);
                for (int j = 0;j < entities.size();j++)
                {
                    if(entities.get(j).getClass() == Sugar.class || entities.get(j).getClass() == Organism.class || entities.get(j).getClass() == Plasma.class )
                    {
                        Entity temp2 =  entities.get(j);
                        Vector v2 = new Vector(temp2.location.x + temp2.mass , temp2.location.y + temp2.mass);
                        if( v1.distanceTo(v2) < temp1.mass )
                        {
                            
                            if(temp1.mass <= 120)
                            {
                                temp1.mass += 1;
                                temp1.speed = 125 / temp1.mass;
                                temp1.foodEaten += 1;
                            }
                            entities.remove(j);
                            this.numberOfFood -= 1;
                        }
                    }
                }
            }
        }
        
        this.numberOfSteps += 1;
    }
}
