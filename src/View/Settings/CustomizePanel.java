package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomizePanel extends JPanel {
    private Control.Controller controller;
    private View.MainFrame mainFrame;
    private View.TopPanel topPanel;
    private View.Settings.AudioPanel audioPanel;
    private KeyboardPanel keyboardPanel;
    private TrailerPanel trailerPanel;
    private View.Settings.SettingsFrame settingsFrame;
    private Color color1Default;
    private Color color2Default;

    private Color firstColor;
    private Color secondColor;

    private boolean firstColorChosen;
    private boolean secondColorChosen;

    private JLabel text;
    private JButton redAndBlue;
    private JButton greenAndYellow;
    private JButton orangeAndPurple;
    private JButton pinkAndGray;
    private JButton blackAndWhite;
    private JButton redAndGreen;
    private JButton blueAndOrange;
    private JButton yellowAndPurple;
    private JButton greenAndPink;
    private JButton orangeAndBlack;
    private JButton purpleAndWhite;
    private JButton pinkAndBlue; // 270
    private JButton grayAndRed;
    private JButton blackAndYellow;
    private JButton whiteAndGreen;
    private JButton redAndPurple;
    private JButton blueAndYellow;
    private JButton greenAndOrange;
    private JButton pinkAndBlack;
    private JButton grayAndWhite;
    private JButton blueAndGreen;

    public CustomizePanel(Control.Controller controller, View.MainFrame mainFrame,
                          View.TopPanel topPanel, View.Settings.AudioPanel audioPanel,
                          KeyboardPanel keyboardPanel, TrailerPanel trailerPanel,
                          View.Settings.SettingsFrame settingsFrame){
        this.setLayout(null);
        this.controller = controller;
        this.settingsFrame = settingsFrame;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;
        this.audioPanel = audioPanel;
        this.keyboardPanel = keyboardPanel;
        this.trailerPanel = trailerPanel;
        this.setBounds(100, 100, 100, 100);
        setup();
        addActionListeners();

        this.add(text);
    }


    /**
     * Sets the two colors of the GUI. If the colors are different
     * then the colors will fade together, creating a gradient color.
     * @param color1 first color that will be faded
     * @param color2 second color that will be faded
     * @author Abdulkadir
     */
    public void setColor(Color color1, Color color2){
        this.color1Default = color1;
        this.color2Default = color2;
        repaint();
    }

    public void setup(){
        text = new JLabel("Choose your colors!");
        text.setBounds(185, 15, 200, 25);

        redAndBlue = new JButton("Red & Blue");
        redAndBlue.setBounds(60, 50, 150, 25);
        redAndBlue.setBackground(Color.WHITE);
        this.add(redAndBlue);

        greenAndYellow = new JButton("Green & Yellow");
        greenAndYellow.setBounds(60, 85, 150, 25);
        greenAndYellow.setBackground(Color.WHITE);
        this.add(greenAndYellow);

        orangeAndPurple = new JButton("Orange & Purple");
        orangeAndPurple.setBounds(60, 120, 150, 25);
        orangeAndPurple.setBackground(Color.WHITE);
        this.add(orangeAndPurple);

        pinkAndGray = new JButton("Pink & Gray");
        pinkAndGray.setBounds(60, 155, 150, 25);
        pinkAndGray.setBackground(Color.WHITE);
        this.add(pinkAndGray);

        blackAndWhite = new JButton("Black & White");
        blackAndWhite.setBounds(60, 190, 150, 25);
        blackAndWhite.setBackground(Color.WHITE);
        this.add(blackAndWhite);

        redAndGreen = new JButton("Red & Green");
        redAndGreen.setBounds(60, 225, 150, 25);
        redAndGreen.setBackground(Color.WHITE);
        this.add(redAndGreen);

        blueAndOrange = new JButton("Blue & Orange");
        blueAndOrange.setBounds(60, 260, 150, 25);
        blueAndOrange.setBackground(Color.WHITE);
        this.add(blueAndOrange);

        yellowAndPurple = new JButton("Yellow & Purple");
        yellowAndPurple.setBounds(60, 295, 150, 25);
        yellowAndPurple.setBackground(Color.WHITE);
        this.add(yellowAndPurple);

        greenAndPink = new JButton("Green & Pink");
        greenAndPink.setBounds(60, 330, 150, 25);
        greenAndPink.setBackground(Color.WHITE);
        this.add(greenAndPink);

        orangeAndBlack = new JButton("Orange & Black");
        orangeAndBlack.setBounds(60, 365, 150, 25);
        orangeAndBlack.setBackground(Color.WHITE);
        this.add(orangeAndBlack);

        purpleAndWhite = new JButton("Purple & White");
        purpleAndWhite.setBounds(270, 365, 150, 25);
        purpleAndWhite.setBackground(Color.WHITE);
        this.add(purpleAndWhite);

        blueAndGreen = new JButton("Blue & Green");
        blueAndGreen.setBounds(270, 330, 150, 25);
        blueAndGreen.setBackground(Color.WHITE);
        this.add(blueAndGreen);

        pinkAndBlue = new JButton("Pink & Blue");
        pinkAndBlue.setBounds(270, 50, 150, 25);
        pinkAndBlue.setBackground(Color.WHITE);
        this.add(pinkAndBlue);

        grayAndRed = new JButton("Gray & Red");
        grayAndRed.setBounds(270, 85, 150, 25);
        grayAndRed.setBackground(Color.WHITE);
        this.add(grayAndRed);

        blackAndYellow = new JButton("Black & Yellow");
        blackAndYellow.setBounds(270, 120, 150, 25);
        blackAndYellow.setBackground(Color.WHITE);
        this.add(blackAndYellow);

        whiteAndGreen = new JButton("White & Green");
        whiteAndGreen.setBounds(270, 155, 150, 25);
        whiteAndGreen.setBackground(Color.WHITE);
        this.add(whiteAndGreen);

        redAndPurple = new JButton("Red & Purple");
        redAndPurple.setBounds(270, 190, 150, 25);
        redAndPurple.setBackground(Color.WHITE);
        this.add(redAndPurple);

        blueAndYellow = new JButton("Blue & Yellow");
        blueAndYellow.setBounds(270, 225, 150, 25);
        blueAndYellow.setBackground(Color.WHITE);
        this.add(blueAndYellow);

        greenAndOrange = new JButton("Green & Orange");
        greenAndOrange.setBounds(270, 260, 150, 25);
        greenAndOrange.setBackground(Color.WHITE);
        this.add(greenAndOrange);

        pinkAndBlack = new JButton("Pink & Black");
        pinkAndBlack.setBounds(270, 295, 150, 25);
        pinkAndBlack.setBackground(Color.WHITE);
        this.add(pinkAndBlack);

        grayAndWhite = new JButton("Gray & White");
        grayAndWhite.setBounds(60, 330, 150, 25);
        grayAndWhite.setBackground(Color.WHITE);
        this.add(grayAndWhite);

        Font font = new Font("Times new Roman", Font.BOLD, 16);
        text.setFont(font);
    }

    public void addActionListeners(){
        redAndBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.RED, Color.BLUE);
            }
        });

        greenAndYellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.GREEN, Color.YELLOW);
            }
        });

        orangeAndPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color purple = new Color(141, 74, 224);
                settingsFrame.setColors(Color.ORANGE, purple);
            }
        });

        pinkAndGray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.PINK, Color.GRAY);
            }
        });

        blackAndWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.WHITE, Color.BLACK);
            }
        });

        redAndGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.red, Color.GREEN);
            }
        });

        blueAndOrange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.BLUE, Color.ORANGE);
            }
        });

        yellowAndPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color purple = new Color(141, 74, 224);
                settingsFrame.setColors(Color.YELLOW, purple);
            }
        });

        greenAndPink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.GREEN, Color.PINK);
            }
        });

        orangeAndBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.ORANGE, Color.BLACK);
            }
        });

        purpleAndWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color purple = new Color(141, 74, 224);

                settingsFrame.setColors(purple, Color.WHITE);
            }
        });

        pinkAndBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.PINK, Color.BLUE);
            }
        });

        grayAndRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.GRAY, Color.RED);
            }
        });

        blackAndYellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.YELLOW, Color.BLACK);
            }
        });

        whiteAndGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.WHITE, Color.GREEN);
            }
        });

        redAndPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color purple = new Color(141, 74, 224);

                settingsFrame.setColors(Color.RED, purple);
            }
        });

        blueAndYellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.BLUE, Color.YELLOW);
            }
        });

        greenAndOrange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color orange = new Color(206, 97, 31);
                settingsFrame.setColors(orange, Color.GREEN);
            }
        });

        pinkAndBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.PINK, Color.black);
            }
        });

        grayAndWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.GRAY, Color.WHITE);
            }
        });

        blueAndGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setColors(Color.BLUE, Color.GREEN);
            }
        });

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

        GradientPaint gradientPaint = new GradientPaint(0, 0,
                color2Default, getWidth(), getHeight(), color1Default);

        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
