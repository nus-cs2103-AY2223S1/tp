---
layout: page
title: Guok Wei Jie's Project Portfolio Page
---

## Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services for patients.
It helps to manage and keep track of patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.

### Code contributed:
[Link to tP Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=guokweijie&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented:
* FindPatientCommand
  * It allows the user to filter patients by name, phone number, email, address, remarks and tags and lists them.
  * It allows the user to filter by one field or multiple fields in one command.
  * The command is case-insensitive.
  * Users can input half words, full words, special characters and numbers to filter by, according to the constraints of each field.
* FindAppointmentCommand
  * It allows the user to filter appointments by name, medical test, slot and doctor.
  * It allows the user to filter by one field or multiple fields in one command.
  * The command is case-insensitive.
  * Users can input half words, full words, special characters and/or numbers to filter by, according to the constraints of each field.
* SetPaidCommand
  * It sets the payment status of a patient's bill to "PAID".
* SetUnPaidCommand
  * It sets the payment status of a patient's bill to "UNPAID".
* Fixed bug in Email class by modifying the regex for the constraints of the email field.
* Wrote tests for FindPatientCommand, FindAppointmentCommand, FindPatientCommandParser, FindAppointmentCommandParser, SetPaidCommand, SetPaidCommandParser, SetUnPaidCommand and SetUnpaidCommandParser.

### Contributions to the User Guide:
* Added documentation and screenshots for:
  * FindPatientCommand
  * FindAppointmentCommand
  * SetPaidCommand
  * SetUnPaidCommand
  * Save the data
  * Edit the data file
* Added the __Quick Start__ guide
* Added the __Target User Group__ and __Value Proposition__ descriptions
* Added the __FAQ__ section
* Added Navigation for __Features__ list
* Modified and standardised headings, screenshot descriptions and spacing for consistency

### Contributions to the Developer Guide:
* Added the __Find Feature__ section with 3 Sequence diagrams
* Added the __Set Payment Status Feature__ section with 2 Sequence diagrams
* Contributed to the __Appendix: Effort__ section
* Added the __Non-Functional Requirements__
* Added the __Glossary__
* Added __Target User Profile, Product Scope and Value Proposition__
* Modified all the user stories and wrote use case UC-01

### Contributions to other Documentations:
* Product Ui image for landing page
* Wrote the README.md

### Contributions to the team-based tasks:
* Milestone management
* Maintained the issue tracker and helped teammates to link some PRs to issues
* Release management
* Updated user docs not specific to feature as mentioned above (Quick Start, FAQ, Glossary, Target User Group, Value Proposition, Non-Functional Requirements)
* Added developer docs not specific to feature as mentioned above (Appendix: Effort, Target User Profile, Product Scope, Value Proposition)
* Triaged and managed all 58 issues from PE Dry Run
* Debugged and fixed more than half of the bugs from PE Dry Run
* Organised team meetings and discussions
* Led the team by assigning tasks, reminding teammates to complete them and checking through their work

### Review/mentoring contributions:
* Reviewed PRs
  * [#1](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/174)
  * [#2](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/172)
  * [#3](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/158)

### Tools:
to be added soon

### Project management:
to be added soon

