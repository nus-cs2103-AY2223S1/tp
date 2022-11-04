---
layout: page
title: Png Yi Wei Jonas' Project Portfolio Page
---

### Project: SoConnect

### Overview

SoConnect is a desktop address book application designed for National University of Singapore (NUS) Computer Science Undergraduates to keep track of their University Social Circle. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.


### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jonaspng&breakdown=true)

* **New Feature**: Added the ability to open contacts' GitHub Profile page with a Command

  * What it does: Allows the user to open contacts' GitHub Profile Page on user default browser using `github INDEX` command

  * Justification: This feature allows users to browse their Peers'/Teaching Assistants'/Professors' GitHub Projects easily. This help keep users up to date with their social circle's computing projects.

  * Credits: Regex to validate valid format for Github username taken from [here](https://github.com/shinnn/github-username-regex)


* **Modified Feature**: Modified `add` command into 3 different commands that add Students, Professors and Teaching Assistants into the application.

  * What it does: Allow users to add Student, Professor and Teaching Assistant into application using `student`, `prof` and `ta` command respectively.

  * Justification: This feature allows users to classify their contacts into Students, Professor and Teaching Assistants whom are all essential person in user's university life.

  * Highlights: First ever major changes from AB3 address book repository base code. Had to modify many parts of the code (e.g. storage for json, test cases) due to these changes made to the previous `add` command.


* **Project management**:

  * Team Leader for Project

  * Managed releases `v1.3(trial)` - `v1.4` (3 releases) on GitHub


* **Enhancements to existing features**:

  * Enhanced `clear` command to prevent accidental clearing of all contacts by forcing users to add an extra "-confirm" tag

  * Wrote additional tests for existing features to increase coverage by 4.84% (Pull requests [#182](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/182), [#72](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/72))


* **Documentation**:

  * User Guide:

    * Added documentation for the features `github` and `edit`: [#95](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/95)

    * Changed `import` command photos to match jar file name: [#126](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/126)

    * Rectified various formatting and style errors: [#120](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/120)

    * Developer Guide:

      * Added implementation details and Use Case for the `github` command: [#74](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/76)

      * Update Model class diagram and Storage class diagram: [#194](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/194)

      * Added Sequence Diagram and activity diagram for `github` command: [#74](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/76)
    
* **Review/mentoring contributions Links**

  * [Link 1](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/93#issuecomment-1288150131)

  * [Link 2](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/74#discussion_r999120307)
