package View.MainMenu;

import Control.Controller;
import View.GameFrame.MainFrame;

import javax.swing.*;

public class Highscores extends JFrame {
    private static int width = 615; //600
    private static int height = 840; // 800

    public Highscores(Controller controller, MainFrame mainFrame){
        super("Main menu");
        this.setSize(width, height);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.setVisible(true);
    }
}
