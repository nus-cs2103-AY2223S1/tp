---
layout: page
title: Zhang Weiqiang's Project Portfolio Page
---

### Project: PetCode

PetCode is a software app that aims to facilitate better working experience and boost business management efficiency for pet sale coordinators.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort the items in buyer/supplier/deliverer/order/pet list.
    * What it does: It allows user to sort different list by the supported keys in ascending order. 
    * Justification: This feature improves the product significantly because originally any new order/pet/contact added to the program will be automatically appended to the end of their respective list, there were no ways to reorder the items in the list. If the user wants to prioritize the orders that are more urgent, he/she has to scroll through the entire order list to manually compare and choose. Therefore, the application should provide a convenient way to save the hassle.
    * Highlights: This feature supports entering multiple keys for sorting a single list. The later keys are used to break ties arise from sorting using previous keys. For instance, `sort pet height weight` will sort the pets by their heights, for two pets with the same height, their relative sequence will be decided by their weights. 

* **New Feature**: Added the ability to check which item belongs to which contact.
   * What it does: It checks a contact at specified index, the application will display different windows for each list input. <br> For `check buyer 1`, it will display the list of orders from buyer at buyer list index 1. <br> For `check supplier 1`, it will display the list of pets from supplier at supplier list index 1. <br> For `check order 1`, it will display the buyer of the order at order list index 1. <br> For `check pet 1`, it will display the supplier of the pet at pet list index 1. <br>
   * Justification: This feature improves the product significantly because originally in the order list, the only buyer related information displayed is the name of the buyer. In order to locate the buyer of a particular order, the user has to memorise the name and navigate to the buyer list look for the matched name. This approach is undesirable and is unlikely to identify the correct buyer, as the user could misremember the names or there could be people with the same names. Therefore, the application should provide a conveninet way to rectify the problem.  
   * Highlights: This feature changes the UI display to provide instant feedback to the user.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wweqg&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Update JavaCI and CodeCov badge [\#61](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/61) [\#62](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/62)
    * Managed release `v1.3` on GitHub.

* **Enhancements to existing features**:
    * Added five unique lists in the application, added and updated relevant methods in logic, model, storage and other class to fit the changes [\#90](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/90) [\#94](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/94)
    * Extended the list command to support five different lists, enhanced the list command by adding the functionality to display different lists [\#164](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/164)
    * Extended the edit command to support three different lists [\#202](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/202)
    * Fixed several bugs [\#222](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/222), [\#234](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/234), [\#237](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/237), [\#239](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/239), [\#244](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/244), [\#261](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/261), [\#262](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/262), [\#264](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/264), [\#265](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/265)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `sort` and `check` [\#177](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/177/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    * Developer Guide:
        * Updated the UML diagram for storage package.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#211](https://github.com/AY2223S1-CS2103T-T09-2/tp/issues/211) [\#305](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/305)
    * Reported bugs and suggestions for other teams in the class (examples: [\#9](https://github.com/wweqg/ped/issues/9), [\#10](https://github.com/wweqg/ped/issues/10), [\#11](https://github.com/wweqg/ped/issues/11),  [\#12](https://github.com/wweqg/ped/issues/12).)

* **Tools**:
    * Used JavaFX for UI related features.


