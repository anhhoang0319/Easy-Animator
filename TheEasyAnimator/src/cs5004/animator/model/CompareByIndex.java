package cs5004.animator.model;

import java.util.Comparator;

/**
 * This class is created to be used as a comparator to sort the indices of the shapes.
 */
public class CompareByIndex implements Comparator<ShapeAbs> {

  @Override
  public int compare(ShapeAbs shape1, ShapeAbs shape2) {
    if (shape1.getShapeIndex() > shape2.getShapeIndex()) {
      return 1;
    } else if (shape1.getShapeIndex() < shape2.getShapeIndex()) {
      return -1;
    } else {
      return 0;
    }
  }
}
