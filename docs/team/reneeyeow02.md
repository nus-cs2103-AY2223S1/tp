---
layout: page
title: Renee's Project Portfolio Page
---

### Project: HR Pro Max++

HR Pro Max++ is a desktop team management application for SME company to manage their team members and project details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Adding in display windows for Task and Staff lists. 
    * What it does: Allows user to view Project, Task and Staff lists in the GUI.
    * Justification: Is a necessary feature as the user needs to be able to view the lists in the GUI to use the commands that require an index number.
    * Highlights: This feature required the creation of a new window for each list, which required understanding of the JavaFX framework. 
    Changes to the MainWindow.java and FXML files were also required to allow the display of the new windows. 
    New java and FXML files also had to be created for the cards and panels involved in the display.
    * Credits: Took inspiration from AB3's person object.
    

  * Implemented Leave status for Staff.
    * What it does: Allows user to view the leave status of a staff member.
    * Justification: Allowing the user to view the availability of a staff member is part of the main features of HR Pro Max++.
    

  * Implemented view command.
    * What it does: Allows user to view the Staff list attached to each Project.
    * Justification: Allowing the user to view the Staff list of a Project is part of the main features of HR Pro Max++.
    

  * Added Staff class to contain Staff fields. 
    * What it does: Allows user to create Staff objects.
    * Justification: Allowing the user to create Staff objects is part of the main features of HR Pro Max++.
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=reneeyeow02&breakdown=true)


* **Project management**:
  * Create test cases for Staff fields. (Pull request [#71](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/71))
  

* **Enhancements to existing features**: 
  * Created special tags for Task completion status - green for completed, red for incomplete.
    * What it does: Allows user to view the completion status of a task in the GUI. Tag is updated when a Task is marked as done or undone.
  * Added headers to the Project, Task and Staff lists.
    * What it does: Gives user a better idea of what the list they are looking at is about.
  * Added title fields for values in each Project, Task and Staff card.
    * What it does: Gives the user a better idea of what each field in the cards are about.
  * Enhanced display to use Split panes for a 1/3 split of the display.
  * Allow wrap text for long text in the display.
    * Justification: Sometimes long text is entered into the fields, and it is better to allow the text to wrap around instead of cutting it off.
  * Adjust app window size to fit all the display.
    * Justification: The app window size is adjusted to fit all the display so that the user does not have to scroll to see the entire display.
   
 
* **Documentation**: 
  * General outline of the User Guide. Standardise the format of the User Guide and fix grammatical errors.
  * Added documentation for the features `list` and `sort` for Projects. 
  * Added glossary tables for Project, Task and Staff. (Pull request [#192](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/192))
  * Created a table of contents and added Back to top functionality (Pull request [#141](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/141))
  * Added a section on the GUI to the User Guide.

* **Community**: to be confirmed

* **Tools**: to be confirmed

* _{you can add/remove categories in the list above}_
