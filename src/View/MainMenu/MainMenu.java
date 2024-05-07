package View.MainMenu;

import Control.Controller;
import View.GameFrame.MainFrame;
import View.GameFrame.TopPanel;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class MainMenu extends JFrame {
    Controller controller;
    MainFrame mainFrame;
    private static int width = 615; //600
    private static int height = 840; // 800
    private JLabel backgroundLabel;
    private JLabel logoLabel;
    private JButton startGameButton;
    private JButton highscoreButton;
    private JButton settingButton;
    private JLabel welcomeText;
    private String username = "(Username)";
    private JButton logoutButton;
    private JButton muteButton;

    public MainMenu(Controller controller, MainFrame mainFrame){
        super("Main menu");
        this.setSize(width, height);
        this.controller = controller;
        this.mainFrame = mainFrame;

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        createBackgroundLogo();
        createButton();

        addActionListeners();

        this.setVisible(true);
    }
    private void createButton(){
        welcomeText = new JLabel("Welcome " + username);
        welcomeText.setBounds(200, 310, 300, 50);
        welcomeText.setFont(new Font("Italic", Font.PLAIN, 20));
        welcomeText.setForeground(Color.white);

        //Login button
        startGameButton = new JButton("Start game");
        startGameButton.setFont(new Font("Italic", Font.PLAIN, 25));
        startGameButton.setBounds(150, 360, 300, 50);
        startGameButton.setFocusPainted(false);
        startGameButton.setFocusable(false);
        startGameButton.setBackground(Color.green);
        startGameButton.setForeground(Color.black);

        //Register button
        highscoreButton = new JButton("Highscores");
        highscoreButton.setFont(new Font("Italic", Font.PLAIN, 25));
        highscoreButton.setBounds(150, 440, 300, 50);
        highscoreButton.setFocusPainted(false);
        highscoreButton.setFocusable(false);
        highscoreButton.setBackground(Color.orange);
        highscoreButton.setForeground(Color.black);

        //playAsGuest button
        settingButton = new JButton("Settings");
        settingButton.setFont(new Font("Italic", Font.PLAIN, 25));
        settingButton.setBounds(150, 520, 300, 50);
        settingButton.setFocusPainted(false);
        settingButton.setFocusable(false);
        settingButton.setBackground(Color.lightGray);
        settingButton.setForeground(Color.black);

        backgroundLabel.add(welcomeText);
        backgroundLabel.add(startGameButton);
        backgroundLabel.add(highscoreButton);
        backgroundLabel.add(settingButton);
    }

    private void createBackgroundLogo(){
        //Tetris background
        ImageIcon tetrisBackground = new ImageIcon("src/Bilder/MainMenuBackground.png");
        tetrisBackground.setImage(tetrisBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        backgroundLabel = new JLabel(tetrisBackground);
        backgroundLabel.setBounds(0, 1, width-15, height-40);

        //Tetris logo
        ImageIcon tetrisLogo = new ImageIcon("src/Bilder/Tetris_logo.png");
        tetrisLogo.setImage(tetrisLogo.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH));
        logoLabel = new JLabel(tetrisLogo);
        logoLabel.setBounds(160, 100, 280, 200);

        this.add(backgroundLabel);
        backgroundLabel.add(logoLabel);
    }

    public void addActionListeners(){
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.startMainFrame();
            }
        });

        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame settingsFrame = new SettingsFrame(controller);
            }
        });
    }
}
