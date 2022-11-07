---
layout: page
title: Project Portfolio Page (@alwhgithub)
---

### Project: HackAssist

HackAssist is a desktop application which allows team leads to distribute and manage tasks for Hackathons. We have implemented various commands and clear user interfaces to help display and organize all the relevant information.

Given below are my contributions to the project.

* **New Feature**: Added feature to dynamically display Person within Tasks, and display Task within Person
  * What it does: For each task, displays the person that the task is assigned to. For each person, displays the list of all tasks that is assigned to that person
  * Justification: Displaying the link between tasks and people assigned is one of our core features.
  * Highlights: The name and email of people assigned in tasks are edited when that person's name or email is edited (vice versa with task's name with the person)
 
* **New Feature**: Created classes Deadline and Category
  * What it does: Allows Tasks to contain Deadline and Category classes, as opposed to LocalDate and String classes
  * Justification: Attributes of Tasks should be well encapsulated with their internal functions
  * Highlights: Simplifies the implementation of Sort and Filter command


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ALWHgithub&tabRepo=AY2223S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Created test cases, increasing Code Coverage from  52.13% to 65.87% [\#59]()
  * Made parameters for commands case insensitive [\#162]()
  * Edited errors for incorrect category when adding/editing task [\#162]()
  
* **Documentation**:
  * User Guide:
    * Updated add, delete and edit commands to be consistent with new features. [\#173]()
    * Documented what purpose each section of the user interface serves [\#169]()
    * Updated Quickstart image to be consistent with Updated UI and added labels for each section of the interface [\#169]()
  * Developer Guide:
    * Updated appendix to be consistent with new features [\#192]()
    * Expanded use cases to cover scenarios not covered in the original application [\#221]()
    * Updated description and implementation of UI to be consistent with new UI [\#206]()
    * Added UML and Activity Diagram for AddTask command [\#97]()

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#54](), [\#90](), [\#193]()
  * Reported bugs and suggestions for other teams in the class

