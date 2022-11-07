---
layout: page
title: Jun Wei's Project Portfolio Page
---

### Project: YellowBook

YellowBook is a desktop application for university students who are involved in many projects to organize their project contacts and tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New feature**: Archive / Unarchive command for tasks
  * What it does:  allows users to mark a task as archived
  * Justification: users may wish to remove a task from their task list but be able to access it at a later time

* **New feature**: Undo / Redo command
  * What it does:  allows users to undo and redo commands
  * Justification: users may wish to undo and redo their previous commands
  * Credits: The general idea was referenced from proposed feature in Addressbook level 3 [developer's guide](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)

* **New feature**: Filter command for contacts / tasks
    * What it does:  allow users to find contacts and tasks by labels
    * Justification: users may wish to find contacts and tasks by labels
    * Highlights: The predicate classes for contact and task were modified to allow for filtering by labels. This allowed the class's reuse and reduced code duplication.

* **New feature**: Find command for tasks
  * What it does:  allows users to find tasks based on task fields (description, deadline, and completion status)
  * Justification: users may wish to find task based on certain task fields
  * Highlights: The find command required the implementation of a new predicate class to filter tasks based on task fields. Tell-Don't-Ask principle was applied by ensuring that checks for whether task fields contain keywords are done in their respective classes (e.g. Description, Deadline, and CompletionStatus) rather than the predicate class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=junwei-tan&breakdown=true)

* **Project management**:
  * Created issues and assigned them

* **Enhancements to existing features**:
    * Updated find contact command to search all fields in addition to name [\#110](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/110)
    * Added unique id to contacts using Java UUID class [\#168](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/168)
    * Added CompletionStatus and ArchivalStatus class [\#263](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/263) 
    * Wrote additional tests for newly added and existing features to increase coverage

* **Documentation**:
    * User Guide:
        * Added documentation for features: `undo`, `redo`, `findC`, `filterC`, `findT`, `filterT`, `archiveT` and `unarchiveT` [\#190](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/190)
        * Added sample usage section [\#196](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/196)
        * Added column in command summary for mnemonic naming of commands [\#190](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/190)
    * Developer Guide:
        * Added description for fields of Person, Task, and Tag [\#165](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/165)
        * Added implementation details for `findC` feature [\#159](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/159)
        * Added implementation details for `undo` and `redo` feature [\#165](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/165)
        * Updated instructions for manual testing [\#287](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/287)
        * Added Non-function requirements (NFRs) [\#80](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/80)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#123](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/123) [\#175](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/175)
    * Reported bugs and suggestions for another team in the class (examples: [1](https://github.com/junwei-tan/ped/issues/9), [2](https://github.com/junwei-tan/ped/issues/7), [3](https://github.com/junwei-tan/ped/issues/5))

* **Tools**:
    * Added `mockito` dependency for testing
