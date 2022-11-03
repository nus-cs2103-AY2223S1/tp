---
layout: page
title: Melissa Anastasia Harijanto's Project Portfolio Page
---

## Project: TrackO

**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory
efficiently in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the
application via a CLI, and the application responds with its GUI, created with JavaFX.

Given below are my contributions to the project.

### Summary of Contributions

  - **Code contributed**: Click here for
    [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=melissaharijanto&breakdown=true).

  - **Major Enhancement**: Implement the UI of the application.
    - What was done: Create a [design draft](https://www.figma.com/file/h6iechtBx1GaeJ6kKeqadb/CS2103T-UI-Design?node-id=0%3A1),
    then implement it into the application with JavaFX.
    - Justification: As the team is taking on a new approach to the project, we wanted to rebrand and give users the
    impression of a fresh and new application.
    - Highlights:
      - To implement the UI, I had to familiarize myself with JavaFX, something I barely have experience with. Doing
      this required me to analyze the code and determine the pros and cons of each element that is being used, e.g. the
      pros and cons of using a `HBox` and `FlowPane`, the former only laying its children horizontally in a single row,
      while the latter lays its children horizontally in multiple rows, but with risk of overflowing.
      - I had to also get in touch with the `.css` file and determine which ones I had to change in order to achieve
      the desired effect.
      - I considered the case of extreme inputs and fix the UI to display such inputs without truncating them.
      Aside from that, I also constantly test the UI in order to keep it bug-free, and immediately fix any that I could
      find.
    - Relevant pull request(s): [#82](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/82),
    [#87](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/87),
    [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111),
    [#139](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/139),
    [#192](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/192)

  - **More Features/Enhancements Added**:
    - **Implement the base model for `java.tracko.model.item`**
      - What was done: Added the base code for `Item` (`ItemName`, `InventoryList`, `Item`, `Description`, `Quantity`
      classes) for the members to build on, utilizing object-oriented programming.
      - Justification: This task had to be done quite early in order to minimize conflicts between the members' code, as
      commands relying on `Item`s had to depend on this class.
      - Highlights:
        - This was one of my earliest exposures to the code base (aside from the tutorial tasks), and implementing this
        made me understand it more.
      - Relevant pull request(s): [#58](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/58)

    - **Implement the following commands: `edito`, `listi`, `findi`**
      - What they do:
        - `edito`: Edits orders.
        - `listi`: Lists all the existing inventory items.
        - `findi`: Finds existing inventory items that match certain keyword(s) inputted by the user.
      - Relevant pull request(s): [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111)

    - **Implement order completion status**
      - What it does: Orders now have a completion status: `paid` or `delivered`.
      - Relevant pull request(s): [#104](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/104)

    - **Implement feature to calculate the price of an order's list of ordered items**
      - What it does: Implement methods to calculate the price of the customer's ordered items.
      - Relevant pull request(s): [#131](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/131)

    - **Write test cases**
      - What was done: Implemented JUnit test cases for the following:
        - `EditOrderDescriptorTest`
        - `EditOrderCommandTest`
        - `EditOrderDescriptorBuilder`
        - `DeleteOrderCommandParserTest`
        - `DeleteOrderCommandTest`
        - `DeleteItemCommandTest`
        - `DeleteItemCommandParserTest`
        - `QuantityTest`
        - `DescriptionTest`
        - `ItemNameTest`
      - Justification: Testing will reduce the chances of bugs occurring in the program.
      - Highlights:
        - Making test cases required me to understand certain methods, such as `assertTrue`, `assertFalse`,
        `assertThrows`, etc. However, the code base itself already has testing utilities that I could use, which
        simplifies the testing process.
        - Designing the tests relating to the `EditOrderCommand` is the most challenging; it required a lot of
        debugging as through the tests, I found out that the command was not executed as how it was meant to be.
        This case especially allowed me to realize that testing is an extremely crucial part of creating a
        bug-free program.
      - Related pull request(s): [#100](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/100),
      [#122](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/122)

  - **Contributions to the UG**:
    - Create layout diagram and edited screenshots for better user experience when reading the guide:
    [Layout Diagram](https://www.canva.com/design/DAFQsG7iwNQ/AF7s8u5uq8cre-kX4JItmw/view?utm_content=DAFQsG7iwNQ&utm_campaign=designshare&utm_medium=link&utm_source=homepage_design_menu), 
    [Edited Screenshots](https://www.canva.com/design/DAFQu54YdXA/ROaBm0rcWfeOKzEzCdlPNQ/view?utm_content=DAFQu54YdXA&utm_campaign=designshare&utm_medium=link&utm_source=homepage_design_menu)
    - Made descriptions for `edito`, `listi`, `findi`, `help`
      - Relevant pull request(s): [#41](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/41),
      [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111/files),
      [#147](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/147)

  - **Contributions to the DG**:
    - Added use cases for the Developer's Guide
      - Relevant pull request(s): [#48](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/48)
    - Made sequence diagrams and implementation details for `edito`, `findi`, `listi`
      - Relevant pull request(s): [#138](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/138)

  - **Contributions to team-based tasks**:
    - Enabling assertions. [#184](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/104)
    - Released initial `.jar` file for [v1.3](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases/tag/v0.2).

  - **Review/mentoring contributions**:
    - Provide detailed descriptions for personal contributions
      - Relevant pull request(s): [#82](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/82),
      [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111)
    - Fix documentation bugs that are usually related to Markdown formatting issues.
      - Relevant pull request(s): [#70](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/70),
      [#135](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/135),
      [#146](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/146)

  - **Contributions beyond the project team**:
    - Assist another project team in detecting bugs and offered tips and/or solutions to fix them:
    [Practical Exam Dry Run](https://github.com/melissaharijanto/ped/issues)
    - Assist other teams' group members in UI implementation by giving helpful tips and offer
    suggestions for overflow bugs and rendering issues:
    [Florentiana Yuwono](https://github.com/florentianayuwono) and
    [John Russell Himawan](https://github.com/johnrhimawan) (names are added with their consent).