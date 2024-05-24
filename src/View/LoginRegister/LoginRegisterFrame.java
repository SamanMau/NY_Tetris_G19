package View.LoginRegister;

import Control.Controller;
import View.GameFrame.TopPanel;
import View.MainMenu.MainMenu;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterFrame extends JFrame {
    Controller controller;
    private static int width = 615; //600
    private static int height = 840; // 800
    private JLabel backgroundLabel;
    private JLabel logoLabel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton playAsGuestButton;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JButton loginButton2 = new JButton();
    private JButton registerButton2 = new JButton();
    private String userName;
    private String userPassword;
    private JButton returnButton;

    public LoginRegisterFrame(Controller controller){
        super("Login/Register");
        this.setSize(width, height);

        this.controller = controller;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        createBackgroundLogo();
        createButton();

        addActionListeners();

        this.setVisible(true);
    }

    private void createButton(){
        //Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Italic", Font.PLAIN, 25));
        loginButton.setBounds(150, 350, 300, 50);
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(Color.black);

        //Register button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Italic", Font.PLAIN, 25));
        registerButton.setBounds(150, 430, 300, 50);
        registerButton.setFocusPainted(false);
        registerButton.setFocusable(false);
        registerButton.setBackground(Color.white);
        registerButton.setForeground(Color.black);

        //playAsGuestButton button
        playAsGuestButton = new JButton("Play as guest");
        playAsGuestButton.setFont(new Font("Italic", Font.PLAIN, 25));
        playAsGuestButton.setBounds(150, 510, 300, 50);
        playAsGuestButton.setFocusPainted(false);
        playAsGuestButton.setFocusable(false);
        playAsGuestButton.setBackground(Color.white);
        playAsGuestButton.setForeground(Color.black);

        backgroundLabel.add(playAsGuestButton);
        backgroundLabel.add(registerButton);
        backgroundLabel.add(loginButton);
    }

    private void createBackgroundLogo(){
        //Tetris background
        ImageIcon tetrisBackground = new ImageIcon("src/Bilder/StartUpFrame.png");
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

    public void startLoginPage(){
        backgroundLabel.remove(playAsGuestButton);
        backgroundLabel.remove(registerButton);
        backgroundLabel.remove(loginButton);

        usernameLabel = new JLabel();
        usernameLabel.setText("Username");

        usernameLabel.setBounds(150, 310, 300, 50);
        usernameLabel.setFont(new Font("Italic", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.white);

        this.usernameTextField = new JTextField("UwU");
        usernameTextField.setFont(new Font("Italic", Font.PLAIN, 20));
        usernameTextField   .setForeground(Color.BLACK);
        usernameTextField.setBounds(150, 350, 300, 50);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(150, 390, 300, 50);
        passwordLabel.setFont(new Font("Italic", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.white);

        passwordTextField = new JPasswordField("123456");
        passwordTextField.setFont(new Font("Italic", Font.PLAIN, 20));
        passwordTextField.setForeground(Color.BLACK);
        passwordTextField.setBounds(150, 430, 300, 50);

        loginButton2 = new JButton("Log in");
        loginButton2.setFont(new Font("Italic", Font.PLAIN, 25));
        loginButton2.setBounds(150, 510, 300, 50);
        loginButton2.setFocusPainted(false);
        loginButton2.setFocusable(false);
        loginButton2.setBackground(Color.white);
        loginButton2.setForeground(Color.black);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Italic", Font.PLAIN, 15));
        returnButton.setBounds(500, 0, 100, 50);
        returnButton.setFocusPainted(false);
        returnButton.setFocusable(false);
        returnButton.setBackground(Color.red);
        returnButton.setForeground(Color.black);

        backgroundLabel.add(returnButton);
        backgroundLabel.add(loginButton2);
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(usernameTextField);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(passwordTextField);

        // Update frame
        addActionListeners();
        revalidate();
        repaint();
    }

    public void startRegisterPage(){
        backgroundLabel.remove(playAsGuestButton);
        backgroundLabel.remove(registerButton);
        backgroundLabel.remove(loginButton);

        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setBounds(150, 310, 300, 50);
        usernameLabel.setFont(new Font("Italic", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.white);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Italic", Font.PLAIN, 20));
        usernameTextField.setForeground(Color.BLACK);
        usernameTextField.setBounds(150, 350, 300, 50);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(150, 390, 300, 50);
        passwordLabel.setFont(new Font("Italic", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.white);

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Italic", Font.PLAIN, 20));
        passwordTextField.setForeground(Color.BLACK);
        passwordTextField.setBounds(150, 430, 300, 50);

        registerButton2 = new JButton("Register");
        registerButton2.setFont(new Font("Italic", Font.PLAIN, 25));
        registerButton2.setBounds(150, 510, 300, 50);
        registerButton2.setFocusPainted(false);
        registerButton2.setFocusable(false);
        registerButton2.setBackground(Color.white);
        registerButton2.setForeground(Color.black);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Italic", Font.PLAIN, 15));
        returnButton.setBounds(500, 0, 100, 50);
        returnButton.setFocusPainted(false);
        returnButton.setFocusable(false);
        returnButton.setBackground(Color.red);
        returnButton.setForeground(Color.black);

        backgroundLabel.add(returnButton);
        backgroundLabel.add(registerButton2);
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(usernameTextField);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(passwordTextField);

        addActionListeners();
        // Update frame
        revalidate();
        repaint();
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

        loginButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If correct username and password. Move to mainMenu. Else show a error message and user have to try again

                //Move to mainMenu
                String name = usernameTextField.getText();
                String password = passwordTextField.getText();
                setPassword(password);
                setName(name);
                int exists = controller.validateUserLoginInfo(name, password);

                if(exists == 0){
                    JOptionPane.showMessageDialog(null, "Wrong username and/or password");
                } else if(exists == 1){
                    dispose();
                    controller.startMainMenu();
                }

            }
        });

        registerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = usernameTextField.getText();
                String password = passwordTextField.getText();
                setPassword(password);
                setName(name);
                String namee = controller.validateUserRegisterInfo(name, password);
                System.out.println("Name: " + namee);
                dispose();
                controller.startMainMenu();
            }
        });
        if(returnButton != null){
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getContentPane().removeAll();
                    repaint();
                    createBackgroundLogo();
                    createButton();
                    addActionListeners();
                }
            });
        }

        playAsGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.startMainMenu();
            }
        });
    }

    public void setPassword(String password){
        this.userPassword = password;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public void setName(String name){
        this.userName = name;
    }

    public String getUserName(){
        return userName;
    }
}
