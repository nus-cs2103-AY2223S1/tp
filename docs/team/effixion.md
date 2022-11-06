---
layout: page
title: Sun Yu Ting's Project Portfolio Page
---

## Project: SoCompiler

### Overview

SoCompiler is the sole app that university students will need to streamline their everyday routines.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **`Module` Class**:
  * Added the `Module` Class and its associated classes.(Pull request [#45](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/45))
  * The associated classes include:
    * `LectureDetails`
    * `ModuleCode`
    * `TutorialDetails`
    * `ZoomLink`
    * `AssignmentDetails`

* **`Telegram` Class**:
  * Added the `Telegram` Class that is associated with a `Person` (Pull request [#115](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/115))

* **Replaced `Address` with `ModuleCode` in `Person`**:
  * Redid the tests for the affected classes that used `Address` in the `Person` Class. (Pull request [#113](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/113))

* **Testing**:
  * Did testing for the following classes:
    * `Module`
    * `ModuleCode`
    * `LectureDetails`
    * `TutorialDetails`
    * `ZoomLink`
    * `AssignmentDetails`
    * `Telegram`
    * `UniqueModuleList`
    * `ModuleDetailsContainsKeywordPredicate`
    * And others... (You can refer to the link under "Code contributed"!)

* **Bugs Fixed**:
  * Found a bug relating to the edit module command while testing the application. Created issue [#121](https://github.com/AY2223S1-CS2103T-W12-1/tp/issues/121).
  Fixed this bug after posting the issue onto GitHub. (Pull request [#143](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/143))
  * 

* **Code contributed**:
  [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=effixion&breakdown=true)

* **Project management**:
  * Helped to approve and merge Pull Requests by my team mates.
  * Did some light debugging and to create the issues on GitHub.
  * Made sure our team adheres to deadlines.
  * Managed the release of v1.3 of SoCompiler.

* **Enhancements to existing features**:
  * Users can now add a `Module` to our application to keep track of details related to their modules that they are currently taking.
  * Users can now associate a `Person` to a `ModuleCode` and his/her `Telegram` handle.

* **Documentation**:
    * User Guide:
      * Did the v1.3 iteration of the UG. (Pull request [#122](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/122))
    * Developer Guide:
        * Did the `Module` Class Section in the DG.
        * Updated the `Model` Component in the DG.
        * Added UML diagrams for these classes:
          * `Module`
          * `Model`
          * `AddModuleCommand`

* **Community**:
  * Reported bugs and suggestions for other teams in the class. (Examples: [1](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/147), 
  [2](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/144), [3](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/130),
  [4](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/134))

* **Contributions to team-based tasks**:
  * Helped to organise and categorise the bugs after the PE-D in our team's [Google Document](https://docs.google.com/document/d/1OFhvvTXxh97xsj_ng3f3Gmx66HFJV9Pazy5_gCdhT4o/edit?usp=sharing).
  The bug list can be found under the section "Post PE-D Bug Lists".
  * Helped to manage the issues posted by the nus-se-bot on our team's [issues page](https://github.com/AY2223S1-CS2103T-W12-1/tp/issues).

* **Tools**:
  * Intellij
  * SourceTree
  * Git
  * GitHub

