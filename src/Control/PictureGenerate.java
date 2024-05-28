package Control;

import View.MainMenu.ChallengePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class PictureGenerate {
    private boolean isRunning;
    private int runFor3Sec;
    private ChallengePanel challengePanel;
    private javax.swing.Timer timer;
    private int picNum = 1;
    private Random rd;


    public PictureGenerate(ChallengePanel challengePanel){
        this.isRunning = false;
        this.runFor3Sec = 4000;
        this.challengePanel = challengePanel;
        rd = new Random();
    }

    public void setIsRunning(){
        this.isRunning = true;
        timer.start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void counter(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runFor3Sec-=100;
                //counterLabel.setText(String.valueOf(second));
                String fileName = "src/Bilder/image" + rd.nextInt(1,4) + ".png";
                String fileName2 = "src/Bilder/image" + rd.nextInt(1,4) + ".png";
                String fileName3 = "src/Bilder/image" + rd.nextInt(1,4) + ".png";
                challengePanel.setPic1(fileName);
                challengePanel.setPic2(fileName2);
                challengePanel.setPic3(fileName3);
                picNum++;

                if(runFor3Sec == 0){
                    runFor3Sec = 3000;
                    timer.stop();

                    if(fileName.equals(fileName2) && fileName3.equals(fileName2)){
                        challengePanel.easyChallenge();
                    } else {
                        challengePanel.hardChallenge();
                    }

                    challengePanel.setButtonEnabled(true);
                }
            }
        });
    }
}
