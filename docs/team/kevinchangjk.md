---
layout: page
title: Kevin Chang's Project Portfolio Page
---

## Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.** The user interacts with it using a CLI, and it has a GUI created with JavaFX.
Given below are my contributions to the project.

### Summary of Contributions

---

**Code Contributed**

> [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=kevinchangjk&tabRepo=AY2223S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented

**New Features**

- Implemented Employee ID system
  - Created an `EmployeeId` class to represent the unique ID of an employee in the company. The implementation can be seen [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/model/person/EmployeeId.java).
  - Justification: This attribute of the employee completely removes any ambiguity of `Person` objects, as well as any possibility of duplicate employees recognised during commands.
  - Highlights: As a field with restrictions placed due to its uniquenes, it lacks modular depth as compared to other basic `Person` fields such as `Name` or `Phone`. It is also much harder to ensure smooth usage and testing due to its widespread implications.

**Existing Features**

- Upgraded `find` function

  - Created a `Department` class for another dimension of categorization. Then modified `find` to build a search filter that can comprise of name, department, and/or job position.
  - Justification: With this command, users of Coydir will be able to look up information on specific employees or group of employees quickly.
  - Highlights: Relatively easier to implement than the Employee ID system.

- Upgraded help window

  - Changed the default help window to provide a command summary. Additionally, instead of a button that copies the URL into register, the button opens the User Guide in the default browser of the user.
  - Justification: The help window now provides more utility, and accessing the User Guide is made faster and simpler.
  - Highlights: Implementing the function to open the User Guide in browser was slightly complex, and needed some workarounds. Building the interface for the help window was also particularly challenging.

- Fixed multiple bugs (Refer to #150, #151, #232, #233).

#### Contributions to the UG

- Restructured the document into current structure
- Added documentation for following parts:
  - Introduction and Logo, Employee Profile, `find` command, `edit` command, `help` command

#### Contributions to the DG

- Edited implementation details for:
  - `find`
- Added documentation for the following parts:
  - Non-functional requirements, Glossary, Manual testing, Table of contents

#### Contributions to team-based tasks

- Helped to create and distribute issues.
- Wrote test code for features that I upgraded (find, ID, department, position).
- Contributed to manual testing of application to find bugs.
- Oversaw backend development and upheld code quality.

#### Reviewing Contributions

- Pull Requests reviewed:
  [#40](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/40),
  [#42](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/42),
  [#54](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/54),
  [#64](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/64),
  [#66](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/66),
  [#67](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/67),
  [#82](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/82),
  [#87](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/87),
  [#91](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/91),
  [#92](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/92),
  [#111](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/111),
  [#112](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/112),
  [#114](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/114),
  [#120](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/120),
  [#121](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/121),
  [#122](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/122),
  [#123](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/123),
  [#126](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/126),
  [#221](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/221),
  [#227](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/227),
  [#239](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/239)
