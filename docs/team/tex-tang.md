---
layout: page
title: Tang Zhi Lin's Project Portfolio Page
---

### Project: GithubContact

GithubContact is an address book **integrated with Github's Public API**, targeted at software engineering project
managers to help them communicate and collaborate with their teams smoothly.

Given below are my contributions to the project.

* **New Feature**: Added the ability to switch between different pages (e.g. people list page, person details page, 
help page).
  * What it does: allows the user to navigate between pages with arrow keys and shortcut keys.
  * Justification: This feature improves the user experiences because the user can navigate between pages without using 
    mouse, as we are building keyboard-friendly application. For example, `Esc` for back and `F1` for help are very 
    intuitive to users.
  * Highlights: 
    * This feature required decent knowledge in `Observable` and `Event` in javaFX.
    * To implement it correctly, it required hours to test it manually as it have too many possible cases (e.g. user 
      switch to mouse interaction in the middle) and we do not have GUI end-to-end testing.
  * Credits: This idea is heavily inspired by [Raycast](https://www.raycast.com/) with some simplification.

* **New Feature**: Added the ability to navigate back to previous page with `back` command or `Esc` key.
  * What it does: application will record the page history of users, and allow users to go back to previous page with
    `back` command or `Esc` shortcut key.
  * Justification: This feature improves the user experiences because it is intuitive to users (i.e. it is very common
    to users such as using browser).
  * Highlights: This feature required knowledge in data structure (i.e. stack) and in-depth analysis of page switching 
    so it won't cause storing infinite pages in history.

* **New Feature**: Added the ability to sort people.
    * What it does: allows the users to sort people by name, role, address in either ascending or descending order.
    * Justification: This feature improves the user experiences because it users do not need to scroll to bottom to find
      person. In addition, people sorted with name in ascending order is intuitive to users.
    * Highlights: 
      * This feature required knowledge in javafx observable `SortedList` and integration with UI because it might 
        break `delete` command as it change the index of a person.
      * This feature needs change in abstraction of `Model` and `Logic` because we want to preserve the usage of `find` 
        which also modify the list.

* **New Feature**: Added Help pages that allows user to open help page with `help` or `F1` key
  * What it does: allows the users to open help page to refer command usages with `help` command or `F1` key.
  * Justification: This feature allows the users to check command usages without referring to external source (e.g.
    user guide).
  * Highlights:
    * This feature required knowledge in javafx `Event` to listen keyboard events from users and open up help page when 
      user pressed `F1` key.
    * This feature required in-depth knowledge in javafx layout-ing and styling in order to build help pages. (e.g. 
      monospace font in command text, fixed-width commands column)

* **New Feature**: Added Person detail page that allows user to view contact detail and GitHub repositories
  * Credits: 
    * Repositories List is adopted from Person List panel

* **New Feature**: Added argument aliases that allows user to pass argument to command with different aliases (e.g. `n/`
  and `name/` will have the same effect).
  * Highlights:
    * This feature is challenging as it required modifications in `ArgumentsTokenizer`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Tex-Tang)

* **Project management**:
  * Managed release `v1.2`, `v1.3trials` ([2 releases](https://github.com/AY2223S1-CS2103T-W08-2/tp/releases)) with 
    ChangeLog.
  * Created and assigned GitHub issues to team members.

* **Enhancements to existing features**:
  * Updated the GUI 
    [#104](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/104)
    [#223](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/223)
    [#226](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/226)
    * Added shortcuts list to footer  
    * Polish styling of PersonList
  * Tests
    * Modify tests for `ArgumentTokenizer` [#138](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/138)
    * Added tests for person's attributes [#142](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/142)
  * Model
    * Make address fields optional [#140](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/140)
  * Remove Edit command [#101](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/101)
    
* **Documentation**:
  * User Guide
    * Added Documentation for `sort` and `back`
    * Added brief command usages and examples for every command in order to allow team to modify further.
  * Developer Guide
    * Added implementation detail (e.g. explanation and UML diagram) for switching page.

* **Community**:
  * PRs reviewed with in-depth discussion (Ranked by priority):
    [#141](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/141)
    [#144](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/144)
    [#149](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/149)
  * Reported bugs and suggestions for other teams in the class: 
    [T13-2](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/68#discussion_r996427389)
