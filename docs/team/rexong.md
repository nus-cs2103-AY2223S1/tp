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
  * Refactored most `person` and `addressbook` to `client` and MyInsuRec` respectively. [#116](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/116) This task alone was fairly simple to complete after looking at tutorials from the textbooks and only had to ensure that the testcases still works despite the change in name.
  * Implemented `delMeeting` command. [#129](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/129) This task implements a new command as part of the CRUD features for meetings which was manageable. 
  * Implemented tests for `delMeeting` command. [#140](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/140) To ensure the `delMeeting` command works as intended, I wrote some test cases to ensure it works as intended.
  * Changed Client's attributes `meeting` to meeting list `List&lt;Meeting&gt;`. [#164](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/164) This task was challenging as our team wanted to make a bidirectional association immutable and I spent hours trying to find a solution that can work. However, we were not able to find a solution and due to time, we settle with our current implementation of having the meeting list in client mutable. 
  * Implemented `listClient` by birthday to list client with upcoming birthday. [#202](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/202) This task was manageable as this was an implementation that builds on functions we already had and I felt that this was similar to implementing a new feature.
  * Fixed valid date accepted by both the `MeetingDate` and `Birthday`. [#294](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/294) This task was fairly simple as I just added checks to ensure valid results.

* **Contributions to team-based task**:
  * Designed the User Interface for MyInsuRec. [Mockup](https://www.figma.com/file/uTvPCqGAF8BiQUaxK1gU5r/CS2103T-MyInsuRec-Mockup?node-id=0%3A1)
  * Find out that bidirectional association between 2 objects were hard to implement with immutability, ie `Meeting` contains a `Client` and a `Client` contains a list of `Meeting`.

* **Documentation**:
  * README:
    * Update the Target Users. [#47](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/47)
  * User Guide:
    * Added documentation for the features `addMeeting` and `delMeeting`. [#57](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/57) and [#58](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/58)
    * Added documentation for command feature. [#59](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/59)
    * Updated documentation for the features `addClient` and `delClient`. [#198](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/198) 
    * Updated documentation for the feature `delMeeting`. [#198](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/198)
    * Added a `How to use this User Guide` section. [#270](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/270)
    * Updated `Overview` section by highlighting the keywords. [#270](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/270)
    * Added `User Interface` section with a Word Document to enable changes. [#270](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/270)
  * Developer Guide:
    * Added delete meeting use case. [#67](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/67)
    * Added `Delete Meeting Feature` section. [#173](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/173)
    * Added a sequence diagram in `Delete Meeting Feature` section. [#173](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/173)
    * Added a activity diagram in `Delete Meeting Feature` section. [#173](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/173)

* **Notable Review Contributions**:
  * Suggested to use `Optional` for `Birthday` attribute in client, this helps to prevent any possible `NullPointerException` error to be thrown. [#168](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/168)
  * Gave a comprehensive UG comment in order to standardize the format used in User Guide by all team member such that the User Guide seems like it is done by one person. [#206](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/206)
