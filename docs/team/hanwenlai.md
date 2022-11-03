---
layout: page
title: Lai Han Wen's Project Portfolio Page
---

### Project: OmniHealth

## Overview

OmniHealth aims to provide doctors with a place to store and centrally manage their patients’ information (eg. medical records, contact information), as well as allowing doctors to schedule appointments, send appointment reminders and other notifications to patients using their stored contact information.

## Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=hanwenlai&breakdown=true)
* **Enhancements implemented**:
  * **New Feature**: Added the `rlist` command.
    * What it does: Allows the user to list all medical records of a specific patient.
  * **New Feature**: Added the mandatory `birthdate` field for each patient.
    * Justification: Allows the user to view the *current* age and birthdate of each patient on the GUI.
  * **New Feature**: Added the feature where the displayed record list is *always* sorted by date.
    * What it does: The displayed record list of a patient will remain sorted by date after executing commands that manipulate it (e.g. `radd`, `redit`).
    * Justification: Allows for a more systematic and intuitive view of the list of medical records.
* **Project management**:
  * Used GitHub issue tracker.
  * Followed the *forking workflow*.
* **Documentation**
  * User Guide:
    * Added documentation for the commands `list` and `rlist`.
  * Developer Guide:
    * Added implementation details for the `rlist` command.
    * Added UML Sequence Diagram showing the execution path of the `rlist` command.
* **Community**:
  * Review/mentoring contributions: Reviewed team members' PRs with helpful responses.
* **Contributions beyond the project team**:
  * Reported [bugs](https://github.com/hanwenlai/ped/issues) in another team's product.
