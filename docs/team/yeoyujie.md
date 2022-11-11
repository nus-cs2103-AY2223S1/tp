---
layout: page
title: Yeo Yu Jie's Project Portfolio Page
---

# Overview
ModQuik is a convenient tool that allows you to keep track of your classes, monitor your students' grades and set up reminders for your tasks.

## Summary of contributions
Given below are my contributions to the project.

* **Code contributed**: [yeoyujie's tP Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yeoyujie&breakdown=true)

* **Enhancements implemented**:
  * Added the ability to add, edit and delete reminders.
    * What it does: Allows the user to add, edit and delete any reminders they might have for any upcoming/past tasks.
    * Justification: This feature makes our product more attractive as users would usually want to have a quick overview of any upcoming tasks they might have, as well as to check the progress of their work so far.
    * Highlights: This enhancement affects existing command as it was similar to the `add`, `edit` and `delete` commands that AddressBook already has.
      Thus, there was a need to abstract out similar components and adhere to the DRY principle.
  * Added sorting reminders by either priority or deadline.
    * What it does: Allows the user to sort the reminders based on either priority or deadline.
    * Justification: This feature makes our product more user-friendly as users could know at a glance which tasks should be prioritised.
    * Highlights: This enhancement was challenging as it required implementing a complex comparator which takes in 2 different comparators as there are other tiebreakers taken into consideration if 2 reminders were to have the same condition (priority/deadline).
  * Wrote test cases to increase coverage from 52.62% to 57.47% (Pull requests #196, #202)
* **Project management**:
  * Managed releases `v1.3.1` - `v1.3.3` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the following features:
      * `add reminder`
      * `edit reminder`
      * `delete reminder`
      * `sort reminder`
    * Implemented the initial skeleton of the command summary
    * Added the glossary  
    * Improve the prefix summary by specifying which commands uses which prefixes
  * Developer Guide:
    * Added the Use Cases section
    * Added manual testing for the following features:
      * `add reminder`
      * `edit reminder`
      * `delete reminder`
      * `sort reminder`
    * Added the glossary

* **Contributions to team-based tasks**:
  * Setting up GitHub team org and repo
  * Maintaining issue tracker
