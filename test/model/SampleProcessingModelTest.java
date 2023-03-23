package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test Class to show that the addImage/getImage method of SampleProccessingModel work.
 */
public class SampleProcessingModelTest {

  /**
   * Test to make sure addImage adds the image to the gallery.
   */
  @Test
  public void addImageTest() {
    SampleProcessingModel m1 = new SampleProcessingModel();

    // for null test
    Image imgNull = null;

    // empty map
    Map<String, Image> mapEmpty = new HashMap<>();

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }
    Image img = new ImageImpl(6, 4, 4, pixels);

    // tests
    assertThrows(IllegalArgumentException.class, () -> m1.addImage("", imgNull));
    assertEquals(mapEmpty, m1.gallery);
    m1.addImage("Image1", img);
    assertEquals(m1.gallery.get("Image1"), img);


  }

  /**
   * Test to make sure getImage accurately gets the location of the image in the gallery.
   */
  @Test
  public void getImageTest() {
    SampleProcessingModel m1 = new SampleProcessingModel();

    // initialize an array of pixels
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }
    Image img = new ImageImpl(6, 4, 4, pixels);

    m1.addImage("Image1", img);

    assertThrows(IllegalArgumentException.class, () -> m1.getImage(null));
    assertEquals(img, m1.getImage("Image1"));
  }
}