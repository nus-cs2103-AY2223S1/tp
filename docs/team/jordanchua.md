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
      `YTS`, `IP` or `DONE`, and the status is shown at the top right-hand corner in the UI
    * Note that the 3 statuses are coloured differently, with the colour scheme being similar to that of a traffic light
      (`YTS` is coloured red, `IP` is coloured yellow, `DONE` is coloured green)
      This allows the user to easily identify the current status of any FYP project.
    * The status of any FYP Project just added to FYP Manager is automatically set to `YTS`,
      which the user can then update accordingly.
    * We have also modified the UI to show projects that are `YTS` or `IP` on the left panel,
      and projects that are `DONE`on the right panel. This greatly increases the convenience for users
      to easily differentiate between projects that are more urgent, mainly those with the status `YTS`or `IP`,
      and those that are already completed.
  * Justification:
    * This feature improves the product significantly since users can now mark the current status
      of any FYP project so that he/she can identify which projects may need more attention or help, especially
      those that are `YTS` or `IP`.
  * Highlights:
    * This enhancement was a totally different command by itself, hence it was challenging to implement everything
      from scratch. It required an in-depth analysis of the current code base, and I had to do extensive
      code-tracing to implement the feature.
    * It was also hard to implement the status as a small coloured box at the top right hand corner
      of the Project card displayed in the UI since I was not too familiar with FXML.
  * Credits:
    * Would like to thank @russelldash332 for the help in implementing the status as a coloured box
      by providing the required FXML knowledge

* **New Feature**: Added the ability to sort the projects by their specialisation, or by their project status
  * What it does:
    * Allows the user to either sort the Projects by their name in alphabetical order, or
      by the project status, specifically those with the status `YTS` are sorted in front of those with the
      status `IP`. Projects with the status `DONE` are listed on the Completed panel.
  * Justification:
    * This feature improves the product since the user can now sort the FYP projects according to alphabetical order,
      which could be useful if the user is trying to search for a specific topic
    * The feature also allows the user to sort the projects by their project status, which is useful as well since
      projects that have the status `YTS` may be of more urgency compared to those with the status `IP`.
  * Highlights:
    * This enhancement was challenging since it required an in depth analysis of the classes
      related to the UI display, which necessitated the introduction of new methods to existing classes.
    * It also required me to modify existing methods affecting how the panel is displayed in the UI, which was
      not easy as well.
  * Credits:
    * Would like to thank @russelldash332 for the help in working through the code tracing with me
      to understand how the UI panel is being displayed

* **Code contributed**: <br>
  [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jordanchua&breakdown=true)

* **Project management**:
  * Helped to provide a workflow for the Project, such as suggesting to refactor the code first
    before starting on implementing our own features and commands
  * Helped in assigning the relevant tasks for each member depending on their respective code proficiency

* **Enhancements to existing features**:
  * Wrote additional tests in related classes such as `FypManagerParser`
    to incorporate relevant testing for introduction of new features
  * Added more relevant error messages to be thrown such as `MESSAGE_STUDENT_NOT_FOUND`  
    which may be relevant in commands such as the `AddCommand` or the `MarkCommand`

* **Documentation**:
  * User Guide:
    * Included the documentation for both the `MarkCommand` and `SortCommand` (#99)
    * Helped to fix the grammatical errors in the User Stories (#78)
    * Helped to create the Command Summary and Product Scope as well (#77)
  * Developer Guide:
    * Added implementation details of both `MarkCommand` and `SortCommand`(#112)
    * Included appropriate UML diagrams for both commands(#112, #126)

* **Community**:
  * Helped to report the bugs for another team's project during PED
    Link to issues raised is below: <br>
    [PED bug report](https://github.com/JordanChua/ped/tree/main/files)
    
* **Tools**
  * Draw.io for drawing my UML diagrams for both `MarkCommand` and `SortCommand
