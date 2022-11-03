---
layout: page
title: Eugene Ong Wei Xiang's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a **contact management and task management desktop app** that aims to help NUS SoC students stay better connected to their school life, both in terms of social connections and school tasks. To better cater to NUS SoC students, SoConnect is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). SoConnect contains 42 kLoC written in Java and uses JavaFX.

Given below are my contributions to SoConnect.

* **New Feature**: Added the ability to sort a list of contacts.
    * What it does: Allows the user to sort the contact list by a selected parameter. User can sort in reverse as well as combine multiple parameters to sort the list by.
    * Justification: This feature improves SoConnect's utility as it provides the user freedom in choosing how their contact list is displayed.
    * Highlights: This enhancement affects the parameters of contacts (e.g. name, address, email, phone, tags). For every parameter stored in a contact, it is necessary to design the sorting order and implement it accordingly. It required an in-depth analysis on how every parameter should be logically sorted. The implementation too was challenging as it required changes to multiple levels of the model component of SoConnect.

* **New Features**: Added the ability to add, remove, edit, and filter tasks (referred to as todos).
    * What it does: Allows the user to manage todos in addition to contacts. Basic management features such as adding, removing, editing todos are available. Users can also filter the todos displayed.
    * Justification: This feature significantly improves SoConnect's utility as users can now use it to manage both contacts and tasks, making SoConnect multipurpose.
    * Highlights: This enhancement affects the design of SoConnect as it now stores data of todos in addition to data of contacts. It required in-depth analysis on design alternatives. The implementation too was tedious as it required heavy modifications to the model, storage, and logic components.
    * Credits: The design and implementation of the todo management features borrows from the original contact management features of AddressBook Level-3, which SoConnect is evolved from. This is to ensure that the contact management features and the todo management features of SoConnect integrate seamlessly together instead of being 2 distinctly different products.

* **Code contributed**:
    * As of 3/11/2022, I have contributed 3810 LoC. [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=eugene-ong-w-x&breakdown=true)

* **Project management**:
    * Project Lead in charge of managing deadlines and deliverables.

* **Documentation**:
    * User Guide:
        * Added documentation for the `sort` contact management feature.
        * Added documentation for `todo add`, `todo delete`, `todo clear`, `todo edit`, `todo show` todo management features.
    * Developer Guide:
        * Added implementation details of the `sort` contact management feature.
        * Added UML sequence diagram for the `sort` contact management feature.
        * Updated existing UML diagrams from AddressBook Level-3.

* **Community**:
    * Over 30 Pull Requests reviewed. [Pull Request Reviews](https://github.com/AY2223S1-CS2103T-W15-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AEugene-Ong-W-X)
    * Posted 12 bug reports and suggestions for other teams. [Bug Reports](https://github.com/Eugene-Ong-W-X/ped/issues)
