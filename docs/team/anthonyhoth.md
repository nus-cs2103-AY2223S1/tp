---
layout: page
title: Anthony's Project Portfolio Page
---

### Project: OmniHealth

## Overview

OmniHealth is a **Patient Management System** tailored to private clinicians to manage patients' details, records and upcoming appointments.
As a private clinician, you can manage and monitor your patient database all in one location.
Utilise OmniHealth's **sorting and filtering system** to help you easily manage and find your patients' details and records of past visits.
Also, OmniHealth's **appointment tracker** allows you to tag upcoming appointments for each patient.

## Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [anthonyhoth's tP Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=anthonyhoth&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Enhancements implemented**:
  * **New Feature**: Added `rdelete` command [\#49](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/49)
    * What it does: Allows for users to delete records from a patient's record list.
    * Justification: Having this feature is essential for the application's functionality to allow users to manage records.
    * Highlights: The feature allows for users to delete records in the filtered/unfiltered list based on the index shown.
  * **New Feature**: Added `redit` command [\#79](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/79)
    * What it does: Allows for users to edit the date, details or medication field of a record.
    * Justification: Instead of having users to delete and add a new record to replace and existing one, this command allows
them to edit and replace an existing records with new fields.
  * Edited OmniHealth's duplicate check for patients
    * Justification: Patients might have the exact same name, so we defined duplicate patients as having duplicate names and home address.
  * Updated the design of GUI to a light theme. [\#108](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/108)
  * Updated User Guide URL for `help` window. [\#103](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/103)
  * Wrote test cases for `rdelete` and `redit` classes. [\#60](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/60), [\#79](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/79)
  * Updated error messages to better suit OmniHealth's context. [\#95](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/95), [\#147](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/147)
* **Project management**: 
  * Added issues for tasks to be completed after weekly project meetings.
  * Updated teammates' issues and PRs with relevant tags.
* **Documentation**:
  * User Guide:
    * Added annotated screenshots and images. [\#103](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/103), [\#108](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/108), [\#154](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/154)
    * Updated section headings and Table of Contents. [\#154](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/154)
    * Updated Command Summary to include new commands implemented. [\#81](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/81)
    * Rearranged contents to increase user readability.
    * Added documentation for `Edit Record` and `Delete Record`. [\#81](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/81)
  * Developer Guide: 
    * Updated User Stories section with nice-to-have user stories. [\#81](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/81)
    * Added implementation details for `Edit Record` and `Delete Record` commands. [\#100](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/100)
    * Added use cases of commands implemented. [\#81](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/81)
* **Community**:
  * Reported a total of [11](https://github.com/anthonyhoth/ped/issues) bugs and suggestions for other teams in the class.
* **Contribution to team-based tasks**:
  * Maintained issue tracker
  * Updated Project Overview. [\#154](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/154)
* **Review/mentoring contributions**:
  * Reviewed teammates' PRs regularly for errors/improvements.
