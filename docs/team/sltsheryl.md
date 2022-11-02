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
  * Handled live updates to UI for commands such as ```mod add```, ```mod mark``` and ```addInt```. 
  * Imported fonts to enhance style.
  * Added color-coded tags to modules. 
  * Handled rescaling of window size.
* Changes to Files Made:
  * The following ```.fxml``` files were created or updated to reflect design changes.
    * ```StudentListCard``` to show a student information details
    * ```StudentListPanel``` to list students
    * ```MainWindow``` for the overall layout of the application and to listen for real-time updates on Student and Mod panels
    * ```ModListCard``` to show details of a module
    * ```ModListPanel``` to list modules 
  * Their respective ```.java``` files were also refactored to make the application more dynamic to changes. 
  * The ```DarkTheme.css``` file was extensively refactored to update the UI interface.

#### New Feature: Add Interest
* What it does: Gives students a hassle-free way of adding new interests to a batchmate.
* Justification: It is crucial for students to be able to add interests without overwriting current interests.
* Highlights: 
  * Implemented ```AddInterestCommand``` which handles adding a set of interests to a student.
  * Implemented ```AddInterestCommandParser``` to parse ```addInt``` command arguments.
  * Wrote tests for ```AddInterestCommandTest``` and ```AddInterestParserCommandTest```.

#### Other Modifications: Modify Student Information
* What it does: Removes ```Address``` field and adds ```Telegram```and ```GitHub``` fields to a student.
* Justification: Since Mass Linkers serves as a CS contact-sharing platform, it would be a value-add to include relevant socials to a batchmate.
* Highlights:
    * Added private ```Telegram``` and ```GitHub``` fields to ```Student```.
    * Created ```Telegram``` and ```GitHub``` classes to handle parsed inputs and display formatting.
    * Redesigned user interface of ```Student``` fields such as addition of GitHub and Telegram logos.
    * Made relevant amendments to ```StudentTest``` tests.
    * Wrote tests for ```GitHubTest``` and ```TelegramTest```.

#### Other Modifications: Ensure Specificity of Error Messages
* What it does: Displays appropriate error messages for different command input errors.
* Justification: Handling and signalling different types of input errors to users increases the ease of use of Mass Linkers as new users are aware of the amendments required, should they key in an invalid command. 
* Highlights:
  * The different types of errors include:
    * Unknown command
    * Missing parameters
    * Invalid index
  * Made amendments to the relevant test files when the above input errors were made.

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sltsheryl&breakdown=true)

#### Project management
* Added weekly Issues for the team on GitHub.
* Facilitated the workflow on Git (PR and Issues categorisation).

#### Documentation
* **User Guide**:
  * Worked on project description.
  * Writeup of FAQ.
  * Proofread user guide for bugs.
* **Developer Guide**:
  * Worked on Overview.
  * Writeup of use cases 1 - 4.
  * Design diagrams and writeup for ```Add Interest``` feature.
  * Refactored UML diagrams to align with project scope.
  * Writeup of Future Developments.
  * Proofread developer guide for bugs.

#### Community:
* Reviewed other teammate's PRs.
* Contributed in weekly team meeting.
* Submitted [bugs and feedback](https://github.com/sltsheryl/ped/issues) for team Salesy, another team in the module.

#### Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
