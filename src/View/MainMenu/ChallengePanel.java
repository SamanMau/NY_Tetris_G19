package View.MainMenu;

import Control.Controller;
import Control.DatabaseController;
import Control.PictureGenerate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChallengePanel extends JPanel {
    private DatabaseController dbc;
    private Controller controller;
    private Color color1;
    private Color color2;

    private JButton spin;
    private JButton accept;

    private PictureGenerate thread;


    private JLabel generateChallenge;

    public ChallengePanel(DatabaseController dbc, Controller controller){
        this.setLayout(null);
        this.dbc = dbc;
        this.controller = controller;

        this.setBounds(0, 0, 500, 500);
        this.setBackground(Color.GREEN);

        thread = new PictureGenerate(this);
        thread.start();

        color1 = new Color(155, 48, 255);
        color2 = new Color(0, 0, 139);

        setUp();

        repaint();
        createImage();
        createButtons();
        addActionListeners();
    }

    public void setUp(){
        generateChallenge = new JLabel("Generate challenge!");
        generateChallenge.setForeground(Color.WHITE);
        Font font = new Font("Italic", Font.PLAIN, 25);
        generateChallenge.setFont(font);
        generateChallenge.setBounds(150, 5, 260, 30);
        this.add(generateChallenge);
    }

    /**
     * This method creates and resizes the tetris logo.
     * @author Saman
     */
    public void createImage(){

        try{
            ImageIcon image = new ImageIcon("src/Bilder/cards.png");
            Image oldSize = image.getImage();
            Image changedSize = oldSize.getScaledInstance(150, 120, Image.SCALE_SMOOTH);
            ImageIcon newSize = new ImageIcon(changedSize);

            JLabel label = new JLabel(newSize);

            label.setBounds(170, 37, 150, 120);

            SwingUtilities.invokeLater(() -> {
                this.add(label);
            });

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setPic1(String name){
        try{
            ImageIcon icon = new ImageIcon(name);
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);

            label.setBounds(100, 250, 80, 80);

            SwingUtilities.invokeLater(() -> {
                this.add(label);
                this.repaint();
            });


        } catch (NullPointerException e){
            e.printStackTrace();
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

        GradientPaint gradientPaint = new GradientPaint(0, 0,
                color1, getWidth(), getHeight(), color2);

        graphics.setPaint(gradientPaint);

        graphics.fillRect(0, 0, getWidth(), getHeight()); //the colors will cover the whole panel

        Graphics2D rectangle = (Graphics2D) g;

        Color color = new Color(36, 202, 218);

        rectangle.setColor(color);

        rectangle.fillRoundRect(50, 150, 400, 300, 40, 40);

        rectangle.drawRoundRect(50, 150, 400, 300, 40, 40);

        Graphics2D picture1 = (Graphics2D) g;
        Graphics2D picture2 = (Graphics2D) g;
        Graphics2D picture3 = (Graphics2D) g;

        Color box = new Color(222, 136, 40);

        picture1.setColor(box);
        picture2.setColor(box);
        picture3.setColor(box);

        picture1.fillRoundRect(100, 250, 80, 80, 20, 20);

        picture1.drawRoundRect(100, 250, 80, 80, 20, 20);

        picture2.fillRoundRect(210, 250, 80, 80, 20, 20);

        picture2.drawRoundRect(210, 250, 80, 80, 20, 20);

        picture3.fillRoundRect(320, 250, 80, 80, 20, 20);

        picture3.drawRoundRect(320, 250, 80, 80, 20, 20);

        Graphics2D showChallenge = (Graphics2D) g;
        showChallenge.setColor(Color.black);
        showChallenge.fillRoundRect(80, 200, 350, 30, 5, 5);
        showChallenge.setColor(Color.WHITE);
        showChallenge.drawRoundRect(80, 200, 350, 30, 5, 5);
    }

    public void createButtons(){
        spin = new JButton("Spin");
        spin.setBackground(Color.WHITE);
        spin.setBounds(120, 350, 100, 25);

        accept = new JButton("Accept challenge");
        accept.setBackground(Color.WHITE);
        accept.setBounds(260, 350, 140, 25);
        accept.setEnabled(false);

        spin.setFocusPainted(false);
        accept.setFocusPainted(false);

        this.add(accept);
        this.add(spin);
    }

    public void addActionListeners(){
        spin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                thread.setIsRunning();

                accept.setEnabled(true);
            }
        });
    }
}
