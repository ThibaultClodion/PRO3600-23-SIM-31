package Map;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Map
{
    //Grid
    private final char[][] grid;

    //Map data's
    public int width = 15;
    public int height = 10;
    public int tileWidth = 64;
    public int tileHeight = 64;

    //Texture and Sprites
    private final Texture groundImage = new Texture("ground.png");
    private final Texture rockImage = new Texture("rock.png");
    private final Texture holeImage = new Texture("hole.png");

    //region <Map Creation>
    public Map(int seed)
    {
        //Initialize a basic map
        grid = new char[width][height];

        for(int i = 0; i < width;i++)
        {
            for(int j = 0; j < height; j++)
            {
                grid[i][j] = 'G';
            }
        }

        //Initialize the prefabs array
        char[][][] prefabs = createPrefabs();

        //Define the nbElementMax and the seed used
        Random random = new Random(seed);
        int nbElementsMax = random.nextInt(40);

        //While there is still elements to put on the map
        while(nbElementsMax > 0)
        {
            //Get a random prefab
            char[][] prefab = prefabs[random.nextInt(prefabs.length)];

            //Decrement the number of elements to put
            nbElementsMax -= HowManyElement(prefab);

            //Find the random position to put the new prefab
            int xPosition = random.nextInt(0, this.width+1);
            int yPosition = random.nextInt(0, this.height+1);

            //Instantiate the prefab on the map
            for(int i = 0; i < prefab.length; i++)
            {
                for(int j = 0; j < prefab[i].length; j++)
                {
                    //Check if the position selected is valid
                    if(isValidPosition(xPosition + i, yPosition + j))
                    {
                        //Allow to only define the useful things in the prefabs
                        if(prefab[i][j] == 'R' || prefab[i][j] == 'H')
                        {
                            grid[xPosition + i][yPosition + j] = prefab[i][j];
                        }
                    }
                }
            }
        }
    }

    private boolean isValidPosition(int line, int column)
    {
        //Check if the position is valid
        return (0 <= line) &&  (line < width) && (0 <= column) && (column < height);
    }

    private int HowManyElement(char[][] prefab)
    {
        int cpt = 0;

        for (char[] line : prefab) {
            for (char c : line) {
                if (c == 'R' || c == 'H') {
                    cpt++;
                }
            }
        }

        return cpt;
    }

    private char[][][] createPrefabs()
    {
        char[][] singleRock = new char[1][1];
        singleRock[0][0] = 'R';

        char[][] twoRockVertical = new char[2][1];
        twoRockVertical[0][0] = 'R';
        twoRockVertical[1][0] = 'R';

        char[][] twoRockHorizontal = new char[1][2];
        twoRockHorizontal[0][0] = 'R';
        twoRockHorizontal[0][1] = 'R';

        char[][] LformRockV1 = new char[2][2];
        LformRockV1[0][0] = 'R';
        LformRockV1[0][1] = 'R';
        LformRockV1[1][0] = 'R';

        char[][] LformRockV2 = new char[2][2];
        LformRockV2[0][0] = 'R';
        LformRockV2[0][1] = 'R';
        LformRockV2[1][1] = 'R';

        char[][] LformRockV3 = new char[2][2];
        LformRockV3[1][0] = 'R';
        LformRockV3[0][1] = 'R';
        LformRockV3[1][1] = 'R';

        char[][] LformRockV4 = new char[2][2];
        LformRockV4[0][0] = 'R';
        LformRockV4[1][1] = 'R';
        LformRockV4[1][0] = 'R';

        char[][] TformRock = new char[3][2];
        TformRock[0][0] = 'R';
        TformRock[1][1] = 'R';
        TformRock[2][0] = 'R';

        char[][] singleHole = new char[1][1];
        singleHole[0][0] = 'H';

        char[][] twoHoleVertical = new char[2][1];
        twoHoleVertical[0][0] = 'H';
        twoHoleVertical[1][0] = 'H';

        char[][] twoHoleHorizontal = new char[1][2];
        twoHoleHorizontal[0][0] = 'H';
        twoHoleHorizontal[0][1] = 'H';

        char[][] LformHoleV1 = new char[2][2];
        LformHoleV1[0][0] = 'H';
        LformHoleV1[0][1] = 'H';
        LformHoleV1[1][0] = 'H';

        char[][] LformHoleV2 = new char[2][2];
        LformHoleV2[0][0] = 'H';
        LformHoleV2[0][1] = 'H';
        LformHoleV2[1][1] = 'H';

        char[][] LformHoleV3 = new char[2][2];
        LformHoleV3[1][0] = 'H';
        LformHoleV3[0][1] = 'H';
        LformHoleV3[1][1] = 'H';

        char[][] LformHoleV4 = new char[2][2];
        LformHoleV4[0][0] = 'H';
        LformHoleV4[1][1] = 'H';
        LformHoleV4[1][0] = 'H';

        char[][] TformHole = new char[3][2];
        TformHole[0][0] = 'H';
        TformHole[1][1] = 'H';
        TformHole[2][0] = 'H';


        return new char[][][] {singleRock, twoRockVertical, twoRockHorizontal, LformRockV1, LformRockV2, LformRockV3, LformRockV4,
        TformRock,TformHole, singleHole, twoHoleVertical, twoHoleHorizontal, LformHoleV1, LformHoleV2, LformHoleV3, LformHoleV4};
    }
    //endregion

    //region <Map Visualisation>
    public Texture GetTexture(int line, int column)
    {
        if(grid[line][column] == 'G')
        {
            return groundImage;
        }
        else if(grid[line][column] == 'R')
        {
            return rockImage;
        }
        else if(grid[line][column] == 'H')
        {
            return holeImage;
        }

        //Normally there should not be other things then G, R, H for now
        else
        {
            return groundImage;
        }
    }
    //endregion

    //region <Map Destruction>
    public void Dispose()
    {
        //Dispose all the Textures
        groundImage.dispose();
        rockImage.dispose();
        holeImage.dispose();
    }
    //endregion
}
