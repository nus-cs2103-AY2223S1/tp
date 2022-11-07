---
layout: page
title: Pei Yee's Project Portfolio Page
---
# Project: checkUp

## Overview

checkUp is a desktop patient medical record management system. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

## Summary of Contributions

### Code contributed
RepoSense: [link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=peiyee88&breakdown=true)
Personal merged PRs: [link](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?q=is%3Apr+author%3Apeiyee88+is%3Aclosed+)

### Enhancements implemented

- Retrieve patient's contact card: `get /nok`
    - What it does: Displays the particular patient's next-of-kin data in a simplified contact card.
    - Justification: Allows medical staff to easily contact the patient's next-of-kin when needed.
- Filter list of patients by Hospital Wing: `get /hw`
    - What it does: Displays filtered list of patients in the query hospital wing.
    - Justification: Doctors can easily locate a patient by a given hospital wing.
- Filter list of patients by Appointment Date: `get /appton`
    - What it does: Displays filtered list of patients that has appointment scheduled on the particular date.
    - Justification: Allows medical staff to keep track of all the appointments.

### Other code contributions

* Make the user interface more appealing by customizing the theme colors of the app.
* Implement contact card (a simplified version of person card) to the GUI so that all information besides
next-of-kin data is abstracted away. 

### Contributions to the UG

- Created skeletal version of user guide.
- Added user documentation for the features `get /nok`, `get /hw` and `get /appton`.
- Reword the original UG so that it is more user-friendly.
- Added a troubleshooting section so that users can refer to whenever they encounter issues using our app.

### Contributions to the DG

* Added implementation details of the `get` feature, including detailed explanations of each prefixed command of:
  * `get /appton`
  * `get /hw`
  * `get /nok`

* Include detailed sequence diagram of the `Storage` component.

### Contributions to team-based tasks

- Created skeletal version of documentation.
- Managed milestones `v1.1`, `v1.2`, `v1.2b`, `v1.3` progress in the team.
- Enforced good quality code by reviewing PRs containing major code changes.
- Provided suggestions for ideas to improve the product.
- Ensured team was on schedule with frequent reminders on deadlines and pending tasks.
- In charge of scheduling team tasks.

### Review/mentoring contributions

* Actively reviewed [PRs](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?page=1&q=is%3Apr+reviewed-by%3Apeiyee88) from other team members.
* Provided insightful comments and suggestions to improve the quality of the code:
  * PR [#210](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/210)
  * PR [#194](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/194)
  * PR [#129](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/129)
  * PR [#79](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/79)
  * PR [#50](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/50)
  

### Contributions beyond the project team
* Actively participated in [forum discussions](https://github.com/nus-cs2103-AY2223S1/forum/issues?q=is%3Aissue+commenter%3Apeiyee88):
  * Forum Issue [#315](https://github.com/nus-cs2103-AY2223S1/forum/issues/315)
