---
layout: page
title: zicotjia's Project Portfolio Page
---

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI),
with GUI created with JavaFX**.

Given below are my contributions to the project.

* **Code Contribute** [Reposense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zicotjia&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=test-code~other~functional-code~docs)

* **New Feature**: Added in Team Class and Command to add Team. (Pull request [\#94](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/94))
    * What it does: It models a team that contains members and tasks.
    * Justification: EZLead aims to help Tech Lead manage employees and task within different teams.
      We implemented the Team class to employees and tasks assigned to the team. A team is considered the same
      if they have the same name, since having multiple groups of the same name will lead to confusion. EZLead
      prevents more than 1 groups of the same name from existing.

* **New Feature**: Handle Storing and Reading of Team and Tasks. (Pull Request [\#94](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/94)
& [\#107](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/107) & [\#115](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/115))
    * What it does: Allows conversion of Team and Tasks into JSON to be stored in local directory and reading
      of JSON file to create models.
    * Justification: EZLead needs to be able to store its current state into readable format to save changes done in it thorough
      different sessions. A JsonAdapted class for every model components is made to allow conversion of data in EZLead to JSON
      to be saved in local storage and to restore previous session state on loading.
    * Highlights: This features require researching and understanding JSON format and also how Java handle JSON.

* **New Feature**: Added the ability to delete team. (Pull Request [\#98](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/98))
    * What it does: Allows deletion of specified team.
    * Justifications: Users may want to delete inactive team to not clutter the GUI.
  
* **New Feature**: Added in Abstract Name class and makes Name class of each model extends the Abstract class. (Pull Request [#274](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/274))
    * What is does: Instead of creating 3 classes called Name within separate directories. The Name classes are now called
      a different name and extends an Abstract Name class.
    * Justifications: Reduce code duplication as personName, teamName and taskName has similar methods. Increase code readibility
      as each model's Name class is given a different name (personName, teamName, taskName instead of 3 separate class with the same name)
    * Highlights: The factory method pattern is used to allow each child class to run their unique regex validation before constructing the object.

* **Enhancement Implemented**: Modify Team class to have a Progress Bar attribute (Pull Request [\#146](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/146))
    * What is does: Keeps track of how many tasks has been completed by a team.
    * Justifications: This features allow user to more easily keep track of progress done teams
      in completing their assigned tasks.
    * Highlights: Storage handling have to be modified to account for the new attributes in the Team class.

* **Contribution to UG**
    * Update User Guide with instruction instructions on accessing and editing data from local storage. (Pull Request [\#240](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/240))

* **Contribution to DG**
    * Edited DeveloperGuide to reflect change from AB3 to new project. (Pull Request [\#73](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/73))
    * Edited value proposition and target user in Developer Guide. (Pull Request [\#76](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/76))
    * Add description and UML diagrams to show execution of create task and edit task commands. (Pull Request [\#160](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/160))

* **Team-based contributions**
    * Set up and manage tP Team Repo in GitHub.
    * Set up tP Team Organization.
    * Set up CodeCov, GitHub actions and CI for team repo.

* **Review/mentoring contributions**
    * Reviewed and suggested changes for other member's PR (Pull Request [\#106](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/106))

* **Contributions beyond the project team**
    * Caught above-average number of bugs during PE-dry run
        * ([\#10](https://github.com/zicotjia/ped/issues/10))
        * ([\#8](https://github.com/zicotjia/ped/issues/8))
        * ([\#7](https://github.com/zicotjia/ped/issues/7))

