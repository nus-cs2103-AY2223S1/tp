---
layout: page
title: Law Sean Meng's Project Portfolio Page
---

### Project: Waddle

Waddle is a simple, no-frills travel planning application catered to people who love doing everything on their keyboards.

Given below are my contributions to the project.

* **New Feature**: Add an edit item command. [#49](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/49)
  * What it does: This command allows users to edit an item in their unscheduled item list.
  * Justification: This feature improves the product significantly as users can easily edit minor details in the
    items in their item list without having to delete and re-add the entire item.
  * Highlights: Writing the `EditItemDescriptor` class allowed me to learn how to use a defensive copy to prevent
    the user from making any unwanted changes to the original copy of the item.
* **New Feature**: Add a multi index class. [#76](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/76)
  * What it does: Allows for access of items inside the day list, using a more complex version of an `Index`.
  * Justification: There is a need for a different indexing of items that are in the day list compared to the
    unscheduled item list. This is to allow users to easily access items in both lists from the same item-planning
    page.
  * Highlights: It was challenging coming up with a way to represent the different indexing that would satisfy our
    needs.
* **New Feature**: Add a plan and unplan command. [#85](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/85), [#87](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/87)
  * What it does: Allows for scheduling and unscheduling of items in the wishlist, to a specific day and time.
  * Justification: As a travel planning application, users will need to schedule their activities in order to create a
    cohesive itinerary using our app.
  * Highlights: There was plenty of collaboration and discussion with team members on where to handle each aspect of
    the plan and unplan commands, such as shifting the inner workings to the `Itinerary` and `Day` classes.
    


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=seox123&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zFR=false&zA=seox123&zR=AY2223S1-CS2103T-W11-4%2Ftp%5Bmaster%5D&zACS=153.1875&zS=2022-09-16&zFS=seox123&zU=2022-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos)

* **Project management**:
  * Managed releases `Waddle v1.4.1` and `1.4.2` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Add a `Priority` field to `Item` class. [#61](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/61)
  * Fix bugs and close issues. [#100](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/100), [#200](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/200), [#201](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/201), [#202](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/202)
  * Write test cases, focussing mainly on `Command` and `Parser` classes. [#53](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/53), [#228](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/228), [#230](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/230), [#241](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/241)

* **Documentation**:
  * User Guide:
    * Updated user guide with the newly-added commands like `plan`, `unplan`, `free`, and more. [#117](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/117)
    * Updated Ui snapshot of the application. [#120](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/120)
  * Developer Guide:
    * Added multiple use cases to the developer guide. [#51](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/51)
    * Added new proposal to unplan items. [#67](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/67)
    
* **Team-based tasks**:
  * Conducted testing of app and opened issues. [#101](https://github.com/AY2223S1-CS2103T-W11-4/tp/issues/101), [#102](https://github.com/AY2223S1-CS2103T-W11-4/tp/issues/102)
  * Made necessary changes and renaming of classes to be more suitable with our application. [#53](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/53), [#85](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/85), [#248](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/248)

* **Community**:
  * Assisted other teams in detection and reporting of bugs during the PE dry run.
