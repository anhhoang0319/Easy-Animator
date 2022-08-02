package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements the interface of animation model.
 * @author Anh Hoang
 */
public class AnimationModelImpl implements AnimationModel {

  protected ArrayList<AnimationTypeAbs> animationList;
  protected HashMap<String, ArrayList<ShapeAbs>> animationMap;
  protected ArrayList<ArrayList<ShapeAbs>> frameList;
  double endTime;
  int[] canvasDimensions;
  
  /**
   * Class constructor - creating an array list that stores different types of animation.
   */
  public AnimationModelImpl() {
    this.animationList = new ArrayList<AnimationTypeAbs>();
    this.animationMap = new HashMap<String, ArrayList<ShapeAbs>>();
    this.endTime = 0;
    canvasDimensions = new int[4];
  }
  
  /**
   * Sort the animation array list based on each animation's starting time. The one with smaller
   * time value should come before the one with bigger time value.
   */
  private void sortAnimations() {
    animationList.sort(new FromTimeComparator());
  }
  
  /**
   * Check the validity of the animation (based on existential status of the shape and time frame
   * matching between the shape and the animation).
   *
   * @param shapeName the name of the shape (i.e., R for rectangle and C for Oval)
   * @param shapeType the type of the shape (i.e., rectangle or oval)
   * @param fromTime  the animation's starting time
   * @param toTime    the animation's ending time
   * @return a boolean value that states whether or not the move being added is value
   */
  private boolean isValidAnimation(String shapeName, String shapeType, double fromTime,
                                   double toTime) {

    for (AnimationTypeAbs current : animationList) {
      if (current.shapeName.equals(shapeName) && current.shapeType.equals(shapeType)
              && fromTime < toTime && fromTime >= current.fromTime && toTime <= current.toTime
              && current.animationType.equals("addShape")) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks the to see if there is an animation conflict so that there are no contradictory moves.
   *
   * @param animation the animation that is going to be added to the array
   * @return a boolean value that states whether or not there is a conflict
   */
  private boolean animationConflict(AnimationTypeAbs animation) {
    for (AnimationTypeAbs current : animationList) {
      if (current.shapeName.equalsIgnoreCase(animation.shapeName)
              && current.shapeType.equalsIgnoreCase(animation.shapeType)
              && current.getAnimationType().equalsIgnoreCase(animation.animationType)
              && ((animation.fromTime >= current.fromTime
              && animation.fromTime <= current.toTime)
              || (current.fromTime >= animation.fromTime
              && current.fromTime <= animation.toTime))) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void addShape(int shapeIndex, String shapeName, String shapeType, double startX,
                       double startY, double dimensionOne, double dimensionTwo,
                       ShapeColor shapeColorObj, double startTime, double endTime)
                       throws IllegalArgumentException {

    if (shapeType.equalsIgnoreCase("rectangle")) {
      animationList.add(new AddShapeAnimation(shapeName, shapeType, new Rectangle(shapeIndex,
              shapeName, startX, startY, shapeColorObj, dimensionOne, dimensionTwo),
              startTime, endTime));
    } else if (shapeType.equalsIgnoreCase("oval")) {
      animationList.add(new AddShapeAnimation(shapeName, shapeType, new Oval(shapeIndex, shapeName,
              startX, startY, shapeColorObj, dimensionOne, dimensionTwo), startTime, endTime));
    } else {
      throw new IllegalArgumentException("Unsupported shape type being added.");
    }

    animationMap.put(shapeName + shapeType, new ArrayList<ShapeAbs>());
    sortAnimations();
    if (endTime > this.endTime) {
      this.endTime = endTime;
    }

  }

  @Override
  public void addMove(String shapeName, String shapeType, double fromX, double fromY, double toX,
                      double toY, double fromTime, double toTime) throws IllegalArgumentException {

    if (!isValidAnimation(shapeName, shapeType, fromTime, toTime)) {
      throw new IllegalArgumentException("Invalid move animation attempted.");
    }

    AnimationTypeAbs animation = new MoveAnimation(shapeName, shapeType, fromX, fromY, toX, toY,
            fromTime, toTime);

    animationList.add(animation);
    sortAnimations();
  }

  @Override
  public void changeColor(String shapeName, String shapeType, ShapeColor fromShapeColor,
                          ShapeColor toShapeColor, double fromTime, double toTime)
                          throws IllegalArgumentException {

    if (!isValidAnimation(shapeName, shapeType, fromTime, toTime)) {
      throw new IllegalArgumentException("Invalid color change animation attempted.");
    }

    AnimationTypeAbs animation = new ChangeColorAnimation(shapeName, shapeType,
            fromShapeColor.redCode, fromShapeColor.greenCode, fromShapeColor.blueCode,
            toShapeColor.redCode, toShapeColor.greenCode, toShapeColor.blueCode, fromTime, toTime);

    if (animationConflict(animation)) {
      throw new IllegalArgumentException("Cannot add contradictory color animation.");
    }
    animationList.add(animation);
    sortAnimations();

  }

  @Override
  public void resize(String shapeName, String shapeType, double fromSize1, double fromSize2,
                     double toSize1, double toSize2, double fromTime, double toTime,
                     boolean resize)
          throws IllegalArgumentException {

    if (!isValidAnimation(shapeName, shapeType, fromTime, toTime)) {
      throw new IllegalArgumentException("Invalid size change animation attempted.");
    }

    AnimationTypeAbs animation = new ChangeSizeAnimation(shapeName, shapeType, fromSize1, fromSize2,
            toSize1, toSize2, fromTime, toTime, true);

    if (animationConflict(animation)) {
      throw new IllegalArgumentException("Cannot add contradictory resize animation.");
    }

    animationList.add(animation);
    sortAnimations();

  }

  @Override
  public String playAnimation() {
    StringBuilder str = new StringBuilder();
    for (AnimationTypeAbs current : animationList) {
      str.append(current.toString());
    }
    return str.toString();
  }

  @Override
  public ArrayList<ArrayList<ShapeAbs>> convertToFrames() {
    this.frameList = new ArrayList<ArrayList<ShapeAbs>>();
    int numOfFrames = (int) this.endTime;

    // Converts the animationList into a hashmap of objects and their independent timelines.
    for (AnimationTypeAbs animation : animationList) {
      String identifier = animation.shapeName + animation.shapeType;
      ArrayList<double[]> shapeFrameList = animation.calculateFrames();

      // Places the particular shape in the exact positions within the timeline in the hashmap.
      // For example, if R1 has a appears time of 5, and disappears time of 10, there will exist
      // an R1 in the indices of 4 - 9.
      if (animation.animationType.equalsIgnoreCase("addShape")) {
        for (int j = 0; j < numOfFrames; j++) {
          if (j < animation.fromTime || j > animation.toTime) {
            animationMap.get(identifier).add(null);
          } else {
            animationMap.get(identifier).add(animation.shape.clone());
          }
        }
      }

      // If it is a move animation, the program takes the x and y coordinate, which is the tweened
      // x & y and updates the corresponding shape position at a given time frame.
      if (animation.animationType.equalsIgnoreCase("move")) {
        for (int j = (int) animation.fromTime; j < endTime; j++) {
          double[] shapeFrame;
          if (animationMap.get(identifier).get(j) != null) {
            if (j >= (int) animation.toTime) {
              shapeFrame = shapeFrameList.get((int) animation.toTime - (int) animation.fromTime);
            } else {
              shapeFrame = shapeFrameList.get(j - (int) animation.fromTime);
            }
            animationMap.get(identifier).get(j).xCor = shapeFrame[0];
            animationMap.get(identifier).get(j).yCor = shapeFrame[1];
          }
        }
      }

      // If it is a color change, the program takes the tweened red, green, blue code and updates
      // the corresponding shape color at a given time frame.
      if (animation.animationType.equalsIgnoreCase("changeColor")) {
        for (int j = (int) animation.fromTime; j < endTime; j++) {
          double[] shapeFrame;
          if (animationMap.get(identifier).get(j) != null) {
            if (j >= (int) animation.toTime) {
              shapeFrame = shapeFrameList.get((int) animation.toTime - (int) animation.fromTime);
            } else {
              shapeFrame = shapeFrameList.get(j - (int) animation.fromTime);
            }
            animationMap.get(identifier).get(j).shapeColor.redCode = (int) shapeFrame[0];
            animationMap.get(identifier).get(j).shapeColor.greenCode = (int) shapeFrame[1];
            animationMap.get(identifier).get(j).shapeColor.blueCode = (int) shapeFrame[2];
          }
        }
      }

      // If it is a size change, the program takes the tweened dimension one and dimension two
      // (i.e Rectangle's height & width or Oval's xRadius & yRadius) and updates
      // the corresponding shape size at a given time frame.
      if (animation.animationType.equalsIgnoreCase("changeSize")) {
        for (int j = (int) animation.fromTime; j < endTime; j++) {
          double[] shapeFrame;
          if (animationMap.get(identifier).get(j) != null) {
            if (j >= (int) animation.toTime) {
              shapeFrame = shapeFrameList.get((int) animation.toTime - (int) animation.fromTime);
            } else {
              shapeFrame = shapeFrameList.get(j - (int) animation.fromTime);
            }
            animationMap.get(identifier).get(j).dimensionOne = shapeFrame[0];
            animationMap.get(identifier).get(j).dimensionTwo = shapeFrame[1];
          }
        }
      }
    }

    // Create a list of timeframes where each frame contains a list of shapes and their properties
    // at the time of that particular frame.
    ArrayList<String> keys = new ArrayList<String>(animationMap.keySet());

    for (int i = 0; i < numOfFrames; i++) {
      frameList.add(new ArrayList<ShapeAbs>());
      for (String key : keys) {
        if (animationMap.get(key).get(i) != null) {
          frameList.get(i).add(animationMap.get(key).get(i));
        }
      }
      frameList.get(i).sort(new CompareByIndex());
    }
    return frameList;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.canvasDimensions = new int[] {x, y, width, height};
  }

  @Override
  public int[] getBounds() {
    return this.canvasDimensions;
  }

  @Override
  public ArrayList<HashMap<String, String>> getStringMapList() {
    ArrayList<HashMap<String, String>> stringMapList = new ArrayList<>();
    for (AnimationTypeAbs animationType : this.animationList) {
      HashMap<String, String> stringMap = animationType.getStringParameters();
      stringMapList.add(stringMap);
    }
    return stringMapList;
  }

  @Override
  public ArrayList<HashMap<String, Double>> getDoubleMapList() {
    ArrayList<HashMap<String, Double>> doubleMapList = new ArrayList<>();
    for (AnimationTypeAbs animationType : this.animationList) {
      HashMap<String, Double> doubleMap = animationType.getDoubleParameters();
      doubleMapList.add(doubleMap);
    }
    return doubleMapList;
  }

}
