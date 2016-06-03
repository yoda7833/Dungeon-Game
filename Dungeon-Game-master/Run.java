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
    static private RoomCorridor room;
    static private Character player;
    static private Scanner reader;
    static public void run()
    {
        reader = new Scanner(System.in);
        room = new RoomCorridor();
        player = new Character(2,room.getMap()[0].length-2);
        while(player.getHealth()>0)
        {
            printing();
            move();
            printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3||room.getMap()[player.getPosRow()][player.getPosCol()]==1)
                player.lostHealth(100);
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
                else
                {
                    System.out.print(room.getMap()[row][col]+" ");
                }
            }
            System.out.println();
        }
    }
    
    static private void move()
    {
        System.out.println("a WASD imput");
        String n = reader.next(); // Scans the next token of the input as an int.
        switch(n){
            case "w": 
                     player.setPosRow(-1);
                     break;
            case "a":
                    player.setPosCol(-1);
                    break;
            case "s":
                    player.setPosRow(1);
                    break;
            case "d":
                    player.setPosCol(1);
                    break;
        }
    }
}
