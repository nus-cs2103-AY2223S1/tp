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
    * What it does: By entering this command (`rlist`) and specifying the index of the patient in the patient list (`PATIENT_INDEX`), the user can view *all* medical records for the specified patient.
    * Justification: The user can get a quick overview of a specific patient's *complete* history of medical records, and then decide what to do next, such as editing or deleting an existing record, or adding a new record.
  
  * **New Feature**: Added the feature where the list of medical records displayed on the GUI is *always* sorted **chronologically**.
    * What it does: The *filtered/unfiltered* list of medical records of a specific patient **will remain sorted chronologically** when displayed on the GUI, even *after* executing commands that manipulate the list of medical records, such as `radd`, `redit` and `rfind`.
    * Justification: This feature will provide a more *systematic and predictable* view of a patient's list of medical records. As a result, the user can easily search for the medical record that they wish to view.
    * Highlights: The list of medical records displayed on the GUI is always sorted in **reverse chronological order**, meaning that the medical records with the *most recent dates appear first*.
  
  * **New Feature**: Added the mandatory `birthdate` field for each patient.
    * What it does: When adding a new patient to the patient list, the user *must* now provide the patient's birthdate. The user can then view the patient's *current age* and birthdate when the **GUI** is displaying the *patient list*.
    * Justification: Since OmniHealth's intended users are doctors, they may readily view any patient's *current age* for purposes such as administering medication or diagnosing a patient's medical condition.
    * Highlights: The displayed age of a patient will be **automatically updated** in relation to the *current date*. As a result, each patient's age *does not* need to be manually updated every year.
  
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
