package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class that represents animation types (i.e., add a shape, move a
 * shape, change the color of a shape, and resize a shape).
 * @author Anh Hoang
 */
public abstract class AnimationTypeAbs {

  protected ShapeAbs shape;
  protected String shapeName;
  protected String shapeType;
  
  protected String animationType;
  protected double fromDimensionOne;
  protected double toDimensionOne;
  protected double fromDimensionTwo;
  protected double toDimensionTwo;
  protected double fromDimensionThree;
  protected double toDimensionThree;
  protected double fromTime;
  protected double toTime;
  
  /**
   * This constructor represents the construction of a shape in the animation.
   *
   * @param shape         the shape (object) to be added
   * @param shapeName     the name of a shape (i.e., R for rectangle and C for Oval)
   * @param shapeType     the type of the shape (i.e., rectangle or oval)
   * @param appearTime    the time the shape starts to appear
   * @param disappearTime the time the shape disappear
   */
  public AnimationTypeAbs(String shapeName, String shapeType, ShapeAbs shape, double appearTime,
                          double disappearTime) {
    this.shape = shape;
    this.shapeType = shapeType;
    this.shapeName = shapeName;
    this.fromTime = appearTime;
    this.toTime = disappearTime;
    this.animationType = "addShape";
  }
  
  /**
   * This constructor represents the animation of moving a shape in the animation.
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
  public AnimationTypeAbs(String shapeName, String shapeType, double fromX, double fromY,
                          double toX, double toY, double fromTime, double toTime) {
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.fromDimensionOne = fromX;
    this.toDimensionOne = toX;
    this.fromDimensionTwo = fromY;
    this.toDimensionTwo = toY;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.animationType = "move";
  }
  
  /**
   * This constructor represents the animation of changing a shape color in the animation.
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
  public AnimationTypeAbs(String shapeName, String shapeType, int fromRed, int fromGreen,
                          int fromBlue, int toRed, int toGreen, int toBlue,
                          double fromTime, double toTime) {
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.fromDimensionOne = fromRed;
    this.toDimensionOne = toRed;
    this.fromDimensionTwo = fromGreen;
    this.toDimensionTwo = toGreen;
    this.fromDimensionThree = fromBlue;
    this.toDimensionThree = toBlue;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.animationType = "changeColor";
  }
  
  /**
   * This constructor represents the animation of changing a shape size in the
   * animation.
   *
   * @param shapeName the name of the shape (i.e., R for rectangle and C for oval)
   * @param shapeType the type of the shape (i.e., rectangle or oval)
   * @param fromSize1 original dimension 1 of the shape (i.e., height for
   *                  rectangle or x-radius for oval)
   * @param fromSize2 original dimension 2 of the shape (i.e., width for rectangle
   *                  or y-radius for oval)
   * @param toSize1   the new dimension 1 of the shape (i.e., height for rectangle
   *                  or x-radius for oval)
   * @param toSize2   the new dimension 2 of the shape (i.e., width for rectangle
   *                  or y-radius for oval)
   * @param fromTime  the starting time of the change process
   * @param toTime    the ending time of the change process
   * @param resize    whether this function is resizing shape (always true)
   */
  public AnimationTypeAbs(String shapeName, String shapeType, double fromSize1, double fromSize2,
      double toSize1, double toSize2, double fromTime, double toTime, boolean resize) {
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.fromDimensionOne = fromSize1;
    this.fromDimensionTwo = fromSize2;
    this.toDimensionOne = toSize1;
    this.toDimensionTwo = toSize2;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.animationType = "changeSize";
  }
  
  /**
   * Calculates a given attribute at any given time using the tweening equation provided
   * in the specifications of the project.
   *
   * @return Tweening calculated
   */
  public double calculateTween(double startTime, double endTime, double currentTime,
                                   double startX, double endX) {
    return startX * ((endTime - currentTime) / (endTime - startTime))
            + endX * ((currentTime - startTime) / (endTime - startTime));
  }
  
  /**
   * Returns the animation type of the animation: either move, changeSize, changeColor, or addShape.
   *
   * @return the animation type
   */
  public String getAnimationType() {
    return this.animationType;
  }
  
  /**
   * Calculates the entire list of information specifying the values of the shape attributes at
   * each frame in the duration of the animation. The key advantage of this approach is that
   * the code is not storing a list of new shapes in memory, but rather the attributes of defined
   * shapes.
   *
   * @return the new dimensions of the shape's attributes, which vary depending on the type of
   *         animation type
   */
  public ArrayList<double []> calculateFrames() {
    int numOfFrames = (int) (toTime - fromTime + 1);
    ArrayList<double []> frameList = new ArrayList<>();

    for (int i = 0; i < numOfFrames; i++) {
      double newDimension1 = calculateTween(fromTime, toTime, fromTime + i,
              fromDimensionOne, toDimensionOne);
      double newDimension2 = calculateTween(fromTime, toTime, fromTime + i,
              fromDimensionTwo, toDimensionTwo);
      frameList.add(new double[]{newDimension1, newDimension2});
    }
    return frameList;
  }
  
  /**
   * Puts all of the shape string attributes into the values and the shape
   * index as the key. This is used for rendering the SVG view.
   * @return a hashmap of all string attributes of a shape.
   */
  public HashMap<String, String> getStringParameters(){
    HashMap<String, String> stringMap = new HashMap<>();
    stringMap.put("shapeName", this.shapeName);
    stringMap.put("shapeType", this.shapeType);
    stringMap.put("animationType", this.animationType);
    if (this.animationType.equals("addShape")) {
      stringMap.put("shapeIndex", String.valueOf(this.shape.getShapeIndex()));
    }
    return stringMap;
  }
  
  /**
   * Puts all of the shape integral/double attributes into the values and the
   * shape index as the key. This is used for rendering the SVG view.
   * @return a hashmap of all integral/double attributes of a shape.
   */
  public HashMap<String, Double> getDoubleParameters() {
    HashMap<String, Double> doubleMap = new HashMap<>();
    doubleMap.put("fromDimensionOne", this.fromDimensionOne);
    doubleMap.put("toDimensionOne", this.toDimensionOne);
    doubleMap.put("fromDimensionTwo", this.fromDimensionTwo);
    doubleMap.put("toDimensionTwo", this.toDimensionTwo);
    doubleMap.put("fromDimensionThree", this.fromDimensionTwo);
    doubleMap.put("toDimensionThree", this.toDimensionThree);
    doubleMap.put("fromTime", this.fromTime);
    doubleMap.put("toTime", this.toTime);
    
    if (this.animationType.equals("addShape")) {
      doubleMap.put("startingX", this.shape.xCor);
      doubleMap.put("startingY", this.shape.yCor);
      doubleMap.put("startingColorRed", this.shape.getColorCode()[0]);
      doubleMap.put("startingColorGreen", this.shape.getColorCode()[1]);
      doubleMap.put("startingColorBlue", this.shape.getColorCode()[2]);
      if (this.shapeType.equalsIgnoreCase("Rectangle")) {
        doubleMap.put("startingHeight", ((Rectangle) this.shape).getHeight());
        doubleMap.put("startingWidth", ((Rectangle) this.shape).getWidth());
      }
      else {
        doubleMap.put("startingXRadius", ((Oval) this.shape).getXRadius());
        doubleMap.put("startingYRadius", ((Oval) this.shape).getYRadius());
      }
    }

    return doubleMap;
  }
}
