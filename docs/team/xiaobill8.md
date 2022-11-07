---
layout: page
title: Bill's Project Portfolio Page
---

### Project: TaskBook

TaskBook is a desktop address book and task assignment application used for efficiently managing contacts and tasks.

Given below are my contributions to the project.

* **New Feature**: Added Task and Tasklist models ([#159](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/159)).
    * What it does: Task model encapsulates an individual task that can be added and displayed.
    * Justification: This feature improves the product significantly because it serves as the foundation for Task functionalities.
    * Highlights: The feature is defensively written such that all Tasks follow a standardised format. The model follows OOP principles closely, and was implemented with the intention of allowing for the extension of custom tasks easily.

* **New Feature**: Added Storage for Tasks ([#126](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/126)).
    * What it does: TaskBookStorage allows for the storage of Tasks in a JSON format.
    * Justification: This feature allows for the saving and loading of Tasks so that users can reopen the application to their last saved state.
    * Highlights: The feature is defensively written to prevent the save file from becoming corrupted. Furthermore, the storage of Tasks is independent of storage of Contacts even though they are in the same JSON file, this minimizes dependencies between them and thus possible errors or conflicts. The storage of Tasks also uses Jackson's `@JsonTypeInfo` and `@JsonSubTypes` for seamless saving and loading of Tasks while maintaining the specific Task subtype.
    * Credits: Integrated Task storage into existing Contact storage implemented in AB-3. Used annotations from the Jackson library.

* **New Feature**: Added self-assignment for Tasks ([#178](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/178).
    * What it does: Since all tasks must be attached to a contact, this feature allows the user to self-assign tasks that are not assigned to or by another person.
    * Justification: The static Myself person is a constant that is not stored in the contact list and cannot be edited by the user. This ensures that the static Myself person cannot be incorrectly edited by the user.
    * Highlights: The feature is defensively written to prevent the static Myself person from becoming corrupted. This ensures that the self-assignment commands do not break as they rely on the static Myself person.

* **Team tasks**:
    * [#116](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/116) Refactored existing "AddressBook" instances to "TaskBook".
    * [#268](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/268) Managed Integration Tests.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=xiaobill8&breakdown=true)

* **Documentation**:
    * User Guide:
        * [#82](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/82) Added instructions for Deadline command.
        * [#178](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/178) Added instructions for adding self-assigned Tasks.
        * [#243](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/243) Updated Quick Start with a valid Todo command.
        * [#303](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/303) Updated Contact Delete command description.
    * Developer Guide:
        * [#82](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/82) Added Non-functional requirements
        * [#163](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/163) Added implementation of Tasks.
        * [#163](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/163) Updated Model component and corresponding Model UML diagram
        * [#163](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/163) Updated Storage component explanation and corresponding Storage UML diagram
        * [#292](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/292) Added Effort Section
        * [#300](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/300) Updated Manual Testing Section

    