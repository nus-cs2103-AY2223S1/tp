---
layout: page
title: Ryan Chua's Project Portfolio Page
---

### Project: SectresBook

SectresBook helps secretaries to maintain all the information of the members of their club by collating a list of identifiable information, past records and future tasks.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w12-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ryanczx&tabRepo=AY2223S1-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * Enhancement: Restructure `Tag`
    * Restructure `Tag` such that SectresBook has a `UniqueTagMapping` that `Person` and `Note` references. This allows SectresBook to only require one `Tag` object per unique tag, instead of each `Person` and `Note` needing their own `Tag` objects.
    * In addition, every `Tag` has a `Person` list containing references of `Person` objects that are "tagged" with the `Tag`.
    * This would allow for future features to better make use of `Tag`.
  * Feature: Locating persons and notes by tag : `findTag`
    * What it does: Finds `Person`s and `Note`s in SectresBook that have the given tags.
    * Justification: This feature improves the product because secretaries can give `Person`s and `Note`s tags and find them by those given tags when they are needed in the future.
  * Enhancement: Added the `Tag` functionality to `Note`
    * Updated `Note` to have a `Tag` set.
    * Updated `Note` commands to work with `Tag` functionality.
  * Added tests to ensure that `Tag` functionality works properly

* **Contributions to the UG**:
  * Added section for Locating persons and notes by tag : `findTag`
    * Brief description and explanation
    * Screenshot for example
  * Did final polishing and checks for UG
    * Language use
    * Formatting
    * Organization
    * Links
    * Conversion to final PDF

* **Contributions to the DG**:
  * Updated `Model` component UML class diagram to reflect the correct associations between `UniqueTagMapping`, `Person`, `Note` and `Tag`.
  * Added section for Find Person by Tag feature
    * Brief description, explanation and design considerations
    * Sequence diagram

* **Contributions to team-based tasks**:
  * Setting up the correct Codecov and CI links at the start.
  * Managed the release v1.3.trial for the trial JAR activity.

* **Review/mentoring contributions**:
  * Read through, checked and approved team PRs.
  * Occasionally offered suggestions or fixes for typos.

* **Contributions beyond the project team**:
  * Gave comments and suggestions for the bugs I found during the PE-D activity.
