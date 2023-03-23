package process;

import org.junit.Test;

import java.util.ArrayList;

import imageprocess.ProcessToBlue;
import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the ToBlue process accurately makes the image blue.
 */
public class ProcessToBlueTest {

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
    ProcessToBlue toBlue = new ProcessToBlue(img);
    Image img2 = toBlue.execute();

    // tests
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(0, img2.getPixels().get(i).get(j).getRed());
        assertEquals(0, img2.getPixels().get(i).get(j).getGreen());
        assertEquals(img.getPixels().get(i).get(j).getBlue(),
                img2.getPixels().get(i).get(j).getBlue());
      }
    }
  }
}
