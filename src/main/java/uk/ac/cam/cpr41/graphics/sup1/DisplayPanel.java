package uk.ac.cam.cpr41.graphics.sup1;

import javax.swing.*;
import java.awt.*;


public class DisplayPanel extends JPanel {
    private int numHorizontalPixels;
    private int numVerticalPixels;
    private Scene scene;

    private double pixelSizeInSpace = 0.1;
    private Vector3 raySource = new Vector3(0, 0, -50);

    public DisplayPanel(int numHorizontalPixels, int numVerticalPixels) {
        this.numVerticalPixels = numVerticalPixels;
        this.numHorizontalPixels = numHorizontalPixels;
    }

    public Dimension getPreferredSize() {
        return new Dimension(numHorizontalPixels, numVerticalPixels);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scene == null) return;

        // Clear display
        Dimension currentSize = getSize();
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, currentSize.width, currentSize.height);

        double colScale = (double) currentSize.width / (double)numVerticalPixels;
        double rowScale = (double) currentSize.height / (double)numHorizontalPixels;

        // Set each pixel to a colour specified by the current scene
        for (int row = 0; row < numVerticalPixels; row++) {
            for (int col = 0; col < numHorizontalPixels; col++) {
                int colPos = (int)(col*colScale);
                int rowPos = (int)(row*rowScale);
                int nextCol = (int)((col+1)*colScale);
                int nextRow = (int)((row+1)*rowScale);

                if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
                    g.setColor(scene.getColourForRay(getRayForPixel(row, col)));
                    g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
                }
            }
        }

    }

    public void setScene(Scene scene) {
        this.scene = scene;
        repaint();
    }

    // Generate a ray going from the viewer to through the pixel
    private Ray getRayForPixel(int row, int col) {
        double pixelX = pixelSizeInSpace * (col + 0.5 - (numHorizontalPixels / 2.0));
        double pixelY = pixelSizeInSpace * ((numVerticalPixels / 2.0) - row - 0.5);

        Vector3 pixel = new Vector3(pixelX, pixelY, 0);
        Vector3 direction = pixel.minus(raySource);

        return new Ray(pixel, direction);
    }

}