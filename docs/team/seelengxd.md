---
layout: page
title: See Leng's Project Portfolio Page
---

### Project: ArtBuddy

ArtBuddy (AB) a desktop application that helps commission-based artists manage their customers, commissions, and artworks easily. Written in Java, ArtBuddy is primarily optimised for use via a Command Line Interface (CLI), but also comes with a GUI created using JavaFX. It has about 14 kLoC.

Given below are my contributions to the project.

* **Key Contribution:** Added the `Commission` model [#68](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/68)
  * What it does:
    * Created classes for Commissions and its attributes.
    * Integrated them with `Storage`.
    * Added all initial `Commission` CRUD methods to `AddressBook` and `ModelManager` to act as the initial convenient interface to use for all commission-related commands.
    * Integrated the `Commission` list into the UI.
  * Justification:
    * This was a prerequisite to all commission-related commands that we wanted to implement.
  * Highlights: 
    * This enhancement was one of the first code-related PRs and required working with three components (`Ui`, `Model`, `Storage`).
      It ended up being quite huge and challenging, requiring a good understanding of many components to implement it without breaking existing abstractions.

* **New feature:** Added Commission Commands [~~C~~RUD]
  * Added `editcom` [#109](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/109) 
  * Added `delcom` [#68](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/68)
  * Added `opencom` [#91](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/91)
  * What it does: Adds read, update and delete functionality for commissions. 
  * Justification: Commissions are a core feature of our application and hence all its CRUD functionality are necessary for our product.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=seelengxd&breakdown=true)

* **Project management**:
  * Set up the GitHub team org/repo and tools such as CodeCov and Gradle
  * Set up the GitHub issue tracker and migrated all user stories to it

* **Enhancements to existing features**:
  * Added ability to select customer on click [#68](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/68)
  * Modified `Tag` to allow spaces [#78](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/78)
  * Changed product icon [#85](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/85)
  * Renamed `edit` command to `editcus` [#109](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/109) 
  * Modified sample data generator to include default commissions and iterations ([#139](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/139), [#142](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/142))
  * Added tests for `Commission`, `delcom`, `opencom`, `editcom` ([#96](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/96), [#99](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/99), [#101](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/101), [#105](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/105), [#109](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/109))

* **Documentation**:
    * User Guide:
      * Added edit commands to UG [#135](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/135), [#147](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/147) 
    * Developer Guide:
      * Added implementation details of the `Commission` model [#119](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/119)
      * Added implementation details of the `delcom` feature [#134](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/134)
      * Added list of user stories [#55](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/55)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#75](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/75), [#92](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/92), [#93](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/93), [#110](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/110), [#111](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/111)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/258#issuecomment-1250442082), [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/239#issuecomment-1249991866), [3](https://github.com/nus-cs2103-AY2223S1/forum/issues/90#issuecomment-1229209705), [4](https://github.com/nus-cs2103-AY2223S1/forum/issues/163#issuecomment-1242213388))
    * Reported [bugs and suggestions](https://github.com/seelengxd/ped/issues) for other teams in the class
