package Settings;

import javax.swing.*;
import java.awt.*;

public class CustomizePanel extends JPanel {
    private Control.Controller controller;
    private View.MainFrame mainFrame;
    private View.TopPanel topPanel;
    private View.Settings.AudioPanel audioPanel;
    private KeyboardPanel keyboardPanel;
    private TrailerPanel trailerPanel;
    private Color color1;
    private Color color2;
    private JLabel firstColor;



    public CustomizePanel(Control.Controller controller, View.MainFrame mainFrame,
                          View.TopPanel topPanel, View.Settings.AudioPanel audioPanel,
                          KeyboardPanel keyboardPanel, TrailerPanel trailerPanel){
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;
        this.audioPanel = audioPanel;
        this.keyboardPanel = keyboardPanel;
        this.trailerPanel = trailerPanel;
        this.setBounds(100, 100, 100, 100);
        setup();
        addActionListeners();
    }

    public void setColor(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
    }

    public void setup(){
        firstColor = new JLabel("First color to blend");
    }

    public void addActionListeners(){

    }
}
