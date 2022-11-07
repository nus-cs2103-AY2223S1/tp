---
layout: page
title: Leong Yu Xuan's Project Portfolio Page
---

### Project: SETA

SETA is a desktop application for CS2103T Teaching Assistants to track their students’ and tutorials’ details, and
questions asked by students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, SETA enables you to manage your tutorials and track your students
more effectively than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete a student.
  * What it does: Allows the user to delete a student.
  * Justification: This feature improves the product significantly because a user remove students after the semester is over.
  * Highlights: This enhancement requires me to create new classes.

* **New Feature**: Added the ability to add a student's attendance.
  * What it does: Allows the user to add the student's attendance.
  * Justification: This feature improves the product significantly because a user can keep track of student's attendance for grading purposes.
  * Highlights: This enhancement requires me to think about how will our target user will use it. The initial plan was to just take in a number as parameter and change the target student's attendance to that value. However, I feel that it is not intuitive to do so and change the command to increment the target student's attendance instead.

* **New Feature**: Added the ability to add a help tag to a student.
  * What it does: Allows the user to indicate which student needs more help.
  * Justification: This feature improves the product significantly because a user can see at one glance which students need more attention.
  * Highlights: This enhancement requires me to analyse how the AB3 implements `Tag` and then make significant changes to it as for SETA, a student can only have a maximum of one tag unlike AB3 that allows more than one tag. I will then need to decide to use it as a simple boolean value or create a class for the help tag. (The latter is being implemented)

* **New Feature**: Added the ability to remove a help tag to a student.
  * What it does: Allows the user to indicate which student, that needed help, does not need help anymore.
  * Justification: This feature improves the product significantly because a user can remove a help tag from a student without the need to
    delete the student and add the student back with the same details but just without the help tag.
  * Highlights: This enhancement requires me to make create new classes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yuxuanleong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code~test-code~other~docs&tabOpen=true&tabType=authorship&tabAuthor=yuxuanleong&tabRepo=AY2223S1-CS2103T-T08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code~other~docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Released v1.2, v1.3.1 and v1.4 on Github
  * Created issues and tagged them for easy reference for my teammates

* **Enhancements to existing features**:
  * Made the headers "Students", "Questions", "Tutorials" in the Main Window of the UI uneditable to improve user
    experience.

* **Documentation**:
  * User Guide:
    * Added Table of Contents for easy navigation [#25](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/25)
    * Added documentation for the features `deletestu`, `attendance`, `helpstu` and `unhelpstu` [#25](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/25)
    * Added screenshots each features [#230](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/230)
  * Developer Guide:
    * Added implementation details of the `attendance` feature and its sequence diagram [#125](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/125)
    * Update Logic component [#125](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/125)
    * Added use cases for the above-mentioned 4 features [#25](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/25)
    
* **Community**:
  * Helped to review PRs and suggested changes.
    * [#118](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/118)
    * [#115](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/115)
    * [#50](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/50)
