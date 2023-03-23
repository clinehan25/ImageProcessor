package view;

//import java.util.Scanner;
//import java.io.FileNotFoundException;
//import java.io.FileInputStream;

/**
 * This interface refers to the resulting view of an Image used in model.
 */
public interface ImageView {
  /**
   * Returns a printed view of the image wit all of its correct RGB values and pixel locations.
   *
   * @Param fileName name of the file being read.
   * @Return A view of the image.
   */
  public void readImage(String fileName);
}
