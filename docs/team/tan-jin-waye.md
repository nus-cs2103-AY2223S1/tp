---
layout: page
title: Tan Jin Waye's Project Portfolio Page
---

### Project: TABS

TABS is a desktop scheduler application used by students managing team projects. It allows the user who is a student to view a project's members and their workload, allocate tasks to them
and get reminders on upcoming deadlines or deliverables.
### Contributions

Given below are my contributions to the project.
* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tan-jin-waye)

* **New Features Added**:
  * [Groups data structure](https://github.com/Tan-Jin-Waye/tp/tree/branch_group)
    * What it does:
      * Data structure which maintains a `GroupName` class and a set of `Person`s as members. Supports basic CRUD operations.
    * Rationale:
      * Need a data structure to represent a group in TABS.  
      * Commands exist for testing purpose only.

  * [Integrate Groups into AB3](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/72)
    * What has been done: 
      * Allows app to store a list of groups, and subsequently perform CRUD operations to modify this list.
      * Skeleton commands to add/delete from list.
    * Rationale:
      * Subsequent operations in our app will require performing operations on groups in the app in a similar manner to `Person`s.
      * Added commands exist for testing purpose only.
    * Highlights:
      * Add `UniqueGroupList` class in the `AddressBook` class;
      * Add `DuplicateGroupException` and `GroupNotFoundException` in relation to Groups;
      * Add methods to modify `UniqueGroupList` in `AddressBook`, `ModelManager`;
      * Add rudimentary `AddGroupCommand` and `DeleteGroupCommand`, including respective parsers and modifying `AddressBookParser` class;
      * Add JUnit test cases for above-mentioned new classes.

  * [Storage for Groups](https://github.com/Tan-Jin-Waye/tp/tree/branch_storage)
    * What has been done:
      * Add supporting classes to convert a Group object into JSON format, and store it in the data file.
    * Rationale:
      * To allow users to save their changes made to Groups in a similar manner to `Person`s

  * [Bulk Task Assignment/Deletion](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/112)
    * What it does:
      * Assigns a task to/Deletes a task from all members of a group (exact behaviour specified in developer guide).
    * Rationale:
      * Enables user to quickly and accurately assign same task. 

  * [Minor JavaFX Edit](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/196) for window size.

* **Enhancements to Existing Features**:
  * [Add JUnit testcases](https://github.com/Tan-Jin-Waye/tp/tree/branch_testing) for `LogicManager`, `AddressBook` and `ModelManager` classes.

* **Documentation**:
  * Developer Guide
    * [Transfer Project Scope and NFRs](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/17) (discussed by team).
    * [Update Use Case Formatting](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/36).
    * [Update AB3 Model Component Details](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/132) and Class diagrams to account for `Group` and `Assignment` classes.
    * [Update Implementation Details](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/132), Sequence and Object diagrams for the following sections:
      * Add/Delete Group Feature
      * Add/Delete Member Feature
      * Display/List Group Feature
      * Assign/Delete Task Feature
      * Bulk Commands Feature
  * User Guide
    * [Transfer Person related Commands](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/20) (discussed by team).

* **Team Based Tasks**:
  * Allocate tasks for v1.3 and v1.4 
  * Consolidate and close Issues as part of PE-Dry Run

* **Reviewing/Mentoring Contributions**:
  * [Reviewed 11 PRs](https://github.com/AY2223S1-CS2103T-W10-1/tp/pulls?q=is%3Apr+reviewed-by%3ATan-Jin-Waye).
  * [Left Comments on 23 PRs](https://github.com/AY2223S1-CS2103T-W10-1/tp/pulls?q=is%3Apr+commenter%3ATan-Jin-Waye).
    * Non-trivial comments (i.e. not simply 'LGTM' or similar): 10
      * [#21](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/21) 
      * [#22](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/22)
      * [#24](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/24)
      * [#25](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/25)
      * [#26](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/26)
      * [#27](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/27)
      * [#29](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/29)
      * [#30](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/30)
      * [#98](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/98)
      * [#211](https://github.com/AY2223S1-CS2103T-W10-1/tp/pull/211)

* **Community**:
  * Reported bugs and offered suggestions to another group during [PE-Dry Run](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues?q=is%3Aissue+Tan-Jin-Waye%2Fped)


