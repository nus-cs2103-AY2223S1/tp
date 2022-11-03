---
layout: page
title: Tee Yi Teng's Project Portfolio Page
---

### Project: FindMyIntern

**Overview**: FindMyIntern is a desktop application that helps students keep track of internship applications. It allows students to consolidate all these applications into a single place, manage these applications, and visualise their application statuses. The user interacts with it using a CLI, and it has a GUI created with JavaFX. FindMyIntern is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=puakii&breakdown=true)

* **Enhancements implemented**:
  * Added new feature `find`
    * What it does: Find specific internship applications based on company and tags
    * Justification: User would want to find certain internship applications from the large number of applications he/she have
  * Implemented `ApplicationStatus`
    * What it does: Represents the status of an internship application
    * Justification: Key field of an internship application. As such, this is crucial for commands such as `mark` to be possible
  * Updated `Tag`
    * Changes: Allow spacing into tag field
    * Justification: User might want to key in more than 1 word into tag
  * Refactored `Person` to `Internship`
    * Changes: Change all instances of `Person` to `Internship` in the code base
    * Justification: Essential for the team to develop FindMyIntern as a brownfield project

* **Contributions to the UG**:
  * Added documentation for `find` feature
  * Added table of contents for UG
    * Allow users to easily navigate between sections in UG
  * Added table of  contents for `Command` section
    * Allow users to easily navigate between different commands

* **Contributions to the DG**:
  * Update DG from `Person` to `Internship`
    * Include updating of all UML diagrams
  * Added implementation for `find` feature
    * Include sequence diagram and design considerations

* **Contributions to team-based tasks**:
  * Added GitHub issues and milestones
  * Tagged issues and PRs with relevant tags
  * Reviewed and fixed bugs submitted by testers

* **Review/mentoring contributions**:
  * Reviewed teammates' PRs and merged them
    * [#19](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/19)
    * [#20](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/20)
    * [#21](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/21)
    * [#36](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/36)
    * [#39](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/39)
    * [#45](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/45)
    * [#53](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/53)
    * [#60](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/60)
    * [#68](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/68)
    * [#83](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/83)
    * [#84](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/84)
    * [#85](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/85)
    * [#87](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/87)
    * [#88](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/88)
    * [#90](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/90)
    * [#101](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/101)
    * [#103](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/103)
    * [#108](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/108)
    * [#156](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/156)
    * [#158](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/158)
    * [#161](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/161)
    * [#163](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/163)
    * [#168](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/168)
    * [#171](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/171)
    * [#172](https://github.com/AY2223S1-CS2103T-T14-1/tp/pull/172)

* **Contributions beyond the project team**:
  * Reported bugs and provided suggestions for other teams: [Bugs](https://github.com/Puakii/ped/issues)
