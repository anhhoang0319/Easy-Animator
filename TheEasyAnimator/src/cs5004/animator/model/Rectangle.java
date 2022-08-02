package cs5004.animator.model;

/**
 * This concrete Rectangle class represents an rectangle that will be created in the animation.
 * It extends the ShapeAbs class and inherits all of the properties from the class.
 */
public class Rectangle extends ShapeAbs {

  public Rectangle(int shapeIndex, String name, double x, double y, ShapeColor shapeColor,
      double dimOne, double dimTwo) throws IllegalArgumentException {
    super(shapeIndex, name, x, y, shapeColor, dimOne, dimTwo);
  }

  /**
   * Gets the area of the rectangle.
   * 
   * @return the area of the rectangle
   */
  @Override
  public double getArea() {
    return this.dimensionOne * this.dimensionTwo;
  }

  /**
   * Returns the width of the rectangle.
   *
   * @return the width of the rectangle
   */
  public double getWidth() {
    return this.dimensionOne;
  }
  
  /**
   * Returns the height of the rectangle.
   *
   * @return the height of the rectangle
   */
  public double getHeight() {
    return this.dimensionTwo;
  }
  
  /**
   * Represents a rectangle (i.e., shape name, shape type, x-coordinate,
   * y-coordinate, dimensions, scales, and color) to a string.
   * @return the information of the rectangle as a string
   */
  @Override
  public String toString() {
    return String.format("Type: %s\n"
                    + "Min Corner: (%.0f,%.0f), Width: %.0f, Height: %.0f, %s\n",
            "Rectangle", getX(), getY(), getWidth(), getHeight(), getShapeColor());
  }
  
  /**
   * Makes a copy of the rectangle's attributes.
   * @return a copy of the tectangle's attributes
   */
  @Override
  public Rectangle clone() {
    return new Rectangle(this.shapeIndex, this.shapeName, this.xCor,this.yCor,
            new ShapeColor(this.shapeColor.redCode, this.shapeColor.greenCode,
                    this.shapeColor.blueCode),this.dimensionOne,this.dimensionTwo);
  }
}
