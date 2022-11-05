---
layout: page
title: Tan Wen Hao Brendan's Project Portfolio Page
---

### Project: FindMyIntern

**Overview**: FindMyIntern is a desktop application that helps students keep track of internship applications. It allows students to consolidate all these applications into a single place, manage these applications, and visualise their application statuses. The user interacts with it using a CLI, and it has a GUI created with JavaFX. FindMyIntern is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nerbnerb&breakdown=true)

* **Enhancements implemented**:
  * Added new feature `filter`
    * What it does: Allows the user to filter internship applications for specific application statuses
    * Justifications: The user will want to filter for different application statuses according to his/her needs, such as viewing internship applications which he/she has been shortlisted for interviews.
  * Implemented `AppliedDate`
    * What it is: Represents the date which the user applied for an internship
    * Crucial field in saving internship applications
  * Overhauled GUI
    * Updated FindMyIntern to a more modern user interface
    * Rearranged and resized elements to better suit users' needs
  * Enhanced `add` feature to accept `InterviewDateTime`
    * What it does: Allows the user to include an optional interview date/time while adding internship applications
    * Automatically sets internship application status to "Shortlisted" instead of default "Applied"

* **Contributions to the UG**:
  * Integrated Bootstrap 5 into user guide
  * Added documentation for `filter` feature
  * Added tutorial section "Adding your first internship application"
    * Useful for new users familiarising themselves with FindMyIntern
    * Annotated UI for new users
  * Added comparison between `filter` and `find` commands
    * Useful as both commands are similar
  * Added section for UI elements
    * Explains purpose of certain elements which may be less obvious
  * Added appendix section
    * Defines requirements about input fields for commands and explains certain fields

* **Contributions to the DG**:
  * Added implementation details of `filter` feature
    * Included activity diagram and design considerations for feature
  * Updated diagrams to reflect changes to UI
  * Added user stories

* **Contributions to team-based tasks**:
  * Added GitHub issues and milestones
  * Tagged issues and PRs with relevant tags
  * Reviewed and fixed bugs submitted by testers

* **Review/mentoring contributions**:
  * Reviewed teammates' PRs and merged them
    * [#17](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/17)
    * [#28](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/28)
    * [#32](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/32)
    * [#44](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/44)
    * [#57](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/57)
    * [#72](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/72)
    * [#80](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/80)
    * [#81](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/81)
    * [#82](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/82)
    * [#154](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/154)

* **Contributions beyond the project team**:
  * Reported bugs and provided suggestions for other teams: [Bugs](https://github.com/nerbnerb/ped/issues)
