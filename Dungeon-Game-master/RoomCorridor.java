
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
     * 1 = wall, 0=empty space
     */

    /**
     * Constructor for objects of class RoomCorridor
     */
    public RoomCorridor()
    {
        map = new int[5][(int)(Math.random()*5)+1];
    }
    public RoomCorridor(int x, int y)
    {
        map = new int[x][y];
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
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
    private void fillMap()
    {
        //still needs work
        for(int row =0; row<map.length;row++)
            for(int col =0; col<map[0].length;col++)
            {
                if(row==0||col==0||row==map.length-1||col==map[0].length-1)
                    map[row][col] = 1;
            }
    }
}
