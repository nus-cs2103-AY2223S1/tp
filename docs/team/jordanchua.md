---
layout: page
title: Chua Yu Xuan Jordan's Project Portfolio Page
---

### Project: JerylFypManager

JerylFypManager is a desktop application catered to professors to manage and track the progress for students’ final year project (FYP), as well as serving as a platform for professors to provide feedback on their students’ progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC. This project is based on AddressBook - Level 3.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark the project with the status {`YTS`, `IP`or `DONE`}.
  * What it does:
    * Allows the Professor to mark any FYP Projects in their FYP Manager with any of the 3 supported statuses
      `YTS`, `IP` or `DONE`
    * The status of any FYP Project just added to FYP Manager is automatically set to `YTS`
  * Justification:
    * This feature improves the product significantly since users can now mark the current status
      of any FYP project so that he/she can identify which projects may need more attention or help, especially
      those that are `YTS` or `IP`.
  * Highlights:
    * This enhancement was challenging to implement everything from scratch. It required an in-depth analysis of the current code base, and I had to do extensive
      code-tracing to implement the feature.
  * Credits:
    * Would like to thank @russelldash332 for the help in implementing the status as a coloured box
      by providing the required FXML knowledge

* **New Feature**: Added the ability to sort the projects by their specialisation, or by their project status
  * What it does:
    * Allows the user to either sort the Projects by their name in alphabetical order, or
      by the project status, specifically in the order {`YTS`, `IP`, `DONE`}.
  * Justification:
    * This feature improves the product since users may be searching for a specific topic, or for projects of a
      specific project status
  * Highlights:
    * This enhancement was challenging since it required the introduction of new methods to existing classes
      , as well as the modification of existing ones
  * Credits:
    * Would like to thank @russelldash332 for the help with the UI panel

* **Code contributed**: <br>
  [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jordanchua&breakdown=true)

* **Project management**:
  * Helped to provide a workflow for the Project such as suggesting refactoring of the code as a priority
  * Helped in assigning the relevant tasks for each member depending on their respective code proficiency

* **Enhancements to existing features**:
  * Wrote additional tests in related classes such as `FypManagerParser`
    to incorporate relevant testing for introduction of new features
  * Added more relevant error messages to be thrown such as `MESSAGE_STUDENT_NOT_FOUND`

* **Documentation**:
  * User Guide:
    * [Included the documentation for both the `MarkCommand` and `SortCommand`](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/99)
    * [Helped to fix the grammatical errors in the User Stories](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/78)
    * [Helped to create the Command Summary and Product Scope as well](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/77)
  * Developer Guide:
    * [Added documentation and appropriate UML diagrams for both commands](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/234)

* **Community**:
  * Helped to report the bugs for another team's project during PED
    Link to issues raised is below: <br>
    [PED bug report](https://github.com/JordanChua/ped/tree/main/files)

* **Tools**
  * Draw.io for drawing my UML diagrams for both `MarkCommand` and `SortCommand`
