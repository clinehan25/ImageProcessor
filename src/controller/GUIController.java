package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextArea;


import fileprocess.ProcessLoad;
import fileprocess.ProcessSave;
import model.SampleProcessingModel;
import view.GUIReadView;

/**
 * Class representing the Controller used in the GUI.
 */
public class GUIController implements ActionListener {
  private SampleProcessingModel model;
  private GUIReadView view;
  private ImageController imageController;

  /**
   * Constructor for the GUI Controller class.
   *
   * @param model           representing a SampleProcessingModel object.
   * @param view            representing a GUIReadView object.
   * @param imageController representing the original ImageController.
   */
  public GUIController(SampleProcessingModel model,
                       GUIReadView view, ImageController imageController) {
    if (model == null || view == null || imageController == null) {
      throw new IllegalArgumentException("Null error.");
    }
    this.model = model;
    this.view = view;
    this.imageController = imageController;
  }

  /**
   * Starts the program.
   *
   * @throws FileNotFoundException if null file is entered.
   */
  public void start() throws FileNotFoundException {
    this.view.setUpGUI(this);
    this.imageController.start();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    File file;
    JFileChooser chooser;

    if (e.getActionCommand().equals("Load")) {
      System.out.println("load");
      chooser = new
              JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
      chooser.showOpenDialog(null);
      file = chooser.getSelectedFile();

      try {
        ProcessLoad load = new ProcessLoad();
        this.model.gallery.put(file.getName(), load.execute(file.getPath()));
        this.view.updateImage(this.model.getImage(file.getName()));
        this.view.updateHistogram(this.model.getImage(file.getName()));
      } catch (IllegalArgumentException exception) {
        throw new IllegalArgumentException(exception);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    } else if (e.getActionCommand().equals("Save")) {
      System.out.println("save");
      try {
        JTextArea textArea = new JTextArea(24, 80);
        chooser = new JFileChooser();
        int retval = chooser.showSaveDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
          try {
            ProcessSave save = new
                    ProcessSave(this.model.getImage(chooser.getName(chooser.getSelectedFile())));
            save.execute(chooser.getSelectedFile().getPath());
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      } catch (NullPointerException | NoSuchElementException | IllegalArgumentException y) {
        throw new IllegalArgumentException();
      }
    }
  }
}
