package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The mock controller of Easy Animation for testing purpose. This mock controller simulates
 * the key structure of the actual animation controller.
 */
public class MockController implements ActionListener {
  protected MockModel model;
  protected MockView view;
  protected String viewType;
  protected JButton button;

  /**
   * Class constructor of the model controller.
   * @param model an instance of the mock model
   * @param viewType the type of view (i.e., text/svg/visual/playback)
   * @param buttonCommand the button to be clicked in the playback view
   */
  public MockController(MockModel model, String viewType, String buttonCommand) {
    this.model = model;
    this.viewType = viewType;
    configureView();

    // set the button to be clicked in the playback view
    if (buttonCommand != null) {
      switch (buttonCommand) {
          // click on the start button
          case "Start":
            this.button = view.start;
            break;
          // click on the the pause button
          case "Pause":
            this.button = view.pause;
            break;
          // click on the restart button
          case "Restart":
            this.button = view.restart;
            break;
          // click on the loop enabling/disabling button
          case "Enable/Disable Loop":
            this.button = view.loop;
            break;
          // click on the speed increase button
          case "Increase Speed":
            this.button = view.increase;
            break;
          // click on the speed decrease button
          case "Decrease Speed":
            this.button = view.decrease;
            break;
      }
    }

    // determine which view to display
    if (this.viewType.equals("playback")) {
      this.view.setListeners(this);
      // click the button
      this.button.doClick();
    } else {
      this.view.display();
    }
  }

  // set up a view object
  private void configureView() {
    if (model.ableToAccessModelInfo()) {
      this.view = new MockView();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      // mocking starting the animation
      case "Start":
        this.view.startAnimation();
        break;
      // mocking pausing the animation
      case "Pause":
        this.view.pauseAnimation();
        break;
      // mocking restarting the animation
      case "Restart":
        this.view.restartAnimation();
        break;
      // mocking enabling or disable Loop
      case "Enable/Disable Loop":
        this.view.loopActivation();
        break;
      // mocking increasing the play speed
      case "Increase Speed":
        this.view.increaseSpeed();
        break;
      // mocking decreasing the play speed
      case "Decrease Speed":
        this.view.decreaseSpeed();
        break;
    }
  }
}
