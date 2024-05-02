/**
 * A class that represents a left panel.
 */
package View;

import Control.Controller;

import javax.swing.*;
import java.awt.*;

public class LPanel extends JPanel {
    JLabel poangText;
    JLabel levelText;
    private Color color1;
    private Color color2;
    private int totalScore = 0;
    private int nextScore = 5000;
    private int level = 1;
    private Controller controller;
    private int newLevel;

    public LPanel(Controller controller){
        this.setPreferredSize(new Dimension(150, 300));
        poangText = new JLabel();
        levelText = new JLabel();
        createPointCounter(0);
        createLevelCounter(0);
        setColor(Color.gray, Color.gray);
        this.controller = controller;
        this.setVisible(true);
    }

    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    public void changeSpeed() {
        int seconds = controller.getCurrentSpeed();
        int newSpeed = seconds - 1;
        controller.startTimer(true, newSpeed);
    }

    public void increaseLevel() {
        this.newLevel = newLevel + 1;
        levelText.setText("Level: " + newLevel);
        changeSpeed();
    }

    public void updateScore(int score) {
        this.totalScore += score;
        poangText.setText("Points: " + totalScore);

        if(totalScore >= nextScore){
            increaseLevel();
            this.nextScore += 5000;
        }
    }

    /**
     * Method used to make a text field that represents the users points,
     * this will increase everytime the user gets more points.
     * @param point when a users points changes, we use this parameter to add it to the text field.
     */
    public void createPointCounter(int point){
        poangText.setText("Points: " + 0);
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
