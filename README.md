# The Easy Animator

*A simple 2D animation player (built in Java)*

The project is intended for learning and practicing CS concepts/topics, which includes object-oriented design, MVC architecture, testing, GUI etc. 


**The scope of this assignment** was to implement the MVC framework that divided the assignment into three components: model, view and the controller. this assignment can visualize some simple animations such as:

* Towers of Hanoi
* The Night Sky
* Big Bang and Crunch

**How to play animation:**
The animator is designed to run in the terminal with a JAR file and command-line argument. The program reads one of the existing formated and provided input text files in the specified play mode (can be found under "util/code/"). If the chosen play mode is visual, users need to specify the play speed (e.g., "-speed 50"; the bigger the number, the greater the speed). In svg/text mode, if the user would like to save the svg/text output to a file, this can be done by typing "-out" and specifying an output file name in the command-line. Here are some valid examples of the command-line argument to run the animator: 

- java -cp EasyAnimator.jar cs5004.animator.controller.EasyAnimator -in toh-3.txt -view text 
- java -cp EasyAnimator.jar cs5004.animator.controller.EasyAnimator -in toh-3.txt -out -outfilename -view svg 
- java -cp EasyAnimator.jar cs5004.animator.controller.EasyAnimator -in toh-5.txt -view visual -speed 20 
- java -cp EasyAnimator.jar cs5004.animator.controller.EasyAnimator -in toh-5.txt -view playback

**The structure of the project**
The project has been structured into various packages that define various components of the project submission. In this document, the methods and functionalities implemented by each package including classes and methods any sub-packages are explained. The packages in the project are:

* model
* controller
* view
* util

cs5004.animator.model package:
The scope of this package is to implement the model component of the Easy Animator project. It includes interfaces, abstracts, and subclasses to handle functionalities which are for Shapes and Animation Types, and includes an AnimationModel interface and AnimationModelImpl class that extends the AnimationModel interface.

util package:
The util package consists of EasyAnimator.jar to run the program and other files given to support running the project.

cs5004.animator.view package:
The scope of this package is to implement the view component of the Easy Animator project and implements the GraphicalAnimationView, PlaybackView, SVGAnimationView and TextualAnimationView. The GraphicalAnimationView and PlaybackView view is supported by the inbuilt functionalities of existing Java functions. It uses both JFrame, a heavy weight container used as the top-level window and also JPanel a lightweight container generally used to organize Graphical user interface components for the visual views. PlaybackView allows user to adjust speed, start, pause the animation.

The AnimationView interface in this package represents a view that displays or projects a view from the model. The interface has one methods:
* Display: Displays the specific view's output.
This interface is extended to classes: GraphicalAnimationView, PlaybackView, VisualView and TextualAnimationView.

cs5004.animator.controller package:
The scope of this package is to implement the controller and acts as a bridge between the model and view functions. This AnimationController class is the animation controller class that coordinates between the animation model and the animation view. Based on user's input, the controller gets certain information from the model and then instructs the specified view to display animation. The Transform class was created to essentially hold all of the shape's attributes. The ShapeEntry class was created for the sole use of the hashmap where the program could store both the index as well as the shape type, which would ultimately be used by the views.

The main-method (EasyAnimator.java): The main method is implemented by EasyAnimator.java, which directly responds to the user's input in the command line, transfers information to the controller, which is then processed through the model to generate a view based on the input files.  It helps decide which of the views are being called for the data text files.


Testing: I have written four test files that create mock view, model, and controller to test if program running correctly.
