# Project information


## Author: Veselin Roganovic
## Project: Code Quality Evaluator


## Description
JetBrains internship project for *Implement code metrics engine for Qodana*.

This console application aims to check the complexity of methods in your java code and how many of those methods aren't named following the camelCase convention.
Your code is provided through a directory, whose path are you providing to the application.
The complexity of the method here is simply the amount of loops and conditional statements (if/switch statements) in the method.


## How to run it?
This project was entirely written in Java using JetBrains IDE - Intellij IDEA.
In order to run this application, you should clone this repository to your computer and simply run this project in Intellij IDEA.

After you run it, the application will ask you to input the path to the directory containing the java files to be analyzed. 
If you just want to try the app, you can just write *default*, and the application will analyze the default directory, which has one of my old java projects (definitely not my finest work).
After the input, the application will do its magic, and you will see the top three most complex methods and their complexity score and the percentage of methods that do not adhere to the camelCase convention.


## Implementation
At the first glance, I thought that this will be much more complex than it actually was. Right now, at the end, it seems pretty interesting and simple. It was very fun to do this and I also learned something new.

This project can be divided into 3 parts, main class (with main method), method package (with Method and MethodComplexityScore classes, representing methods found in the parsed code and everything related to them) and codeparser package (with CodeParser class, that parses java files, keeps all found methods and evaluates them).
There is also the exceptions package and it holds one special custom exception class for this program.
Every single part serves its purpose, and they connect and work together perfectly.

Main method is the entry point of this program. The program here gets user input about the directory path and gives that directory to the CodeParser and runs his methods and outputs the results.

CodeParser class is a part of codeparser package. It has a purpose to get all the java files from the given directory, parse them, add all the methods to his list of methods, and then it has two functions that are actually visible to user - getting the three most complex methods and getting the percentage of method names that are not in camelCase.
Getting all the java files was done recursively (to also check all the nested directories), searching for all the files with names that end in .java and every java file in the given and nested directories was then parsed.
The parsing was done with the help of JavaParser library (learned something new!), but every file was also preprocessed before it was parsed (because of a JavaParser bug with string templates). After the parsing all the methods found were added to the list of Method objects in CodeParser class.
Getting the most complex methods and percentage of method names that are not in camelCase was pretty easy, because Method class has methods to evaluate method complexity and to check if the method is named following the camelCase convention. All it took then was to run through all the methods and sort them/get the best/get the percentage.

Method class has the two already mentioned method and keeps its own declaration and complexity score (which is an object of the class MethodComplexityScore).
Evaluate complexity method for a goal has to calculate the number of loops and conditional statements. This is written recursively, because each code component inside the method body can also have its own code components (example: nested loops). So the method is calculating all loops and conditional statements on the surface, but also the nested ones.
Method for the camelCase check is even easier - it just checks if the method name is matching the camelCase regex.

MethodComplexityScore class holds the number of loops, number of conditional statements and final score (sum of the previous two). It helps to compare Method class objects and keep track of their complexity scores while evaluating.



