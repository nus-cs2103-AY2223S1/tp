---
layout: page
title: Kartikeya's Project Portfolio Page
---

# Project: AddressBook Level 3

## Overview

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Summary of Contributions

### Code contributed
RepoSense: [link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kxrt&breakdown=true)

### Enhancements implemented

- Obtaining count of all patients: `count`
  - What it does: Returns count of all patients in the application.
  - Justification: Allows medical staff to easily keep track of total patient count.
- Creating past appointments for patients: `appt`
  - What it does: Creates a record of a past appointment for a patient in the application, with details such as the date
  of the appointment, the doctor's diagnosis and the medication prescribed during the appointment.
  - Justification: Significant feature for the application as it allows storing a history of patient visits that can
  later be accessed by date or by patient. This improves the medical record system by integrating patient history.
  - Highlights: Adding this impacted many currently existing commands, such as `add` and side effects caused by the
  `edit` command. This was a challenging addition that required editing of over 20 files simultaneously.
- Creating upcoming appointments for patients: `edit upcoming/`
  - What it does: Schedules a future appointment for a patient.
  - Justification: Allows medical staff to keep track of when a patient is next due for a visit.

### Contributions to the UG

- Added user documentation for `count`.
- Renamed all instances of "person(s)" to "patient(s)" for consistency.
- Updated introductory line to match checkUp's identity.
- Updated command summary.
- Added user documentation for `appt`.
- Updated `edit` documentation to include the `upcoming/` tag.

### Contributions to the DG

- Added "Patient Administrator/Staff" to glossary.
- Added documentation for the Appointment feature as a whole, including:
  - A description of the feature.
  - Class and object diagrams for the feature's implementation.
  - A possible use case with outlined steps for the feature.
  - A sequence diagram of the use case with method calls shown. [TBA]

### Contributions to team-based tasks

- Set up project workspace and team repository with member management.
- Set up CodeCov.io on team repository.
- Set up CI on team repository.
- Set up CD for pull requests on team repository using Netlify.
- Created priority and type tags in team repository.
- Updated CI status and CodeCov badges in `index.md`.
- Created and managed milestones `v1.1`, `v1.2`, `v1.2b`, `v1.3` progress in the team.
- Ensured team was on schedule with frequent reminders on deadlines and pending tasks.

### Review/mentoring contributions

- Reviewed multiple PRs across the different SDLC versions.
- Planned Appointment feature and its integration with the other features in the application.

### Contributions beyond the project team

- https://github.com/nus-cs2103-AY2223S1/forum/issues/323#issuecomment-1265182783
- https://github.com/nus-cs2103-AY2223S1/forum/issues/335#issuecomment-1272447673
- https://github.com/nus-cs2103-AY2223S1/forum/issues/366
- https://github.com/nus-cs2103-AY2223S1/forum/issues/315#issuecomment-1263175854
