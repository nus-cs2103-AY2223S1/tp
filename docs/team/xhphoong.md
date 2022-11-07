---
layout: page
title: xhphoong's Project Portfolio Page
---

### Project: HealthCare Xpress

Healthcare Xpress is a desktop medical address book application used by medical administrators, for managing medical staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10k lines of code.

Given below are my contributions to the project.

* **New Features**: 

1. Assign a Patient's dateslot(s) to a nurse
    * What it does: Allows the medical administrator to assign a patient's dateslot(s) to a nurse. 
    * Justification: This is the main feature of our product. The medical administrator assign the patient's dateslot(s) to nurse and the system will record down the assign status of each date slot and the homevisits that the nurses have to attend to. The system will also auto check time crashes. This makes the administrative assigning process more easy and effective. 
    * Related Pull Requests: [#239](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/239) [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118)

2. Deassign a Patient's dateslot(s) / a Nurse's homevisit(s)
    * What it does: Allows the medical administrator to deassign a patient's dateslot(s) / a nurse's homevisit(s).
    * Justification: In case of a sudden change made or wrong assigning made, the medical administrator can deassign the patient's dateslot(s) / the nurse's homevisit(s).
    * Related Pull Requests: [#239](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/239) [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118)

3. Unmark a Patient's dateslot as fail to visit
    * What it does: Allows the medical administrator to unmark a patient's dateslot that has passed as fail to visit.
    * Justification: When the nurse attended to the homevisits but the patient were not there or other circumstance that made the nurse to fail visit the patient, the medical administrator can unmark a patient's dateslot as fail to visit so that the medical administrator can keep track of the visit status of each date slot and determine whether there is a need to schedule a new home visit date and slot with the patient. 
    * Related Pull Requests: [#239](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/239) [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118)

4. Undo unmark a Patient's dateslot as success visit
    * What it does: Allows the medical administrator to undo the unmarking of a patient's dateslot that has passed to success visit.
    * Justification: When the medical administrator had unmark the wrong date slot, the medical administrator can undo the unmarking of a patient's dateslot to success visit so that the medical administrator can keep track of the correct visit status of each date slot.
    * Related Pull Requests: [#239](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/239) [#123](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/123)
   
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=xhphoong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=xhphoong&tabRepo=AY2223S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements to existing features**:

1. Add gender attribute to person. [#67](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/67/files)
2. Add patient that extends from person and has date and slot attribute. [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118) [#67](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/67/files)
3. Add home visit, unavailable date list and fully scheduled date list attributes to the nurse (created by other teammates). [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118)
4. Modify the add feature to add patient. [#67](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/67/files)
5. Modify the edit feature to edit newly-added attributes such as date and slot, unavailable date and gender. [#239](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/239) [#118](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/118) [#79](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/79) [#67](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/67/files)

* **Documentation**:
    * User Guide:
        * Add Patient Feature
        * Edit Feature
        * Assign Feature
        * Deassign Feature
        * Unmark Feature
        * UndoUnmark Feature
        * Related Pull Requests : [#130](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/130) [#122](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/122)
    * Developer Guide:
        * Use cases  [#248](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/248) [#29](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/29)
        * Initial Add Command Implementation [#104](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/104)

* **Contributions to team-based tasks**:
    * Setting up the GitHub team organisation and repo
    * Code reviews made for other teammates' pull requests
    * Fix bugs and errors 
    * Improve code quality 



