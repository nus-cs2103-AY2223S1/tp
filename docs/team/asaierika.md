---
layout: page
title: Jiang Zhimeng's Project Portfolio Page
---

### Project: myStudents

myStudents helps tuition center admins manage details of the students, tutors and classes. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. 

It is a brown-field project evolved based on [AB3](https://github.com/nus-cs2103-AY2223S1/tp). Compared to AB3 that only manipulates one type of entity `Person`, myStudent needs to manipulate three
types of entities: `Student`, `Tutor` and `TuitionClass`. To adapt to the three different entities, myStudent has made enhancements to the existing structure and commands of AB3. Additionally, myStudent has also implemented some new commands and new GUI features.

Given below are my contributions to the project.
 
* **Code contributed**: 
  * [Github](https://github.com/asaierika/tp)
  * [Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=asaierika&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=asaierika&tabRepo=AY2223S1-CS2103T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management**:
  * In charge of the `Ui`.
  * Set up the team Organisation.
  * Set up the team Repository. 
  * Managed release [v1.3.1](https://github.com/AY2223S1-CS2103T-F12-4/tp/releases/tag/v1.3.1).

* **New features implemented**:
  * Introduced the enum class `ListType` in `Model` which keeps track of the current list type [#49](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/49).
    * This feature is crucial for our project, since myStudent monitors three types of entities: `Student`, `Tutor` and `TuitionClass`. 
Most of the commands such the delete command and the show command needs to refer to or modify the current list type. 
The introduction of `ListType` marks 
an evolution from a single type of entity in AB3 to multiple types of entities in myStudent.
  * Implemented the `show command` which displays the descriptions of the specified `Student` or `Tutor` [#103](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/103).
    * The description of the `Student` and `Tutor` is not shown in the list panel. The `show command` displays the description 
of the specified `Student` or `Tutor` in a separate description panel. 
    * The `show command` is closely related to `Ui` as it makes use of the `SelecetionMode` of `ListView` in JavaFX.
  * Implemented clickable list tabs [#81](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/81).
    * The user can click at the list tabs to switch to a different list, which supplements the `list command`.
  * Implemented clickable person cards [#122](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/122).
    * The user can click at the `student card` or `tutor card` to display the description of the `student` 
  or `tutor`, which supplements the `show command`.
  * Implemented different themes for the GUI. The user is able to change the color scheme [#122](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/122).
  * Implemented opening and exiting animations for the GUI [#122](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/122).

* **Enhancements to existing features**:
  * Modified the `list command` [#49](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/49).
    * The previous `list command` in AB3 is only for one type of entity `Person`. To suite the multuple types of entities in myStudent, the modified `list command` allows the user to display
one of the three different lists, the `student list`, `tutor list` or `class list`.
  * Modified the `delete command`[#62](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/62).
    * The previous `delete command` in AB3 is only for one type of entity. The modified `delete command` deletes one of the three entities depending on the
current list, which can be one of the three lists: `student list`, `tutor list` and `class list`.
  * Modified the `CommandResult` class. Introduced enum class `CommandType` to account for the type of the command that is executed [#103](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/103).
    * The previous `CommandResult` allows the `Ui` to handle the `help command` and the `exit command`. The modified `CommandResult` class
allows the `Ui` to handle more `Ui` changes for more command types.
  * Helped in making the `Person` class abstract [#107](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/107).
    * The `Person` class in AB3 is concrete. In the earlier stage of our project, we accommodated our codes to `Person` class as we did not wish
to break the pre-existing functionalities of AB3, which leads to dependencies in the `Logic`, `Storage` and `Ui`. In latter stage we decided to make `Person` class abstract, before which the dependencies need to be cleaned.

* **Documentation**:
  * User Guide:
    * Added all diagrams for the User Guide.
    * Added the Layout section.
    * Added documentation for the show command.
  * Developer Guide:
    * Added documentation for the list type feature.
    * Added the sequence diagram for the `list command` in the list type feature.
    * Updated the class diagram of the `Ui`.
    * Updated the sequence diagram for the `delete command` in the `Logic`.

* **Community**:
  * PRs reviewed can be found [here](https://github.com/AY2223S1-CS2103T-F12-4/tp/pulls?q=is%3Apr+commenter%3Aasaierika).
  * Bugs reported for other people's project can be found [here](https://github.com/asaierika/ped/issues).
