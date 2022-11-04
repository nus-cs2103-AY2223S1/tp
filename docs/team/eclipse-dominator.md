---
layout: page
title: Zhaoqi's Project Portfolio Page
---

### Project: Contactmation

Contactmation is a desktop address book application used for project, team, company management. Powerful, effective, simple yet complex.

### Underlying design of Contactmation
Draft, program, remodel the Model component of contactmation.
- Created Abstract Display Item and abstracted out all related component (person, task, group) to the item
- Created logic for how display items are nested within each other


### Custom Commands
Redesign commands to enable commands to take in custom field and output custom fields
Redesigned command parser to tranform it into a singleton and allow users to add new custom aliases and command to the parser

Added custom commands components:
- alias
- macro
- e
- cmp
- seq
- int
- float
- str
- r 
- if 
- ...

### UI
Added new custom fields display view list for the person, task, groups
Adds display view of currently active path