
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
     // instance variables - replace the example below with your own
    /*
     * 1 = wall, 0 = empty space, 2 = door , 3 = monster
     */
    private char enter;
    private int[][] map;
    private int roomType;
    /**
     *  Default constructor for objects of class RoomCorridor
     */
    public Room()
    {
        roomType = (int)(Math.random()*3);
        switch(roomType){
            case 0:
                    map = new int[5][(int)(Math.random()*10)+10];
                    fillMap();
                    break;
            case 1:
                    map = new int[5][5];
                    fillMap();
                    break;
            case 2:
                    map = new int[(int)(Math.random()*3)+10][(int)(Math.random()*3)+10];
                    fillMap();
                    break;
            default:
                    map = new int[3][3];
                    fillMap();
                    break;
        }
        //map = new int[5][(int)(Math.random()*10)+10];
        //fillMap();
    }
    public Room(char type)
    {
        enter = type;
        int random = (int)(Math.random()*100+1);
        if(enter == 't'||enter == 'b')
        {
            map = new int[(int)(Math.random()*10)+10][5];
            fillMap();
        }
        else
        {
            
        }
        
    }
    /**
     *  Constructor for objects of class RoomCorridor when given the map size
     */
    public Room(int x, int y, int type)
    {
        map = new int[x][y];
        fillMap();
        roomType = type;
    }
    /**
     *  Constructor for objects of class RoomCorridor when given a RoomCorridor object
     */
    public Room(Room input)
    {
        for(int row =0; row<map.length;row++)
            for(int col =0; col<map[0].length;col++)
            {
                map[row][col] = input.getMap()[row][col];
            }
        roomType = input.getRoomType();
    }

    public int getRoomType()
    {
        return roomType;
    }
    
    public int roomX()
    {
        return map.length;
    }
    
    public int roomY()
    {
        return map[0].length;
    }
    
    public int[][] getMap()
    {
        return map;
    }
    
    public int changeMap(int row, int col, int change)
    {
        int changed = map[row][col];
        map[row][col]=change;
        return changed;
    }
    
    private void fillMap()
    {
        //still needs work
        for(int row =0; row<map.length;row++)
            for(int col =0; col<map[0].length;col++)
            {
                if(row==0||col==0||row==map.length-1||col==map[0].length-1)
                    map[row][col] = 1;
                else
                    map[row][col] = 0;
            }
        map[map.length/2][0]=2;
        fillMonster();
    }
    
    private void fillMonster()
    {
        for(int row =0; row<map.length;row++)
            for(int col =0; col<map[0].length;col++)
            {
                if(map[row][col]==0&&(int)(Math.random()*3)==0&&col!=1&&col!=map[0].length-2&&row!=1&&row!=map.length-2)
                    map[row][col] = 3;
            }
        boolean isMonster=false;
        for(int[] x: map)
            for(int y: x)
            {
                if(y==3)
                    isMonster=true;
            }
        if(isMonster==false)
        {
            for(int row =0; row<map.length;row++)
                for(int col =0; col<map[0].length;col++)
                {
                    if(map[row][col]==2&&map[row+1][col]==2)
                    {
                        
                    }
                }
        }
    }
}
