package View.Settings;

import Control.Controller;
import View.GameFrame.MainFrame;
import View.GameFrame.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class AudioPanel extends JPanel {
    private Controller controller;
    private MainFrame mainFrame;
    private TopPanel topPanel;
    private ButtonGroup group;
    private boolean multiColors;
    private JRadioButton theme1;
    private JRadioButton theme2;
    private JRadioButton theme3;
    private JRadioButton defaultSong;
    private JLabel chooseOwnText;
    private JButton chooseOwnSongBtn;
    private JLabel information;
    private JLabel chooseSong;
    private Font font;
    private JLabel changeAudioText;
    private JButton higher;
    private JButton lower;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;


    public AudioPanel(Controller controller, SettingsFrame settingsFrame){
        this.setBounds(100, 100, 100, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        this.controller = controller;

        setUpButtons();
        setFont();
        addActionListeners();
        setUpPicture();

        group = new ButtonGroup();
        group.add(theme1);
        group.add(theme2);
        group.add(theme3);
        group.add(defaultSong);

        this.add(theme1);
        this.add(theme2);
        this.add(theme3);
        this.add(defaultSong);
        this.add(chooseSong);
        this.add(chooseOwnText);
        this.add(chooseOwnSongBtn);
        this.add(information);
        this.add(changeAudioText);
        this.add(higher);
        this.add(lower);
    }

    public void setUpButtons(){
        font = new Font("Times new Roman", Font.BOLD, 16);

        chooseSong = new JLabel("Choose song!");
        chooseSong.setBounds(200, 10, 140, 40);

        theme1 = new JRadioButton("Audio 1");
        theme2 = new JRadioButton("Audio 2");
        theme3 = new JRadioButton("Audio 3");
        defaultSong = new JRadioButton("Default");

        theme1.setBounds(200, 50, 80, 35);
        theme1.setBackground(Color.LIGHT_GRAY);

        theme2.setBounds(200, 90, 80, 35);
        theme2.setBackground(Color.LIGHT_GRAY);

        theme3.setBounds(200, 130, 80, 35);
        theme3.setBackground(Color.LIGHT_GRAY);

        defaultSong.setBounds(200, 170, 80, 35);
        defaultSong.setBackground(Color.LIGHT_GRAY);

        chooseOwnText = new JLabel("Or choose your own");
        chooseOwnText.setBounds(185, 210, 150, 40);

        chooseOwnSongBtn = new JButton("Choose own song");
        chooseOwnSongBtn.setBounds(170, 250, 160, 40);
        chooseOwnSongBtn.setBackground(Color.lightGray);

        information = new JLabel("The chosen file needs to be a .wav file");
        information.setBounds(120, 290, 300, 40);

        higher = new JButton("+");
        higher.setBounds(260, 380, 50, 30);
        higher.setBackground(Color.lightGray);

        lower = new JButton("-");
        lower.setBounds(160, 380, 50, 30);
        lower.setBackground(Color.lightGray);

        changeAudioText = new JLabel("Change volume of audio");
        changeAudioText.setBounds(160, 340, 280, 40);
    }

    public void setFont(){
        chooseSong.setFont(font);
        defaultSong.setFont(font);
        theme1.setFont(font);
        theme2.setFont(font);
        theme3.setFont(font);
        chooseOwnText.setFont(font);
        chooseOwnSongBtn.setFont(font);
        information.setFont(font);
        changeAudioText.setFont(font);
    }

    public void setUpPicture(){
        ImageIcon image = new ImageIcon("src/Bilder/musicVolume.png");
        Image changedSize = image.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
        ImageIcon newSize = new ImageIcon(changedSize);

        JLabel label = new JLabel(newSize);

        label.setBounds(210, 370, 50, 50);
        this.add(label);
    }

    /**
     * Sets the two colors of the GUI. If the colors are different
     * then the colors will fade together, creating a gradient color.
     * @param color1 first color that will be faded
     * @param color2 second color that will be faded
     * @author Saman
     */
    /**
     * Sets the two colors of the GUI. If the colors are different
     * then the colors will fade together, creating a gradient color.
     * @param color1 first color that will be faded
     * @param color2 second color that will be faded
     * @author Saman
     */
    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
        multiColors = false;
        repaint();
    }

    public void setMultiColors(Color color1, Color color2, Color color3,
                               Color color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        multiColors = true;
        repaint();
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

        if(multiColors){
            Point2D startGradient = new Point2D.Float(0, 0); //starting point of the gradient
            Point2D endGradient = new Point2D.Float(getWidth(), getHeight()); //end point of the gradient
            float[] colorCoordinates = {0.25f, 0.5f, 0.70f, 1.0f}; //places the colors at different places
            Color[] colorList = {color1, color2, color3, color4};

            LinearGradientPaint gradientPaint = new LinearGradientPaint(startGradient, endGradient,
                    colorCoordinates, colorList); //paints colors on a line

            Graphics2D graphics = (Graphics2D) g;
            graphics.setPaint(gradientPaint); //sets the paint created by "LinearGradientPaint"

            graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel

        } else {

            Graphics2D graphics = (Graphics2D) g;

            GradientPaint gradientPaint = new GradientPaint(0, 0,
                    color1, getWidth(), getHeight(), color2);

            graphics.setPaint(gradientPaint);

            graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel

        }

    }

    /**
     * Adds action listeners to different buttons
     * @author Saman
     */
    public void addActionListeners(){

        higher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.incrementVolume();
            }
        });

        lower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.decrementVolume();
            }
        });

        theme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/dark.wav");
            }
        });

        theme2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/audio2.wav");
            }
        });

        theme3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/audio3.wav");
            }
        });

        chooseOwnSongBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.chooseOwnSong();
            }
        });

        defaultSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/audio1.wav");
            }
        });

    }

}
