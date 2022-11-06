---
layout: page
title: Wu Lezheng's Project Portfolio Page
---

### Project: PetCode

PetCode is a software app that aims to facilitate better working experience and boost business management efficiency for pet sale coordinators.

Given below are my contributions to the project.

* **Functionality**: Added the `order` package to the `model` component. (Pull request: [#82](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/82))
  * What it does: Added an `Order` class and its different components such as `PriceRange` and `AdditionalRequests` to simulate pet trade in the real-world.
  * Justification: These models provide abstraction of real-world objects and lay the foundation of our project, so that existing and added operations/commands could be performed using these models.
  * Highlights: Some of the SOLID principles of OOP are applied in the creation of these classes.

* **Functionality**: Extended `person` to different categories in the `model` component. (Pull request: [#82](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/82))
  * What it does: Extended the `Person` class into three categories -- `Buyer`, `Supplier`, and `Deliverer`, with their respective attributes.
  * Justification: These models stimulate the different parties involved in pet sales and lay the foundation of our project, so that existing and added operations/commands could be performed on them.
  * Highlights: Some of the SOLID principles of OOP are applied in the creation of these classes.

* **Functionality**: Updated the layout of GUI and how each section of the GUI works to suit our app. (Pull request: [#142](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/142))
  * What it is about: Resigned the UI to have different list panels and list cards for different categories of `Person` as well as `Order` and `Pet`,
  * Justification: Since our product classifies `Person` into three different categories -- `Buyer`, `Supplier`, and `Deliverer`, it is necessary to have a **separate list panel** for each of these three type of `Person`. Initially the UI also primarily makes use of `label` of JavaFx, which is plain text.
                   This is not helpful if we want to display more information in an organised and visually pleasant way. Therefore, we need to rework the whole UI to meet our project's needs.
  * Highlights: This requires a deep understanding of the functions and features of JavaFX and Scene Builder. I also need to think from the users' perspective to make the UI more clear-cut and easier to understand.

* **Functionality**: Added a popup window for adding buyer and supplier. (Pull request: [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159))
  * What it is about: Added a popup window with prompt text for adding `Buyer` with any number of `Order`and `Supplier` with any number of `Pet`, without the need to input any prefixes.
  * Justification: If the user wants to add a `Buyer` with multiple `Order`, or add a `Supplier` with multiple `Pet`, there will be repetitive entering of a lot of prefixes.
                   The user needs to memorise the prefixes for each attribute of the person or item, and they may get lost when entering such a long command line. Therefore, we recognise the need for a pop-up window for adding a `Person` (`Buyer` or `Supplier` for the current version), which has text fields that prompt the user to enter the required information without prefixes.
  * Highlights: The popup window is designed with the aim to improve the user experience (UX), with keyboard shortcuts to cater to users who can type fast and prompt text to remind the users what to input. When required information is not entered or in the wrong format, the users will also be notified. It also demonstrates my ability in using Scene Builder and JavaFX.

* **New Feature**: Added command `AddCommandWithPopupWindow`. (Pull request: [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159))
  * What it does: Added a command that invokes the popup window and generates an `AddBuyerCommand` or `AddSupplierCommand` during saving in the popup window.
  * Justification: This is the internal working of how the popup window can add a buyer/supplier to the storage, without the need for the user to perform additional tasks except pressing the save button or use `CTRL + S` in the popup window.
  * Highlights: The command demonstrates how the UI can be linked to `model` and `logic`, as it requires some understanding of all three parts of the code.

* **New Feature**: Extended the `EditCommand` to three categories: edit buyer command, edit supplier command, edit deliverer command. (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
  * What it does: Built up on the `EditCommand` initially modified by @wweqg and extended it to `EditBuyerCommand`, `EditDelivererommand` and `EditSupplierCommand`.
  * Justification: `Buyer`, `Deliverer` and `Supplierr` can have different attributes. Making a separate `EditCommand` for each of them allows customised editing and also leaves space for future implementations if more distinct attributes are added them.
  * Highlights: This makes use of polymorphism as there is a need to abstract out the common logic in all three types of `EditCommand`.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wu-lezheng&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Wu-Lezheng&tabRepo=AY2223S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
  * Managed releases `v1.3` on GitHub ([Link to v1.3 release](https://github.com/AY2223S1-CS2103T-T09-2/tp/releases/tag/v1.3.1))
  * Recorded and managed the majority of the meeting discussions in the meeting minutes in the Google Drive folder.
  * Allocated tasks to team members in the meeting minutes in the Google Drive folder.
  * Created and assigned issues to team members on GitHub.
  * Created labels and categorised issues on GitHub.


* **Enhancements to existing features**:
  * Updated the style of GUI to have rounded corners. (Pull request: [#142](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/142))
  * Categorised all Java classes into their respective packages. (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
  * Adapted the `CommandResult` class to the added/modified commands.
  * Wrote additional tests to increase test coverage.
  * Fixed several bugs. (Pull requests: [#325](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/325), [#362](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/362))


* **Documentation**:
  * User Guide:
    * Added documentation for the features:
      * `Adding a person with a popup window` (Pull request: [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159))
      * `Editing attributes of a contact` (Pull request: [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205))
    * Edited the existing documentation of features: `Listing contacts or items`.
    * Edited the documentation of features:
      * `Adding a buyer`, `Adding a deliverer`, `Adding a supplier`, `Adding a pet to a supplier`, `Adding an order to a buyer` (Pull request: [#325](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/325))
      * `Sorting contacts` (Pull request: [#365](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/365))
    * Proofread the whole UG and made amendments.
      (Pull requests: [#356](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/356), [#364](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/364), [#365](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/365))
  * Developer Guide:
    * Updated the `UI component`. (Pull request: [#180](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/180))
    * Added implementation details of the `Display of person list`. (Pull request: [#180](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/180))
    * Added implementation details of the `Pop-up window for add command`. (Pull request: [#204](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/204))
    * Updated `Deleting a buyer` under `Appendix: Instructions for manual testing`. (Pull request: [#325](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/325))
  * JavaDoc: JavaDocs are added along the way for all classes related to the added or modified features and functionalities stated above.
    (Pull requests: [#119](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/119),
                    [#142](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/142),
                    [#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159),
                    [#205](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/205),
                    [#372](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/372))


* **Community**:
  * PRs reviewed and clarifications on own PRs (with non-trivial comments):
    [\#156](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/156#discussion_r1000077198),
    [\#159](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/159#discussion_r1005400272),
    [#352](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/352).
  * Reported bugs and suggestions for other teams in the class:
    [#165](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/165),
    [#170](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/170),
    [#178](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/178),
    [#186](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/186),
    [#195](https://github.com/AY2223S1-CS2103T-W08-2/tp/issues/195).


* **Tools**:
  * Used JavaFX to modify the UI.
  * Used Scene Builder to create more FXML files.
  * Used PlantUML to add more UML diagrams in the developer guide.
