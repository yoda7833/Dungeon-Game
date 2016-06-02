
/**
 * Write a description of interface Room here.
 * 
 * @author CJ Cassidy 
 * @version 5/31/16
 */
public interface Room
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the result produced by sampleMethod 
     */
    /* MAP KEY 
     * 1 = wall, 0=empty space, door = 2
     */
    
    public int roomX();
    public int roomY();
    public int[][] getMap();
}
