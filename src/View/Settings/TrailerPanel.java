package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class TrailerPanel extends JPanel {
    private Control.Controller controller;
    private View.Settings.SettingsFrame settingsFrame;
    private Color color1;
    private Color color2;
    private JLabel trailerText;
    private JButton playVideo;
    private JLabel davinciResolve;

    public TrailerPanel(Control.Controller controller,
                        View.Settings.SettingsFrame settingsFrame){
        this.controller = controller;
        this.settingsFrame = settingsFrame;

        this.setBounds(100, 100, 100, 100);
        this.setLayout(null);

        setUp();
        setFont();
        addActionListener();
        this.add(trailerText);
        this.add(playVideo);
        this.add(davinciResolve);
    }

    public void setUp(){
        trailerText = new JLabel("Check out our Tetris trailer!");
        trailerText.setBounds(160, 90, 200, 35);
        davinciResolve = new JLabel("Editing program used: Davinci resolve 18");
        davinciResolve.setBounds(5, 400, 400, 35);

        playVideo = new JButton("Play");
        playVideo.setBounds(195, 150, 100, 35);
        playVideo.setBackground(Color.WHITE);
    }

    public void setFont(){
        Font font = new Font("Times new Roman", Font.BOLD, 16);
        trailerText.setFont(font);
        playVideo.setFont(font);
        davinciResolve.setFont(font);
    }

    public void addActionListener(){
        playVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.playVideo();

            }
        });
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
}
