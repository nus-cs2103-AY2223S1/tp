---
layout: page
title: Zhang Yue's Project Portfolio Page
---

### Project: Teacher's Pet

Teacher’s Pet is a desktop application for managing contacts of students and classes, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Teacher’s Pet can get your contact and class management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete multiple students.
  * What it does: allows the user to delete multiple students in one command.
  * Justification: This feature improves the product significantly because a user can have many students
  * and deleting in a batch as opposed to entering the delete command repeatedly greatly improves efficiency and
  * contributes to a smoother user experience.
  * Highlights: This enhancement affects existing commands. It required an in-depth analysis of design alternatives due to the different possible user inputs. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Search by address.
  * What it does: allows the user to find students by address.
  * Justification: This feature enhances the flexibility of the product because it allows for the user to find students living at a particular region, e.g. Jurong.
  * Highlights: Allows for a partial search as opposed to full word search used in other find commands.
  
* **New Feature**: Search by email.
  * What it does: allows the user to find students by email.
  * Highlights: Implemented such that the search is case insensitive.

* **Code contributed**: ~3.7k LoC. [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=gnahzeus&tabRepo=AY2223S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Helped set up the GitHub team org/repo 
  * Necessary general code enhancements e.g., refactoring work related to renaming the product
  * Maintaining the issue tracker
  * Support work to establish a forking workflow

* **Enhancements to existing features**:
  * Incorporated the new Next of Kin Phone Number field into add, edit and delete commands.
  * Incorporated tags into add, edit and delete commands.

* **Documentation**:
  * User Guide:
    * Responsible for writing parts of the [user guide](https://ay2223s1-cs2103t-t09-4.github.io/tp/UserGuide.html).
    * Added documentation for the sections `Add Next of Kin Phone`, `Add tag`, `Edit tag`, `Delete Multiple Students`, `Find by email`, `Find by address`.
  * Developer Guide:
    * Responsible for writing parts of the [developer guide](https://ay2223s1-cs2103t-t09-4.github.io/tp/DeveloperGuide.html).
    * Added documentation for the sections `Find-by feature`, `Use case: Find student by address` and more.

* **Community**:
  * Reported bugs and provided suggestions for other team, which can be found [here](https://github.com/gnahzeus/ped/issues).

* **Tools**:
  * IntelliJ
  * Github
