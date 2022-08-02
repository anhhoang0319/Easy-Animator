package cs5004.animator.controller;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The mock view of Easy Animation for testing purpose.
 */
public class MockView {
  protected JButton start, pause, restart, loop, increase, decrease;
  protected String startTest;
  protected String pauseTest;
  protected String restartTest;
  protected String loopTest;
  protected String increaseSpeedTest;
  protected String decreaseSpeedTest;
  protected String nonPlaybackViewTest;

  /**
   * The constructor of the mock view.
   */
  public MockView() {
    this.start = new JButton("Start");
    this.pause = new JButton("Pause/Resume");
    this.restart = new JButton("Restart");
    this.loop = new JButton("Enable/Disable Loop");
    this.increase = new JButton("Increase Speed");
    this.decrease = new JButton("Decrease Speed");

    this.start.setActionCommand("Start");
    this.pause.setActionCommand("Pause");
    this.restart.setActionCommand("Restart");
    this.loop.setActionCommand("Enable/Disable Loop");
    this.increase.setActionCommand("Increase Speed");
    this.decrease.setActionCommand("Decrease Speed");
  }

  /**
   * Set an action listener for all the buttons.
   *
   * @param e an action event (e.g., a button clicking)
   */
  public void setListeners(ActionListener e) {
    this.start.addActionListener(e);
    this.pause.addActionListener(e);
    this.restart.addActionListener(e);
    this.loop.addActionListener(e);
    this.increase.addActionListener(e);
    this.decrease.addActionListener(e);
  }

  /**
   * Animation display of the textual, SVG, or visual view.
   */
  public void display() {
    this.nonPlaybackViewTest = "Textual/SVG/Visual animation successfully displayed";
  }

  /**
   * Start the animation play.
   */
  public void startAnimation() {
    this.startTest = "Playback animation is starting";
  }

  /**
   * Pause the animation.
   */
  public void pauseAnimation() {
    this.pauseTest = "Playback animation is paused";
  }

  /**
   * Restart the animation.
   */
  public void restartAnimation() {
    this.restartTest = "Playback animation is restarted";
  }

  /**
   * Enable or disable loops.
   */
  public void loopActivation() {
    this.loopTest = "Playback animation loop activated/deactivated";
  }

  /**
   * Increase the animation play speed.
   */
  public void increaseSpeed() {
    this.increaseSpeedTest = "Playback animation speed has been increased by 1";
  }

  /**
   * Decrease the animation play speed.
   */
  public void decreaseSpeed() {
    this.decreaseSpeedTest = "Playback animation speed has been decreased by 1";
  }
}
