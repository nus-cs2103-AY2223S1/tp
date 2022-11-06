---
layout: page
title: Guo Qi's Project Portfolio Page
---

### Project: Survin

Survin is a desktop application for surveyors to use to keep track of people they have surveyed. The surveyor can easily follow up with people they have surveyed for additional information or for confirmation. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: `clone` command
  * What it does: Allow the users to add a new person based on the details of an existing person.
  * Justification: This feature provides an easy way to add a new person without having to retype all the similar information again, for example, this can be useful in the scenario of adding a father and a son that are living in the same household, the user does not have to retype the repeated information such as address, race, surveys and etc again.
  * Highlights: It's fine if the users have forgotten to update some of the fields when cloning a person because they can always use edit command to edit again later, however, at least one of the unique optional fields (Name, Phone or Email) has to be provided when executing clone command else it will not work.

* **New Feature**: Shortcut keys
  * What it does: Allow the users to use keyboard shortcuts to add helpful text to the command box.
  * Justification: This feature provides a fast and efficient way for user to enter the commands, it can also help the users to avoid mistakes such as typos and missing prefixes in the command.
  * Highlights: Currently, only shortcut for add command has included all the prefixes because most of the fields are compulsory to add, shortcuts for other commands do not contain any prefixes because most of the the fields are optional, if we include all the prefixes the user has to take more time to delete away those unwanted prefix.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=NUSDG&breakdown=true)

* **Project management**:
  - Record demo video.
  
* **Documentation**:
  * User Guide:
    * Added documentation for the feature `clone` [\#124](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/124)
    * Added documentation for the features `shortcut keys` [\#147](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/147)
  * Developer Guide:
    - Added implementation details for feature `clone` [\#129](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/129)
