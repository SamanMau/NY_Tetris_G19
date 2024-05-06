package View.Settings;

import Control.Controller;
import View.GameFrame.MainFrame;
import View.GameFrame.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioPanel extends JPanel {
    private Controller controller;
    private ButtonGroup group;
    private JRadioButton theme1;
    private JRadioButton theme2;
    private JRadioButton theme3;
    private JRadioButton defaultSong;
    private JLabel chooseOwnText;
    private JButton chooseOwnSongBtn;
    private JLabel information;
    private JLabel chooseSong;
    private Font font;
    private JLabel changeAudioText;
    private JButton higher;
    private JButton lower;

    public AudioPanel(Controller controller, SettingsFrame settingsFrame){
        this.setBounds(100, 100, 100, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        this.controller = controller;

        setUpButtons();
        setFont();
        addActionListeners();
        setUpPicture();

        group = new ButtonGroup();
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
        this.add(changeAudioText);
        this.add(higher);
        this.add(lower);
    }

    public void setUpButtons(){
        font = new Font("Times new Roman", Font.BOLD, 16);

        chooseSong = new JLabel("Choose song!");
        chooseSong.setBounds(200, 10, 140, 40);

        theme1 = new JRadioButton("Audio 1");
        theme2 = new JRadioButton("Audio 2");
        theme3 = new JRadioButton("Audio 3");
        defaultSong = new JRadioButton("Default");

        theme1.setBounds(200, 50, 80, 35);
        theme1.setBackground(Color.LIGHT_GRAY);

        theme2.setBounds(200, 90, 80, 35);
        theme2.setBackground(Color.LIGHT_GRAY);

        theme3.setBounds(200, 130, 80, 35);
        theme3.setBackground(Color.LIGHT_GRAY);

        defaultSong.setBounds(200, 170, 80, 35);
        defaultSong.setBackground(Color.LIGHT_GRAY);

        chooseOwnText = new JLabel("Or choose your own");
        chooseOwnText.setBounds(185, 210, 150, 40);

        chooseOwnSongBtn = new JButton("Choose own song");
        chooseOwnSongBtn.setBounds(170, 250, 160, 40);
        chooseOwnSongBtn.setBackground(Color.lightGray);

        information = new JLabel("The chosen file needs to be a .wav file");
        information.setBounds(120, 290, 300, 40);

        higher = new JButton("+");
        higher.setBounds(260, 380, 50, 30);
        higher.setBackground(Color.lightGray);

        lower = new JButton("-");
        lower.setBounds(160, 380, 50, 30);
        lower.setBackground(Color.lightGray);

        changeAudioText = new JLabel("Change volume of audio");
        changeAudioText.setBounds(160, 340, 280, 40);
    }

    public void setFont(){
        chooseSong.setFont(font);
        defaultSong.setFont(font);
        theme1.setFont(font);
        theme2.setFont(font);
        theme3.setFont(font);
        chooseOwnText.setFont(font);
        chooseOwnSongBtn.setFont(font);
        information.setFont(font);
        changeAudioText.setFont(font);
    }

    public void setUpPicture(){
        ImageIcon image = new ImageIcon("src/Bilder/musicVolume.png");
        Image changedSize = image.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
        ImageIcon newSize = new ImageIcon(changedSize);

        JLabel label = new JLabel(newSize);

        label.setBounds(210, 370, 50, 50);
        this.add(label);
    }

    public void addActionListeners(){

        higher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.incrementVolume();
            }
        });

        lower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.decrementVolume();
            }
        });

        theme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/dark.wav");
            }
        });

        theme2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/audio2.wav");
            }
        });

        theme3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewMusic("src/Ljud/audio3.wav");
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
                controller.setNewMusic("src/Ljud/audio1.wav");
            }
        });

    }

}
