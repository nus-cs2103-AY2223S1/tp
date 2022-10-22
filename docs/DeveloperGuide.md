---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletestudent 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student`, `Task` and `Lesson` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `JarvisParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddTaskCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletestudent 1")` API call.

![Interactions Inside the Logic Component for the `deletestudent 1` Command](images/DeleteStudentSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `JarvisParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddTaskCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddTaskCommand`) which the `JarvisParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddTaskCommandParser`, `DeleteStudentCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the student data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

Similar analogues exist for task and lesson data. The class diagram is similar apart from:
1. the different naming (`TaskBook` and `LessonBook` instead of `StudentBook`, `UniqueTaskList` and `UniqueLessonList` instead of `UniqueStudentList`)
2. the components of the `Task` and `Lesson` classes. For example, instead of `StudentName` and `MatricNumber`, `Lesson` is composed out of `LessonAttendance`, `TimePeriod` etc.

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T11-3/tp/tree/master/src/main/java/jarvis/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />
This diagram only shows the StudentBook Storage, but storage for Lessons and Tasks is done similarly. The only difference is the name of the classes (`JsonTaskBookStorage` instead of `JsonStudentBookStorage`, `JsonAdaptedTask` instead of `JsonAdaptedStudent` etc.)

The `Storage` component,
* can save student, task and lesson data as well as user preference data in json format, and read them back into corresponding objects.
* inherits from `StudentBookStorage`, `TaskBookStorage` and `UserPrefStorage`, which means it can be treated as any one of them (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `jarvis.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Mark Task as done / not done
In order to mark a task as completed, the user keys in a valid command (e.g. `marktask 2`). Parsing of the user input is done (see the sequence diagram for deleting a student in the [Logic component](#logic-component) for a similar parsing sequence) and a `MarkTaskCommand` is then generated. The following sequence diagram shows what happens when the `MarkTaskCommand` is executed.

<img src="images/MarkTaskSequenceDiagram.png" width="550"/>

1. First, the list of tasks is obtained and the index is matched to the corresponding task.
2. This task is then marked as done.
3. The list of tasks in the model is then updated in order to display the updated task in the UI.

The implementation for marking a task as not done is similar.

### Adding a Lesson

In order to add a Lesson into JARVIS, the user keys in a valid command (e.g. `addmc l/mastery check 1 sd/2022-09-15T20:00 ed/2022-09-15T20:30 si/1 si/2`)
Parsing of the user input is done and a `AddMasteryCheckCommand` is then generated. (See the sequence diagram for deleting a student in the [Logic component](#logic-component))

The sequence diagram is similar apart from:
1. the command executed and parsed (`addmc l/mastery check 1 sd/2022-09-15T20:00 ed/2022-09-15T20:30 si/1 si/2` instead of `deletestudent 2`)
2. the different command class (`AddMasteryCheckCommandParser` and `AddMasteryCheckCommand` instead of `DeleteStudentCommandParser` and `DeleteStudentCommand`)
3. function called in main (`addLesson` instead of `deleteStudent`)

`MasteryCheckCommandParser` checks if:

1. all prefixes are present
2. lesson description is not empty
3. start date time is before end date time
4. student indexes are int

Otherwise, `ParseException` will be thrown.

The rationale behind this design is that for all `Lesson`, there must be a `LessonDesc` present.
It is also illogical for a lesson to start after the end date time. A `Student` must also be assigned manually to a `MasteryCheck`
as the purpose of `MasteryCheck` is to assess a student's capability.

**Future Implementation**
- Allow user to input duration of lesson(in hours) to replace end date time
- JARVIS will calculate the end date time for user based on the given start date time and duration
- Helps to shorten the command required to be typed as lessons are likely to end on the same day


The following sequence diagram shows what happens when the `AddMasteryCheckCommand` is executed upon a successful command.

<img src="images/AddMasteryCheckSequenceDiagram.png" width="550"/>

- `AddMasteryCheckCommand` will get the students involved in the `MasteryCheck` via indexing of the `lastShownList`. If no `Student` are found based on the index, `CommandException` will be thrown, stating invalid student index.
- After a `MasteryCheck` object is created, `Model` will check if there already exists a `MasteryCheck` in the current `LessonBook` with the same identity fields. If this is the case,`CommandException` will be thrown, stating duplicate Mastery Check.
- `Model` will also check with existing `Lessons` if there will be a clash in `TimePeriod`. This serves as a reminder to the user that there is already another lesson at that time slot. `CommandException` will be thrown, stating clash in timeslot.


The above explanation is also applicable to adding consultation and studio lessons.
They are similar apart from:
1. the different naming(`AddConsultCommandParser`, `AddStudioCommandParser` etc instead of `AddMasteryCheckParser`)
2. for `Studio`, all `Student` currently in the `StudentBook` instead of `FilteredStudentList` will be used to create `LessonAttendance` and `LessonNotes`
   1. Studio are tutorials and all students are expected to attend. 
   2. As a result, adding a Studio command does not require user to input student indexes.

### Adding notes for a lesson

In adding notes for an existing `Lesson` in JARVIS, the user has the option to:
1. add overall notes for a `Lesson`
2. add `Student` specific notes for a `Lesson` 

To add an overall note for an existing `Lesson` in JARVIS, the user keys in a valid add note command (e.g addnote n/get back to the class on streams li/1). 

To add a `Student` specific note to an existing `Lesson` in JARVIS, the user similarly keys in a valid add note command, but additionally specifying the student index (e.g addnote n/get back to jeff on streams li/1 si/3).

Parsing of the user input is done and a `AddNoteCommand` is then generated.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: CS1101S TA

* has to keep track of significant number of tasks
  * grade mission and quests
  * schedule mastery checks
  * studio attendance and participation
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage students and tasks in an organised manner easily and quickly


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                           | I want to …​                                                    | So that I …​                                                                               |
|----------|-----------------------------------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| `* * *`  | potential user                    | find the installation/setup instructions                        | can install the app properly                                                               |
| `* * *`  | user ready to start using the app | see the basic commands                                          | can learn how to use the most basic features of the app                                    |
| `* * *`  | user ready to start using the app | add the students in my tutorial class into the app              | can track the students' attendance and grades                                              |
| `* * *`  | user                              | add my tasks                                                    | will not forget to do any of the tasks                                                     |
| `* * *`  | user                              | mark tasks as done                                              | can focus on the remaining tasks                                                           |
| `* * *`  | user                              | mark tasks as not done                                          | can go back and redo tasks that are incomplete                                             |
| `* * *`  | clumsy user                       | delete tasks                                                    | can remove tasks I have wrongfully added                                                   |
| `* * *`  | clumsy user                       | delete students                                                 | can remove students I have wrongfully added                                                |
| `* * *`  | user                              | list the tasks that I need to do                                | can see all my tasks                                                                       |
| `* * *`  | user                              | list students in my class                                       | can see all my students with relevant details                                              |
| `* * *`  | user                              | see my students' mastery check completion status                | know which students I have not seen for mastery check                                      |
| `* * *`  | user                              | update my students' mastery check completion status             | can keep track of which students I have already seen for mastery check                     |
| `* *`    | first time user                   | see the app being populated with sample students and tasks      | can try out the functions of the app                                                       |
| `* *`    | user ready to start using the app | clear all current data                                          | can get rid of the sample data used for exploring the app and input my own data            |
| `*`      | user                              | assign different priorites to my tasks                          | can focus on the more important tasks                                                      |
| `*`      | user ready to start using the app | import my timetable for the semester	                           | can plan my TA duties in sync with tasks from other modules                                |
| `*`      | user                              | add in mastery check timeslots                                  | can keep track of when and who I have to see for mastery check and I can make preparations |
| `*`      | user                              | detect if there any schedule conflicts in my upcoming tasks     | resolve those conflicts and complete all my tasks                                          |
| `*`      | user                              | add in timeslots for consultations	                             | can keep track of details of consultation and students                                     |
| `*`      | user                              | get the task with the next earliest deadline	                   | can plan my schedule accordingly                                                           |
| `*`      | user                              | receive reminders about upcoming deadlines                      | am able to meet all my deadlines on time                                                   |
| `*`      | user                              | receive notifications when my students submit their assignments | know that I have assignments to grade                                                      |
| `*`      | user                              | keep track of my students' level of participation               | decide how to assign tutorial participation exp for each student                           |
| `*`      | user                              | keep track of my students' grades                               | can focus more on the weaker students                                                      |
| `*`      | user                              | send notifications to my students                               | can remind them to submit their work                                                       |
| `*`      | user                              | see performance statistics on each assignment                   | can spend more time on topics that my students are weak in                                 |
| `*`      | user                              | see attendance statistics for studios                           | can understand the needs of my students better                                             |
| `*`      | user                              | hide irrelevant data                                            | can focus on the more relevant data                                                        |



### Use cases

(For all use cases below, the **System** is `JARVIS` and the **Actor** is `AVENGER`, unless specified otherwise)

**Use case: UC1 - Add a student**

**MSS**

1. Avenger requests to add a student.
2. JARVIS successfully adds the student.

Use case ends.

**Extensions**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC2 - Add a Task**

**MSS**

1. Avenger requests to add a task.
2. JARVIS successfully adds the task.

Use case ends.

**Extensions**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC3 - List students**

**MSS**

1. Avenger requests to list students.
2. JARVIS displays the list of students.

Use case ends.

**Extensions**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC4 - List tasks**

**MSS**

1. Avenger requests to list tasks.
2. JARVIS displays the list of tasks.

Use case ends.

**Extensions**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC5 - Delete a student**

Preconditions:  There are existing students in JARVIS.

**MSS**

1. Avenger performs <ins>list students(UC3)</ins>.
2. JARVIS displays list of students.
3. Avenger requests to delete a student.
4. JARVIS deletes the student.

Use case ends.

**Extensions**

* 3a. JARVIS fails to understand request.
  * 3a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 3.

**Use case: UC6 - Delete a task**

Preconditions: There are existing tasks in JARVIS.

**MSS:**

1. Avenger performs <ins>list tasks (UC4)</ins>.
2. JARVIS displays list of tasks.
3. Avenger requests to delete a task.
4. JARVIS deletes the task.

Use case ends.

**Extensions:**

* 3a. JARVIS fails to understand request.
  * 3a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 2.

**Use case: UC7 - Clear all students and tasks**

Preconditions: There are existing tasks and/or students in JARVIS.

Guarantees: All students and tasks will be deleted.

**MSS:**

1. Avenger requests to clear all data.
2. JARVIS requests for confirmation message.
3. Avenger confirms.
4. JARVIS clears all data.

Use case ends.

**Extensions:**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

* 2a. Avenger fails to confirm.

  Use case ends.

**Use case: UC8 - Mark task as done**

Preconditions: There are existing tasks in JARVIS

Guarantees: Specified task is marked as done

**MSS:**

1. Avenger requests to mark a task as done.
2. JARVIS displays the list of tasks with the aforementioned task marked as done.

Use case ends.

**Extensions:**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

* 1b. JARVIS detects invalid task.
  * 1b1. JARVIS informs Avenger that the task does not exist.
  * 1b2. JARVIS displays the list of tasks.
  * 1b3. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC9 - Mark task as not done**

Preconditions: There are existing tasks in JARVIS

Guarantees: Specified task is marked as not done

**MSS:**

1. Avenger requests to mark a task as not done.
2. JARVIS displays the list of tasks with the aforementioned task marked as not done.

Use case ends.

**Extensions:**

* 1a. JARVIS fails to understand request.
  * 1a1. JARVIS tells Avenger to make a request again.

  Use case resumes from step 1.

* 1b. JARVIS detects invalid task.
  * 1b1. JARVIS informs Avenger that the task does not exist.
  * 1b2. JARVIS displays the list of tasks.
  * 1b3. JARVIS tells Avenger to make a request again.

    Use case resumes from step 1.

**Use case: UC10 - Record student's mastery check result**

Preconditions: There are existing students in JARVIS.

**MSS:**

1. Avenger performs <ins>list students (UC3)</ins>.
2. JARVIS displays the list of students.
3. Avenger requests to record a student’s mastery check result.
4. JARVIS displays list of students and mastery check results, with the aforementioned student’s updated mastery check result.

Use case ends.

**Extensions:**

* 3a. JARVIS fails to understand request.
  * 3a1. JARVIS tells Avenger to make a request again.

    Use case resumes from step 3.

### Non-Functional Requirements

1.  Should work on Windows, Linux, and OS-X platforms that has version 11 of Java (i.e. no other Java versions) installed.
2.  Should work without requiring an installer and be packaged in a single jar file
3.  All data for the system should be stored locally in a human editable text file, and not be dependent on any remote server
4.  GUI should not cause any resolution-related inconveniences for standard screen resolutions (1920 x 1080 and higher) and screen scales 100% and 125%
5.  GUI should be usable (i.e. all functionality can be used, not necessarily optimally) for screen resolutions 1280 x 720 and higher, and for screen scales 150%
6.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands over other means of input.
7.  The product is intended only for a single user (i.e. not a multi-user product)
8.  The system is not required to handle the actual grading of student's works

*{More to be added}*

### Glossary
* **Mastery check**: An assessment where students in pairs must present what they have learnt in the module to their TA, and the TA will assess the students’ understanding of the concepts.
* **Avenger**: A teaching assistant (TA) who is responsible for teaching a studio class.
* **Studio**: A tutorial class with up to 8 students.
* **XP**: Points that will count towards a student’s grade
* **Source Academy**: Online platform used for CS1101S.
* **Mission**: Assignment on Source Academy
* **Quest**: Optional assignment on Source Academy

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample students and tasks. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all persons are being shown

   1. Prerequisites: List all student using the `liststudent` command. Multiple students in the list.

   1. Test case: `deletestudent 1`<br>
      Expected: First student is deleted from the list. Details of the deleted student shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `deletestudent 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `deletestudent`, `deletestudent x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
