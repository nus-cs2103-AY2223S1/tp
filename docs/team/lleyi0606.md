---
layout: page
title: Lin Leyi's Project Portfolio Page
---

### Project: NotionUS

NotionUS is a desktop task tracking application used for tasks, and specially tailored toward university students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.
Given below are my contributions to the project.

* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=lleyi0606&tabRepo=AY2223S1-CS2103T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Enhancements implemented**:
  * Command History (Returning to a previous command)
    * The challenging part of the implementation of this feature was that it had to be integrated with the UI. I explored and had to understand how FXML worked from scratch to find the correct place to incorporate my functions. 
  * List 
    * By tag
    * By deadline 
    * Enhancing `List` to take in multiple tags 
      * Prior to this enhancement, only one filter could be applied in every list command. However, we found that this wouldn't be optimised for our target users' usage, and decided to implement stacking of filters in a single command. 
      * Taking inspiration from how `add` takes in multiple flags with the help of `ArgumentMultimap`, `ListCommandParser` was designed in a similar manner. 
      * Adapted `ListCommand` to take in a list of `Predicates` that represented each filter, and reduced them into one in the `ListCommand#execute` method to be passed into `Model#updateFilteredTaskList` 
* **Contributions to the UG**: 
  * Made some example diagrams for the individual commands, showing the before and after effects
    * For `add`, `delete`, `tag`, `list`, etc. 
  * Ensured that the tone was appropriate and consistent throughout 
  * Contributed to writing a portion of the [command structure](https://ay2223s1-cs2103t-f12-3.github.io/tp/UserGuide.html#command-structure)
  * Wrote the portions on [Returning to a previous command](https://ay2223s1-cs2103t-f12-3.github.io/tp/UserGuide.html#returning-to-a-previous-command--updown-keys) and some `list` commands (`tag`, `deadline`) 
* **Contributions to the DG**:
  * Wrote implementation details for [List feature](https://ay2223s1-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#53-list-feature)
    * Created UML sequence diagrams to showcase the mechanism at each step under Usage Scenario 
  * Wrote implementation details for [Returning to a previous command](https://ay2223s1-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#55-returning-to-a-previous-command)
    * Created UML action diagram
    * Created diagrams to showcase how the mechanism works at each step under Usage Scenario
  * Wrote some manual testing examples (8.2, 8.3, 8.6, 8.7)
  * Numbering of sections to make the DG more organised 
* **Contributions to team-based-tasks**: 
  * Take minutes in meetings
  * Helped to communicate with TA for tutorial submissions 
  * Facilitated discussion for PE-D Reviewers Peer Review and submitted on behalf of the team 
* **Review/mentoring contributions:** 
  * Help to review and merge PRs of other team members [(View here)](https://github.com/AY2223S1-CS2103T-F12-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Alleyi0606)
* **Contributions beyond the project team**: 
  * Helped to bug-check and give suggestions to other teams [(View here)](https://github.com/lleyi0606/ped/issues)
