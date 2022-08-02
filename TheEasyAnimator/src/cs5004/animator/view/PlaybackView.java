package cs5004.animator.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;

/**
 * The PlayBack View is a class that implements the AnimationView interface and produces a
 * visual representation of the data when it is called from the command line and allows the user
 * to interactive with the animation being displayed.
 *
 * @param <ShapeAbs> this class uses the ShapeAbs data types to render its output.
 */
public class PlaybackView<ShapeAbs> extends JPanel implements AnimationView {

  protected JFrame frame;
  protected JPanel buttonPanel;
  protected JPanel animationPanel;
  protected JSplitPane splitPanel;
  protected DrawAnimation<ShapeAbs> animationCanvas;
  protected ArrayList<ArrayList<ShapeAbs>> frameList;
  protected JButton start, pause, restart, loop, increase, decrease;
  protected JLabel speedStatus;
  protected JLabel loopStatusLabel;
  protected JLabel playAnimation;
  Timer timer;
  String loopStatus;
  boolean playStatus;
  int[] bounds;
  int tempo;
  int speed;

  /**
   * This constructor creates a JFrame that contains a JSplitPane displaying the graphic output
   * of the data as well as JButtons used to interact with the data.
   *
   * @param frames an array list of array lists of ShapeAbs objects in the order of appearance.
   * @param bounds an int array of the canvas dimensions
   * @param tempo the speed that which the user wants to play the animation
   **/

  public PlaybackView(ArrayList<ArrayList<ShapeAbs>> frames, int[] bounds, int tempo) {
    this.tempo = 1000 / tempo;
    this.speed = tempo;
    this.playStatus = true;
    this.bounds = bounds;
    this.frameList = frames;
    this.frame = new JFrame("Playback View");
    this.loopStatus = "No";
    this.splitPanel = new JSplitPane();
    this.buttonPanel = createButtons();
    this.animationPanel = createAnimationCanvas();

    // Create the overall frame for the playback view
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1200, 1200));
    frame.setLayout(new GridLayout());

    // Create a splitpane that has separates the animation and the buttons
    splitPanel.setPreferredSize(new Dimension(900, 900));
    splitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPanel.setDividerLocation(850);
    splitPanel.add(animationPanel, JSplitPane.TOP);
    splitPanel.add(buttonPanel, JSplitPane.BOTTOM);
    splitPanel.getTopComponent().setMinimumSize(new Dimension(850,850));

    // Add the splitpane to the frame
    frame.getContentPane().add(splitPanel);
    frame.pack();
    frame.setVisible(true);

    this.timer = new Timer(this.tempo, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();

        if (animationCanvas.frameCount == frameList.size() - 1) {
          timer.stop();
          if (loopStatus.equalsIgnoreCase("Yes")) {
            animationCanvas.frameCount = 0;
            timer.restart();
          } else {
            playAnimation.setText("[ Animation is finished ]     ");
            playStatus = false;
            pause.setEnabled(false);
          }
        }
      }
    });
  }

  /**
   * This function creates the JPanel that holds all of the JButtons that correspond with
   * each of the events listed.
   * @return a JPanel of JButtons
   */
  private JPanel createButtons() {
    JPanel buttonFrame = new JPanel();
    buttonFrame.setBackground(new Color(173,216,230));
    buttonFrame.setLayout(new GridBagLayout());
    JPanel buttons = new JPanel();
    JPanel status = createStatusBox();
    BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.X_AXIS);
    buttons.setLayout(buttonLayout);
    buttons.setPreferredSize(new Dimension(200, 200));

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

    buttons.add(this.start);
    buttons.add(this.pause);
    buttons.add(this.restart);
    buttons.add(this.loop);
    buttons.add(this.increase);
    buttons.add(this.decrease);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridy = 2;
    buttonFrame.add(buttons, gbc);
    gbc.gridy = 0;
    buttonFrame.add(status, gbc);
    return buttonFrame;
  }

  /**
   * This function creates a status box that informs the user as to what is happening when.
   * For example, it gives the user the speed, status of looping, and status of animation.
   * @return a JPanel of JLabels
   */
  private JPanel createStatusBox() {
    JPanel status = new JPanel();
    status.setBackground(new Color(173,216,230));
    playAnimation = new JLabel("[ Animation is ready to play ]     ");
    speedStatus = new JLabel("[ Speed Setting: " + this.speed + " ]    ");
    loopStatusLabel = new JLabel("[ Looping Enabled: " + this.loopStatus + " ]");
    JLabel blankSpace = new JLabel("           ");
    status.setLayout(new BorderLayout());
    status.add(playAnimation, BorderLayout.WEST);
    status.add(speedStatus, BorderLayout.CENTER);
    status.add(loopStatusLabel, BorderLayout.EAST);
    status.add(blankSpace, BorderLayout.SOUTH);
    return status;
  }

  /**
   * This function is responsible for creating the actual animation canvas and placing it
   * onto a light gray background to make it more visible
   * @return a JPanel with the animation on it.
   */
  private JPanel createAnimationCanvas() {
    Dimension animationSize = new Dimension(800, 800);
    JPanel animation = new JPanel();
    animation.setBackground(Color.LIGHT_GRAY);
    animation.setLayout(new GridBagLayout());
    this.animationCanvas = new DrawAnimation<ShapeAbs>(this.frameList);
    animationCanvas.setPreferredSize(animationSize);
    animationCanvas.setMinimumSize(animationSize);
    animation.add(this.animationCanvas, new GridBagConstraints());
    return animation;
  }

  @Override
  public void display() throws InterruptedException {
    Runnable doCreateAndShowGUI = new Runnable() {
      public void run() {
        timer.start();
      }
    };
    SwingUtilities.invokeLater(doCreateAndShowGUI);
  }

  /**
   * Sets the listeners for each of the buttons.
   * @param e when you put an action listener onto a button, when the button is clicked,
   *          the action listener will automatically invoke the action performed function.
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
   * Responsible for starting the animation as expected.
   */
  public void startAnimation() {
    try {
      this.display();
      playAnimation.setText("[ Animation is currently playing ]     ");
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    start.setEnabled(false);
  }

  /**
   * Responsible for pausing/resuming the animation when clicked.
   */
  public void pauseAnimation() {
    if (playStatus) {
      timer.stop();
      playStatus = false;
      playAnimation.setText("[ Animation is currently paused ]     ");
    } else {
      timer.start();
      playStatus = true;
      playAnimation.setText("[ Animation is currently playing ]     ");
    }
  }

  /**
   * Responsible for restarting the animation from the first frame.
   */
  public void restartAnimation() {
    this.animationCanvas.frameCount = 0;
    timer.restart();
    pause.setEnabled(true);
    playAnimation.setText("[ Animation is currently playing ]     ");
  }

  /**
   * Responsible for enabling/disabling looping in the animation.
   */
  public void loopActivation() {
    if (loopStatus.equalsIgnoreCase("Yes")) {
      loopStatus = "No";
    } else {
      loopStatus = "Yes";
    }
    loopStatusLabel.setText("[ Looping Enabled: " + this.loopStatus + " ]");
  }

  /**
   * Responsible for increasing the tempo by 1 each time the button is clicked.
   */
  public void increaseSpeed() {
    int newTempoInc = 1000 / (1000 / this.tempo + 1);
    if (newTempoInc > 0) {
      timer.setDelay(newTempoInc);
    }
    this.tempo = newTempoInc;
    this.speed++;
    speedStatus.setText("[ Speed Setting: " + (this.speed) + " ]    ");
  }

  /**
   * Responsible for decreasing the tempo by 1 each time the button is clicked.
   */
  public void decreaseSpeed() {
    int newTempoDec = 1000 / (1000 / this.tempo -1 );
    timer.setDelay(newTempoDec);
    this.tempo = newTempoDec;
    if (this.speed > 0) {
      this.speed--;
    }
    speedStatus.setText("[ Speed Setting: " + (this.speed) + " ]    ");
  }
}
