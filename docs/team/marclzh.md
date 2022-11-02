---
layout: page title: Marcus Lee's Project Portfolio Page
---

## Project: OmniHealth

OmniHealth is a **Patient Management System** tailored to private clinicians to manage patients' details, records and
upcoming appointments.

OmniHealth aims to provide doctors with a place to store and centrally manage their patients’ information (eg. medical
records, contact information), as well as allowing doctors to schedule appointments, send appointment reminders and
other notifications to patients using their stored contact information.

Given below are my contributions to the project.

* **New Feature**: Added the `Record` and `RecordList`
  fields [\#37](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/37)
    * What it does: Allows for OmniHealth to store a patient's records in a list.
    * Justification: Since the target users are doctors, a place to centrally manage their patient records is a key
      feature in improving their workflow.
    * Highlights: The list of records is automatically sorted chronologically, and the addition of an additional record
      field required many changes across the different components of OmniHealth, including its UI and test cases.

* **New Feature**: Added a `rclear` command [\#50](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/50)
    * What it does: Allows for users to clear the records of a specified patient.
    * Justification: Having this feature improves the utility of the application by allowing the user to quickly reset a
      patient's records if needed.

* **New Feature**: Added the ability to create and manage `Appointments` for a
  patient. [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)
    * What it does: Allows users to create, and delete `Appointments`.
    * Justification: Since the target users are doctors, this is a key feature that allows users to plan out their
      schedule of when next to see a patient. Due to the nature of different illnesses and medication requirements, the
      ability to forward plan appointments for the user's patients would greatly benefit their workflow.
    * Highlights: This feature entailed adding code to most of the areas of OmniHealth, including its model, UI and test
      cases, as well as requiring changes to existing commands.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=marclzh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=marclzh&tabRepo=AY2223S1-CS2103T-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Created team's organisation and repository.
    * Set up project's Continuous Integration.
    * Set up project's website.
    * Created `v1.1`, `v1.2`, `v1.3` and `v1.4` milestones.
    * Changed product icon and name.
    * Reviewed and approved PRs for merging.

* **Enhancements to existing features**:
    * Added additional duplicate detection for addresses. [\#149](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/149)
    * Updated GUI to show number of `Records`. [\#37](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/37)
    * Updated GUI to show the patient's currently scheduled
      appointment. [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)
    * Wrote test cases for `Record` classes. [\#58](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/58)
    * Wrote test cases for `Appointment` classes. [\#91](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/91)

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `Find Record`
          . [\#29](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/29)
        * Added documentation for the features `Add Appointment` and `Clear Appointment`. (Pull
          Requests [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)
          , [\#98](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/98))
        * Added details on date validation for features that include
          dates. [\#149](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/149)
        * Improved continuity of UG, allowing for better
          flow. [\#149](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/149)
        * Amended various issues found by other students. [\#149](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/149)
    * Developer Guide:
        * Added implementation details of the `Clear Record`
          feature. [\#89](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/89)
        * Added implementation details of the `Add Appointment` and `Clear Appointment`
          features. [\#89](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/89)
        * Added use cases for the `Add Appointment` and `Clear Appointment`
          features. [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)

* **Community**:
    * PRs reviewed (with non-trivial review comments):(Pull
      Requests [\#45](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/45),
      [\#47](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/47)
      , [\#48](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/48)
      , [\#54](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/54)
      , [\#56](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/56)
      , [\#60](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/60)
      , [\#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72)
      , [\#77](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/77)
      , [\#79](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/79)
      , [\#85](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/85)
      , [\#95](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/95))
    * Reported a total of [11](https://github.com/marclzh/ped/issues) bugs and suggestions for other teams in the class.
