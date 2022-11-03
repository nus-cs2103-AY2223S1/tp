---
layout: page
title: Leong Ming Jian Eugene's Project Portfolio Page
---

### Project: JerylFypManager

JerylFypManager is a desktop application catered to professors to manage and track the progress for students’ final 
year project (FYP), as well as serving as a platform for professors to provide feedback on their students’ progress. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 
10 kLoC. This project is based on AddressBook - Level 3.

Given below are my contributions to the project.

* **New Feature**:
FindCommand - allows users to search for the desired FYP projects with specified input keywords
  (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/118)
  * What it does: Professors can search for the projects that they are interested in using keywords. More specifically,
  they can search via any one of the following fields of a student:
    1) Name
    2) Student ID
    3) Project Title
    4) Tags

  * Justifications: Professors would be interested in searching for projects that they are interested in, and they 
  would want to filter the projects by certain fields: for instance, by specialisation (which could be student's tags),
  or by the project names, etc.

  * The appeal: This makes it easier for Professors to sieve out the projects that they are interested in, and they can
  view the filtered projects at one glance.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=eugenelmj&breakdown=true)

* **Project management**:
  * Took on the role of writing down the minutes of our weekly meetings, and made prompt updates to the group
    * Summarised to members the tasks to keep track of, as well as provide any additional information (as discussed 
      during the meeting) to aid them in their tasks.
  * Reminded teammates of the deadlines to abide by from time to time, so that we could meet the
    deadlines on time. These were also communicated via the meeting minutes as well.

* **Enhancements to existing features**:
  * See "New Feature" above for more details on contributions to FindCommand.
  * Made an overhaul to the naming (from Person -> Student) to better fit the theme of a FYP Manager
  * Added a new student attribute for studentID, and removed the phone attribute
    (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/87)
  
* **Documentation**:
    * User Guide: 
      * Edited part of the explanations for AddCommand (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/87)
      * Made thorough checks of UG several times, and fixed documentation bugs when spotted.
        In particular, whether the provided example codes work as intended, the spelling/grammatical issues, 
        and the logic.
      * Updated the glossary

    * Developer Guide:
      * Written up a thorough explanation of how FindCommand works
      * Added State Diagrams for the command
      * Added a sequence diagram for the command (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/118)
      * Contributed several user stories. (https://github.com/AY2223S1-CS2103-F09-1/tp/pull/76)
      * Came up with Use Case (UC03 - Marking Project Status).
      * Made thorough checks of UG several times, and fixed documentation bugs when spotted.
        * In particular, the spelling/grammatical issues, and the logic.

* **Community**:
  * Raised bugs in the initial releases of the other teams' projects
    (https://github.com/eugenelmj/ped/issues)

* **Tools**:
  * draw.io for the drawing of the UML diagram for FindCommand in Developer Guide
