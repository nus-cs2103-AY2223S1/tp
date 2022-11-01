---
layout: page
title: Teo Yu Qi's Project Portfolio Page
---

### Project: Plannit

**Plannit** is an **all-in-one application** that streamlines the execution of module
deliverables by **empowering NUS students** with the ability to manage **tasks**, **links** and
**module-mates** (i.e., students in the same module) to increase their productivity.

Given below are my contributions to the project.
* **New Feature**: UI overhaul
  * What it does: The UI of the original AB3 was modified to accommodate all 
    the new features implemented by my team.
  * Justification: The AB3 initially only contained contacts, while Plannit 
    now featured many new components including [modules](../UserGuide.md#21-modules),
    [tasks](../UserGuide.md#22-tasks) and [links](../UserGuide.md#23-links). 
    As such, an overhaul was needed to allow all components to be displayed 
    to users in a neat and organised manner.
  * Highlights: Extensive modification of the AB3's code was necessary for 
    us to achieve the intended look. Furthermore, the addition of new 
    components meant we now had much more content to be fit within the same 
    window. This was not an easy task, considering the need to optimise 
    between displaying maximum information and keeping the design 
    minimalistic.

* **New Feature**: Adding, deleting and swapping of `Task` objects
  * What it does: Allows students to assign tasks to modules. Tasks can be
    ordered in the specific manner the student wishes.
  * Justification: This feature lets students keep track of the tasks to be
    completed, and also order them based on their respective priorities. 
    This allows them to complete their tasks in a timely fashion.
  * Highlights: This is a completely new component that was not previously 
    in the design of AB3. Hence, much thought was put into designing the 
    component's behaviour to maximise our user's utility.

* **New Feature**: Interactive UI for `Task` objects
  * What it does: Tasks are hidden by default while on the home page and 
    will only be displayed when users [`goto`](../UserGuide.md#25-navigation) a specific 
    module, or when they [double-click](../UserGuide.md#224-peeking-at-tasks)
    on the module.
  * Justification: Earlier implementations of the UI with both tasks and 
    links of all modules being displayed on the home page resulted in a very 
    cluttered layout. With our current implementation, we greatly reduce the 
    clutter by only displaying the tasks of modules indicated by the user.
  * Highlights: This enhancement needed an observable `Boolean` 
    value to indicated to the UI whether to display the tasks. This proved 
    highly challenging to implement, with much research having to be done on 
    the best (and simplest) method to do so. Furthermore, this feature 
    involved implementation of a GUI functionality, which was not something
    explored in our iP.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=teoyuqi&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=teoyuqi&tabRepo=AY2223S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Wrote additional tests for existing features ([#155](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/155)
  
* **Documentation**:
  * User Guide:
    * Created a base UG for teammates to build upon ([#36](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/36))
    * Added documentation for commands `add-task`, `delete-task` and 
      `swap-task` ([#54](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/54), [#164](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/164))
    * Added documentation for peeking of tasks ([#164](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/164))
  * Developer Guide:
    * Updated commands, links and diagrams of `Logic`, `Model`, `Ui` and 
      `Storage` components to be inline with Plannit ([#107](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/107))
    * Added implementation detail of `Task` and `TaskList` components ([#83](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/83))

* **Review contributions**:
  * Reviewed numerous PRs (e.g., [#81](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/81),
    [#98](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/98)
    and [#104](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/104))

* **Contributions beyond the project team**:
  * Sent 10 bug [reports](https://github.com/teoyuqi/ped/issues) in 
    [SETA](https://github.com/AY2223S1-CS2103T-T08-4/tp) 
    during the practical exam dry run.
  * Contributed to forum discussions (
    [#15](https://github.com/nus-cs2103-AY2223S1/forum/issues/15),
    [#389](https://github.com/nus-cs2103-AY2223S1/forum/issues/389))
