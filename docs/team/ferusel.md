---
layout: ppp
title: Mai Ting Kai's Project Portfolio Page
---

<!-- markdownlint-disable-next-line blanks-around-headers -->
### Project: FoodRem
{: .no_toc}

<!-- markdownlint-disable-next-line proper-names -->
{{ site.data.foodrem.about.summary }}

Given below are my contributions to the project.

* **New Feature**: Statistics feature

  * What it does: Calculates some statistics regarding the Items and Tags in FoodRem's inventory, and displays them to the user. Statistics include: Top three most commonly used tags, top three most expensive items in inventory, total cost accrued due to food waste. 
  * Highlights: These statistics were chosen after discussion as they were determined to be most useful and provided a high degree of flexibility. It was not easy to choose the statistics. 
   
* **New Feature**: `Item` class

  * What it does: Provides an internal representation of an `Item` in FoodRem. Adapted from the original AB3's `Person` class, I laid the foundation by adding the necessary attributes to represent an `Item`. I also wrote test cases for the `Item` class.
  * Justification: Represent the `Item` to be stored in a restaurant's inventory. It is necessary as it represents the business logic of the program.
  * Highlights: Improvements were made over the original `Person` class, where SWE principles, like the Open-Closed principle, was used to refactor and design new code.
  * Credits: AddressBook - Level 3's original codebase, where we adapted the `Person` class and other relevant classes.

* **New Feature**: `inc`, `dec`, `view` commands

  * What it does: `inc` allows the user to increment a chosen item by a specified quantity. `dec` allows the user to decrement a chosen item by a specified quantity. `view` displays all relevant information about an `Item`, such as the quantity, name, tags, to the user.
  * Justification: These are key features for FoodRem.

* **New Feature**: `sort` command

  * What it does: This command allows users to sort the currently displayed `Item` list by a specified criteria. This facilitates the display of statistics to the user.
  * Justification: One of the key features for FoodRem.
  * Highights: Faced complexity as it initially allowed sorting by multiple criteria, such as by name and quantity. Eventually refactored to only sort by one criteria.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Ferusel&tabRepo=AY2223S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

List of PRs is incomplete, only vital ones are shown.

* **Enhancements to existing features**:
 
  * Add Item model [[PR#143]]
  * Add Item Unit Tests [[PR#155]]
  * Add Sort Command [[PR#158]]
  * Add Increment and Decrement Command [[PR#161]]
  * Add View command [[PR#209]]
  * Add Statistics Command [[PR#360]]
  * Update find command [[PR#367]]
  * Fix stats command [[PR#372]]

* **Documentation**:

  * User Guide:
    * Add User Guide section [[PR#82]]
    * Add Acknowledgements section to User Guide [[PR#162]]
    * Add Glossary section to User Guide [[PR#163]]
    * Consolidate UG v1.3 [[PR#264]]
    * Update UG with v1.3 Features [[PR#258]]
    * Update README with v1.3 items [[PR#311]]
    * Update UG after Refactoring [[PR#318]]
    * Improve UG for v1.3 [[PR#335]]
    * Fix UG after Peer Review [[PR#351]]
    * Fix find command description in Command Summary [[PR#324]]
    * Add warning for find command [[PR#539]]
   
  * Developer Guide:
    * Add Glossary, UC3, UC4 to Developer Guide [[PR#87]]
    * Add Glossary section to Developer Guide [[PR#164]]
    * Add Sorting user stories to DG [[PR#210]]
    * Add Sort Command UML [[PR#232]]
    * Update Developer Guide [[PR#528]]

* ** Features**:
  * Add Item model [[PR#143]]
  * Add Sort Command [[PR#158]]
  * Add Increment and Decrement Command [[PR#161]]
  * Add View command [[PR#209]]
  * Add Statistics Command [[PR#360]]
  * Update find command [[PR#367]]
  * Fix stats command [[PR#372]]
   

