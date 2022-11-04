---
layout: page
title: Eiffel Leo's Project Portfolio Page
---

### Project: OmniHealth

## Overview

OmniHealth is a **Patient Management System** tailored to private clinicians to manage patients' details, records and
upcoming appointments.

OmniHealth aims to provide doctors with a place to store and centrally manage their patients’ information (eg. medical
records, contact information), as well as allowing doctors to schedule appointments, send appointment reminders and
other notifications to patients using their stored contact information.

## Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added `radd` command [#47](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/47)
  * What it does: Allows users to add a new record to a given patient's record list.
  * Justification: This feature facilitates the storage and maintenance of medical records within OmniHealth.
  * Highlights: 
  
* **New Feature**: Added `Medication` field in patient records. 
[#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72)
  * What it does: Allows users to explicitly have a medication field within their medical records which can be displayed 
  and searched for easily.
  * Justification: This features addresses the user's need to easily see what medication was previously prescribed to
  patients so that they may more holistically perform their patient diagnosis.
  * Highlights: To improve user flexibility, the medication field is able to take in multiple inputs (including 0). 

* **New Feature**: Added `DisplayedPerson` field in `ModelManager` to handle record display logic. 
[#54](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/54)
  * What it does: Encapsulates the current person whose records are being viewed. Record commands (eg. `radd`) are 
  executed through this `DisplayedPerson`.
  * Justification: The key reason to maintain a `DisplayedPerson` field would be the need to change the current person 
  whose records are being displayed/accessed whenever the user inputs the corresponding command. It is hence logical to 
  capture this logic within a wrapper class which can change the current person being displayed using 
  `DisplayedPerson#setPerson`. Additionally, the class allows us to maintain OOP but preventing the model from directly
  interacting and editing a person's records.
  * Highlights: All record commands go through the `DisplayedPerson`.
  
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=EiffelLKF)

* **Project management**: 
  * Organised and coordinated team meetings.
  * Published Releases v1.3.trial and v1.3.1.
  * Used GitHub issue tracker.
  * Reviewed and approved PRs for merging.

* **Enhancements to existing features**:
  * Added illegal spacing check for parsing of `Name` and `Address`. 
  [#152](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/152)
  * Improve defensibility of code by enforcing separation of Record and Patient commands by the current view mode of 
  OmniHealth (ie. Record commands can only be called when patient record list is displayed, vice versa). 
  [#54](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/54)
  * Added methods to parse `Record` fields in `ParserUtil` class to facilitate parsing of new records.
  [#47](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/47)
  * Updated GUI to show `Medication` fields of patient records.
  [#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72)
  * Wrote test cases for `Medication` classes.
  [#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72)
  * Wrote test cases for `AddRecordCommand` and `AddRecordCommandParser`classes. 
  [#61](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/61)
  
* **Documentation**: 
  * User Guide: 
    * Added documentation for the feature `Add Record`.
    [#9](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/9)
    [#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72)
    * Added details on general command requisite logic / limitations.
    [#101](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/101)
    * General aesthetic changes (eg. reformat summary table, fix typos).
    [#101](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/101)
    [#146](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/146)
    
  * Developer Guide: 
    * Added implementation details for the `Add Record` feature.
      [#88](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/88)
    * Added use cases of `Add Record` and general reformatting.
    [#25](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/25)
    [#54](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/54)
    
* **Community**:
  * PRs reviewed (with non-trivial comments): 
  [#37](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/37)
  [#45](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/45)
  [#48](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/48)
  [#49](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/49)
  [#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)
  [#69](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/69)
  [#77](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/77)
  [#79](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/79)
  [#80](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/80)
  [#85](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/85)
  [#95](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/95)
  [#153](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/153)
  * Created a total for [15](https://github.com/AY2223S1-CS2103T-T14-3/tp/issues?q=is%3Aissue+author%3AEiffelLKF) 
  issues for team GitHub issue tracker.
  * Reported a total of [7](https://github.com/EiffelLKF/ped/issues) bugs and suggested for PE-D.

