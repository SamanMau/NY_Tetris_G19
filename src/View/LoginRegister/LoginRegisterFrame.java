package View.LoginRegister;

import javax.swing.*;
import java.awt.*;

public class LoginRegisterFrame extends JFrame {
    private static int width = 615; //600
    private static int height = 840; // 800

    public LoginRegisterFrame(){
        super("Login/Register");
        this.setSize(width, height);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);


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

        this.setVisible(true);


    }
}
