package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a SVG view that translates animation data to SVG syntax. In addition, the
 * SVG view displays the animation in two ways: 1) if an output file name is specified, the
 * animation SVG script is saved to a svg file; 2) otherwise, the SVG script is printed to the
 * system screen.
 */
public class SVGAnimationView implements AnimationView {
  private ArrayList<HashMap<String, String>> stringMapList;
  private ArrayList<HashMap<String, Double>> doubleMapList;
  private HashMap<String, String> shapeIndexMap; // e.g., HashMap<orderedToBeAppendedAsIndex,
  // shapeName>
  private HashMap<String, String> shapeSVGMap; // e.g., HashMap<shapeName, SVGText>
  private HashMap<String, String> shapeSuffixMap; // e.g., HashMap<shapeName, suffix/closingTag>

  private String outfileName;
  private int speed;
  private int index;
  private int shapeCount;

  /**
   * The constructor of the svg view class.
   *
   * @param stringMapList An array list of hash maps that store information of animation actions as
   *                      strings
   * @param doubleMapList An array list of hash maps that store information of * animation actions
   *                      as doubles
   * @param outfileName   the name of the svg file to be output
   * @param speed         the number of ticks per second (ie., animation play speed)
   */
  public SVGAnimationView(ArrayList<HashMap<String, String>> stringMapList,
                          ArrayList<HashMap<String, Double>> doubleMapList,
                          String outfileName, int speed) {
    this.stringMapList = stringMapList;
    this.doubleMapList = doubleMapList;
    shapeIndexMap = new HashMap<>();
    this.shapeSVGMap = new HashMap<>();
    this.shapeSuffixMap = new HashMap<>();
    if (outfileName != null) {
      this.outfileName = outfileName;
    }
    this.speed = speed;
    this.index = 0;
    shapeCount = 0;
  }

  /**
   * Display the animation as SVG. If an output svg file name is specified, the animation is written
   * out as an svg file. Otherwise the svg text will be printed to the screen.
   *
   * @throws IOException if the output svg file cannot be written.
   */
  @Override
  public void display() throws IOException {
    String svg = toSVG();
    if (this.outfileName == null) {
      System.out.println(svg);
    } else {
      try {
        FileWriter fileWriter = new FileWriter(this.outfileName);
        fileWriter.write(svg);
        fileWriter.close();
      } catch (IOException ioe) {
        throw new IOException("SVG file writing operation failed.");
      }
    }
  }

  /**
   * Get the translated svg text as a string.
   *
   * @return
   */
  @Override
  public String toString() {
    return toSVG();
  }

  // helper method
  // translate the animation to a string in svg syntax
  private String toSVG() {
    // create the file header
    String svg = "<svg width=\"1500\" height=\"1500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    // if there's complete data, initiate the translation process
    if (stringMapList != null && doubleMapList != null) {
      // translate the each animation action to svg
      for (HashMap<String, String> animationStringMap : stringMapList) {
        // add shape
        if (animationStringMap.get("animationType").equals("addShape")) {
          addShape();
        }
        // move a shape
        else if (animationStringMap.get("animationType").equals("move")) {
          move();
        }
        // change the color of a shape
        else if (animationStringMap.get("animationType").equals("changeColor")) {
          changeColor();
        }
        // change the size of a shape
        else {
          resize();
        }
        // increment the index in the hashmaps
        this.index++;
      }

      // append the translated svg text to the output string
      for (int i = 0; i < this.shapeCount; i++) {
        String j = String.valueOf(i);
        String shapeName = this.shapeIndexMap.get(j);
        String svgString = this.shapeSVGMap.get(shapeName);
        String closingTag = this.shapeSuffixMap.get(shapeName);
        svg += svgString; // append body svg of each shape
        svg += (closingTag + "\n"); // append closing tag for each shape
      }

      // reset the value just in case the view translation is invoked again
      shapeIndexMap = new HashMap<>();
      this.shapeSVGMap = new HashMap<>();
      this.shapeSuffixMap = new HashMap<>();
      this.index = 0;
      shapeCount = 0;
    }

    // append the last line to close off the svg file
    svg += "</svg>\n";

    // return the svg as a string
    return svg;
  }

  // helper method
  // translate the action of adding a shape to svg language
  private void addShape() {
    String shapeSVGCode;
    String attributeName1;
    String attributeName2;
    String attributeName3;
    String attributeName4;
    double dimension1;
    double dimension2;

    String shapeType = stringMapList.get(this.index).get("shapeType");
    String shapeName = stringMapList.get(this.index).get("shapeName");
    String shapeIndex = this.stringMapList.get(this.index).get("shapeIndex");
    shapeIndexMap.put(shapeIndex, shapeName);
    this.shapeCount++;
    if (shapeType.equalsIgnoreCase("Rectangle")) {
      attributeName1 = "x";
      attributeName2 = "y";
      attributeName3 = "width";
      attributeName4 = "height";
      shapeSVGCode = "rect";
      dimension1 = doubleMapList.get(this.index).get("startingWidth");
      dimension2 = doubleMapList.get(this.index).get("startingHeight");
      shapeSuffixMap.put(shapeName, "</rect>\n");
    } else {
      attributeName1 = "cx";
      attributeName2 = "cy";
      attributeName3 = "rx";
      attributeName4 = "ry";
      shapeSVGCode = "ellipse";
      dimension1 = this.doubleMapList.get(this.index).get("startingXRadius");
      dimension2 = this.doubleMapList.get(this.index).get("startingYRadius");
      shapeSuffixMap.put(shapeName, "</ellipse>\n");
    }

    double x = this.doubleMapList.get(this.index).get("startingX");
    double y = this.doubleMapList.get(this.index).get("startingY");
    double colorRed = this.doubleMapList.get(this.index).get("startingColorRed");
    double colorGreen = this.doubleMapList.get(this.index).get("startingColorGreen");
    double colorBlue = this.doubleMapList.get(this.index).get("startingColorBlue");

    String svgString = String.format("<%s id=\"%s\" %s=\"%.0f\" %s=\"%.0f\" %s=\"%.0f\" "
                    + "%s=\"%.0f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >\n",
            shapeSVGCode, shapeName, attributeName1, x,
            attributeName2, y, attributeName3, dimension1, attributeName4, dimension2,
            colorRed, colorGreen, colorBlue);
    shapeSVGMap.put(shapeName, svgString);
  }

  // helper method
  // translate the action of moving a shape to svg language
  private void move() {
    String svgString = "";
    String attributeName1;
    String attributeName2;
    String shapeType = this.stringMapList.get(this.index).get("shapeType");
    String shapeName = this.stringMapList.get(this.index).get("shapeName");
    if (shapeType.equalsIgnoreCase("Rectangle")) {
      attributeName1 = "x";
      attributeName2 = "y";
    } else {
      attributeName1 = "cx";
      attributeName2 = "cy";
    }

    double fromX = this.doubleMapList.get(this.index).get("fromDimensionOne");
    double toX = this.doubleMapList.get(this.index).get("toDimensionOne");
    double fromY = this.doubleMapList.get(this.index).get("fromDimensionTwo");
    double toY = this.doubleMapList.get(this.index).get("toDimensionTwo");
    double fromTime = this.doubleMapList.get(this.index).get("fromTime") * 1000 / this.speed;
    double toTime = this.doubleMapList.get(this.index).get("toTime") * 1000 / this.speed;
    double duration = toTime - fromTime;

    if ((int) fromX != (int) toX) {
      svgString += String.format("\t<animate attributeType=\""
                      + "xml\" begin=\"%.0fms\" dur=\"%.0fms\" "
                      + "attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
              fromTime, duration, attributeName1, fromX, toX);
    }
    if ((int) fromY != (int) toY) {
      svgString += String.format("\t<animate attributeType=\""
                      + "xml\" begin=\"%.0fms\" dur=\"%.0fms\" "
                      + "attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
              fromTime, duration, attributeName2, fromY, toY);
    }

    String oldStr = this.shapeSVGMap.get(shapeName);
    this.shapeSVGMap.replace(shapeName, oldStr + svgString);
  }

  // helper method
  // translate the action of changing the color of a shape to svg language
  private void changeColor() {
    String shapeName = this.stringMapList.get(this.index).get("shapeName");
    double fromRed = this.doubleMapList.get(this.index).get("fromDimensionOne");
    double toRed = this.doubleMapList.get(this.index).get("toDimensionOne");
    double fromGreen = this.doubleMapList.get(this.index).get("fromDimensionTwo");
    double toGreen = this.doubleMapList.get(this.index).get("toDimensionTwo");
    double fromBlue = this.doubleMapList.get(this.index).get("fromDimensionThree");
    double toBlue = this.doubleMapList.get(this.index).get("toDimensionThree");
    double fromTime = this.doubleMapList.get(this.index).get("fromTime") * 1000 / this.speed;
    double toTime = this.doubleMapList.get(this.index).get("toTime") * 1000 / this.speed;
    double duration = toTime - fromTime;


    String svgString = String.format("\t<animate attributeType="
                    + "\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" "
                    + "attributeName=\"fill\" from=\"rgb(%.0f,%.0f,%.0f)\" "
                    + "to=\"rgb(%.0f,%.0f,%.0f)\" fill=\"freeze\" />\n",
            fromTime, duration, fromRed, fromGreen, fromBlue, toRed, toGreen, toBlue);

    String oldStr = this.shapeSVGMap.get(shapeName);
    this.shapeSVGMap.replace(shapeName, oldStr + svgString);
  }

  // helper method
  // translate the action of resizing a shape to svg language
  private void resize() {
    String svgString = "";
    String attributeName1;
    String attributeName2;
    String shapeName = this.stringMapList.get(this.index).get("shapeName");
    String shapeType = this.stringMapList.get(this.index).get("shapeType");
    if (shapeType.equalsIgnoreCase("Rectangle")) {
      attributeName1 = "width";
      attributeName2 = "height";
    } else {
      attributeName1 = "rx";
      attributeName2 = "ry";
    }

    double fromDimensionOne = this.doubleMapList.get(this.index).get("fromDimensionOne");
    double toDimensionOne = this.doubleMapList.get(this.index).get("toDimensionOne");
    double fromDimensionTwo = this.doubleMapList.get(this.index).get("fromDimensionTwo");
    double toDimensionTwo = this.doubleMapList.get(this.index).get("toDimensionTwo");
    double fromTime = this.doubleMapList.get(this.index).get("fromTime") * 1000 / this.speed;
    double toTime = this.doubleMapList.get(this.index).get("toTime") * 1000 / this.speed;
    double duration = toTime - fromTime;

    if ((int) fromDimensionOne != (int) toDimensionOne) {
      svgString += String.format("\t<animate attributeType=\""
                      + "xml\" begin=\"%.0fms\" dur=\"%.0fms\" "
                      + "attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
              fromTime, duration, attributeName1, fromDimensionOne, toDimensionOne);
    }
    if ((int) fromDimensionTwo != (int) toDimensionTwo) {
      svgString += String.format("\t<animate attributeType=\""
                      + "xml\" begin=\"%.0fms\" dur=\"%.0fms\" "
                      + "attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
              fromTime, duration, attributeName2, fromDimensionTwo, toDimensionTwo);
    }

    String oldStr = this.shapeSVGMap.get(shapeName);
    this.shapeSVGMap.replace(shapeName, oldStr + svgString);
  }
}
