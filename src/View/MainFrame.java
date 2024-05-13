/**
 * This class represents the main frame of the GUI. This class is responsible for
 * adding the different panels to the GUI. This class also manages the creation
 * of keys and links them to actions. The main objects used to create keys is
 * "InputMap" and "ActionMap". Inputmap decides which keys should react to
 * keyboard clicks, while ActionMap decides what will happen when a button
 * is clicked.
 * @author Saman, Huy
 */
package View;

import Control.Controller;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame{
    private static int width = 615; //600
    private static int height = 840; // 800
    private InputMap key;
    private View.Playfield playfield;
    private ActionMap action;
    private Controller controller;
    private View.TopPanel topPanel;
    private View.LPanel lPanel;
    private View.RPanel rPanel;
    private View.BottomPanel bottomPanel;

    /**
     * Constructor, creates and adds the different panels.
     * @param controller controller object
     * @param playfield playfield object, the game field itself.
     * @author Huy
     */
    public MainFrame(Controller controller, View.Playfield playfield){
        super("Tetris");
        this.controller = controller;
        this.setSize(width, height);
        this.playfield = playfield;
        lPanel = new View.LPanel(controller);
        rPanel = new View.RPanel();
        bottomPanel = new View.BottomPanel();
        topPanel = new View.TopPanel(playfield, lPanel, rPanel, bottomPanel, this, controller);

        //Add panel
        this.add(lPanel, BorderLayout.WEST);
        this.add(rPanel, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(playfield, BorderLayout.CENTER);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        createKeys("LEFT", "RIGHT", "UP", "DOWN", "SPACE");
    }

    /**
     * This method creates key binds for keyboard inputs. The first
     * parameter after "getKeyStroke" represents the name of the key
     * on your keyboard, while "actionMapKey" is the identifier.
     * @author Saman, Melvin
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
     * @author Saman
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

    public void setEnabledTrue(){
        topPanel.setEnabledTrue();
    }

    public void sendFileToTopPanel(String file){
            topPanel.setNewMusic(file);
    }

    public void incrementPoints(int points){
        lPanel.updateScore(points);
    }

}
