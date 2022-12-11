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

  * What it does: allows the user to tag a valid item with a valid tag ([[PR#171]], [[PR#172]])
  * Justification: This feature improves the product significantly since a user will be able to tag items with a specific tag for classification purposes. This tag will be unique and can be referenced by any item if the item contains this tag
  * Highlights: This enhancement will allow for better classification and searching for items in other commands. For instance

* **New Feature**: Added the ability untag an item ([[PR#172]])
  * What it does: Similar to tag feature, just that items can be untagged if user does not want to use that tag to classify an item anymore.

* **New Feature**: Added the ability list all tags ([[PR#200]])

  * What it does: User can call the `listtag` command to see all the tags that is available
  * Justification: User may not remember what were the tags he has created or deleted, hence will need a way to  check
  * Highlights: This feature will be useful when trying to use other tag commands since user can see all the available tags

* **Refactored feature**:
  * Refactored the `clear` command in AB3 to the `reset` command in FoodRem. This clears all items and tags stored ([[PR#160]])

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Eugenetanwl3881&tabRepo=AY2223S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Refactoring done to project packages ([[PR#149]])
  * Team tasks: Creation of issues, completing some weekly team tasks, updating AboutUs ([[PR#241]], [[PR#22]], [[Issue#456]], [[Issue#240]])

* **Testing**:
  * Wrote some tests for tag features ([[PR#235]])

* **Documentation**:

  * User Guide:
    * Added documentation for the commands `list`, `find`, `exit` [[PR#77]]
    * Add FAQ into UG [[PR#178]]
    * Add Command Summary Table [[PR#152]]
    * Updating sections and fixing bugs for UG ([[PR#261]], [[PR#292]], [[PR#306]], [[PR#420]] )

  * Developer Guide:
    * Added User Stories and Use Cases in DG ([[PR#79]], [[PR#142]])
    * Added NFRs in DG [[PR#57]]
    * Added implementation details of the `newtag` and `tag` feature. ([[PR#230]], [[PR#464]])
    * DG User Stories Table  [[PR#419]]
    * Added Instructions for Manual testing [[PR#505]]
    * Added Effort Section [[PR#503]]
    * Updating and fixing bugs in DG ([[PR#36]],[[PR#278]], [[PR#281]], [[PR#287]], [[PR#319]], [[PR#334]], [[PR#419]], [[PR#521]])

* **Community**:

  * PRs reviewed (with non-trivial review comments): ([[PR#199]], [[PR#232]], [[PR#143]])
