---
layout: page
title: Wu Changjun's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a **desktop app for contact and task management**. It aims to help NUS SoC students stay better connected to their school life, in terms of social connections and school tasks. SoConnect is also **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 42 kLoC.


## Contribution

### New Autocomplete Feature: Autocomplete on Search Functionality

**What it does**: Provides a list of autocompleted search entries based on what the user has typed in the command box, this allows the user to choose the autocompleted entries and search without having to type the full command.

**Justification**: This feature improves the user productivity significantly because the user will not need to type the full command upon searching, this will also reduce the mistakes that the user might make when entering a long search command.

**Highlight**: This enhancement affects the user interface. It required an in-depth analysis on how the autocomplete entries are generated and how the command box should behave when the user clicks it or enters certain keys. The implementation was challenging as it requires some knowledge on JavaFX and how autocomplete works.

### New Todo Feature: Date feature for Todo

**What it does**: Allows user to add a date to their todos.

**Justification**: This helps the users to manage their todos better as they know when to finish their todos. They can also filter and view the todos that they have between any two dates so they can better plan their schedule.

**Highlight**: This enhancement affects the `todo add`, `todo edit` and `todo show` commands. It required an in-depth analysis of design alternatives. This implementation was challenging as it required changes to other `todo` commands.

### Enhancements Implemented:
* Revamped the GUI to include the `todos`.

### Code Contribution
As of 7/11/2022, I have contributed 3562 LoC. [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ugholaf&breakdown=true)


### Project Management
* In charge of code quality

**Documentation**:
* User Guide:
  * Added documentation for `Layout` section.
  * Added images for all the commands.
  * Added documentation for the `autocomplete` feature.
  * Updated documentation for `todo show`.
* Developer Guide:
  * Added implementation details for the `autocomplete`.
  * Added UML sequence diagram for `autocomplete`.
  * Added UML activity diagram for `autocomplete`.
  * Updated existing UML diagrams from AddressBook Level-3.

**Community**:
* 20 Pull Requests reviewed. [Pull Requests Review link](https://github.com/AY2223S1-CS2103T-W15-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AUgholaf)
* Posted 10 bug reports and suggestions for other teams. [Bug Reports](https://github.com/Ugholaf/ped/issues)
