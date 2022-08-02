package cs5004.animator.controller;

/**
 * The mock model of Easy Animation for testing purpose.
 */
public class MockModel {
  protected boolean modelAccess;

  /**
   * The class constructor of the mock model.
   */
  public MockModel() {
    this.modelAccess = true;
  }

  /**
   * Signals whether the information of the model can be signaled from outside the class.
   *
   * @return whether the model information can be accessed
   */
  public boolean ableToAccessModelInfo() {
    return this.modelAccess;
  }
}
