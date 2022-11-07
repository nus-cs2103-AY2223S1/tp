---
layout: page
title: Shawn Lee's Project Portfolio Page
---

## Project: NotionUS

NotionUS is a desktop address book application used for teaching Software Engineering principles. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contributed**: My contributions to the code can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=xenonshawn&breakdown=true)
* **Enhancements implemented**:
  * Morphed the original project from AB3 to NotionUS which necessitated changes in [69 files](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/37).
  * Added the `Task`, `Module`, `Deadline` classes and their respective unit tests
    * Deadline was later modified to allow for optional deadlines
    * `Task` was later modified to support sorting through implementation of the generic `Comparable` interface
  * The existing AB3 command parser was modified to ignore extraneous prefixes/flags in a command
    * Due to constraints of v1.4, it could not be modified to throw an error but hence currently silently ignores extraneous flags
* **Contributions to the UG**:
  * Wrote the initial portion of the Quick Start guide
  * Added information for the `add` and `edit` commands
  * Added the command summary and settled on the command format syntax
  * Rearranged the commands and subcatergorized them for ease of reference
  * Added links throughout the UG to improve navigability
* **Contributions to the DG**: 
  * Updated the Logic and Model architecture diagrams to reflect the new NotionUS structure
  * Added more information relating to the new classes (eg `Task`, `Module` and `Deadline`) in the Model section
  * Provided two proposed features, one for acceptance of multiple date formats and one for aliasing of commands
* **Contributions to team-based-tasks**: 
  * Set up most aspects of the GitHub team repository and permissions, including GitHub Actions and issue tracker labels
  * Maintained the issue tracker by assigning issues to the respective team members as according to meeting resolutions
  * As the team lead, I ensured that most of the team members are on track for their deliverables
  * I helped in release management, eg creating of the JAR files and managing the milestones
* **Review/mentoring contributions:**
  * The full list of PRs I've reviewed can be found [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AXenonShawn)
  * In particular, [#44](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/44), [#48](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/48) and [#184](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/184)
  * Helped the team to do an overall UG/DG review
* **Contributions beyond the project team**:
  * [Posted occasionally](https://github.com/nus-cs2103-AY2223S1/forum/issues?q=is%3Aissue+author%3AXenonShawn+) on the module forum. In particular, [#392](https://github.com/nus-cs2103-AY2223S1/forum/issues/392) was referenced in three other issues
