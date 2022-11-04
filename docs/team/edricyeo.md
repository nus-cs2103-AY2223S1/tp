---
layout: page
title: Edric Yeo's Project Portfolio Page
---

### Project: Friendnancial
**Overview:**
Friendnancial is a desktop contact management application used by
financial advisors to manage their clients and contacts. The user
interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java and has about 10 kLoC.


**Summary of Contributions**:

* **Code contributed**: https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=edricyeo&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other


* **Enhancements implemented**:
    * **Creating Reminders**
        * Implemented the `remind` command to allow users to create a reminder, tagged to a person.
        * Added functionality to the `Person` class to store a new `Reminder` field.
        * Modified the UI to display information regarding the reminders.
    * **Deleting Reminders**
        * Implemented the `deleteR` command to allow users to delete reminders, given the index of the reminder.
    * **Find**
        * Added functionality for the `find` command to find contacts based off name, phone, email, birthday, address fields.
        * Refactored the parsing of the `find` command to accept prefixes for the different field to find by. This was difficult because there was a lot of code and test cases that had to be refactored after the parsing of the `find` command was changed.
        * Implemented input checking and error checking for the `find` command. A different parsing of the `find` command created bugs when the find command was parsed. Therefore, I had to use assertions and exceptions to ensure the `find` command accepted valid user inputs.


* **Contributions to the UG**:
    * Wrote "Locating persons by keyword" section
    * Wrote "Adding a reminder" section
    * Wrote "Deleting a reminder" section


* **Contributions to the DG**:
    * Wrote "Remind feature" section, explaining how the `remind` command was implemented.
    * Wrote "Delete Reminder feature" section, explaining how the `deleteR` command was implemented.
    * Created sequence diagrams for the `remind` and `deleteR` command using plantUML


* **Contributions to team-based tasks**:
    * Create and delegate tasks to teammates, using github Issues.
    * Refactored a lot of AB3 references in the user guide and developer guide


* **Review/mentoring contributes**:
    * Reviewed PR's with suggestions to improve OOP [#98](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/98)
    * Reviewed PR's with suggestions to delete redundant code [#63](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/63)
    * Reviewed PR's that were good to merge
    * Merged PR's that have been reviewed by other teammates
    * Reported bugs and provided suggestions on how to fix them


* **Contributions beyond the project team:** `to be added soon`


