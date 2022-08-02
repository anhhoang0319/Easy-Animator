package cs5004.animator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.ShapeColor;

/**
 * This class implements the AnimationBuilder interface.
 */
public final class AnimationBuilderImpl implements AnimationBuilder<AnimationModelImpl> {

  private AnimationModelImpl model;
  protected Map<String, ShapeEntry> shapeMap;
  protected Map<String, Transform> initialState;
  protected Map<String, ArrayList<Transform>> shapeTransformMap;
  protected Map<String, Integer> minTicks;
  protected Map<String, Integer> maxTicks;
  protected int shapeCount;
  
  /**
   * This constructor takes in the data from the AnimationReader and stores it in various
   * hashmaps according to the data type. It also constructs an empty AnimationModelImpl that
   * will be eventually populated with the data.
   */
  public AnimationBuilderImpl() {
    model = new AnimationModelImpl();
    shapeMap = new HashMap<String, ShapeEntry>();
    initialState = new HashMap<String, Transform>();
    shapeTransformMap = new HashMap<String, ArrayList<Transform>>();
    minTicks = new HashMap<String, Integer>();
    maxTicks = new HashMap<String, Integer>();
    shapeCount = 0;
  }
  
  @Override
  public AnimationModelImpl build() {
    // Stores the initial state of the shape that will later be used to actual render the shape.
    for (HashMap.Entry<String, ShapeEntry> shape : shapeMap.entrySet()) {
      int appear = minTicks.get(shape.getKey());
      int disappear = maxTicks.get(shape.getKey());

      Transform initialParams = initialState.get(shape.getKey());
      int xcor = initialParams.x1;
      int ycor = initialParams.y1;
      int dimOne = initialParams.w1;
      int dimTwo = initialParams.h1;
      int redStart = initialParams.r1;
      int greenStart = initialParams.g1;
      int blueStart = initialParams.b1;

      model.addShape(shape.getValue().shapeIndex, shape.getKey(), shape.getValue().shapeType, xcor,
              ycor, dimOne, dimTwo, new ShapeColor(redStart, greenStart, blueStart), appear,
              disappear);

      // Loops through the transformation map, which is comprised of all different animation types
      // and translates the motions into a readable format by the animation model.
      for (Transform transformation : shapeTransformMap.get(shape.getKey())) {

        if (transformation.x1 != transformation.x2 || transformation.y1 != transformation.y2) {
          model.addMove(shape.getKey(), shape.getValue().shapeType, transformation.x1,
                  transformation.y1, transformation.x2, transformation.y2, transformation.t1,
                  transformation.t2);
        }

        if (transformation.r1 != transformation.r2 || transformation.g1 != transformation.g2
                || transformation.b1 != transformation.b2) {
          model.changeColor(shape.getKey(), shape.getValue().shapeType,
                  new ShapeColor(transformation.r1, transformation.g1, transformation.b1),
                  new ShapeColor(transformation.r2,
                          transformation.g2, transformation.b2), transformation.t1,
                          transformation.t2);
        }

        if (transformation.h1 != transformation.h2 || transformation.w1 != transformation.w2) {
          model.resize(shape.getKey(), shape.getValue().shapeType, transformation.h1,
                  transformation.w1, transformation.h2, transformation.w2, transformation.t1,
                  transformation.t2,
                  true);
        }
      }
    }
    return model;
  }

  /**
   * Sets the dimensions of the canvas.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return the instance of the model with the correct canvas dimensions.
   */
  public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height) {
    model.setBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type) {
    shapeMap.put(name, new ShapeEntry(shapeCount++, type));
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModelImpl> addMotion(String name,
                                                        int t1, int x1, int y1, int w1, int h1,
                                                        int r1, int g1, int b1, int t2, int x2,
                                                        int y2, int w2, int h2, int r2, int g2,
                                                        int b2) {

    if (t1 == t2) {
      minTicks.put(name, t1);
      initialState.put(name, new Transform(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2,
              g2, b2));
    } else {
      if (maxTicks.containsKey(name)) {
        if (maxTicks.get(name) < t2) {
          maxTicks.replace(name, t2);
        }
      } else {
        maxTicks.put(name, t2);
      }

      if (!shapeTransformMap.containsKey(name)) {
        shapeTransformMap.put(name, new ArrayList<Transform>());
      }

      shapeTransformMap.get(name).add(new Transform(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2,
              w2, h2, r2, g2, b2));

    }
    return this;
  }

  /**
   * A getter function to get the StringMapList from the model.
   *
   * @return the stringMapList from the model.
   */
  public ArrayList<HashMap<String, String>> getStringMapList() {
    return this.model.getStringMapList();
  }

  /**
   * A getter function to get the DoubleMapList from the model.
   *
   * @return the doubleMapList from the model.
   */
  public ArrayList<HashMap<String, Double>> getDoubleMapList() {
    return this.model.getDoubleMapList();
  }

}
