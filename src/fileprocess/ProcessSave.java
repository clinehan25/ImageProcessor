package fileprocess;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import img.Image;

/**
 * A process class that is used to save the provided image.
 */
public class ProcessSave {
  private final Image img;

  public ProcessSave(Image img) {
    this.img = img;
  }

  /**
   * Method that saves the image provided a path.
   *
   * @param path representing the filepath of this image.
   */
  public void execute(String path) throws IOException {
    if (!path.endsWith(".ppm")
            && !path.endsWith(".bmp")
            && !path.endsWith(".png")
            && !path.endsWith(".jpg")) {
      throw new IllegalArgumentException("File must end with an image extension");
    }
    if (path.endsWith(".ppm")) {
      FileImageOutputStream fs = new FileImageOutputStream(new File(path));

      fs.write(("P3\n" + this.img.getWidth() + " "
              + this.img.getHeight() + "\n" + this.img.getValue() + "\n").getBytes());
      for (int i = 0; i < this.img.getHeight(); i++) {
        for (int j = 0; j < this.img.getWidth(); j++) {
          fs.write((this.img.getPixels().get(i).get(j).getRed() + "\n").getBytes());
          fs.write((this.img.getPixels().get(i).get(j).getGreen() + "\n").getBytes());
          fs.write((this.img.getPixels().get(i).get(j).getBlue() + "\n").getBytes());
        }
      }
    } else {
      BufferedImage bImg = new BufferedImage(img.getWidth(), img.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      int r = 0;
      int b = 0;
      int g = 0;
      for (int i = 0; i < bImg.getHeight(); i++) {
        for (int j = 0; j < bImg.getWidth(); j++) {
          r = this.img.getPixels().get(i).get(j).getRed();
          g = this.img.getPixels().get(i).get(j).getGreen();
          b = this.img.getPixels().get(i).get(j).getBlue();
          bImg.setRGB(i, j, new Color(r, g, b).getRGB());
        }
      }
      ImageIO.write(bImg, path.substring(path.indexOf(".")), new File(path));
    }
  }
}

    // OLD METHOD
    /*
    String[] fullPath = path.split("\\.");
    String nameAndFormat = Arrays.stream(fullPath)
            .reduce((first, second) -> second)
            .orElse("no filename on image");
    if (nameAndFormat.equals("no filename on image")) {
      throw new IllegalArgumentException("image has no filename");
    } else {
      try {
        executeHelper(this.img, nameAndFormat, path);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void executeHelper(Image img, String nameAndFormat, String path) throws
          IllegalArgumentException, IOException {
    if (nameAndFormat.contains("ppm")) {
      FilterOutputStream saveStream = new FilterOutputStream(new FileOutputStream(path));
      StringBuilder rgbFormat = new StringBuilder();
      for (int i = 0; i < img.getHeight(); i++) {
        for (int j = 0; j < img.getWidth(); j++) {
          //Iterate through pixels in provided img and append them in rgb format to StringBuilder.
          Pixel pixelToAppend = img.getPixels().get(i).get(j);
          rgbFormat.append("(")
                  .append(pixelToAppend.getRed())
                  .append("), (")
                  .append(pixelToAppend.getGreen())
                  .append("), (")
                  .append(pixelToAppend.getBlue())
                  .append(")");
        }
      }
      //Encode string to bytes and use in StringBuilder object
      String systemOutput = "Width of saved image: " + img.getWidth() + "\n" +
              "Height of saved image: " + img.getHeight() + " \n" +
              "Maximum value of a color in this file:" + img.getValue() + "\n " +
              "RGB representation of image color:" + rgbFormat;
      Charset charset = StandardCharsets.US_ASCII;
      byte[] fileByteArray = charset.encode(systemOutput).array();
      saveStream.write(fileByteArray);
      saveStream.close();
      System.out.println("Successfully Saved");
    } else {
      // TODO: Not very flexible, will have to rework with switch-case, if non-ppm format introduced
      throw new IllegalArgumentException("Provided wrong format.");
    }
  }
}*/
