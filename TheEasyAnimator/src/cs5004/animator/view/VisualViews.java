package cs5004.animator.view;

import java.awt.*;

import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;

public interface VisualViews {

  void display() throws InterruptedException;

  void drawOval(Oval shape, Graphics g);
  void drawRect(Rectangle shape, Graphics g);

}
