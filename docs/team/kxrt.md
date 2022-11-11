---
layout: page 
title: Kartikeya's Project Portfolio Page
---

###### Overview

checkUp is a desktop patient medical record management system. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 15 kLoC.

I was the designated team lead for this project group. Given below are my contributions to checkUp.

###### Code contributed

RepoSense: [link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kxrt&breakdown=true) <br>
Commit History: [link](https://github.com/AY2223S1-CS2103T-W16-3/tp/commits?author=kxrt) <br>
Pull Requests: [link](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?q=is%3Apr+author%3Akxrt) <br>
Issues: [link](https://github.com/AY2223S1-CS2103T-W16-3/tp/issues?q=is%3Aissue+assignee%3Akxrt)

###### Features implemented

- Obtaining count of all patients and medication: `count`
    - What it does: Returns count of all patients in the application, along with prescription counts for long-term
      medication.
    - Justification: Allows medical staff to easily keep track of total patient count.
- Appointments feature
  - Created the main classes that encapsulate the appointments feature, including both past and upcoming appointments.
  - Creating past appointments for patients: `appt`
      - What it does: Creates a record of a past appointment for a patient in the application, with details such as the
        date of the appointment, the doctor's diagnosis and the medication prescribed during the appointment.
      - Justification: Significant feature for the application as it allows storing a history of patient visits that can
        later be accessed by date or by patient. This improves the medical record system by integrating patient history.
      - Highlights: This was a major feature added to the application during the project, as it laid the basis for future
        enhancements. Adding this impacted many currently existing commands, such as `add` and side effects caused by the
        `edit` command. This was a challenging addition that required editing of over 20 files simultaneously.
  - Creating upcoming appointments for patients: `edit ua/`
      - What it does: Schedules a future appointment for a patient.
      - Justification: Allows medical staff to keep track of when a patient is next due for a visit.

###### Other code contributions

- UI changes to PersonViewPanel to beautify it from a rudimentary text layout.
- Added unit tests for `CountCommand` and Appointment-based classes, increasing coverage by 3% across over 600 lines of
  JUnit test code.

###### Contributions to the UG

- Documented `count`, `appt`, `view`, `delappt` commands.
- Updated documentation for `edit` and `add` commands to reflect changes made to them.
- Updated command summary.
- Rewrote introduction and added a section on the purpose of the application.
- Created UG [banner image](../images/ug-images/editCommand/checkUp_banner.png).
- Refactored parameters for commands into tables for better readability.
- Defined `+`, `-` and `*` symbols to indicate the type of command parameter.
- Added relevant user stories for the `Appointment` and `CountCommand` features.

###### Contributions to the DG

- Added "Patient Administrator/Staff" to glossary.
- Added documentation for the Appointment feature as a whole, including:
    - A description of the feature.
    - Class and object diagrams for the feature's implementation.
    - A sequence diagram of the use case with method calls shown.
- Added documentation for the CountCommand feature, including:
    - A description of the feature.
    - A sequence diagram of the use case with method calls shown.
- Updated documentation for `Model` component, including creating new class diagrams.
- Added use cases for `count`, `appt` and `edit ua/` commands.
- Created and added class diagram for the `Person` class.

###### Contributions to team-based tasks

- Set up project workspace and team repository with member management.
- Set up code coverage, CI, CD on team repository.
- Set up CD for pull requests on team repository using Netlify.
- Created priority and type tags in team repository.
- Organised regular team meetings to discuss progress and issues, and to establish the direction of the project.
- Scheduled tasks for team members and ensured they were completed on time.
- Ensured team was on schedule with frequent reminders on deadlines and pending tasks.
- Checked for code quality and adherence to coding standards.
- Created application [logo](../images/checkUp_512.png).
- Defined and added non-functional requirements.
- Created and managed milestones `v1.1`, `v1.2`, `v1.2b`, `v1.3`, `v1.3b`, `v1.4` progress in the team.
- Reviewed bug tester contributions through the quality of bugs reported (after PE-D).

###### Review/mentoring contributions

- Reviewed [47 pull requests](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?page=1&q=is%3Apr+reviewed-by%3Akxrt)
  across the different SDLC versions.
- Planned Appointment feature and its integration with the other features in the application.

###### Contributions beyond project team

- Forum:
  [#34](https://github.com/nus-cs2103-AY2223S1/forum/issues/34#issuecomment-1221230839)
  [#38](https://github.com/nus-cs2103-AY2223S1/forum/issues/38#issuecomment-1221234801)
  [#48](https://github.com/nus-cs2103-AY2223S1/forum/issues/48#issuecomment-1222054272)
  [#105](https://github.com/nus-cs2103-AY2223S1/forum/issues/105#issuecomment-1231079132)
  [#118](https://github.com/nus-cs2103-AY2223S1/forum/issues/118#issuecomment-1233689433)
  [#119](https://github.com/nus-cs2103-AY2223S1/forum/issues/119#issuecomment-1233675121)
  [#141](https://github.com/nus-cs2103-AY2223S1/forum/issues/141#issuecomment-1236156886)
  [#165](https://github.com/nus-cs2103-AY2223S1/forum/issues/165#issuecomment-1242684073)
  [#315](https://github.com/nus-cs2103-AY2223S1/forum/issues/315#issuecomment-1263175854)
  [#323](https://github.com/nus-cs2103-AY2223S1/forum/issues/323#issuecomment-1265182783)
  [#335](https://github.com/nus-cs2103-AY2223S1/forum/issues/335#issuecomment-1272447673)
  [#366](https://github.com/nus-cs2103-AY2223S1/forum/issues/366)
  [#367](https://github.com/nus-cs2103-AY2223S1/forum/issues/367#issuecomment-1287829837)
  [#370](https://github.com/nus-cs2103-AY2223S1/forum/issues/370#issuecomment-1288053235)
- Reviewed product of team F11-3 and
  reported [relevant bugs](https://github.com/AY2223S1-CS2103T-F11-3/tp/issues?q=is%3Aissue+kxrt).

