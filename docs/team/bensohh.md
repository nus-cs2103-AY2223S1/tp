---
layout: page
title: Benjamin Soh's Project Portfolio Page
---

### Project: bobaBot

bobaBot is a desktop application for managing customers’ membership details. It is optimized for Command Line Interface (CLI) while retaining some benefits of the Graphical User Interface (GUI). If you are a cashier working at a bubble tea shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as compared to other GUI applications.

bobaBot contains a database of customers’ information and supports operations to add, update, delete and even find customers based on various inputs. Every entry in the database contains the customer's name, phone number, email address, birthday month, reward points and membership tags.

bobaBot is written in Java and has about 13 kLoC.

Given below are my contributions to the project.
* **New Feature**: `UndoCommand` (Pull Requests [#141](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/141))
    * **What it does**: This feature allows the user to undo previously executed commands by storing the previous versions of bobaBot and retrieving them when the `undo` command is executed.

    * **Justification**: When the user makes a mistake and executed an unintended command, they can easily execute `undo` to return the state before executing the unintended command. For e.g., if the user accidentally deletes a customer from bobaBot, they can call `undo` to return to the previous state (where the customer is not deleted) instead of having trouble finding out all the details and adding the customer back into the system again.

    * **Highlights**: This feature was implemented by drawing inspiration from the proposed feature implementation for `Undo/Redo` within AB3's Developer's Guide. The brainstorming and implementation process to fit within the entire bobaBot system required in-depth understanding of the highly-nested files and facade classes. Multiple bugs were found in the initial phase of the implementation because `undo` is linked to almost every single command/feature within bobaBot.

    *  **Credits**: Inspiration from AB3's Developer's Guide


* **New Feature**: `RedoCommand` (Pull Requests [#141](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/141))
    * **What it does**: This feature allows the user to revert the previously executed `undo` commands by storing the versions of bobaBot and retrieving them when the `redo` command is executed.

    * **Justification**: When the user makes a mistake and executes an unintended `undo` command, they can easily execute `redo` to return the state before executing the unintended `undo` command. For e.g., if the user adds a customer into bobaBot and accidentally executes an `undo` command (returns to state where the customer is not added into bobaBot), they can call `redo` to return to the state (where the customer is added) instead of having trouble finding out all the details and adding the customer back into the system again.

    * **Highlights**: Similar to `undo`, this feature was implemented by drawing inspiration from the proposed feature implementation for `Undo/Redo` within AB3's Developer's Guide. The brainstorming and implementation process to fit within the entire bobaBot system required in-depth understanding of the highly-nested files and facade classes. Multiple bugs were found in the initial phase of the implementation because `redo` is linked to almost every single command/feature within bobaBot.

    *  **Credits**: Inspiration from AB3's Developer's Guide


* **New Feature**: `Promotions` (Pull Requests [#150](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/150), [#154](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/154))
    * **What it does**: Renders the promotion images within a folder into the GUI so users can be aware of and double-check the ongoing promotions when customers claim a particular deal/offer.

    * **Justification**: There are often many ongoing promotions for a bubble tea shop and the user might not be aware of them if they are new to the job. This helps to keep them informed about the shop's ongoing promotions and also allows them to validate it when customers try to claim them.

    * **Highlights**: We encountered some difficulty during the process of creating our JAR file even though the IntelliJ code was running properly. After some extensive research, we found out that when we created a JAR file via Gradle, images/files in folders had to be referenced as a resource rather than the standard method of using the `File` class and referring to the items within via their file path.

    *  **Credits**: Adapted a method to reference files as a resource from [mkyong](https://mkyong.com/java/java-read-a-file-from-resources-folder/)


*  **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w09&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=bensohh&tabRepo=AY2223S1-CS2103T-W09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
    * Managed releases `v1.2.1` and `v1.3.trial` (2 releases) on GitHub
    * Managed the group's weekly meeting [notes](https://docs.google.com/document/d/1AXohjIbRHtfREuhE_MzOeW9P974NsOOpw3zL6W8-Dv8/edit)
    * Managed the UI screenshots for all the features demo


* **Enhancement to existing features**: `DeleteCommand` - Changing identifier from `index` to `PHONE_NUMBER` or `EMAIL`. (Pull Requests [#67](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/67), [#112](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/112))
    * **What it does**: This feature allows the user more flexibility in choosing the type of identifier to `delete` the customer by. Previously, the user can only `delete` customers by `index` according to AB3's implementation of `DeleteCommand`. However, bobaBot now supports `delete` via 2 types of identifiers - `PHONE_NUMBER` or `EMAIL`.

    * **Justification**: Previously, the user can only delete a customer by referencing the `index` shown on the result display and the process of deletion would often require a `find` customer first, followed by a `delete` customer based on the search result. However, this creates a dependency on `find` and is not very efficient. With the current implementation in bobaBot, users can ask for either the customer's `PHONE_NUMBER` or `EMAIL` and they can remove the customer via either identifier.

    * **Highlights**: The previous implementation was much simpler since it dealt with `index` so deletion was pretty straightforward. However, I had to revamp the entire process of deleting customers, starting with the retrieving of a particular customer via `PHONE_NUMBER` or `EMAIL` instead of `index`. It took some time to understand and navigate through the highly-nested classes in AB3 and multiple trials and errors before I got it to work.


* **Enhancement to existing features**: JavaFX - Transform the original AB3's UI to the proposed wire-framing design for bobaBot. (Pull Requests [#88](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/88), [#150](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/150))
    * **What it does**: Makes the bobaBot application more visually appealing to the user and support other features within bobaBot.

    * **Justification**: The previous UI from AB3 does not support all the features we implemented in bobaBot. Therefore, we had to change the UI to one that could support all the features in bobaBot such as the `promotions` feature. There is also a need to change the original `dark theme` to one that catered more towards our users such as a `milk tea theme`.

    * **Highlights**: This enhancement required the use of both CSS and SceneBuilder. Throughout the process, we also realised that the rendering of JavaFX may differ on different operating systems such as macOS (M1 chip) and Windows PC so there was a need for multiple individuals within the team to double-check the UI to ensure that it is working accordingly. Even though it required a lot of time to research and tamper with the fields available in SceneBuilder, it was an enjoyable process when I managed to achieve the initial wire-frame design.


* **Documentation**:
    * User Guide:
        * Added documentation for the Commands `Delete`, `Undo` and `Redo` (Pull Requests [#36](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/36), [#158](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/158/files))
        * Added screenshots for all the Commands in bobaBot (Pull Request [#207](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/207))
        * Standardise the format for notation (Pull Request [#214](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/214))
    *	Developer Guide:
         *	Added the User Stories (Pull Request [#37](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/37))
         *	Updated and added UML diagrams for `delete` Command (Pull Request [#114](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/114))
         *	Added implementation details for the Commands `delete`


* **Community**:
    * PRs reviewed (with non-trivial comments): [#139](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/139). [#143](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/143)