package cs5004.animator.model;

import java.util.ArrayList;

/**
 *  This concrete ChangeColorAnimation class represents changing the shape's color in the
 *  animation. This class extends the AnimationTypeAbs class and inherits all of the appropriate
 *  properties from the superclass.
 */
public class ChangeColorAnimation extends AnimationTypeAbs {

  /**
   * Changes the color of a shape.
   *
   * @param shapeName the name of a shape (i.e., R for rectangle and C for Oval)
   * @param shapeType the type of a shape (i.e., rectangle or oval)
   * @param fromRed   the original red code of the shape
   * @param fromGreen the original green code of the shape
   * @param fromBlue  the original blue code of the shape
   * @param toRed     the new red code of the shape
   * @param toGreen   the new green code of the shape
   * @param toBlue    the new blue code of the shape
   * @param fromTime  the starting time of the color changing process
   * @param toTime    the ending time of the color changing process
   */
  public ChangeColorAnimation(String shapeName, String shapeType, int fromRed, int fromGreen,
      int fromBlue, int toRed, int toGreen, int toBlue, double fromTime, double toTime) {
    super(shapeName, shapeType, fromRed, fromGreen, fromBlue, toRed, toGreen, toBlue, fromTime,
        toTime);
  }
  
  @Override
  public String toString() {
    return String.format(
        "Shape %s changes color from (%.0f,%.0f,%.0f) "
            + "to (%.0f,%.0f,%.0f) from t=%.0f to t=%.0f\n\n",
        this.shapeName, this.fromDimensionOne, this.fromDimensionTwo, this.fromDimensionThree,
        this.toDimensionOne, this.toDimensionTwo, this.toDimensionThree, this.fromTime,
        this.toTime);
  }
  
  @Override
  public ArrayList<double []> calculateFrames() {
    int numOfFrames = (int) (toTime - fromTime + 1);
    ArrayList<double []> frameList = new ArrayList<>();

    for (int i = 0; i < numOfFrames; i++) {
      double newRed = calculateTween(fromTime, toTime, fromTime + i,
              fromDimensionOne, toDimensionOne);
      double newGreen = calculateTween(fromTime, toTime, fromTime + i,
              fromDimensionTwo, toDimensionTwo);
      double newBlue = calculateTween(fromTime, toTime, fromTime + i,
              fromDimensionThree, toDimensionThree);
      frameList.add(new double[]{newRed, newGreen, newBlue});
    }
    return frameList;
  }
}
