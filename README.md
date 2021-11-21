# COMP2042_CW_hcyhm1 - Brick Breaker Game
This report provides a summary on the major refactoring activities, additions and documentations made to the given codes for the Software Maintenance (COMP2042) Coursework 2021.
# About the Game
This is a simple arcade video game. Player's goal is to destroy a wall with a small ball. The game has very simple commmand: 

SPACE - Start/Pause the game 

A - move the player LEFT

D - move the player RIGHT 

ESC - Enter/Exit the pause menu 

ALT+SHITF+F1 - open console 

The game automatically pause if the frame loses focus.
# Gradle Run

# Major Refactoring Activities
**1. Using Model View Controller architectural pattern**

Classes were seperated into respective packages based on the MVC pattern. The MVC pattern seperates an application into 3 main logical componnents: model, view, and controller.
The Model contains core functionality and data of the application. Manages the system data and associated operation of data.
The View displays data to user, but cannot influence what user will do with the data. It defines and manages how the data is presented to the user.
The Controller acts on both the model and view.
