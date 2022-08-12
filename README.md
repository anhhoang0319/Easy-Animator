# The Easy Animator

*A simple 2D animation player (built in Java)*

The project is intended for learning and practicing CS concepts/topics, which includes object-oriented design, MVC architecture, testing, GUI etc. 


The scope of this assignment was to implement the MVC framework that divided the functionalities of implementing animation into three components: model, view and the controller to project animations such as:
* Small Demonstration
* Towers of Hanoi
* The Night Sky
* Big Bang and Crunch
The project has been structured into various packages that define various components of the project submission. In this document, the methods and functionalities implemented by each package including classes and methods any sub-packages are explained. The packages in the project are:
* model
* controller
* view
* utils
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


Testing: We have written four test files that  create mock view, model, and controller to test if program running correctly.
