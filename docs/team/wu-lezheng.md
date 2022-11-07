---
layout: page
title: Wu Lezheng's Project Portfolio Page
---

### Project: PetCode

PetCode is a software app that aims to facilitate better working experience and boost business management efficiency for pet sale coordinators.

Given below are my contributions to the project.

* **Functionality**: Added the `order` package to the `model` component. (Pull request: [#82](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/82))
  * What it does: Added an `Order` class and its subcomponents.
  * Justification: These models lay the foundation so that existing and added operations/commands could be performed using these models.
  * Highlights: Some of the SOLID principles of OOP are applied in these classes.

* **Functionality**: Extended `person` to different categories. (Pull request: [#82](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/82))
  * What it does: Extended the `Person` class into `Buyer`, `Supplier`, and `Deliverer`, with their respective attributes.
  * Justification: These models lay the foundation so that existing and added operations/commands could be performed on them.
  * Highlights: Some of the SOLID principles of OOP are applied in these classes.

* **New Feature**: Added a popup window for adding command. (Pull request: [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159))
  * What it is about: Added a popup window with prompt text for adding buyers with orders and suppliers with pets, without the need to input any prefixes.
  * Justification: There is repetitive entering of multiple indexes when the users want to add a buyer with orders or to add a supplier with pets, which is very demanding in terms of memorisation.
  * Highlights: The popup window is designed to improve the user experience (UX), with keyboard shortcuts and prompt texts. It also shows how UI, model and logic components can be linked.

* **New Feature**: Extended the `EditCommand` (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
  * What it does: Extended the `EditCommand` to `EditBuyerCommand`, `EditDelivererommand` and `EditSupplierCommand`.
  * Justification: `Buyer`, `Deliverer` and `Supplierr` can have different attributes. Making a separate `EditCommand` for each of them allows customised editing even more distinct attributes are added to them in the future.
  * Highlights: This makes use of polymorphism.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wu-lezheng&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Wu-Lezheng&tabRepo=AY2223S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.3` on GitHub ([Link to v1.3 release](https://github.com/AY2223S1-CS2103T-T09-2/tp/releases/tag/v1.3.1))
  * Recorded and managed the majority of the meeting discussions in the meeting minutes in the Google Drive folder.
  * Created and assigned issues to team members on GitHub.
  * Created labels and categorised issues on GitHub.

* **Enhancements to existing features**:
  * Updated the style of GUI and its layout. (Pull request: [#142](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/142))
  * Categorised all Java classes into their respective packages. (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
  * Wrote additional tests to increase test coverage.
  * Fixed several bugs. (Pull requests: [#325](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/325), [#362](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/362))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature: `Adding a person with a popup window`. (Pull request: [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159))
    * Added documentation for the feature: `Editing attributes of a contact`. (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
    * Added documentation for the feature: `Listing contacts or items`. (Pull request: [#207](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/207))
    * Proofread the whole UG.
      (Pull requests: [#356](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/356), [#364](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/364), [#365](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/365))
  * Developer Guide:
    * Updated the `UI component`. (Pull request: [#180](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/180))
    * Added implementation of `Display of person list`. (Pull request: [#180](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/180))
    * Added implementation of `Pop-up window for add command`. (Pull request: [#204](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/204))

* **Community**:
  * PRs reviewed (with non-trivial comments):
    [\#156](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/156#discussion_r1000077198),
    [#352](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/352).
  * Reported bugs and suggestions for other teams in the class:
    [#165](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/165),
    [#170](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/170),
    [#178](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/178),
    [#186](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/186),
    [#195](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/195).

* **Tools**:
  * Used JavaFX and Scene Builder to modify the UI.
  * Used PlantUML to add more UML diagrams in the developer guide.
