---
layout: page
title: Chew Hong Jin's Project Portfolio Page
---

### Project: `CLIMods`

`CLIMods` is a desktop application that allows students to plan out their timetable with the use of a Command Line
Interface. `CLIMods` allows users to manage their modules much faster than doing it on the common website
that most NUS students use [nusmods.com](https://nusmods.com).

Given below are my contributions to the project.

- **New Feature**: Added the ability to pick lessons in selected modules
    - `pick` command
    - What it does: Allow users to pick lessons for selected modules
    - Justification: This feature enhances the product tremendously because it allows users to easily pick lessons 
      hence organizing their respective timetables based on most updated information on NUSMods. The lesson timings, 
      days and venues will also be displayed.  
    - Highlights: This feature will check if lesson type is available, and later check if lesson code is available / 
      valid based on the lessons that the module offers in the particular semester.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sarrrdin&breakdown=true)

- **Project management**:
    - Taking down minutes during team project meetings.
    - Organize and document minutes in google docs for easy reference.

- **Enhancements to existing features**:
    - `add` command:
      - allows user to add module.
      - module to be added will be based on semester.
      - add command checks whether module is offered in semester.
      - add command throws an error message if module is not offered.
      - add command will also automatically add in unselectable lessons
        - example such as fixed lecture timings (square sides on NUSMods)
        
    - `delete` command:
      - allows user to delete module.
      - module to be deleted will be based on module added.
      - throws error message if modulecode to be deleted is invalid.
      
    - `exit` command:
      - allows user to exit climods.
      - no need to use mouds, keyboard command `exit` will do.

- **Documentation**:
    - Developer Guide:
      - Added user stories and use cases.
      
    - User Guide:
      - Added documentation for Add Command.
      - Added documentation for Delete Command.
      - Added documentation for Pick Command.
      - Added documentation for Exit Command.

- **Community**:
    - PRs reviewed (with non-trivial review comments): [#99](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/99), 
     [#101](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/101)
    