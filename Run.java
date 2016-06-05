import java.util.Scanner;
/**
 * runs the game
 * 
 * @author CJ Cassidy 
 * @version 6/2/16
 */
public class Run
{
    //player = 9
    private Room room;
    private Character player;
    private Scanner reader;
    private boolean hideMonsters;
    public void run()
    {
        hideMonsters = false;
        reader = new Scanner(System.in);
        room = new Room();
        player = new Character(room.getMap().length/2,room.getMap()[0].length/2);
        boolean won = false;

        while(player.getHealth()>0&&!won)
        {
            if(player.hasTrueSight())
                hideMonsters=false;
            else
                hideMonsters=true;
            printing();
            //if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)//will start battle
            //System.out.println("You walked onto a monter");
            String moveChar = move();
            if(moveChar.compareTo("q")==0)//causes
                break;
            if(moveChar.compareTo("p")==0)
                player.usePotion();
            if(moveChar.compareTo("i")==0)
                displayInventory();
            printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==1)
                player.setHealth(-1);
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)
            {
                boolean win = player.fighting();
                printing();
                if(win)
                {
                    room.changeMap(player.getPosRow(),player.getPosCol(),0);
                }
            }
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==2)
            {
                if(player.getPosRow()==0)
                {
                    room = new Room('t');
                }
                else if(player.getPosRow() == (room.roomRow() - 1))
                {
                    room = new Room('b');
                }
                else if(player.getPosCol()==0)
                {
                    room = new Room('l');
                }
                else if(player.getPosCol() == (room.roomCol() - 1))
                {
                    room = new Room('r');
                }
                player.setPos(room.getLocationRow() ,room.getLocationCol());
            }
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==5||room.getMap()[player.getPosRow()][player.getPosCol()]==6)
            {
                if(room.getMap()[player.getPosRow()][player.getPosCol()]==6&&player.hasKey())
                {
                    player.bossFight();
                    won = true;
                }
                else
                {
                    player.foundChest();
                    try {
                        Thread.sleep(2000);                 //1000 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    room.getMap()[player.getPosRow()][player.getPosCol()]=0;
                }
            }
        }
        System.out.println();
        if(won)
        {
            System.out.println("You recovered the Holy Sword and escaped the dungeon");
            try {
                Thread.sleep(4000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Wait...You were expecting to get the princess?");
            try {
                Thread.sleep(4000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Nah man you princess is in another dungeon");
            System.out.println("Better luck next time :P");
        }
        else
            System.out.println("You Died Game Over");
    }

    private void printing()
    {
        System.out.println('\f');
        for(int row =0;row<room.getMap().length;row++)
        {
            for(int col =0;col<room.getMap()[0].length;col++)
            {
                if(row==player.getPosRow()&&col==player.getPosCol())
                {
                    System.out.print(9+" ");
                }
                else if(hideMonsters&& room.getMap()[row][col]==3)
                {
                    System.out.print(0 +" ");
                }
                else if(room.getMap()[row][col]==-1)
                {
                    System.out.print("  ");
                }
                else if(room.getMap()[row][col]==6)
                {
                    System.out.print(5+ " ");
                }
                else
                {
                    System.out.print(room.getMap()[row][col]+" ");
                }
            }
            System.out.println();
        }
    }

    private String move()
    {
        System.out.println("Input a WASD imput to move or q to quit or p to use a potion or i for inventory");
        String n;
        //do{
        n = reader.next(); // Scans the next token of the input as an int.
        // n = i.substring(0,1);
        //}while(!(n.compareTo("w")==0||n.compareTo("a")==0||n.compareTo("s")!=0||n.compareTo("d")==0));
        switch(n){
            case "w": 
            player.movePosRow(-1);
            break;
            case "a":
            player.movePosCol(-1);
            break;
            case "s":
            player.movePosRow(1);
            break;
            case "d":
            player.movePosCol(1);
            break;
        }
        int random = (int)(Math.random()*100+1);
        if(random == 0)
            System.out.println("You feel something touch your back and look to see nothing there");
        else if(random == 1)
            System.out.println("You hear the sound of a door slamming shut in the distance");

        //if()
        return n;
    }

    private void displayInventory()
    {
        System.out.println('\f');
        System.out.println("Inventory:");
        System.out.println();
        System.out.println("Health: "+player.getHealth()+"/"+player.getMaxHealth());
        System.out.println();
        System.out.println("Potions: "+player.getPotions());
        System.out.println();
        System.out.println("Key: "+ player.hasKey());
        System.out.println();
        System.out.println("Enter anything to Continue...");
        reader.next();
    }

    
}

