---
layout: page
title: Douglas Lim's Project Portfolio Page
---

### Project: MODPRO

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:  Implemented `AddTagCommand`
    * What it does: Adds the tag to a task in the task list.
    * Justification: This feature allows users to add priority status or deadline to their task so
      that they can track the priority and deadline to complete the task.
    * Highlights: The implementation for `AddTagCommand` was slightly challenging  as there were several cases
      such the task already having the tag that needs to be considered. Moreover, given that the various tags added
      interact with the `Task` classes when being added, some changes in `Task` methods have to be made.


* **New Feature**: Implemented `LinkExamCommand`
    * What it does: Links the exam in the exam list to a task in the task list.
    * Justification: This feature helps users to track the progress of completion status of exam-related tasks separately
      after linking is performed between a task and a exam.
    * Highlights: This enhancement affects existing commands namely `EditModuleCommand` and `EditTaskCommand`. The
      implementation was not straightforward as multiple different classes(`Task` and `Exam`) were involved and the
      `Task` class had to be modified to store this link.


* **New Feature**: Partially implemented `AddModuleCommand` which allows users to add modules to the module list.


* **New Feature**: Implemented the `EditTagCommand` which allows users to edit the tags linked to the task.


* **New Feature**: Implemented the `DeleteTagCommand` which allows users to delete the tags linked to the task.


* **New Feature**: Implemented the `SortTaskCommand` which allows users to sort the tasks stored in the task list.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=dlimyy)


* **Project management**:
    * Managed releases `v1.3.0` and `v1.3.1` on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the features `AddModuleCommand`, `AddTagCommand`, `EditTagCommand`, `DeleteTagCommand`,
          `LinkExamCommand` and `SortTaskCommand`.
        * Updated the `Quick Start` portion of the UG to reflect change in application to `MODPRO`.
    * Developer Guide:
        * Added implementation details of `SortTaskCommand`

* **Contribution to Team Tasks**
    * Set up the GitHub Repo for `MODPRO`
    * Added CodeCov coverage for our GitHub repo
    * Enabled assertions in the build.gradle file for our team project
    * Contributed to the design of UI image of our product

* **Community**:
    * Reviewed numerous PR
    * Reported a total of 13 bugs for the group working on [MyInsuRec](https://github.com/AY2223S1-CS2103T-W16-4/tp)
      (Examples of bugs include [1](https://github.com/dlimyy/ped/issues/13), [2](https://github.com/dlimyy/ped/issues/12),
      [3](https://github.com/dlimyy/ped/issues/10))

