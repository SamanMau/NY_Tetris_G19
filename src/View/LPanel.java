/**
 * A class that represents a left panel.
 */
package View;

import javax.swing.*;
import java.awt.*;

public class LPanel extends JPanel {
    JLabel poangText;
    JLabel levelText;
    private Color color1;
    private Color color2;

    public LPanel(){
        this.setPreferredSize(new Dimension(150, 300));
        poangText = new JLabel();
        levelText = new JLabel();
        createPointCounter(0);
        createLevelCounter(1);
        setColor(Color.gray, Color.gray);
        this.setVisible(true);
    }

    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
        repaint();
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
