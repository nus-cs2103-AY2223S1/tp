---
layout: page
title: Zhehao's Project Portfolio Page
---

##### Project: CS2103 tP
__Overview:__ checkUp is a desktop patient medical record management system. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 15 kLoC.

###### Summary of contributions
__Code Contributed:__ All my code contribution can be viewed here:
[RepoSense Report](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=charles1026&breakdown=true)

###### Enhancements implemented
- Updating `Person` class to support patient specific fields
  - Description: `Person` now configured to support `NextOfKin`, `PatientType`, `HospitalWing`, 
    `FloorNumber`, `WardNumber` and `Medication` fields
  - Justification: Allows medical staff to store personal information needed for patients.
- Creating new patient profiles: `add`
  - Description: Adds new patients with their all their personal particulars.
  - Justification: Allows medical staff to store new patients in the app.
- Editing current patient profiles: `edit`
  - Description: Edits the personal particulars of current patients in the app via the index of the patient.
  - Justification: Allows medical staff to update existing patient particulars in the app.
- Creating the Patient Details Panel: `PersonViewPanel`
  - Description: A new Ui component which displays all of a patient's details in one place. It defaults to the first 
    patient in the database and the most recent added / edited patient.
  - Justification: Allows medical staff to quickly view and access a patient's information without having to type in 
    multiple commands.
- Changing the patient viewed in the Patient Details Panel: `view`
  - Description: Manually changes the patient being displayed via the index via the index of the patient.
  - Justification: Allows medical staff to quickly view and access all their patients' information.
- Clicking on Patient Details Panel to edit the selected patient particulars: `PersonViewPanel`
  - Description: Clicking on the fields in the Patient Details Panel brings up the relevant edit command in the 
    Command Box.
  - Justification: Allows medical staff to have quickly edit a patient's particulars without having to type in the whole
    `edit` command.
- Creating upcoming appointments for new patients: `add ua/`
  - What it does: Schedules a future appointment for a patient directly from the `add` command.
  - Justification: Allows medical staff to quickly create an upcoming appointment for a new patient.
- Deleting past appointments: `delappt`
  - What it does: Deletes the most recent past appointment for the patient selected via his/her index. 
  - Justification: Allows medical staff to change the past appointment of a patient if it is documented incorrectly or 
    if new information needs to be added.
- Creating keyboard shortcuts: `CommandBox` and `CommandHistory`
  - What it does: `UP` and `DOWN` arrow keys to navigate previous commands, `Ctrl + Shift + C` to clear the command text.
  - Justification: Allows medical staff to navigate previous commands and clear input text quickly.
- Consulting a patient: `consult`
  - What it does: Create a past appointment for the current date and clear the upcoming appointment for the current 
    date(if present). The patient is selected by his/her index.
  - Justification: Allows doctors to quickly document their consultation with a patient without having to enter 
    multiple commands.
  
###### Other code contributions
- Fixed Patient Details Panel display bugs; Fixed `UpcomingAppointment` and `PastAppointment` date parsing bugs; 
  `Person` duplicate check not case-sensitive bug; And, fixed duplicate `PastAppointment` not caught bug.
- Added unit tests for all the enhancements in the section above.

###### Contributions to the UG
- Updated documentation for [`add`](https://checkup.netlify.app/userguide#adding-a-patient-add) 
  and [`edit`](https://checkup.netlify.app/userguide#editing-a-patient-edit) commands to reflect changes made to them.
- Documented [`view`](https://checkup.netlify.app/userguide#viewing-a-patient-view),
  [`delappt`](https://checkup.netlify.app/userguide#deleting-past-appointment-for-patient-delappt) 
  and [`consult`](https://checkup.netlify.app/userguide#consulting-a-patient-consult) 
  commands and [Patient Details Panel Clickability](https://checkup.netlify.app/userguide#person-details-panel) and 
  [Keyboard Shortcuts](https://checkup.netlify.app/userguide#keyboard-shortcuts) enhancements.
- Created example and feedback images for all the commands to improve user readability.
- Reorganised the whole [Features](https://checkup.netlify.app/userguide#features) section to a 3 segment structure to 
  group related commands together and improve user readability
- Added all hyperlinks across the whole user guide to improve navigability.

###### Contributions to the DG
- Updated the [`Ui`](https://checkup.netlify.app/developerguide#ui-component)
  component to reflect our changes and created a new UML Class Diagram for it.
- Wrote the documentation for the [`add`](https://checkup.netlify.app/developerguide#add-command),
  [`edit`](https://checkup.netlify.app/developerguide#edit-command),
  [`delappt`](https://checkup.netlify.app/developerguide#delappt-delete-appointment-command),
  [`consult`](https://checkup.netlify.app/developerguide#consult-command) 
  and [`view`](https://checkup.netlify.app/developerguide#view-command) commands, 
  as well as for the [Patient Details Panel Clickability](https://deploy-preview-219--checkup.netlify.app/developerguide#patient-details-panel)
  and [Keyboard Shortcuts](https://checkup.netlify.app/developerguide#keyboard-shortcuts). Included for each command:
  - Description of the purpose of the feature and how it works.
  - UML Sequence Diagram to illustrate the flow of the feature.
- Added user stories, use cases and manual testing for all the above-mentioned features.

###### Contributions to team-based tasks
- Ensured team was on schedule to meet deadlines and milestone submissions.
- Checked for code quality and adherence to coding standards.

__Review/mentoring contributions:__ Reviewed [PRs](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  for team members through the SDLC versions.
