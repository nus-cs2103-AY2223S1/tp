---
layout: page
title: Jia Xi's Project Portfolio Page
---

### Project: Watson, The Teacher's Companion

Watson is a desktop database application that
reduces the administrative workload of teachers greatly. 
The user interacts with it using a CLI, and it has a GUI 
created with JavaFX. It is written in Java, and has about 
10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * `Find Command with Prefix`
    * What it does: Allows the user to find a student 
    in Watson by searching via "NAME", "STUDENT_CLASS"
    and/or "SUBJECT" keywords, entering these keywords
    after the prefixes "n/", "c/" and "s/" respectively.
    * Justification: This feature allows users to have
    flexibility in finding students inside Watson, as 
    users can find not just by name but by class and 
    subject as well.
  * `Mark Attendance Command`
    * What it does: Allows the user to mark attendance for
    multiple students in a particular class and on a 
    particular day, using their assigned index numbers.
    * Justification: Allowing the marking of attendance
    for multiple students at once speeds up the process,
    and the one-liner command makes it faster than a 
    similar GUI interface.

* **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=joosxi&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  * `Pull Requests`:
    * (#67): Find command with prefix v1.3
    * (#76): Update Developer Guide for 
    find command(Finding by class prefix)
    * (#79): Update edit command
    * (#91): Mark attendance command
    * (#98): Update User Guide for edit command
    * (#100): Update Developer Guide for edit command
    * (#190): Enhancements to User Interface
    * (#197): Add test cases for FindCommandTest
    * (#216): Update User Guide for attendance command and step-by-step example for multiple commands
    * (#219): Update User Guide by resizing big images

* **Project management**:
  * Backend Developer

* **Enhancements to existing features**:
  * `Edit Command`: Enhanced edit command to be able to
  edit student class and remarks.
  * `UI Improvements`: Improved on formatting for display
  of students' details, and colour coding for attendance
  level.

* **Documentation**:
  * User Guide:
    * Documentation of `Edit command`
  * Developer Guide:
    * Documentation of `Find command (Finding by class prefix)`
    * Documentation of `Edit command`

* **Community**:
  * Helped to review code of a total of 13 pull requests

