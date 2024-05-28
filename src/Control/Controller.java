/**
 * The controller class which handles the essential game logic.
 */
package Control;

import Model.IncorrectFormatException;
import Model.BlocksManager;
import Model.TetrisBlock;
import View.GameFrame.MainFrame;
import View.GameFrame.Playfield;
import View.LoginRegister.LoginRegisterFrame;
import View.MainMenu.MainMenu;
import View.GameFrame.TopPanel;
import Control.DatabaseController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

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
    private boolean gameState = true;
    private Playfield playfield;
    private MainFrame mainFrame;
    private Clip clip;
    private String music, musicOff;
    private FloatControl controlVolume;
    private File file;
    private AudioInputStream audioInputStream;
    private float previousAudioVolume = 0;
    private float currentAudioVolume = 0;
    private LoginRegisterFrame loginRegisterFrame;
    private DatabaseController databaseController;
    private String nameUser;
    private int userID;
    private String status;
    private int totalPoints;
    private int totalChallenges;
    private int totalGames;
    private Queue<TetrisBlock> blockQueue;

    public Controller() {
        this.playfield = new Playfield(this);
        loginRegisterFrame = new LoginRegisterFrame(this);
        blocksManager = new BlocksManager();
        this.listOfShape = blocksManager.getListOfShape();
        this.listOfColors = blocksManager.getListOfColors();
        //generateBlock();
        blockQueue = new LinkedList<>();
        addToQueue();
        collision = false;
        databaseController = new DatabaseController();
        music = "src/Ljud/audio1.wav";
        musicOff ="on";
        setFile(music);
        playMusic();
    }

    public void addToQueue(){
        while(blockQueue.size() < 2){
            blockQueue.add(generateBlock());
        }

        if(mainFrame != null){
            block = blockQueue.poll();

            TetrisBlock newBlock = blockQueue.peek();
            if(newBlock != null){
                int index = newBlock.getIndex();

                mainFrame.sendUpComingBlock(index);

            }
        }

    }

    public void setUserID(int id){
        this.userID = id;
    }

    public void setCurrentSpeed(int speed) {
        this.seconds = speed;
    }


    public int getCurrentSpeed() {
        return seconds;
    }

    public int getUserIDNoConn(){
        return userID;
    }

    public int getUserID(String nameUser){
        int id = databaseController.getUserID(nameUser);
        return id;
    }

    public String getStatus(int id){
        String status = databaseController.getStatus(id);
        return status;
    }

    public int getTotalGames(int id){
        int totalGames = databaseController.getTotalGames(id);
        return totalGames;
    }

    public int getTotalPoints(String nameUser){
        int id = databaseController.getUserID(nameUser);

        int points = databaseController.getUserPoints(id);
        return points;
    }

    public int getTotalChallenges(int id){
        int totalChallenges = databaseController.getTotalChallenges(id);
        return totalChallenges;
    }

    public void startMainFrame(){
        mainFrame = new MainFrame(this, playfield);
    }

    public void startMainMenu(){
        MainMenu mainMenu = new MainMenu(this, mainFrame);
    }

    public void setMultiColors(Color color1, Color color2, Color color3,
                               Color color4){
        if(mainFrame != null){
            mainFrame.setMultiColors(color1, color2, color3, color4);
        }
    }

    public void setColor(Color color1, Color color2){
        if(mainFrame != null){
            mainFrame.setColor(color1, color2);

        }
    }

    public MainFrame getMainFrame(){
        return mainFrame;
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

    public void startTimer(boolean gameState, int time) {
        this.gameState = gameState;
        this.seconds = time;

        if (gameState) {
            this.speed = new Timer(seconds, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (collision) {
                        addColorToBoard();
                        //generateBlock();
                        addToQueue();
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

    public boolean gameIsOver() {
        gameState = false;
        playfield.repaint();
        return false;
    }


    public void checkIfNewStatus(){
        int points = databaseController.getUserPoints(userID);
        String status = databaseController.getStatus(userID);

        if((points >= 10000) && (points <= 14000) && (!status.equals("Intermediate"))){ // 10000 && 20000 (original)
            databaseController.updateStatus("Intermediate", userID);

        } else if(points > 14000 && (points <= 20000) && (!status.equals("Advanced"))){ //20000 && 40000 (original)
            databaseController.updateStatus("Advanced", userID);

        } else if(points > 20000 && (points <= 24000) && (!status.equals("Expert"))){ // 40000 && 60000 (original)
            databaseController.updateStatus("Expert", userID);
        }
        else if(points > 24000 && (points <= 29000) && (!status.equals("Master"))){ //60000 && 80000 (original)
            databaseController.updateStatus("Master", userID);
        }
        else if(points > 29000 && (points <= 33000) && (!status.equals("Grandmaster"))){ //80000 && 100000 (original)
            databaseController.updateStatus("Grandmaster", userID);
        }
        else if(points > 33000 && (points <= 37000) && (!status.equals("Legend"))){ //100000 && 150000 (original)
            databaseController.updateStatus("Legend", userID);
        }
        else if((points > 37000) && (!status.equals("The Head Of The Table"))){ //150000 (original)
            databaseController.updateStatus("The Head Of The Table", userID);
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
            mainFrame.getTopPanel().setEnabledTrue();
            int totalPoints = mainFrame.getTotalPoints();
            databaseController.updateAmountGames(userID);
            databaseController.updatePoints(userID, totalPoints);
            checkIfNewStatus();
            gameIsOver();
            resetColorBoard();
            return true;
        } else {
            return false;
        }
    }

    public void resetGame() {
        mainFrame.resetPoints();
        mainFrame.resetLevel();
    }

    public boolean gameState() {
        return gameState;
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
                 /*   if (boardCol < board.length && board[boardRow][boardCol] != null) {
                        return true;
                    } */


                    if (boardRow >= board.length || boardCol < 0 || boardCol >= board[0].length) {
                        return true;
                    }


                    if (boardRow < board.length && board[boardRow][boardCol] != null) {
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
    public TetrisBlock generateBlock() {
        randomNum = rd.nextInt(7);
        int[][] shape = listOfShape.get(4);
        Color color = listOfColors.get(4);
        block = new TetrisBlock(shape, color,4);
        return block;
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
                if(!isCollidingWithBlock(0)) {
                    block.goDown();
                }
            } while (action.equals("space") && !isAtBottom() && !isCollidingWithBlock(0));

            if (isAtBottom() || isCollidingWithBlock(0)) {
                block.incrementY(-1);
                addColorToBoard();
                clearFullRows();
                //generateBlock();
                addToQueue();
                collision = false;
            }

        } else if (action.equals("up")) {
            if (!isCollidingWithBlock(0) && !isAtBottom()) {
                block.rotationBlock();
            }
        }
        playfield.repaint();
    }

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

    public void setFile(String SoundFileName) {
        try {
            file = new File(SoundFileName);
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            controlVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolume.setValue(-20.0f);

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
        clip.close();
    }

    public void setNewMusic(String newSong){
        clip.stop();
        clip.close();
        music = newSong;

        if(musicOff.equals("on")){
            setFile(music);
            playMusic();
        }
    }

    /**
     * This method is used to lower the volume of music.
     * The lowest volume that a floatControl can manage is
     * -80, of type float.
     * @author Saman
     */
    public void decrementVolume() {
        currentAudioVolume -= 3.0f; //"f" står för float

        if(currentAudioVolume < -80.f){
            currentAudioVolume = -80.f;
        }

        controlVolume.setValue(currentAudioVolume);
    }

    /**
     * Increments the volume of the music currently playing.
     * "6.0" is the highest decible that a song can handle.
     * We need to make sure that the audio does not go above
     * 6.0, as it would cause an error. The higher the number,
     * the higher the volume. We then set this audio volume
     * to the float control, which changes the volume.
     * @author Saman
     */
    public void incrementVolume() {
        if(currentAudioVolume >= 6.0f){
            currentAudioVolume = 6.0f;
        }
        else{
            currentAudioVolume += 3.0f; //"f" står för float
        }

        controlVolume.setValue(currentAudioVolume);
    }

    /**
     * Checks if the music is playing or not.
     * @param musicOff
     * @author Saman, Melvin
     */
    public void checkIfPlay(String musicOff){
        if (musicOff.equals("off")) {
            setFile(music);
            playMusic();
            this.musicOff = "on";
           // playMusic.setText("Music on");
        }

        else if (musicOff.equals("on")) {
            stop();
            this.musicOff = ("off");
            //playMusic.setText("Music off");
        }
    }

    public int validateUserLoginInfo(String name, String password){
        int exists = databaseController.validateUserLoginInfo(name, password);

        if(exists == 1){
            setUserName(name);
            int id = getUserID(nameUser);
            setUserID(id);
        }

        return exists;
    }

    public String validateUserRegisterInfo(String name, String password){
        String userName = databaseController.validateUserRegisterInfo(name, password);
        setUserName(userName);
        int id = getUserID(nameUser);
        setUserID(id);

        return userName;
    }

    public void setUserName(String name){
        this.nameUser = name;
    }

    public String getUserName(){
        return nameUser;
    }

    public String getMusicOff() {
        return musicOff;
    }

    public void changeKeys(String left, String right, String up, String down, String space){
        if (mainFrame != null){
            mainFrame.createKeys(left,right,up,down,space);
        }
    }
    public void changeTheme(String theme){
        if (mainFrame != null){
            if(theme == "Wildwest"){
                mainFrame.changeTheme(theme);
            }
            else if(theme == "Party"){
                mainFrame.changeTheme(theme);
            }
        }
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
}