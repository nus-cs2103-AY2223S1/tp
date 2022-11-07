# Kian Seong's Project Portfolio Page

## Arrow
**Arrow** is a **desktop app** that **help software project managers keep track of their members’ tasks and deliverables**. By associating tasks to specific teammates, users can **keep track of what needs to be done and have quick access to contact information should they wish to reach out to the teammate**. More specifically, apart from the _command box_, there are two main lists which store the tasks list and the teammates list. In this way, our users can use two lists to organize the team project easily.

## Summary of Contributions

- **Code Contributed**:
[RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kianseong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Storage Feature**: Replicated the `AddressBook` storage and created a `TaskPanel` storage
  - What: The `TaskPanel` storage holds all the data for the tasks in the list.
  - Justification: The task panel and address book are two separate  lists. Cluttering the implementation of the storage and the actual storage could confuse users and developers.
  - Credits: The storage was copied from AB3 wholesale, however it is a skill to identify the files and tests to copy.
- **Task Add Feature**: Added the `task add` command
  - What: Users can add a task to the task panel.
  - Credits: `Title` was taken from `Address` as they possess the same characteristics.
- **QoL Feature**: Arrow key command log navigation
  - What: Users are able to use the UP and DOWN arrow keys to navigate previously entered commands.
- **Code Quality**: Ensured a certain level of the quality of code
  - What: Looked at and updated code pushed to ensure a higher quality.
  - Justification: This might not be nice to the writer of the code but it does ensure a higher standard of code in our project. Although, still not the best, I put in some effort.
- **Tests**: Wrote some tests for the commands that did not have them to ensure that they work as described.
  - Credit: If the command was taken from AB3, then tests as well.

- **Documentation**
  - User Guide:
    - Added `task add` command documentation
    - Added proposed features section
    - Reorganised content
    - Added line breaks to improve readability and formatting
  - Developer Guide:
    - Added Table of Contents and set hyperlinks to each section
    - Added sequence diagram for `task add` command
    - Updated `LogicClass` diagram
    - Wrote instructions and test cases for manual testing of some commands.
