---
layout: page
title: Rezwan Arefin's Project Portfolio Page
---

### Project: TA-Assist
TA-Assist is a desktop application targeted at NUS Teaching Assistants (TA). It helps them to keep track of their students' grades, attendance, and work submission status of relevant modules.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.
Given below are my contributions to the project.

#### New Features 
* Grading related behavior, which includes: 
    * A `SessionData` class to hold the data regarding a session for a student.
    * A `StudentModuleData` to hold all the `SessionData` for a student in a module.  
    * A `JsonAdapterSessionData` class to handle storing `SessionData` data.
    * A `JsonAdaptedStudentModuleData` class to handle storing `StudentModuleData` data.
    * Integration between the new classes with the TA-Assist architecture.
    * The `grade` command to allow users to give grades to students for a session.
* The `view` command to allow users to view session-wise grades of a student in a module.
* Collapsible student cards in the GUI to allow users to hide the student details.
* Feature to display current focused class (if any) in the GUI. 
  * Implemented using bindings. 
* Alert dialog to ask confirmation from users before starting with an empty data file, in case the data load failed.
* Functionality to back up the data file everytime it is loaded successfully.

#### Code contributed 
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=RezwanArefin01&breakdown=true).

#### Project management 
* Refactored AddressBook to TA-Assist in the whole repository. 
* Refactored `Tag` to `ModuleClass` in the whole repository. In collaboration with [Lin Zechen](/docs/team/bubbl3t.md).
* Authored 29 issues to help keep track of current issues and progress.
* Reviewed 30 pull requests to ensure quality and consistency across the project.

#### Enhancements to existing features 
* Fixed the help window not being loaded in Linux environment.
* Extracted a generic `UniqueList<T>` class replacing previously existing `Unique*List` classes.
    * The generic type `T` implements the `Identity<T>` interface, which makes a way to compare two objects of type `T` with a defined identity, ignoring other data fields.    
    * This change was necessary to reduce code duplication and improve code quality.
* Updated the `find` command to filter on the current displayed list instead of all students. 
* Removed empty fields from the student card in the GUI, in order to not have empty spaces between fields. 
* Added text wrapping to the command result box.

#### Documentation 
* Added implementation details regarding loading and saving of `SessionData` and `StudentModuleData` in the DG.
* Added implementation details and sequence diagrams for the `grade` command to the DG. 
* Added implementation details and sequence diagrams for the `view` command in the DG.
* Updated class diagrams in the DG to reflect the changes made due to `grade` command.
* Edited the documents to make them more consistent and readable.  
* Updated styling of the website to make it more visually appealing. 

