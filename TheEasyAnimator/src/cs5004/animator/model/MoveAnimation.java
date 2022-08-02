package cs5004.animator.model;

/**
 *  This concrete MoveAnimation class represents moving a shape in the animation. This class
 *  extends the AnimationTypeAbs class and inherits all of the appropriate properties from the
 *  superclass.
 */
public class MoveAnimation extends AnimationTypeAbs {

  /**
   * Move a shape.
   *
   * @param shapeName the name of a shape (i.e., R for rectangle and C for Oval)
   * @param shapeType the type of a shape (i.e., rectangle or oval)
   * @param fromX     the original x-coordinate of the shape
   * @param fromY     the original y-coordinate of the shape
   * @param toX       the x-coordinate of the shape to be moved to
   * @param toY       the y-coordinate of the shape to be moved to
   * @param fromTime  the staring time of the movement
   * @param toTime    the ending time of the movement
   */
  public MoveAnimation(String shapeName, String shapeType, double fromX, double fromY, double toX,
                       double toY, double fromTime, double toTime) {
    super(shapeName, shapeType, fromX, fromY, toX, toY, fromTime, toTime);
  }
  
  @Override
  public String toString() {
    return String.format("Shape %s moves from (%.0f,%.0f) to (%.0f,%.0f) "
        + "from t=%.0f to t=%.0f\n",
        this.shapeName, this.fromDimensionOne, this.fromDimensionTwo, this.toDimensionOne,
        this.toDimensionTwo, this.fromTime, this.toTime);
  }
}
