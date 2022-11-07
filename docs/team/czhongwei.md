---
layout: page
title: Chee Zhong Wei's Project Portfolio Page
---

###Project: SectresBook

SectresBook helps secretaries to maintain all the information of the members of their club by collating a list of identifiable information, past records and future tasks.

Given below are my contributions to the project.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w12-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=czhongwei&tabRepo=AY2223S1-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements implemented:**
    * Enhancement: Locate persons by starting digits of phone number in `find`
      * Originally, `find` only searches for a person that contains the whole keyword in their names
      * Now, `find` also searches for a person with starting digits of the phone number (at least 2) that matches the keywords provided
    * Enhancement: Added `Birthday` attribute to `Person`
      * Each `Person` stores an additional information `Birthday`, which is in dd/mm/yyyy format
      * The regular expression for `Birthday` includes checks for 29th February on leap years and invalid dates
    * Enhancement: Changed prefixes of properties
      * Changed prefixes of properties in commands to better differentiate them


* **Contributions to the UG:**
  * Editted section to include enchancements : `find`
    * Brief description and explanation
  * Added screenshots for different features
  * Wrote up the opening paragraph of the UG


* **Contributions to the DG:**
  * Added content to `find` feature section:
    * Brief description, explanation and design considerations
    * Sequence diagram
  * Added more User stories
  * Added more Non-Functional Requirements
  * Added more instructions for manual testing
  * Did final polishing and checks for UG:
    * Language use
    * Spelling mistakes
    * Formatting


* **Review/mentoring contributions**:
  * Read through, checked and approved team PRs.
