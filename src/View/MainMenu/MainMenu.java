package View.MainMenu;

import Control.Controller;
import Control.DatabaseController;
import View.GameFrame.MainFrame;
import View.GameFrame.TopPanel;
import View.LoginRegister.LoginRegisterFrame;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private String username;
    private JButton logoutButton;
    private JButton muteButton;
    private JButton profileButton;
    private String status;
    private int totalPoints;
    private int totalChallenges;
    private int totalGames;
    private int userID;
    private TopPanel topPanel;
    private JButton returnHighscoresButton;

    private JButton challengeButton;

    private DatabaseController dbc;

    public MainMenu(Controller controller, MainFrame mainFrame, DatabaseController dbc){
        super("Main menu");
        this.setSize(width, height);
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;
        this.dbc = dbc;

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        createBackgroundLogo();
        createButton();
        addActionListeners();
        this.setVisible(true);
    }

    public void userInfo(){
        username = controller.getUserName();
        totalPoints = controller.getTotalPoints(username);
        userID = controller.getUserIDNoConn();
        status = controller.getStatus(userID);
        totalChallenges = controller.getTotalChallenges(userID);
        totalGames = controller.getTotalGames(userID);
    }

    /**
     * Initiate Buttons and Text and add them to the background.
     * @author Huy
     */
    private void createButton(){
        userInfo();

        welcomeText = new JLabel("Welcome " + username);
        welcomeText.setBounds(220, 310, 300, 50);
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

        //Mute button
        muteButton = new JButton("Music on");
        muteButton.setFont(new Font("Italic", Font.PLAIN, 15));
        muteButton.setBounds(5, 60, 120, 50); //5, 60, 100, 50
        muteButton.setFocusPainted(false);
        muteButton.setFocusable(false);
        muteButton.setBackground(Color.white);
        muteButton.setForeground(Color.black);

        //Mute button
        profileButton = new JButton("Profile");
        profileButton.setFont(new Font("Italic", Font.PLAIN, 15));
        profileButton.setBounds(5, 5, 120, 50); //5, 5, 100, 50
        profileButton.setFocusPainted(false);
        profileButton.setFocusable(false);
        profileButton.setBackground(Color.white);
        profileButton.setForeground(Color.black);

        //Log out button
        logoutButton = new JButton("Log out");
        logoutButton.setFont(new Font("Italic", Font.PLAIN, 15));
        logoutButton.setBounds(480, 0, 120, 50);
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusable(false);
        logoutButton.setBackground(Color.red);
        logoutButton.setForeground(Color.black);

        challengeButton = new JButton("Challenges");
        challengeButton.setFont(new Font("Italic", Font.PLAIN, 15));
        challengeButton.setBounds(5, 115, 120, 50);
        challengeButton.setFocusPainted(false);
        challengeButton.setFocusable(false);
        challengeButton.setBackground(Color.white);
        challengeButton.setForeground(Color.black);


        backgroundLabel.add(logoutButton);
        backgroundLabel.add(profileButton);
        backgroundLabel.add(muteButton);
        backgroundLabel.add(welcomeText);
        backgroundLabel.add(startGameButton);
        backgroundLabel.add(highscoreButton);
        backgroundLabel.add(settingButton);
        backgroundLabel.add(challengeButton);
    }
    /**
     * Adds the backgrounds picture and logo to the frame.
     * @author Huy
     */
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

    /**
     * Add function to button
     * @author Huy
     */
    public void addActionListeners(){
        /**
         * Turn off this frame and open MainFrame, which is the frame to play the game.
         * @author Huy
         */
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.startMainFrame();
                controller.addToQueue();
            }
        });

        /**
         * Open challenge frame to spin and accept challenge.
         * @author Saman
         */
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChallengeFrame challengeFrame = new ChallengeFrame(dbc, controller);
            }
        });

        /**
         * Open a highscores frame to see your and everyone else highscores.
         * @author Huy
         */
        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Highscores highscoresFrame = new Highscores(dbc, controller);
            }
        });

        /**
         * Open settings frame to access game settings.
         * @author Huy
         */
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame settingsFrame = new SettingsFrame(controller);
            }
        });

        /**
         * Mute button to turn the music on or off.
         * @author Huy
         */
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkIfPlay(controller.getMusicOff());
                if(controller.getMusicOff() == "off"){
                    muteButton.setText("Music off");
                }
                else{
                    muteButton.setText("Music on");
                }
            }
        });

        /*
        Eftersom storleken på strängen "status" kan variera i längd, allt från
        "Expert" till "The head of the table", så behöver vi en funktion som alltid lägger
        status label i mitten, oavsett dess längd. Vi börjar med att använda FontMetrics,
        som används för att mäta bredden på en label baserat på vilken font den är i.

        I koden "int x = (width - statusWidth);" subtraherar vi längden på panelen med status
        label panelen för att få fram den area som status label inte täcker. Exempelvis, om
        label är 40 pixlar, och vår panel är 400 pixlar, så innebär det att label inte täcker
        en area på 360 pixlar. Därefter dividerar vi 360 på 2 för att få fram den exakta mitten
        för att kunna placera label.
         */
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRootPane().setGlassPane(new JComponent() {
                    public void paintComponent(Graphics g) {
                        if(username != null){
                            disableButton();
                            g.setColor(new Color(0, 0, 0, 150));
                            g.fillRect(0, 0, getWidth(), getHeight());

                            g.setColor(new Color(0, 157, 191));
                            g.fillRoundRect(125, 200, 350, 500, 50, 50);

                            g.setColor(Color.white);
                            g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
                            g.drawString(username, 260, 250);
                            g.setFont(new Font("Italic", Font.PLAIN, 30)); //45

                            FontMetrics fontMetrics = g.getFontMetrics(); //mäter storleken på en sträng
                            int statusWidth = fontMetrics.stringWidth(status); //hämtar storleken på status label i pixlar
                            int statusX = (width - statusWidth) / 2;

                            String totalPointsText = String.valueOf(totalPoints);
                            int pointsWidth = fontMetrics.stringWidth(totalPointsText);

                            int totalPointsX = (width - pointsWidth) / 2;

                            String totalChallengesText = String.valueOf(totalChallenges);
                            int challengesWidth = fontMetrics.stringWidth(totalChallengesText);
                            int challengesX = (width - challengesWidth) / 2;

                            String totalGamesText = String.valueOf(totalGames);
                            int totalGamesWidth = fontMetrics.stringWidth(totalGamesText);
                            int totalGamesX = (width - totalGamesWidth) / 2;

                            g.drawString(status, statusX, 330); //150
                            g.drawString(totalPointsText, totalPointsX, 435); //250
                            g.drawString(totalChallengesText, challengesX, 540); //280
                            g.drawString(totalGamesText, totalGamesX, 645); //270

                            g.setColor(new Color(0, 21, 133));
                            g.setFont(new Font("Italic", Font.PLAIN, 30));
                            g.drawString("Status", 260, 370);
                            g.drawString("Total points", 222, 475);
                            g.drawString("Total challenges", 190, 580);
                            g.drawString("Total games", 217, 685);

                            g.setColor(Color.red);
                            g.fillRoundRect(420, 170, 80, 80, 80, 80);
                            g.setColor(Color.white);
                            g.setFont(new Font("Italic", Font.PLAIN, 20));
                            g.drawString("Return", 430, 218);

                            super.paintComponent(g);
                        }
                    }
                });

                getRootPane().getGlassPane().setVisible(true);

                returnHighscoresButton = new JButton();
                returnHighscoresButton.setBounds(420, 160, 80, 90);
                returnHighscoresButton.setOpaque(false);
                returnHighscoresButton.setContentAreaFilled(false);
                returnHighscoresButton.setBorderPainted(false);
                returnHighscoresButton.setEnabled(true);
                backgroundLabel.add(returnHighscoresButton);
                returnHighscoresButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableButton();
                        getRootPane().getGlassPane().setVisible(false);
                    }
                });
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginRegisterFrame loginRegisterFrame = new LoginRegisterFrame(controller);
            }
        });
    }

    /**
     * Disable all buttons so the user can't click it.
     * @author Huy
     */
    public void disableButton(){
        startGameButton.setEnabled(false);
        highscoreButton.setEnabled(false);
        settingButton.setEnabled(false);
        logoutButton.setEnabled(false);
        muteButton.setEnabled(false);
        profileButton.setEnabled(false);
    }
    /**
     * Enable all button.
     * @author Huy
     */
    public void enableButton(){
        startGameButton.setEnabled(true);
        highscoreButton.setEnabled(true);
        settingButton.setEnabled(true);
        logoutButton.setEnabled(true);
        muteButton.setEnabled(true);
        profileButton.setEnabled(true);
    }

}
