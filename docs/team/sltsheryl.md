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
  * Handles the interaction between the ```model``` and ```ui``` components. (Pull request [#133](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/133))
* Justification: To improve user experience with an aesthetically pleasing UI.
* Highlights:
  * Implemented a UI overhaul, with its new design inspired by Outlook. (Pull request [#87](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/87), [#100](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/100), [#72](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/72))
  * Created module panel to display the modules taken by selected student. 
  * Handled live updates to UI for commands such as ```mod add```, ```mod mark``` and ```addInt```. (Pull request [#105](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/105), [#129](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/129))
  * Imported fonts to enhance style. (Pull request [#133](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/133))
  * Added color-coded tags to modules. 
  * Handled rescaling of window size. (Pull request [#131](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/131))
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
  * Implemented ```AddInterestCommand``` which handles adding a set of interests to a student. (Pull request [#118](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/118))
  * Implemented ```AddInterestCommandParser``` to parse ```addInt``` command arguments.
  * Wrote tests for ```AddInterestCommandTest``` and ```AddInterestParserCommandTest```.

#### Other Modifications: Modify Student Information
* What it does: Removes ```Address``` field and adds ```Telegram```and ```GitHub``` fields to a student.
* Justification: Since Mass Linkers serves as a CS contact-sharing platform, it would be a value-add to include relevant socials to a batchmate.
* Highlights:
    * Added private ```Telegram``` and ```GitHub``` fields to ```Student```. (Pull request [#64](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/64))
    * Created ```Telegram``` and ```GitHub``` classes to handle parsed inputs and display formatting.
    * Redesigned user interface of ```Student``` fields such as addition of GitHub and Telegram logos.
    * Made relevant amendments to ```StudentTest``` tests.
    * Wrote tests for ```GitHubTest``` and ```TelegramTest```.

#### Other Modifications: Ensure Specificity of Error Messages
* What it does: Displays appropriate error messages for different command input errors. (Pull request [#167](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/167), [#206](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/206))
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
  * Writeup of FAQ. (Pull request [#211](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/211))
  * Proofread user guide for bugs and enhance writing quality. (Pull request [#176](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/176), [#239](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/239), [#219](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/219), [#210](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/210))
* **Developer Guide**:
  * Worked on Overview.
  * Writeup of use cases 1 - 4. (Pull request [#63](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/63), [#24](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/24))
  * Design diagrams and writeup for ```Add Interest``` feature. (Pull request [#119](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/119))
  * Refactored UML diagrams to align with project scope. (Pull request [#146](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/146), [#138](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/138))
  * Writeup of Future Developments. (Pull request [#214](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/214))
  * Proofread developer guide for bugs.

#### Community:
* Reviewed other teammate's PRs. (Pull request [#246](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/246), [#218](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/218), [#207](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/207), [#117](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/117), [#95](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/95), [#149](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/149#issuecomment-1292136614), [#95](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/95#issuecomment-1279923223), [#132](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/132#discussion_r1002453049), [#101](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/101#discussion_r998665397))
* Contributed in weekly team meeting.
* Submitted [bugs and feedback](https://github.com/sltsheryl/ped/issues) for [Team Salesy](https://ay2223s1-cs2103t-w08-4.github.io/tp/), another team in the module.

#### Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
