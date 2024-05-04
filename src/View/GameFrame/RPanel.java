package View.GameFrame;

import javax.swing.*;
import java.awt.*;

public class RPanel extends JPanel {
    private Color color1;
    private Color color2;

    public RPanel(){
        this.setPreferredSize(new Dimension(150, 300));
        this.setBackground(Color.gray);
        this.setLayout(null);
        setColor(Color.gray, Color.gray);
        this.setVisible(true);
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
