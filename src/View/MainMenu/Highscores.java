package View.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Highscores extends JFrame {
    private static int width = 615; //600
    private static int height = 840; // 800
    private Color backgroundColor = new Color(49, 49, 49);

    public Highscores(){
        super("Highscores");
        this.setSize(width, height);

        createBackgroundLogo();
        addUserHighscores(getTopHighscores(), "Username");
        addTopHighscores(getTopHighscores(), getTopHighscoresNames());


        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
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


    public void addUserHighscores(String[] userHighscores, String username){
        JLabel userHighscoresText = new JLabel("User Highscores");
        userHighscoresText.setBounds(345, 420, 300, 50);
        userHighscoresText.setFont(new Font("Italic", Font.PLAIN, 30));
        userHighscoresText.setForeground(Color.white);

        JList<String> highscoresJList = new JList<>();
        String[] highscoresCombine = new String[10];
        for(int i = 0; i < userHighscores.length; i++){
            highscoresCombine[i] = username + " | Points: " + userHighscores[i] + " | Level: " + i;
        }
        highscoresJList.setListData(highscoresCombine);

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
