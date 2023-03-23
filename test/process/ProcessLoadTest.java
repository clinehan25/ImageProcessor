package process;

import org.junit.Test;

import java.io.FileNotFoundException;

import fileprocess.ProcessLoad;
import img.Image;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the load process accurately displays loads an image to gallery.
 */
public class ProcessLoadTest {

  @Test
  public void executeTest() throws FileNotFoundException {

    ProcessLoad load = new ProcessLoad();
    Image img = load.execute("res/Koala.ppm");
    assertEquals(1024, img.getWidth());
    assertEquals(768, img.getHeight());
    assertEquals(255, img.getValue());
  }
}