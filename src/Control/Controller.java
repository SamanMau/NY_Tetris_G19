/**
 * The controller class which handles the essential game logic.
 */
package Control;

import Model.IncorrectFormatException;
import Model.BlocksManager;
import Model.TetrisBlock;
import View.LPanel;
import View.MainFrame;
import View.Playfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private BlocksManager blocksManager;
    private ArrayList<int[][]> listOfShape;
    private ArrayList<Color> listOfColors;
    private TetrisBlock block;
    private int kvadrat = 30;
    private final int column = 10;
    private final int row = 20;
    private int seconds;
    private Random rd = new Random();
    private int randomNum = rd.nextInt(7);
    private Color[][] board = new Color[20][10];
    private Timer speed;
    private boolean collision;
    private boolean gameState = false;
    private Playfield playfield;
    private MainFrame mainFrame;
    // private ArrayList<Integer> scores = new ArrayList<>();


    public Controller() {
        this.playfield = new Playfield(this);
        mainFrame = new MainFrame(this, playfield);
        blocksManager = new BlocksManager();
        this.listOfShape = blocksManager.getBlockList();
        this.listOfColors = blocksManager.getListOfColors();
        generateBlock();
        collision = false;
    }

    public void chooseOwnSong() {
        JFileChooser fileChooser = new JFileChooser();
        int openDialog = fileChooser.showSaveDialog(null);

        try {
            if (openDialog == 0) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                String name = file.toString();

                if (!(name.endsWith(".wav"))) {
                    throw new IncorrectFormatException("The file type needs to be .wav");
                } else {
                    mainFrame.sendFileToTopPanel(name);
                }
            }
        } catch (IncorrectFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public int getCurrentSpeed(){
        return seconds;
    }

    public void startTimer(boolean gameState, int time) {
        this.gameState = gameState;
        this.seconds = time;

        if (gameState) {
            this.speed = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (collision) {
                        addColorToBoard();
                        generateBlock();
                        collision = false;
                    } else {
                        boolean checkBlockOutOfPlayfield = checkBlockOutOfPlayfield();
                        if (!checkBlockOutOfPlayfield) {
                            if (isAtBottom() || isCollidingWithBlock(0)) {
                                addColorToBoard();
                            } else {
                                block.incrementY();
                            }
                        } else {
                            stopTimer();
                        }
                    }
                    playfield.repaint();
                }
            });
            this.speed.start();
        }
    }

    private boolean checkBlockOutOfPlayfield() {
        int blockHeight = block.getHeight();
        int rowWithColor = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != null) {
                    rowWithColor++;
                    break;
                }
            }
        }
            if (blockHeight + rowWithColor > board.length) {
            System.out.println("You lost");
            resetColorBoard();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the current block has reached or passed the bottom of the game board.
     * block.getY() : gets the current y-coordinate of the block on the game board
     * block.getShape().length : gets the height of the current block on the game board.
     * First, calculates the potential position of the bottom of the block.
     * Then compares the calculated position with the length of the game board.
     * If the position is greater than or equal to the length of the game board, it means the block has reached or passed the bottom of the board.
     * @return true if the block is at or below the bottom of the board, false otherwise.
     * @author Abdulkadir
     *
     */
    private boolean isAtBottom() {
        return (block.getY() + block.getShape().length) * kvadrat >= board.length * kvadrat;
    }

    /**
     * Checks if the current block collides with another block when moved horizontally by the given increment.
     * It loops through each cell of the block shape, then it checks if the cell of the block at row and col contain 1.
     * Then it checks if the specified column index is valid with in the game board's bound
     * and if there is a block already placed at the calculated position
     * @param xIncrement The amount by which the block is moved horizontally.
     * @return True if the block collides with another block, otherwise false
     * @author Abdulkadir, Bisma
     */
    private boolean isCollidingWithBlock(int xIncrement) {
        int y = block.getY() + 1;
        int x = block.getX() + xIncrement;
        int[][] shape = block.getShape();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {

                if (shape[row][col] == 1) {
                    int boardRow = y + row;
                    int boardCol = x + col;

                    if (boardCol < board.length && board[boardRow][boardCol] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Generates a random number between 0 - 6. This random generated number
     * is then used to get a tetris block from an index. We retrieve its shape
     * and color, and then we create a new instance of "block".
     */
    public void generateBlock() {
        randomNum = rd.nextInt(7);
        int[][] shape = listOfShape.get(4);
        Color color = listOfColors.get(4);
        block = new TetrisBlock(shape, color);
    }

    /**
     * Return a Tetris block with shape and color.
     *
     * @return a tetris block
     */
    public TetrisBlock getBlock() {
        return block;
    }

    /**
     * Adds the color of the current Tetris block to the game board.
     * This method loops through each cell of the block shape,
     * check its position on the board, and sets the board cell to the block color.
     */
    public void addColorToBoard() {
        int y = block.getY(); // Get the Y-coordinate of the block's position on the board
        int x = block.getX(); // Get the X-coordinate of the block's position on the board
        int[][] shape = block.getShape(); // Get the shape of the block

        // Loop through each cell of the block shape
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {

                /*If the cell of the block at row and col contain 1,
                 set the board at Y-coordinate and X-coordinate to the color of the block*/
                if (shape[row][col] == 1) {
                    int boardRow = y + row;
                    int boardCol = x + col;

                    //Den kontrollerar att blocken inte går utanför spelplanen
                    //Om platsen vi ska gå till innehåller redan block (dvs den är inte null) så fortsätter loopen uppåt
                    board[boardRow][boardCol] = block.getColor();
                }
            }
        }
        collision = true;
        clearFullRows();
    }


    /**
     * Return a game board.
     *
     * @return a game board
     */
    public Color[][] getBoard() {
        return board;
    }

    public void stopTimer() {
        this.speed.stop();
    }

    /**
     * The if statemnts after "if(action.equals(specific action)" basically checks if the tetris block already
     * is at the edge of the play field. This is essential so, when the block is at the edge or at the bottom,
     * the user cannot longer move the block. In informal words, the first if statement can be translated to
     * "if the block already is at the edge of the game area or if the block has touched the bottom". If this
     * statement is not correct, then the block is moved to the relevant location.
     *
     * @param action represents what key has been pressed.
     */
    public void decideMove(String action) {
        if (action.equals("left")) {
            if ((block.getX() == 0) || isAtBottom() || isCollidingWithBlock(-1)) {
                return;
            }
            block.goLeft();
        } else if (action.equals("right")) {
            if ((block.getX() + block.getShape()[0].length >= column) || isAtBottom() || isCollidingWithBlock(1)) {
                return;
            }
            block.goRight();
        } else if (action.equals("down") || action.equals("space")) {

            do {
                if (!isCollidingWithBlock(0)) {
                    block.goDown();
                }
            } while (action.equals("space") && !isAtBottom() && !isCollidingWithBlock(0));

            if (isAtBottom() || isCollidingWithBlock(0)) {
                block.incrementY(-1);
                addColorToBoard();
                clearFullRows();
                generateBlock();
                collision = false;
            }

        } else if (action.equals("up")) {
            if (!isCollidingWithBlock(0) && !isAtBottom()) {
                block.rotationBlock();
            }
        }
        playfield.repaint();
    }


  /*  private void restartGameLogic() {
        collision = false;
        if (!gameState) {
            startTimer(true);
        }
    } */

    public void clearFullRows() {
        int width = board[0].length;
        int height = board.length;
        int pointsEarned = 0;
        int rowsCleared = 0;

        //loop som kollar ifall någon rad är fylld.
        for (int row = height - 1; row >= 0; row--) {
            boolean fullRow = true;

            //inre loop går igenom varje column i en specefik rad för
            // att se om alla columner är fyllda.
            for (int col = 0; col < width; col++) {
                if (board[row][col] == null) {
                    fullRow = false;
                    break;
                }
            }
            //om true, en loop som raderar raden och flyttar ovanstående ner.
            // "r" = varje kolumn i specefik rad får värdet av kolumnen från raden ovanför.
            if (fullRow) {
              //  int width = board[0].length;
                //  int height = board.length;
                for (int r = row; r > 0; r--) {
                    for (int c = 0; c < width; c++) {
                        board[r][c] = board[r- 1][c];
                    }
                }

                //Columner i övra raden blir null
                for (int c = 0; c < width; c++) {
                    board[0][c] = null;
                }
                rowsCleared++;
               // clearRow(row);
                row++;
            }

        }

        pointsEarned = calculatePointsForRowClear(rowsCleared);
        mainFrame.incrementPoints(pointsEarned);
        playfield.repaint();
    }

    /**
     * Calculates the points earned for clearing rows based on the number of rows cleared.
     * This method calls in clearFullRows method.
     * @param rowsCleared - represents the number of rows that have been cleared
     * @return - The points earned by clearing the specified rows
     * @author Abdulkadir
     */

    private int calculatePointsForRowClear(int rowsCleared) {
        int pointsEarned = 0;

        switch (rowsCleared) {
            case 1:
                pointsEarned = 100;
                break;
            case 2:
                pointsEarned = 300;
                break;
            case 3:
                pointsEarned = 500;
                break;
            case 4:
                pointsEarned = 800;
                break;
            default:
                break;
        }
        return pointsEarned;
    }
    
    public void resetColorBoard(){
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = null;
            }
        }
        gameState = false;
        disableKeyboard();
        stopTimer();
        playfield.repaint();
    }

    public void disableKeyboard(){
        mainFrame.disableKeyboard("leftKey");
        mainFrame.disableKeyboard("rightKey");
        mainFrame.disableKeyboard("upKey");
        mainFrame.disableKeyboard("downKey");
        mainFrame.disableKeyboard("spaceKey");
    }

}