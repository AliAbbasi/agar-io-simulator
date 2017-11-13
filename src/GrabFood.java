//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : GrabFood.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//



public class GrabFood extends StepStrategy
{
    public Enviroment enviroment;
    public GrabFood(Enviroment enviroment )
    {
        this.enviroment = enviroment;
    }  

    @Override
    public String getName()  
    {
        return "GF";
    }
    
    @Override
    public boolean isFinished()
    {
        return true; 
    }

    @Override
    public void step(Entity e, double deltaTime)
    {
        Vector v = new Vector(e.location.x + e.mass, e.location.y  +e.mass);  
        
        int nearest = 2000;
        int t = 0;
        int i = 0;
        int j = 0;
        for (i = 0;i < enviroment.entities.size();i++)
        {
            if(enviroment.entities.get(i).getClass() == Sugar.class || enviroment.entities.get(i).getClass() == Organism.class || enviroment.entities.get(i).getClass() == Plasma.class)
            {
                Entity temp = enviroment.entities.get(i);
                t = (int) v.distanceTo(temp.location);
                if( t < nearest)
                {
                    nearest = t;
                    j = i;
                } 
            }
        }
        
       Entity ex =enviroment.entities.get(j);
       double tx = ex.location.x;
       double ty = ex.location.y;
        
       
       
        
        
        e.direction.x =  ex.location.x;
        e.direction.y =  ex.location.y;
        
    
        
  
              
        if(e.location.x + e.mass  < tx) 
            e.location.x = e.location.x + e.speed;
        else
           e.location.x = e.location.x - e.speed;
        
        
        if(e.location.y + e.mass   < ty) 
            e.location.y = e.location.y + e.speed;
        else
            e.location.y = e.location.y - e.speed;
            
        
        
        //check for eaten foods
        ex = enviroment.entities.get(j);
        t = (int) v.distanceTo(ex.location);
        
        if(t - 1  <  (e.mass  ))
        {
           if(e.mass <= 120)
            {
                Cell c =(Cell)e;
                c.foodEaten++;
                e.mass += 1;
                e.speed = 125/e.mass;
            }
            
            enviroment.entities.remove(j);
            --enviroment.numberOfFood;
        }
            
        
    }
}
        
    

