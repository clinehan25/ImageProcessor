package process;

import org.junit.Test;

import java.util.ArrayList;

import imageprocess.ProcessIntesity;
import imageprocess.ProcessValue;
import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the ToGrey process accurately makes the image grey.
 */
public class ProcessToGreyTest {

  /**
   * Test for value greyscale.
   */
  @Test
  public void executeValueTest() {

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }

    // initialize an image w/ the array
    Image img = new ImageImpl(8, 4, 4, pixels);

    // initialize the process w/ the image
    ProcessValue toGrey = new ProcessValue(img);
    Image img2 = toGrey.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int r = img.getPixels().get(i).get(j).getRed();
        int b = img.getPixels().get(i).get(j).getBlue();
        int g = img.getPixels().get(i).get(j).getGreen();
        int grey = Math.max(r, Math.max(g, b));

        assertEquals(grey, img2.getPixels().get(i).get(j).getRed());
        assertEquals(grey, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(grey, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }

  /**
   * Tests for the intensity greyscale.
   */
  @Test
  public void executeIntensityTest() {

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }

    // initialize an image w/ the array
    Image img = new ImageImpl(8, 4, 4, pixels);

    // initialize the process w/ the image
    ProcessIntesity toGrey = new ProcessIntesity(img);
    Image img2 = toGrey.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int r = img.getPixels().get(i).get(j).getRed();
        int b = img.getPixels().get(i).get(j).getBlue();
        int g = img.getPixels().get(i).get(j).getGreen();
        int grey = (r + b + g) / 3;

        assertEquals(grey, img2.getPixels().get(i).get(j).getRed());
        assertEquals(grey, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(grey, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }

  @Test
  public void executeLumaTest() {

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }

    // initialize an image w/ the array
    Image img = new ImageImpl(8, 4, 4, pixels);

    // initialize the process w/ the image
    ProcessIntesity toGrey = new ProcessIntesity(img);
    Image img2 = toGrey.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int r = img.getPixels().get(i).get(j).getRed();
        int b = img.getPixels().get(i).get(j).getBlue();
        int g = img.getPixels().get(i).get(j).getGreen();
        int grey = (int)((0.2126 * r) + (0.7152 * g) + (0.0722 * b));

        assertEquals(grey, img2.getPixels().get(i).get(j).getRed());
        assertEquals(grey, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(grey, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }
}
