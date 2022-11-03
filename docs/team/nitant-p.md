---
layout: page
title: Nitant Panicker's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a desktop address book application designed for National University of Singapore (NUS) Computer Science Undergraduates to keep track of their University Social Circle. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

* **Enhancements to existing features**: Added the ability to find contacts by any field and added a new search mode.
      
  * What it does: allows the user to find and filter contacts by any field used in our program.

  * Justification: Previously, the find command only supported the `name` field. This reduces the usefulness of the find command. There are many use cases, as listed in the Developer Guide, to search by other fields.

  * Highlights: Its implementation was done in a way to make it as extendable as possible as our team was constantly adding new fields to contacts. To support `OR` and `ALL` search mode for some fields, efficiency and practicality was a factor in deciding its implementation before settling on set operations.


  * Wrote additional test cases for existing features to increase coverage from 57.02% to 65.06% [#192](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/192). Test coverage increase unknown: [#196](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/196) [#35](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/35)
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nitant-p&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation:**

  * User Guide
    * Added documentation for the feature `find` [#118](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/118)
  * Developer Guide
    * Added sequence diagram for `find` command [#118](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/118) 
    * Wrote user stories for `find` [#118](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/118)
    * Added use cases for `find` command [#118](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/118)


* **Community**:

  * PRs reviewed with non-trivial review comments: [#31](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/31/files#r985051245) [#54](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/54#issuecomment-1279929158)



