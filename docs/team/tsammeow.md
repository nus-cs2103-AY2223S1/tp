---
layout: page
title: Samuel Tan's Project Portfolio Page
---

### Project: PayMeLah

PayMeLah is a desktop app for **keeping track of** and **managing the debts** your friends owe you. It can also help **do simple calculations** for you, such as including GST or splitting debts amongst your friends. What’s more, it is optimised for you to do everything from just your keyboard!

Given below are my contributions to the project.

* **New Features**:
  * `finddebt` command [#78](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/78)
    * What it does: Finds debts based on loose matching of debt description.
    * Justification: This is a key feature to help the user look for debts when they do not remember the exact capitalisation of a debt description or only remember one of the words contained within it.
    * Highlights: It performs a case-insensitive search for specific words in the debt description, so a user who isn't sure whether the debt was labelled `KFC` or `kfc` can still find the relevant debts.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?tabOpen=true&tabType=authorship&tabAuthor=tsammeow&tabRepo=AY2223S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Issue creation for team tasks:
    * [#109](https://github.com/AY2223S1-CS2103T-W13-3/tp/issues/109)

* **Enhancements to existing features**:
  * Enhanced the `find` command with a large assortment of additional fields to search by [#149](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/149) [#183](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/183)
    * What it does now: Finds debts based on a variety of filters.
    * Justification: This is a key feature to help the user look for specific people using any of a large variety of search criteria.
    * Highlights: It can search on any `Person` field or `Debt` field, and also has ways to search for a range of values for certain fields.
  * Enhanced the `add` command to make all non-name fields optional [#168](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/168)
    * What it does now: Only `n/name` needs to be specified. All other fields can be left out without issue.
    * Justification: This speeds up the user's entry of data into PayMeLah when first using the app, since name is the only essential information needed. Furthermore, users may not know e.g. the phone numbers of some people.
    * Highlights: All relevant data classes in the model component got a new constant representing a field left empty.

* **Contributions to team-based tasks**:
  * Performed renaming of the project and its components from AddressBook Level-3 to PayMeLah [#60](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/60)
  * Implemented `DebtList` class to represent an immutable list of `Debt`s [#66](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/66)
  * Implemented the ability to save a `Debt` to json file [#66](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/66)
  * Fixed critical bugs related to JSON saving [#74](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/74)
  * Added the ability to test `Debt`-related features with `TypicalPersons` [#75](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/75)
  * Refactored `EditPersonDescriptor` to more general-purpose `PersonDescriptor` [#149](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/149)
  * Added utility method to create `PersonDescriptor`s from `ArgumentMultimap` [#168](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/168)
  * Added utility class `DebtsDescriptor` that describes a group of debts [#183](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/183)
  * Added utility method to create `DebtDescriptor`s from `ArgumentMultimap` [#183](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/183)
  * Added general-purpose predicate to filter by fields present in a `PersonDescriptor` or `DebtsDescriptor` [#149](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/149), [#183](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/183)
  * Implemented test utilities to construct new instances of `DebtList`, `PersonDescriptor`, `DebtsDescriptor` [#66](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/66), [#149](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/149), [#183](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/183)

* **Documentation**:
  * User Guide:
    * Documented improved find command [#149](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/149) [#319](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/319)
    * Documented finddebt command [#45](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/45) [#320](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/320)
    * Created a Glossary for the definition of terms used in the User Guide [#271](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/271)
  * Developer Guide:
    * Documented the implementation of find command [#371](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/371)
    * Wrote manual test cases for find command [#345/a6f9972](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/345/commits/a6f99728e5cf41906bf4015f03e7743b4ee31cef)

* **Community**:
  * PRs reviewed with non-trivial comments:
    * [#69](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/69)
    * [#80](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/80)
    * [#81](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/81)
    * [#83](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/83)
  * Reported bugs for other team, some listed below (accepted):
    * [#84](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/84)
    * [#86](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/86)
    * [#95](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/95)
    * [#101](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/101)
    * [#102](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/102)
    * [#110](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/110)
    * [#113](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/113)
    * [#119](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/119)
    * [#130](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/130)
    * [#131](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/131)
