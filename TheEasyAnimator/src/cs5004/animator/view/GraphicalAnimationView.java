package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * The GraphicalAnimationView is a class that implements the AnimationView interface and produces a
 * visual representation of the data when it is called from the command line.
 *
 * @param <ShapeAbs> this class uses the ShapeAbs data types to render its output.
 */
public class GraphicalAnimationView<ShapeAbs> implements AnimationView {
  protected JFrame frame;
  protected JPanel panel;
  protected DrawAnimation<ShapeAbs> animationCanvas;
  protected ArrayList<ArrayList<ShapeAbs>> frameList;
  protected int[] bounds;
  Timer timer;
  int tempo;
  
  /**
   * This constructor creates a JFrame that contains a JPanel displaying the graphic output of the
   * data as well as a scroll bar that is used for when the user cannot completely see the
   * animation.
   *
   * @param frames an array list of array lists of ShapeAbs objects in the order of appearance.
   * @param bounds the canvas dimensions
   * @param tempo  the time interval between frames
   */
  public GraphicalAnimationView(ArrayList<ArrayList<ShapeAbs>> frames, int[] bounds, int tempo) {
    this.bounds = bounds;
    this.frameList = frames;
    this.frame = new JFrame("Easy Animator");
    this.panel = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());

    this.animationCanvas = new DrawAnimation<ShapeAbs>(this.frameList);

    animationCanvas.setPreferredSize(new Dimension(this.bounds[2], this.bounds[3]));
    frame.setPreferredSize(new Dimension(this.bounds[2], this.bounds[3]));

    JScrollPane scroll = new JScrollPane(animationCanvas,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    frame.getContentPane().add(scroll);
    frame.pack();
    frame.setVisible(true);

    this.tempo = tempo;
    tempo = 1000 / tempo;
    this.timer = new Timer(tempo, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();

        if (animationCanvas.frameCount == frameList.size() - 1) {
          timer.stop();
        }
      }
    });
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

}
