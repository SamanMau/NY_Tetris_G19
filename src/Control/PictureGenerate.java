package Control;

import View.MainMenu.ChallengePanel;

public class PictureGenerate extends Thread {
    private boolean isRunning;
    private long runFor3Sec;
    private ChallengePanel challengePanel;


    public PictureGenerate(ChallengePanel challengePanel){
        this.isRunning = false;
        this.runFor3Sec = 3000;
        this.challengePanel = challengePanel;
    }

    public void setIsRunning(){
        this.isRunning = true;
        run();
    }

    public void generatePictures(){

    }


    @Override
    public void run(){
        int picNum = 1;

        long startTime = System.currentTimeMillis();

        while (isRunning && (System.currentTimeMillis() - startTime < runFor3Sec)){
                String name = "src/Bilder/image" + picNum + ".png";
            System.out.println(name);
            challengePanel.setPic1(name);

            if(picNum == 3){
                picNum = 1;
            }

            picNum++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
