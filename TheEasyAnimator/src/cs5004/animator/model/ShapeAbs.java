package cs5004.animator.model;

/**
 * The ShapeAbs class serves as the abstract for the Rectangle and Oval concrete class.
 */
public abstract class ShapeAbs implements Shape {

  protected int shapeIndex;
  protected String shapeName;
  protected double xCor;
  protected double yCor;
  protected ShapeColor shapeColor;
  protected double dimensionOne;
  protected double dimensionTwo;
  
  /**
   * The ShapeAbs constructor serves as the super constructor for all of the
   * individual shapes themselves. It will store the index, name, x-coordinate,
   * y-coordinate, and color of the shape.
   *
   * @param shapeIndex the index of the shape (the position it was added)
   * @param name       the name of the shape
   * @param x          x-coordinate of the shape
   * @param y          y-coordinate of the shape
   * @param shapeColor the color of the shape
   * @throws IllegalArgumentException if the color object passed is null.
   */
  public ShapeAbs(int shapeIndex, String name, double x, double y, ShapeColor shapeColor,
      double dimOne, double dimTwo) throws IllegalArgumentException {

    if (shapeColor == null) {
      throw new IllegalArgumentException("Please input correct color for the shape!");
    }

    if (dimOne <= 0 || dimTwo <= 0) {
      throw new IllegalArgumentException("Dimension one and two must be larger than 0");
    }
    this.shapeIndex = shapeIndex;
    this.shapeName = name;
    this.xCor = x;
    this.yCor = y;
    this.shapeColor = shapeColor;
    this.dimensionOne = dimOne;
    this.dimensionTwo = dimTwo;
  }
  
  /**
   * Returns the current x coordinate of where the shape is. The x coordinate for
   * the shapes vary depending on the type of shape. A rectangle's x value
   * represents the bottom-left edge of the rectangle. The ovals' x value
   * represents the x-coordinate of the center of the oval.
   *
   * @return the x coordinate of the shape
   */
  @Override
  public double getX() {    
    return this.xCor;
  }

  /**
   * Returns the current y coordinate of where the shape is. The y coordinate for
   * the shapes vary depending on the type of shape. A rectangle's y value
   * represents the bottom-left edge of the rectangle. The ovals' y value
   * represents the y-coordinate of the center the oval.
   *
   * @return the y coordinate of the shape
   */
  @Override
  public double getY() {
    return this.yCor;
  }

  /**
   * Get the color of a shape in the combining of red code, green code, blue code.
   * @return the color of the shape in String format
   */
  @Override
  public String getShapeColor() {
    return this.shapeColor.getColorCombo();
  }

  /**
   * Gets the name of the shape.
   * @return the name of the shape
   */
  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  /**
   * Gets the index of the shape. The index of the shape is the number in which it
   * has been added to the animation.
   * 
   * @return the index of the shape
   */
  @Override
  public int getShapeIndex() {
    return this.shapeIndex;
  }

  /**
   * Gets the RGB values of the shape.
   * @return a double array which is the rgb values of the shape. (ie [0.0, 255.0, 180.0])
   */
  @Override
  public double[] getColorCode() {
    return new double[] { this.shapeColor.redCode, this.shapeColor.greenCode,
        this.shapeColor.blueCode };
  }

  /**
   * Makes a copy of the shape's attributes.
   * @return a copy of the shape's attributes
   */
  @Override
  public ShapeAbs clone() {    
    return null;
  }

  
}
