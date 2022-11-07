---
layout: page
title: Melissa Anastasia Harijanto's Project Portfolio Page
---

## Project: TrackO

**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory
efficiently in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the
application via a CLI, and the application responds with its GUI, created with JavaFX.

### Summary of Contributions

<div markdown="block" class="alert alert-info">:information_source: **Note:**
Numbers followed by a `#` denotes the pull request number.
</div>

  - **Code contributed**: Click here for
    [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=melissaharijanto&breakdown=true).

  - **Major Enhancement**: Implement the UI of the application. — [#82](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/82),
    [#87](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/87),
    [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111),
    [#139](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/139),
    [#192](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/192)
    - What was done: Create a [design draft](https://www.figma.com/file/h6iechtBx1GaeJ6kKeqadb/CS2103T-UI-Design?node-id=0%3A1),
    then implement it into the application with JavaFX. 

  - **More Features/Enhancements Added**:
    - **Implement the base model for `java.tracko.model.item`** — [#58](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/58)
      - What was done: Added the base code for `Item` (`ItemName`, `InventoryList`, `Item`, `Description`, `Quantity`
      classes) for the members to build on, utilizing object-oriented programming.

    - **Implement the following commands: `edito`, `listi`, `findi`** — [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111)
        - `edito`: Edits orders.
        - `listi`: Lists all the existing inventory items.
        - `findi`: Finds existing inventory items that match certain keyword(s) inputted by the user.

    - **Implement order completion status** — [#104](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/104)
      - What it does: Orders now have a completion status: `paid` or `delivered`.

    - **Implement feature to calculate the price of an order's list of ordered items** — [#131](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/131)
        - What it does: Implement methods to calculate the price of the customer's ordered items.
  
    - **Write test cases** — [#100](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/100),
      [#122](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/122)
      - What was done: Implemented JUnit test cases for the following: `EditOrderDescriptorTest`, 
      `EditOrderCommandTest`, `EditOrderDescriptorBuilder`, `DeleteOrderCommandParserTest`, `DeleteOrderCommandTest`,
      `DeleteItemCommandTest`, `DeleteItemCommandParserTest`, `QuantityTest`, `DescriptionTest`, `ItemNameTest`
      - Justification: Testing will reduce the chances of bugs occurring in the program.

  - **Contributions to the UG**:
    - Create and added layout diagram and edited screenshots for better user experience when reading the guide:
    [Layout Diagram](https://www.canva.com/design/DAFQsG7iwNQ/AF7s8u5uq8cre-kX4JItmw/view?utm_content=DAFQsG7iwNQ&utm_campaign=designshare&utm_medium=link&utm_source=homepage_design_menu),
    [Edited Screenshots](https://www.canva.com/design/DAFQu54YdXA/ROaBm0rcWfeOKzEzCdlPNQ/view?utm_content=DAFQu54YdXA&utm_campaign=designshare&utm_medium=link&utm_source=homepage_design_menu) — [#212](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/212)
    - Made descriptions for `edito`, `listi`, `findi`, `help` — [#41](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/41),
      [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111/files),
      [#147](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/147)
    - Fixed UG bugs due to Markdown syntax issues — [#135](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/135), 
      [#216](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/216)
    - Added `Symbols & Syntax` section — [#225](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/225)

  - **Contributions to the DG**:
    - Added use cases for the Developer's Guide — [#48](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/48)
    - Made UML sequence diagrams and implementation details for `edito`, `findi`, `listi` — [#138](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/138)
    - Add manual testing guide for `help`, `clear`, `exit`, `Editing the data file` and `Starting with missing/corrupted data files` — [#225](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/225)

  - **Contributions to team-based tasks**:
    - Enabling assertions. [#184](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/104)
    - Released initial `.jar` file for [v1.3](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases/tag/v0.2).

  - **Review/mentoring contributions**:
    - Provide detailed descriptions for personal contributions — [#82](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/82),
      [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111)
    - Fix documentation bugs that are usually related to Markdown formatting issues — [#70](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/70),
      [#135](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/135),
      [#146](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/146)
    - Reviewed and offered suggestions for fellow teammates' pull requests.

  - **Contributions beyond the project team**:
    - Assist another project team in detecting bugs and offered tips and/or solutions to fix them:
    [Practical Exam Dry Run](https://github.com/melissaharijanto/ped/issues)
    - Assist other teams' group members in UI implementation by giving helpful tips and offer
    suggestions for overflow bugs and rendering issues:
    [Florentiana Yuwono](https://github.com/florentianayuwono) and
    [John Russell Himawan](https://github.com/johnrhimawan) (names are added with their consent).
