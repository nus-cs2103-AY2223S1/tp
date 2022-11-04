---
layout: page
title: Li Tianze's Project Portfolio Page
---

### Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track and manage patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.

### Code contributed:
[Link to tP Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tzl0&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements to existing features:

#### Patient Feature

* Refracted existing class Person to Patient.
* Modified the class Model to support select operations on patients.

* SelectPatientCommand
    * It allows the user to select a patient and shows the corresponding appointments and bills
      to the patient in the appointment list and bill list.

#### Appointment Feature

* Added class Appointment to represent the reservation information including the name of the patient,
  doctor name, time slot and the medical test to take.

* Modified the class Model to support add, edit, delete, find, select and sort operations on appointments.

* Modified the class Storage to support storing the appointments.

* AddAppointmentCommand
    * It allows the user to add an appointment with given information including the name of the patient,
      the doctor's name, the time slot and the medical test to take.
    * It warns the user if the input of information in the parameters is invalid.
    * It checks if there is any identical appointment in the HealthContact.
    * It gives the user an example when user input is not valid.

* EditAppointmentCommand
    * It allows the user to edit an existing appointment with updated information.
    * It allows the user to edit the name of the patient to which the appointment is attached.
    * It checks if the new patient in the user input exists.
    * It allows the user to edit the time slot, doctor name and medical test of an existing appointment.
    * It warns the user if the input of information in the parameter is invalid.
    * It gives the user an example when user input is not valid.

* SelectAppointmentCommand
    * It allows the user to select an appointment and shows the corresponding bills to the patient in the bill list.

#### Bill Feature

* Added class Bill to represent the bill of the appointment with the bill information including
  bill amount, bill date and the payment status of the bill.

* Modified the class Model to support addition, edition, deletion, find, selection and sort operations on appointments.

* Modified the class Storage to support storing the bills.

* AddBillCommand
    * It allows the user to add a bill to an existing appointment with the given information of the
      bill including bill amount, bill date and payment status.
    * It checks if there is any bill attached to the same appointment.
    * It warns the user if the input of information in the parameters is invalid.
    * It checks if there is any identical bill in the HealthContact.
    * It gives the user an example when user input is not valid.

#### Graphic User Interface of the product
* Repositioned the command box and result display.
* Added displayed appointment list and bill list.
* Tile the appointment list and bill list to the right to improve user experience.

### Documentation:
* Added JavaDoc to the written code to ensure understandability.

### Contributions to the User Guide:

* Created the frame of the User Guide.
* Written the add commands and select commands in the User Guide

### Contributions to the Developer Guide:
to be added soon

### Contributions to the team-based tasks:

* Mock Ui image for the product.
* Ui image for the landing product.
* Maintained the product website.

### Review/mentoring contributions:

* Mentored the implementation of find commands to support finding by multiple fields.

* Mentored the implementation of EditBillCommand.

* Mentored the implementation of DeleteCommands to support the deletion of appointments
  and bills when deleting a patient.

* Reviewed Pull Requests:
    * [Improve Find Command](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/120)
    * [Add EditAppointmentCommandParser Tests](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/82)
    * [Tests for delete appointment](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/96)

### Contributions beyond the project team:
to be added soon

### Tools:
* IntelliJ
* Gradle

### Project management:
to be added soon
