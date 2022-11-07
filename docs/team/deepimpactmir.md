---
layout: page
title: Jeremy Toh's Project Portfolio Page
---

### Project: Survin

Survin is a desktop application for surveyors to use to keep track of people they have surveyed. The surveyor can easily follow up with people they have surveyed for additional information or for confirmation. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

-   **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=deepimpactmir&breakdown=true)

-   **New Feature**: `mark` and `unmark` commands.

    -   What it does: Allows the user to mark whether a certain survey of a person is done. Users can use unmark to set it as not done.
    -   Justification: This feature allows the user to track whether surveys are completed or not, which helps the user to better keep track of the progress of the surveys.
    -   Highlights: Implementing this feature necessitate a change in how data is stored since we have to store a new boolean field `isDone` in `Survey` to ensure that the status of a survey is persistent.

-   **New Feature**: `undo` command.

    -   What it does: allows the user to undo all previous commands one at a time. Note that we only implemented `undo` but not `redo`.
    -   Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    -   Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    -   Credits: Borrowed heavily from AddressBook-4 with little changes.

-   **Enhancements to existing features**: `delete` surveyees by attributes.

    -   What it does: Allows the user to delete surveyees by `RACE`, `RELIGION` or `SURVEY`.
    -   Justification: This is particularly helpful for surveyors since they might want to delete all persons that is in a particular survey.
    -   Highlights: Since we support a person holding multiple surveys, we have to make sure that the person is only deleted if the person only has that one survey.

-   **Enhancements to existing features**: `add` command supporting multiple surveys

    -   What it does: Change the behaviour of `add` such that if the new person added only differs from the existing person in survey, then, `add` would add the new survey to the existing person instead of adding a new person.
    -   Justification: Since the user probably intended to use add new surveys to the existing person instead of creating an identical person, this change ensures that `add` behaves as expected to the user.

-   **Project management**:

    -   Upgraded the versions of `gradle`, `junit`, `javafx` in `build.gradle` [\#83](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/83)

-   **Documentation**:

    -   User Guide:
        -   Add documentation for the features `delete`, `mark`, `undo`.
    -   Developer Guide:
        -   Add new diagrams for the enhancement to `delete` [\#125](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/125/)

-   **Review contributions**:

    -   Selected PR reviews: [\#81](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/81), [\#113](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/113)
