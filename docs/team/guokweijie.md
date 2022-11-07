---
layout: page
title: Guok Wei Jie's Project Portfolio Page
---

## Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services for patients.
It helps to manage and keep track of patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.

* __Code contributed__:
[Link to tP Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=guokweijie&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* __Enhancements implemented__:
  * Implemented FindPatientCommand, FindAppointmentCommand, FindPatientCommandParser and FindAppointmentCommandParser
    * FindPatientCommand filters patients by name, address, email, phone, remarks and tags
    * FindAppointmentCommand filters appointments by name, medical test, slot and doctor
    * Both commands are case-insensitive and allow the user to filter by one or multiple fields in a single command
    * Both commands are implemented using many Optional predicates and lambda functions
    * Filter input is much more flexible than AB3, allowing the user to input partial words, numbers and special characters
    * Learnt to use regular expressions to restrict the user input to only the appropriate characters for each field
    * Came up with methods to handle edge cases like checking for multiple occurrences of the same prefix in one command
  * Implemented SetPaidCommand and SetUnpaidCommand to set bills' payment statuses
    * User enters index of bill to set the payment status to paid or unpaid respectively
  * Added tests to check how the commands handle normal and edge cases:
    * FindPatientCommandTest, FindPatientCommandParserTest, FindAppointmentCommandTest and FindAppointmentCommandParserTest
    * SetPaidCommandTest, SetPaidCommandParserTest, SetUnpaidCommandTest and SetUnpaidCommandParserTest
  * Modified AB3's CommandTestUtil and ModelManagerTest due to usage of Optional predicates for the Find commands
  * Fixed AB3's bug in Email class by modifying the regex for the constraints of the email field <br>


* __Contributions to the User Guide__:
  * Added documentation and screenshots for __FindPatientCommand__, __FindAppointmentCommand__, __SetPaidCommand__, __SetUnPaidCommand__, __Save the data__ and __Edit the data file__
  * Added the __Quick Start__ guide and __FAQ__ section
  * Added the __Target User Group__ and __Value Proposition__ descriptions
  * Added navigation for all features in the __Features__ list
  * Modified and standardised headings, command word section, format section and screenshot descriptions for consistency


* __Contributions to the Developer Guide__:
  * Added the __Find Feature__ section and __Set Payment Status Feature__ section
  * Added the __Find feature__ section in the __Appendix: Effort__ section
  * Added the __Non-Functional Requirements__ section and the __Glossary__ section
  * Added the __Target User, Target User Profile, Product Scope and Value Proposition__ sections
  * Edited all user stories and added use cases UC-01 to UC-09

* __Contributions to the team-based tasks__:
  * Milestone and Release management
  * Maintained the issue tracker and helped teammates to link some PRs to issues
  * Triaged and managed all 58 issues from PE Dry Run
  * Debugged and fixed more than half of the bugs from PE Dry Run
  * Organised team meetings and led discussions on the project
  * Led the team by assigning tasks, reminding teammates to complete them and checking through their work
  * Managed and scribed the project Google document and made team submissions like demo screenshots and PE Dry Run bug report


* __Review/mentoring contributions__:
  * Mentored teammates for implementation and tests:
    * FindBillCommand, FindBillCommandParser
    * FindBillCommandTest, FindBillCommandParserTest
  * Some PRs I reviewed:
    * [Find bill optimised](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/158)
    * [v1.3 Bug fix](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/167)
    * [Add SelectAppointmentCommand](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/123)
    * [Fix incorrect Doctor name constraint](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/257)
