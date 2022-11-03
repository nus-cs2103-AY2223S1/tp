---
layout: page
title: Daniel Lee's Project Portfolio Page
---

### Project: InternConnect

InternConnect is a one-stop, convenient, and efficient platform to manage and empower how internship campus recruiters
who prefer CLI to GUI work with their applicants’ data. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Enhancements to existing features**: Upgrade existing find functionality (PR [#182](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/182)).
  * What it does now:
    * Allows user to find by multiple keywords per specified field, and search for multiple fields all at once

  * Justification:

    * This feature improves the product significantly because it empowers to user to search for applicants not just by name, but by whatever field that they want to search for, its' relevant keywords, all in 1 command. 
    * This makes the `find` command much more powerful, robust, useful and customizable for the user to narrow down and find that specific applicant they are looking for.

  * Highlights:

    * Initially, I wanted to create a generic abstract class `ContainsKeywordsPredicate` which all sub predicates can inherit from, in the spirit of OOP and keeping the method signature for the find command relatively the same. However, after further thought, in the spirit of a brownfield project, and for consistency with the previous version of the product, I instead decided to create each sub predicate individually, and combine these predicates into 1 predicate as per the original method signature of `FindCommand#FindCommand(predicate)` to input into `Model#updateFilteredApplicantList(predicate)`.
    * However, this does not make sense OOP wise, and it removed traces of all the sub predicates before it was actually executed. I hence decided to meet between the 2, and have the find command keep a list of active predicates to be applied, and then combine the predicates together into a single predicate to avoid changing the method signature of `Model#updateFilteredApplicantList(predicate)`.

  This way, the codebase is still consistent with the previous version of the product, and is easy for developers to understand, maintain and extend in the future.


* **New Fields Added**: Added new field `graduationDate` to an applicant (PR [#110](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/110)).

  * Now we can specify the value of `graduationDate` in `add`, `edit`, `find` using a `g/` prefix.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lulucopter&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lulucopter&tabRepo=AY2223S1-CS2103-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Reviewed and approved team members' PRs
  * Initial set up of project [Caban board](https://github.com/orgs/AY2223S1-CS2103-F14-2/projects/1/views/1)

* **Enhancements to existing features**:
  * Adjusted CLI prefixes for better user friendly input (PR [#168](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/168))

* **Documentation**:
  * User Guide:
    * Refined and added documentation for the features `find` and `help` (PR [#86](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/86))
  * Developer Guide:
    * Add sequence diagram for `find` command (PR [#184](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/184))
    * Update all UML architecture and model diagrams with new applicant attributes (PR [#157](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/157/))
    * Update new upgraded `find` functionality (PR [#158](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/158))
    * Added user stories, use cases for `find` and `help` commands (PR [#86](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/86))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#166](https://github.com/nus-cs2103-AY2223S1/ip/pull/166), [#142](https://github.com/nus-cs2103-AY2223S1/ip/pull/142) [#116](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/116)
  * Contributed to forum discussions [forum](https://nus-cs2103-ay2223s1.github.io/dashboards/contents/forum-activities.html#43-lee-niel-lulucopter-9-posts) (examples: [#155](https://github.com/nus-cs2103-AY2223S1/forum/issues/155), [#35](https://github.com/nus-cs2103-AY2223S1/forum/issues/35))
  * Reported bugs and suggestions for other teams in the class

* **Tools**:
  * Used Github and Git for version control, with SourceTree as a GUI for Git
  * Used IntelliJ to edit all files
  * Used PlantUML to create UML diagrams
