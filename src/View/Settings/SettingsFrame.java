package View.Settings;

import Control.Controller;
import View.MainFrame;
import View.TopPanel;

import javax.swing.*;

public class SettingsFrame extends JFrame {
    private Controller controller;
    private MainFrame mainFrame;
    private TopPanel topPanel;

    public SettingsFrame(Controller controller, MainFrame mainFrame, TopPanel topPanel){
        super("Settings");
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;
        this.setSize(500, 500);

        createTabs();

        this.setVisible(true);
    }

    public void createTabs(){
        JTabbedPane tab = new JTabbedPane();

        AudioPanel audioPanel = new AudioPanel(controller, mainFrame, this, topPanel);

        KeyboardPanel keyboardPanel = new KeyboardPanel(controller, mainFrame, this);

        tab.addTab("Audio", audioPanel);
        tab.addTab("Controls", keyboardPanel);

        this.add(tab);
    }
}
