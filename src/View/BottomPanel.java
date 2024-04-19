/**
 * A class that will be used to add an image of our choice to the GUI.
 */
package View;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    public BottomPanel(){
        this.setPreferredSize(new Dimension(600, 103));
        this.setBackground(Color.gray);
        createImage();

        this.setVisible(true);

    }

    public void createImage(){

        try{
            ImageIcon image = new ImageIcon("src/Bilder/Tetris_logo.png");
            Image oldSize = image.getImage();
            Image changedSize = oldSize.getScaledInstance(90, 80, Image.SCALE_AREA_AVERAGING);
            ImageIcon newSize = new ImageIcon(changedSize);

            JLabel label = new JLabel(newSize);

            this.add(label);

        } catch (NullPointerException e){
            System.out.println("error");
        }
    }
}
