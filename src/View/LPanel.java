/**
 * This class represents the left panel of the GUI.
 * This class handles the display of the score and level,
 * and the gradient of colors.
 * @author Saman, Abdulkadir, Huy
 */
package View;

import javax.swing.*;
import java.awt.*;

public class LPanel extends JPanel {
    JLabel poangText;
    JLabel levelText;
    private Color color1;
    private Color color2;
    private int totalScore = 0;
    private int nextScore = 3000;
    private int level = 1;
    private Control.Controller controller;
    private int newLevel;
    private JLabel label;
    private JLabel nextBlockText;

    /**
     * Constructor, initializes objects.
     * @author Saman
     */
    public LPanel(Control.Controller controller){
        this.setPreferredSize(new Dimension(150, 300));
        poangText = new JLabel();
        levelText = new JLabel();
        createPointCounter(0);
        createLevelCounter(1);
        setColor(Color.gray, Color.gray);
        this.controller = controller;
        this.setVisible(true);
    }

    /**
     * Sets the two colors of the GUI. If the colors are different
     * then the colors will fade together, creating a gradient color.
     * @param color1 first color that will be faded
     * @param color2 second color that will be faded
     * @author Abdulkadir
     */
    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    /**
     * This method is used to decrease the speed of the game by one unit and starts the
     * timer with the newSpeed. It gets the current speeed of the game from controller.
     * Then calculates the newSpeed by subtracting one unit from the current speed.
     * Starts the game timer with the updated speed.
     * @author Abdulkadir, Saman
     */
    public void changeSpeed() {
        int seconds = controller.getCurrentSpeed();
        if(seconds > 50){
            int newSpeed = seconds - 5;
            //controller.startTimer(true, newSpeed);
            controller.setCurrentSpeed(newSpeed);
        }

    }

    /**
     * Increases the game level by one and updates the text field to display the users current level.
     * This method is called whenever the user reaches a new level,
     * and it updates the text field with the new level value.
     * @author Abdulkadir, Saman
     */
    public void increaseLevel() {
        this.newLevel = newLevel + 1;
        levelText.setText("Level: " + newLevel);
        changeSpeed();
    }

    /**
     * Updates the user's total score and checks if the user has reached the next level.
     * This method is called whenever the user scores point, and it updates the total score,
     * displayed in the text field.
     * @param score the points earned by the user to be added to the total score.
     * @author Abdulkadir, Saman
     */
    public void updateScore(int score) {
        this.totalScore += score;
        poangText.setText("Points: " + totalScore);

        if(totalScore >= nextScore){
            increaseLevel();
            this.nextScore += 3000;
        }
    }

    public void showUpComingBlock(String file){
        try{
            ImageIcon image = new ImageIcon(file);
            Image oldSize = image.getImage();
            Image changedSize = oldSize.getScaledInstance(90, 80, Image.SCALE_DEFAULT);
            ImageIcon newSize = new ImageIcon(changedSize);

            if(label == null){
                label = new JLabel(newSize);
                this.add(label);
            } else {
                label.setIcon(newSize);
            }


        } catch (NullPointerException e){
            System.out.println("error");
        }
    }



    /**
     * Method used to make a text field that represents the users points,
     * this will increase everytime the user gets more points.
     * @param point when a users points changes, we use this parameter to add it to the text field.
     */
    public void createPointCounter(int point){
        poangText.setText("Points: " + point);
        poangText.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(poangText);
    }

    /**
     * Method used to make a text field that represents the users level,
     * this will increase everytime the user reaches a new level.
     * @param level when a users level changes, we use this parameter to add it to the text field.
     */
    public void createLevelCounter(int level){
        levelText.setText("Level: " + 0);
        levelText.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(Box.createVerticalStrut(50));
        this.add(levelText);

        nextBlockText = new JLabel("Next block");
        nextBlockText.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(Box.createVerticalStrut(50));
        this.add(nextBlockText);
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
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }


}
