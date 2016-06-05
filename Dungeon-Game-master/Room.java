
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
    private int[][] map;
    private int roomType;
    private char enterPoint;
    private int locationRow;
    private int locationCol;
    /**
     *  Default constructor for objects of class RoomCorridor
     */
    public Room()
    {
        roomType = (int)(Math.random()*4);
        enterPoint = 'g';
        switch(roomType){
            case 0:
            map = new int[5][(int)(Math.random()*10)+10]; //horizontal corridor
            fillMap();
            break;
            case 1:
            map = new int[5][5]; //Small Room
            fillMap();
            break;
            case 2:
            map = new int[(int)(Math.random()*3)+10][(int)(Math.random()*3)+10]; //big Room
            fillMap();
            break;
            case 3:
            map = new int[(int)(Math.random()*10)+10][5]; //verticle corridor
            fillMap();
            break;
            default:
            map = new int[3][3];
            fillMap();
            break;
        }
    }

    //used for knowing where to enter and place player
    public Room(char enter)
    {
        enterPoint = enter;
        int rand = (int)(Math.random()*3);
        if(enterPoint == 'r' || enterPoint == 'l')
        {
            if(rand==0)
            {
                roomType=10;
                map = new int[5][5]; //Small Room
            }
            else if(rand==1)
            {
                roomType=10;
                map = new int[(int)(Math.random()*3)+10][(int)(Math.random()*3)+10]; //big Room
            }
            else
            {
                roomType=0;
                map = new int[5][(int)(Math.random()*10)+10]; //horizontal corridor
            }

        }
        else if(enterPoint == 't' || enterPoint == 'b')
        {
            if(rand==0)
            {
                roomType=10;
                map = new int[5][5]; //Small Room
            }
            else if(rand==1)
            {
                roomType=10;
                map = new int[(int)(Math.random()*3)+10][(int)(Math.random()*3)+10]; //big Room
            }
            else
            {
                roomType=3;
                map = new int[(int)(Math.random()*10)+10][5]; //verticle corridor
            }

        }
        //map = new int[5][5]; //Small Room
        //map = new int[(int)(Math.random()*3)+10][(int)(Math.random()*3)+10]; //big Room
        //map = new int[(int)(Math.random()*10)+10][5]; //verticle corridor
        //map = new int[3][3];
        fillMap();

    }

    public int getLocationRow()
    {
        return locationRow;
    }
    
    public int getLocationCol()
    {
        return locationCol;
    }
    
    public int getRoomType()
    {
        return roomType;
    }

    public int roomRow()
    {
        return map.length;
    }

    public int roomCol()
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
        placeDoors();
        placePlayer();
        fillMonster();
    }

    private void placePlayer()
    {
        if(enterPoint == 'l')
        {
            //spawn right
            locationRow = roomRow()/2;
            locationCol = roomCol() - 2;
        }
        else if(enterPoint == 'r')
        {
            //spawn left
            locationRow = roomRow()/2;
            locationCol = 2;
        }
        else if(enterPoint == 't')
        {
            //spawn bottom
            locationRow = roomRow()-2;
            locationCol = roomCol()/2;
        }
        else if(enterPoint == 'b')
        {
            //spawn top
            locationRow = 2;
            locationCol = roomCol()/2;
        }
    }

    private void placeDoors()
    {
        if(roomType==0)//horizontal
        {
            if(enterPoint == 'l')
            {
                map[roomRow()/2][0] = 2;//left 
            }
            else if(enterPoint == 'r')
            {
                map[roomRow()/2][roomCol()-1] = 2;//right
            }
            else
            {
                map[roomRow()/2][0] = 2;//left 
                map[roomRow()/2][roomCol()-1] = 2;//right
            }
        }
        else if(roomType==3)//verticle
        {
            if(enterPoint == 'b')
            {
                map[roomRow()-1][roomCol()/2] = 2;//bottom
            }
            else if(enterPoint == 't')
            {
                map[0][roomCol()/2] = 2;//top
            }
            else
            {
                map[roomRow()-1][roomCol()/2] = 2;//bottom
                map[0][roomCol()/2] = 2;//top
            }
        }
        else//square
        {
            if(enterPoint == 't')
            {
                map[roomRow()/2][0] = 2;//left
                map[roomRow()/2][roomCol()-1] = 2;//right
                map[0][roomRow()/2] = 2;//top
            }
            else if(enterPoint == 'b')
            {
                map[roomRow()-1][roomCol()/2] = 2;//bottom
                map[roomRow()/2][0] = 2;//left
                map[roomRow()/2][roomCol()-1] = 2;//right
            }
            else if(enterPoint == 'l')
            {
                map[roomRow()/2][0] = 2;//left
                map[0][roomCol()/2] = 2;//top
                map[roomRow()-1][roomCol()/2] = 2;//bottom
            }
            else if(enterPoint == 'r')
            {
                map[roomRow()/2][roomCol()-1] = 2;//right
                map[0][roomCol()/2] = 2;//top
                map[roomRow()-1][roomCol()/2] = 2;//bottom
            }
            else
            {
                map[roomRow()/2][0] = 2;//left
                map[roomRow()/2][roomCol()-1] = 2;//right
                map[0][roomRow()/2] = 2;//top
                map[roomRow()-1][roomCol()/2] = 2;//bottom
            }
        }
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
