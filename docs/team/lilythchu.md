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

* **New feature**: Added the ability to view grade chart
    * What it does: Allows the user to view the pie chart showing an overview of the number of students in each grade category.
    * Justification: This feature improves the product significantly as it helps TAs to keep track of their student's progress.

* **Enhancements to existing features**:
    * Added the ability to add and delete tutorials.
        * What it does: Allows the user to add and delete any tutorials they might have.
        * Justification: This feature makes our product more attractive as users would usually want to keep track of tutorials that they are teaching.
        * Highlights: This enhancement affects existing command as it was similar to the `add`, `edit` and `delete` commands that AddressBook already has.
          Thus, there was a need to abstract out similar components and adhere to the DRY principle.
    * Improved Ui
        * What it does: Allows our app to have multiple tabs and users can click to switch between those tabs, changes the content's layout and adds more icons.
        * Justification: This feature enhances the visualization of our app which makes it more attractive.

* **Documentation**:
    * User Guide:
        * Added screenshots for `switch f/grade` and created a table comparing before executing command to after executing command.
        * Added documentation for `tutorial` and `switch` feature.
    * Developer Guide:
        * Updated class diagram and description for `UI` component
        * Added implementation for show grade features
        * Added instruction for manual testing for `tutorial` and `switch` feature.
        * Added use case for `delete tutorial`
      
* **Contributions to team-based tasks**:
    * Assigning issues to teammates.
    * Update `README.md` and main page of product website.

* **Review/mentoring contributions**:
    * PRs reviewed with non-trivial review comments: [#50](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/50), [#56](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/56#discussion_r992426482), [#113](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/113), [#248](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/248)
