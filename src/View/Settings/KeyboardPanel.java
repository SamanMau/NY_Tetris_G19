package Settings;

import Control.Controller;
import View.GameFrame.MainFrame;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class KeyboardPanel extends JPanel {
    private Controller controller;
    private MainFrame mainFrame;
    private SettingsFrame settingsFrame;
    private JRadioButton arrow;
    private JRadioButton WASD;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private boolean multiColors;

    public KeyboardPanel(Controller controller, SettingsFrame settingsFrame){
        this.setBounds(100, 100, 150, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        this.controller = controller;

        setUpKeyboard();
    }

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

    public void setUpKeyboard(){
        JLabel choose = new JLabel("Choose which keybinds to use");
        choose.setBounds(160, 10, 200, 40);

        arrow = new JRadioButton("Arrows");
        WASD = new JRadioButton("W,A,S,D");

        arrow.setBounds(200, 50, 80, 35);
        arrow.setBackground(Color.WHITE);
        arrow.setFocusable(false);

        WASD.setBounds(200, 90, 80, 35);
        arrow.setBackground(Color.white);
        WASD.setFocusable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(arrow);
        group.add(WASD);

        WASD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeKeys("A","D","W","S","SPACE");
            }
        });

        arrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeKeys("LEFT", "RIGHT", "UP", "DOWN", "SPACE");
            }
        });

        this.add(arrow);
        this.add(WASD);
        this.add(choose);
    }
}


