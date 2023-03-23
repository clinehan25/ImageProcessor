package controller;

import imageprocess.ProcessBlur;
import imageprocess.ProcessExposure;
import imageprocess.ProcessLuma;
import imageprocess.ProcessSepia;
import imageprocess.ProcessSharpen;
import imageprocess.ProcessValue;
import img.Image;
import model.SampleProcessingModel;
import imageprocess.ProcessHorizontalFlip;
import fileprocess.ProcessSave;
import imageprocess.ProcessVerticalFlip;
import fileprocess.ProcessLoad;
import imageprocess.ProcessToBlue;
import imageprocess.ProcessToRed;
import imageprocess.ProcessToGreen;
import imageprocess.ProcessIntesity;
import view.GUIReadView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * An image controller that controls the processes and takes in live inputs.
 */
public class ImageController {

  private SampleProcessingModel model;


  /**
   * Constructor for the Image Controller using a view and a model.
   *
   * @param model An image model.
   * @throws IllegalArgumentException if any input is null.
   */
  public ImageController(SampleProcessingModel model, GUIReadView gui) {
    if (model == null || gui == null) {
      throw new IllegalArgumentException("Model is null.");
    }

    this.model = model;
  }

  /**
   * Starts the Image Processor.
   *
   * @throws IllegalArgumentException if inputs are incorrect.
   */
  public void start() throws FileNotFoundException {
    Scanner s = new Scanner(System.in);
    ArrayList<String> inputs = new ArrayList<>();

    while (true) {

      for (int i = 0; i < 3; i++) {
        if (s.hasNext()) {
          inputs.add(s.next());
        }
      }


      switch (inputs.get(0)) {
        case "- file":
          this.startFile(inputs.get(1));
          break;

        case "brighten":
          ProcessExposure exposure = new ProcessExposure(Integer.parseInt(inputs.get(1)),
                  this.model.getImage(inputs.get(2)));
          if (s.hasNext()) {
            inputs.add(s.next());
            this.model.addImage(inputs.get(3), exposure.execute());
          }
          break;

        case "horizontal-flip":
          ProcessHorizontalFlip hFlip = new
                  ProcessHorizontalFlip(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), hFlip.execute());
          break;

        case "vertical-flip":
          ProcessVerticalFlip vFlip = new ProcessVerticalFlip(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), vFlip.execute());
          break;

        case "load":
          //For some reason it started throwing NoSuchElement randomly.
          //My hypothesis is that We're closing the scan somewhere.
          try {
            ProcessLoad load = new ProcessLoad();
            this.model.gallery.put(inputs.get(2), load.execute(inputs.get(1)));
          } catch (NoSuchElementException e) {
            throw new NoSuchElementException("no such element available");
          }

          break;

        case "save":
          //Throws NullPointer's out the whazoo.
          try {
            ProcessSave save = new ProcessSave(this.model.getImage(inputs.get(2)));
            save.execute(inputs.get(1));
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        case "toBlue":
          ProcessToBlue blue = new ProcessToBlue(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), blue.execute());
          break;

        case "toRed":
          ProcessToRed red = new ProcessToRed(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), red.execute());
          break;

        case "toGreen":
          ProcessToGreen green = new ProcessToGreen(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), green.execute());
          break;

        case "intensity":
          ProcessIntesity grey = new ProcessIntesity(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), grey.execute());
          break;

        case "value":
          ProcessValue value = new ProcessValue(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), value.execute());
          break;

        case "sharpen":
          ProcessSharpen sharpen = new ProcessSharpen(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), sharpen.execute());
          break;

        case "luma":
          ProcessLuma luma = new ProcessLuma(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), luma.execute());
          break;

        case "blur":
          ProcessBlur blur = new ProcessBlur(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), blur.execute());
          break;

        case "sepia":
          ProcessSepia sepia = new ProcessSepia(this.model.getImage(inputs.get(1)));
          this.model.addImage(inputs.get(2), sepia.execute());
          break;

        case "menu":
          System.out.println("Usage instructions:\n"
                  + "In order to run our text based program please follow these simple steps.\n"
                  + "— If you would like to run a script, please type “-file [file-name]”. \n"
                  + "— Otherwise, start by using the “load” command listed below to load a file, \n"
                  + "before any other command."
                  + "— Then, you would be able to manipulate the image to your liking, \n"
                  + "by utilizing the commands below."
                  + "— Finally, save your modified image using “save” command under \n"
                  + "the supported formats (png/jpg/jpeg/bmp/ppm)."
                  + "— Use “Q” or “q” to terminate the program. \n"
                  + "Supported program commands: \n"
                  + "load [image-path] [image-name] \n"
                  + "save [image-path] [image-name] \n"
                  + "toRed [original-image-name] [gallery-path] \n"
                  + "toGreen [original-image-name] [gallery-path] \n"
                  + "toBlue [original-image-name] [gallery-path] \n"
                  + "value [image-name] [gallery-path] \n"
                  + "luma [image-name] [gallery-path] \n"
                  + "intensity [image-name] [gallery-path] \n"
                  + "horizontal-flip [image-name] [gallery-path] \n"
                  + "vertical-flip [image-name] [gallery-path] \n"
                  + "blur [image-name] [gallery-path] \n"
                  + "sharpen [image-name] [gallery-path] \n"
                  + "sepia [image-name] [gallery-path] \n"
                  + "brighten [augment-amount] [image-name] [gallery-path] \n"
                  + "menu");
          break;

        case "q":
          return;

        case "Q":
          return;

        default:
          System.out.println("Invalid input, please try again.");
          break;
      }
      inputs.clear();
    }
  }


  /**
   * Helper for start that is used for reading a file.
   *
   * @param name name of the file read
   * @throws FileNotFoundException if file doesn't exist
   */
  public void startFile(String name) throws FileNotFoundException {
    Scanner s = new Scanner(new FileInputStream(name));

    while (s.hasNext()) {
      switch (s.next()) {
        case "brighten":
          ProcessExposure exposure = new ProcessExposure(Integer.parseInt(s.next()),
                  this.model.getImage(s.next()));
          if (s.hasNext()) {
            this.model.addImage(s.next(), exposure.execute());
          }
          break;

        case "horizontal-flip":
          ProcessHorizontalFlip hFlip = new
                  ProcessHorizontalFlip(this.model.getImage(s.next()));
          this.model.addImage(s.next(), hFlip.execute());
          break;

        case "vertical-flip":
          ProcessVerticalFlip vFlip = new ProcessVerticalFlip(this.model.getImage(s.next()));
          this.model.addImage(s.next(), vFlip.execute());
          break;

        case "load":
          try {
            ProcessLoad load = new ProcessLoad();
            Image ret = load.execute(s.next());
            this.model.gallery.put(s.next(), ret);
          } catch (NoSuchElementException e) {
            throw new NoSuchElementException("no such element available");
          }
          break;

        case "save":
          try {
            String ret = s.next();
            ProcessSave save = new ProcessSave(this.model.getImage(s.next()));
            save.execute(ret);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        case "toBlue":
          ProcessToBlue blue = new ProcessToBlue(this.model.getImage(s.next()));
          this.model.addImage(s.next(), blue.execute());
          break;

        case "toRed":
          ProcessToRed red = new ProcessToRed(this.model.getImage(s.next()));
          this.model.addImage(s.next(), red.execute());
          break;

        case "toGreen":
          ProcessToGreen green = new ProcessToGreen(this.model.getImage(s.next()));
          this.model.addImage(s.next(), green.execute());
          break;

        case "intensity":
          ProcessIntesity grey = new ProcessIntesity(this.model.getImage(s.next()));
          this.model.addImage(s.next(), grey.execute());
          break;

        case "value":
          ProcessValue value = new ProcessValue(this.model.getImage(s.next()));
          this.model.addImage(s.next(), value.execute());
          break;

        case "sharpen":
          ProcessSharpen sharpen = new ProcessSharpen(this.model.getImage(s.next()));
          this.model.addImage(s.next(), sharpen.execute());
          break;

        case "luma":
          ProcessLuma luma = new ProcessLuma(this.model.getImage(s.next()));
          this.model.addImage(s.next(), luma.execute());
          break;

        case "blur":
          ProcessBlur blur = new ProcessBlur(this.model.getImage(s.next()));
          this.model.addImage(s.next(), blur.execute());
          break;

        case "sepia":
          ProcessSepia sepia = new ProcessSepia(this.model.getImage(s.next()));
          this.model.addImage(s.next(), sepia.execute());
          break;

        case "q":
        case "Q":
          return;

        default:
          System.out.println("Invalid file, please try again.");
          break;
      }
    }
  }
}
