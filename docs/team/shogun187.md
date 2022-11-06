---
layout: page
title: Shaugn Tan Sean Hon's Project Portfolio Page
---

## Project: UniNurse

UniNurse is a desktop application used for managing patient contact details and tasks. The user interacts with it using
a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

## Contributions to project

### Code contributed
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=shogun187&breakdown=true)

### New feature: added the ability to...

Added the ability to ...
* What it does:
* Justification:
* Highlights:
* Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

Added an output panel to the UI
* What it does: Displays important information about patient details, tasks, and schedules for the day
* Justification: Provides more specific information for nurses when needed


### Enhancements to existing features

Added the ability to display patients added/edited/deleted in the output panel 
* What it does: Shows the patient card of the patient added/edited/deleted
* Justification: The existing application only displayed the patient's details in the result box which is text based
* Highlights: patientOfInterest attribute added to Model to contain the patient added/deleted/edited and subsequently used by the output panel

Added the ability to track patients added and deleted during an undo/redo command
* What it does: Show the patients who were added and deleted during an undo/redo command 
* Justification: The existing undo/redo operation only contained the different states of the UninurseBook, but does not track the specific patients involved
* Highlights: PatientListTracker class is used to track the patients added and deleted

### Contributions to User Guide
* Added documentation for the `editPatient` feature [\#50]()
* Added documentation for the `findPatient` feature [\#50]()

### Contributions to Developer Guide
* Added documentation and class diagrams for UI component
* Added implementation details for displaying added/edited/deleted patients

### Contributions to team-based tasks

### Contributions beyond project team
