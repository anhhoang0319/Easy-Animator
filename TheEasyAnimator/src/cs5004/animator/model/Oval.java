package cs5004.animator.model;

public class Oval extends ShapeAbs {

  /**
   * The oval constructor calls the super constructor from the ShapeAbs class, but
   * also stores the xRadius and yRadius information that will be important in
   * knowing for the creation of the oval.
   *
   * @param shapeIndex the index of the shape
   * @param name       the name of the shape.
   * @param x          x-coordinate of the oval
   * @param y          y-coordinate of the oval
   * @param color      the color of the oval
   * @param xRadius    the length of the x radius (horizontal radius) of the oval
   * @param yRadius    the length of the y radius (vertical radius) of the oval
   * @throws IllegalArgumentException when the xRadius or yRadius is less than or
   *                                  equal to 0.
   */
  protected Oval(int shapeIndex, String name, double x, double y, ShapeColor shapeColor,
      double dimOne, double dimTwo) throws IllegalArgumentException {
    super(shapeIndex, name, x, y, shapeColor, dimOne, dimTwo);
  }

  /**
   * Gets the area of the oval.
   * 
   * @return the area of the shape
   */
  @Override
  public double getArea() {
    return this.dimensionOne * this.dimensionTwo * Math.PI;
  }

  /**
   * Returns the length of the x radius (horizontal radius) of the oval.
   * 
   * @return the x radius of the oval
   */
  public double getXRadius() {
    return this.dimensionOne;
  }

  /**
   * Returns the length of the y radius (vertical radius) of the oval.
   * 
   * @return the y radius of the oval
   */
  public double getYRadius() {
    return this.dimensionTwo;
  }

  /**
   * Represents an oval (i.e., shape name, shape type, x-coordinate,
   * y-coordinate, dimensions, scales, and color) to a string.
   * @return the information of the oval as a string
   */
  @Override
  public String toString() {
    return String.format("Type: %s\n" 
        + "Center: (%.2f,%.2f), X radius: %.2f, Y radius: %.2f, %s\n",
        "Oval", getX(), getY(), getXRadius(), getYRadius(), getShapeColor());
  }

  /**
   * Makes a copy of the oval's attributes.
   * @return a copy of the oval's attributes
   */
  @Override
  public Oval clone() {
    return new Oval(this.shapeIndex, this.shapeName, this.xCor, this.yCor,
        new ShapeColor(this.shapeColor.redCode, this.shapeColor.greenCode,
            this.shapeColor.blueCode),
        this.dimensionOne, this.dimensionTwo);
  }
}
