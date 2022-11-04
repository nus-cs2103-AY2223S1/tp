---
layout: page
title: Siew Ee Sung's Project Portfolio Page
---

### Project: CinternS

CinternS is a desktop intern-management application used for managing internship applications for Computer Science students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Data Archiving
    * What it does: Allows user to hide past applications in the list and refer/retrieve back if necessary.
    * Justification: This feature improves the convenience of users in managing their applications significantly as they can hide unwanted data (e.g. past applications) to better manage the actual application list while not losing the benefits of keeping track of old applications (deleting it).
    * Highlights: This enhancement utilises and extends the idea of `FindCommand` filtering the list shown to user. It requires in-depth analysis of past commands to find alternatives that fits the existing implementation most. This feature provides three additional commands:
      1. `ListArchiveCommand`: Allows users to list all archived applications.
      2. `ArchiveCommand`: Allows users to archive selected application.
      3. `RetrieveCommand`: Allows users to retrieve selected application.
   * Credits: Idea inspired by `FindCommand` implementation from [address-book-level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: Showing statistic
    * What it does: A simple feature that allows user to review his overall applications' statistic.
    * Justification: This feature assists user in reviewing his internship application performance quickly especially when there are many applications added in CinternS.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=eesung00&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.2` - `v1.3` (2 releases) on Github.
    * Managed issues, pull request and milestones on Github.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `archive`, `statistics` and `delete`: [#27](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/27/files), [#115](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/115/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb), [#190](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/190).
    * Developer Guide:
        * Added implementation details of the `archive` and `statistics` feature: [#91](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/91/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b), [#190](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/190)

* **Community**:
    * PR reviewed highlights: [#58](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/58), [#109](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/109)
    * Reported bugs for other teams in the class [here](https://github.com/eesung00/ped/issues)

