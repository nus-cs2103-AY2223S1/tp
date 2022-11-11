---
layout: page
title: Eric Lee's Project Portfolio Page
---

### Project: Contactmation

Contactmation is a powerful **desktop based project and task management solution** that **helps you efficiently and
effectively manage many projects at once** through the Command Line Interface (CLI).

Contactmation will be able to help you save all your project member details, keep track of
each project, and delegate tasks to each project.

My contributions to the projects are listed below.

### Features implemented

#### Storage component

Extension of all `Storage` classes to support the `Model` component of the project.

Due to the complexity of having nested `Groups` and the fact that `Person` and `Task` may
also be nested, there is a need for conversion between a flat `json` file structure and
the tree-like structure in the `AddressBook`. 

Additionally, the `Storage` component must also
support the saving of states of `Alias`, `Macros / Custom commands`, `Abstracted attributes`,
`Task time completion date and times`. The save state of `unique ids` are also needed
that help identify each `Person`, `Group` and `Task` in their `JsonAdapted` status. This will
be needed to correctly reproduce the tree-like structure of the `AddressBook`.

Error checking for each component and attribute is also put in place to check for corrupted data.

### Code contribution

Code contribution for this project is listed in [RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=autumn-sonata&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Main Enhancements implemented

- Extension of all `Storage` classes to support the `Model` component of the project. [#62](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/62)
  - Add `JsonAdaptedAbstractDisplayItem` to abstract components for `JsonAdaptedPerson`, `JsonAdaptedGroup` 
  and `JsonAdaptedTask`. [#62](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/62)
  - Add `JsonAdaptedAbstractAttribute` to convert all `AbstractAttribute` to Json format and correctly parse them back into
  their respective classes. 
  - Add `JsonAdaptedCustomCommandBuilder` to save alias and macros.
- Updated the **testing for the storage components** that increased code coverage by `5.58%`. [#168](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/168)
- Made builder classes for **easier testing of model classes**. [#155](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/155)
  - Add `GroupBuilder`, `PersonBuilder` and `TaskBuilder` for easy building of `Group`, `Person` and `Task` during testing. [#155](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/155)
  - Add `TypicalGroups`, `TypicalPersons` and `TypicalTasks` for standardised test cases of valid `Group`, `Person` and `Task`. [#166](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/166)
  - Add `invalidGroupAddressBook`, `invalidTaskAddressBook` to existing `invalidPersonAddressBook` for checking of Json file data retrieval. [#166](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/166)
  - Add `invalidAndValidGroupAddressBook`, `invalidAndValidTaskAddressBook` to existing `invalidAndValidPersonAddressBook` for checking of errors in Json file data retrieval. [#166](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/166)
  - Add typical, duplicate and invalid Json files in `JsonAddressBookStorageTest` for testing of save and load functions in `JsonSerializableAddressBookTest`. [#166](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/166)
  - Add additional test data in `SampleDataUtil` that is a duplicate of the test data Json files. [#157](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/157)

<div style="page-break-after: always;"></div>

### Contributions to the user guide

I structured the user guide and rewrote the following sections [#172](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/172):

- Introduction
- About
  - Purpose 
  - User guide navigation
  - Contactmation Window Guide
  - Prerequisites
- Quick start
- Before you begin
  - Standardised Format Style
    - Combining the format styles
  - Constraints on placeholder words
  - Making groups within groups
- Overview of feature, basic feature and advanced features.
  - Add field command
  - Advanced
- FAQ section
- Glossary
- Advanced commands in the Commands summary section

### Contributions to the developer guide

- Wrote the storage component section. [#47](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/47)
- Added the `PlantUML` diagrams relating to the storage components in the storage section. [#47](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/47)

<div style="page-break-after: always;"></div>

### Pull Requests reviewed

Here are some of the pull requests that I have reviewed with non-trivial comments
in the code review.

- Hard coding of regex. [#151](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/151)
- Importing errors. [#167](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/167)
- Developer guide diagram missing. [#50](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/50)
