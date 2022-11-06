---
layout: page
title: Wai Chong's Project Portfolio Page
---

### Project: GREWZ

GREWZ is an all-in-one desktop application to assist teaching assistants in their day-to-day teaching duties.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Create students' profiles based on limited student information.
  * What it does: This makes certain information fields optional to fill in, so that teacher assistants can create a profile for the students without needing to know/fill in all information.
  * Justification: This feature helps teacher assistants to add student's profile flexibly based on the limited student information that they have on their students.
  * Highlights: Optional fields (Class Group, Email, Phone Number, Attendance List) can be left blank. Teaching assistants can use the edit feature if they want fill in those optional fields in the future.

* **New Feature**: Added deadlines to Task Book.
  * What it does: This allows teaching assistants to add in their daily tasks and their deadlines, so that it will remind them to complete the task before it expires.
  * Justification: Some tasks are time-sensitive, therefore it is important to note down the deadline, so that it can act as a reminder to complete the task.
  * Highlights: The deadline of a task can be changed using the edit-task feature.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=whysochong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Evande1&tabRepo=AY2223S1-CS2103T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.3` - `v1.4rc` (3 releases) on GitHub
  * Assisted in approving and merging PRs of implementations done by other teammates

* **Enhancements to existing features**:
  * Modified the given `add` feature (Pull requests [\#71](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/71))
  * Updated the UI and test cases for `deadlines` (Pull requests [\#99](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/99))
  * Modified `task` class and split it into two different types - `ToDos` and `Deadlines`. (Pull requests [\#99](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/99))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `task` and `add`
    * Edited FAQ to include more comprehensive questions and answers
    * Edited Command Summary to include new updated features.
    * Fixed minor grammatical/style issues (Pull Request [\#206](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/206))
  * Developer Guide:
    * Added implementation details and UML diagrams of the modified `add` feature. (Pull Request [\#96](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/96))
    * Added Use Cases for task-related - `deadline` and student-related - `edit` and `delete` commands (Pull Request [/#118](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/118))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#81](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/81), [\#83](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/83), [\#95](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/95), [\#112](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/112), [\#117](https://github.com/AY2223S1-CS2103T-W12-4/tp/pull/117)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Whysochong/ped/issues/9), [2](https://github.com/Whysochong/ped/issues/7), [3](https://github.com/Whysochong/ped/issues/6), [4](https://github.com/Whysochong/ped/issues/5), [5](https://github.com/Whysochong/ped/issues/4), [6](https://github.com/Whysochong/ped/issues/3), [7](https://github.com/Whysochong/ped/issues/2), [8](https://github.com/Whysochong/ped/issues/1))
