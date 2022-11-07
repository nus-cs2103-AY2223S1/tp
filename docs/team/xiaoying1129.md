---
layout: page
title: Xiaoying's Project Portfolio Page
---

### Project: Watson, The Teacher's Companion

Watson is a desktop database application that
reduces the administrative workload of teachers greatly.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort students by grade - `sort` command
    * What it does: allows teachers to sort students by grade in ascending or descending order according to their preference
    * Justification: This feature allows the teacher to see the students who are performing well and those who need help at a glance

* **New Feature**: Added the ability to add remarks to student - `remark` command
    * What it does: allows teachers to add personalized remark for each student
    * Justification: This feature allows the teacher to add remarks to students which can be used as a quick memo or help to remind the teacher of certain information about the student

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=xiaoying1129&breakdown=true)
  * `Pull Requests`:
    * [#32]: Added a skeletal Personal Portfolio Page
    * [#36]: Update `add`, `list` and `edit` in User Guide
    * [#56]: Add `find` by subject, personality, class
    * [#81]: Update Developer Guide for `sort` and add assertions
    * [#87]: Add `sort` command
    * [#90]: Add `remark` command
    * [#101]: Update Developer Guide and User Guide for `find`, `sort` and `remark` commands
    * [#191]: Fix User Guide issues from PE dry run
    * [#207]: Update Personal Portfolio Page
    * [#209]: Add test cases to increase coverage

* **Project management**:
    * `PRs reviewed`
      * [#31], [#34], [#35], [#37], [#38], [#42], [#48], [#53], [#58], [#59], [#60], [#64], [#68], [#79], [#83], [#85], [#93], [#98], [#100]

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage: (Pull Request [#209])

* **Documentation**:
    * User Guide:
        * Added documentation for `add`, `list` and `edit` features to the UG draft [#36]
        * Added documentation for `find`, `sort` and `remark` features [#101]
        * Update User Guide to fix documentation bugs reported in the PE dry run [#191]
    * Developer Guide:
        * Added proposed implementation for the `sort` feature [#81]
        * Added documentation for `find`, `sort` and `remark` features [#101]

* **Community**:
    * [PRs reviewed](https://github.com/AY2223S1-CS2103T-T08-1/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed) (with non-trivial review comments)
    * Reported bugs and suggestions for other teams in the class: [14 bugs reported in the PE dry run](https://github.com/xiaoying1129/ped/issues)
