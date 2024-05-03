package Settings;

import Control.Controller;
import View.MainFrame;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardPanel extends JPanel {
    private Controller controller;
    private MainFrame mainFrame;
    private SettingsFrame settingsFrame;
    private JRadioButton arrow;
    private JRadioButton WASD;
    private Color color1;
    private Color color2;

    public KeyboardPanel(Controller controller, MainFrame mainFrame, SettingsFrame settingsFrame){
        this.setBounds(100, 100, 150, 100);
        this.setLayout(null);

        this.controller = controller;
        this.mainFrame = mainFrame;

        setUpKeyboard();
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

        GradientPaint gradientPaint = new GradientPaint(0, 0,
                color1, getWidth(), getHeight(), color2);

        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel
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
                mainFrame.createKeys("A","D","W","S","SPACE");
            }
        });

        arrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.createKeys("LEFT", "RIGHT", "UP", "DOWN", "SPACE");
            }
        });

        this.add(arrow);
        this.add(WASD);
        this.add(choose);
    }
}


