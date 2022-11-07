---
layout: page
title: Caden's Project Portfolio Page
---

### Project: Teacher's Pet

Teacher’s Pet is a desktop application for managing contacts of students and classes, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Teacher’s Pet can get your contact and class management tasks done faster than traditional GUI apps.

Teacher’s Pet allows you to manage your schedule and keep track of your students. The app stores basic information about your students, such as Name, Contact Number, Class Date and more.

Given below are my contributions to the project.

* **New Feature**: Added the `mark` command
  * What it does: Allows the tutor to mark a student as present for the class.
  * Justification: Enables the tutor to keep track of the schedule.
  * Highlights: Automatically increases the `Money Owed` of the student by the predefined `Rates per Class`.  Display the cross symbol in the student's box of the schedule list.
  * Pull Requests: [#160](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/160)


* **New Feature**: Added the `pay` command
  * What it does: Allows the tutor to indicate that a student has paid a certain amount of money.
  * Justification: Enables the tutor to keep track of how much money a student owes or paid.
  * Highlights: Automatically decreases the `Money Owed` of the student by the paid amount, while increasing the `Money Paid`.
  * Pull Requests: [#172](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/172)


* **New Feature**: Added the Statistics Window
  * What it does: Track the tutor's statistics.
  * Justification: Enables the tutor to keep track of how much money he/she had earned, and the number of students he/she has.
  * Highlights: Automatically update the statistics in the top right window.
  * Pull Requests: [#67](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/67), [#95](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/95)


* **New Feature**: Added a notification feature for students who owe money
  * What it does: Highlights the student's name in red and displays the amount owed, if the student owes money.
  * Justification: Notifies the tutor who to collect money from in the day.
  * Pull Requests: [#146](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/146)


* **Enhancement to existing features** -`Find` command:
  * What it does: `find` command now allows the tutor to search for details of the students based on their attributes (`Name`, `Phone`, `Next-of-Kin Phone`, `Email`, `Address`, `Class Date`, `Tags`)
  * Justification: As a `Person` has other attributes other than `Name`, the tutor might want to search by other attributes as well.
  * Areas contributed:
    * Constructed the skeletal code for group members to implement other attribute searches.
    * Find-by-name: A search system which allows the tutor to search up details of the student by his/her name.
    * Find-by-phone: A search system which allows the tutor to search up details of the student by his/her phone number.


* **Code contributed**: ~2.2kLoC. All my code contribution can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cadencjk&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=cadencjk&tabRepo=AY2223S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).


* **Project management and team based tasks contribution**:
    * Managed [v1.2.1 release](https://github.com/AY2223S1-CS2103T-T09-4/tp/releases/tag/v1.2.1).
    * Created product demonstration video.
    * Documented [v1.2 Features Demonstration](https://docs.google.com/document/d/18XgQeugctKcNy1_1Fay5zRtmfvYsSSdNzT-hTWjukho/edit#heading=h.mzftiz8issv8).
    

* **Documentation**:
    * User Guide:
        * Certain parts of the [user guide](https://ay2223s1-cs2103t-t09-4.github.io/tp/UserGuide.html) were written by me.
        * Added documentation for the sections `mark`, `pay`, `Find by Name`, `Find by Student's Contact Number` and `Prefix Summary`.
    * Developer Guide:
        * Certain parts of the [developer guide](https://ay2223s1-cs2103t-t09-4.github.io/tp/DeveloperGuide.html) were written by me.
        * Added documentation for the sections `Statistics Display Feature`, `Mark Student Feature` and `Use case: Mark student as present for class`.


* **Community**:
    * Reported bugs and provided suggestions for other team, which can be found [here](https://github.com/cadencjk/ped/issues).


* **Tools**:
    * IntelliJ
    * Sourcetree
    * Github
