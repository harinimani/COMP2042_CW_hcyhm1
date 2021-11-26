# COMP2042_CW_hcyhm1 - Brick Breaker Game
This report provides a summary on the major refactoring activities, additions and documentations made to the [given](https://github.com/FilippoRanza/Brick_Destroy) codes for the Software Maintenance (COMP2042) Coursework 2021.
# About the Game
This is a simple arcade video game. Player's goal is to destroy a wall with a small ball. The game has very simple commands: 

|     Keys      |     Action    |
| ------------- | ------------- |
|`SPACE`|Start/Pause Game|
|`A`|Moves the Player LEFT|
|`D`|Moves the Player RIGHT|
|`ESC`|Enter/Exit the Pause Menu|
|`ALT+SHIFT+F1`|Open Debug Console|
 

The game automatically pause if the frame loses focus.
# Gradle Run

Pre-requisite : Java 8 to Java 15

Note: Gradle is not compatible with Java versions after Java 15.

A build file is added as it automatically downloads and configures the dependencies and other libraries used. Gradle Wrapper allows us to run the build file without installing Gradle. When we invoke "gradlew", it downloads and builds the Gradle version specified. In order to run the application from the command line, the following steps can be followed:

  1. Open command prompt and navigate to the folder where the file exists by copying the path and typing: cd <path>
  2. Run the application using the command "gradlew run".

 > gradlew run


# Major Refactoring Activities
**1. Using Model View Controller architectural pattern**

Classes were seperated into respective packages based on the MVC pattern. The MVC pattern seperates an application into 3 main logical componnents: model, view, and controller.
The Model contains core functionality and data of the application. It manages the system data and associated operation of data.
The View displays the data to the user, but cannot influence what the user will do with the data. It defines and manages how the data is presented to the user.
The Controller acts on both the model and view. Controllers inform the model what to do.


**2. Single Responsibility Principle**
  
  Single Responsibility Principle states that an object should only have one responsibility. Hence classes with multiple functionalities do not withhold to this principle.
  One such class is the Crack nested class within the Brick class. The Crack class has be removed as a seperate class to withhold to this principle.
  

**3. Open-Closed Principle**
  
  The SOLID design, Open-Closed principle states the software entities must be open for 'extension' but closed for 'modification'.
