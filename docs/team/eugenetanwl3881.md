---
layout: ppp
title: Eugene Tan's Project Portfolio Page
---

<!-- markdownlint-disable-next-line blanks-around-headers -->
### Project: FoodRem
{: .no_toc}

<!-- markdownlint-disable-next-line proper-names -->
{{ site.data.foodrem.about.summary }}

Given below are my contributions to the project.

* **New Feature**: Added the ability tag an item

  * What it does: allows the user to tag a valid item with a valid tag (PR [\#171](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/171), [\#172](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/172))
  * Justification: This feature improves the product significantly since a user will be able to tag items with a specific tag for classification purposes. This tag will be unique and can be referenced by any item if the item contains this tag
  * Highlights: This enhancement will allow for better classification and searching for items in other commands. For instance 

* **New Feature**: Added the ability untag an item (PR [\#172](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/172))
  *  What it does: Similar to tag feature, just that items can be untagged if user does not want to use that tag to classify an item anymore.

* **New Feature**: Added the ability list all tags (PR [\#200](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/200))

  *  What it does: User can call the `listtag` command to see all the tags that is available
  *  Justification: User may not remember what were the tags he has created or deleted, hence will need a way to  check
  *  Highlights: This feature will be useful when trying to use other tag commands since user can see all the available tags

* **Refactored feature**:
  * Refactored the `clear` command in AB3 to the `reset` command in FoodRem. This clears all items and tags stored (PR [\#160](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/160))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Eugenetanwl3881&tabRepo=AY2223S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Refactoring done to project packages (PR [\#149](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/149))
  * Team tasks: Creation of issues, completing some weekly team tasks (PR [\#241](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/241))

* **Testing**:
  * Wrote some tests for tag features (PRs [\#235](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/235))

* **Documentation**:

  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  
  * PRs reviewed (with non-trivial review comments): [\#199](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/199), [\#32](), [\#232](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/232), [\#143](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/143)

  
* Update AboutUs ([PR#22](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/22))
* Remove AB3 Info from Developer Guide ([PR#36](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/36))
* Add NFRs to DG ([PR#57](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/57))
* Add skeletal UG ([PR#65](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/65))
* Add Commands for list, find and bye in UG ([PR#77](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/77))
* Add Acknowledgments in README ([PR#78](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/78))
* Add Use Cases 1 and 2 in DG ([PR#79](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/79))
* Add User Stories and Update Use Cases ([PR#142](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/142))
* Organize commands to subpackages based on their type. ([PR#149](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/149))
* Update UG Command Summary Table  ([PR#152](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/152))
* Update \"reset\" command ([PR#160](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/160))
* Add tag command (tagging of items) UI ([PR#171](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/171))
* Add \"tag\" and \"untag\" command (tags) to tag/untag an item ([PR#172](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/172))
* Add FAQ into UG ([PR#178](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/178))
* Add listtag command  ([PR#200](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/200))
* Add Implementation details in DG ([PR#230](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/230))
* Added Tests for Tag Commands ([PR#235](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/235))
* Enable assertions for Week 10 Team Task ([PR#241](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/241))
* Update UG  ([PR#261](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/261))
* Update DG ([PR#278](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/278))
* Small Fixes for DG Headers for Use Cases ([PR#281](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/281))
* Minor DG updates ([PR#287](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/287))
* Fix errors for UG Draft ([PR#292](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/292))
* Update Images and Admonitions in UG ([PR#306](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/306))
* DG Minor Updates ([PR#319](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/319))
* Fix minor errors for DG ([PR#334](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/334))
* Reformat DG User Stories Table  ([PR#419](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/419))
* Fix UG Bugs reported in PED ([PR#420](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/420))
* Fix bugs for renametag command
