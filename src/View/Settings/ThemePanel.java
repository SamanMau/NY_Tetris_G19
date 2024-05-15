/**
 * This class represents a panel which manages different themes.
 * @author Saman
 */

package View.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import View.GameFrame.*;
import View.Settings.CustomizePanel;

public class ThemePanel extends JPanel {
    private Control.Controller controller;
    private BottomPanel bottomPanel;
    private JLabel chooseText;
    private JButton party;
    private JButton partyInfo;
    private JButton happy;
    private JButton wildWestInfo;
    private JTextArea partyText;
    private JButton wildWest;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private boolean multiColors;
    private View.Settings.AudioPanel audioPanel;
    private Settings.KeyboardPanel keyboardPanel;
    private JButton defaultTheme;
    private Color defaultColor1;
    private Color defaultColor2;
    private Settings.TrailerPanel trailerPanel;
    private JButton rock;
    private JButton rockInfo;
    private JLabel customize;
    private CustomizePanel customizePanel;

    public ThemePanel(Control.Controller controller, View.Settings.AudioPanel audioPanel,
                      Settings.KeyboardPanel keyboardPanel, Settings.TrailerPanel trailerPanel,
                      CustomizePanel customizePanel){
        this.controller = controller;
        this.trailerPanel = trailerPanel;
        this.customizePanel = customizePanel;

        defaultColor1 = new Color(106, 130, 251);
        defaultColor2 = new Color(218, 119, 242);

        this.setBounds(100, 100, 100, 100);
        this.setLayout(null);
        this.keyboardPanel = keyboardPanel;
        this.audioPanel = audioPanel;

        setup();
        addListeners();
        this.add(chooseText);
        this.add(party);
        this.add(partyInfo);

        this.add(wildWest);
        this.add(wildWestInfo);
        this.add(rock);
        this.add(rockInfo);
        this.add(customize);
        this.add(happy);

        this.add(defaultTheme);
    }

    /**
     * This method creates the different jLabels and buttons
     * that will be displayed in the panel. The labels and
     * buttons also get a specific color and font
     * @author Saman
     */
    public void setup(){
        chooseText = new JLabel("Choose theme in GUI");
        chooseText.setBounds(175, 10, 150, 25);

        customize = new JLabel("Want to customize your own GUI? Press the Customize tab!");
        customize.setBounds(10, 400, 450, 25);

        party = new JButton("Party theme");
        party.setBounds(180, 50, 150, 35);
        party.setBackground(Color.WHITE);
        party.setFocusable(false);

        partyInfo = new JButton("Info");
        partyInfo.setBounds(330, 70, 80, 15);
        partyInfo.setFocusable(false);
        partyInfo.setBackground(Color.white);

        wildWest = new JButton("Wild west theme");
        wildWest.setBounds(180, 100, 150, 35);
        wildWest.setBackground(Color.WHITE);
        wildWest.setFocusable(false);

        wildWestInfo = new JButton("Info");
        wildWestInfo.setBounds(330, 120, 80, 15);
        wildWestInfo.setBackground(Color.white);

        defaultTheme = new JButton("Default");
        defaultTheme.setBounds(180, 150, 150, 35);
        defaultTheme.setBackground(Color.white);
        defaultTheme.setFocusable(false);

        rock = new JButton("Rock theme");
        rock.setBounds(180, 200, 150, 35);
        rock.setBackground(Color.white);
        rock.setFocusable(false);

        rockInfo = new JButton("Info");
        rockInfo.setBounds(330, 220, 80, 15);
        rockInfo.setBackground(Color.white);

        happy = new JButton("Happy theme");
        happy.setBounds(180, 250, 150, 35);
        happy.setBackground(Color.WHITE);
        happy.setFocusable(false);

        Font font = new Font("Times new Roman", Font.BOLD, 16);
        chooseText.setFont(font);
        party.setFont(font);
        wildWest.setFont(font);
        rock.setFont(font);
        customize.setFont(font);
        happy.setFont(font);
    }

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
        Graphics2D graphics = (Graphics2D) g;

        if(multiColors){
            Point2D startGradient = new Point2D.Float(0, 0); //starting point of the gradient
            Point2D endGradient = new Point2D.Float(getWidth(), getHeight()); //end point of the gradient
            float[] colorCoordinates = {0.25f, 0.5f, 0.70f, 1.0f}; //places the colors at different places
            Color[] colorList = {color1, color2, color3, color4};

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
     * Adds actionlisteners to the different buttons.
     * @author Saman
     */
    public void addListeners(){

        happy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1 = new Color(255, 255, 0);
                Color color2 = new Color(255, 165, 0);
                Color color3 = new Color(255, 0, 255);
                Color color4 = new Color(0, 191, 255);


                setMultiColors(color1, color2, color3, color4);


                audioPanel.setMultiColors(color1, color2, color3, color4);
                customizePanel.setMultiColors(color1, color2, color3, color4);
                keyboardPanel.setMultiColors(color1, color2, color3, color4);
                trailerPanel.setMultiColors(color1, color2, color3, color4);
                controller.setMultiColors(color1, color2, color3, color4);

                controller.setNewMusic("src/Ljud/happy.wav");
            }
        });

        rock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1 = new Color(128, 0, 32);  // Burgundy
                Color color2 = new Color(54, 69, 79);  // Charcoal
                controller.setColor(color1, color2);
                setColor(color1, color2);
                keyboardPanel.setColor(color1, color2);
                audioPanel.setColor(color1, color2);
                trailerPanel.setColor(color1, color2);
                customizePanel.setColor(color1, color2);


                controller.setNewMusic("src/Ljud/rock.wav");
            }
        });

        defaultTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setColor(defaultColor1, defaultColor2);
                setColor(defaultColor1, defaultColor2);
                keyboardPanel.setColor(defaultColor1, defaultColor2);
                audioPanel.setColor(defaultColor1, defaultColor2);
                trailerPanel.setColor(defaultColor1, defaultColor2);
                customizePanel.setColor(defaultColor1, defaultColor2);
                controller.setNewMusic("src/Ljud/audio1.wav");
            }
        });

        partyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.Information.InformationFrame informationFrame = new View.Information.InformationFrame("Party info");
            }
        });

        rockInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.Information.InformationFrame informationFrame = new View.Information.InformationFrame("Rock info");
            }
        });

        wildWestInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.Information.InformationFrame informationFrame = new View.Information.InformationFrame("Wild west info");
            }
        });

        party.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setColor(Color.MAGENTA, Color.YELLOW);
                setColor(Color.MAGENTA, Color.YELLOW);
                keyboardPanel.setColor(Color.MAGENTA, Color.YELLOW);
                audioPanel.setColor(Color.MAGENTA, Color.YELLOW);
                trailerPanel.setColor(Color.MAGENTA, Color.YELLOW);
                customizePanel.setColor(Color.MAGENTA, Color.YELLOW);
                controller.setNewMusic("src/Ljud/party.wav");
            }
        });

        wildWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Color color1 = new Color(255, 209, 87);
                Color color2 = new Color(194, 53, 45);

                controller.setColor(color2 ,color1);
                setColor(color2, color1);
                keyboardPanel.setColor(color2, color1);
                audioPanel.setColor(color2, color1);
                customizePanel.setColor(color2, color1);
                controller.setNewMusic("src/Ljud/wildWest.wav");
            }
        });
    }

}
