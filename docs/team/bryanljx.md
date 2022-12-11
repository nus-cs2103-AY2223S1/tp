---
layout: ppp
title: Bryan Lim Jing Xiang's Project Portfolio Page
---

<!-- markdownlint-disable-next-line blanks-around-headers -->
### Project: FoodRem
{: .no_toc}

<!-- markdownlint-disable-next-line proper-names -->
{{ site.data.foodrem.about.summary }}

Given below are my contributions to the project.

**Refactoring**:

* **Refactored Feature**: Refactored `tag` model [[PR#140]]
  * What it does: encapsulates the idea of `tag` so that users can create and add `tag` to `item`.
  * Justification: This feature allows `item` to be classified into different categories. The refactoring allows for creating of any tags as well as updating of the restrictions we would like to place on names for `tag`.
  * Highlights: This refactoring allows users to create any `tag` they want. Through the use of regex, it also updates the restrictions we would like to place on names for `tag`, such as character limit or disallowed characters.

* **Refactored Feature**: Added storage for `tag` model [[PR#223]]
  * What it does: Adds storage functionality for the refactored `tag` model.
  * Justification: This feature is needed as we needed to store a separate list for `tag` besides the `item` list.

**New feature**:

* **New Feature**: Added `renametag` command [[PR#179]]
  * What it does: Allows for renaming of existing `tag`.
  * Justification: Users might make errors in creating `tag` so we want to allow them to rename `tag`.

* **New Feature**: Added `deletetag` command [[PR#199]]
  * What it does: Allows for deletion of existing `tag`.
  * Justification: To allow users to delete `tag` no longer used.

* **New Feature**: Added `filtertag` command ([[PR#302]], [[PR#353]])
  * What it does: Allows for filtering for `item` by `tag`.
  * Justification: This feature is the core functionality added by `tag`. We can classify `item` by `tag` so naturally we would like to filter by `tag`.
  * Credits: Thanks to `eugenetanwl3881` for catching a bug with the Ui related to `filtertag`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=bryanljx&tabRepo=AY2223S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Wrote tests for existing features
