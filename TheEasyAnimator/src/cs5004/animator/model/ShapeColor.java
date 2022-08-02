package cs5004.animator.model;

/**
 * The concrete color class represents the color of the shape. The color consists of varying
 * units of red, green, and blue.
 * @author Anh Hoang
 */
public class ShapeColor {

  protected int redCode;
  protected int greenCode;
  protected int blueCode;
  
  public ShapeColor(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException(
          "Please input correct color codes for red, green, and blue. Codes should be in"
          + " range of 0 and 255 inclusively.");
    }
    this.redCode = red;
    this.greenCode = green;
    this.blueCode = blue;
  }
  
  String getColorCombo() {
    return "Color: (" + this.redCode + ", " + this.greenCode + ", " + this.blueCode + ")";
  }
}
