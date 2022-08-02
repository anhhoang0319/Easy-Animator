package cs5004.animator.controller;

/**
 * The ShapeEntry class was created for the sole use of the hashmap where the
 * program could store both the index as well as the shape type, which would
 * ultimately be used by the views.
 */
public class ShapeEntry {
  int shapeIndex;
  String shapeType;

  ShapeEntry(int index, String type) {
    shapeIndex = index;
    shapeType = type;
  }
}
