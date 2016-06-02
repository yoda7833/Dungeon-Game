
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
    }
    
    public boolean hassword()
    {
        
    }
    
    public int hasKey()
    {
        
    }
    
    public int hasTrueSight()
    {
        
    }
    
    public int getHealth(int y)
    {
        
    }
    
    public void foundSword()
    {
        hasSword = true;
    }
    
    public void foundKey()
    {
        hasKey = true;
    }
    
    public void hastruesight()
    {
        hasTrueSight = true;
    }
}
