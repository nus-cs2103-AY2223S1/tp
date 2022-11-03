---
layout: page
title: Jet Lee's Project Portfolio Page
---

### Project: InTrack

InTrack is a desktop application that helps Computer Science students who are applying for multiple internships to keep
track of their progress and deadlines across these applications. It is optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.
* **Code contributed**: My contributions can be accessed via this 
  [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jetlfj&breakdown=true)
* **Enhancements implemented**:
  * New feature: `select` command (PR: [#103](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/103))
    * What it does: Allow users to select and view the full details of an internship application.
    * Justification: Initially, InTrack only supports viewing internship applications in full detail. This causes each
    internship application to take up a lot of space in the list. Showing the full details only after selecting a 
    particular internship application allows for the original list to be much more compact, so users can scroll through
    it more easily.
    * Highlights: Implementing this feature requires in-depth understanding how the various components in the 
    application function and interact. It also requires significantly more modifications to the existing model and UI. 
  * New feature: `deltask` command (PR: [#103](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/103))
    * What it does: Allows users to delete a task from an internship application.
    * Justification: This enables users to correct a mistake when adding tasks or to remove them when they are cancelled
    and no longer need to be tracked.
  * Enhancement to existing feature: `GUI` (PR: [#103](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/103) and 
  [#151](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/151))
    * Changes: Update the UI of AB3 to meet the requirements of InTrack.
    * Highlights: Modifying the UI requires in-depth of how the UI classes observe and update when the target classes
    are changed. It also requires some understanding of JavaFX, FXML and CSS.
  * Enhancement to existing feature: `Status` (PR: [#68](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/68))
    * What it does: Tracks the current status of an internship application.
    * Changes: Removed the need to provide a status when adding an internship application as almost all newly-added
    internships would still be in progress. This saves users some time.
  * Enhancement to existing feature: `edit` command (PR: [#124](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/124))
    * What it does: Allows users to edit the details of an internship application.
    * Changes: Incorporated the `select` command to let the user view the full details of an internship application 
    first before editing. This prevents users from accidentally editing the wrong internship applications.
  * Enhancement to existing feature: `addtask` command (PR: [#103](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/103))
    * What it does: Allows users to add a task to an internship application.
    * Changes: Incorporated the `select` command to let the user view the full details of an internship application
      first before adding a task.
  * Enhancement to existing feature: `remark` command (PR: [#148](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/148))
    * What it does: Allows users to add a remark to an internship application.
    * Changes: Incorporated the `select` command to let the user view the full details of an internship application
      first before adding a remark.
* **Contributions to the UG:**:
  * Added complete documentation for the following features: `add`, `delete`, `select`, `edit`, `list`.
  * Updated documentation for other features.
  * Added most of the screenshots in the UG.
  * Added most of the Command Summary.
  * Handled formatting of the UG.
* **Contributions to the DG**: 
  * Added explanation, implementation, diagrams and design considerations for the following feature: `status`.
  * Added all use cases.
* **Contributions to team-based tasks**:
  * Set up the GitHub team organisation and repository
  * Set up tools including Gradle, Codecov and Continuous Integration (CI)
  * Managed release: [v1.3.2](https://github.com/AY2223S1-CS2103T-T11-2/tp/releases/tag/v1.3.2)
  * Maintained the issue tracker and milestones for the team
  * Created the features demo for v1.2
* **Review/mentoring contributions:**:
  * Reviewed PRs (Examples: [#66](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/66),
  [#80](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/80),
  [#136](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/136))
  * Helped team members with testing and debugging their code.
* **Contributions beyond the project team**:
  * Contributed to forum discussions (Examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/35), 
  [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/135),
  [3](https://github.com/nus-cs2103-AY2223S1/forum/issues/230))
