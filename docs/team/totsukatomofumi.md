---
layout: page
title: Totsuka Tomofumi's Project Portfolio Page
---

### Project: myStudent

myStudent is **an easy-to-use, portable and aesthetically-pleasing desktop application for tuition centre admins
for managing the students, tutors and tuition classes of a tuition center**.
It is optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Implemented the logic to update students' next of kin details or `NextOfKin` command. (Pull request [#105](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/105))
  * What it does: Allows users to add, update or remove the details of a student's next of kin.
  * Justification: This feature allows users to update the next of kin details of an existing student whenever they want, allowing them to ensure up-to-date next of kin information.
  * Highlights: Implemented `NextOfKinCommand` and `NextOfKinCommandParser` classes. There was a need to carefully implement the logic when coordinating with `Model` and `UI` components.

* **New Feature**: Implemented parsing of new user input fields and improved existing ones as well. (Pull requests [#53](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/53), [#88](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/88))
  * What it does: Allow for new user input fields to be parsed when user executes a command.
  * Justification: This feature allows more user input fields for commands especially when the application now handles three types of entities.
  * Highlights: Great consideration was needed in when implementing parsers for the different user input fields. There was also the need to understand the workings of regular expressions.
  The parsers are made flexible as they allow and are able to parse a number of formats that the user might use.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=totsukatomofumi&breakdown=true)

* **Project management**:
  * Actively led discussions in the weekly team meetings.
  * Actively created GitHub issues and assigned them to team members based on their strengths.
  * Allocated PE-D bugs to team members based on their strengths.

* **Enhancements to existing features**:
  * Enhanced the `Add` command [#53](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/53).
    * Previously, the command handled the adding of one entity type. For this project, there was a need to add three different types of entities. 
    * There was a need to implement new parsers for new user input fields as aforementioned.
    * Note that the syntax is made sleek as we did not resort to the use of, i.e, "add_s" or "add_t", which is unsightly.
  * Enhanced the `Edit` command [#64](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/64).
    * Now, the index specified to the command refers to the displayed list.
    * Also, according to what type of entities the list is displaying currently, the accepted user input fields will adjust accordingly.
  * Enhanced the `Clear` command [#65](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/65).
    * Now, the command clears either the list of all students, list of all tutors, and list of all tuition classes depending on which type list is being displayed.
  * Implemented the parsing when improving the `List` command syntax [#89](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/89).
    * Previously, a previous implementation of the command for myStudent used "list_s" or "list_t" to specify which entity list to view.
    This was improved by making the syntax sleek, i.e., "list student" and "list tutor".
  * Implemented the logic when enhancing the `Help` command [#123](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/123).
    * Previously, the command only showed a single window. Now, a specific command can be specified to view a help page regarding that command, i.e, "help add".
    * The logic and parsing of the user input for this command was implemented while the formatting of the help messages were left to be done.
    
* **Documentation**:
  * User Guide:
    * Adjusted some styling and formatting of entire page.
    * Added documentation for the `Add` command.
    * Added documentation for the `Edit` command.
    * Added documentation for the `NextOfKin` command.
    * Added documentation for the `Clear` command.  
  * Developer Guide:
    * Added implementation documentation for the `Add` command.
    * Added implementation documentation for the `Edit` command.

* **Community**:
  - Non-trivial PRs reviewed and merged: [#41](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/41), [#122](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/122), [#234](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/234)
  - The [full list](https://github.com/AY2223S1-CS2103T-F12-4/tp/pulls?q=is%3Apr+commenter%3Atotsukatomofumi) of PRs reviewed can be found here


