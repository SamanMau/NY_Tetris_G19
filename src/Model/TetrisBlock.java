/**
 * A model class that represents a tetris block.
 * The class has relevant getters to manage changes in coordinates in the gameArea.
 * Study the methods and see in which context they are used (find usages).
 */
package Model;
import java.awt.*;

public class TetrisBlock  {
    private int[][] shape;
    private Color color;
    private int x; // is used to determine where in the game plan the tetris block is going to move in the x coordinate.
    private int y; // is used to determine where in the game plan the tetris block is going to move in the y coordinate.

    /**
     * Constructor used to create objects of a tetris block.
     * @param shape when creating a block, you enter 1:s and 0:s in a 2d array.
     * @param color and then you give it a color.
     */
   public TetrisBlock(int[][] shape, Color color){
       createBlock(shape, color);
       this.x = 4;
       this.y = 0;
   }

   public void createBlock(int[][] shape, Color color){
       this.shape = shape;
       this.color = color;
   }

   public int[][] getShape(){
       return this.shape;
   }

   public Color getColor(){
       return this.color;
   }

    /**
     * The methods below are used to increment or decrement x and y coordinates so that
     * we are able to move a block in tehe gameArea.
     */

    public void incrementY() {
        y++;
    }

    public void incrementX() {
        x++;
    }

    public void decrementX() {
        x--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void goRight(){
        x++;
    }

    public void goLeft(){
        x--;
    }

    public void goDown(){
        y++;
    }


    public void incrementY(int i) {
    }

    public int getHeight(){
        return shape.length;
    }


    //Rotate block
    public void rotationBlock() {
        this.shape = rotate();
    }

    public int[][] rotate(){
        int[][] tempShape = new int[shape[0].length][shape.length];
        int tempShapeRow = 0;
        int tempShapeCol = 0;

        for(int col = 0; col < shape[0].length; col++){
            for (int row = shape.length-1; row >= 0; row--) {
                tempShape[tempShapeRow][tempShapeCol] = shape[row][col];
                if (tempShapeCol < shape.length){
                    tempShapeCol++;
                }
            }
            tempShapeRow++;
            tempShapeCol = 0;
        }

        if(getX() + getShape().length  <= 10){
            return tempShape;
        }
        else {
            int decrementTimes = getX() + getShape().length - 10;
            for (int i = 0; i < decrementTimes; i++){
                decrementX();
            }
            return tempShape;
        }
    }
}
