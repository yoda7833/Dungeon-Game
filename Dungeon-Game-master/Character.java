
/**
 * Write a description of class Character here.
 * 
 * @author (Roman Errigo) 
 * @version (1.1)
 */
public class Character
{
    private int health;
    private int locationRow;
    private int locationCol;
    private boolean hasSword;
    private boolean hasKey;
    private boolean hasTrueSight;
    private int maxHealth; // added max health for functionality, cannot heal past max health
    private int block;//added negates damage and removes a charge
    private boolean armor;
    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        maxHealth = 20; // added
        health = 20;
        block = 0; //added
        locationRow = 0;
        locationCol = 0;
        armor = false;
        hasSword = false;
        hasKey = false;
        hasTrueSight = false;
    }
    
    public Character(int row, int col)
    {
        maxHealth = 20;
        health = 20;
        block = 0; //added
        locationRow = row;
        locationCol = col;
        armor = false;
        hasSword = false;
        hasKey = false;
        hasTrueSight = false;
    }

    // Getter Methods
    public int getHealth()
    {
        return health;
    }
    
    public int getPosRow()
    {
        return locationRow;
    }
    
    public int getPosCol()
    {
        return locationCol;
    }
    
    public boolean hasSword()
    {
        return hasSword;
    }
    
    public boolean hasKey()
    {
        return hasKey;
    }
    
    public boolean hasTrueSight()
    {
        return hasTrueSight;
    }
    
    public boolean hasArmor() //added
    {
        return armor;
    }
    //setter Methods
    public int setHealth(int x)
    {
        int oldHealth = health;
        health=x;
        return oldHealth;
    }
    
    public int takeDamage(int damage)//I updated the takeDamage method to incorporate armor as well as blocking with a shield into calculating damage
    {
        if(block > 0)
        {
            System.out.println("You succesfully blocked the strike");
            block-=1;
            if(block == 0)
            {
                System.out.println("Your Shield has been broken");
            }
        }
        else if(armor)
        {
            health -= (damage/2);
        }
        else
        {
            health -= damage;
        }
        return health;
    }
    public int restoreHealth(int healing)//added restore health method, cannot heal over max health
    {
        if((health + healing) >= maxHealth)
        {
            health = maxHealth;
        }
        else
        {
            health+= healing;
        }
        return health;
    }
    
    
    //I organized the methods so they are easier to find. They are grouped with their opposite or related method
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
    
    //added
    public void foundShield()
    {
        block = 3;
    }
    public void breakShield()
    {
        block = 0;
        System.out.println("Your Shield has been broken");
    }
    //added
    public void foundArmor()
    {
        armor = true;
    }
    public void breakArmor()
    {
        armor = false;
    }
    
    
    public int movePosRow(int x)//renamed
    {
        return locationRow+=x;
    }
    public int movePosCol(int x)//renamed
    {
        return locationCol+=x;
    }
    
    
    public void setPos(int row, int col)
    {
        locationRow=row;
        locationCol=col;
    }
    
    
    public int setMaxHealth(int newMaxHealth) //added
    {
        maxHealth = newMaxHealth;
        return maxHealth;
    }
    
    
    public void foundChest() //if you step on a space with a chest run this method
    {
        int number = ((int)(Math.random()*100 + 1));
        if(number >=1 && number <= 15)
        {
            foundShield();
            System.out.println("You found a sheild");
        }
        if(number >=16 && number <= 30)
        {
            foundArmor();
            System.out.println("You found some armor");
        }
        if(number >=31 && number <= 45)
        {
            foundTrueSight();
            System.out.println("You found a potion of true sight");
        }
        if(number >=46 && number <= 60)
        {
            foundKey(); //possible addition
            System.out.println("You found a key, this could come in handy");
        }
        if(number >=61 && number <= 75)
        {
            foundSword();
            System.out.println("You found a sword");
        }
        if(number >=61 && number <= 100)
        {
            System.out.println("The chest is empty");
        }
    }
    
    public boolean isDead()//added 
    {
        if(health <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
