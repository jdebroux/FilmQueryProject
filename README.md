## Film Query Project

Week 7 homework for Skill Distillery Java bootcamp.

### Overview

Welcome to the sdvid database!

In this command-line app, you will be able to search through a database to find a film either by ID or by searching by keyword and then display the films data.  The films data displayed includes:

* Title
* Release Year
* Rating
* Language
* Description
* Category
* A list of the actors
* A list of all the copies of that title and their condition

If you want more information about the film there is a sub-menu to get it, once a film is found.  I also edited the toString methods to make things more user friendly in terms of readability.  I also created a specific method just for the try/catch statements around a user inputing an integer since it happens more than a couple times throughout this project.

### Technologies Used

* Java
* Eclipse
* Atom
* Git/Github
* Terminal
* Abstraction
* Poly-Morphism
* Inheritance
* Encapsulation
* SQL
* Maven

### Lessons Learned

Through this app I was able to learn how to connect to a database and communicate with it.  I experimented with the use of prepared statements to extract specific data.  That being said, I also learned why using prepared statements are so beneficial and how dangerous regular statements can be due to them leaving a possibility open to SQL injection.  I also learned how to incorporate Maven, and use it to download needed files automatically.

### Attack Plan

In starting this app, I first looked at the ERD and figured out which statements I was going to have to make, as well as, which tables I was going to have to join in order to get the information I wanted.  After I knew that, it was easy to make the connections to the database, and create the menu's for the user to connect everything together.  Completing the basic requirements early left a decent amount of time to work on some stretch goals.

Stretch goals I worked on:

* Added a sub-menu so the user could choose how much information they wanted to see
* Added the films category to the printed description
* When viewing film details, the user sees all copies of that film and their condition
* Writing J-Unit tests for each of the methods that search the database
