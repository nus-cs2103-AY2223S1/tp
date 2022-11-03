---
layout: page
title: Jason Qiu's Project Portfolio Page
---

### Project: Swift+

Swift+ is a **project management application** designed to help software engineering (SWE) project leads in tracking their daily
interactions with contacts. The user interacts with Swift+ mainly through a Command Line Interface (CLI). The application is 
written in **Java** and has about **10k LoC**.

Here are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jasonqiu212&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **New feature**: Added the ability to find tasks
  * Relevant pull request(s): [#58](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/58)
  * What it does: This feature allows users to search for tasks using keywords.
  * Justification: This feature eases the process of finding tasks, since users can easily search using keywords.
 
* **New feature**: Added `deadline`, `description`, and `isDone` fields for tasks 
  * Relevant pull request(s): [#103](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/103), [#132](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/132)
  * What it does: This feature give users the option to add deadlines and descriptions to their tasks. It also allows the user to mark their tasks as completed and mark their tasks as incomplete.
  * Justification: This enhancement allows users to add more information to their created tasks.
  * Highlights: The main challenge while implementing this task was making the `deadline` and `description` optional. Although Java has the `Optional` class to wrap around optional values, it was difficult to decide where to add this layer of abstraction while considering the Single Level of Abstraction Principle (SLAP).

* **Enhancements to existing features**:
  * Renamed `find` command from AB3 to `find_contact` ([#56](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/56))

* **Project management**:
  * Managed releases `v1.1` to `v1.3` (4 releases) on GitHub
  * Updated Jekyll site-wide settings ([#10](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/10))
  * Added user stories, target user profile, and value proposition to developer guide ([#16](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/16))
  * Removed AB3 traces from user guide ([#34](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/34))

* **Documentation**:
  * User Guide:
    * Added sections on how to use this user guide and how to get started ([#193](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/193))
    * Added documentation for the `find_task` command
    * Made cosmetic changes to header colors and spacing to match color scheme of app
  * Developer Guide:
    * Added implementation details of finding contacts and tasks ([#82](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/82))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#19](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/19), [#20](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/20), [#48](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/48), [#57](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/57), [#62](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/62), [#83](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/83), [#85](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/85), [#89](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/89), [#99](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/99), [#128](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/128), [#133](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/133), [#187](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/187), [#195](https://github.com/AY2223S1-CS2103T-T12-2/tp/pull/195)
  * Contributed to forum discussions (examples: [#288](https://github.com/nus-cs2103-AY2223S1/forum/issues/288), [#317](https://github.com/nus-cs2103-AY2223S1/forum/issues/317))
  * Reported bugs and added suggestions for other teams in the class (examples: [#241](https://github.com/AY2223S1-CS2103T-W16-4/tp/issues/241), [#262](https://github.com/AY2223S1-CS2103T-W16-4/tp/issues/262), [#272](https://github.com/AY2223S1-CS2103T-W16-4/tp/issues/272))
  * Found bug in `addressbook-level3` site website: [#149](https://github.com/se-edu/addressbook-level3/issues/149)
