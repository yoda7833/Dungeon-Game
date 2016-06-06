import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
/**
 * runs the game
 * 
 * @author CJ Cassidy 
 * @version 6/2/16
 */
public class Dungeon_Game
{
    //player = 9
    private Room room;
    private Character player;
    private Scanner reader;
    private boolean hideMonsters;
    JFrame frame = new JFrame("Dungeon Game");
    int r;
    public void run()
    {
        JPanel blah = new JPanel();
        frame.add(blah);
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
            displayFrame();
            System.out.println('\f');
            //printing();
            //if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)//will start battle
            //System.out.println("You walked onto a monter");
            String moveChar = move();
            if(moveChar.compareTo("q")==0)//causes
                break;
            if(moveChar.compareTo("p")==0)
                player.usePotion();
            if(moveChar.compareTo("i")==0)
                displayInventory();
            displayFrame();
            System.out.println('\f');
            //printing();
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==1)
                player.setHealth(-1);
            if(room.getMap()[player.getPosRow()][player.getPosCol()]==3)
            {
                displayMonster();
                boolean win = player.fighting();
                System.out.println('\f');
                //printing();
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
                    frame.dispose();
                    player.bossFight();
                    if(!player.isDead())
                    {
                        won = true;
                    }
                }
                else
                {
                    player.foundChest();
                    try {
                        Thread.sleep(1000);                 //1000 milliseconds is one second.
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
        System.out.print("Equipped: ");
        if(player.hasSword())
        {
            System.out.print("| Sword |");
        }
        if(player.hasShield())
        {
            System.out.print("| Sheild |");
        }
        if(player.hasArmor())
        {
            System.out.print("| Armor |");
        }
        if(player.hasTrueSight())
        {
            System.out.print("| True Sight |");
        }

        System.out.println();
        System.out.println("Enter anything to Continue...");
        reader.next();
    }

    public void displayBoss()
    {
        frame.getContentPane().removeAll();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        ImageIcon image9 = new ImageIcon(getClass().getResource("boss.jpg"));
        JLabel label9 = new JLabel(image9);
        panel.add(label9); 
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    public void displayMonster()
    {
        frame.getContentPane().removeAll();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        ImageIcon image10 = new ImageIcon(getClass().getResource("monsterLarge1.png"));
        ImageIcon image11 = new ImageIcon(getClass().getResource("monsterLarge2.png"));
        if(r == 0)
        {
            JLabel label10 = new JLabel(image10);
            panel.add(label10); 
        }
        else
        {
            JLabel label11 = new JLabel(image11);
            panel.add(label11); 
        }

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    public void displayFrame()
    {
        frame.getContentPane().removeAll();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(((40*(room.roomCol()))+5),((40*(room.roomRow())))+30);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT,0,0);

        FlowLayout layout1 = new FlowLayout(FlowLayout.LEFT,0,0);
        frame.setLayout(layout1);

        ImageIcon image1 = new ImageIcon(getClass().getResource("player.png"));
        ImageIcon image2 = new ImageIcon(getClass().getResource("wall.png"));
        ImageIcon image3 = new ImageIcon(getClass().getResource("door.png"));
        ImageIcon image4 = new ImageIcon(getClass().getResource("floor.png"));
        ImageIcon image5 = new ImageIcon(getClass().getResource("chest.png"));

        ImageIcon image6 = new ImageIcon(getClass().getResource("monster1.png"));
        ImageIcon image7 = new ImageIcon(getClass().getResource("monster2.png"));
        //ImageIcon image8 = new ImageIcon(getClass().getResource("monster3.png"));
        for(int row = 0; row < room.roomRow();row++)
        {
            JPanel panel = new JPanel();
            panel.setLayout(layout);
            for(int col = 0; col < room.roomCol();col++)
            {
                if(row==player.getPosRow()&&col==player.getPosCol())
                {
                    JLabel label1 = new JLabel(image1);
                    panel.add(label1);
                }
                else if(room.getMap()[row][col] == 1)
                {
                    //ImageIcon image1 = new ImageIcon(getClass().getResource("wall.png"));
                    JLabel label2 = new JLabel(image2);
                    panel.add(label2);
                }
                else if(room.getMap()[row][col] == 2)
                {
                    //ImageIcon image2 = new ImageIcon(getClass().getResource("door.png"));
                    JLabel label3 = new JLabel(image3);
                    panel.add(label3);
                }

                else if(!hideMonsters&&room.getMap()[row][col] == 3)
                {
                    r = (int)(Math.random()*2);
                    if(r == 0)
                    {
                        JLabel label6 = new JLabel(image6);
                        panel.add(label6); 
                    }
                    else
                    {
                        JLabel label7 = new JLabel(image7);
                        panel.add(label7); 
                    }
                    /*
                    else
                    {
                    JLabel label8 = new JLabel(image8);
                    frame.add(label8); 
                    }
                     */
                }
                else if(room.getMap()[row][col] == 5 || room.getMap()[row][col] == 6)
                {
                    JLabel label5 = new JLabel(image5);
                    panel.add(label5);
                }
                else//(room.getMap()[row][col] == 0)
                {
                    //ImageIcon image2 = new ImageIcon(getClass().getResource("player.jpg"));
                    JLabel label4 = new JLabel(image4);
                    panel.add(label4);
                }
            }
            frame.add(panel);
        }
        frame.revalidate();
        frame.repaint();
    }

}
