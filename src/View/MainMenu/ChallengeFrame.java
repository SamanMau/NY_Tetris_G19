package View.MainMenu;

import Control.Controller;
import Control.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class ChallengeFrame extends JFrame {
    private DatabaseController dbc;
    private Controller controller;

    public ChallengeFrame(DatabaseController databaseController, Controller controller){
        setLayout(null);
        this.setSize(500, 500);

        this.dbc = databaseController;
        this.controller = controller;
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //stänger bara ett fönster. Påverkar ej resten av programmet.

        ChallengePanel challengePanel = new ChallengePanel(databaseController, controller);
        this.add(challengePanel);

        this.setVisible(true);
    }

}
