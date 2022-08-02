package cs5004.animator.model;

/**
 * This concrete AddShapeAnimation class represents adding a shape that will be used in the
 * animation. This class extends the AnimationTypeAbs class and inherits all of the appropriate
 * properties from the superclass.
 */
public class AddShapeAnimation extends AnimationTypeAbs {

  /**
   * Add a shape to an animation.
   *
   * @param shapeName     the name of a shape (i.e., R for rectangle and C for Oval)
   * @param shapeType     the type of the shape (i.e., rectangle or oval)
   * @param shape         the shape (object) to be added
   * @param appearTime    the time the shape starts to exist
   * @param disappearTime the time the shape ceases to exist
   */
  public AddShapeAnimation(String shapeName, String shapeType, ShapeAbs shape, double appearTime,
                           double disappearTime) {

    super(shapeName, shapeType, shape, appearTime, disappearTime);
  }

  @Override
  public String toString() {
    String str = String.format("Shape:\nName: %s\n", this.shapeName);
    str += this.shape.toString();
    str += String.format("Appears at t=%.0f\n"
                    + "Disappears at t=%.0f\n\n",
            this.fromTime, this.toTime);
    return str;
  }
}
