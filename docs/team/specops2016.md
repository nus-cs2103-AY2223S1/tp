---
layout: page
title: Gregory Wong's Project Portfolio Page
---

## Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track and manage patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.

### Code contributed:
[Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=specops2016&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=SpecOps2016&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
### Enhancements to existing features:
* SortPatientCommand
    * It allows the user to sort patients by name, phone number, email and address.
    * It allows the user to sort patients either by ascending or descending order.
    * The command is case-insensitive.
* SortAppointmentCommand
    * It allows the user to sort appointments by name, medical test, time slot and doctor assigned.
    * It allows the user to sort appointments either by ascending or descending order.
    * The command is case-insensitive.
* SortBillCommand
    * It allows the user to sort bills by name, amount, bill date and payment status.
    * It allows the user to sort bills either by ascending or descending order.
    * The command is case-insensitive.
* UndoCommand
    * It reverts the user's last command.
* RedoCommand
    * It reverts the user's last undo.
* UndoCommandTest and UndoCommandParserTest
    * It tests the undo command. 
* RedoCommandTest and RedoCommandParserTest
    * It tests the redo command.
* SortPatientCommandTest and SortPatientCommandParserTest
    * It tests the sortpatient command.
* SortBillCommandTest and SortBillCommandParserTest
    * It tests the sortbill command. 
* SortAppointmentCommandTest and SortAppointmentCommandParserTest
    * It tests the sortappointment command. 
* AddBillCommandTest and AddBillCommandParserTest
    * It tests the addbill command. 
* Modified CommandTestUtil to include new fields for testing
   
### Contributions to the User Guide:
* Added documentation and screenshots for:
    * SortPatientCommand
    * SortAppointmentCommand
    * SortBillCommand
    * UndoCommand
    * RedoCommand
* Contributed to Quickstart guide

### Contributions to the Developer Guide:
* Added the __Undo/Redo Feature__ section with 2 Sequence diagrams and 3 State diagrams
* Added the __Sort Feature__ section with 1 Sequence diagram
* Added the __User Stories__

### Contributions to the team-based tasks:
* Closed milestones
* Released JAR files
* Updated user docs not specific to feature
* Updated developer docs not specific to feature
* Added test cases for existing features that were not assigned to me
* Added developer docs not specific to feature as mentioned above (Appendix: Effort, User Stories)

    



