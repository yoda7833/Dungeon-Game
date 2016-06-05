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
    static private Room room;
    static private Character player;
    static private Scanner reader;
    static private boolean hideMonsters;
    static public void run()
    {
        hideMonsters = false;
        reader = new Scanner(System.in);
        room = new Room();
        player = new Character(room.getMap().length/2,room.getMap()[0].length/2);
        while(player.getHealth()>0)
        {
            printing();
            //if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)//will start battle
            //System.out.println("You walked onto a monter");
            if(move().compareTo("q")==0)//causes
                break;
            printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==1)
                player.setHealth(-1);
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)
            {
                boolean won = player.fighting();
                printing();
                if(won)
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
        }
        System.out.println();
        System.out.println("You Died Game Over");
    }
    
    static private void printing()
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
                else
                {
                    System.out.print(room.getMap()[row][col]+" ");
                }
            }
            System.out.println();
        }
    }
    
    static private String move()
    {
        System.out.println("Input a WASD imput to move or q to quit");
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
        return n;
    }
}
