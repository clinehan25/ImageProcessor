package process;

import org.junit.Test;

import java.util.ArrayList;

import imageprocess.ProcessToGreen;
import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the ToGreen process accurately makes the image green.
 */
public class ProcessToGreenTest {

  @Test
  public void executeTest() {

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
    ProcessToGreen toGreen = new ProcessToGreen(img);
    Image img2 = toGreen.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(0, img2.getPixels().get(i).get(j).getRed());
        assertEquals(img.getPixels().get(i).get(j).getGreen(),
                img2.getPixels().get(i).get(j).getGreen());
        assertEquals(0, img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }
}
