---
layout: page
title: Jovon's Project Portfolio Page
---

## Project: idENTify
### Overview
IdENTify is a **desktop app made for Ear, Nose and Throat (ENT) administrative staff to manage patients' contact details
and appointments, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, idENTify can get your patients/appointments' management tasks
done faster than traditional GUI apps.

### Summary of Contributions
Given below are my contributions to the project.

Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jovonlim&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

Enhancements implemented:
* `Appointment` Class with storage: To create different appointments for the patients and store those objects into JSON format.
* `Observer` pattern: Made use of Observable and Callback to automatically update the patients lists and appointment lists when appointments are updated, added or removed.
* `Book` Command: Allows users to book a new appointment for the specified patient. Appointments are automatically sorted according to their dateTime.
* `Edit Appt` Command: Allows users to edit an existing appointment details.
* `Mark` Command: Marking a recurring appointment will automatically book a new appointment for the patient, provided the patient does not have an existing appointment at the new dateTime.
* `History` feature with storage: Keep tracks of the most recent 10 commands and allows the user to cycle through them to reduce repetition of similar but long commands. The commands will then be stored into JSON format.

Contributions to the UG:

Updated `Intro`, `Quick Start` sections and added `Book` command, `Edit Appts` command and `History` feature.

Contributions to the DG:

* Updated UML diagrams and descriptions for `Ui`, `Model` and `Storage`.
* Added UML diagrams and descriptions for `Book` and `Edit Appts` commands.
* Added Appendix Effort.

Contributions to team-based tasks:

Created milestones and assigned some issues for team members.

Review/mentoring contributions:

* [PR_Review_1](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/66)
* [PR_Review_2](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/76)
* [PR_Review_3](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/112)
* [PR_Review_4](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/114)

Contributions beyond the project team:

[Bugs reported during PE-D](https://github.com/JovonLim/ped/issues)
