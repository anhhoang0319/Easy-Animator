package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;

/**
 * The draw animation class produces the graphics output of each frame.
 */
public class DrawAnimation<ShapeAbs> extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 2953044783292396015L;
  protected int frameCount = 0;
  protected ArrayList<ArrayList<ShapeAbs>> frameList;

  public DrawAnimation(ArrayList<ArrayList<ShapeAbs>> frames) {
    this.frameList = frames;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.black);
    if (frameCount == frameList.size()) {
      return;
    }

    ArrayList<ShapeAbs> frame = frameList.get(frameCount++);
    for (ShapeAbs shape : frame) {
      if (shape instanceof Oval) {
        drawOval((Oval) shape, g);
      } else if (shape instanceof Rectangle) {
        drawRect((Rectangle) shape, g);
      }
    }
  }

  /**
   * This private method is used to draw a rectangle given certain parameters.
   *
   * @param shape the rectangle shape being passed in
   * @param g     the graphics object
   */
  private void drawRect(Rectangle shape, Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color((int) shape.getColorCode()[0],
            (int) shape.getColorCode()[1],
            (int) shape.getColorCode()[2]));
    g2d.fill(new Rectangle2D.Double(shape.getX(), shape.getY(),
            shape.getWidth(),
            shape.getHeight()));
  }

  /**
   * This private method is used to draw an oval given certain parameters.
   *
   * @param shape the oval shape being passed in
   * @param g     the graphics object
   */
  private void drawOval(Oval shape, Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color((int) shape.getColorCode()[0],
            (int) shape.getColorCode()[1], (int) shape.getColorCode()[2]));
    g2d.fill(new Ellipse2D.Double(shape.getX(), shape.getY(),
            shape.getXRadius(), shape.getYRadius()));
  }
}
