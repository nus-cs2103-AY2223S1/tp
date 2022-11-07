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
* FindPatientCommand, FindAppointmentCommand, FindPatientCommandParser and FindAppointmentCommandParser
  * FindPatientCommand filters patients by name, phone number, email, address, remarks and tags
  * FindAppointmentCommand filters appointments by name, medical test, slot and doctor
  * Both commands are case-insensitive and allow the user to filter by one or multiple fields in a single command
  * Both commands are implemented using many Optional predicates and lambda functions instead of creating a new predicate class for each field as in AB3
  * The filter input is much more flexible than AB3, allowing the user to input partial words, numbers and special characters, according to the input constraints of each field
  * Learnt to use regular expressions to restrict the user input to only the appropriate characters for each field
  * Came up with methods to handle edge cases like checking for multiple occurrences of the same prefix in one command
* SetPaidCommand and SetUnpaidCommand
  * SetPaidCommand sets the payment status of a patient's bill to "PAID"
  * SetUnpaidCommand sets the payment status of a patient's bill to "UNPAID"
  * User enters index of bill on the filtered bill list to set the payment status for both commands
* Added tests to check how the commands handle normal and edge cases:
  * FindPatientCommandTest and FindPatientCommandParserTest (tested every field singly and multiple fields at once also)
  * FindAppointmentCommandTest and FindAppointmentCommandParserTest (tested every field singly and multiple fields at once also)
  * SetPaidCommandTest and SetPaidCommandParserTest
  * SetUnpaidCommandTest and SetUnpaidCommandParserTest
* Modified AB3's CommandTestUtil and ModelManagerTest due to usage of Optional predicates for the Find commands
* Fixed AB3's bug in Email class by modifying the regex for the constraints of the email field

### Contributions to the User Guide:
* Added documentation and screenshots for:
  * FindPatientCommand
  * FindAppointmentCommand
  * SetPaidCommand
  * SetUnPaidCommand
  * Save the data
  * Edit the data file
* Added the whole __Quick Start__ guide
* Added the __Target User Group__ and __Value Proposition__ descriptions
* Added the whole __FAQ__ section
* Added navigation for all features in the __Features__ list
* Modified and standardised headings, command word section, format section and screenshot descriptions for consistency

### Contributions to the Developer Guide:
* Added the __Find Feature__ section with 3 Sequence diagrams
* Added the __Set Payment Status Feature__ section with 2 Sequence diagrams
* Added the __Find feature__ section in the __Appendix: Effort__ section
* Added the __Non-Functional Requirements__ section
* Added the __Glossary__ section
* Added the __Target User, Target User Profile, Product Scope and Value Proposition__ sections
* Edited all user stories
* Added use cases UC-01 to UC-09

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
* Managed and scribed the project Google document and made team submissions like PE Dry Run bug report and app demo screenshots

### Review/mentoring contributions:
* Reviewed PRs:
  * [#1](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/174)
  * [#2](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/172)
  * [#3](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/158)
* Mentored teammates for features:
  * FindBillCommand and FindBillCommandParser (code was also referenced)
  * FindBillCommandTest and FindBillCommandParserTest (code was also referenced)
