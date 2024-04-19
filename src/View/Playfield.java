package View;

import Control.Controller;
import Model.*;

import javax.swing.*;
import java.awt.*;
/**
 * This class represent the playfield where the Tetris game is displayed.
 * Responsible for rendering the game board and Tetris blocks.
 */
public class Playfield extends JPanel{
    private final int column = 10;
    private final int row = 20;
    private final int kvadrat = 30;
    private TetrisBlock block;
    private Color[][] board = new Color[20][10];
    Controller controller;
    /**
     * Starts the playfield dimensions, background color, and layout.
     * Also start the controller and get the tetris block and game board from the controller.
     *
     * @param controller The controller managing game logic.
     */
    public Playfield(Controller controller){
        this.setPreferredSize(new Dimension(300, 600));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
        this.controller = controller;
        getBlock();
        getBoard();
    }

    /**
     * Get the current Tetris block from the controller.
     */
    private void getBlock(){
        block = controller.getBlock();
    }

    /**
     * Get the current game board from the controller.
     */
    private void getBoard(){
        board = controller.getBoard();
    }

    /**
     * Renders the game board and Tetris block.
     *
     * @param g The Graphics g used for rendering.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        getBoard();
        /*
        De två sista argumenten i "drawRect" är "width" och "height". Anledningen till varför
        vi skickar in kvadrat som argument är eftersom vi har initierat längden på kvadraten
        lite längre upp. Eftersom en kvadrat är lika långt på både höjden och bredden, så
        skickar vi in storleken på kvadrat på width och height.
        */

        /**
        * This method draw the playing field based on the board.
        * By looping through the board and check if the board position is filled with color
        * if the position is filled with color then the program will fill a color on the position.
        * Otherwise, it will draw an empty grid.
        */
        for(int width = 0; width < column; width++){
            for(int height = 0; height < row; height++){
                if (board[height][width] != null) {
                    g.setColor(board[height][width]);
                    g.fillRect(width * kvadrat, height * kvadrat, kvadrat, kvadrat);
                    g.setColor(Color.BLACK);
                    g.drawRect(width * kvadrat, height * kvadrat, kvadrat, kvadrat);
                }
                else {
                    g.setColor(Color.gray);
                    g.drawRect(width * kvadrat, height * kvadrat, kvadrat, kvadrat);
                }
            }
        }

        drawBlock(g);
    }

    /**
     * Draws the Tetris block on the playfield.
     *
     * @param g The Graphics g used for rendering.
     */
    private void drawBlock(Graphics g) {
        getBlock();
        for(int row = 0; row < block.getShape().length; row++){
            for (int col = 0; col < block.getShape()[0].length; col++){

                if(block.getShape()[row][col] == 1){
                    int x = col * kvadrat + block.getX() * kvadrat;
                    int y = row * kvadrat + block.getY() * kvadrat;

                    //Draw block
                    g.setColor(block.getColor());
                    g.fillRect(x, y, kvadrat, kvadrat);

                    //Draw grid within the block
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, kvadrat, kvadrat);
                }
            }
        }
    }
}
