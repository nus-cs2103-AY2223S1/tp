---
layout: page
title: Developer Guide
---
# Developer Guide

## Table of contents
* [Implementation](#implementation)
  * [Edit Class Feature](#edit-class-feature)
    * [Implementation details](#implementation-details)
    * [Design Considerations](#design-considerations)
  * [Statistics Display Feature](#statistics-display-feature)
  * [[Proposed] Sort-by](#proposed-sort-by-feature)
* [Appendix](#appendix-requirements)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
  * [User Stories](#user-stories)
  * [Use Cases](#use-cases)
    * [Use case: **Delete a student**](#use-case-delete-a-student)
    * [Use case: **Edit a student contact detail**](#use-case-edit-a-student-contact-detail)
    * [Use case: **Find student contact details**](#use-case-find-student-contact-details)
    * [Use case: **Find student by address**](#use-case-find-student-by-address)
    * [Use case: **Mark student as present for class**](#use-case-mark-student-as-present-for-class)
    * [Use case: **Allocate a slot for future class**](#use-case-allocate-a-slot-for-future-class)
  * [Non-Functional Requirement](#non-functional-requirement)
  * [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------
## Design
### Architecture

<img src="images/DG-images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/DG-images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/DG-images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component
[//]: # (TODO: The UI component needs to be updated)

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/DG-images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter`, `StatisticsDisplay`, `ScheduleListPanel` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/DG-images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TeachersPetParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DG-images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/DG-images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TeachersPetParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `TeachersPetParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/DG-images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the teacher's pet data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TeachersPet`, which `Person` references. This allows `TeachersPet` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/DG-images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/DG-images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both teacher's pet data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TeachersPetStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
## Implementation

This section describes some noteworthy details on how certain features are implemented.
The features covered in this guide are:

* [Edit Class Feature](#edit-class-feature)
* [Statistics Display Feature](#statistics-display-feature)
* [[Proposed] Sort-by feature](#proposed-sort-by-feature)
* [[Proposed] Find-by feature](#proposed-find-by-feature)

### Edit Class Feature

This feature allows the teacher to create a class at a specified date and time.

#### Implementation Details

The edit class mechanism is facilitated by ClassStorage. It stores the date of the classes as well as the students who attend them.

Additionally, it implements the following operations:

ClassStorage#saveClass() — Saves the new class into its storage.

ClassStorage#removeExistingClass() — Removes class from storage to free up the time slot.

ClassStorage#hasConflict() — Checks if there is a conflict between the class timings.

The `EditCommandParser` reads the input and passes it to `ParserUtil` which returns an `Index`. If the given index is not a positive integer,
a `ParseException` will be thrown.
If the index is valid, `ParserUtil` will then check that both the date and time are valid before creating an `EditCommand`.

During the execution of `EditCommand`, if the given index is not within the range of the list, a `CommandException` will be thrown.
Otherwise, the model will then obtain the student using getFilteredPersonList.

Before assigning the class to the student, `ClassStorage` will check that there is no conflict between the timings of the new class
and the existing classes. `ClassStorage` will also check if the student has a pre-existing class. If yes, the pre-existing class
will be removed in order to free up the time slot. If there is no time conflict, `ClassStorage` will proceed to
save both the new class and student.

The following sequence diagram shows how the edit class operation works:

![EditClassSequenceDiagram](images/DG-images/EditClassSequenceDiagram.png)

The following activity diagram summarizes what happens when a teacher executes an edit class command:

![EditClassActivityDiagram](images/DG-images/EditClassActivityDiagram.png)

#### Design Considerations:
##### Aspect: Input format for edit class:

* **Alternative 1**: dt/yyyy-MM-dd 0000-2359
  * Pros: Easy to implement.
  * Cons: The teacher has to fully match the date format and order, which is much more cumbersome.

* **Alternative 2**: dt/Day-of-Week 0000-2359 (case-insensitive)
  * Pros: More convenient and easier for the teacher to type.
  * Cons:
    1. Harder to implement.
    2. Only can set the class to a date at most 1 week away.
  
### Statistics Display Feature

This feature allows the teacher to get an overall view of his/her teaching statistics, which includes the number of students, total money owed and total money paid by the current list of students.

#### Implementation Details

`MainWindow.fxml` is modified to update the `UI` from the original single-panel view to include an additional top right statistics panel.
The calculation of the statistics is facilitated by `StatisticsCalculator`, which stores `ReadOnlyTeachersPet` internally that keeps track of the list of students.

Additionally, it implements the following operations:
- `StatisticsCalculator#getSize()` — Gets the current number of students in the list.
- `StatisticsCalculator#getAmountOwed()` — Calculates the total amount owed by students in the list.
- `StatisticsCalculator#getAmountPaid()` — Calculates the total amount paid by students in the list.

When a user command gets executed, the 3 operations are performed once to display the updated statistics.

How the individual operations work:
- `StatisticsCalculator#getSize()`
  1. When `StatisticsCalculator#getSize()` is called by `StatisticDisplay`, `StatisticsCalculator` attains the updated list of students from `ReadOnlyTeachersPet`.
  2. After getting the list of students in the form of `ObservableList<Person>`, StatisticsCalculator returns the `size` of the `ObservableList<Person>`.
  
  ![StatisticsCalculatorGetSizeSequenceDiagram](images/DG-images/StatisticsCalculatorGetSizeSequenceDiagram.png)

- `StatisticsCalculator#getAmountOwed()`
  1. When `StatisticsCalculator#getAmountOwed()` is called by `StatisticDisplay`, `StatisticsCalculator` attains the updated list of students from `ReadOnlyTeachersPet`.
  2. After getting the list of students in the form of `ObservableList<Person>`, StatisticsCalculator iterates across all students in the list and sums the total amount owed.

  ![StatisticsCalculatorGetAmountOwedSequenceDiagram](images/DG-images/StatisticsCalculatorGetAmountOwedSequenceDiagram.png)

- `StatisticsCalculator#getAmountPaid()`
  1. When `StatisticsCalculator#getAmountPaid()` is called by `StatisticDisplay`, `StatisticsCalculator` attains the updated list of students from `ReadOnlyTeachersPet`.
  2. After getting the list of students in the form of `ObservableList<Person>`, StatisticsCalculator iterates across all students in the list and sums the total amount paid.
  
  ![StatisticsCalculatorGetAmountPaidSequenceDiagram](images/DG-images/StatisticsCalculatorGetAmountPaidSequenceDiagram.png)

#### Design Considerations:
##### Aspect: Implementing the statistics function:

* **Alternative 1 (current choice)**: Call three different statistics functions (getSize(), getAmountOwed(), getAmountPaid()) 
    * Pros: Each function obeys Single Responsibility Principle (SRP).
    * Cons: More code must be typed.

* **Alternative 2**: Call a single statistics function which returns an array of statistical values
    * Pros: 
        1. Fewer repetition of code.
        2. More optimised solution as `ObservableList<Person>` needs to be iterated only once. 
    * Cons: Violates Single Responsibility Principle (SRP) as the function would have multiple responsibilities.

### [Proposed] Sort-by feature

This feature allows the user (teacher) to sort the students from Teacher's Pet by one of the specified keywords.

#### Proposed Implementation

The proposed `sort` mechanism is facilitated within [TeachersPet.java](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/model/TeachersPet.java).
The `SortCommand` object will be creating a comparator based on the argument received and pass to `TeachersPet` so that it will return the
list of person as per usual. Additionally, it implements the following operation:
- `TeachersPet#SortBy(ComparatorM<Person>)` -- Updates the `FilteredPersonList` by reordering the list with the given `Comparator`

The following diagram illustrates how the operation works:

![SortBySequenceDiagram](images/DG-images/SortBySequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `SortByCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

### [Proposed] Find-by feature

This feature allows the user (teacher) to find a list of students from Teacher's Pet by one of the specified keywords.

#### Proposed Implementation

The proposed `find` mechanism is facilitated within [TeachersPet.java](https://github.com/AY2223S1-CS2103T-T09-4/tp/tree/master/src/main/java/seedu/address/model/TeachersPet.java).
There are 4 different variations of `find`:
1. Find by name: Find all matching student(s) using any matching full keyword(s) from name of student using `find n/[KEYWORDS]`.
2. Find by email: Find all matching student(s) with any matching full keyword(s) from email of student using `find e/[KEYWORDS]`.
3. Find by address: Find all matching student(s) using any matching full keyword(s) from address of using `find a/[KEYWORDS]`.
4. Find by tag: Find all matching student(s) with exact matching full keyword(s) from tag(s) of student using `find t/[TAG]`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The 4 variations cannot be mixed with one another.</div>

The following activity diagram summarizes what happens when a user executes a find command:

![FindActivityDiagram](images/DG-images/FindActivityDiagram.png)

Below is an example of the general flow of a find by address command.

##### Find by address
1. `FindCommandParser` will parse the keywords to `AddressContainsKeywordsPredicate`.
2. `AddressContainsKeywordsPredicate` will be generated and a predicate value will be returned to `FindCommandParser`.
3. `FindCommandParser` will send the predicate value to `FindCommand`.
4. `FindCommand` will be generated and the command will be returned to the `FindCommandParser`.
5. `FindCommand` will call the `execute(model)` function, and pass the predicate value into `Model` through `updateFilteredTaskList`.
6. `filteredTasks` list will be updated accordingly in `ModelManager` and the list display in Teacher's Pet will be updated.
7. `CommandResult` will eventually be returned and feedback will be given to the user.

The Sequence Diagram below shows how the components interact with each other when the user issues a find command:

![FindByAddressSequenceDiagram](images/DG-images/FindByAddressSequenceDiagram.png)

## Appendix: Requirements

### Target User Profile

- a private tutor who teaches 1-1 classes and needs to manage the students’ details
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

### Value Proposition

Manage contacts and schedule of students faster than a typical mouse/GUI driven app

### User Stories

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


### Use Cases

(For all use cases below, the **System** is the `Teacher's Pet` and the **Actor** is the `teacher`, unless specified otherwise)

#### Use case: **Delete a student**

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

#### Use case: **Edit a student contact detail**

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

#### Use case: **Find student contact details**

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

#### Use case: **Find student by address**

**MSS**

1. Teacher requests to [find](#use-case-find-student-by-address) a student by address
2. Teacher’s Pet shows a list of filtered students according to their provided query

   Use case ends.

**Extensions**

- 1a. Teacher requests to find by address without providing any query.
    - 1a1. Teacher’s Pet displays invalid command format message.

      Use case ends.

- 1b. Teacher's Pet detects multiple students share the same address in the system.
    - 1b1. Teacher’s Pet lists the details of multiple students.

      Use case ends.

#### Use case: **Mark student as present for class**

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

#### Use case: **Allocate a slot for future class**

**MSS**

1. Teacher requests to find the next available slot for class
2. Teacher discusses with the student about whether the proposed slot is possible
3. Teacher [edits](#use-case-edit-a-student-contact-detail) the student record with the next class date

**Extensions**

- 2a. The student cannot make it on the proposed slot
  - Step 1-2 is repeated until a mutually-agreed slot is found.

    Use case resumes at step 3.

### Non-Functional Requirement

1. Should work on any *mainstream OS* as long as it has Java`11` or above installed.
2. Should be able to hold up to 50 students without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text _(i.e. not code, not system admin commands)_ should
   be able to accomplish most of the tasks faster using commands than using the mouse.
4. If the user wants to complete something, he can do it within 3 commands maximum.
5. The application can store the changed user data after the application has been closed and load from memory when it is
   opened.
6. The UI page should load when first launched within 2 seconds.

### Glossary

| Terms         | Definition                                           |
|---------------|------------------------------------------------------|
| Mainstream OS | Windows, Linux, Unix, OS-X                           |
| CLI           | Command Line Interface                               |
| Class         | The 1-1 tutoring time slot of a student              |
| Day-of-Week   | 3-letter Abbreviation; case-insensitive eg. Mon, MON |

Note:
- Command Line Interface: Text based user interface for the user to interact with, by passing in single line commands.
