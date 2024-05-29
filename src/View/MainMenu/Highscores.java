package View.MainMenu;

import Control.Controller;
import Control.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class Highscores extends JFrame {
    private static int width = 615; //600
    private static int height = 840; // 800
    private Color backgroundColor = new Color(49, 49, 49);

    private DatabaseController dbc;
    private Controller controller;

    private String[] topHighScoreList;

    private String[] personalHighScoreList;

    public Highscores(DatabaseController dbc, Controller controller){
        super("Highscores");
        this.setSize(width, height);
        this.controller = controller;
        this.dbc = dbc;

        createBackgroundLogo();
        createTopHighScores();
        createPersonalHighscore();
        displayTopHighScores();
        displayPersonalScore();

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void createTopHighScores(){
        if(dbc!= null){
            topHighScoreList = dbc.getTopHighScores();
        }

    }

    public void createPersonalHighscore(){
        String name = controller.getUserName();

        personalHighScoreList = dbc.getPersonalList(name);
    }

    public void displayTopHighScores(){
        JLabel topHighscoresText = new JLabel("Top Highscores");
        topHighscoresText.setBounds(45, 420, 300, 50);

        topHighscoresText.setFont(new Font("Italic", Font.PLAIN, 30));

        topHighscoresText.setForeground(Color.white);


        JList<String> jList = new JList<>();

        jList.setListData(topHighScoreList);

        jList.setBounds(10, 480, width/2 - 20, height/2-100);
        jList.setFont(new Font("Italic", Font.PLAIN, 18));
        jList.setBackground(backgroundColor);
        jList.setForeground(Color.white);
        //Makes JList not clickable
        jList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
            }
        });

        this.add(topHighscoresText);
        this.add(jList);

    }

    private void createBackgroundLogo(){

        //Tetris logo
        ImageIcon tetrisLogo = new ImageIcon("src/Bilder/Tetris_logo.png");
        tetrisLogo.setImage(tetrisLogo.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(tetrisLogo);
        logoLabel.setBounds(160, 100, 280, 200);

        getContentPane().setBackground(backgroundColor);

        this.add(logoLabel);
    }

    /*
    Vänster highscore
     */
    public void addTopHighscores(String[] topHighScores, String[] topHighscoresNames){
        JLabel topHighscoresText = new JLabel("Top Highscores");

        topHighscoresText.setBounds(45, 420, 300, 50);

        topHighscoresText.setFont(new Font("Italic", Font.PLAIN, 30));

        topHighscoresText.setForeground(Color.white);

        JList<String> highscoresJList = new JList<>();

        String[] highscoresCombine = new String[10];

        for(int i = 0; i < topHighScores.length; i++){
            highscoresCombine[i] = topHighscoresNames[i] + " | Points: " + topHighScores[i] + " | Level: " + i;
        }

        highscoresJList.setListData(highscoresCombine);

        highscoresJList.setBounds(10, 480, width/2 - 20, height/2-100);
        highscoresJList.setFont(new Font("Italic", Font.PLAIN, 18));
        highscoresJList.setBackground(backgroundColor);
        highscoresJList.setForeground(Color.white);
        //Makes JList not clickable
        highscoresJList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
            }
        });

        this.add(topHighscoresText);
        this.add(highscoresJList);
    }

    public String[] getTopHighscores(){
        String[] topHighscores = new String[10];
        for(int i = 0; i < 10; i++){
            topHighscores[i] = String.valueOf(i);
        }
        return topHighscores;
    }

    public String[] getTopHighscoresNames(){
        String[] topHighscoresNames = new String[10];
        for(int i = 0; i < 10; i++){
            topHighscoresNames[i] = "DummyName" + i;
        }
        return topHighscoresNames;
    }



    /*
    Höger highscore, personlig.
     */
    public void displayPersonalScore(){
        JLabel userHighscoresText = new JLabel("Personal Highscore");
        userHighscoresText.setBounds(330, 420, 300, 50);
        userHighscoresText.setFont(new Font("Italic", Font.PLAIN, 30));
        userHighscoresText.setForeground(Color.white);

        JList<String> highscoresJList = new JList<>();

        highscoresJList.setListData(personalHighScoreList);

        highscoresJList.setBounds(330, 480, width/2 - 20, height/2-100);
        highscoresJList.setFont(new Font("Italic", Font.PLAIN, 18));
        highscoresJList.setBackground(backgroundColor);
        highscoresJList.setForeground(Color.white);
        //Makes JList not clickable
        highscoresJList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
            }
        });

        this.add(userHighscoresText);
        this.add(highscoresJList);
    }
}
