package process;

import org.junit.Test;

import java.util.ArrayList;


import imageprocess.ProcessExposure;
import img.Image;
import img.ImageImpl;
import img.Pixel;


import static org.junit.Assert.assertEquals;

/**
 * Test to show that the exposure process accurately displays a brightened/darkened image.
 */
public class ProcessExposureTest {

  /**
   * Test for a brightened image.
   */
  @Test
  public void executeBrightenTest() {

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

    // initialize a process w/ the image
    ProcessExposure brighten = new ProcessExposure(4, img);
    Image img2 = brighten.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(i + 4, img2.getPixels().get(i).get(j).getRed());
        assertEquals(j + 4, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(i + j + 4, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }

  /**
   * Test for a darkened image.
   */
  @Test
  public void executeDarkenTest() {

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(100, 100, 100));
      }
      pixels.add(pix);
    }

    // initialize an image w/ the array
    Image img = new ImageImpl(8, 4, 4, pixels);

    // initialize a process w/ the image
    ProcessExposure darken = new ProcessExposure(-4, img);
    Image img2 = darken.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(96, img2.getPixels().get(i).get(j).getRed());
        assertEquals(96, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(96, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }
}