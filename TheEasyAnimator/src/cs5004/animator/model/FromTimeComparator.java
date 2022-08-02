package cs5004.animator.model;

import java.util.Comparator;

/**
 * This class represents a comparator that compares two animation based on their starting time.
 */
public class FromTimeComparator implements Comparator<AnimationTypeAbs> {

  @Override
  public int compare(AnimationTypeAbs a1, AnimationTypeAbs a2) {
    if (a1.fromTime > a2.fromTime) {
      return 1;
    } else if (a1.fromTime < a2.fromTime) {
      return -1;
    } else {
      return 0;
    }
  }
}
