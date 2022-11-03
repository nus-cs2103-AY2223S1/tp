---
layout: page
title: Charlton Teo's Project Portfolio Page
---

### Project: InTrack

InTrack is a desktop application that helps Computer Science students who are applying for multiple internships to keep
track of their progress and deadlines across these applications. It is optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.
* **Code contributed**: My contributions can be accessed via this
  [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=bigcrushes&breakdown=true)
* **Enhancements implemented**: 
  * New feature: `stats` command (PR: [#71](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/71))
    * What it does: Allow users to display statistics of the current list of internship applications based on their 
  status.
    * Justification: This allows the user to get a quick overview of the results of all their applications, which will be 
  crucial in helping them evaluate how strong their applications are at a glance.
    * Highlights: Implementing this feature required careful testing as calculating percentages can lead to division by zero 
  errors. Implementing it with OOP in mind was also tricky as a directly iterative approach would have been a quick way 
  to implement this feature.

  * New feature: `filter` command (PR: [#100](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/100))
      * What it does: Allow users to filter the internship applications by status.
      * Justification: This allows the user to review their internship applications, viewing which internship applications
      are still in progress and require work or collating successful/unsuccessful applications to assess what went well 
      and what went wrong respectively. 

  * New feature: `findt` command (PR: [#133](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/133))
    * What it does: Allow users to filter the internship applications by tags.
    * Justification: Tags are frequently used as identifiers and having these identifiers would be quite useless
    without a way to find them throughout the list. This command allows users to do so.

  * Enhancement to existing feature: `addtask` command (PR: [#202](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/202))
    * What it does: Allows users to add a task to an internship application
    * Changes: Changed behaviour of accepting correctly formatted but invalid dates to detecting and rejecting them instead.

* **Contributions to the UG**: 
    * Added complete documentation for the following features: `findn`, `findp`, `findt`, `stats`, `filter`.
    * Fixed documentation and standardisation for other features.
    * Added help command screenshot.
  
* **Contributions to the DG**: 
  * Added explanation, implementation, diagrams and design considerations for the following feature: `add`.
  
* **Contributions to team-based tasks**: 
  * Drafted up initial user guide from AB3 to InTrack.
  * Added position command to morph AB3 to InTrack. (PR: [#54](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/54))
  * Fix duplicate checking to take into account both company and position. (PR: [#67](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/67))
  * Fix duplicate checking to be case-insensitive. (PR: [#78](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/78))
  * Documented team's v1.2 postmortem.
  * Managed release: [v1.3.1](https://github.com/AY2223S1-CS2103T-T11-2/tp/releases/tag/v1.3)
  * Helped reformat v1.3 features demo to be more reader-friendly and added some features with screenshots.
  * Consolidated number of bugs of each type for PED.

* **Review/mentoring contributions**: 
  * Reviewed PRs (Examples: [#99](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/99),
  [#118](https://github.com/AY2223S1-CS2103T-T11-2/tp/releases/tag/v1.3), [#204](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/204)
  )
* **Contributions beyond the project team**: 
  * 12 bugs reported on Healthcare Xpress' product (Example: [#165](https://github.com/AY2223S1-CS2103-F13-4/tp/issues/165), 
  [#186](https://github.com/AY2223S1-CS2103-F13-4/tp/issues/186), [#229](https://github.com/AY2223S1-CS2103-F13-4/tp/issues/146)
  )
