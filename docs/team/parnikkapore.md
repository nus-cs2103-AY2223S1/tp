---
layout: page
title: Poomklao Teerawatthanaprapha's Project Portfolio Page
---

### Project: CodeConnect

CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and is linked to their local contacts to find students that take the same modules. This gives them an easy way to source for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=parnikkapore&breakdown=true)

* **Team tasks**:
  * Created and managed the team's repository and CI
  * Occasionally checked the consistency of the product and calls for changes if necessary
  * Investigated and fixed a flaky test in completely deterministic code ([#55](https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/55))
  * Investigated and fixed failing tests due to a violated deep assumption ([1f8a0be](https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/90/commits/1f8a0be9027044f2b5763e736f313a6708731745)...[1312776](https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/90/commits/1312776ed1fd660586f9bcffd2529c352f07a6f7))

* **New features**:
  * Model classes for task tracking
  * Add / delete task commands

* **Enhancements to existing features**:
  * Natural date parsing for task deadlines (using [JChronic](https://mvnrepository.com/artifact/com.rubiconproject.oss/jchronic))
    * Investigated and tested several lightweight Java natural date parsing libraries
    * Wrote tests to verify expected behavior of substituted libraries in the future
  * Space-separated modules for `addc` and `editc` commands

* **Documentation**:
  * User Guide:
    * Added user guide items for add / remove task commands
    * Improved the formatting and styling
      * Involves locally running Jekyll, bumping its dependencies, and modifying the .scss source.
    * Revised product introduction
    * Added legend for note and warning boxes
  * Developer Guide:
    * Gathered and formatted use cases for task management
    * Documented add task command, including a sequence diagram and design decisions
    * Added guide introduction / purpose statement

* **Community**:
  * Reviewed Kuang Long's PRs for code quality issues and potential unintended behavior (Notable: [#47](https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/47). [#159](https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/159)) as the primary reviewer
  * Contributed to the course-wide forum (Notable: [#246](https://github.com/nus-cs2103-AY2223S1/forum/issues/246#issuecomment-1250198702), [#334](https://github.com/nus-cs2103-AY2223S1/forum/issues/334))

* **Tools**:
  * CodeCov integration and configuration
