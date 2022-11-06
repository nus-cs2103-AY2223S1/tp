---
layout: page
title: Chang Si Kai's Project Portfolio Page
---

### Project: MyInsuRec

MyInsuRec is a clients and meetings management app for insurance agents.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sikai00&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements implemented**
    * Model: Create `Meeting` class and its subsidiary classes: [#100](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/100), [#101](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/101)
        * What it does: The ability to represent `Meeting` in _MyInsuRec_.`Meeting` forms one of the key supportive pillars alongside `Client` and `Product`, as it is _MyInsuRec_'s goal to help manage meetings with clients.
        * Other notable details:
            * Added test for `ModelManager#hasMeeting`.
    * Model: Implement `NoConflictMeetingList` class: [#102](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/102)
        * What it does: Key component of the model, as it stores all `Meeting` and rejects `Meeting` with timing conflicts.
        * Justification: Help prevent users from accidentally scheduling 2 meetings at the same time.
    * New feature: Listing all meetings (`listMeeting`): [#126](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/126)
        * What it does: Shows the user a summarized list of all their meetings.
        * Other notable details: 
            * Created new panel for use in UI and hooked up with all major components (`Ui`, `Logic`, `Model`, `Storage` etc.), laying the groundwork for all other `Meeting` related commands.
            * Ability to switch between different UI panels.
            * All relevant tests were added (`ListMeetingCommandTest`). Other tests which were involved were also updated.
    * UI: Introduce first iteration of UI for `viewClient`: [#147](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/147)
    * UI: Introduce first iteration of UI for `viewMeeting`: [#143](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/143)
    * New feature: User definable product list and its related commands (`addProduct`, `listProduct`, `delProduct`): [#187](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/187)
        * What it does: The ability to represent `Product` in _MyInsuRec_.`Product` forms one of the key supportive pillars alongside `Client` and `Meeting`, as it is _MyInsuRec_'s goal to help manage products.
        * Justification: Help better organization of products (and the clients who purchased the products). User can associate their clients with different products (possibly multiple products), which helps their to quickly identify which clients purchased what products. Enforces what products user can assign to their client, helping prevent accidental assigning of mislabeled products.
        * Other notable details:
            * Complete set of commands (`addProduct`, `listProduct`, `delProduct`).
            * Complete implementation with all major components (`Ui`, `Logic`, `Model`, `Storage` etc.)
            * All relevant tests were added (`AddProductCommandTest`, `ListProductCommandTest`, `DeleteProductCommandTest`, `UniqueProductListTest`, `ProductTest`), covering 100% of the code added.

* **Project management**:
    * Release management
        * Managed all version releases.
            * This includes fixing important bugs right before release to ensure a good release: [#150](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/150), [#153](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/153), [#154](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/154), [#204](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/204), [#205](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/205), [#209](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/209)
    * Set up (v1.3, v1.4) and wrapped up some milestones (v1.2, v1.3).
    * Renamed JAR file: [#201](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/201)
    * Led most team meetings for discussions, set agendas and deadlines to ensure everybody completes their tasks on time for release.
    * Introduced skeleton for UG for others to work off on: [#194](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/194)
    * Added preface for some sections of UG: [#218](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/218)
    * Tidied up and standardize formatting across UG (numbering, typos, etc.). Also created [rough formatting guide](https://docs.google.com/document/d/1c9IWYMYPcLlwPadN1ml3aglUxt-0pMV7zbsSpisGfR8/): [#278](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/278), [#285](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/285)

* **General documentation**:
    * About Us:
        * Updated everyone's roles and responsibilities and made necessary changes to its formatting: [#22](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/22)
    * README:
        * Added project description: [#25](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/25)
        * Designed and added first iteration of UI: [#85](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/85)

* **User Guide**:
    * Sections added:
        * Added first iteration of documentation for the features `addClient`, `listMeeting`, `exit`: [#29](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/29)
        * Added first iteration of documentation for the command summary: [#29](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/29)
        * Added **Introduction** and **Product Overview**: [#194](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/194)
        * Added **Symbols** table: [#194](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/194), [#278](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/278)
        * Added documentation for `listMeeting`, `listProduct` and `delProduct`: [#194](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/194), [#218](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/218)
        * Added **Installation Guide and Tutorial**: [#206](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/206)
        * Added **Troubleshooting subsection: Checking your system's Java version**: [#206](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/206)
        * Added **Contact us**: [#233](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/233)
    * Other notable details:
        * Introduce **Use case** subsections under every feature to boost user-centricness: [#218](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/218)

* **Developer Guide**:
    * Use cases for the features implemented: [#56](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/56)
    * Rationale for different view panels: [#180](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/180)
    * `listMeeting`: [#180](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/180)
    * Reorganize and refactor the Developer Guide: [#310](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/310)
    * Updated the class diagrams for all major components: [#314](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/314)
    * Added Effort section: [#314](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/314)
    * Add client with product feature: [#314](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/314)

* **Review/mentoring contributions**:
    * PRs reviewed:
      * Notable reviews:
        * Make aware the use of `checkstyle` feature to prevent CI/CD fails: [#16](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/16)
        * General code quality and software engineering principles: [#191](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/191)
        * Suggestions of good practices on the use of Java's Optional: [#185](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/185)

* **Community (outside of team)**:
    * PRs reviewed (with non-trivial review comments) outside of team: [#30](https://github.com/nus-cs2103-AY2223S1/ip/pull/30/files/8747b6733960793f58073631497b68ef093b584a), [#402](https://github.com/nus-cs2103-AY2223S1/ip/pull/402/files/3e90719169c9d34d360f5f543bf639817b2e78ac),
    * Contributed to forum discussions (examples: [#25](https://github.com/nus-cs2103-AY2223S1/forum/issues/25), [#136](https://github.com/nus-cs2103-AY2223S1/forum/issues/136), [#140](https://github.com/nus-cs2103-AY2223S1/forum/issues/140))
