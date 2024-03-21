# Project Title

Text baised version of the game mancala  

## Description

This program is a Java implementation of the Mancala board game. It consists of classes representing the pits, stores, players, and the board. The game logic is implemented in the textUI file and handles player moves, stone distribution, capturing opponent stones, and determining the end of the game. This program, includes exception handling to manage invalid moves and pit accesses. 

## Getting Started
Make sure you are in the top level directory and then see Executing program from there

### Dependencies

java,  java.util.ArrayList,  org.junit.jupiter.api.BeforeEach, org.junit.jupiter.api.Test, static org.junit.jupiter.api.Assertions.*,
java.util.Scanner, java.util.List

### Executing program

gradle build
gradle echo 
    > Task :echo
    To run the program from jar:
    java -jar build/libs/Mancala.jar
    To run the program from class files:
    java -cp build/classes/java/main ui.TextUI

java -jar build/libs/Mancala.jar    
Expected output (click on this file so it will display correctly)
+-------+-------+-------+-------+-------+-------+-------+-------+
|       |   4   |   4   |   4   |   4   |   4   |   4   |       |
+   0   +-------+-------+-------+-------+-------+-------+   0   +
|       |   4   |   4   |   4   |   4   |   4   |   4   |       |
+-------+-------+-------+-------+-------+-------+-------+-------+

Player1 enter the pit you want to move stones from: 

(The output will Look like this initially but change based on the specific 
inputs you enter to move different pieces)

## Limitations

There are no knowen errors at this time, all methonds are belived to be fully functioning
The capturing feature was not fully tested and thus may not be fully functional at this time but should work in some capsity

## Author Information
Colin Campbell
1235313
ccampb47@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.
* 0.3
    * FIxed textUI class so it outputs and loops correctly
* 0.2
    * Various bug fixes and optimizations
    * Added missing methods to board and mancallagame classes
* 0.1
    * Used Chat GPT 3.5 to make the AI version of the assignemnt

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
*Chat GPT 3.5 was used for the AI portion of this assingment



