---
layout: page
title: Jeremy Toh's Project Portfolio Page
---

### Project: Survin

Survin is a desktop application for surveyors to use to keep track of people they have surveyed. The surveyor can easily follow up with people they have surveyed for additional information or for confirmation. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

-   **New Feature**: `mark` and `unmark` commands.

    -   What it does: Allows the user to mark whether a certain survey of a person is done. Users can use unmark to set it as not done.
    -   Justification: This feature allows the user to track whether surveys are completed or not, which helps the user to better keep track of the progress of the surveys.
    -   Highlights: Implementing this feature necessitate a change in how data is stored since we have to store a new boolean field `isDone` in `Survey` to ensure that the status of a survey is persistent.

-   **Updated Feature**: `delete` surveyees by attributes.

    -   What it does: Allows the user to delete surveyees by `RACE`, `RELIGION` or `SURVEY`.
    -   Justification: This is particularly helpful for surveyors since they might want to delete all persons that is in a particular survey.

-   **New Feature**: `undo` command.

    -   What it does: allows the user to undo all previous commands one at a time. Note that we only implemented `undo` but not `redo`.
    -   Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    -   Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    -   Credits: Borrowed heavily from AddressBook-4 with little changes.

-   **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=deepimpactmir&breakdown=true)

-   **Project management**:

    -   Upgraded the versions of `gradle`, `junit`, `javafx` in `build.gradle` [\#83](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/83)

-   **Enhancements to existing features**:

    -   To be added soon

-   **Documentation**:

    -   User Guide:
        -   Add documentation for the features `delete`, `mark`, `undo`.

-   **Community**:

    -   To be added soon

-   **Tools**:

    -   To be added soon

-   _{you can add/remove categories in the list above}_
