---
layout: page
title: Daniel Lim's Project Portfolio Page
---

### Project: Friendnancial

**Overview:**
Friendnancial is a desktop contact management application used by
financial advisors to manage their clients and contacts. The user
interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java and has about 10 kLoC.

**Summary of Contributions**

* **Code Contributed**:
  * [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=daniellimweien&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Enhancements Implemented**:
  * **Automated GUI Testing**:
    * **Description**: Added automated GUI testing into Friendnancial using the `TestFX` framework.
    * **What it does**: Automates the testing of the key parts of the GUI by simulating a human interacting with it.
    * **Justification**: Manual user testing of the GUI and its different components is time-consuming. Especially so when we
    have to frequently do regression testing as we add features to Friendnancial. Hence, automating the GUI testing process
    saves us a lot of time.
    * **Highlights**: I resolved conflicts that arose when integrating the new tests into our existing project due to changes in our
    project structure as compared to AddressBook Level 4. I also fixed an issue with the new tests breaking our existing Continuous
    Integration pipeline on GitHub Actions.
    * **Credits**:
      * Test code adapted from [AddressBook Level 4](https://se-education.org/addressbook-level4/).
      * Continuous Integration pipeline fix made use of [this](https://github.com/marketplace/actions/gabrielbb-xvfb-action) GitHub Action.


* **Contributions to the User Guide**:
  * Updated outdated screenshots and diagrams in the User Guide
  * Updated the "Command Summary" section of the User Guide
  * Restructured the display of features in the User Guide


* **Contributions to the Developer Guide**:
  * Wrote the "Add Feature" section, explaining how the `add` feature works with our updates.
  * Created the sequence diagram for the `add` feature.
  * Wrote the "Instructions for Manual Testing" section, giving instructions on how to manually test the entire application and all
  of it's existing features. As well as coming up with test cases that user's can start with for manual testing.
  * Wrote the "Appendix C" section for Automated GUI testing, instructing users on how to fix common integration problems when adding
  automated GUI testing to a project and to the Continuous Integration Pipeline.


* **Contributions to Team Based Tasks**:
  * Created issues on GitHub Issues and assigned tasks to teammates.
  * Linked Pull Requests, Issues to each other and to the Milestone for Milestone Management.
  * Incorporated `TestFX` for automated GUI testing into the project.
  * Fixed Continuous Integration issues that arose when adding automated GUI testing to our pipeline.


* **Reviewing/Mentoring Contributions**:
  * Reviewed PR with suggestion to improve abstraction [#82](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/82)
  * Reviewed PR with suggestion to make better use of software engineering principles [#92](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/92)
  * Reviewed PRs that were ready for merging and that passed our Continuous Integration checks
  * Merged PRs that had already been reviewed as the second layer of checks
  * Gave credit to teammates where good and well documented code was written


* **Contributions Beyond the Team Project**:
  * `to be added soon`

