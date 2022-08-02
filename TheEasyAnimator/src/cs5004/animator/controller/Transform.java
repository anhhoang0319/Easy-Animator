package cs5004.animator.controller;

/**
 * The Transform class was created to essentially hold all of the shape's attributes.
 */
public class Transform {
  int t1;
  int t2;
  int x1;
  int y1;
  int x2;
  int y2;
  int w1;
  int h1;
  int w2;
  int h2;
  int r1;
  int g1;
  int b1;
  int r2;
  int g2;
  int b2;

  Transform(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
            int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.t1 = t1;
    this.t2 = t2;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.w1 = w1;
    this.h1 = h1;
    this.w2 = w2;
    this.h2 = h2;
    this.r1 = r1;
    this.g1 = g1;
    this.b1 = b1;
    this.r2 = r2;
    this.g2 = g2;
    this.b2 = b2;
  }
}
