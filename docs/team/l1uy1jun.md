---
layout: page
title: L1uY1jun's Project Portfolio Page
---

### Project: TrackAScholar

TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an attribute `ApplicationStatus` to keep track of the status of the scholarship application.

* **New Feature**: Added the ability to remove scholarship applications in bulk.
  * What it does: Allows the user to remove all the completed (accepted / rejected) scholarship applications by specifying which status to remove.
  * Justification: This feature improves the product significantly because a user can remove all the finished scholarship application together with one command without having to tediously delete them one by one.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=L1uY1jun&tabRepo=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.2.1` and `v1.3` on GitHub

* **Enhancements to existing features**:
  * Enhanced the `find` command. (Pull Request [#111](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/111))
    * What it does: Allows the user to find applicant(s) using either 1 or multiple prefixes and attributes. User is able to find applicant(s) by name, scholarship applied for and majors taken.
    * Justification: This feature enhances the overall product as user is able to find applicant(s) they want to look for immediately.
      User can pinpoint applicant(s) precisely using multiple prefixes and attributes.
      Hence, this command improves overall user experience as the user can now specify additional keywords to identify the scholarship application(s) they are interested in easily.
    * Highlights: Allow find to search for applicant using partial matching of individual attributes.
      i.e. finding multiple names while specifying a single major matches only all the applicants whose name is partially mentioned and has taken the major specified.
    * Added 2 new classes `ScholarshipContainsKeywordsPredicate` and `MajorContainsKeywordsPredicate` to assist searching.

  * Wrote additional test cases for existing features to improve coverage.
  
* **Documentation**:
  * User Guide:
    * Wrote introduction for user guide and updated sequence of features to improve flow. [#128](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/128)
    * Updated documentation for all features. [#128](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/128)
    * Added overview of TrackAScholar app. [#131](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/131)

  * Developer Guide:
    * Updated developer guide for TrackAScholar. [#95](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/95)
    * Added cosmetic tweaks for entire developer guide. [#95](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/95)
    * Added implementation details for `add` and `remove` feature: [#95](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/95) [#102](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/102)

* **Community**:
  * Hosted multiple discussion sessions with team members on PR issues.
  * PRs reviewed (with non-trivial suggestions): [#92](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/92), [#109](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/109), [#112](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/112)
  * Reported bugs for another team [here](https://github.com/L1uY1jun/ped/issues)
