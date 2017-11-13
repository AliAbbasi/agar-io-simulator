//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Agarion
//  @ File Name : AvoidLarger.java
//  @ Date : 11/07/2015
//  @ Author : Ali Abbasi
//
//



public class AvoidLarger extends StepStrategy
{
    Enviroment enviroment;
    public AvoidLarger(Enviroment enviroment)
    {
        this.enviroment = enviroment;
    }

    @Override
    public String getName()
    {
        return "AL";
    }

    @Override
    public void step(Entity e, double deltaTime)
    {
        Vector v = new Vector(e.location.x , e.location.y  );  
        int nearest = 2000;
        int t = 0;
        int i = 0;
        int j = 0;
        for (i = 0;i < enviroment.entities.size();i++)
        {
            if(enviroment.entities.get(i).getClass() == BasicCell.class || enviroment.entities.get(i).getClass() == Roamer.class || enviroment.entities.get(i).getClass() == Evader.class || enviroment.entities.get(i).getClass() == Hunter.class)
            {
                Entity temp = enviroment.entities.get(i);
                t = (int) v.distanceTo(temp.location);
                if( t < nearest && t != 0)
                {
                    nearest = t;
                    j = i;
                } 
            }
        }
        
        Entity temp = enviroment.entities.get(j);
        
        if(e.mass < temp.mass)
        {
            if(temp.location.x <= e.location.x && temp.location.y <= e.location.y){
                e.direction.x = 1030; e.direction.y = 720;}
            else if(temp.location.x >= e.location.x && temp.location.y <= e.location.y){
                e.direction.x = 310;e.direction.y = 720;}
            else if(temp.location.x <= e.location.x && temp.location.y >= e.location.y){
                e.direction.x = 1030;e.direction.y = 0;}
            else 
                e.direction.x = 310;e.direction.y = 0;

            if(e.location.x + e.mass  < e.direction.x) 
                e.location.x = e.location.x + e.speed;
            else
                e.location.x = e.location.x - e.speed;


            if(e.location.y + e.mass   < e.direction.y) 
                e.location.y = e.location.y + e.speed;
            else
                e.location.y = e.location.y - e.speed;
        }
        
    }
        

}
