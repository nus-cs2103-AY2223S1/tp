---
layout: page
title: Dominic Berzin's Project Portfolio Page
---

### Project: ModtRekt

ModtRekt is a desktop app for managing modules and tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Task-related commands such as **Add** and **Remove**
  * What it does: Allows a user to add a task to a specified module, or delete unnecessary tasks.
  * Justification: A necessary feature for the application to function as intended, to allow a user to add tasks that
  are specific to a module, and to allow removal of completed tasks.
  * Highlights: Extensive modification and refactoring of AB3's original
  person class, including class variables and command flags.
  * Credits: Original AB3 for codebase as a reference

* **New Feature**: Link modules and tasks
  * What it does: Link a specific task to a module.
  * Justification: Necessary feature in order for the user to be able to view tasks by modules. Also enables a relationship
  between task and module commands, for example, marking a module as done would mark all existing tasks as done. Additionally,
  deleting a module would delete all its active task's as well.
  * Highlights: Module was initially implemented as an instance variable for each task, but this
  has since been modified to utilise a ModCode instead.
  * Credits: [Jun Hao](https://github.com/hojunhao2000) for the further modifications to this feature.

* **New Feature**: Ability to add module only via module code
  * What it does: Allows a user to add a module by only specifying its module code,
  and module information such as name and credits are obtained directly from NUSMods.
  * Justification: Enhances the user experience as adding a module now requires only a module code,
  and the rest of the information is provided through the backend logic.
  * Highlights: HTTP requests were utilised to obtain information from NUSMods API.
  * Credits: NUSMods for their public API and module information.

* **Major Enhancement**: Add a fallback file containing AY2022-2023 module information
  * What it does: Allows the user to utilise the shorthand add module command (i.e. `add module CS2103T`), even
  if their device has no internet connection, or if the request to fetch data from NUSMods fails.
  * Justification: This was implemented in order to prevent our application to be reliant on an external API
  or server, and to ensure our user can still enjoy the benefits of the shorthand command in the event of an unforeseen
  error.
  * Highlights: Fallback file data contains only the module title and module credits, and is stored as a JSON
  file. This data is obtained via the module code, which acts as a key for the data.
  * Credits: NUSMods for their public API and module information.
  
* **Major Enhancement**: Modify the command parser to be simpler, and to utilise JCommander, instead of a parser based on the original AB3
  codebase.
  * What it does: Simplifies the command usage for the user, and returns descriptive
  error messages in the event of an invalid command.
  * Justification: The original AB3 codebase utilised flags such as `\n` for their commands, and this resulted in some commands
  being extremely lengthy. The commands were then simplified to utilise flags with a dash, such as `-p`, and were also shortened
  to require as little flags as possible, while providing the same amount of functionality.
  * Highlights: Some commands do not even require flags, for example, `add module CS2103T`.
  * Credits: [Jonathan](https://github.com/jontmy) for introducing and implementing the JCommander skeleton

* **Minor Enhancement**: Allow case-insensitive module codes in commands, so the user can enter a module code as `CS2103T`, 
`cS2103t` or `cs2103t`, and the command would work in the same manner.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=domoberzin&breakdown=true&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**: Reviewed pull requests made by other team members and gave feedback, requesting changes where necessary.

* **Community**: Descriptively reported bugs, with images and steps to replicate where possible,
for another team and gave suggestions for improvements. Bug reports can be found [here](https://github.com/domoberzin/ped/issues)

* **Other contributions**: 
  * Part of the team that conducted the product demonstration.

<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Added pictures to illustrate the execution of every command in our application to the User Guide.
    * Added a glossary section to the User Guide, to educate the reader on certain terminology that may be foreign to them.
    * Added a section on how module data is automatically fetched from NUSMods via module code.
    * Added sections on the commands for adding and removing tasks.
  * Developer Guide:
    * Added a section on the implementation of the task-related commands.
    * Added information on how NUSMods API and the fallback files are used for module information.
    * Added use cases and user stories
  
