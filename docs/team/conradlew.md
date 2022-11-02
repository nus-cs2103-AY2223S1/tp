---
layout: page
title: Conrad's Project Portfolio Page
---

### Project: DevEnable

DevEnable is a product for developers who have to manage different projects spread
across multiple GitHub repositories. It helps developers organize information about
different projects they are working on in one place so that they may prioritize and
have an overview. It removes the hassle of having to navigate to our/organization’s
GitHub repo every time and manually check different pages to see which tasks require
immediate attention. The user interacts with it using a CLI, and it has a GUI created
with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to pin/unpin entities (projects, clients and issues)
    * What it does: Allows the user to pin entities to the top of their respective lists one at a time. Preceding pin commands can be reversed by using the pin command again on the same entity.
    * Justification: This feature improves the product significantly because a user can value some entities more than others and the application should provide a way for users to keep the more important entities easily visible.
    * Highlights: This enhancement affected the existing sort commands. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to the existing sort commands.

* **New Feature**: Added a list command that allows the user to navigate to the different entity lists commands using the CLI.

* **New Feature**: Added a default view command that allows the user to choose what entity list they would prefer to see on opening the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=conradlew&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.3.trial` - `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the storage, model and UI to support display of multiple entities (Pull requests [\#64](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/64), [\#65](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/65))
    * Wrote additional tests for implemented features to increase coverage (Pull requests [\#104](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/104), [\#133](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/133))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `default view` and `pin` [\#141](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/141)
    * Developer Guide:
        * Added implementation details of the `list`, `default view` and `pin` features [\#106](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/106).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#30](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/30), [\#80](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/80), [\#62](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/62)
    * Reported bugs and suggestions for other teams in the class [(Link)](https://github.com/ConradLew/ped/issues)
