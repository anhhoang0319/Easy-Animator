package cs5004.animator.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Junit test of the Animation Controller (testing controller and action listener).
 */
public class AnimationControllerTest {
  private MockModel model;
  private MockController controller;
  private String buttonCommand;

  @Before
  public void setUp() {
    model = new MockModel();
  }

  // test displaying non-playback view (i.e., text/svg/visual) through the controller
  @Test
  public void testChoosingNonPlaybackView() {
    controller = new MockController(model, "text", buttonCommand);
    assertEquals("Textual/SVG/Visual animation successfully displayed",
            controller.view.nonPlaybackViewTest);
    controller = new MockController(model, "svg", buttonCommand);
    assertEquals("Textual/SVG/Visual animation successfully displayed",
            controller.view.nonPlaybackViewTest);
    controller = new MockController(model, "visual", buttonCommand);
    assertEquals("Textual/SVG/Visual animation successfully displayed",
            controller.view.nonPlaybackViewTest);
  }

  // The tests below are about playback view
  // test clicking on the start button through the controller
  @Test
  public void testActionListener1() {
    buttonCommand = "Start";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation is starting", controller.view.startTest);
  }

  // test clicking on the pause button through the controller
  @Test
  public void testActionListener2() {
    buttonCommand = "Pause";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation is paused", controller.view.pauseTest);
  }

  // test clicking on the restart button through the controller
  @Test
  public void testActionListener3() {
    buttonCommand = "Restart";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation is restarted", controller.view.restartTest);
  }

  // test clicking on the enable/disable Loop button through the controller
  @Test
  public void testActionListener4() {
    buttonCommand = "Enable/Disable Loop";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation loop activated/deactivated", controller.view.loopTest);
  }

  // test clicking on the increase speed button through the controller
  @Test
  public void testActionListener5() {
    buttonCommand = "Increase Speed";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation speed has been increased by 1", controller.view.increaseSpeedTest);
  }

  // test clicking on the increase speed button through the controller
  @Test
  public void testActionListener6() {
    buttonCommand = "Decrease Speed";
    controller = new MockController(model, "playback", buttonCommand);
    assertEquals("Playback animation speed has been decreased by 1", controller.view.decreaseSpeedTest);
  }
}
