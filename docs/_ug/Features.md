<!-- markdownlint-disable-file first-line-h1 -->
This section covers how to use each command in detail.
Before continuing, ensure you have read the section on [Flags](#flags) and [Placeholders](#placeholders).

What you should expect to find:

* A description of the command
* The expected behaviour for the command
* A few valid and invalid examples of the command
* Important points to note

**IMPORTANT:**

* Square brackets indicate an optional parameter.
* For each command, "Format" indicates the syntax of the command.

### Item Features

{% include_relative _ug/features/ItemFeatures.md %}

### Tag Features

{% include_relative _ug/features/TagFeatures.md %}

### Receive help during usage

Command: `help [COMMAND_WORD]`

> Description: Displays a help message for this application.

Example:
```text
help
help delete
held help
```

Output:

A new window will open showing instructions.

If no `COMMAND_WORD` was specified, only a general help will be provided. 
The general help shows a list of commands available to the user and a URL to this User Guide.

If a `COMMAND_WORD` was specified, additional help for that command will be provided in addition to the general help.

--- 

### Reset the application

### Exit the application

Command: `bye`

> Description: Exits FoodRem program.

---

Example:

Input

```text
bye
```
