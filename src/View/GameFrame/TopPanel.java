/**
 * A panel in the GUI which represents the top panel. It has 3 buttons.
 * Flowcontrol is used to manage the volume of audio.
 */
package View.GameFrame;

import Control.Controller;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {
    private JButton startGame;
    private JButton showHighscore;
    private JButton playMusic;
    private JButton endGame;
    private JButton settings;
    private LPanel lPanel;
    private RPanel rPanel;
    private boolean gameStarted;
    private Controller controller;
    private Playfield playfield;
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private Color color1;
    private Color color2;


    /**
     * Constructor that sets a dimension for the panel and a color.
     * @param playfield used to call the timer method
     * @param mainFrame used to call a method that linkes keys (right, left, up, down, space) to a specific action.
     */
    public TopPanel(Playfield playfield, LPanel lPanel, RPanel rPanel, BottomPanel bottomPanel, MainFrame mainFrame, Controller controller){
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(Color.gray);

        this.bottomPanel = bottomPanel;
        this.lPanel = lPanel;
        this.rPanel = rPanel;

        this.controller = controller;
        this.mainFrame = mainFrame;
        this.playfield = playfield;
        setColor(Color.gray, Color.gray);
        CreateBtn();
        addActionListeners();

        this.setLayout(null);

        this.add(startGame);
        this.add(showHighscore);
        this.add(endGame);
        this.add(playMusic);
        this.add(settings);

        this.setVisible(true);
    }

    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
        rPanel.setColor(color1, color2);
        bottomPanel.setColor(color1, color2);
        lPanel.setColor(color1, color2);
        repaint();
    }


    /**
     * The paintComponent()- method is responsible for the drawing of the GUI.
     * "super.paintComponent(g)" is responsible for rendering and painting the background
     * The parameter "g" in the paintComponent()- method is casted to a Graphics2D object.
     * Which gives more access to the control of colors. GradientPaint is used to create the
     * gradient colors. x = 0 and y = 0 are the coordinates of where the colors will start
     * to gradiate, which is the top left corner. The getWidth and getHeight() methods
     * decides how far the colors will stretch.
     * @author Saman
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        GradientPaint gradientPaint = new GradientPaint(0, 0,
                color1, getWidth(), getHeight(), color2);

        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel
    }

    /**
     * Initializes buttons and gives them coordinates and colors. Each button is then given an
     * actionlistener, for instance, for the "startgame" button, we call the startTimer method which
     * basically makes the blocks fall down. And then we also call "linkButtonToEvent" to link keys to events.
     * For the "startgame" button we use "setFocusable(false)" because without this method, it is not possible to
     * use the keys after pressing a button. Try removing the method. And then, run the program, press "startGame"
     * button, and then you will se what I mean :)
     */
    private void CreateBtn(){
        startGame = new JButton("Start game");

        showHighscore = new JButton("Show highscore");

        playMusic = new JButton("Music on");

        endGame = new JButton("End game");

        settings = new JButton("Settings");

        startGame.setBounds(247, 28, 100, 35);
        Color green = new Color(0, 128, 60, 157);
        startGame.setBackground(green);
        startGame.setFocusPainted(false);
        startGame.setFocusable(false);


        showHighscore.setBounds(147, 64, 129, 35);
        Color orange = new Color(167, 112, 50);
        showHighscore.setBackground(orange);
        showHighscore.setFocusPainted(false);
        showHighscore.setFocusable(false);

        endGame.setBounds(335, 64, 115, 35);
        Color red = new Color(192, 30, 30);
        endGame.setBackground(red);
        endGame.setFocusPainted(false);
        endGame.setFocusable(false);

        playMusic.setBounds(0,0,100,35);
        playMusic.setBackground(Color.WHITE);
        playMusic.setFocusPainted(false);
        playMusic.setFocusable(false);

        playMusic.setActionCommand("gameMusic");


        settings.setBounds(500, 0, 100, 35);
        settings.setBackground(Color.WHITE);
        settings.setFocusable(false);

    }

    public void startSettingsFrame(){
        SettingsFrame settingsFrame = new SettingsFrame(controller);
    }

    public void addActionListeners(){
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame settingsFrame = new SettingsFrame(controller);
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.linkKeyToEvent(true);
                controller.startTimer(true);
                gameStarted = true;
            }
        });

        playMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.checkIfPlay(controller.getMusicOff());
                if(controller.getMusicOff() == "off"){
                    playMusic.setText("Music off");
                }
                else{
                    playMusic.setText("Music on");
                }
            }
        });

        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameStarted){
                    controller.stopTimer();
                    gameStarted = false;
                } else {
                    return;
                }
            }
        });
    }
}
