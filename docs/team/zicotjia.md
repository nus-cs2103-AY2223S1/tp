---
layout: page
title: zicotjia's Project Portfolio Page
---

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI),
with GUI created with JavaFX**. It is written in Java

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

* **New Feature**: Added the ability to delete team. (Pull Request [#\98](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/98))
    * What it does: Allows deletion of specified team.
    * Justifications: Users may want to delete inactive team to not clutter the GUI.

* **Enhancement Implemented**: Modify Team class to have a Progress Bar attribute (Pull Request [#\146](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/146))
    * What is does: Keeps track of how many tasks has been completed by a team.
    * Justifications: This features allow user to more easily keep track of progress done teams 
      in completing their assigned tasks.
    * Highlights: Storage handling have to be modified to account for the new attributed in the Team class.
  
* **Contribution to UG**
    *  