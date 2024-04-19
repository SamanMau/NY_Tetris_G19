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
    private Playfield playfield;
    private ActionMap action;
    private Controller controller;
    private TopPanel topPanel;
    private LPanel lPanel;
    private RPanel rPanel;
    private BottomPanel bottomPanel;

    public MainFrame(Controller controller, Playfield playfield){
        super("Tetris");
        this.controller = controller;
        this.setSize(width, height);
        this.playfield = playfield;
        topPanel = new TopPanel(playfield, this, controller);
        lPanel = new LPanel();
        rPanel = new RPanel();
        bottomPanel = new BottomPanel();

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
     * This method creates key binds for keyboard inputs. The method "put" can be seen as the method "add" in an arraylist.
     * The parameter you see marked as "s" right after "getKeyStroke" represents the name of the key on your keyboard,
     * while "actionMapKey" is the identifier.
     */
    public void createKeys(String left, String right, String up, String down, String space){
        InputMap key;

        key = this.getRootPane().getInputMap();
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

    public void sendFileToTopPanel(String file){
            topPanel.setNewMusic(file);
    }
}
