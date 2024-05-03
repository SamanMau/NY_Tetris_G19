/**
 * This frame represents settings. It manages different
 * panels, such as audioPanel and keyboardpanel.
 * @author Saman, Melvin
 */
package View.Settings;

import Control.Controller;
import Settings.KeyboardPanel;
import Settings.ThemePanel;
import View.MainFrame;
import View.TopPanel;

import javax.swing.*;

public class SettingsFrame extends JFrame {
    private Controller controller;
    private MainFrame mainFrame;
    private TopPanel topPanel;
    private JTabbedPane tab;

    public SettingsFrame(Controller controller, MainFrame mainFrame, TopPanel topPanel){
        super("Settings");
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;
        this.setSize(500, 500);
        this.setResizable(false);

        createTabs();
        this.add(tab);


        this.setVisible(true);
    }

    /**
     * This method displays each panel as a specific tab
     * @author Saman, Melvin
     */
    public void createTabs(){
        tab = new JTabbedPane();

        View.Settings.AudioPanel audioPanel = new View.Settings.AudioPanel(controller, mainFrame, this, topPanel);

        KeyboardPanel keyboardPanel = new KeyboardPanel(controller, mainFrame, this);

        ThemePanel themePanel = new ThemePanel(controller, mainFrame, topPanel);

        tab.addTab("Audio", audioPanel);
        tab.addTab("Controls", keyboardPanel);
        tab.addTab("Theme", themePanel);
    }
}
