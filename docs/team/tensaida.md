---
layout: page
title: Devesh's Project Portfolio Page
---

### Project: Teaching Assistant Assistant

Teaching Assistant Assistant (TAA) is a desktop app for Teaching Assistants (TAs). It keeps track of TAs’ students,
tutorial groups, and tasks.


Given below are my contributions to the project.

* **Enhancement Added**:
    * **New Feature**: Added the grade functionality and commands
        * What it does: allows the user to view and edit grades 
        * Every student-task pair is associated with a grade. Therefore, it was natural to use to a map to implement 
the grades feature. However, maps were not used in the AB3 codebase so it was challenging to incorporate a map into
TAA. Since the grade interacts with both the `Student` and `Task` classes, there were some unexpected behaviours which
had to be dealt with, particularly when tasks and students were edited (as these operations would result in the creation
of new `Student` and `Task` objects).

* **Team-tasks**: 
    * Refactored variables, class names, etc. in the AB3 codebase to make semantic sense for TAA [#50](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/50)
    * Set up [Kanban board](https://postimg.cc/BPbCmLT0/cb052c47) on Github Projects to track issues better
    * Set up Milestone v1.1 and assigned issues. Also set up Milestone v1.4
    * Incorporated [Mockito](https://site.mockito.org/) into project to create mock classes faster in unit tests [#69](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/69/)

* **Code contributed**:
    * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tensaida&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=tensaida&tabRepo=AY2223S1-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
    * [Created Issues/PRs](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues?q=author%3Atensaida+)

* **Documentation**:
    * User Guide:
        * Added documentation about the grade functionality [#117](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/117)

    * Developer Guide:
        * Added documentation about the grade functionality [#231](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/231)
        * Updated UML diagrams to reflect current iteration of TAA [#236](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/236)

* **Reviewing/mentoring contributions**:
    * PR Review: [#239](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/239), [#246](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/246), [#92](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/92), [#217](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/217)

* **Contributions beyond the project team**:
    * [Reported bugs for PED](https://github.com/tensaida/ped/issues)
