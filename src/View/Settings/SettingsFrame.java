/**
 * This frame represents settings. It manages different
 * panels, such as audioPanel and keyboardpanel.
 * @author Saman, Melvin
 */
package View.Settings;

import Control.Controller;
import Settings.KeyboardPanel;
import Settings.TrailerPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private Controller controller;
    //private MainFrame mainFrame;
    //private TopPanel topPanel;
    private JTabbedPane tab;
    private Color color1;
    private Color color2;
    private AudioPanel audioPanel;
    private KeyboardPanel keyboardPanel;
    private ThemePanel themePanel;
    private TrailerPanel trailerPanel;
    private CustomizePanel customizePanel;

    public SettingsFrame(Controller controller){
        super("Settings");
        this.controller = controller;
        //this.mainFrame = mainFrame;
        //this.topPanel = topPanel;
        this.setSize(500, 500);
        this.setResizable(false);

        color1 = new Color(106, 130, 251);
        color2 = new Color(218, 119, 242);

        createTabs();
        setTabColors();
        this.add(tab);

        this.setVisible(true);
    }

    /**
     * This method displays each panel as a specific tab
     * @author Saman, Melvin
     */
    public void createTabs(){
        tab = new JTabbedPane();

        audioPanel = new View.Settings.AudioPanel(controller, this);

        keyboardPanel = new KeyboardPanel(controller, this);

        trailerPanel = new TrailerPanel(controller, this);

        customizePanel = new CustomizePanel(controller, audioPanel, keyboardPanel,
                trailerPanel, this);

        themePanel = new ThemePanel(controller, audioPanel, keyboardPanel,
                trailerPanel, customizePanel);

        tab.addTab("Audio", audioPanel);
        tab.addTab("Controls", keyboardPanel);
        tab.addTab("Customize", customizePanel);
        tab.addTab("Theme", themePanel);
        tab.addTab("Trailer", trailerPanel);
        tab.setBackground(Color.WHITE);
    }

    public void setTabColors(){
        audioPanel.setColor(color1, color2);
        themePanel.setColor(color1, color2);
        keyboardPanel.setColor(color1, color2);
        trailerPanel.setColor(color1, color2);
        customizePanel.setColor(color1, color2);
    }

    public void setColors(Color color1, Color color2){
        audioPanel.setColor(color1, color2);
        themePanel.setColor(color1, color2);
        keyboardPanel.setColor(color1, color2);
        trailerPanel.setColor(color1, color2);
        customizePanel.setColor(color1, color2);

        if(controller.getMainFrame() != null){
            controller.getMainFrame().getTopPanel().setColor(color1, color2);
        }

    }
}
