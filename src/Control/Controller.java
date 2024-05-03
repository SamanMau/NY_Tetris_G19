/**
 * The controller class which handles the essential game logic.
 */
package Control;

import Model.IncorrectFormatException;
import Model.BlocksManager;
import Model.TetrisBlock;
import View.MainFrame;
import View.Playfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
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
    private Random rd = new Random();
    private int randomNum = rd.nextInt(7);
    private Color[][] board = new Color[20][10];
    private Timer speed;
    private boolean collision;
    private boolean gameState = false;
    private Playfield playfield;
    private MainFrame mainFrame;

    public Controller() {
        this.playfield = new Playfield(this);
        mainFrame = new MainFrame(this, playfield);
        blocksManager = new BlocksManager();
        this.listOfShape = blocksManager.getBlockList();
        this.listOfColors = blocksManager.getListOfColors();
        generateBlock();
        collision = false;
    }

    /**
     * This method is called when the user tries choosing a song.
     * JFileChooser opens up the file manager in the users computer.
     * The variable "openDialog" returns "0" if the user selected a file.
     * The try catch checks if the chosen file is a wav file or not.
     * @author Saman
     */
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

    public void startTimer(boolean gameState) {
        this.gameState = gameState;

        if (gameState) {
            this.speed = new Timer(400, new ActionListener() {
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

    //Den här metoden kontrollerar ifall det aktuella blocket har nått botten av spelplanen
    private boolean isAtBottom() {
        // block getY(),  hämtar den aktuella y-posisionen för det aktuella blocket på spelplanen
        // block.getShape().length, Hämtar höjden på det aktuella blocket på spelplanen
        //Först beräknas den potentiella positionen för botten av blocket
        //Sedan jämförs den beräknade position med längden av spelplanen
        //Om position är större eller lika med längeden på spelplan, innebär att blocket har nått botten eller passerat botten av spelplan
        return (block.getY() + block.getShape().length) * kvadrat >= board.length * kvadrat;
    }

    private boolean isCollidingWithBlock(int xIncrement) {
        int y = block.getY() + 1; // Get the Y-coordinate of the block's position on the board
        int x = block.getX() + xIncrement; // Get the X-coordinate of the block's position on the board
        int[][] shape = block.getShape(); // Get the shape of the block

        // Loop through each cell of the block shape
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                /*If the cell of the block at row and col contain 1,
                 set the board at Y-coordinate and X-coordinate to the color of the block*/
                if (shape[row][col] == 1) {
                    int boardRow = y + row;
                    int boardCol = x + col;

                    // Denna if-sats ser till att blocken inte går utanför spelplan
                    // Om boardRow + 1 är mindre än board.length innebär att det
                    // finns minst en rad kvar under den aktuella raden på spelplanen
                    // board[boardRow + 1][boardCol] != null, kontrollera om det finns block i nästa rad under det aktuella blocket
                    // Om både villkoren uppfylls returnerar true, alltså att det finns collision annars false
                    //if (boardRow + 1 < board.length && board[boardRow + 1][boardCol] != null) {
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
        int[][] shape = listOfShape.get(randomNum);
        Color color = listOfColors.get(randomNum);
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
     * This method is called when a button is clicked on the keyboard.
     * Different approaches are taken based on what key has been pressed.
     * The nested if statements checks whether the tetris block already
     * is at the edge or at the bottom of the playfield.
     * @param action refers to the button clicked
     * @author Saman, Huy
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
                if(!isCollidingWithBlock(0)) {
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

    /**
     * Method called to play a video from the desktop. URI is used to
     * format the file location to a URI, which then gets sent to
     * the "browse" method in the Desktop class to play the video.
     * @author Saman
     */
    public void playVideo(){
        File video = new File("src/Video/trailer.mp4");
        URI uri = video.toURI();

        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void restartGameLogic() {
        collision = false;
        if (!gameState) {
            startTimer(true);
        }
    }

    public void clearFullRows() {
        int width = board[0].length;
        int height = board.length;

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
                for (int r = row; r > 0; r--) {
                    for (int c = 0; c < width; c++) {
                        board[r][c] = board[r- 1][c];
                    }
                }

                //Columner i övra raden blir null
                for (int c = 0; c < width; c++) {
                    board[0][c] = null;
                }

                row++;
            }
        }
        playfield.repaint();
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