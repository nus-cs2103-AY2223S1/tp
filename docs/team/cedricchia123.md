---
layout: page
title: Cedric Chia's Project Portfolio Page
---

### Project: TABS

TABS is a desktop scheduler application used by students managing team projects. It allows the user who is a student to view a project's members and their workload, allocate tasks to them
and get reminders on upcoming deadlines or deliverables.

## Contributions
Given below are my contributions to the project.

* **New Feature**: `DeleteGroupCommand` [#91](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/91)
  * Details:
    * This command class allows the deletion of a group, removing its corresponding members as well as their allocated tasks.
    * This helps the user to delete a group instantly.
  * Justification:
    * When the project comes to an end, the project team leader might want to tidy up his workspace in TABS. Offering the functionality
    of removing the group instantly will be efficient as he does not need to delete each member and group one by one.

* **New Feature**: Added functionality of `Workload` to tasks. [#113](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/113)
  [#122](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/122)
  * Details:
    * This enum class allows a workload to be tracked for each task assigned to each member.
    * A workload of low, medium or high can be tagged to each task, specified with "/w" prefix.
    * Made `assignment` class implement `Comparable<Assignment>` to compare workload, listing tasks of higher workload first.
  * Justification:
    * One of the key focus of TABS is to gauge a person's workload. Implementing a way to view a how overloaded a 
    member is at a glance will allow the project team leader make better decisions when allocating tasks.
  * Difficulties:
    * Deciding to make workload an ordinal or categorical variable was crucial. We decided to make workload categorical to make
    TABS easier to use for beginners.

* **New Feature**: Added functionality of `Deadline` to tasks. [#113](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/113)
  * Details: 
    * This class allows TABS to store deadlines into tasks, specified with "/d" prefix
    * Along with this class, the `DateTimeParser` class is implemented to recognise inputs by the user, to parse as dates.
  * Justification:
    * Deadlines are an additional note for users to track when a task is due to better manage their projects.
  * Difficulties:
    * Parsing dates led to unforeseen situations where dates not present in the calendar (30 Feb) will be recognised
    as (28 Feb). We decided to make the formatting of these dates strict such that the user has to input a date within range.
    [#144](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/144)
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cedricchia123&breakdown=true)

* **Project Management**:
    * Opened Github issues for bug report.
    * Closed Github issues timely with each PR.
    * Update ReadMe page [#24](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/24)

* **Enhancements implemented**:
    * Refactored AB3 code to improve readability [#94](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/94).
    * Improve workload indicator to make colour change from green to red gradual [#142](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/142).
      * Credits: Formula referenced from [link](https://stackoverflow.com/questions/340209/generate-colors-between-red-and-green-for-a-power-meter).
    * Refactored `edit` command in AB3 to `editperson`, adding `FullNamePredicate` class to edit them by full name [#73](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/73).
    * Added test cases for `editperson` and `deleteperson` commands [#100](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/100).
    * Fixed test cases for task commands [#124](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/124).

* **Documentation**:
    * User Guide:
      * Added documentation for group features [#25](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/25).
      * Reformatted user guide to fix PE-D bugs and improve readability [#206](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/206) [#217](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/217).
    * Developer Guide:
      * Added documentation and UML diagram for `deletegroup` feature [#116](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/116).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#123](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/123) [#193](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/193)

