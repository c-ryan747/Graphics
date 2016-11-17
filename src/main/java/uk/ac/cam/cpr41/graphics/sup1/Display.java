package uk.ac.cam.cpr41.graphics.sup1;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private Scene scene;
    private DisplayPanel displayPanel;

    public Display(int numHorizontalPixels, int numVerticalPixels) {
        super("Display");

        // JFrame setup
        setSize(numHorizontalPixels, numVerticalPixels);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // DisplayPanel setup
        displayPanel = new DisplayPanel(numVerticalPixels, numHorizontalPixels);
        add(displayPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        displayPanel.setScene(scene);
    }

}
