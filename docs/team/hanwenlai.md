---
layout: page
title: Lai Han Wen's Project Portfolio Page
---

### Project: OmniHealth

## Overview

OmniHealth is a **Patient Management System** tailored to private clinicians to manage patients' details, records and upcoming appointments.
As a private clinician, you can manage and monitor your patient database all in one location.
Utilise OmniHealth's **sorting and filtering system** to help you easily manage and find your patients' details and records of past visits.
Also, OmniHealth's **appointment tracker** allows you to tag upcoming appointments for each patient.

## Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=hanwenlai&breakdown=true)

* **Enhancements implemented**:

  * **New Feature**: Added the `rlist` command. [\#48](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/48)
    * What it does: By entering this command (`rlist`) and specifying the index of the patient in the patient list (`PATIENT_INDEX`), the user can view *all* medical records for the specified patient.
    * Justification: The user can get a quick overview of a specific patient's *complete* history of medical records, and then decide what to do next, such as editing or deleting an existing record, or adding a new record.
  
  * **New Feature**: Added the feature where the list of medical records displayed on the GUI is *always* sorted **chronologically**. [\#69](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/69)
    * What it does: The *filtered/unfiltered* list of medical records of a specific patient **will remain sorted chronologically** when displayed on the GUI, even *after* executing commands that manipulate the list of medical records, such as `radd`, `redit` and `rfind`.
    * Justification: This feature will provide a more *systematic and predictable* view of a patient's list of medical records. As a result, the user can easily search for the medical record that they wish to view.
    * Highlights: The list of medical records displayed on the GUI is always sorted in **reverse chronological order**, meaning that the medical records with the *most recent dates appear first*.
  
  * **New Feature**: Added the mandatory `birthdate` field for each patient. [\#80](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/80)
    * What it does: When adding a new patient to the patient list, the user *must* now provide the patient's birthdate. The user can then view the patient's *current age* and birthdate when the **GUI** is displaying the *patient list*.
    * Justification: Since OmniHealth's intended users are doctors, they may readily view any patient's *current age* for purposes such as administering medication or diagnosing a patient's medical condition.
    * Highlights: The displayed age of a patient will be **automatically updated** in relation to the *current date*. As a result, each patient's age *does not* need to be manually updated every year.

  * **New Feature**: Added the **date validation** to validate the *date of a record* or a patient's *birthdate*. [\#105](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/105)
    * What it does: When *adding or editing* the date of a record or a patient's birthdate, an error message will be displayed if the newly entered date is later than the current date.
    * Justification: This feature *prevents* the user from unintentionally entering incorrect dates, hence eliminating the need to edit the patient (`edit`) or record (`redit`) only to correct the record date or birthdate.
    * Highlights: **Different error messages** will be displayed to the user when the user either inputs a date with an *incorrect format*, or a date that is *after the current date*.

  * Implemented the **phone number validation**, such that phone numbers input can only be between 5 and 15 digits long, both inclusive. [\#151](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/151)

  * Wrote test cases for `ListRecordCommand` and `ListRecordCommandParser` classes. [\#63](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/63)
  
  * Wrote test cases for `Birthdate` classes. [\#80](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/80)
  
* **Project management**:
  * Used GitHub issue tracker.
  * Followed the *forking workflow*.
  * Reviewed and approved PRs for merging.

* **Documentation**
  * User Guide:
    * Added documentation for the `list` command. [\#11](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/11)
    * Added documentation for the `rlist` command. [\#59](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/59)
    * Update documentation for commands to reflect the addition of `birthdate` field. [\#80](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/80)
    * Update documentation for commands that require an `Index` to be specified, such that the index given must be below a set limit. [\#156](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/156)
  * Developer Guide:
    * Added implementation details for the `rlist` command. [\#90](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/90)
    * Added UML Sequence Diagram showing the execution path of the `rlist` command. [\#102](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/102)
    * Added a `Table of Contents` and `Back to ToC` shortcuts to improve navigability of DG. [\#161](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/161)
    * Added use cases for `list` and `rlist` commands.

* **Community**:
  * PRs reviewed (with non-trivial comments): [\#45](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/45), [\#47](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/47), [\#54](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/54), [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67), [\#81](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/81)

* **Contributions beyond the project team**:
  * Reported [6 bugs](https://github.com/hanwenlai/ped/issues) in another team's product.
