package View.LoginRegister;

import View.GameFrame.TopPanel;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterFrame extends JFrame {
    private static int width = 615; //600
    private static int height = 840; // 800
    private JButton loginButton;
    private JButton registerButton;
    private JButton playAsGuestButton;

    public LoginRegisterFrame(){
        super("Login/Register");
        this.setSize(width, height);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        createButton();
        createBackgroundLogo();

        this.setVisible(true);


    }

    private void createButton(){
        //Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Italic", Font.ROMAN_BASELINE, 20));
        loginButton.setBounds(150, 350, 300, 50);
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(Color.black);

        //Register button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Italic", Font.ROMAN_BASELINE, 20));
        registerButton.setBounds(150, 430, 300, 50);
        registerButton.setFocusPainted(false);
        registerButton.setFocusable(false);
        registerButton.setBackground(Color.white);
        registerButton.setForeground(Color.black);

        //Register button
        playAsGuestButton = new JButton("Play as guest");
        playAsGuestButton.setFont(new Font("Italic", Font.ROMAN_BASELINE, 20));
        playAsGuestButton.setBounds(150, 510, 300, 50);
        playAsGuestButton.setFocusPainted(false);
        playAsGuestButton.setFocusable(false);
        playAsGuestButton.setBackground(Color.white);
        playAsGuestButton.setForeground(Color.black);

        this.add(playAsGuestButton);
        this.add(registerButton);
        this.add(loginButton);
    }

    private void createBackgroundLogo(){
        //Tetris background
        ImageIcon tetrisBackground = new ImageIcon("src/Bilder/StartUpFrame.png");
        tetrisBackground.setImage(tetrisBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(tetrisBackground);
        backgroundLabel.setBounds(0, 1, width-15, height-40);


        //Tetris logo
        ImageIcon tetrisLogo = new ImageIcon("src/Bilder/Tetris_logo.png");
        tetrisLogo.setImage(tetrisLogo.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(tetrisLogo);
        logoLabel.setBounds(160, 100, 280, 200);

        this.add(logoLabel);
        this.add(backgroundLabel);
    }

    public void startLoginPage(){

    }

    public void startRegisterPage(){

    }

    public void addActionListeners(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Move to login panel
                startLoginPage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Move to register panel
                startRegisterPage();
            }
        });

        playAsGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Move to Main menu as guest. Stats such as points... will not be saved as guest.
            }
        });
    }
}
