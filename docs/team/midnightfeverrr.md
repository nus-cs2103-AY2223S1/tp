---
layout: page
title: Marciano Renzo William's Project Portfolio Page
---

## Project: ModtRekt

ModtRekt is a desktop app which helps undergraduate students plan their modules over their course
of study, and manage their tasks and deadlines for each of them.

ModtRekt is optimized for use via a CLI while still having the benefits of a GUI.

### Summary of Contributions

* **Major Enhancement**: Added backend to frontend functionality.
  * What it does: Allows visible changes to the GUI upon user input.
  * Justification: This feature allows user to view data that they have input to _ModtRekt_, such that the data is displayed in a cohesive window.
  * Highlights: This feature was done mainly by refactoring from AB3 Template.
* **Major Enhancement**: Added and designed a **clean and user-friendly Graphical User Interface (GUI)**.
  * What it does: Improves User Interface/User Experience in _ModtRekt_.
  * Justification: Improves usability by ensuring that the user has a great experience in using the application.
  * Highlights: Did an initial mockup in Figma as well as reusing the color scheme from AB3. Did various skeleton code for various UI components.
  * Credits: [Jonathan](https://github.com/jontmy) helped with the future iterations.
  * Major Enhancement: Implemented `done module`, `undone module`, and `list module` commands.
    * What it does:
      * `Done Module`: Allows user to archive a particular module he/she has completed.
      * `Undone Module`: Allows user to undo an archiving of a particular module he/she has completed.
      * `List Module`: Allows the user to list the all module/active module in the GUI.
    * Justification: User can get rid of the module that they are done with, to avoid cluttering. User can also undo their decision.
    * Highlights: This three commands function helps to 'glue' the three components of the app altogether.
      * In the `Done Module` command, when a module is marked as `done`, it updates a part of GUI which shows the information of the total MCs user has fulfilled.
      * In the `List Module` command, user can avoid cluttering in the GUI by hiding the module that they have marked as done.
* **Minor Enhancement**: Added several JUnit tests in testing the code.
* **Minor Enhancement**: Did mock-up of the GUI using Figma.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=midnightfeverrr&breakdown=true&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Other contributions**: Project presentation.
  * Part of the team that did the product pitch.
  * Did several part of User Guide as shown in [here](UserGuide.md)
  * Did several part of Developer Guide as shown in [here](DeveloperGuide.md)
