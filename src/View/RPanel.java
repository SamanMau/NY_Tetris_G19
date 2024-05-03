/**
 * This class represents the right panel of the GUI.
 * @author Saman, Melvin, Abdulkadir
 */

package View;

import javax.swing.*;
import java.awt.*;

public class RPanel extends JPanel {
    private Color color1;
    private Color color2;

    /**
     * Sets the dimension and color of the right panel
     * @author Saman
     */
    public RPanel(){
        this.setPreferredSize(new Dimension(150, 300));
        this.setBackground(Color.gray);
        this.setLayout(null);
        setColor(Color.gray, Color.gray);
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
                color2, getWidth(), getHeight(), color1);

        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }


}
