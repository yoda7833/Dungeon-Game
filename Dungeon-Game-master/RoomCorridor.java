
/**
 * Write a description of class RoomCorridor here.
 * 
 * @author CJ Cassidy
 * @version 5/31/16
 */
public class RoomCorridor implements Room
{
    // instance variables - replace the example below with your own
    int[][] map;
    /*
     * 1 = wall, 0 = empty space, 2 = door , 3 = monster
     */

    /**
     *  Default constructor for objects of class RoomCorridor
     */
    public RoomCorridor()
    {
        map = new int[5][(int)(Math.random()*5)+5];
        fillMap();
    }
    /**
     *  Constructor for objects of class RoomCorridor when given the map size
     */
    public RoomCorridor(int x, int y)
    {
        map = new int[x][y];
        fillMap();
    }
    /**
     *  Constructor for objects of class RoomCorridor when given a RoomCorridor object
     */
    public RoomCorridor(RoomCorridor input)
    {
        for(int row =0; row<map.length;row++)
            for(int col =0; col<map[0].length;col++)
            {
                map[row][col] = input.getMap()[row][col];
            }
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
                if(map[row][col]==0&&(int)(Math.random()*2)==0)
                    map[row][col] = 3;
            }
    }
}
