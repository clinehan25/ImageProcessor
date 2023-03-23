package view;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.Scanner;

import model.SampleProcessingModel;

/**
 * The View of the image displayed to the user.
 */
public class ImageReadView implements ImageView {

  SampleProcessingModel model;

  /**
   * Constructs a view based off the model.
   *
   * @param model The model the view is modeled after.
   */
  public ImageReadView(SampleProcessingModel model) {
    this.model = model;
  }


  // unfinished method for HW6
  @Override
  public void readImage(String fileName) {
    /*Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      System.out.println("File "+fileName+ " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
    }*/
  }
}
