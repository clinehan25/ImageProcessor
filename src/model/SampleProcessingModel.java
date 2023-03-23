package model;

import java.util.HashMap;
import java.util.Map;


import img.Image;

/**
 * Represents the implementation of an image processor.
 */
public class SampleProcessingModel implements IProcessingModel {

  public Map<String, Image> gallery;

  /**
   * Constructor for this processor.
   */
  public SampleProcessingModel() {
    this.gallery = new HashMap<String, Image>();
  }

  @Override
  public void addImage(String filepath, Image img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException("Cannot add a null image");
    }
    if (filepath == null) {
      throw new IllegalArgumentException("Cannot add image without description");
    }
    this.gallery.put(filepath, img);
  }

  @Override
  public Image getImage(String filepath) throws IllegalArgumentException {
    if (filepath == null) {
      throw new IllegalArgumentException("Location for image not found");
    }
    return this.gallery.get(filepath);
  }
}
