---
layout: page
title: Chew Hong Jin's Project Portfolio Page
---

### Project: `CLIMods`

`CLIMods` is a desktop application that allows students to plan out their timetable with the use of a Command Line
Interface. `CLIMods` allows users to manage their modules much faster than doing it on the common website
that most NUS students use [nusmods.com](https://nusmods.com).

Given below are my contributions to the project.

* **Infrastructure**: Added code to store unique UserModules in a list (Pull request [\#59](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/59))
    * Used throughout application code for almost all command that involves changing UserModule details.

- **New Feature**: Added the ability to pick lessons in selected modules
    - `pick` command (Pull request [\#84](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/84))
    - What it does: Allow users to pick lessons for selected modules
    - Justification: This feature enhances the product tremendously because it allows users to easily pick lessons 
      hence organizing their respective timetables based on most updated information on NUSMods. The lesson timings, 
      days and venues will also be displayed.  
    - Highlights: This feature will check if lesson type is available, and later check if lesson code is available / 
      valid based on the lessons that the module offers in the particular semester.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sarrrdin&breakdown=true)

- **Project management**:
    - Take down minutes during team project meetings.
    - Organize and document minutes in google docs for easy reference.
    - Manage Demo Video release

- **Enhancements to existing features**:
    - `add` command (Pull request [\#59](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/59), [\#85](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/85), [\#104](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/104)):
      - allows user to add a module.
      - module to be added will be based on semester (Pull request: [\#85](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/85))
      - add command checks whether module is offered in semester.
      - add command throws an error message if module is not offered.
      - add command will also automatically add in unselectable lessons (Pull request: [\#104](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/104))
        - example such as fixed lecture timings (square sides on NUSMods)
        
    - `delete` command (Pull request [\#59](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/59)):
      - allows user to delete a module.
      - module to be deleted will be based on module added.
      - throws error message if modulecode to be deleted is invalid.
      
    - `exit` command (Pull request [\#65](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/65)):
      - allows user to exit climods.
      - no need to use mouds, keyboard command `exit` will do.

    - Wrote additional tests while enhancing existing features and implementing new features. 

- **Documentation**:
    - Developer Guide:
      - Added user stories and use cases. (Pull request [\#31](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/31))
      - Added ParserClasses Diagram
      - Added ModelClass Diagram
      - Update details of ModelClass 
      
    - User Guide:
      - Added documentation for `add` Command.
      - Added documentation for `rm` Command.
      - Added documentation for `pick` Command.
      - Added documentation for `exit` Command.

- **Community**:
    - PRs reviewed (with non-trivial review comments): [#99](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/99), 
     [#101](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/101)
