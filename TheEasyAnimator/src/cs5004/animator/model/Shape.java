package cs5004.animator.model;

/**
 * This interface represents a single shape that will go in an animation, which is comprised
 * of multiple shapes of different features.
 */
public interface Shape {

  /**
   * Returns the current x coordinate of where the shape is. The x coordinate for
   * the shapes vary depending on the type of shape. A rectangle's x value
   * represents the bottom-left edge of the rectangle. The ovals' x value
   * represents the x-coordinate of the center of the oval.
   *
   * @return the x coordinate of the shape
   */
  double getX();
  
  /**
   * Returns the current y coordinate of where the shape is. The y coordinate for
   * the shapes vary depending on the type of shape. A rectangle's y value
   * represents the bottom-left edge of the rectangle. The ovals' y value
   * represents the y-coordinate of the center the oval.
   *
   * @return the y coordinate of the shape
   */
  double getY();
  
  /**
   * Returns the area of the shape. The equation for the area of the shape differs
   * based on what kind of shape it is. Each equation is defined in the shapes
   * concrete class.
   *
   * @return the total area of the shape
   */
  double getArea();
  
  /**
   * Get the color of a shape in the combining of red code, green code, blue code.
   * @return the color of the shape in String format
   */
  String getShapeColor();
  
  /**
   * Represents a shape (i.e., shape name, shape type, x-coordinate,
   * y-coordinate, dimensions, scales, and color) to a string.
   * @return the information of the shape as a string
   */
  String toString();
  
  /**
   * Gets the name of the shape.
   * @return the name of the shape
   */
  String getShapeName();
  
  /**
   * Gets the index of the shape. The index of the shape is the number in which it
   * has been added to the animation.
   * 
   * @return the index of the shape
   */
  int getShapeIndex();
  
  /**
   * Gets the RGB values of the shape.
   * @return a double array which is the rgb values of the shape. (ie [0.0, 255.0, 180.0])
   */
  double[] getColorCode();
  
  /**
   * Makes a copy of the shape's attributes.
   * @return a copy of the shape's attributes
   */
  ShapeAbs clone();
}
