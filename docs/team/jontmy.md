---
layout: page
title: Jonathan Tay's Project Portfolio Page
---

### Project: ModtRekt

ModtRekt is a desktop app which helps undergraduate students plan their modules over their course
of study, and manage their tasks and deadlines for each of them.

ModtRekt is optimized for use via a CLI while still having the benefits of a GUI.

Given below are my contributions to the project.

* **New Feature**: Implemented task and deadline archival (marking as done) via the commands `done task` and `undone task`.
  * What it does: Allows user to mark tasks and deadlines as done or undone, with its status reflected as a badge in the UI.
  * Justification: This feature allows the user to keep track of which tasks and deadlines they have completed, and which ones they have yet to complete.
  * Highlights: The done tasks and be hidden or shown with the `list task` command.

* **New Feature**: Implemented task and deadline prioritization and associated sub-command `edit -p`.
    * What it does: Allows user to prioritize tasks and deadlines, which are then displayed in the GUI in order of priority (none, low, medium, high).
    * Justification: This keeps the user aware of the most important tasks and deadlines, and allows them to focus on them first.
    * Highlights: This feature is implemented using a `Priority` enum, which is used in both `Task` and `Deadline` classes.
    * Credits: [Jun Hao](https://github.com/hojunhao2000) for the base `edit task` command, prior to which the command was implemented separately as `prioritize`.

* **Major Enhancement**: Redesigned the module UI.
  * What it does: Replaced the inherited AB3 UI with a new UI that displays modules and tasks neatly with support for badges such as "done" and "selected".
  * Justification: The visual design of the app is important to the user experience, and the old AB3 UI was not suitable for ModtRekt.
  * Highlights: The new module list is much more aesthetically pleasing with consistent spacing and typography.
  * Credits: [Marco](https://github.com/midnightfeverrr) for designing the initial mockup in Figma, [Jun Hao](https://github.com/HoJunHao2000) for implementing the `Module` class which the UI depends on.

* **Major Enhancement**: Implemented the `list task` command.
  * What it does: `list task` shows all undone tasks, and `list task -a` shows _all_ tasks.
  * Justification: This allows the user to hide completed tasks from the UI, which can be useful if the user has a lot of tasks.
  * Credits: AB3 codebase for the original `list` command.

* **Major Enhancement**: Refactored the command parser to use the JCommander library.
  * What it does: Replaced command parsing in `ModtrektParser` via AB3's `ArgumentTokenizer` with JCommander.
  * Justification: The AB3 parser was not suitable for ModtRekt's Unix-style commands, which have many optional arguments. JCommander is a mature and more suitable library for parsing commands.
  * Highlights: JCommander does the heavy lifting of parsing commands via Java annotations, and every `Command` class no longer requires an accompanying `Parser` class.
  * Credits: [Cedric Beust](https://github.com/cbeust) for creating the [JCommander](https://jcommander.org/) library.

* **Major Enhancement**: Introduced the idea of command scoping in our commands.
  * What it does: AB3 commands were a single command word, so I added a second word `task` or `module` depending on the command's scope to unify different commands.
  * Justification: This makes the commands more consistent and easier to understand what it affects.
  * Highlights: Prior to this, most commands were unclear as to what they affected, and some commands were ambiguous (e.g. `done` and `archive`).
  * Credits: [All other team members](https://github.com/orgs/AY2223S1-CS2103T-W10-4/people) for refactoring the commands to use the new scoping rule.

* **Major Enhancement**: Improved the typography and styling for the user guide.
  * What it does: The user guide now has consistent spacing and a new color scheme.
  * Justification: This improves the readability of the user guide.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jontmy&breakdown=true&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**: Reviewed pull requests opened by other team members, tested their features, and suggested changes where necessary.

* **Documentation**:
  * Added the section "Marking tasks as done" to the developer guide.
  * Added the section "Task listing" to the developer guide.
  * Added the section "General command syntax" to the user guide.
  * Updated the command summary in the user guide for accuracy.

* **Community**: Thoroughly tested [another team's project](https://github.com/AY2223S1-CS2103T-T12-2/tp), reporting bugs found with screenshots and detailed instructions on how to reproduce them [here](https://github.com/jontmy/ped/issues).

* **Other contributions**: Demonstrated ModtRekt in the accompanying module CS2101 in week 12.
