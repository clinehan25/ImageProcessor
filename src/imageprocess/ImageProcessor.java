package imageprocess;

import img.Image;

/**
 * This interface refers to the image processor used on a model(image) in the gallery.
 */
public interface ImageProcessor {

  /**
   * A method that executes an operation in the Processor.
   *
   * @return A new image after having performed an operation.
   */
  Image execute();
}
