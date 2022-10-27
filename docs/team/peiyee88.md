---
layout: page
title: Pei Yee's Project Portfolio Page
---
# Project: checkUp

## Overview

checkUp is a desktop address book application used for managing patients' details efficiently. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Summary of Contributions

### Code contributed
RepoSense: [link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=peiyee88&breakdown=true)

### Enhancements implemented

- Retrieve patient's next-of-kin data: `get /nok`
    - What it does: Returns that particular patient's next-of-kin data.
    - Justification: Allows medical staff to easily contact the patient's next-of-kin when needed.
- Retrieve a list of patients within a hospital wing: `get /hw`
    - What it does: Returns all the patients details staying within a hospital wing.
    - Justification: Allow medical staff to easily know that whether a particular hospital wing is oversubscribed.
    - Also, it allows nurses to attend to the patients faster.
- Retrieve a list of appointments by date: `get /appton`
    - What it does: Returns a list of patients that has appointment scheduled on the particular date.
    - Justification: Allows medical staff to keep track of all the appointments.

### Contributions to the UG

- Create skeletal version of user guide.
- Added user documentation for `get /nok`, 
- Added user documentation for `get /appton`
- Added user documentation for `get /hw`
- Updated command summary.

### Contributions to the DG

- Added documentation for the following features `get /nok` `get /hw` `get /appton` which includ:
    - A description of the feature.
    - Class and object diagrams for the feature's implementation. [TBA]
    - A possible use case with outlined steps for the feature. [TBA]
    - A sequence diagram of the use case with method calls shown. 

### Contributions to team-based tasks

- Create skeletal version of documentation.
- Updated CI status and CodeCov badges in `index.md`.
- Managed milestones `v1.1`, `v1.2`, `v1.2b`, `v1.3` progress in the team.
- Ensured team was on schedule with frequent reminders on deadlines and pending tasks.

### Review/mentoring contributions

- Reviewed multiple PRs across the different SDLC versions.

### Contributions beyond the project team
