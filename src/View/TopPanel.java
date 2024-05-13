/**
 * A panel in the GUI which represents the top panel. It has 3 buttons.
 * Flowcontrol is used to manage the volume of audio.
 * @author Saman, Melvin
 */
package View;

import Control.Controller;
import View.Settings.SettingsFrame;
import View.LPanel;
import View.Playfield;
import View.MainFrame;
import View.BottomPanel;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class TopPanel extends JPanel {
    private JButton startGame;
    private JButton showHighscore;
    private JButton playMusic;
    private JButton endGame;
    private JButton settings;
    private LPanel lPanel;
    private View.RPanel rPanel;
    private boolean gameStarted;
    private Clip clip;
    private Controller controller;
    private sound se= new sound();
    private String music, musicOff;
    private FloatControl controlVolume;

    private Playfield playfield;
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private boolean multiColors;

    private float previousAudioVolume = 0;
    private float currentAudioVolume = 0;

    /**
     * Constructor that sets a dimension for the panel and a color.
     * @param playfield used to call the timer method
     * @param mainFrame used to call a method that linkes keys (right, left, up, down, space) to a specific action.
     */
    public TopPanel(View.Playfield playfield, View.LPanel lPanel, RPanel rPanel, BottomPanel bottomPanel, MainFrame mainFrame, Controller controller){
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(Color.gray);

        this.bottomPanel = bottomPanel;
        this.lPanel = lPanel;
        this.rPanel = rPanel;
        currentAudioVolume = -20;

        this.controller = controller;
        this.mainFrame = mainFrame;
        this.playfield = playfield;

        Color color1 = new Color(106, 130, 251);
        Color color2 = new Color(218, 119, 242);

        setColor(color1, color2);
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

    public void setMultiColors(Color color1, Color color2, Color color3,
                               Color color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        rPanel.setMultiColors(color1, color2, color3, color4);
        bottomPanel.setMultiColors(color1, color2, color3, color4);
        lPanel.setMultiColors(color1, color2, color3, color4);
        multiColors = true;
        repaint();
    }

    /**
     * Sets the two colors of the GUI. If the colors are different
     * then the colors will fade together, creating a gradient color.
     * @param color1 first color that will be faded
     * @param color2 second color that will be faded
     * @author Saman
     */
    public void setColor(Color color1, Color color2){
        this.color1 = color2;
        this.color2 = color1;
        rPanel.setColor(color1, color2);
        bottomPanel.setColor(color1, color2);
        lPanel.setColor(color1, color2);
        multiColors = false;
        repaint();
    }

    public void setNewMusic(String newSong){
        clip.stop();
        clip.close();
        music = newSong;
        controlVolume.setValue(currentAudioVolume);

        if(musicOff.equals("on")){
            se.setFile(music);
            se.playMusic();
        }
    }

    /**
     * This method manages the gradient of colors. "super.paintComponent(g)" is
     * responsible for rendering and painting the background. "Graphics2D"
     * gives more control of the colors. The gradientpaint object is used to
     * create gradient colors. x and y are the coordinates of where the colors
     * will start to gradiate (top left corner). The getWidth and getHeight()
     * methods decides how far the colors will stretch.
     * @param g the <code>Graphics</code> object to protect
     * @author Saman
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        if(multiColors){
            Point2D startGradient = new Point2D.Float(0, 0); //starting point of the gradient
            Point2D endGradient = new Point2D.Float(getWidth(), getHeight()); //end point of the gradient
            float[] colorCoordinates = {0.25f, 0.5f, 0.70f, 1.0f}; //places the colors at different places
            Color[] colorList = {color1, color2, color3, color1};

            LinearGradientPaint gradientPaint = new LinearGradientPaint(startGradient, endGradient,
                    colorCoordinates, colorList); //paints colors on a line

            graphics.setPaint(gradientPaint); //sets the paint created by "LinearGradientPaint"

            graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel

        } else {

            GradientPaint gradientPaint = new GradientPaint(0, 0,
                    color1, getWidth(), getHeight(), color2);

            graphics.setPaint(gradientPaint);

            graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel

        }

    }

    /**
     * Initializes buttons and gives them coordinates and colors. Each button is then given an
     * actionlistener. For the "startgame" button we use "setFocusable(false)" because without this method, it is not possible to
     * use the keys after pressing a button.
     * @author Saman
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
        music = "src/Ljud/audio1.wav";
        musicOff ="on";
        se.setFile(music);
        se.playMusic();

        settings.setBounds(500, 0, 100, 35);
        settings.setBackground(Color.WHITE);
        settings.setFocusable(false);

    }

    public void setEnabledTrue(){
        startGame.setEnabled(true);
    }

    /**
     * Adds actionlisteners to the different keys.
     * @author Saman
     */
    public void addActionListeners(){
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame settingsFrame = new SettingsFrame(controller, mainFrame, TopPanel.this);
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.linkKeyToEvent(true);
                controller.startTimer(true, 500); //500
                gameStarted = true;
                startGame.setEnabled(false);
            }
        });

        playMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkIfPlay(musicOff);
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

    /**
     * Checks if the music is playing or not.
     * @param musicOff
     * @author Saman, Melvin
     */
    public void checkIfPlay(String musicOff){
        if (musicOff.equals("off")) {
            se.setFile(music);
            se.playMusic();
            this.musicOff = "on";
            playMusic.setText("Music on");
        }

        else if (musicOff.equals("on")) {
            se.stop();
            this.musicOff = ("off");
            playMusic.setText("Music off");
        }
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
            currentAudioVolume += 2.0f; //"f" står för float
        }

        controlVolume.setValue(currentAudioVolume);
    }

    /**
     * Decrements the volume of the music currently playing.
     * "-80.0" is the lowest decible that a song can handle.
     * We need to make sure that the audio does not go under
     * -80.0, as it would cause an error. We then set this
     * audio volume to the float control, which changes the volume.
     * @author Saman
     */
    public void decrementVolume() {
        currentAudioVolume -= 3.0f; //"f" står för float

        if(currentAudioVolume <= -80.f){
            currentAudioVolume = -80.f;
        }

        controlVolume.setValue(currentAudioVolume);
    }

    public class sound {
        File file;
        AudioInputStream audioInputStream;
        public void setFile(String SoundFileName) {
            try {
                file = new File(SoundFileName);
                audioInputStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                controlVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                controlVolume.setValue(currentAudioVolume);

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
    }

}
