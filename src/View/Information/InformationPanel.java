/**
 * A panel created by the informationframe.
 * It shows different text based on which
 * information button has been clicked
 * @author Saman
 */

package View.Information;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private Color color;
    private JTextArea textArea;
    private String choice;

    public InformationPanel(String choice){
        this.setLayout(null);
        this.setBounds(0, 0, 600, 400);
        this.choice = choice;

        color = new Color(89, 110, 136, 221);

        setTextField();

        this.setBackground(color);
        this.add(textArea);
    }

    /**
     * Shows different text based on what information button was clicked
     * The value of the string variable "choice" is sent by informationframe,
     * which in turn is sent by the themepanel.
     * @author Saman
     */
    public void setTextField(){
        textArea = new JTextArea();

        textArea.setBounds(0, 20, 600, 400);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(color);
        textArea.setForeground(Color.WHITE); //ändrar färg på texten till vit
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder());

        if(choice.equals("Party info")){
            textArea.setText("The party theme has cheerful colors that give" +
                    "the feeling of being at a party! The music that accompanies the \n" +
                    "party theme matches well with the colors. \n \n Preview");

            try{
                ImageIcon image = new ImageIcon("src/Bilder/party.png");
                Image oldSize = image.getImage();
                Image changedSize = oldSize.getScaledInstance(190, 250, Image.SCALE_AREA_AVERAGING);
                ImageIcon newSize = new ImageIcon(changedSize);

                JLabel label = new JLabel(newSize);
                label.setBounds(200, 100, 190, 250);

                this.add(label);

            } catch (NullPointerException e){
                System.out.println("error");
            }
        }

        else if(choice.equals("Wild west info")){
            textArea.setText("The wild west theme lets you play tetris like" +
                    "a real cowboy! With its old and vibrant colors, " +
                    "you are guaranteed \nto feel like a cowboy in the" +
                    "1800's in Texas. The music that accompanies" +
                    "this theme is a well known song \n that is often " +
                    "associated with the wild west. " +
                    "Buckle up, and play Tetris! \n Preview" );

            try{
                ImageIcon image = new ImageIcon("src/Bilder/wildWest.png");
                Image oldSize = image.getImage();
                Image changedSize = oldSize.getScaledInstance(190, 250, Image.SCALE_AREA_AVERAGING);
                ImageIcon newSize = new ImageIcon(changedSize);

                JLabel label = new JLabel(newSize);
                label.setBounds(200, 100, 190, 250);

                this.add(label);

            } catch (NullPointerException e){
                System.out.println("error");
            }
        }

        else if(choice.equals("Rock info")){
            textArea.setText("Have you ever wanted to play tetris while " +
            "feeling like a rockstar? Then this is the " +
                    "right theme for you! \nThe rock theme which is accompanied" +
                    " by this theme makes you a real rockstar. \n " +
                    "Get your guitar, go crazy, and play Tetris! \n Preview");

            try{
                ImageIcon image = new ImageIcon("src/Bilder/rock.png");
                Image oldSize = image.getImage();
                Image changedSize = oldSize.getScaledInstance(190, 250, Image.SCALE_AREA_AVERAGING);
                ImageIcon newSize = new ImageIcon(changedSize);

                JLabel label = new JLabel(newSize);
                label.setBounds(200, 100, 190, 250);

                this.add(label);

            } catch (NullPointerException e){
                System.out.println("error");
            }
        }
    }
}
