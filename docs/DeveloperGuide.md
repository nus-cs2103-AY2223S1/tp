---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Code used: [Input History](https://github.com/AY2122S2-CS2103T-W13-3/tp)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="700" />


The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores the task book data i.e., all `Task` objects which are contained in a `TaskList` object
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="700" />

The `Storage` component,
* can save both address book data, task book data and user preference data in json format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`, `TaskBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Student Class Group Feature
The class group feature allows a student to have a class group. The feature consists of the following command
- ```ClassGroupCommand``` - Can add, delete and modify a student's class group field.
- ```EditCommand``` - Can modify a student's class group field
- ```AddCommand``` - Can add a student directly with a class field

The class group commands follow similar paths of execution which defers slightly from Logic sequence diagram.
This is illustrated in the sequence diagram below, which shows the diagram for ClassGroupCommand.

**Class Group command**

Implementation:

The following is a more detailed explanation on how ```ClassCommand``` works.

1. The ```AddressBookParser``` will select ```ClassGroupCommandParser``` and parse the user input.
2. Following which, ```ClassGroupCommand#execute(Model model)``` method is called which
updates the class group field of a student.
3. If the student already has class field data, the input will overwrite existing data.
4. If class field input is empty, ```ClassGroupCommand#execute(Model model)``` will delete the class group data.

![Class Group sequence diagram](images/ClassGroupSequenceDiagram.png)

#### Design considerations:

**Aspect: Command Syntax**
- Current implementation: Using the command word ````class````
- Pros: Simple to understand
- Cons: Not as fast to type and can be made faster to type if length of command is shorter
- Alternatives considered: We considered using ```<Index> c/``` format, e.g. ```1 c/CS2032S Lab 32```
which sets the class group field of the student with index ```1``` to ```CS2030S Lab 32```
- Pros: Faster for user who can type fast
- Cons: Does not follow the format as other commands making it confusing for the user.

### Student Attendance feature ###
The student attendance feature keeps track of student's attendance. The feature consists of commands namely,
- ```AttendanceAddCommand``` - Adds an attendance list to the student in the class list.
- ```AttendanceDeleteCommand``` - Removes the attendance list to the student in the class list.
- ```AttendanceMarkCommand``` - Marks or unmarks student's attendance in his attendance list.

The attendance commands all follow similar paths of execution which defers slightly from Logic sequence diagram.
This is illustrated in the sequence diagram below, which shows the diagram for Student<INSERT>Command.


The attendance commands when executed will use methods exposed by the ```Model``` interface and perform the related operations.

**Common steps among the Attendance Commands**

1. The ```AddressBookParser``` will select ```AttendanceCommandParser``` and parse the user input.
2. The ```AttendanceCommandParser#parse``` will select another AttendanceParser to parse in the arguments.
3. The arguments are tokenized and the respective refined inputs of each argument is created.

**Add Attendance command**
Implementation:

The following is a more detailed explanation on how ```AttendanceAddCommand``` works.

1. After the successful parsing of user input into ```AttendanceCommandParser```, the input gets parsed into ```AttendanceAddCommandParser``` to further separate user input.
2. Following which, ```AttendanceAddCommand#execute(Model model)``` method is called which validates the attendance list.
3. If the student index or size specified is invalid, a `ParserExeception` will be thrown and attendance will not be added to the student.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the format of adding attendance contains error(s), GREWZ will display either an unknown command or wrong formatting error message.</div>

4. The method `Model#setStudent(studentToEdit, editedStudent)` and `Model#updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS)` gets called and a new `CommandResult` will be returned with the success message.

**Mark Attendance command**

Implementation:

The following is a more detailed explanation on how `AttendanceMarkCommand` works.

1. After the successful parsing of user input into ```AttendanceCommandParser```, the input gets parsed into ```AttendanceMarkCommandParser``` to further separate user input.
2. Following which, ```AttendanceMarkCommand#execute(Model model)``` method is called which validates the attendance list.
3. If the student index, lesson number or attendance value specified is invalid, a ```ParserExeception``` will be thrown and attendance will not be marked.
4. The method ```Model#setStudent(studentToEdit, editedStudent)``` gets called and a new `CommandResult` will be returned with the success message.

**Delete Attendance command**

Implementation:

The following is a more detailed explanation on how `AttendanceDeleteCommand` works.

1. After the successful parsing of user input into ```AttendanceCommandParser```, the input gets parsed into ```AttendanceDeleteCommandParser``` to further separate user input.
2. Following which, ```AttendanceDeleteCommand#execute(Model model)``` method is called which validates the attendance list.
3. If the student index specified is invalid, a ```ParserExeception``` will be thrown and attendance list will not be delete.
4. The method ```Model#setStudent(studentToEdit, editedStudent)``` and `Model#updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS)` gets called and a new `CommandResult` will be returned with the success message.

![attendance delete activity](images/AttendanceDeleteActivityDiagram.png)
Activity diagram for AttendanceDeleteCommand
![attendance delete sequence](images/AttendanceDeleteSequenceDiagram.png)
Sequence diagram for AttendanceDeleteCommand
#### Design considerations:

**Aspect: Command Syntax**
- Current implementation: Using 2 command word syntax \n E.g. ```attendance add```
- Pros: Increases clarity and flexibility of future commands
- Cons: Users have to type more
- Alternatives considered: We considered using only ```attendance```, while using the forward slash ```/``` with specific prefixes for attendance commands, e.g. ```attendance 1 a/10 s/12```, which adds an attendance list of size 12 to the first student.
- Pros: Users type less
- Cons: Users might accidentally delete/alter attendance lists and takes a longer time to learn command prefixes.

**Aspect: Size of attendance lists**
- Current Implementation: Max size of 12
- Pros: No need to resize attendance list display, users typically do not have more than 12 tutorials.
- Cons: Less flexibility in size of attendance list.

### Upload Student Picture Feature
The address book is able to have profile pictures assigned to each student. The upload picture feature allows for tutors to add the profile picture corresponding to a student.
This feature comprises a single ```UploadPictureCommand```

The command when executed uses methods exposed by the ```Model``` interface and ```ImageStorage``` Class.

The following is a more detailed explanation of how the `UploadPictureCommand` works.
1. After the successful parsing of user input into ```UploadPictureCommandParser```, the ```UploadPictureCommand``` object is created.
2. Following which, ```UploadPictureCommand#execute(Model model)``` method is called which calls ```ImageStorage#chooseImage()``` to open the file chooser.
3. The user then selects the picture from their files, and it is checked by ```ImageStorage#isJpgFile()``` for being a file of valid format.
4. The file is then uploaded via ```ImageStorage#uploadImage(Student student, File file)``` into the images folder in the current working directory which was created upon intialization of GREWZ.
5. If the student index or size specified is invalid, a `ParserExeception` will be thrown and attendance will not be added to the student.

![picture upload activity](images/PictureUploadActivityDiagram.png)
Activity diagram for PictureUploadCommand
![picture upload sequence](images/PictureUploadSequenceDiagram.png)
Sequence diagram for PictureUploadCommand


#### Design considerations:

**Aspect: How to select an image**
- Current implementation: A file chooser window is opened.
- Pros: User can navigate visually through the files.
- Cons: User will need to use a mouse instead of typing only.
- Alternatives considered: We considered passing in a ```String``` for the file path that indicates the location of the picture to upload as a way of selecting the picture.
- Pros: Users only needs to type.
- Cons: File paths can be very lengthy and if their file names are similar it is very easy to make a mistake when typing it out.

**Aspect: Proccessing of Images**
- Current Implementation: Handled by functions in the ImageStorage Class.
- Pros: All operations regarding choosing, uploading and validating the picture is done in the same class.
- Cons: The ImageStorage Class becomes just a class of static functions which cannot be tested.

**Aspect: Changing an existing Image**
- Current Implementation: User just uses ```upload-pic``` command for student they want to change the picture of and reselcts the picture.
- Pros: Single command word to add and edit picture, convenient to use.
- Cons: Users might accidentally upload the image for the wrong student and there is no way to undo the change.
- Alternatives considered: We have considered having a separate ```update-pic``` command solely for changing an existing picture of a student.
- Pros: Clearer instruction and prevents error from user.
- Cons: User will have to be more familiar with more commands.

### Add/delete Task feature
The add/delete `Task` feature allows users to create and remove tasks. This feature uses the following commands:
* `task` t/TITLE d/DESCRIPTION
* `remove-task` INDEX

which invokes the `TaskCommand` and the `RemoveTaskCommand` respectively.
These commands when executed will use methods exposed by the `Model` and `TaskBookStorage` interface and perform the related operations.

#### About Task
Each `Task` has non-optional title and description fields. Future iterations may introduce new types of `Task`, including `Deadline` and `Assignment`.
Currently, task information is stored in a different file from student information as they are two separate (and unrelated) data types.

The following is a more detailed explanation on how the `TaskCommand` works.
1. If the title or description fields are missing or invalid, a 'ParserException' will be thrown and the new `Task` will not be added.
2. After the successful parsing of user input into `TaskCommandParser`, the `TaskCommand` object is created.
3. Following which, `TaskCommand#execute(Model model)` method is called which eventually calls the `TaskList#add(Task toAdd)` method, adding the new `Task` object to the internal list.
4. Next, the `TaskBookStorage#saveTaskBook(ReadOnlyTaskBook taskBook)` method is called, which serializes each `Task` in the updated `TaskBook` and writes them to the `taskbook.json` file at the predefined relative path.
5. Lastly, if the `TaskBook` has been saved without problems, a new `CommandResult` will be returned with the success message.

![AddTaskSequenceDiagram](images/AddTaskSequenceDiagram.png)

Sequence diagram for TaskCommand

![AddTaskActivityDiagram](images/AddTaskActivityDiagram.png)

Activity diagram for TaskCommand

The following is a more detailed explanation on how the `RemoveTaskCommand` works.
1. If the task index specified is invalid, a `ParserException` will be thrown and the specified `Task` will not be removed.
2. After the successful parsing of user input into `RemoveTaskCommandParser`, the `RemoveTaskCommand` object is created.
3. Following which, `RemoveTaskCommand#execute(Model model)` method is called which eventually calls the `TaskList#remove(Task toRemove)` method, removing the specified `Task` object from the internal list.
4. Next, similar to `TaskCommand`, the `TaskBookStorage#saveTaskBook(ReadOnlyTaskBook taskBook)` method is called, which serializes each `Task` in the updated `TaskBook` and writes them to the `taskbook.json` file at the predefined relative path.
5. Lastly, if the `TaskBook` has been saved without problems, a new `CommandResult` will be returned with the success message.

#### Design considerations:

**Aspect: Storage for `TaskBook`**
- Current implementation: A totally new storage class, serializer class and data file specifically for `Task`
- Pros: Easy to distinguish different classes handling different types of data (`Student` vs `Task`)
- Cons: Some classes and methods are similar across `AddressBook` and `TaskBook`
- Alternatives considered: We considered integrating `TaskBook` into the given `AddressBook` infrastructure, meaning that we will be storing `Task` data together with `Student` data into `addressbook.json`
- Pros: Easier to implement, less code to write
- Cons: Higher coupling, since any change in `TaskBook` could potentially affect `AddressBookStorage`

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Enhanced\] ***Add*** Feature

This feature was enhanced to help teacher assistants add students' profiles with ease.
The feature uses the command :
* `add` n/NAME, i/STUDENTID, [p/PHONE], [e/EMAIL], [c/CLASSGROUP], [t/TAGS]


#### Feature Updates
* ~~Compulsory~~ Optional Fields to Fill in (Fields in Square Bracket are Optional).
* ***Only*** Name and Student ID are a must.
* Provides a **more flexibly way** of adding students' profiles.

The improved feature allows user to leave certain fields empty if they do not have the information to fill them.

#### The `add` Command Implementation:

The following is a more detailed explanation on how the new `add` feature works.

1. The `AddressBookParser` will select `AddCommandParser` and parse the user input.
2. `AddCommandParser` checks for optional fields that are not filled in and will automatically set them to 'NA' in the Addressbook.
3. Following which, `AddCommand#execute(Model model)` method is called which adds the students into the Addressbook.
4. If the student's data is already there, the input will throw an error saying "This student already exists."

![AddCommand Sequence Diagram](images/AddCommandSequenceDiagram.png)
Sequence Diagram for Improved AddCommand Feature
![AddCommand Activity Diagram](images/AddCommandActivityDiagram.png)
Activity Diagram for Improved AddCommand Feature

#### Design considerations

**Aspect: Wrapping 'NA' into Type X**
* Current implementation : Making 'NA' into a new X type when Information for X is not available where X can be Email, Phone or Class Group Type.
* Pros: Simple to Store and Understand, works as intended even if users decide to input 'NA' into these optional fields.
* Cons: Not exactly logically correct as NA is not of Type X.


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

**Target user profile**:

* Tech savvy university teacher assistants
* has a need to manage student contacts
* Prefer desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**:
* Teacher assistant have a lot of students information to manage and our product will make it easier for teacher assistants to remember student information, such as names, faces, attendance, etc
* Teacher assistants also want to ensure their students are doing well and our product will ensure that they can keep track of student's performance

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                     | I want to …​                                              | So that I can…​                                       |
|----------|-----------------------------|-----------------------------------------------------------|-------------------------------------------------------|
| `* * *`  | teacher assistant           | be able to add comments                                   | monitor my students' progress.                        |
| `* * *`  | teacher assistant           | be able to retrieve my student's contact details          | look them up easily.                                  |
| `* * *`  | teacher assistant           | be able to recognise my students from different tutorials | identify them.                                        |
| `* * *`  | teacher assistant           | track the date and time while using my app                | so that I can be on time for my classes.              |
| `* * *`  | forgetful teacher assistant | record attendance                                         | keep track of my students.                            |
| `* * *`  | teacher assistant           | add student's profiles flexibly                           | create profiles based on limited student information. |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `GREWZ` and the **Actor** is the `teacher assistant`, unless specified otherwise)

**Use case: UC01 - Add a Student**

**Guarantees:**  A student contact is added to GREWZ.

**MSS**

1. Tutor adds the student by entering the command with student details.
2. GREWZ adds student.
Use case ends.

**Extensions**

* 1a. GREWZ detects an error in entered data.
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with student details
    * Repeat steps until data is correct
    Use case resumes in step 2

**Use case: UC02 - Delete a Student**

**Guarantees:** A student contact is deleted from GREWZ.

**MSS**
1. Tutor deletes a specific student contact by entering the command along with the index that corresponds to the student.
2. Student contact is deleted.
Use case ends

**Use case: UC03 - Edit a Student**

**Guarantees:** Student's details are changed in GREWZ

**MSS**
1. Tutor edits a specific student contact by entering the command along with the index that corresponds to the student.
2. Student contact in GREWZ is edited
Use case ends

**Extension**
* 1a. GREWZ detects that the index given is invalid
    * 1a1. GREWZ responds with an error message
    * 1a2. User re-enters the command with valid index
    Use case resumes in step 2

**Use case: UC04 - Add attendance to Student**

**Guarantees:**  An attendance is added to the Student.

**MSS**
1. Tutor adds attendance to student by entering command with student index, module and number of lessons.
2. GREWZ adds attendance to student.
Use case ends.

**Extensions**
* 1a. GREWZ detects an error in entered data.
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with student details
    * Repeat steps until data is correct
    Use case resumes in step 2

**Use case: UC05 - Delete attendance of a Student**

**Guarantees:**  An attendance is deleted from the Student.

**MSS**
1. Tutor deletes attendance to student by entering command with student index.
2. GREWZ deletes the attendance of the student.

**Extensions**
* 1a. GREWZ detects an error in entered data.
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with correct student index and command
    * Repeat steps until data is correct
    Use case resumes in step 2

**Use case: UC06 - Mark attendance of Student**

**Guarantees:**  An attendance is deleted from the Student.

**MSS**
1. Tutor marks attendance of student by entering command with student index, lesson number, marked value.
2. GREWZ marks/unmarks the attendance of the student.

**Extensions**
* 1a. GREWZ detects an error in entered data or attendancelist is not present
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with correct student index and data
    * Repeat steps until data is correct
    Use case resumes in step 2

**Use case: UC07 - Find a Student**

**Guarantees:**  A Tutor can search to find Student details with limited information.

**MSS**
1. Tutor searches for student details by entering command with keywords.
2. GREWZ returns a list of students whose details match the keywords, completely or partially.
   Use case ends.

**Use case: UC08 - Add class group to student**

**Guarantees:**  A class group is added to the Student.

**MSS**
1. Tutor adds class group to student by entering command with index of student and class group.
2. GREWZ adds class group to student.
Use case ends.

**Extensions**
* 1a. GREWZ detects an error in entered index.
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with student details
    * Repeat steps until data is correct
    Use case resumes in step 2

**Use case: UC09 - Add a ToDo Task**

**Guarantees:** A tutor can add a ToDo task to the TaskBook with a given title and description.

**MSS**
1. Tutor enters correct command with title and description into the command input.
2. GREWZ adds the ToDo task to the TaskBook and displays it.
Use case ends.

**Extensions**
* 1a. GREWZ detects an error in the given command format.
    * 1a1. GREWZ responds with an error message
    * 1a2. User re-enters the command with title and description
    * Repeat steps until command input is correct
    Use case resumes in step 2

**Use case: UC10 - Add a Deadline Task**

**Guarantees:**  A tutor can add a task to the TaskBook along with a given deadline.

**MSS**
1. Tutor adds task to student by entering command with title, description and deadline.
2. Task is added to the TaskBook and the deadline is displayed as well.
   Use case ends.

**Extensions**
* 1a. GREWZ detects an error in the given date format.
    * 1a1. GREWZ responds with an error message
    * 1a2. User enters command with corrected date format.
    * Repeat steps until data is correct
      Use case resumes in step 2


**Use case: UC11 - Uploading a Student Picture**

**MSS**
1. Tutor enters a upload picture command with the index of student.
2. File chooser is opened.
3. Tutor selects picture to upload.
4. Picture is uploaded and saved in images folder.
5. Use case ends

**Extensions**
* 1a. GREWZ detects an invalid index.
  * 1a1. GREWZ responds with an error message.
  * 1a2. User enters command with corrected index.
  * Repeat steps until index is correct.
    Use case resumes in step 2

* 3a. Picture is not of JPG format.
  * 3a1. GREWZ detects invalid file.
  * 3a2. GREWZ responds with an error message.
  Use case ends

**Use case: UC12 - Remove a Task**

**Guarantees:** A tutor can remove a task from the TaskBook with the given index.

**MSS**
1. Tutor removes task from TaskBook by entering command with given task index.
2. GREWZ removes the specified task from TaskBook.

**Extensions**
* 1a. GREWZ detects an invalid task index.
  * 1a1. GREWZ responds with an error message
  * 1a2. User enters command with correct task index
  * Repat steps until index is valid
  Use case resumes in step 2


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  User has to have a basic grasp of English as other languages are not supported.

*{More to be added}*

### Glossary
* **CLI**: Command Line Interface
* **CLI**: Graphical User Interface
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Command**: 

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
