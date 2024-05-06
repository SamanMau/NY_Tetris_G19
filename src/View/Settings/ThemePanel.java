package View.Settings;

import View.GameFrame.*;
import View.Information.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemePanel extends JPanel {
    private Control.Controller controller;
    private BottomPanel bottomPanel;
    private RPanel rPanel;
    private LPanel lPanel;
    private JLabel chooseText;
    private JButton party;
    private JButton partyInfo;
    private JButton wildWestInfo;
    private JTextArea partyText;
    private JButton wildWest;

    public ThemePanel(Control.Controller controller){
        this.controller = controller;
        this.rPanel = rPanel;
        this.lPanel = lPanel;

        this.setBounds(100, 100, 100, 100);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);

        setup();
        addListeners();
        this.add(chooseText);
        this.add(party);
        this.add(partyInfo);

        this.add(wildWest);
        this.add(wildWestInfo);
    }

    public void setup(){
        chooseText = new JLabel("Choose theme in GUI");
        chooseText.setBounds(175, 10, 150, 25);

        party = new JButton("Party theme");
        party.setBounds(180, 50, 120, 35);
        party.setBackground(Color.lightGray);
        party.setFocusable(false);

        partyInfo = new JButton("Info");
        partyInfo.setBounds(300, 70, 80, 15);
        partyInfo.setFocusable(false);

        wildWest = new JButton("Wild west theme");
        wildWest.setBounds(180, 100, 150, 35);
        wildWest.setBackground(Color.lightGray);
        wildWest.setFocusable(false);

        wildWestInfo = new JButton("Info");
        wildWestInfo.setBounds(330, 120, 80, 15);

        Font font = new Font("Times new Roman", Font.BOLD, 16);
        chooseText.setFont(font);
        party.setFont(font);
        wildWest.setFont(font);
    }

    public void addListeners(){
        partyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InformationFrame informationFrame = new InformationFrame("Party info");
            }
        });

        wildWestInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InformationFrame informationFrame = new InformationFrame("Wild west info");
            }
        });

        party.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeTheme("Party");
            }
        });

        wildWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeTheme("Wildwest");

            }
        });
    }

}
