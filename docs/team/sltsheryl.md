---
layout: page
title: Sheryl-Lynn Tan's Project Portfolio Page
---

### Project: Mass Linkers
Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

####New Enhancement: User Interface

I was in charge of building the application's UI interface and handling the interaction between the model and the ui components.
1. Implemented a UI overhaul, with its new design inspired by Outlook. 
2. Created module panel to display the modules taken by selected student. 
3. Handled live updates to UI when for commands such as ```mod add```, ```mod mark``` and ```addInt```.
4. Added color-coded tags to modules. 
5. Redesigned interface of ```Person``` fields such as addition of GitHub and Telegram logos.

**Changes to Files Made:**

The following ```.fxml``` files were created or updated to reflect design changes.
* ```PersonListCard``` to show a student information details
* ```PersonListPanel``` to list students
* ```MainWindow``` for the overall layout of the application and to listen for real-time updates on Person and Mod panels
* ```ModListCard``` to show details of a module
* ```ModListPanel``` to list modules

Their respective ```.java``` files were also refactored to make the application more dynamic to changes. 

The ```DarkTheme.css``` file was extensively refactored to update the UI interface.

####New Feature: Add Interest

I also worked on the add interest feature and updated ```Person``` with new fields. 

The ```add int``` command gives users a hassle-free way of adding an interest to a student.

```AddInterestCommandTest``` and ```AddInterestParserCommandTest``` were also created to include testing overage on the Add Interest feature. 


####Code contributed

[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sltsheryl&breakdown=true)

####Project management
* Added weekly Issues - tasks that were required to be completed by the team. 
* Facilitated the workflow on Git (PR and Issues categorisation)


####Documentation
* **User Guide**:
  * Worked on project description
  * Proofread user guide for bugs
* **Developer Guide**:
    * Writeup for use cases 1 - 4
    * Design diagrams and writeup for Add Interest feature
    * Proofread developer guide for bugs

####Community:
* Reviewed other teammate's PRs
* Contributed in weekly zoom meeting

####Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
