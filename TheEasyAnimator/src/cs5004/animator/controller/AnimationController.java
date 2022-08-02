package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.GraphicalAnimationView;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGAnimationView;
import cs5004.animator.view.TextualAnimationView;

/**
 * This is the animation controller class that coordinates between the animation model and
 * the animation view. Based on user's input, the controller gets certain information
 * from the model and then instructs the specified view to display animation.
 */
public class AnimationController implements ActionListener {
  private AnimationModelImpl model;
  private AnimationView view;
  private String viewType;
  private String outFileName;
  private String speed;
  private ArrayList<HashMap<String, String>>  svgStringMapList;
  private ArrayList<HashMap<String, Double>> svgDoubleMapList;

  /**
   * The constructor of the controller.
   * @param model the model of the Easy Animation
   * @param viewType the type of the view (i.e., textual, SVG, visual, or playback view)
   * @param outFileName the file name to be write to
   * @param speed the speed of the animation display (i.e., number of ticks per second)
   * @param svgStringMapList a list of hashmap that stores String animation information
   * @param svgDoubleMapList a list of hashmap that stores double animation information
   * @throws IOException when a file cannot be written to
   * @throws InterruptedException when a process is interrupted
   */
  public AnimationController(AnimationModelImpl model, String viewType, String outFileName,
                             String speed, ArrayList<HashMap<String, String>>  svgStringMapList,
                             ArrayList<HashMap<String, Double>> svgDoubleMapList) throws IOException,
                             InterruptedException {
    this.model = model;
    this.viewType = viewType;
    this.outFileName = outFileName;
    this.speed = speed;
    this.svgStringMapList = svgStringMapList;
    this.svgDoubleMapList = svgDoubleMapList;
    configureView();

    if (this.viewType.equals("playback")) {
      ((PlaybackView)this.view).setListeners(this);
    }
    else {
      this.view.display();
    }
  }

  /**
   * The ConfigureView method is a void method that is called to create the appropriate view based on what the user
   * enters in in the command line.
   */
  private void configureView() {
    // set up a view in textual format
    if (this.viewType.equals("text")) {
      this.view = new TextualAnimationView(this.model.playAnimation(),
              this.outFileName, this.speed);
    }
    // set up a view in svg format
    else if (this.viewType.equals("svg")) {
      this.view = new SVGAnimationView (this.svgStringMapList,
              this.svgDoubleMapList,
              this.outFileName, Integer.parseInt(this.speed));
    }
    // set up a view in interactive visual format (i.e., playback view)
    else if (this.viewType.equals("playback")) {
      this.view = new PlaybackView (this.model.convertToFrames(),
              this.model.getBounds(),
              Integer.parseInt(this.speed));
    }
    // set up a view in visual format
    else {
      this.view = new GraphicalAnimationView (this.model.convertToFrames(),
             this.model.getBounds(), Integer.parseInt(this.speed));
    }
  }

  /**
   * The actionPerformed method is used for the Playback View and is a controller for what particular action gets
   * executed based on what ActionEvent is passed through the parameter.
   * @param e when you put an action listener onto a button, when the button is clicked, the action listener will
   *          automatically invoke the action performed function.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      // start the animation
      case "Start":
        ((PlaybackView)this.view).startAnimation();
        break;

      // pause the animation
      case "Pause":
        ((PlaybackView)this.view).pauseAnimation();
        break;

      // restart the animation
      case "Restart":
        ((PlaybackView)this.view).restartAnimation();
        break;

      // enable or disable Loop
      case "Enable/Disable Loop":
        ((PlaybackView)this.view).loopActivation();
        break;

      // increase the play speed
      case "Increase Speed":
        ((PlaybackView) this.view).increaseSpeed();
        break;

      // decrease the play speed
      case "Decrease Speed":
        ((PlaybackView)this.view).decreaseSpeed();
        break;
    }
  }
}
