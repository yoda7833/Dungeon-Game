
/**
 * Write a description of class Character here.
 * 
 * @author (Roman Errigo) 
 * @version (1.0)
 */
public class Character
{
    private int health;
    private int locationRow;
    private int locationCol;
    private boolean hasSword;
    private boolean hasKey;
    private boolean hasTrueSight;
    
    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        health = 20;
        locationRow = 0;
        locationCol = 0;
        hasSword = false;
        hasKey = false;
        hasTrueSight = false;
    }
    
    public Character(int row, int col)
    {
        health = 20;
        locationRow = row;
        locationCol = col;
        hasSword = false;
        hasKey = false;
        hasTrueSight = false;
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
        return health+=y;
    }
    public int lostHealth(int y)
    {
        return health-=y;
    }
    public int setHealth(int x)
    {
        int oldHealth = health;
        health=x;
        return oldHealth;
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
    
    public int getPosRow()
    {
        return locationRow;
    }
    
    public int getPosCol()
    {
        return locationCol;
    }
    public int setPosRow(int x)
    {
        return locationRow+=x;
    }
    
    public int setPosCol(int x)
    {
        return locationCol+=x;
    }
    
    public void setPos(int row, int col)
    {
        locationRow=row;
        locationCol=col;
    }
}
