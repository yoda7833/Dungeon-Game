import java.util.Scanner;
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
    private Scanner reader;
    private int potion;
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
        reader = new Scanner(System.in);
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
        reader = new Scanner(System.in);
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

    public boolean hasShield()
    {
        if(block >=1)
            return true;
        return false;
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
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void usePotion()
    {
        if(potion>0)
        {
            restoreHealth(maxHealth/2);
            potion--;
            System.out.println("You now feel like half a new man");
            reader.next();
        }
        else
        {
            System.out.println("You can't use something you don't have...");
            reader.next();
        }
    }

    public int getPotions()
    {
        return potion;
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

    public int movePosRow(int x)
    {
        return locationRow+=x;
    }

    public int movePosCol(int x)
    {
        return locationCol+=x;
    }

    public void setPos(int row, int col)
    {
        locationRow=row;
        locationCol=col;
    }

    public int setMaxHealth(int newMaxHealth)
    {
        maxHealth = newMaxHealth;
        return maxHealth;
    }

    public void foundBag() //when you kill a monster you can get loot :D
    {
        int number = ((int)(Math.random()*100 + 1));
        if(number>=1&&number<= 10)
        {
            potion++;
            System.out.println("You found a potion");
            int random = (int)(Math.random()*100+1);
            if(random==0)
                System.out.println("Hope it wasn't the blue one");
        }
        if(number >=11 && number <= 25)
        {
            foundArmor();
            System.out.println("You found some armor");
        }
        if(number >=25 && number <= 40)
        {
            foundSword();
            System.out.println("You found a sword");
        }
        if(number >=41 && number <= 100)
        {
            System.out.println("You found nothing");
        }
    }

    public void foundChest() //if you step on a space with a chest run this method
    {
        int number = ((int)(Math.random()*100 + 1));
        if(number >=1 && number <= 5)
        {
            System.out.println("You found nothing");
        }
        if(number >=5 && number <= 50)
        {
            foundShield();
            System.out.println("You found a sheild");
        }
        if(number >=76 && number <= 82)
        {
            foundTrueSight();
            System.out.println("You found a potion of true sight");
        }
        if(number>=25&&number<= 75)
        {
            potion++;
            System.out.println("You found a potion");
            int random = (int)(Math.random()*100+1);
            if(random==0)
                System.out.println("Hope it wasn't the blue one");
        }
        if(number >=83 && number <= 90)
        {
            foundKey(); //possible addition
            System.out.println("You found a key, this could come in handy");
        }
        if(number >=91 && number <= 100)
        {
            maxHealth+=5;
            restoreHealth(5);
            System.out.println("You found a heart container");
            System.out.println("Your max health has increased by 5");
        }
    }

    public boolean isDead()
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

    public boolean fighting()
    {
        int monsterHealth = (int)(Math.random()*4+12);
        boolean monsterArmor= false;
        if((int)(Math.random()*2)==0)
        {
            monsterArmor = true;
        }

        System.out.println('\f');
        System.out.println("You have entered a fight");
        do{
            System.out.println("Your health: "+ health);
            System.out.println("Monster's health: "+ monsterHealth);
            System.out.println("Type to fight");
            System.out.println("Fight options: POWER  PRECISE  RUN");
            String n;
            String k;
            do{
                do{
                    k = reader.next(); // Scans the next token of the input as a String.
                }while(k.length()<=0);
                n = k.toLowerCase();
                //System.out.println();
            }while(!(n.compareTo("power")==0||n.compareTo("precise")==0||n.compareTo("run")==0));

            if(n.compareTo("power")==0)
            {
                if(monsterArmor)
                {
                    int num = (int)(Math.random()*10+1);
                    if(hasSword)
                    {
                        int rand = (int)(Math.random()*100+1);
                        if(rand<=10)
                        {
                            hasSword = false;
                            System.out.println("Your Sword Broke");
                            int random1 = (int)(Math.random()*50+1);
                            if(random1==1)
                                System.out.println("Thats what you get for using a sword you found on the ground");
                        }
                        if(rand>=11&&rand<=31)
                        {
                            armor = false;
                            System.out.println("Your Armor Broke");
                            int random1 = (int)(Math.random()*50+1);
                            if(random1==1)
                                System.out.println("Thats what you get for using Armor you found on the ground");
                        }
                        monsterHealth -= (((int)(Math.random()*3+5))+5)/3;
                        System.out.println("You hear a *dink*");
                        if(num==1)
                            System.out.println("Ya dink");
                    }
                    else
                    {
                        monsterHealth -= ((int)(Math.random()*3+5))/3;
                        System.out.println("You hear a *dink*");
                        if(num==1)
                            System.out.println("Ya dink");
                    }
                }
                else
                {
                    if(hasSword)
                    {
                        monsterHealth -= (((int)(Math.random()*3+5))+5);
                    }
                    else
                    {
                        monsterHealth -= ((int)(Math.random()*3+5));
                    }
                }
            }
            else if(n.compareTo("precise")==0)
            {
                if(hasSword)
                {
                    monsterHealth -= (((int)(Math.random()*3+3))+5);
                }
                else
                {
                    monsterHealth -= ((int)(Math.random()*3+3));
                }
            }
            else if(n.compareTo("run")==0)
            {
                health/=2;
                return false;
            }
            takeDamage((int)(Math.random()*4+1));
            System.out.println("Type Anything to Continue...");
            reader.next();
            System.out.print('\f');
        }while(monsterHealth>0&&health>0);
        foundBag();
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    public void bossFight()
    {
        int monsterHealth = (int)(Math.random()*25+125);
        boolean monsterArmor= false;
        System.out.println('\f');
        System.out.println("You reach inside and see the dim glow of a holy sword...");
        try {
            Thread.sleep(3000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("HOLY SHIT THAT WASN'T A CHEST");
        do{
            if((int)(Math.random()*2)==0)
            {
                monsterArmor = true;
            }
            else
                monsterArmor= false;
            System.out.println("Your health: "+ health);
            System.out.println("Boss health: "+ monsterHealth);
            System.out.println("Type to fight");
            System.out.println("Fight options: POWER  PRECISE  POTION");
            String n;
            String k;
            do{
                do{
                    k = reader.next(); // Scans the next token of the input as a String.
                }while(k.length()<=0);
                n = k.toLowerCase();
                //System.out.println();
            }while(!(n.compareTo("power")==0||n.compareTo("precise")==0||n.compareTo("run")==0||n.compareTo("potion")==0));

            if(n.compareTo("power")==0)
            {
                if(monsterArmor)
                {
                    if(hasSword)
                    {
                        monsterHealth -= (((int)(Math.random()*3+5))+5)/3;
                        System.out.println("You hear a *dink*");
                    }
                    else
                    {
                        monsterHealth -= ((int)(Math.random()*3+5))/3;
                        System.out.println("You hear a *dink*");
                    }
                }
                else
                {
                    if(hasSword)
                    {
                        monsterHealth -= (((int)(Math.random()*3+5))+5);
                    }
                    else
                    {
                        monsterHealth -= ((int)(Math.random()*3+5));
                    }
                }
            }
            else if(n.compareTo("precise")==0)
            {
                if(hasSword)
                {
                    monsterHealth -= (((int)(Math.random()*3+3))+5);
                }
                else
                {
                    monsterHealth -= ((int)(Math.random()*3+3));
                }
            }
            else if(n.compareTo("potion")==0)
            {
                usePotion();
            }
            takeDamage((int)(Math.random()*4+1));
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.print('\f');
        }while(monsterHealth>0&&health>0);
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
