---
layout: page
title: Shum Jie Hui's Project Portfolio Page
---
### Project: TrackAScholar


TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

* **New Feature**: Added the ability to filter applicants by their scholarship application status: [#56](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/56), [#62](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/62), [#68](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/68)
  * Justification: Allows administrative staff to manage scholarship applicants more easily by organising them based on their specific application status.
  * Credits: Developed in tandem with Benjy's help.
  
* **New Feature**: Added the ability to sort applicants by name, scholarship or application status: [#85](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/85), [#87](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/87), [#88](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/88), [#92](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/92)
  * Justification: Allows administrative staff to manage scholarship applicants more easily by sorting them based on their name or scholarship.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ShummyOwnzYou&tabRepo=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management**:
  * Refactored all instances of `AddressBook` and `Person` to `TrackAScholar` and `Applicant`,
    along with all related methods, test cases and parameter names: [#65](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/65), [#71](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/71)

* **Enhancements to existing features**: 
  * Updated `Logic` and `Model` to display pinned applicants on the UI: [#114](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/114)
  * Changed `Unpin` command to unpin by name instead of index: [#122](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/122),  [#123](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/123)
    * Justification: 
      * The pinned applicant list does not display applicant index number to avoid inconsistencies and confusion between the 2 applicant lists for commands requiring indexes. 
      * This change allows a more straightforward way to unpin applicants as applicant names are guaranteed to be unique.
  * Wrote additional test cases for new and existing features to improve coverage by 3%: [#62](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/62), [#186](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/186), [#199](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/199), [#207](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/207)

* **Documentation**:
    * User Guide:
      * Updated user guide with pin feature: [#121](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/121)
      * Made minor improvements in format for user guide: [#126](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/126), [#132](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/132)
    * Developer Guide: 
      * Updated developer guide with sort feature and corresponding UML diagrams: [#106](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/106), [#132](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/132) 
      * Updated developer guide with all use cases: [#39](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/39), [#209](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/209)
      * Updated developer guide with manual testing for sort feature: [#209](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/209)

* **Community**: 
  * PRs reviewed (with non-trivial suggestions): [#111](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/111), [#124](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/124), [#204](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/204), [#210](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/210)
  * Hosted multiple discussion sessions with team members on PR issues and weekly tasks.
  * Reported bugs for [CS2103T-T17-1](https://github.com/AY2223S1-CS2103T-T17-1/tp) during PE dry run [here](https://github.com/ShummyOwnzYou/ped/issues).


