---
layout: page
title: Rama Aryasuta Pangestu's Project Portfolio Page
---

## Project: UniNurse

[UniNurse](https://ay2223s1-cs2103t-t12-4.github.io/tp/) is a desktop application used for managing patient contact details and tasks.
It is targeted at private nurses to help them manage their patients in a more organized and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

## Contributions to project

#### New features

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rama-pang&breakdown=true).

* **New feature**: Added the ability to undo or redo a command.
  * What it does: undoing a command reverts all data to a state before the last command which modifies it, and redoing a command is reverting an undo command.
  * Justification: undoing and redoing commands are common features in all applications to prevent accidental usage of an incorrect command, by making it reversible.
  * Implemented `PersistentUninurseBook`, a wrapper class around a list of past versions of `UninurseBook`s, allowing the user to undo (or redo) to a previous state.

* **New feature**: Added the ability to specify options to a command to modify its behaviour depending on the options.
  * What it does: commands can now take option flags which are specified before command parameters which modify the command's behaviour, allowing each command word (such as `add`, `edit`, `delete`, and `view`) to be used for different purposes.
  * Justification: each command word can be reused for different purposes, making it more intuitive and easy to remember.
  * Implemented `ParserUtil` methods to parse the options in a command, and `XYZGenericCommandParser` which contains the behaviour of the `add`, `edit`, `delete`, and `view` commands depending on whether the user specifies certain options e.g. the `EditGenericCommandParser` will call `EditPatientCommandParser` if only the patient index option is specified, but will call `EditTaskCommandParser` if the patient index and task index option is specified.

* **New feature**: Added the ability for `find` command to search for attribute-specific keywords.
  * What it does: `find` command can search for attribute-specific keywords e.g. only search certain keywords in `NAME` attributes.
  * Justification: allow users to search for specific patients by their attributes.
  * Implemented `PatientMatchPredicate`, which tests whether a patient matches the specified keywords in the `find` command, and `XYZContainsKeywordsPredicate` which tests whether a specific attribute of a patient contains the attribute-specific keywords.

* **New feature**: Added the ability to navigate through the command history by using the `↑` and `↓` arrow keys.
  * What it does: using the `↑` and `↓` arrow keys will replace the text in the `CommandBox` with the previous/next command.
  * Justification: enhance the user experience by allowing the user to reuse previous commands more easily without retyping the command.

* **Enhancements to existing features**:
  * Modified and generalized the `clear` command to only delete currently-displayed patients.
  * Modified recurring tasks' frequency to recognize singular/plural forms.

* **Contributions to User Guide**:
  * Added the glossary.
  * Added documentation for `undo`, `redo`, `find`, and `clear` commands.
  * Added documentation for the ability to navigate through the command history by using the `↑` and `↓` arrow keys.
  * Added the patient parameter summary section.
  * Added more explanation on editing a patient's multi-valued attributes.
  * Fix un-centered images and broken hyperlinks.
  * Various minor grammatical fixes, consistency, and improvements.

* **Contributions to Developer Guide**:
  * Updated the documentation and implementation for the `undo`/`redo` feature.
  * Added use case for `undo`/`redo` feature.
  * Updated several diagrams to include the implementation for the `undo`/`redo` feature.
