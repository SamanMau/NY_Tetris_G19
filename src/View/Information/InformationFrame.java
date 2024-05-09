/**
 * A frame which is displayed when the user wants
 * information about the different themes.
 * @author Saman
 */

package View.Information;

import javax.swing.*;

public class InformationFrame extends JFrame {
    private String choice;

    public InformationFrame(String choice){
        this.setLayout(null);
        this.setSize(600, 400);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //stänger bara ett fönster. Påverkar ej resten av programmet.
        this.choice = choice;

        InformationPanel informationPanel = new InformationPanel(choice);

        this.add(informationPanel);

        this.setVisible(true);
    }

}
