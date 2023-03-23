package fileprocess;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * A process class that is used to load the provided image.
 */
public class ProcessLoad {

  /**
   * Executes the Load process for the image.
   *
   * @param fileName representing the file name of the image.
   * @return an image
   * @throws FileNotFoundException when the filename is invalid.
   */
  public Image execute(String fileName) throws FileNotFoundException {
    Scanner sc;

    if (fileName.endsWith("ppm")) {
      try {
        sc = new Scanner(new FileInputStream(fileName));
      } catch (FileNotFoundException e) {
        System.out.println("File " + fileName + " not found!");
        return null;
      }

      StringBuilder builder = new StringBuilder();

      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
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
      System.out.println("Width of image: " + width);
      int height = sc.nextInt();
      System.out.println("Height of image: " + height);
      int maxValue = sc.nextInt();
      System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

      ArrayList<ArrayList<Pixel>> temp = new ArrayList<>();
      for (int i = 0; i < height; i++) {
        ArrayList<Pixel> p = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int redChannel = sc.nextInt();
          int greenChannel = sc.nextInt();
          int blueChannel = sc.nextInt();
          //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
          p.add(new Pixel(redChannel, greenChannel, blueChannel));
        }
        temp.add(p);
      }
      return new ImageImpl(maxValue, width, height, temp);
    } else {
      BufferedImage bImg;
      try {
        bImg = ImageIO.read(new File(fileName));
      } catch (IOException e) {
        throw new IllegalStateException("File couldn't be read");
      }
      ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
      int maxValue = 0;
      for (int i = 0; i < bImg.getHeight(); i++) {
        for (int j = 0; j < bImg.getWidth(); j++) {
          Color c = new Color(bImg.getRGB(i, j));
          pixels.get(i).set(j, new Pixel(c.getRed(), c.getGreen(), c.getBlue()));
          maxValue = Math.max(maxValue, Math.max(c.getRed(), Math.max(c.getGreen(), c.getBlue())));
        }
      }

      int width = bImg.getWidth();
      System.out.println("Width of image: " + width);
      int height = bImg.getHeight();
      System.out.println("Height of image: " + height);
      System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

      return new ImageImpl(maxValue, bImg.getWidth(), bImg.getHeight(), pixels);
    }
  }
}
