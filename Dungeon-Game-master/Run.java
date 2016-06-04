import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
        player = new Character(room.getMap().length/2,room.getMap()[0].length-2);
        while(player.getHealth()>0)
        {
            printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)//will start battle
                System.out.println("You walked onto a monter");
            if(move().compareTo("q")==0)//causes
                break;
            printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==1)
                player.setHealth(-1);
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)
                System.out.println("You walked onto a monter");
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==2)
            {
                room = new Room();
                player.setPos(room.getMap().length/2,room.getMap()[0].length-2);
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
        String k;
        //do{
            do{
                k = reader.nextLine(); // Scans the next token of the input as an int.
            }while(k.length()<=0);
            n = k.substring(0,1);
        //}while(!(n.compareTo("w")==0||n.compareTo("a")==0||n.compareTo("s")!=0||n.compareTo("d")==0||n.compareTo("q")==0));
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
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Dungeon Game");
        frame.setSize(0,0);
        frame.setLocation(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel testLabel = new JLabel("Test");
        testLabel.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(testLabel, BorderLayout.CENTER);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
