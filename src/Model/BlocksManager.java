/**
 * A class used to manage a list of blocks.
 * Den har en instansvariabel för varje
 */

package Model;
import java.awt.*;
import java.util.*;
/**
 * This class manage a list of blocks for the game.
 * Each block is represented by a 2D array of integers where 1 represents a block cell and 0 represents an empty cell.
 * This class also manages a list of colors related to each block.
 */


public class BlocksManager {
    private int[][] tBlock;
    private int[][] lBlock;
    private int[][] zBlock;
    private int[][] sBlock;
    private int[][] iBlock;
    private int[][] oBlock;
    private int[][] jBlock;
    private ArrayList<int[][]> listOfShape;
    private ArrayList<Color> listOfColors;

    /**
     * Create blocks and add them to the list.
     * Also create the list of colors.
     */
    public BlocksManager(){
        createBlock();
        listOfShape = new ArrayList<>();
        this.listOfColors = new ArrayList<>();
        addBlockToList();
        addColorsToList();
    }

    public void createBlock(){
        tBlock = new int[][]{{0, 1, 0}, {1, 1, 1}};
        lBlock = new int[][]{{1, 0}, {1, 0}, {1, 1}};
        zBlock = new int[][]{{1,1,0},{0,1,1}};
        sBlock = new int[][]{{0,1,1},{1,1,0}};
        iBlock = new int[][]{{1},{1},{1},{1}};
        jBlock = new int[][]{{0,1},{0,1},{1,1}};
        oBlock = new int[][]{{1,1}, {1,1}};

    }
    /**
     * Initializes all block.
     */
    public void addBlockToList(){
        listOfShape.add(tBlock);
        listOfShape.add(lBlock);
        listOfShape.add(zBlock);
        listOfShape.add(sBlock);
        listOfShape.add(iBlock);
        listOfShape.add(oBlock);
        listOfShape.add(jBlock);
    }
    /**
     * Add to the list of colors.
     */
    public void addColorsToList(){
        listOfColors.add(Color.white);
        listOfColors.add(Color.orange);
        listOfColors.add(Color.green);
        listOfColors.add(Color.red);
        listOfColors.add(Color.cyan);
        listOfColors.add(Color.pink);
        listOfColors.add(Color.yellow);
    }

    /**
     * Gets the list of block shape.
     *
     * @return The list of block.
     */
    public ArrayList<int[][]> getListOfShape(){
        return listOfShape;
    }

    /**
     * Gets the list of colors.
     *
     * @return The list of colors.
     */
    public ArrayList<Color> getListOfColors(){return listOfColors;}
}
