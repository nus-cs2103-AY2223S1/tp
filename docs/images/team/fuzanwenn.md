---
layout: page
title: Fu Zanwen's Project Portfolio Page
---

## Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track and manage patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.

### Code contributed:
[Link to tP Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=fuzanwenn&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements to existing features:

#### Remark Feature

* Added class Remark to represent the remark of the patient.
* Added class RemarkCommand to represent the remark command.
* Added class RemarkCommandParser to parse the remark command.
* Added class RemarkCommandTest to test the remark command.
* Added class RemarkCommandParserTest to test the remark command parser.
* Added class RemarkTest to test the remark class.
* Modified the class Model to support remark operations on patients.
* Modified the class Storage to support storing the remark.
* Modified the edit command to support editing the remark of the patient.
* Modified the class Patient to support remark operations.
* Modified the class PatientBuilder to support remark operations.
* Modified the class TypicalPatients to support remark operations.
* Modified the class JsonAdaptedPatient to support remark operations.

#### EditPatient Feature

* Refracted EditPersonCommand to EditPatientCommand.
* Refracted EditPersonDescriptor to EditPatientDescriptor.
* Refracted EditPersonDescriptorTest to EditPatientDescriptorTest.
* Refracted EditPersonCommandParser to EditPatientCommandParser.
* Refracted EditPersonCommandParserTest to EditPatientCommandParserTest.
* Refracted EditPersonCommandTest to EditPatientCommandTest.
* Refracted EditPersonDescriptorBuilder to EditPatientDescriptorBuilder.
* Refracted EditPersonDescriptorBuilderTest to EditPatientDescriptorBuilderTest.
* Refracted Person class to Patient class.

#### Bill Feature

* Added class Bill to represent the bill of the appointment with the bill information including
  bill amount, bill date and the payment status of the bill.
* Added supportive classes Amount, BillDate and PaymentStatus.
* EditBillCommand:
    * It allows the user to edit the bill amount, bill date of an existing bill.
    * It warns the user if the input of information in the parameter is invalid.
    * It gives the user an example when user input is not valid.

### Documentation:

* Added JavaDoc to the written code to ensure understandability.

### Contributions to the User Guide:

* Added documentation and screenshots for:
  * EditPatientCommand
  * AddBillCommand
  * EditBillCommand

### Contributions to the Developer Guide:

* Added Use Case for:
  * AddPatientCommand
  * AddBillCommand
  * AddAppointmentCommand
  * EditPatientCommand
  * EditAppointmentCommand
  * EditBillCommand
  * FindPatientCommand
  * FindAppointmentCommand
  * FindBillCommand
  * UndoCommand
  * RedoCommand
  * SortPatientCommand
  * SortAppointmentCommand
  * SortBillCommand
  * DeletePatientCommand
  * DeleteAppointmentCommand
  * DeleteBillCommand
  * ListCommand
  * SetPaidCommand
  * SetUnpaidCommand
  * SelectPatientCommand
  * SelectAppointmentCommand
* Added Add feature implementation with sequence diagram.
* Added Edit feature implementation with sequence diagram.
* Added Effort for Edit feature implementation.

### Contributions to the team-based tasks:

* Tested the application and report bugs.
* Added class Bill to represent the bill of the appointment with the bill information including
  bill amount, bill date and the payment status of the bill.
* Added test cases for EditAppointmentCommandParser class.
* Added test cases for EditAppointmentDescriptorBuilder class.
* Added test cases for Bill class, Amount class, BillDate class and PaymentStatus class.
* Added test cases for JsonAdaptedBill class.
* Added test cases for NameContainsKeywordsPredicateAppointment class.
* Added test cases for DeleteAppointmentCommand class.
* Added test cases for AddBillCommandParser class.
* Added test cases for FindBillCommandParser class.
* Maintained the issue tracker.
* Released JAR file.

### Review/mentoring contributions:

* Reviewed PRs from other team members:
    * [Branch update delete patient command](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/81)
    * [Undo/Redo command](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/136)
    * [Update developer guide for SelectCommand](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/136)
    * [Add HealthContactParserTest](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/340)

### Tools:
* IntelliJ
* Gradle
* CheckStyle
* SourceTree

### Project management:
to be added soon

