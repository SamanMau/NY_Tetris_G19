package View.Settings;

import Control.Controller;
import Settings.KeyboardPanel;
import View.GameFrame.MainFrame;
import View.GameFrame.TopPanel;

import javax.swing.*;

public class SettingsFrame extends JFrame {
    private Controller controller;
    private JTabbedPane tab;

    public SettingsFrame(Controller controller){
        super("Settings");
        this.controller = controller;
        this.setSize(500, 500);
        this.setResizable(false);

        createTabs();
        this.add(tab);


        this.setVisible(true);
    }

    public void createTabs(){
        tab = new JTabbedPane();

        AudioPanel audioPanel = new AudioPanel(controller, this);

        KeyboardPanel keyboardPanel = new KeyboardPanel(controller, this);

        ThemePanel themePanel = new ThemePanel(controller);

        tab.addTab("Audio", audioPanel);
        tab.addTab("Controls", keyboardPanel);
        tab.addTab("Theme", themePanel);
    }
}
