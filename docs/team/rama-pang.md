---
layout: page
title: Rama Aryasuta Pangestu's Project Portfolio Page
---

## Project: UniNurse

UniNurse is a desktop application used for managing patient contact details and tasks. The user interacts with it using
a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Contributions to project

### New feature: added the ability to undo or redo a command

Added the ability to undo or redo a command
* What it does: undoing a command reverts all data to a state before the last command which modifies it, and redoing a command is reverting an undo command.
* Justification: undoing and redoing commands are common features in all applications to prevent accidental usage of an incorrect command, by making it revertible.
* Highlights:
* Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

### New feature: added option-flags in commands to modify its behaviour

Added the ability to specify options to a command to modify its behaviour
* What it does: commands can now take option-flags which are specified before command parameters which modifies the command's behaviour, allowing each command word to be used for different purposes
* Justification: each command word can be reused for different purposes, making it more intuitive and easy-to-remember
* Highlights:
* Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

### Code contributed
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rama-pang&breakdown=true)

### Enhancements to existing features

* Added the ability to navigate through the command history by using the `↑` and `↓` arrow keys.
* Improved the `find` command to search through keywords in a patient's different attributes
* Modified the `clear` command to only delete currently-displayed patients.

### Contributions to User Guide

* Added the glossary.
* Added documentation for `undo` and `redo` commands.
* Added documentation for the `find` command.
* Added documentation for the `clear` command.
* Added documentation for the ability to navigate through the command history by using the `↑` and `↓` arrow keys.

### Contributions to Developer Guide

* Updated the documentation and implementation for the `undo` and `redo` commands.

### Contributions to team-based tasks

### Contributions beyond project team
