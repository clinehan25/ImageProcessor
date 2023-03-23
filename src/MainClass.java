import java.io.FileNotFoundException;
import controller.GUIController;
import controller.ImageController;
import model.SampleProcessingModel;
import view.GUIReadView;


/**
 * Main method class.
 */
public class MainClass {

  /**
   * Main method.
   *
   * @param args args.
   * @throws FileNotFoundException if file doesn't exist.
   */
  public static void main(String[] args) throws FileNotFoundException {

    // setting up background
    SampleProcessingModel model = new SampleProcessingModel();
    GUIReadView view = new GUIReadView(model, "Image Processor");

    ImageController controller = new ImageController(model, view);
    GUIController guiController = new GUIController(model, view, controller);

    guiController.start();
  }
}
