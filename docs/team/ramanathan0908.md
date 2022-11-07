---
layout: page
title: Ramanathan Kumarappan's Project Portfolio Page
---

## Project: UniNurse

[UniNurse](https://ay2223s1-cs2103t-t12-4.github.io/tp/) is a desktop application used for managing patient contact details and tasks.
It is targeted at private nurses to help them manage their patients in a more organized and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

## Contributions to project

### Code contributed
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ramanathan0908&breakdown=true)
### New feature: Added the ability to have recurring tasks [PR #252](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/252).
* What it does: Recurring tasks can be specified with a recurrence, then it would automatically generate the next task once the upcoming task has passed its task date
* Justification: Tasks that are recurring can be common for nurses, and it can be tedious for them to manually generate them by adding a new one frequently. Recurring tasks removes the tedious aspect and makes it easier for them
* Highlights: This feature is essential for nurses as they often have tasks that they have to perform every certain interval. This feature allows them to never forget the task.
### New feature: Added the ability to view Tasks for a particular day [PR #207](https://github.com/AY2223S1-CS2103T-T12-4/tp/pull/207).
* What it does: Allows nurses to check what Tasks they have on a particular date.
* Justification: Private nurses often need to have a overarching view of their duties in a time span so that they can plan and better organise their time.
* Highlights: This feature is essential for nurses to plan their tasks.
### Enhancements to existing features
Added date and time properties to tasks.
* Tasks can have date and time associated with them, making it easier for the nurses to know when they should be performed.
* Time related commands can be implemented, so nurses can view tasks or patients for a particular day.
### Contributions to User Guide
* Added documentation for the `addPatient` feature
* Added documentation for the `deletePatient` feature
* Added documentation for the `Adding a task` feature
* Added documentation for the `Editing a task` feature
* Added documentation for t
### Contributions to Developer Guide
* Added the implementation details along with the sequence diagram viewing tasks on a particular day feature
* Added implementation details and class diagram Tasks component under model