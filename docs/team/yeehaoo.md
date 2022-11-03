### Project: Healthcare Xpress

Healthcare Xpress is a desktop medical address book application used by medical administrators, for managing medical staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 8200 lines of code.

Given below are my contributions to the project.



* **New Feature**: Update Emergency Contact
  * What it does: Allows the medical administrator to add and update attending physician and next of kin contacts for patients.
  * Justification: In case of emergency, the medical administrator would be able to quickly reach the attending physician and next of kin, and inform them of the emergency. For example, if an incident occurs during a nurse's home visit, the attending physician can be reached within a quick query instead of having to query some other database.
  * Highlights: This implementation was challenging to make efficient, and the plan initially was to create two commands to solve this issue. However, it was found that they could be combined through polymorphism instead and the problem was solved efficiently.
  * Pull request: [#110](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/110)
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ay2223s1-cs2103-f13-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=yeehaoo&tabRepo=AY2223S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements to existing features**: Filtered List
  * What it does: Allows the medical administrator to filter enrolled patients or nurses by several filters, including category, gender, tags and address.
  * Justification: This feature allows the medical administrator to quickly filter the list of people based on given specifications, to facilitate other tasks such as assigning patients to nurses based on location, or other such queries.
  * Highlights: The implementation took some time because instead of creating a new function, the current implementation had to be examined and adapted to our use case. Once the current mechanism was understood, then the tweaks were applied.
  * Pull request: [#68](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/68)
  

* **Contributions to team-based tasks**:
  * Managed issues and milestones
  * Handled deadlines, frequent check-ins with team
  * Code reviews for many pull requests


* **Documentation**:
  * User Guide:
    * List feature
    * Update emergency contact feature
  * Developer Guide:
    * List feature
    * Update emergency contact feature


* **Community**:
  * PRs reviewed: [#29](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/29), [#32](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/32), [#44](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/44), [#75](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/75), 
  * Reported bugs during PE-D exercise.
