/**
 * A class that represents a left panel.
 */
package View.GameFrame;

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

    public LPanel(Control.Controller controller){
        this.setPreferredSize(new Dimension(150, 300));
        poangText = new JLabel();
        levelText = new JLabel();
        this.controller = controller;
        createPointCounter(0);
        createLevelCounter(1);
        setColor(Color.gray, Color.gray);
        this.setVisible(true);
    }

    public int getTotalPoints(){
        return totalScore;
    }

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
        levelText.setText("Level: " + level);
        levelText.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(Box.createVerticalStrut(50));
        this.add(levelText);
    }

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
