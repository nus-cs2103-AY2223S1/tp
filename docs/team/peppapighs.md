---
layout: page
title: Pontakorn Prasertsuk's Project Portfolio Page
---

### Project: Swift+

Swift+ is a desktop contact management application for SWE project leads working on multiple projects to track their
interactions and meetings with clients and colleagues. The user interacts with it using a CLI, and Swift+ has a GUI created
with JavaFX. It is written in Java and has about 10kLoC.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=peppapighs&breakdown=true&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **New feature**: Added the task schema and the ability to add tasks.
  - What it does: allows the user to add tasks to the contact list.
  - Justification: This feature improves the product significantly because a user can track the tasks.
  - Highlights: This enhancement serves as the foundation for the other task features.
- **New features** Added the bridge relation between tasks and contacts.
  - What it does: allows the user to assign tasks to contacts and vice versa.
  - Justification: This feature improves the product significantly because a user can track the associations between contacts and tasks.
  - Highlights: This enhancement serves as the foundation for the other contact-task features.
- **New features** Added the ability to assign a contact to a task.
  - What it does: allows the user to assign a contact to a task.
  - Justification: This feature improves the product significantly because a user can make change to the associations between contacts and tasks.
- **Enhancements to existing features**:
  - Updated package name from `seedu.address` to `swift` #40, #42
  - Rename `list` command to `list_contact` command #48
- **Documentation**:
  - User Guide:
    - Add documentation for `add_task` command #45
    - Add documentation for `assign` command #99
  - Developer Guide:
    - Add implementation details of contact-task bridge relation #79
- **Reviewing/Mentoring**:
  - PRs reviewed: #51, #55, #52, #56, #58, #81, #82, #83, #85, #89, #103, #105, #117, #113, #127, #134, #137
