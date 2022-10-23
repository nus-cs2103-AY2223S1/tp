[![CI Status](https://github.com/AY2223S1-CS2103T-T14-2/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2223S1-CS2103T-T14-2/tp/actions/workflows/gradle.yml)
[![Code coverage](https://codecov.io/gh/AY2223S1-CS2103T-T14-2/tp/branch/master/graph/badge.svg?token=HGCQQ52YOS)](https://codecov.io/gh/AY2223S1-CS2103T-T14-2/tp)

![Ui](docs/images/Ui.png)

# About

Have you ever been struggling with a coding assignment for days, wanting to reach out to a friend, but not knowing who?
Do you ever get overwhelmed with tasks, not knowing which to prioritize?

CodeConnect solves both these problems at once by integrating your contacts with your tasks! CodeConnect is a command based application written in Java that runs locally in your computer. Our motivation was to provide a simple and friendly solution to the hectic life of computing students.
CodeConnect allows you to quickly manage your tasks and contacts through a text based command line interface, with the option of using your mouse for quicker navigation. Tasks and contacts can be easily searched for and sorted to, to provide enhanced organization and management functionality.

## Features

* Keep track of your tasks, their deadlines, and the modules they belong to
  * Mark them as complete or uncompleted
  * Have your contacts and tasks all listed out in a specified order!!
* Manage your contacts and keep track of the modules they are taking

## Usage


### Finding tasks: `find`

Finds tasks from your task list via keywords, or via module. 

Format:
`find /t {task}`, `find /m {module}`

Examples:
* `find /t homework` returns `Science homework`, `Math homework`
* `find /m CS1101S` returns `Problem set 4`, `Reading assignment 2`

### Adding a task: `add`

Adds a task to the task manager.

Format:
`add {task_name} /by {deadline} /mod {module_code}`

Examples:
* `add finish problem set 5 /by Week 6 Sunday /mod CS2040S`
* `add finish SEP application /by 2359 today`

### Deleting a task: `del`

Deletes a task from the task manager list.

Format:
`del {task_index}`

Example:
`del 5`

### Marking a task: `mark`

Marks a task as completed in the task manager list.

Format:
`mark {task_index}`

Example:
`mark 3`

### Unmarking a task: `unmark`

Marks a task as incomplete in the task manager list.

Format:
`unmark {task_index}`

Example:
`unmark 3`

### Listing tasks: `list`

Listing all tasks either in the order of the most recent task added or timing of deadline.

Format:
`list / list time`

Examples:
* `list` (order of most recent task added)
* `list time` (order of timing of deadline)

### Adding a contact: `addc`

Adds a contact to the contact list.

Format: `addc {name} /email {email} /hp {phone number} /mods {module1} {module2}...`

Examples:
* `addc Bob Martin /email bobbymartini@gmail.com /hp 98765432 /mods CS1101S CS1231S`
* `addc Betsy Crowe /email betsycrowe@gmail.com /hp 89985432`

### Deleting a contact: `delc`

Deletes the specified contact from the contact list.

Format: `delc {contact_index}`

Example:
* `delc 2` deletes the contact at index 2 in the contact list.

### Editing a contact : `editc`

Edits the specified contact from the contact list.

Format: `editc {contact_index} {field prefix + field description}`

Examples:
* `editc 2 n/ John Wong` edits the name of the contact at index 2 in the contact list to `John Wong`.

### Finding contacts: `findc`

Find persons in your contacts via keywords, or via module.

Format:
`findc /n {name}` `findc /m {module}`

Examples:
* `findc /n John` returns `john`, `John Doe`
* `findc /m CS1231S` returns `Alex Yeoh`, `David Li`

### Listing contacts: `listc`

Listing out all contacts in the contact list.

Format:
`listc`

Examples:
* `listc`
