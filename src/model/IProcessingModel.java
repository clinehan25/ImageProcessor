package model;


import img.Image;

/**
 * This interface represents the operations offered by the image processing
 * model. One object of the model is one image processor.
 */
public interface IProcessingModel {

  /**
   * Method that adds the given image to the gallery with the filepath string value.
   *
   * @param filepath representing the name/path.
   * @param img      representing the image object.
   * @throws IllegalArgumentException if the parameters are null.
   */
  void addImage(String filepath, Image img) throws IllegalArgumentException;

  /**
   * Retrieves the image filepath.
   *
   * @param filepath representing the location of the image object.
   * @return the provided image object.
   * @throws IllegalArgumentException if the filepath doesn't exist.
   */
  Image getImage(String filepath) throws IllegalArgumentException;
}
