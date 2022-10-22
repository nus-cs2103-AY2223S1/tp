---
layout: page
title: Sheryl-Lynn Tan's Project Portfolio Page
---

### Project: Mass Linkers
Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Enhancement: Refactored the GUI and its behavior
* What it does:
  * Displays the new user interface design.
  * Handles the interaction between the ```model``` and ```ui``` components.
* Justification: To improve user experience with an aesthetically pleasing UI.
* Highlights:
  * Implemented a UI overhaul, with its new design inspired by Outlook. 
  * Created module panel to display the modules taken by selected student. 
  * Handled live updates to UI when for commands such as ```mod add```, ```mod mark``` and ```addInt```. 
  * Added color-coded tags to modules. 
  * Handled rescaling of window size.
* Changes to Files Made:
  * The following ```.fxml``` files were created or updated to reflect design changes.
    * ```PersonListCard``` to show a student information details
    * ```PersonListPanel``` to list students
    * ```MainWindow``` for the overall layout of the application and to listen for real-time updates on Person and Mod panels
    * ```ModListCard``` to show details of a module
    * ```ModListPanel``` to list modules 
  * Their respective ```.java``` files were also refactored to make the application more dynamic to changes. 
  * The ```DarkTheme.css``` file was extensively refactored to update the UI interface.

#### New Feature: Add Interest
* What it does: Gives users a hassle-free way of adding new interests to a student.
* Justification: It is crucial for users to be able to add interests without overwriting current interests.
* Highlights: 
  * Implemented ```AddInterestCommand``` which handles adding a set of interests to a student.
  * Implemented ```AddInterestPaerserCommand``` to parse an ```addInt``` command arguments.
  * Wrote tests for ```AddInterestCommandTest``` and ```AddInterestParserCommandTest```.

#### Other Modifications: Align Person with Mass Linkers Purpose
* What it does: Removes ```Address``` and add new ```Telegram```and ```GitHub``` fields to a student.
* Justification: Since Mass Linkers serves as a CS address book, it would be a value-add to include relevant socials to a student.
* Highlights:
    * Added private ```Telegram``` and ```GitHub``` fields to ```Person```.
    * Created ```Telegram``` and ```GitHub``` classes to handle parsed inputs and display formatting.
    * Redesigned user interface of ```Person``` fields such as addition of GitHub and Telegram logos.
    * Made relevant amendments to ```PersonTest``` tests.
    * Wrote tests for ```GitHubTest``` and ```TelegramTest```.

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sltsheryl&breakdown=true)

#### Project management
* Added weekly Issues - tasks that were required to be completed by the team. 
* Facilitated the workflow on Git (PR and Issues categorisation).

#### Documentation
* **User Guide**:
  * Worked on project description.
  * Proofread user guide for bugs.
* **Developer Guide**:
    * Writeup for use cases 1 - 4
    * Design diagrams and writeup for ```Add Interest``` feature.
    * Proofread developer guide for bugs.

#### Community:
* Reviewed other teammate's PRs.
* Contributed in weekly team meeting.

#### Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
