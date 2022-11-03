---
layout: page
title: Chantell's Project Portfolio Page
---

### Project: InTrack

InTrack is a desktop application that helps Computer Science students who are applying for multiple internships to keep
track of their progress and deadlines across these applications. It is optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=chantellyu&breakdown=true)
* **Enhancements implemented**: 
  * New feature: `findp` command (PR: [#87](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/87))
      * What it does: Allow users to find all internship applications whose positions match the user input.
      * Justification: Initially, InTrack only supports finding internship applications via name. However, this may limit 
    users who wish to search by other parameters. We felt that position would also be a highly searched for parameter.
  * Enhancement to existing feature: `help` (PR: [#150](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/150))
    * Changes: Modified the UI of the Help Window.
  * Enhancement to existing feature: `sort time` (PR: [#147](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/147))
      * Changes: Rewrote the code of the `sort time` feature as it initially would throw out an error once there was 
    an application in the list that did not contain a dated task. Changed the code to allow entries with tasks to be 
    sorted, while those without dated tasks would be listed at the bottom.
* **Contributions to the UG:**: 
  * Added complete documentation for the following features: `edit`, `remark`.
  * Updated documentation for other features.
  * Proofread UG for readability and comprehensibility.
  * Handled organization and formatting of the UG.
* **Contributions to the DG**: 
  * Added explanation, implementation, diagrams and design considerations for the following feature: `findn`.
* **Contributions to team-based tasks**: 
  * Reviewed [15](https://github.com/AY2223S1-CS2103T-T11-2/tp/pulls?q=is%3Apr+is%3Aclosed) team pull requests.
* **Review/mentoring contributions:**: 
  * Reviewed PRs (Examples: [#86](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/86),
      [#118](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/118),
      [#133](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/133))
* **Contributions beyond the project team**:
  * Submitted [17](https://github.com/AY2223S1-CS2103T-W13-2/tp/issues?q=is%3Aissue+is%3Aclosed) issues during the 
  practical exam dry run.
