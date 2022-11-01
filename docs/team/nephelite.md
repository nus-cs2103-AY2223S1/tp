---
layout: page
title: Tjan Eng Ger, Kevin's Project Portfolio Page
---

### Project: TaskBook

TaskBook is a desktop address book and task assignment application for NUS students to efficiently manage their contacts and tasks.

Given below are my contributions to the project.

* **New Features**:
  * `task sort` ([#144](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/144), [#176](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/176))
    * Added support to sort tasks alphabetically, by when they were added, or by their date.
  * `task find` ([#161](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/161))
    * Added support to filter tasks by a query, their assignment, or their done status.
  * `contact sort` ([#174](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/174), [#176](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/176))
    * Added support to sort contacts alphabetically, or by when they were added.
  * `contact find` ([#171](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/171))
    * Added support to find contacts by a query.
  * TaskListPanel ([#99](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/99))
    * Added Gui component that displays tasks added to Task Book, after applying filters from find commands, and sorted by sort commands.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nephelite&breakdown=true)


* **Project management**:
    * Managed releases [v1.3](https://github.com/AY2223S1-CS2103T-T13-4/tp/releases/tag/v1.3), [v1.3.1](https://github.com/AY2223S1-CS2103T-T13-4/tp/releases/tag/v1.3.1) (2 releases) on Github.


* **Enhancements to existing features**:
  * Gui revamp
    * Change in color scheme ([#177](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/177))
    * Assigning different task list card colors to each type of task ([#177](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/177))
    * Formatting task list cards ([#177](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/177))
  * Added SortedList wrapper to the filteredPersonList in ModelManager ([#174](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/174))


* **Documentation**:
    * User Guide:
        * Front matters:
          * Introduction ([#236](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/236))
          * Gui breakdown ([#239](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/239))
          * Useful notations ([#236](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/236))
        * Feature descriptions:
          * `task sort`([#150](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/150), [#176](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/176))
          * `task find` ([#161](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/161))
          * `contact sort` ([#174](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/174), [#176](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/176))
          * `contact find` ([#174](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/174))
    * Developer Guide:
        * [Acknowledgements](../DeveloperGuide.md#acknowledgements) ([#182](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/182))
        * [Update Ui diagram](../images/UiClassDiagram.png) ([#157](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/157))
        * [Sorting task list implementation](../DeveloperGuide.md#sorting-task-list) ([#157](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/157))
        * [Sorting diagram](../images/SortDescriptionAlphabeticalSequenceDiagram.png) ([#157](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/157))


* **Team**:
  * PRs reviewed(non-trivial review comments): [#175](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/175), [#178](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/178), [#238](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/238)  
  * Made [meeting agendas](https://docs.google.com/document/d/14En-ABkoTu9jwAkJWjvaA7r7CGlvVIR7W2VpX8hGLHE) for all non-trivial team meetings.
    * Assisted in documenting some parts of the discussions.
  * Main facilitator of team meetings
  * Did both [v1.2 and v1.3 feature demos](https://docs.google.com/document/d/14En-ABkoTu9jwAkJWjvaA7r7CGlvVIR7W2VpX8hGLHE)


* **Bugs fixed**:
  * Fields with long strings are not well formatted ([#200](https://github.com/AY2223S1-CS2103T-T13-4/tp/issues/200))
  * Missing functionality in CLI only available in the Gui ([#229](https://github.com/AY2223S1-CS2103T-T13-4/tp/issues/229))
  * Phone number documentation ([#204](https://github.com/AY2223S1-CS2103T-T13-4/tp/issues/204))


* **Community**:
    * Reported [11 bugs](https://github.com/Nephelite/ped/issues) for other teams in PE-D.
    * Posted on forum (activity in 11 posts):
      * [#37](https://github.com/nus-cs2103-AY2223S1/forum/issues/37): Reporting bug involving video quiz.
      * [#59](https://github.com/nus-cs2103-AY2223S1/forum/issues/59): Asked about if adding Gradle manually was allowed.
      * [#161](https://github.com/nus-cs2103-AY2223S1/forum/issues/161): Disambiguation about a weekly quiz.
      * [#173](https://github.com/nus-cs2103-AY2223S1/forum/issues/173): Asked about which JAR file was used for grading in iP.
      * [#190](https://github.com/nus-cs2103-AY2223S1/forum/issues/190): Attempted to help resolve a fellow's student's query about missing JavaFX runtime components.
      * [#274](https://github.com/nus-cs2103-AY2223S1/forum/issues/274): Asked about automated JAR file smoke tests by module coordinator failing.
      * [#346](https://github.com/nus-cs2103-AY2223S1/forum/issues/346): Asked about a Github CI failure.
      * [#393](https://github.com/nus-cs2103-AY2223S1/forum/issues/393): Asked for further clarification about a fellow student's query about whether an issue is a bug or Uii enhancement.
      * iP smoke test posts:
        * [#246](https://github.com/nus-cs2103-AY2223S1/forum/issues/246): Asked about having to use `-Djdk.gtk.version=2` to run files 
          * This discussion led to useful information referenced by many students.
        * [#230](https://github.com/nus-cs2103-AY2223S1/forum/issues/230): Smoke test.
        * [#242](https://github.com/nus-cs2103-AY2223S1/forum/issues/242): Smoke test.
