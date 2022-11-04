---
layout: page title: Lim Qiao En's Project Portfolio Page
---

### Project: SETA

SETA is a desktop application for CS2103T Teaching Assistants to track their students’ and tutorials’ details, and
questions asked by students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, SETA enables you to manage your tutorials and track your students
more effectively than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to record student's total response count for all tutorials
    * What it does: allows user to update the student's response count
    * Justification: This feature improves the product significantly as the user needs to track student's total response
      count to grade student participation.
    * Highlights: This implementation required me to look at DeleteCommand and EditCommand and figure out which parts to
      take to make this feature.

* **New Feature**: Added the ability to mark a tutorial as done
    * What it does: allows user to update the status of the tutorial as complete
    * Justification: This feature improves the product significantly as users may have multiple tutorial groups, and the
      feature allows them to update which tutorials are incomplete.
    * Highlights: The implementation was tricky, I had to discuss with my members the parameters of the function, if it
      should have just the index of the tutorial in the tutorial list, or the date and time as well as the tutorial
      number. There was not much I could refer to in the original AB3 except DeleteCommand, and I had to decide if the
      parameter for this feature had to be a boolean, or a string. I was also the first to do up the marking type of
      feature, so my teammates could not really help me in the implementation.

* **New Feature**: Added the ability to mark a tutorial as undone
    * What it does: allows user to update the status of the tutorial as incomplete
    * Justification: This feature improves the product significantly as users may have multiple tutorial group, and they
      may make a mistake in marking another tutorial as complete, this feature allows them to undo that action.
    * Highlights: The implementation was less complicated as I could refer to previous code.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=T08&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=qiaoen17&tabRepo=AY2223S1-CS2103T-T08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
    * AddResponse:
        * Add on to current response count of a student.
        * Add the responses given by each student on Zoom, and store the Responses as Strings.
    * MarkTut:
        * Mark and unmark with this command.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addresponse`, `marktut`, `unmarktut`.
        * Did cosmetic tweaks to existing documentation of features `attendance`.
    * **Developer Guide**:
        * Add implementation details of `addresponse` feature.
        * Edited Appendix: Requirements
            * Rearrange user stories and use cases to match order of appearance for features.
            * Updated user stories to match current product e.g. adding use case for `unmarktut`.
            * Edit priority tab of user stories table to match current product.
        * Edited Appendix: Instructions for Manual Testing
            * Added more manual tests for features e.g. `addresponse`, `attendance`.

* **Team based tasks**:
    * Set up the team website using Jekyll
    * Set up issue tracker and add issues for my part
    * Edit milestone v1.2 date
    * Set up protection for master branch
    * Contributed for milestones v1.1, v1.2, v1.3, v1.4

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#48](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/48#pullrequestreview-1131168176),
      [\#61](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/61#pullrequestreview-1138428286),
      [\#77](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/77#discussion_r994027380),
      [\#99](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/99#pullrequestreview-1150261965)

* **Tools**:
    * Java
    * CSS
