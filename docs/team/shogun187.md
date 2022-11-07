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

### New feature: Output Panel  

* What it does: Displays important information about patient details, tasks, and schedules for the day
* Justification: Provides more specific information for nurses when needed


### Enhancements to existing features

Added Command Type enumeration to Logic component to cover all commands.

* What it does: Stores the command type in each command so that MainWindow can determine the command entered and update itself accordingly.
* Justification: The exisiting application only displayed the patient list, hence there was no need for the enhancement. However, we needed a way for the output panel to update itself accordingly based on the command executed.  

Added the ability to display patients added/edited/deleted in the output panel 
* What it does: Shows the patient card of the patient added/edited/deleted
* Justification: The existing application only displayed the patient's details in the result box which is text based
* Highlights: patientOfInterest attribute added to Model to contain the patient added/deleted/edited and subsequently used by the output panel

Added the ability to track patients added and deleted during an undo/redo command
* What it does: Show the patients who were added and deleted during an undo/redo command 
* Justification: The existing undo/redo operation only contained the different states of the UninurseBook, but does not track the specific patients involved
* Highlights: PersonListTracker class is used to track the patients added and deleted

### Contributions to User Guide
* Added documentation for email and task parameters
* Added documentation for the `editPatient` feature
* Added documentation for the `findPatient` feature 

### Contributions to Developer Guide
* Added documentation and class diagrams for UI component
* Added implementation details for displaying added/edited/deleted patients
* Added instructions for manual testing
* Added the following use cases: UC04, UC05
* Added acknowledgement for font usage

### Contributions to team-based tasks

### Contributions beyond project team
