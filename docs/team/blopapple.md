---
layout: page
title: Lau Jun Jie's Project Portfolio Page
---

## Project: UniNurse

UniNurse is a desktop application used for managing patient contact details and tasks. The user interacts with it using
a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

## Contributions to project

### New feature: Viewing patient's details

Added the ability to view a patient's detatils.
* What it does: Allows the user to view all detials of a patient in a separate view provided in the output panel.
* Justification: It allows the user to view all important details of a patient in a neatl and clean format.
* Highlights: The view provided requires technical knowledge on dynamically resizing GUI elements when more patient details are added or removed.

### New feature: Viewing patient's tasks

Added the ability to view the list of tasks for a particular patient or for all patients.
* What it does: Allows the user to view all tasks for a particular patient of for all patients in a separate view provided in the output panel.
* Justification: It allows the user to only view the tasks deatils for patients and does not display any other details, allowing the user to focus on the list of tasks that are presented.
* Highlights: The view layouts are designed to be optimal for viewing important details in a condensed view.

### New feature: Adding patient's medications and remarks

Added the ability to add/edit/delete the medications and remarks for a patient.
* What it does: Allows the user to add/edit/delete a medication or a remark to a patient.
* Justification: It allows the user to add information such as patient medications or additional notes about the patient to take note of.
* Highlights: A dynamic way of editing a medication was implemented as it consists of 2 fields (medication type and dosage). This is encapsulated in an utility class to handle modifications of either one of or both fields.

### New feature: Dynamic editing of patient's tasks

Added the ability to dynamically edit a task for a patient.
* What it does: Allows the user to dynamically edit a patient's task. 
* Justification: It allows the user to edit a task without requiring the user to repeat the same fields that are not going to be edited.
* Highlights: A dynamic way of editing a task utilizes an utiliy class similar to the one used in editing medications.

### Code contributed
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=blopapple&breakdown=true)

### Enhancements to existing features
* Updated GUI to support the different views available.
    * Refine UpdatedPatientCard [\#293](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/293)
    * Implemented ScheduleListPanel to support time-based queries for task viewing [\#285](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/285)
    * Implemented TruncatedTaskListPanel to support viewing all patients' tasks [\#285](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/285)

### Contributions to User Guide
* Added documentation for the `listTask` feature [\#67](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/67)
* Added documentation for the `viewTask` feature [\#67](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/67)
* Added documentation for the `view` related features [\#402](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/402)
* Added documentation for the `add/edit/delete` `Medication` feature [\#403](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/403)
* Added documentation for the `add/edit/delete` `Remark` feature [\#403](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/403)
* Added documentation for the `focus` feature [\#433](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/433)

### Contributions to Developer Guide
* Updated User Profile [\#46]()
* Updated Value Proposition [\#46]()
* Updated User Stories [\#46]()
* Updated Glossary [\#49]()
* Added implementation details of `listTask` feature [\#230]()
* Added implementation details of `viewTask` feature [\#230]()

### Contributions to team-based tasks
* Managed the code quality of the codebase
* Helped with code reviews for major feature changes

### Contributions beyond project team
