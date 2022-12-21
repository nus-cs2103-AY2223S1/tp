---
layout: page
title: Sheryl-Lynn Tan's Project Portfolio Page
---
### Project: Mass Linkers
Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Enhancement: Refactored the GUI and its behavior
* What it does:
  * Implements a UI overhaul with its new design inspired by Outlook. (Pull request [#87](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/87), [#100](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/100), [#72](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/72))
  * Handles the interaction between the ```model``` and ```ui``` components. (Pull request [#133](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/133))
* Justification: To improve user experience with an aesthetically pleasing UI.
* Highlights:
  * Created module panel, ```ModListPanel.fxml``` and ```ModListCard.fxml```, to display the modules taken by selected student. 
  * Handled live updates to UI for commands such as ```mod add```, ```mod mark``` and ```addInt```. (Pull request [#105](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/105), [#129](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/129))
  * Enhance style (Pull request [#133](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/133)) and fix UI bugs. (Pull request [#131](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/131))

#### New Feature: Add Interest
* What it does: Gives students a hassle-free way of adding new interests to a batchmate.
* Justification: It is crucial for students to be able to add interests without overwriting current interests.
* Highlights: 
  * Implemented ```AddInterestCommand``` and ```AddInterestCommandParser``` which handles adding a set of interests to a student. (Pull request [#118](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/118))

#### Other Modifications: Modify Student Information
* What it does: Removes ```Address``` field and adds ```Telegram```and ```GitHub``` fields to a student.
* Justification: Since Mass Linkers serves as a CS contact-sharing platform, it would be a value-add to include relevant socials to a batchmate.
* Highlights:
    * Added private ```Telegram``` and ```GitHub``` fields to ```Student```. (Pull request [#64](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/64))
    * Redesigned user interface of ```Student``` fields such as addition of GitHub and Telegram logos.

#### Other Modifications: Ensure Specificity of Error Messages
* What it does: Displays appropriate error messages for different command input errors. (Pull request [#167](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/167), [#206](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/206))
* Justification: Handling and signalling different types of input errors to users increases the ease of use of Mass Linkers.
* Highlights:
  * The different types of errors include unknown command, missing parameters and invalid index.

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sltsheryl&breakdown=true)

#### Project management
* Added weekly Issues for the team on GitHub.
* Facilitated the workflow on Git (PR and Issues categorisation).

#### Documentation
* **User Guide**:
  * Writeup of project description and FAQ. (Pull request [#211](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/211))
  * Proofread user guide for bugs and enhance writing quality. (Pull request [#176](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/176), [#239](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/239), [#219](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/219), [#210](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/210))
* **Developer Guide**:
  * Writeup of Overview, Use cases 1 - 4 (Pull request [#63](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/63), [#24](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/24)) and Future Developments. (Pull request [#214](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/214))
  * Design diagrams and writeup for ```Add Interest``` feature. (Pull request [#119](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/119))
  * Proofread developer guide for bugs.

#### Community:
* Reviewed other teammate's PRs. (Pull request [#246](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/246), [#218](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/218), [#207](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/207), [#117](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/117), [#95](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/95), [#149](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/149#issuecomment-1292136614), [#95](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/95#issuecomment-1279923223), [#132](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/132#discussion_r1002453049), [#101](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/101#discussion_r998665397))
* Contributed in weekly team meeting.
* Submitted [bugs and feedback](https://github.com/sltsheryl/ped/issues) for [Team Salesy](https://ay2223s1-cs2103t-w08-4.github.io/tp/), another team in the module.

#### Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
