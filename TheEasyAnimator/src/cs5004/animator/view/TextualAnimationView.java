package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents a textual view that outputs animation in plain English language.
 * If the name of an output file is specified, the animation text is saved to a txt file.
 * Otherwise the animation text is printed to the system screen.
 */
public class TextualAnimationView implements AnimationView {
  String data;
  String outfileName;
  String speed;

  /**
   * The class constructor of the textual view.
   *
   * @param data        the animation data from the model to be displayed
   * @param outfileName the name of the file to be saved
   * @param speed       animation play speed (i.e., number of ticks per second)
   * @throws IllegalArgumentException if the data is null
   */
  public TextualAnimationView(String data, String outfileName, String speed)
          throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null.");
    }

    this.outfileName = outfileName;
    this.speed = speed;

    String speedMsg = String.format("Animation Speed: %s tick(s) per second.\n\n", this.speed);
    this.data = (speedMsg + data);
  }

  /**
   * Displays the animation in text (either by printing or saving to a txt file).
   *
   * @throws IOException if the output file cannot be written.
   */
  @Override
  public void display() throws IOException {

    if (this.outfileName == null) {
      System.out.println(data);
    } else {
      try {
        FileWriter fileWriter = new FileWriter(this.outfileName);
        fileWriter.write(data);
        fileWriter.close();
      } catch (IOException ioe) {
        throw new IOException("Text file writing operation failed.");
      }
    }
  }

  @Override
  public String toString() {
    return this.data;
  }
}
