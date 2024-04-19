package View.Settings;

import Control.Controller;
import View.MainFrame;
import View.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioPanel extends JPanel {
    private Controller controller;
    private MainFrame mainFrame;
    private TopPanel topPanel;

    public AudioPanel(Controller controller, MainFrame mainFrame, SettingsFrame settingsFrame,
                      TopPanel topPanel){
        this.setBounds(100, 100, 100, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        this.controller = controller;
        this.mainFrame = mainFrame;
        this.topPanel = topPanel;

        setUpSongs();
    }

    public void setUpSongs(){
        JLabel chooseSong = new JLabel("Choose song!");
        chooseSong.setBounds(200, 10, 80, 40);

        JRadioButton theme1 = new JRadioButton("Theme 1");
        JRadioButton theme2 = new JRadioButton("Theme 2");
        JRadioButton theme3 = new JRadioButton("Theme 3");
        JRadioButton defaultSong = new JRadioButton("Default");

        theme1.setBounds(200, 50, 80, 35);
        theme1.setBackground(Color.LIGHT_GRAY);

        theme2.setBounds(200, 90, 80, 35);
        theme2.setBackground(Color.LIGHT_GRAY);

        theme3.setBounds(200, 130, 80, 35);
        theme3.setBackground(Color.LIGHT_GRAY);

        defaultSong.setBounds(200, 170, 80, 35);
        defaultSong.setBackground(Color.LIGHT_GRAY);

        JLabel chooseOwnText = new JLabel("Or choose your own");
        chooseOwnText.setBounds(185, 210, 120, 40);

        JButton chooseOwnSongBtn = new JButton("Choose own song");
        chooseOwnSongBtn.setBounds(180, 250, 140, 40);
        chooseOwnSongBtn.setBackground(Color.lightGray);

        JLabel information = new JLabel("The chosen file needs to be a .wav file");
        information.setBounds(145, 290, 240, 40);

        theme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.setNewMusic("src/Ljud/dark.wav");
            }
        });

        theme2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.setNewMusic("src/Ljud/theme2.wav");
            }
        });

        theme3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.setNewMusic("src/Ljud/theme3.wav");
            }
        });

        chooseOwnSongBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.chooseOwnSong();
            }
        });

        defaultSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.setNewMusic("src/Ljud/theme1.wav");
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(theme1);
        group.add(theme2);
        group.add(theme3);
        group.add(defaultSong);

        this.add(theme1);
        this.add(theme2);
        this.add(theme3);
        this.add(defaultSong);
        this.add(chooseSong);
        this.add(chooseOwnText);
        this.add(chooseOwnSongBtn);
        this.add(information);
    }

}
