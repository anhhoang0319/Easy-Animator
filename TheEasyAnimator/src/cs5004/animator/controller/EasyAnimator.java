package cs5004.animator.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.model.AnimationModelImpl;

/**
 * The EasyAnimator class represents the controller, which directly responds to the user's input in
 * the command line. It helps decide which of the views are being called for the data text files.
 */
public class EasyAnimator extends JFrame {
  /**
   * This is the main method of the assignment. This is used to parse and run command
   * line input.
   * @param args command line input
   * @throws IOException when a file cannot be written out
   * @throws InterruptedException when a process is interrupted
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    JFrame frame = new JFrame();
    // validity check - pop error window if invalid
    if (!argumentIsValid(args)) {
      JOptionPane.showMessageDialog(frame, "Invalid argument.", "Message",
              JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // extract the arguments from the array into local variables
    String viewType = null;
    String inFileName = null;
    String outFileName = null;
    String speed = "1";
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0) {
        if (args[i].equals("-view")) {
          viewType = args[i + 1];
        } else if (args[i].equals("-in")) {
          inFileName = args[i + 1];
        } else if (args[i].equals("-out")) {
          outFileName = args[i + 1];
        } else {
          speed = args[i + 1];
        }
      }
    }

    // create a populated animation model by reading data from a txt file
    assert false;
    FileInputStream fis = new FileInputStream(inFileName);
    InputStreamReader isr = new InputStreamReader(fis);
    new AnimationReader();
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    AnimationModelImpl model = AnimationReader.parseFile(isr, builder);
    ArrayList<HashMap<String, String>> svgStringMapList = builder.getStringMapList();
    ArrayList<HashMap<String, Double>> svgDoubleMapList = builder.getDoubleMapList();

    // create a controller and pass the control to the controller
    AnimationController controller;
    controller = new AnimationController(model, viewType, outFileName, speed, svgStringMapList, svgDoubleMapList);
  }

  /**
   * This static method is created to verify that the arguments passed through the command line are
   * valid arguments.
   *
   * @param args the command line arguments
   * @return a boolean value true or false
   */
  static boolean argumentIsValid(String[] args) {
    // check length validity: min 4, max 8, and has to be an even number
    if (args.length < 4 || args.length > 8 || args.length % 2 == 1) {
      return false;
    }

    // check if -view and -in arguments are present
    // also check argument pair validity
    boolean viewIsPresent = false;
    boolean inIsPresent = false;
    for (int i = 0; i < args.length; i++) {
      // check -view pair
      if (i % 2 == 0) {
        if (args[i].equals("-view")) {
          viewIsPresent = true;
          if (!args[i + 1].equals("text") && !args[i + 1].equals("svg")
                  && !args[i + 1].equals("visual") && !args[i + 1].equals("playback")){
            return false;
          }
        }
        // check -in pair
        else if (args[i].equals("-in")) {
          inIsPresent = true;
          if (!args[i + 1].contains(".txt")) {
            return false;
          }
        }
        // check -out pair
        else if (args[i].equals("-out")) {
          if (!args[i + 1].contains(".txt") && !args[i + 1].contains(".svg")) {
            return false;
          }
        }
        // check -speed pair
        else if (args[i].equals("-speed")) {
          String speedString = args[i + 1];
          try {
            int speedInt;
            speedInt = Integer.parseInt(speedString);
            if (speedInt < 1) {
              return false;
            }
          } catch (NumberFormatException nfe) {
            return false;
          }
        }
      }
    }
    return viewIsPresent && inIsPresent;
  }
}
