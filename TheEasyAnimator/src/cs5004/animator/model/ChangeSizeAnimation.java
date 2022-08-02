package cs5004.animator.model;

/**
 *  This concrete ChangeSizeAnimation class represents changing the size of a shape in the
 *  animation. This class extends the AnimationTypeAbs class and inherits all of the appropriate
 *  properties from the superclass.
 *  @author Anh Hoang
 */
public class ChangeSizeAnimation extends AnimationTypeAbs {

  /**
   * This constructor represents the animation of changing a shape size in the animation.
   *
   * @param shapeName the name of the shape (i.e., R for rectangle and C for oval)
   * @param shapeType the type of the shape (i.e., rectangle or oval)
   * @param fromSize1 original dimension 1 of the shape (i.e., height for rectangle or x-radius for
   *                  oval)
   * @param fromSize2 original dimension 2 of the shape (i.e., width for rectangle or y-radius for
   *                  oval)
   * @param toSize1   the new dimension 1 of the shape (i.e., height for rectangle or x-radius for
   *                  oval)
   * @param toSize2   the new dimension 2 of the shape (i.e., width for rectangle or y-radius for
   *                  oval)
   * @param fromTime  the starting time of the change process
   * @param toTime    the ending time of the change process
   * @param resize    whether this function is resizing shape (always true)
   */

  public ChangeSizeAnimation(String shapeName, String shapeType, double fromSize1, double fromSize2,
                             double toSize1, double toSize2, double fromTime, double toTime,
                             boolean resize) {

    super(shapeName, shapeType, fromSize1, fromSize2, toSize1, toSize2, fromTime, toTime, resize);
  }
  
  @Override
  public String toString() {
    String str;
    if (shapeType.equalsIgnoreCase("rectangle")) {
      str = String.format("Shape %s scales from Width: %.0f Height: %.0f "
                      + "to Width: %.0f Height: %.0f from t=%.0f to t=%.0f\n\n",
              this.shapeName, this.fromDimensionOne, this.fromDimensionTwo,
              this.toDimensionOne, this.toDimensionTwo, this.fromTime, this.toTime);
    }
    else {
      str = String.format("Shape %s scales from X radius: %.0f Y radius: %.0f "
                      + "to X radius: %.0f Y radius: %.0f from t=%.0f to t=%.0f\n\n",
              this.shapeName, this.fromDimensionOne, this.fromDimensionTwo,
              this.toDimensionOne, this.toDimensionTwo, this.fromTime, this.toTime);
    }
    return str;
  }
}
