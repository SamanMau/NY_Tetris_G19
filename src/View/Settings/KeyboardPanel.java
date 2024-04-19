package View.Settings;

import Control.Controller;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardPanel extends JPanel {
    private Controller controller;
    private MainFrame mainFrame;
    private SettingsFrame settingsFrame;
    private JRadioButton arrow;
    private JRadioButton WASD;

    public KeyboardPanel(Controller controller, MainFrame mainFrame, SettingsFrame settingsFrame){
        this.setBounds(100, 100, 150, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        this.controller = controller;
        this.mainFrame = mainFrame;

        setUpKeyboard();
    }

    public void setUpKeyboard(){
        JLabel choose = new JLabel("Choose which keybinds to use");
        choose.setBounds(160, 10, 200, 40);

        arrow = new JRadioButton("Arrows");
        WASD = new JRadioButton("W,A,S,D");

        arrow.setBounds(200, 50, 80, 35);
        arrow.setBackground(Color.LIGHT_GRAY);
        arrow.setFocusable(false);

        WASD.setBounds(200, 90, 80, 35);
        arrow.setBackground(Color.LIGHT_GRAY);
        WASD.setFocusable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(arrow);
        group.add(WASD);

        WASD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.createKeys("A","D","W","S","SPACE");
            }
        });

        arrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.createKeys("LEFT", "RIGHT", "UP", "DOWN", "SPACE");
            }
        });

        this.add(arrow);
        this.add(WASD);
        this.add(choose);
    }
}


