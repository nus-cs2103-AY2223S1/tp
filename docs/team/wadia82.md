---
layout: page
title: Wadia Ganim's Project Portfolio Page
---

## Project: HealthContact
**Overview**: <br>
HealthContact is a desktop application built for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track and manage patient data, appointments and bills, especially for clinics who do not have apps to expedite the process.

Given below are my contributions to the project.


### Code contributed:
[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wadia82&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=guokweijie&tabRepo=AY2223S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


### Enhancements to existing features:
1. Added the ability to delete all appointments and bills of the patient when a patient is deleted (Delete Patient Command). 
   Added the ability to delete individual appointments(Delete Appointment Command) and bills(Delete Bill Command).

    - What it does: Lets the user delete patient, appointment, or bill identified by their index in the currently displayed list.

    - Justification: Earlier deleting a contact from the address book would just delete the contact details. This is not intuitive or user-friendly as they would then have to proceed to delete the appointments and the bills of the patient.
Additionally, there was no support for any operations for appointments and bills, so those had to be explicitly added.


2. Added the ability to find bills by name, date, amount, and whether the bills have been paid or not(payment status).

    - What is does: It allows the user to find a certain bill or bills matching the entered keyword(s). The user can find bill(s) by one or multiple fields in a single command. 

    - Justification: Earlier the address book allowed the user to look for a contact only by name. Upon adding Bills to HealthContact and bills having multiple fields,
it only makes sense to allow the user to find a bill by whatever criteria they wish to. 
   

3. Test cases for Delete
    
    - Delete Patient command and command parser test

    - Delete Appointment command and command parser test

    - Delete Bill command and command parser test


4. Test cases for Find Bill 

    - Find bill command and parser test


5. Test cases for Edit Appointment and Bill

    - Edit appointment command test

    - Edit bill command test


### Contributions to the User Guide:
* Added documentation for features delete patient, delete appointment, delete bill and find bill [(#177)](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/177)


### Contributions to the Developer Guide:
* Added documentation for delete feature 
* Added manual testing instructions for all commands [(#324)](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/324) [(#334)](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/334)


### Contributions to the team-based tasks:
* Drafted the team's AboutUs.md file [(#43)](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/43/files)


### Review/mentoring contributions:
* Added constructive comments on PR [#357](https://github.com/AY2223S1-CS2103T-W08-1/tp/pull/357)
* Looked through and approved [PRs](https://github.com/AY2223S1-CS2103T-W08-1/tp/pulls?q=is%3Apr+reviewed-by%3Awadia82)
