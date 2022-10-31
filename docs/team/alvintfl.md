---
layout: page
title: Alvin Tan's Project Portfolio Page
---

### Project: YellowBook

YellowBook is for university students who are involved in many projects and have to organize their project contacts and tasks. The user interacts with YellowBook using a CLI. YellowBook has a GUI created using JavaFX, is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Sort by deadline command
  * What it does: Sorts tasks by their deadlines and maintains the sorted order.
  * Justification: Students with multiple tasks may want to prioritise doing those that are due soon to ensure that they are completed on time. Additionally, they would not want to keep sorting the list whenever changes are made to the task list.
  * Highlights: Maintaining the sorted order was the hardest part as existing commands such as `AddTaskCommand` can disrupt the sorted order. Additionally, I had to modify storage to store information about the sorted order as students will not want to sort the list each time they restart the app.

* **New Feature**: Sort by id command
  * What it does: Sorts tasks by their ids, which is the order they were added in, and maintains the sorted order.
  * Justification: Students who have used the sort by deadline command may want to switch back to the original list to view tasks based on recent it was added instead.
  * Highlights: A new id field had to be added to the task class. I also had to ensure that new ids given to new tasks do not conflict with existing ids as this will mess up the sorted order. Storage also had to be modified to store the new id field.

* **New Feature**: Delete all command
  * What it does: Delete all contacts and tasks who contain the label(s) specified. The label is also deleted.
  * Justification: When students are done with their project, they would not want to keep the contacts and tasks related to the project anymore. This provides a convenient way to delete the contacts and tasks instead of deleting them one at a time. Multiple labels are also allowed to be specified as students may finish multiple projects at similar dates. 
  * Highlights: I had to take note to not delete contacts that were involved in unfinished projects as students would still need to keep track of their contacts.

* **New Feature**: Edit task command that edits a task.

* **New Feature**: List task command that lists tasks in the task list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=alvintfl&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=alvintfl&tabRepo=AY2223S1-CS2103T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases from `v1.2`-`v1.3` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Added tabs to display task list and tag list: (Pull request [#125](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/125))
  * Updated the syntax of contact commands from Addressbook 3 (Pull request [#108](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/108))

* **Documentation**:
  * User Guide:
    * Added documentation for features: `addC`,`editT`, `sortD`, `sortI` and `deleteA` (Pull requests [#76](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/76), [#178](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/178))
  * Developer Guide:
    * Added implementation details, sequence diagram and design considerations for sort features (Pull request [#156](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/156))
    * Added use case for adding a contact (Pull request [#74](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/74))
    * Updated target user profile, value proposition and user stories based on discussion in project documents (Pull request [#64](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/64))

* **Community**:
  * [PRs reviewed](https://github.com/AY2223S1-CS2103T-F11-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aalvintfl+)
