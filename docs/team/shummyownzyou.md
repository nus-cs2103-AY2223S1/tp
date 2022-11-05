---
layout: page
title: ShummyOwnzYou's Project Portfolio Page
---
### Project: TrackAScholar


TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

* **New Feature**: Added the ability to filter applicants by application status together with Benjy: [#56](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/56), [#62](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/62), [#68](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/68)
  * Justification: Allows administrative staff to manage scholarship applicants more easily by organising them based on their specific application status.
  
* **New Feature**: Added the ability to sort applicants by name, scholarship or application status: [#85](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/85), [#87](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/87), [#88](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/88), [#92](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/92)
  * Justification: Allows administrative staff to manage scholarship applicants more easily by sorting them based on their name or scholarship.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=ShummyOwnzYou&tabRepo=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management**:
  * Refactored all instances of `AddressBook` and `Person` to `TrackAScholar` and `Applicant`,
    along with all related methods, test cases and parameter names. [#65](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/65), [#71](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/71)

* **Enhancements to existing features**: 
  * Updated `Logic` and `Model` to display pinned applicants on the UI: [#114](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/114)
  * Changed `Unpin` command to unpin by name instead of index: [#122](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/122),  [#123](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/123)
    * Justification: 
      * The pinned applicant list does not display applicant index number to avoid inconsistencies and confusion between the 2 applicant lists for commands requiring indexes. 
      * This change allows a more straightforward way to unpin applicants as applicant names are guaranteed to be unique.


* **Documentation**:
    * User Guide:
      * Updated user guide to include pin command: [#121](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/121)
      * Made minor improvements in format for user guide: [#126](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/126), [#132](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/132)
    * Developer Guide: 
      * Updated developer guide with use case 5: [#39](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/39)
      * Updated developer guide with sort feature and corresponding UML diagrams: [#106](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/106), [#132](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/132)  

* **Community**: 
  * PRs reviewed (with non-trivial suggestions): [#111](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/111), [#124](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/124)
  * Hosted multiple discussion sessions with team members on PR issues and weekly tasks.
  * Reported bugs for another team during PE dry run [here](https://github.com/ShummyOwnzYou/ped/issues).


