---
layout: page
title: Chan Chung Loong's Project Portfolio Page
---

### Project: FoodWhere

**FoodWhere is a desktop application for managing your food stall addresses and reviews.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Features**
  * Implemented `Rating` class, which adds a rating for a `Review` for critics to rate a stall from 0 to 5 inclusive. ([#151](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/151)) 
  * Implemented copying of `Stall` and `Review` data onto the computer clipboard, which enables the user to right-click on either a `Stall` or `Review` to copy the name, address, or any other information such as tags to the system clipboard. ([#169](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/169]), [#237](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/237))
    * Justification: This feature allows the user to easily edit the content without typing out the entire review if the data is very long into the command text box.
* **Enhancements to Graphical User Interface (GUI)**
  * Modified user interface to have two separate lists for `Stall` and `Review`. ([#46](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/46), [#82](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/82), [#88](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/88), [#99](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/99), [#126](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/126), [#138](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/138), [#162](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/162))
    * In the Stall list, the `name`, `address`, and `tags` of the Stall are listed.
    * In the Review list, the `name` of stall, `address` of stall, `date` of visit, review `content`, `rating` and `tags` are listed.
    * For `ratings` in `Review`, each rating is represented by a star which allows for quick visualisation.
  * Justification:
    * This enhancement is important as all other features implemented must be reflected correctly in both the stall list and review list.
* **Enhancements to existing features**
  * Refactoring `Address`, `Name` and `Tag` into `model.commons` package ([#119](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/119), [#224](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/224))
  * Justification:
    * Refactoring these fields into their own common packages helped to reduce confusion as all these fields are shared by both `Stall` and `Review`, and hence, they should be in a common package rather than situated in either `model.stall` or `model.review`.
* **Enhancements to new features**
  * Added error messages for any missing mandatory fields in RAddCommand and SAddCommand. ([#216](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/216))
* **Enhancements to validation checks and error messages**
  * Ensured that all validation checks and exceptions are handled properly.
  * Ensured that all error messages shown to the user upon execution of an incorrect command or missing fields are correct and easy to understand, so that the user can recover from any errors and continue using the application.
* **Code Contributed**
  * [Link to RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=HIkoya&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16)
* **Project Management**
  * Assisted in approving and merging PRs of implementations done by other teammates.
* **Documentation**
  * User Guide
    * Updated name to FoodWhere and modify introduction of application. ([#22](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/22))
    * Added new commands into command summary table and updated screenshots of User Interface. ([#125](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/125), [#227](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/227))
  * Developer Guide
    * Added use cases 1 to 5, 8 and table of user stories. ([#38](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/38), [#259](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/259))
    * Added implementation of Review class and updated User Interface UML diagrams ([#180](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/180))
    * Added use case for clear command ([#259](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/259))
* **Community**
  * Updated the user guide URL in the HelpWindow. ([#44](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/44))
  * Modify the default package name to `foodwhere`. ([#71](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/71))
  * Enable assertions and modified build.gradle to create the correct JAR filename. ([#81](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/81), [#184](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/184)) 
  * Reviewed PRs with non-trivial review comments. ([#95](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/95))
  * Reported bugs and suggestions for other team in the same CS2103/T class. ([PE-Dry Run](https://github.com/Hikoya/ped/issues))
  * Improve test coverage of project. ([#329](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/329))
  * Answered questions or helped out to solve tasks posted by others in forum. ([Forum](https://github.com/nus-cs2103-AY2223S1/forum/issues?q=is%3Aissue+commenter%3AHikoya)) 
* **Tools**
  * IntelliJ IDEA
  * JavaFX Scene Builder
