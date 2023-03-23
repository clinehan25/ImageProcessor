package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import img.Image;

/**
 * Class representing the histogram element in the program's GUI.
 */
public class HistogramReadView extends JPanel {
  public Image img;

  /**
   * Constructs a Histogram View JPanel.
   */
  public HistogramReadView(Image img) {
    super();
    this.img = img;
  }

  /**
   * Sets a new histogram to be displayed in the GUI.
   */
  public void setHistogram(Image img) {
    this.repaint();
  }

  @Override
  public void paint(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    //super.paint(g);
    if (this.img != null) {
      super.paint(g);
      int[][] histogram = this.getHistogram();

      int maxR = 0;
      int minR = Integer.MAX_VALUE;
      int maxG = 0;
      int minG = Integer.MAX_VALUE;
      int maxB = 0;
      int minB = Integer.MAX_VALUE;
      int maxI = 0;
      int minI = Integer.MAX_VALUE;
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < histogram[0].length; j++) {
          switch (i) {
            case 0:
              if (histogram[i][j] > maxR) {
                maxR = histogram[i][j];
              }
              if (histogram[i][j] < minR) {
                minR = histogram[i][j];
              }
              break;
            case 1:
              if (histogram[i][j] > maxG) {
                maxG = histogram[i][j];
              }
              if (histogram[i][j] < minG) {
                minG = histogram[i][j];
              }
              break;
            case 2:
              if (histogram[i][j] > maxB) {
                maxB = histogram[i][j];
              }
              if (histogram[i][j] < minB) {
                minB = histogram[i][j];
              }
              break;
            case 3:
              if (histogram[i][j] > maxI) {
                maxI = histogram[i][j];
              }
              if (histogram[i][j] < minI) {
                minI = histogram[i][j];
              }
              break;
            default:
              return;
          }

        }
      }

      int normal = 0;
      Color[] colors = new Color[4];
      colors[0] = new Color(255, 0, 0, 63);
      colors[1] = new Color(0, 255, 0, 63);
      colors[2] = new Color(0, 0, 255, 63);
      colors[3] = new Color(255, 255, 0, 63);
      for (int i = 0; i < 4; i++) {
        g.setColor(colors[i]);
        for (int j = 0; j < histogram[0].length; j++) {
          switch (i) {
            case 0:
              normal = (int) (((double) (histogram[i][j] - minR) / (maxR - minR)) * 300);
              g.drawLine(j, 100, j, 100 - normal);
              break;
            case 1:
              normal = (int) (((double) (histogram[i][j] - minG) / (maxG - minG)) * 300);
              g.drawLine(j, 100, j, 100 - normal);
              break;
            case 2:
              normal = (int) (((double) (histogram[i][j] - minB) / (maxB - minB)) * 300);
              g.drawLine(j, 100, j, 100 - normal);
              break;
            case 3:
              normal = (int) (((double) (histogram[i][j] - minI) / (maxI - minI)) * 300);
              g.drawLine(j, 100, j, 100 - normal);
              break;
            default:
              return;
          }
          //System.out.println(j);
          //System.out.println(300 - normal);
          //System.out.println(histogram[i][j]);
        }
      }
      System.out.println("done");
    }
    //this.repaint();
  }

  /**
   * Gets the image's histogram.
   *
   * @return returns an array of the histogram arrays.
   */
  public int[][] getHistogram() {
    int[][] ret = new int[4][256];
    for (int i = 0; i < this.img.getHeight(); i++) {
      for (int j = 0; j < this.img.getWidth(); j++) {
        ret[0][this.img.getPixels().get(i).get(j).getRed()] += 1;
        ret[1][this.img.getPixels().get(i).get(j).getGreen()] += 1;
        ret[2][this.img.getPixels().get(i).get(j).getBlue()] += 1;
        int grey = (this.img.getPixels().get(i).get(j).getRed()
                + this.img.getPixels().get(i).get(j).getBlue()
                + this.img.getPixels().get(i).get(j).getGreen()) / 3;
        ret[3][grey] += 1;
      }
    }
    return ret;
  }

  /**
   * Sets a new image.
   *
   * @param img the new image
   */
  public void setImg(Image img) {
    this.img = img;
    this.repaint();
  }
}
