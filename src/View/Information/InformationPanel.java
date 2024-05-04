package View.Information;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private Color color;
    private JTextArea textArea;
    private String choice;

    public InformationPanel(String choice){
        this.setBounds(0, 0, 300, 300);
        this.choice = choice;

        color = new Color(89, 110, 136, 221);

        setTextField();

        this.setBackground(color);
        this.add(textArea);
    }

    public void setTextField(){
        createTextArea();

        if(choice.equals("Party info")){
            textArea.setText("The party theme has cheerful colors that give \n" +
                    "the feeling of being at a party! The music that \n" +
                    "accompanies the " + "party theme matches well \nwith the colors.");
        }

        else if(choice.equals("Wild west info")){
            textArea.setText("The wild west theme lets you play tetris like \n" +
                    "a real cowboy! With its old and vibrant colors, \n" +
                    "you are guaranteed to feel like a cowboy in the \n" +
                    "1800's in Texas. The music that accompanies\n" +
                    "this theme is a well known song that is often \n" +
                    "associated with the wild west.\n" +
                    "Buckle up, and play Tetris!" );
        }
    }

    public void createTextArea(){
        textArea = new JTextArea();
        textArea.setBounds(0, 0, 250, 300);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(color);
        textArea.setForeground(Color.WHITE); //ändrar färg på texten till vit
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder());
    }
}
