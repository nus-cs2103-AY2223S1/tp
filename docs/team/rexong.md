---
layout: page
title: Ong Han Wei's Project Portfolio Page
---

### Project: MyInsuRec

MyInsuRec is a desktop app for financial advisors to manage and organize their clients and meetings,
helping financial advisors stay connected with their clients.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rexong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements Implemented**:
  * Refactored most `person` and `addressbook` to `client` and MyInsuRec` respectively. [#116](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/116)
  * Implemented `delMeeting` command. [#129](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/129)
  * Implemented tests for `delMeeting` command. [#140](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/140)
  * Changed Client's attributes `meeting` to meeting list `List&lt;Meeting&gt;`. [#164](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/164)
  * Implemented `listClient` by birthday to list client with upcoming birthday. [#202](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/202)
  * Fixed valid date accepted by both the `MeetingDate` and `Birthday`. [#294](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/294)

* **Contributions to team-based task**:
  * Designed the User Interface for MyInsuRec. [Mockup](https://www.figma.com/file/uTvPCqGAF8BiQUaxK1gU5r/CS2103T-MyInsuRec-Mockup?node-id=0%3A1)
  * Find out that bidirectional association between 2 objects were hard to implement with immutability, ie `Meeting` contains a `Client` and a `Client` contains a list of `Meeting`.

* **Documentation**:
    * README:
        * Update the Target Users
    * User Guide:
        * Added documentation for the features `addMeeting` and `delMeeting`
        * Added documentation for command feature
    * Developer Guide: [To be updated]

* **Community**: [To be updated]

* **Tools**: [To be updated]
