---
layout: page
title: Desmond Yong Shao Tian's Project Portfolio Page
---

### Project: FindMyIntern

**Overview**: FindMyIntern is a desktop application that helps students keep track of internship applications. It allows students to consolidate all these applications into a single place, manage these applications, and visualise their application statuses. The user interacts with it using a CLI, and it has a GUI created with JavaFX. FindMyIntern is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=desmondyst&breakdown=true)

* **Enhancements implemented**: 
  * Added new feature `mark`
    * What it does: Allows the user to mark a specific internship application with an application status
    * Justifications: The user will want to be able to keep track of the current status of his/her internship applications. This is crucial in making other features such as `filter` and `sort` possible
  * Implemented `InterviewDateTime`
    * What it is: Represents the interview's date and time of a particular internship application
    * Key field in saving internship applications
    * Highlights: This is an optional field. As such, I had to take extra care in making sure that this field is stored and retrieved properly from the local storage
  * Enhanced `add` feature
    * Changes: Allows the user to add multiple internship applications for the same company
    * Justifications: The user can apply for multiple roles at the same company
  * Enhanced `edit` feature
    * Changes: Allows the user to modify an internship's interview date and time through the `edit` command 
    * Justifications: It is quite common for the interview's date and time of an internship to change

* **Contributions to the UG**: 
  * Added documentation for `mark` feature
  * Added section on "Command Line Guide"
    * Useful for new users that are not familiar with command line interface
    * Provides users with a quick guide on how to use command line in FindMyIntern, without diving deep into it
  * Contributed to table of contents

* **Contributions to the DG**: 
  * Added implementation of `mark` feature
    * Included activity diagram and design considerations for feature
  * Added use cases for `add`, `mark`, `find`, `filter`

* **Contributions to team-based tasks**: 
  * Added GitHub issues and milestones
  * Tagged issues and PRs with relevant tags
  * Reviewed and fixed bugs submitted by testers

* **Review/mentoring contributions**: 
  * Reviewed teammates’ PRs and merged them
    * [#16](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/16)
    * [#23](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/23)
    * [#25](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/25)
    * [#31](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/31)
    * [#33](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/33)
    * [#35](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/35)
    * [#37](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/37)
    * [#38](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/38)
    * [#40](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/40)
    * [#52](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/52)
    * [#58](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/58)
    * [#78](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/78)
    * [#79](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/79)
    * [#100](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/100)
    * [#159](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/159)
    * [#165](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/165)
    * [#170](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/170)
    * [#174](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/174)

* **Contributions beyond the project team**: 
  * Reported bugs and provided suggestions for other teams: [Bugs](https://github.com/desmondyst/ped/issues)

