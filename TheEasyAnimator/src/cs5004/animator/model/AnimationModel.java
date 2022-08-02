
package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the model of an animation. The model operations include adding a shape to
 * the animation, moving a shape in the animation, as well as changing the color and resizing a
 * shape from the animation.
 * @author Anh Hoang
 */
public interface AnimationModel {

  /**
   * Add a shape to animation array list.
   * 
   * @param shapeIndex    the index when the sahpe is added
   * @param shapeName     the name of the shape
   * @param shapeType     the type of the shape
   * @param startX        the starting x-coordinate of the shape
   * @param startY        the starting y-coordinate of the shape
   * @param dimensionOne  the weight or x-radius of the shape
   * @param dimensionTwo  the height or y-radius of the shape
   * @param shapeColorObj the color of the shape
   * @param startTime     the time that the shape starts to appear
   * @param endTime       the time that the shape starts to appear
   * @throws IllegalArgumentException if the shape to be added is neither
   *                                  rectangle nor oval
   */
  void addShape(int shapeIndex, String shapeName, String shapeType, double startX, double startY,
      double dimensionOne, double dimensionTwo, ShapeColor shapeColorObj, double startTime,
      double endTime) throws IllegalArgumentException;
  
  /**
   * Add a move animation to the animation array list.
   * 
   * @param shapeName the name of the shape (i.e., R for rectangle and O for oval)
   * @param shapeType the type of the shape (i.e., rectangle or oval)
   * @param fromX     the x-coordinate of the shape before the movement
   * @param fromY     the y-coordinate of the shape before the movement
   * @param toX       the x-coordinate of the shape after the movement
   * @param toY       the y-coordinate of the shape after the movement
   * @param fromTime  the starting time of the movement
   * @param toTime    the ending time of the movement
   * @throws IllegalArgumentException if the movement is not valid (checked based
   *                                  on existential status of the shape and time
   *                                  frame matching between the shape and the
   *                                  animation). Additionally, it throws an
   *                                  exception if a contradictory move is trying
   *                                  to be made.
   */
  void addMove(String shapeName, String shapeType, double fromX, double fromY, double toX,
      double toY, double fromTime, double toTime) throws IllegalArgumentException;
  
  /**
   * Adds a change color animation to the animation array list.
   * 
   * @param shapeName      the name of the shape (i.e., R for rectangle and O for
   *                       oval)
   * @param shapeType      the type of the shape (i.e., rectangle or oval)
   * @param fromShapeColor the original color of the shape
   * @param toShapeColor   the color to change to
   * @param fromTime       the starting time of the color changing process
   * @param toTime         the ending time of the color changing process
   * @throws IllegalArgumentException if the movement is not valid (checked based
   *                                  on existential status of the shape and time
   *                                  frame matching between the shape and the
   *                                  animation). Additionally, it throws an
   *                                  exception if a contradictory move is trying
   *                                  to be made.
   */
  void changeColor(String shapeName, String shapeType, ShapeColor fromShapeColor,
      ShapeColor toShapeColor, double fromTime, double toTime) throws IllegalArgumentException;
  
  /**
   * Adds a change size animation to the animation array list.
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
   * @throws IllegalArgumentException if the movement is not valid (checked based
   *                                  on existential status of the shape and time
   *                                  frame matching between the shape and the
   *                                  animation). Additionally, it throws an
   *                                  exception if a contradictory move is trying
   *                                  to be made.
   */
  void resize(String shapeName, String shapeType, double fromSize1, double fromSize2,
      double toSize1, double toSize2, double fromTime, double toTime, boolean resize)
      throws IllegalArgumentException;
  
  /**
   * Generate a log of the overall animation.
   *
   * @return the log of the overall animation as a string
   */
  String playAnimation();
  
  /**
   * This function is used to convert the animation list to frames. This array
   * list of array list ShapeAbs will be used in the visual view. This
   * creates a list of frames, which each contain precalculated shape objects to
   * be displayed in each time frame.
   *
   * @return an array list of array lists of ShapeAbs.
   */
  ArrayList<ArrayList<ShapeAbs>> convertToFrames();
  
  /**
   * A setter function that is used to set the dimensions of the canvas for the
   * visual view.
   * @param x      the xcoordinate of the frame
   * @param y      the y coordinate of the frame
   * @param width  the width of the frame
   * @param height the height
   */
  void setBounds(int x, int y, int width, int height);
  
  /**
   * A getter function that is used to get the dimensions of the canvas for the
   * visual view.
   *
   * @return an int array that has all of the dimensions of the canvas
   */
  int[] getBounds();

  /**
   * This function gets a list of hashmaps that map the names of the shapes to
   * their string attributes; This is used for the SVG view.
   * @return a list of hashmaps
   */
  ArrayList<HashMap<String, String>> getStringMapList();
  
  /**
   * This function gets a list of hashmaps that map the names of the shapes to
   * their integral/double attributes; This is used for the SVG view.
   *
   * @return a list of hashmaps
   */
  ArrayList<HashMap<String, Double>> getDoubleMapList();
}
