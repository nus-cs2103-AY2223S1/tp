---
layout: page
title: Linus Chui's Project Portfolio Page
---

# Project: ConnectNUS

## Overview
ConnectNUS is a desktop address book application used for NUS CS students to keep track of their own modules, and their friend's modules and contacts as well. This is to facilitate NUS CS students to keep track of their friends to work with so that they can easily find friends to collaborate with.

## Contributions
Given below are my contributions to the project.

* **New Feature**: Timetable
    * What it does: View user's or person's timetable for the week (with the lessons added).
    * Justification: Easily arrange meetings by viewing everyone's schedule to find their free time.
    * Highlights:
      * User can add `Lesson` to `User` or `Person` in contacts.
      * Running the command to show the `Timetable` opens up the target `Timetable` in a separate window.
      * The `Lesson` are sorted in chronological order before displaying so the User does not have to add `Lesson`s in chronological order.
      * The feature checks for invalid lessons with overlapping time periods and informs the User that an invalid time has been given.
      * A sample screenshot of the `Timetable` window is given below.

    <img src="../images/TimetableWindow.png" width="300" />
  
   * Created test cases for `Timetable`.  [\#212](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/212) [\#216](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/216)

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=linuschui&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**: Add module to contacts
    * What it does: Add current. previous and planned modules to any persons saved in contact.
    * Justification:
        * Current: Find group mates or people to work with.
        * Previous: Find people to consult or obtain resources like cheatsheets.
        * Planned: Find people to plan tutorials with.
    * Created test cases for the `Module`, `Lesson` and their related classes. [\#90](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/90)  [\#100](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/100)

* **Documentation**:
    * User Guide:
        * Added Introduction section [\#165](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/165)
        * Added Quick Start section [\#167](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/167)
        * Added documentation for the feature `timetable` [\#103](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/103)
        * Added Navigating the User Guide and Glossary sections: [\#193](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/193)
        * Updated the documentation for the features `timetable` and `help` for better readability [\#193](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/193)
    * Developer Guide:
        * Added implementation details of the `timetable` feature. [\#102](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/102)
        * Added Introduction, Glossary and Acknowledgement sections : [\#198](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/198)

* **Review / mentoring contributions**:
    * Reported bugs and suggestions for other teams in the class [SectresBook](https://github.com/AY2223S1-CS2103T-W12-2/tp)
    * PRs approved: [\#69](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/69) [\#83](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/83) [\#84](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/84) [\#95](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/95) [\#96](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/96) [\#97](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/97) [\#98](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/98) [\#99](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/99) [\#109](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/109) [\#166](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/166) [\#169](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/169) [\#179](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/179) [\#180](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/180) [\#186](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/186) [\#189](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/189) [\#190](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/190) [\#192](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/192) [\#205](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/205) [\#209](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/209) [\#210](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/210) [\#218](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/218)
    * Supported testing of features implemented by other team members. 
    * Identified bugs with `CommandResult` in features `add` and `timetable`.
    * Guided implementation of displaying the `Timetable` using `LessonComparator`.
    * Fixed bug where overlapping `Lesson` timings are allowed to be added to `Timetable` of `User` and `Contact`. [\#168](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/168)
    * Updated error in `Timetable` `CommandResult` with the correct sample command.  [\#168](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/168)
    * Created test cases for the `Timetable` class.  [\#212](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/212)
    * Guided the format of User Guide and Developer Guide for easy readability and navigation.
    * Standardised the format of the Developer Guide. [\#221](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/221)
    * Proofread User Guide and Developer Guide. [\#233](https://github.com/AY2223S1-CS2103T-T14-4/tp/pull/233)
