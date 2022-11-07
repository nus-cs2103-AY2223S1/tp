---
layout: page
title: 'Izzah Bte Abdul Jalil's Project Portfolio Page
---

## Project: UniNurse

[UniNurse](https://ay2223s1-cs2103t-t12-4.github.io/tp/) is a desktop application used for managing patient contact details and tasks. 
It is targeted at private nurses to help them manage their patients in a more organized and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

## Contributions to project

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=izzahaj&breakdown=true)
* **New Feature**: Added ability to add/delete medical conditions to a patient ([PR #202](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/202), [PR #239](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/239)).
  * What it does: Allows users to add and delete medical conditions to a patient.
  * Justification: This feature is essential to help users keep track of and remember a patient's (multiple) medical conditions.
* **New Feature**: Added the ability to edit a medical condition ([PR #266](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/266)). 
  * What it does: Allows users to edit medical conditions of a patient.
  * Justification: Allows users to update a patient's medical condition without the hassle of deleting and re-creating the medical condition.
* **Testing**:
  * Wrote unit and integration tests (> 90% coverage) for the condition and tag features mentioned.
  * Found and reported bugs via manual testing of the application ([Issues and bugs reported](https://github.com/AY2223S1-CS2103T-T12-4/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aizzahaj)).
* **Enhancements to existing features**:
  * Implemented cumulative addition of tags to a patient ([PR #245](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/245)).
  * Added ability to edit and delete tags one by one ([PR #264](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/264), [PR #283](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/283)).
* **Project management**:
  * Managed releases `v1.1`-`v1.4` on GitHub (7 releases).
  * Maintained the issue tracker.
* **Contributions to team-based tasks**:
  * Set up the team repository and project website.
  * Set up Codecov (code coverage plugin) in the team repository.
  * Changed the product icon.
* **Contributions to User Guide**:
  * Revamped user guide to use more welcoming and reader-directed language ([PR #130](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/130)).
  * Regrouped sub-headers in feature section in table of contents for better readability ([PR #366](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/366)).
  * Added a command summary table, `How to use this guide` section, and `Parameter constraints` section.
  * Added icons, tips and notes to `adding a patient`, `editing a patient`, `deleting a patient`, `clearing all entries` features.
  * Added documentation for `adding a tag`, `editing a tag`, `deleting a tag`, `adding a medical condition`, `editing a medical condition` and `deleting a medical condition` features.
* **Contributions to Developer Guide**:
  * Added non-functional requirements.
  * Added the following use cases: UC01, UC08, UC15 - 20
  * Added implementation details for multi-valued attributes feature (including activity and sequence diagrams) ([PR #444](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/444)).
  * Added a short introduction section.
  * Re-organised and added subheadings to Logic, UI, Model and Storage component sections ([PR #446](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/446)).
