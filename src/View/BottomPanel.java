/**
 * A class that will be used to add an image of our choice to the GUI.
 */
package View;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private Color color2;
    private Color color1;

    public BottomPanel(){
        this.setPreferredSize(new Dimension(600, 103));
        createImage();

        setColor(Color.gray, Color.gray);

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

    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        GradientPaint gradientPaint = new GradientPaint(0, 0,
                color2, getWidth(), getHeight(), color1);

        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
