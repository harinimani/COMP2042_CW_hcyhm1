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
  Hence CrackController from BrickController class, PauseMenuView from GameBoardView and LevelsModel from WallController, have all been made as a separate class to withhold the Single Responsibility Principle.
  

**3. Open-Closed Principle**
  
 The SOLID design, Open-Closed principle states the software entities must be open for 'extension' but closed for 'modification'. Hence an abstract PlayerController class was created with methods that must be present in future player extensions.
 
**4. Encapsultation**
 
 Getter and Setter methods are added in to avoid data violation. Major changes made in the WallController, PlayerModel, BrickController classes.
 
**5. Adding Path Variables**
 
 Using Path variables in additional classes for ease of future modifications. Example in the HomeMenuView,LeaderboardView,ScoresTableView
 
**6. Standardize variable and method names** 
 
 Renamed methods and variables based on the Oracle Java Variable and Method Naming Convention Documentation. Significant changes made in Player class.
 
**7. JUnit** 
 
 JUnit test classes added to the WallController,PlayerModel, BonusBrickModel, ClayBrickModel, CementBrickModel, SteelBrickModel, TimesaverBrickModel, LevelsModel.
 
 
# Major Additions
**1. Adding Background Images** 
 
 Added a Background image for the HomeMenu, Leaderboard, Instruction views, as well as the buttons.
 
**2. HomeMenuView**  
 
 Significant additons made to HomeMenuView. Converted it to a JFrame and added in the Info and Scores buttons as well. 
 
**3. Instructions View**  
 
 Added in an Instructions View to display the "How To Play" info to the users. 

**4. Game Timer** 
 
 Added an in-game timer to keep track of the total time taken the player has taken to complete the game. Used in calculating the scores as well. 
 
**5. Scores and Leaderboard**
 
 A ScoreController, ScoreTableView and LeaderBoardView classes were added in to store and display the top 5 scores in a JTable format. Displays the player name, total number of bricks broken, minutes taken and seconds taken. Also have a pop-up JOptionPane to notify and request user to enter name to store into leaderboard.

**6. Additional Levels** 
 
 Three additional levels were added in. Introduce three new bricks, Bonus, TimeSaver and Slow bricks. Bonus brick increases total bricks broken by 4 on impact. TimeSaver brick deducts 5 seconds from the player's time taken. Slow brick slows the in-game player's movement for 5 seconds.
 
 
