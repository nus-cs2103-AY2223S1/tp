---
layout: page
title: Rama Aryasuta Pangestu's Project Portfolio Page
---

### Project: UniNurse

UniNurse is a desktop application used for managing patient contact details and tasks. The user interacts with it using
a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

#### New features

* **Added the ability to undo or redo a command.**
  * What it does: undoing a command reverts all data to a state before the last command which modifies it, and redoing a command is reverting an undo command.
  * Justification: undoing and redoing commands are common features in all applications to prevent accidental usage of an incorrect command, by making it revertible.
  * Implemented `PersistentUninurseBook`, a wrapper class around a `List<UninurseBookSnapshot>` which stores past versions of `UninurseBook`, allowing the user to undo (or redo) to a previous state.
  * Implemented `UninurseBookSnapshot`, a wrapper class around a `UninurseBook` and a `CommandResult` which is a snapshot of the current state after a command is executed.

* **Added the ability to specify options to a command to modify its behaviour depending on the options.**
  * What it does: commands can now take option-flags which are specified before command parameters which modifies the command's behaviour, allowing each command word (such as `add`, `edit`, `delete`, and `view`) to be used for different purposes.
  * Justification: each command word can be reused for different purposes, making it more intuitive and easy-to-remember.
  * Implemented `ParserUtil#parseOptions`, `ParserUtil#eraseOptions`, and related methods, to parse the options in a command.
  * Implemented `XYZGenericCommandParser` which contains the behaviour of the `add`, `edit`, `delete`, and `view` commands respectively, if the user specifies certain options e.g. the `EditGenericCommandParser` will call `EditPatientCommandParser` if only the patient index option is specified, but will call `EditTaskCommandParser` if the patient index and task index option is specified.

* **Added the ability for `find` command to search for attribute-specific keywords.**
  * What it does: `find` command can search for attribute-specific keywords e.g. only search certain keywords in `NAME` attributes.
  * Justification: allow users to search for specific patients by their attributes.

* **Added the ability to navigate through the command history by using the `↑` and `↓` arrow keys.**
  * What it does: using the `↑` and `↓` arrow keys will replace the text in the `CommandBox` to the previous/next command.
  * Justification: enhance the user experience by allowing the user to reuse previous command more easily without retyping the command.

#### Code contributed

* You may visit the following [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rama-pang&breakdown=true).

#### Enhancements to existing features

* Modified and generalized the `clear` command to only delete currently-displayed patients.
* Various bug fixes reported internally or from PE-D.

#### Contributions to User Guide

* Added the glossary.
* Added documentation for `undo`, `redo`, `find`, and `clear` commands.
* Added documentation for the ability to navigate through the command history by using the `↑` and `↓` arrow keys.
* Added the patient parameter summary section.
* Added more explanation on editing a patient's multi-valued attributes.
* Various minor fixes and improvements.

#### Contributions to Developer Guide

* Updated the documentation and implementation for the `undo` and `redo` feature.
* Added use case for `undo`/`redo` feature.
* Updated several diagrams to include the implementation for the `undo`/`redo` feature.
