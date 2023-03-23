package view;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import img.Image;
import model.SampleProcessingModel;

/**
 * This class represents the GUI and its view.
 */
public class GUIReadView extends JFrame {


  // image
  private JLabel imageLabel;
  private JPanel imagePanel;


  private HistogramReadView histogramPanel;

  public GUIReadView(SampleProcessingModel model, String name) {
    super(name);
  }

  /**
   * Method used to set up the GUI for the program.
   *
   * @param listener representing an action listener for user inputs on gui elements.
   */
  public void setUpGUI(ActionListener listener) {

    // sets up main view and window view.
    this.setSize(1000, 1000);
    this.setVisible(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScroll = new JScrollPane(mainPanel);
    this.add(mainScroll);


    // set up image view
    this.imageLabel = new JLabel();
    this.imagePanel = new JPanel();
    mainPanel.add(this.imagePanel);
    JScrollPane imageScroll = new JScrollPane(this.imageLabel);
    imageScroll.setPreferredSize(new Dimension(600, 400));
    this.imagePanel.add(imageScroll);

    // set up histogram view
    // histogram
    JLabel histogramLabel = new JLabel();
    this.histogramPanel = new HistogramReadView(null);
    mainPanel.add(this.histogramPanel);
    JScrollPane histogramScroll = new JScrollPane(histogramLabel);
    histogramScroll.setPreferredSize(new Dimension(600, 100));
    this.histogramPanel.add(histogramScroll);

    // sets up the text info on commands
    JLabel infoText = new JLabel();
    infoText.setPreferredSize(new Dimension(200, 100));
    infoText.setText("Enter command: 'menu menu menu' to see a command list, "
            + "or 'q q q' or 'Q Q Q' to quit.");
    infoText.setVerticalTextPosition(SwingConstants.TOP);
    mainPanel.add(infoText);

    // sets up the load button
    // load button
    JPanel loadPanel = new JPanel();
    mainPanel.add(loadPanel);
    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");
    loadButton.addActionListener(listener);
    loadPanel.add(loadButton);

    // sets up the save button
    // save button
    JPanel savePanel = new JPanel();
    mainPanel.add(savePanel);
    JButton saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    saveButton.addActionListener(listener);
    savePanel.add(saveButton);


    this.pack();
  }

  /**
   * Updates the image displayed.
   *
   * @param img the new image
   */
  public void updateImage(Image img) {
    BufferedImage bImg = new BufferedImage(img.getWidth(),
            img.getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Color color = new Color(
                img.getPixels().get(i).get(j).getRed(),
                img.getPixels().get(i).get(j).getGreen(),
                img.getPixels().get(i).get(j).getBlue());

        bImg.setRGB(j, i, color.getRGB());
      }
    }
    this.imageLabel.setIcon(new ImageIcon(bImg));
    this.imagePanel.repaint();
    this.imagePanel.validate();
  }

  /**
   * Updates the histogram.
   *
   * @param img the new image
   */
  public void updateHistogram(Image img) {
    this.histogramPanel.setImg(img);
    this.histogramPanel.repaint();
    this.histogramPanel.validate();
  }
}
