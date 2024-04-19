/**
 * A class that represents a left panel.
 */
package View;

import javax.swing.*;
import java.awt.*;

public class LPanel extends JPanel {
    JLabel poangText = new JLabel();
    JLabel levelText = new JLabel();
    public LPanel(){
        this.setPreferredSize(new Dimension(150, 300));
        this.setBackground(Color.gray);
        createPointCounter(0);
        createLevelCounter(1);
        this.setVisible(true);
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
}
