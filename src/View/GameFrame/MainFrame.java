/**
 * This class represents the main frame of our GUI. This class is responsible for
 * adding the different panels (LPanel, RPanel, Playfield etc.) to the GUI.
 * This class also manages the creation of keys and links them to actions.
 * The main objects used to create keys is "InputMap" and "ActionMap".
 * These classes makes it possible for the user to control in what direction the tetris blocks will go.
 * Inputmap tells the computer which keyboard key should be linked to an "identifier" which you could name to anything.
 * The ActionMap then uses this identifier from the InputMap to associate an action / event to each key.
 * This concept is very similar to the relation between keys and values in a hashmap.
 */
package View.GameFrame;

import Control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private static int width = 615; //600
    private static int height = 840; // 800
    private InputMap key;
    private View.GameFrame.Playfield playfield;
    private ActionMap action;
    private Controller controller;
    private View.GameFrame.TopPanel topPanel;
    private View.LPanel lPanel;
    private View.GameFrame.RPanel rPanel;
    private View.GameFrame.BottomPanel bottomPanel;
    private JLabel counterLabel;
    private Timer timer;
    private int second;
    private JButton returnPauseButton;

    public MainFrame(Controller controller, View.GameFrame.Playfield playfield){
        super("Tetris");

        this.controller = controller;
        this.setSize(width, height);
        this.playfield = playfield;
        lPanel = new View.LPanel(controller);
        rPanel = new View.GameFrame.RPanel();
        bottomPanel = new View.GameFrame.BottomPanel();
        topPanel = new View.GameFrame.TopPanel(playfield, lPanel, rPanel, bottomPanel, this, controller);
        second = 4;
        startGame();

        //Add panel
        this.add(lPanel, BorderLayout.WEST);
        this.add(rPanel, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(playfield, BorderLayout.CENTER);
        playfield.setVisible(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        createKeys("LEFT", "RIGHT", "UP", "DOWN", "SPACE");

    }

    /**
     * This method creates key binds for keyboard inputs. The method "put" can be seen as the method "add" in an arraylist.
     * The parameter you see marked as "s" right after "getKeyStroke" represents the name of the key on your keyboard,
     * while "actionMapKey" is the identifier.
     */
    public void createKeys(String left, String right, String up, String down, String space){
        this.key = this.getRootPane().getInputMap();
        action = this.getRootPane().getActionMap();

        key.put(KeyStroke.getKeyStroke(left), "leftKey");
        key.put(KeyStroke.getKeyStroke(space), "spaceKey");
        key.put(KeyStroke.getKeyStroke(down), "downKey");
        key.put(KeyStroke.getKeyStroke(right), "rightKey");
        key.put(KeyStroke.getKeyStroke(up), "upKey");

    }
    /**
     * Links specific keyboard inputs to actions to move the blocks.
     * This method is called from the TopPanel class when the user presses the
     * "startGame" button. In this method, for each key, we send the name of the key to a method
     * in the controller class "decideMove" which determines whether the block will change
     * its location on the x coordinate or y coordinate.
     * @param started Indicates whether the game has started or not.
     */
    public void linkKeyToEvent(boolean started){
        if(started){
            action.put("leftKey", new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.decideMove("left");
                }
            });

            action.put("spaceKey", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.decideMove("space");
                }
            });

            action.put("downKey", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.decideMove("down");
                }
            });

            action.put("rightKey", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.decideMove("right");
                }
            });

            action.put("upKey", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.decideMove("up");
                }
            });
        }
    }

    public void sendUpComingBlock(int index){
        switch (index){
            case 0: lPanel.showUpComingBlock("src/Bilder/0.png");
                break;

            case 1: lPanel.showUpComingBlock("src/Bilder/1.png");
                break;

            case 2: lPanel.showUpComingBlock("src/Bilder/2.png");
                break;

            case 3: lPanel.showUpComingBlock("src/Bilder/3.png");
                break;

            case 4: lPanel.showUpComingBlock("src/Bilder/4.png");
                break;

            case 5: lPanel.showUpComingBlock("src/Bilder/5.png");
                break;

            case 6: lPanel.showUpComingBlock("src/Bilder/6.png");
                break;

        }

    }

    public void disableKeyboard(String key) {
        action.remove(key);
    }

    public void sendFileToTopPanel(String file){
            controller.setNewMusic(file);
    }

    public void changeTheme(String theme){
        if(theme == "Wildwest"){
            Color color1 = new Color(255, 209, 87);
            Color color2 = new Color(194, 53, 45);

            topPanel.setColor(color2 ,color1);
            controller.setNewMusic("src/Ljud/wildWest.wav");
        }
        else if(theme == "Party"){
            topPanel.setColor(Color.MAGENTA, Color.YELLOW);
            controller.setNewMusic("src/Ljud/party.wav");
        }
    }

    public void incrementPoints(int points){
        lPanel.updateScore(points);
    }

    public int getTotalPoints(){
        return lPanel.getTotalPoints();
    }

    public View.GameFrame.TopPanel getTopPanel() {
        return topPanel;
    }

    public void setMultiColors(Color color1, Color color2, Color color3,
                               Color color4){
        topPanel.setMultiColors(color1, color2, color3, color4);
    }

    public void setColor(Color color1, Color color2){
        topPanel.setColor(color1, color2);
    }

    public void startGame(){
        counterLabel = new JLabel();
        counterLabel.setBounds(280, 325, 100, 100);
        counterLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 70));
        this.add(counterLabel);
        topPanel.disablePauseButton();
        counter();
        timer.start();
    }

    public void counter(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                counterLabel.setText(String.valueOf(second));
                if(second == 0){
                    linkKeyToEvent(true);
                    playfield.setVisible(true);
                    controller.startTimer(true, 500); //500
                    counterLabel.setVisible(false);
                    topPanel.enablePauseButton();
                    timer.stop();
                }
            }
        });

    }

    public void resetPoints() {
        lPanel.resetPoints();
    }

    public void resetLevel() {
        lPanel.resetLevel();
    }
}
