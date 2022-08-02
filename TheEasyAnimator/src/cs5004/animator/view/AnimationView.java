package cs5004.animator.view;

import java.io.IOException;

/**
 * This interface is the interface that is implemented by all the views.
 */
public interface AnimationView {

  /**
   * Displays the specific view's output. For instance, visual will produce a visual representation
   * of the data, SVG will represent SVG output of the data, and the textual will produce a text
   * output of the data.
   *
   * @throws InterruptedException if the visual view is closed early
   * @throws IOException if the txt or svg file is unable to be written 
   */
  void display() throws InterruptedException, IOException;
}
