
/**
 * Write a description of class Character here.
 * 
 * @author (Roman Errigo) 
 * @version (1.0)
 */
public class Character
{
    private int health;
    private int locationX;
    private int locationY;
    private boolean hasSword;
    private boolean hasKey;
    private boolean hasTrueSight;
    
    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        int health = 20;
        int locationX = 0;
        int locationY = 0;
        boolean hasSword = false;
        boolean hasKey = false;
        boolean hasTrueSight = false;
    }

    public int getHealth()
    {
        return health;
    }
    
    public int takeDamage(int damage)
    {
        health -= damage;
        return health;
    }
    
    public boolean hassword()
    {
         //not doing anything just return false to compile
        return false;
    }
    
    public int hasKey()
    {
        //not doing anything just return -1 to compile
        return -1;
    }
    
    public boolean hasTrueSight()
    {
        return hasTrueSight;
    }
    
    public int getHealth(int y)
    {
         //not doing anything just return -1 to compile
        return -1;
    }
    
    public void foundSword()
    {
        hasSword = true;
    }
    
    public void lostSword()
    {
        hasSword = false;
    }
    
    public void foundKey()
    {
        hasKey = true;
    }
    
    public void lostKey()
    {
        hasKey = false;
    }
    
    public void foundTrueSight()
    {
        hasTrueSight = true;
    }
    
    public void lostTrueSight()
    {
        hasTrueSight = false;
    }
}
