---
layout: page
title: Developer Guide
---

## Table of contents
* [Target User Profile](#target-user-profile)
* [Value Proposition](#value-proposition)
* [User Stories](#user-stories)
* [Use Case](#use-case)
    * [Use case: **Delete a student**](#use-case-delete-a-student)
    * [Use case: **Edit a student contact detail**](#use-case-edit-a-student-contact-detail)
    * [Use case: **Find student contact details**](#use-case-find-student-contact-details)
    * [Use case: **Mark student as present for class**](#use-case-mark-student-as-present-for-class)
    * [Use case: **Allocate a slot for future class**](#use-case-allocate-a-slot-for-future-class)
* [Non-Functional Requirement](#non-functional-requirement)
* [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------
## Target User Profile

- a private tutor who teaches 1-1 classes and needs to manage the students’ details
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

## Value Proposition

Manage contacts and schedule of students faster than a typical mouse/GUI driven app

## User Stories

| S/N | As a/an ...                                                                  | I can ...                                                                           | So that...                                                              | Priority   |
|-----|------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|-------------------------------------------------------------------------|------------|
| 1   | Tutor who prefers flexibility                                                | Reschedule my class                                                                 | I can allow the students to more flexibility when arranging for a class | HIGH       |
| 2   | Meticulous tutor                                                             | Add teaching notes                                                                  | so that I can record any additional information about the student.      | HIGH       |
| 3   | Organised tutor                                                              | View which students I have in the day                                               | I know what to expect in the day                                        | HIGH       |
| 4   | Tutor                                                                        | Edit the students contact details                                                   | I am able to contact the student or next-of-kin whenever necessary      | HIGH       |
| 5   | Tutor with forgetful students                                                | Check how much a single student owes me                                             | I can remind the student to pay me                                      | HIGH       |
| 6   | New user                                                                     | See which commands are available for me to use                                      | I know how to use the application                                       | HIGH       |
| 7   | Tutor who has many students                                                  | Check the students’ phone number                                                    | I can contact them                                                      | HIGH       |
| 8   | Forgetful tutor                                                              | Know the address of the students                                                    | Know where I should go to for class                                     | HIGH       |
| 9   | Tutor who likes a clear overview                                             | View a list of all students                                                         | Have a clear view of all students                                       | HIGH       |
| 10  | Tutor who likes to minimise the number of applications opened on his desktop | Have a way to close the application                                                 | Exit the application                                                    | HIGH       |
| 11  | Tutor who has many students                                                  | I can check the students’ next of kin’s phone number                                | Contact the parents or guardian under certain circumstances             | HIGH       |
| 12  | As a tutor who likes to have a clear picture                                 | Have a better UI to view the schedule and the tasks                                 | So that I can view everything in one page                               | HIGH       |
| 13  | Careless tutor                                                               | be warned if I’ve added an existing student to the student list                     | I do not have duplicated students in my contact book                    | MEDIUM     |
| 14  | Inexperienced tutor at coding                                                | Receive a echo of my command                                                        | I know that I’ve typed the information correctly                        | MEDIUM     |
| 15  | Forgetful tutor                                                              | Be warned if the addition of a new session will clash with a previously set session | I can have a peace of mind that my students’ sessions do not clash      | MEDIUM     |
| 16  | Busy tutor                                                                   | Sort my students based on the class timing                                          | I know when is my next class                                            | MEDIUM     |
| 17  | Tutor                                                                        | Search up the students by the class date                                            | I know which students I have on a specific day                          | MEDIUM     |
| 18  | Tutor                                                                        | Edit the students unpaid session                                                    | I can calculate how much money a student owes                           | MEDIUM     |
| 19  | Lazy tutor                                                                   | Use a single command to find multiple students                                      | Find multiple students quickly                                          | MEDIUM     |
| 20  | Lazy tutor                                                                   | Use a single command to delete multiple students                                    | Delete multiple students quickly                                        | MEDIUM     |
| 21  | Busy tutor                                                                   | Use shorter commands                                                                | I can simplify the commands by using aliases                            | MEDIUM     |
| 22  | Tutor who needs to manage many students                                      | View the students he has after or before a certain date                             | Filter students before/after a certain datetime                         | MEDIUM/LOW |
| 23  | Tutor who wants to do things quick                                           | Delete all students at once                                                         | Clear my entire schedule                                                | MEDIUM/LOW |
| 24  | Tutor                                                                        | Find the next available time slot                                                   | I do not have to check for clashes each time I schedule the next class  | LOW        |
| 25  | Busy tutor                                                                   | Sort my students based on alphabetical order                                        | I can find the details of my students quicker                           | LOW        |
| 26  | Poor tutor                                                                   | Sort my students based on the amount of money owed                                  | I know who owes the most amount of money so that I can ask for payment. | LOW        |
| 27  | Busy tutor                                                                   | Add the date of the next class in a more natural format (eg: Next Tuesday)          | I do not need to search for the date manually                           | LOW        |
| 28  | Forgetful tutor                                                              | See tags by students                                                                | See necessary info such as #python.                                     | LOW        |
| 29  | Tutor who loves money                                                        | Do a summation of how much money earned                                             | Know which students I should follow up with regarding the fees.         | LOW        |
| 30  | Tutor who loves money                                                        | Know the total money unpaid by all students                                         | Know the total money unpaid by students                                 | LOW        |
| 31  | Tutor who wants to keep track of expenses                                    | Check the total amount of money paid by all students                                | I can check the total amount I have earned                              | LOW        |

## Use Case

(For all use cases below, the **System** is the `Teacher's Pet` and the **Actor** is the `Teacher`, unless specified otherwise)

### Use case: **Delete a student**

**MSS**

1. Teacher requests to list students
2. Teacher’s Pet shows a list of students
3. Teacher requests to delete a specific student in the list
4. Teacher’s Pet deletes the student

   Use case ends.


**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. The given index is invalid.
    - 3a1. Teacher’s Pet shows an error message.

      Use case resumes at step 2.

[](#use-case-edit-a-student-contact-detail)
### Use case: **Edit a student contact detail**

**MSS**

1. Teacher requests to edit contact number of a specific student in the list
2. Teacher’s Pet edits the student

   Use case ends.

**Extensions**

- 1a. The given index is invalid.
    - 1a1. Teacher’s Pet shows an error message.

  Use case ends.

- 1b. The given contact number is invalid.
    - 1b1. Teacher’s Pet shows an error message.

  Use case ends.

### Use case: **Find student contact details**

**MSS**

1. Teacher requests to find the details of a specific student
2. Teacher’s Pet shows the student’s details

   Use case ends.

**Extensions**

- 1a. Student name does not exist in the system.
    - 1a1. Teacher’s Pet shows an error message.

      Use case ends.

- 1b. Multiple students share the same name in the system.
    - 1b1. Teacher’s Pet lists the details of multiple people.

      Use case ends.

### Use case: **Mark student as present for class**

**MSS**

1. Teacher requests to list students
2. Teacher’s Pet shows a list of students
3. Teacher requests to mark a specific student in the list as present for class
4. Teacher’s Pet marks the student as present for class

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    - 3a1. Teacher’s Pet shows an error message.

        Use case resumes at step 2.

* 3b. The student’s attendance is already marked as present.

    - 3b1. Teacher’s Pet shows a message indicating student’s attendance is already marked as present.

        Use case resumes at step 2.

### Use case: **Allocate a slot for future class**

**MSS**

1. Teacher requests to find the next available slot for class
2. Teacher discusses with the student about whether the proposed slot is possible
3. Teacher [edits](#use-case-edit-a-student-contact-detail) the student record with the next class date

**Extensions**

- 2a. The student cannot make it on the proposed slot
  - Step 1-2 is repeated until a mutually-agreed slot is found.

    Use case resumes at step 3.

## Non-Functional Requirement

1. Should work on any *mainstream OS* as long as it has Java`11` or above installed.
2. Should be able to hold up to 50 students without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text _(i.e. not code, not system admin commands)_ should
   be able to accomplish most of the tasks faster using commands than using the mouse.
4. If the user wants to complete something, he can do it within 3 commands maximum.
5. The application can store the changed user data after the application has been closed and load from memory when it is
   opened.
6. The UI page should load when first launched within 2 seconds.

## Glossary

| Terms         | Definition                              |
|---------------|-----------------------------------------|
| Mainstream OS | Windows, Linux, Unix, OS-X              |
| CLI           | Command Line Interface                  |
| Class         | The 1-1 tutoring time slot of a student |

Note:
- Command Line Interface: Text based user interface for the user to interact with, by passing in single line commands.
