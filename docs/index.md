---
layout: page
title: AddressBook Level-3
---

[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/se-edu/addressbook-level3/actions)
[![codecov](https://codecov.io/gh/se-edu/addressbook-level3/branch/master/graph/badge.svg)](https://codecov.io/gh/se-edu/addressbook-level3)

![Ui](images/Ui.png)

**AddressBook is a desktop application for managing your contact details.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

* If you are interested in using AddressBook, head over to the [_Quick Start_ section of the **User Guide**](UserGuide.html#quick-start).
* If you are interested about developing AddressBook, the [**Developer Guide**](DeveloperGuide.html) is a good place to start.

## Features

* Keep track of your tasks, their deadlines, and the modules they belong to
  * Mark them as complete or uncompleted

## Usage

### Adding a task: add

Adding a task to the task manager.

Format:
`add {task_name} /by {deadline} /mod {module_code}`

Examples:
`add finish problem set 5 /by Week 6 Sunday /mod CS2040S`
`add finish SEP application /by 2359 today`

### Deleting a task: del

Deleting a task from the task manager list.

Format:
`del {task_index}`

Example:
`del 5`

### Marking a task: mark

Marking a task as completed in the task manager list.

Format:
`mark {task_index}`

Example:
`mark 3`

### Unmarking a task: unmark

Unmarking a task as incomplete in the task manager list.

Format:
`unmark {task_index}`

Example:
`unmark 3`

**Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)
