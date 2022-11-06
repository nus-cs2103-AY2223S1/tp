---
layout: page
title: Liu Han's Project Portfolio Page
---

### Project: JerylFypManager

JerylFypManager is a desktop application catered to professors to manage and track the progress for students’ final
year project (FYP), as well as serving as a platform for professors to provide feedback on their students’ progress.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
10 kLoC. This project is based on AddressBook - Level 3.

Given below are my contributions to the project.


* **New Feature**: <br>
`ListDeadlineCommand` - version 1.3 of ListDeadlineCommand to allow users to view the deadline lists of a specific student
(https://github.com/AY2223S1-CS2103-F09-1/tp/pull/155)
  * What it does: 
    * Allow professor to view the progress updates uploaded by his student through deadline lists.
  * Justification:
    * This feature improves the product since it provides a convenient way for professor to keep track of the progress made by his student
  * Further modification:
    * As this feature is added before the JAR v1.3 release but after the PE-D, it is not tested in PE-D. Unfortunately, it is removed from the product since it is not required to modify this feature in v1.4.
<br>


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sweetpotato0213&breakdown=true)

* **Project management**:
  * Approved PRs and merging them.

* **Enhancements to existing features**:<br>
  `HelpCommand` extension - version 1.2 of HelpCommand to allow users to specify the specific command they need help on
  (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/102)
* What it does: The initial version of `HelpCommand` only allows users to enter `Help` and directs user to the User Guide without considering what users really want to know about
  (1) Different from AB3, `HelpCommand` can accept arguments matching the other commands
  (2) Different from AB3, `HelpCommand` will ignore all the invalid arguments and offer the most related help according to user input
* Implement `HelpCommand` classes for all the other commands, e.g., `HelpAddCommand` and `HelpDeleteCommand`
* Implement `HelpCommandParser` class and make changes to `FypManagerParser` correspondingly
  <br>
* Credits: The command is improved by Russell in later versions. <br><br>

* **Documentation**:
    * User Guide:
      * Added notes in `notes about the command format` to address the issue regarding the output of `HelpCommand` (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/209)
      * Phrased the description of `HelpCommand` differently to avoid ambiguity (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/209)
    * Developer Guide: 
      * Changed the format of some unmatched benefits and corrected the benefit of keywords searching (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/79)
      * Added the explanation of `HelpCommand` feature of version 1.2 (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/122)

* **Community**:
  * Reported some bugs in other team's software product during the PE-D as shown [here](https://github.com/SweetPotato0213/ped/issues).

* **Tools**:
