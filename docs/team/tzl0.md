---
layout: page
title: Li Tianze's Project Portfolio Page
---

### Project: HealthContact

HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services for patients. It helps to manage and keep track of patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.
Given below are my contributions to the project.

* **Code contributed**:
  * [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tzl0&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Refracted `AddressBook` to `HealthContact`.
  * Refracted `Person` to `Patient`.
  * Modified the Model and Storage to support `Appointment` and `Bill`.

* **New features**:
  * **Object Entity**
    * Implemented class `Appointment` to encapsulate the reservation information including the name of the patient,
      doctor name, time slot and the medical test to take.
    * Implemented class `Bill` to encapsulate the bill of the appointment with the bill information including
      bill amount, bill date and the payment status of the bill.
  * **Commands**
    * Implemented class `CommandWord` to enable shortcuts for the long command words.
    * Implemented `AddAppointmentCommand` to add `Appointment` to the application.
    * Implemented `AddBillCommand` to add `Bill` to the application.
    * Implemented `EditAppointmentCommand` to modify the existing `Appointment` in the application.
    * Implemented `SelectPatientCommand` to show the appointments and bills of a patient.
    * Implemented `SelectAppointmentCommand` to show the bills of a patient.
  * *Added extensive tests to new features*.

* **Graphic User Interface of the product**:
  * Repositioned the command box and result display.
  * Added displayed appointment list and bill list.
  * Tile the appointment list and bill list to the right to improve user experience.

* **Documentation**:
  * Added JavaDoc to the written code to ensure understandability.

* **Contributions to the User Guide**:
  * Created the frame of the User Guide.
  * Written the add commands and select commands in the User Guide

* **Contributions to the Developer Guide**:
  * Updated existing class diagrams including `Model`, `Ui` and `Storage`.
  * Created sequence diagrams for add, edit and select features.
  * Added Command Shortcut Feature.

* **Contributions to the team-based tasks**:
  * Mock Ui image for the product.
  * Ui image for the landing product.
  * Maintained the product website.

* **Review/mentoring contributions**:
  * Mentored the implementation of find commands to support finding by multiple fields.
  * Mentored the implementation of `EditBillCommand`.
  * Mentored the implementation of delete commands to support the deletion of appointments
    and bills when deleting a patient.


* **Reviewed Pull Requests**:
  * [Improve Find Command](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/120)
  * [Add EditAppointmentCommandParser Tests](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/82)
  * [Tests for delete appointment](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/96)

* **Tools**:
  * IntelliJ
  * Gradle
  * PlantUML
  * SourceTree
  * Checkstyle
