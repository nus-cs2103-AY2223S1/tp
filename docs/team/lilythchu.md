---
layout: page
title: Chu Thi Thanh's Project Portfolio Page
---

# Overview

ModQuik is a **desktop app for NUS Teaching Assistants, optimised for use via a Command Line Interface (CLI)**.
It allows TAs to keep track of their responsibilities, students’ progress and schedules for the ongoing semester.

## Summary of contributions
Given below are my contributions to the project.

* **Code contributed**: [lilythchu's tP Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lilythchu&breakdown=true)

* **New feature**: Added the ability to switch between different contents within the app.
    * What it does: Allows the user to switch between student, tutorial, consultation and grade chart by typing command. Switches automatically to related content after executing command successfully.
    * Justification: This feature makes our product more optimal for fast-typists. It allows users to switch between different tabs without the mouse and brings them to desired tab after executing command related to its contents.
    * Highlights: This enhancement improved the entire UI and allowed us to have multiple tabs which users could view.

* **New feature**: Added the ability to view grade chart
    * What it does: Allows the user to view the pie chart of all their students' grade.
    * Justification: This feature improves the product significantly as it helps TAs to keep track of their student's progress.

* **Enhancements to existing features**:
    * Added the ability to add and delete tutorials.
        * What it does: Allows the user to add and delete any tutorials they might have.
        * Justification: This feature makes our product more attractive as users would usually want to keep track of tutorials that they are teaching.
        * Highlights: This enhancement affects existing command as it was similar to the `add`, `edit` and `delete` commands that AddressBook already has.
          Thus, there was a need to abstract out similar components and adhere to the DRY principle.

* **Documentation**:
    * User Guide:
        * Added screenshots for the following features:
            * `mark reminder`
            * `switch f/grade`
        * Added documentation for `tutorial` and `switch` feature.
    * Developer Guide:
        * Updated class diagram and description for `UI` and `Model` component
        * Added implementation for show grade features
        * Added instruction for manual testing for `tutorial` and `switch` feature.
        * Added use case for `delete tutorial`
      
* **Contributions to team-based tasks**:
    * Assigning issues to teammates.
    * Update `README.md` and main page of product website.

* **Review/mentoring contributions**:
    * PRs reviewed with non-trivial review comments: [#50](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/50), [#56](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/56#discussion_r992426482), [#113](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/113), [#248](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/248)
