---
layout: page
title: Chloe's Project Portfolio Page
---

### Project: ArtBuddy

ArtBuddy (AB) a desktop application that helps commission-based artists manage their customers,
commissions, and artworks easily. Written in Java, ArtBuddy is primarily optimised for use via a
Command Line Interface (CLI), but also comes with a GUI created using JavaFX.

Given below are my contributions to the project.

* **Key Contribution**: Added the `Iteration` model [#84](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/84)
  * What it does: Iterations serve as a form of 'version control' for users, allowing them to track
  the progress of commission through revised versions of their artworks.
  * Highlights:
    * Created the classes for `Iteration` and its associated attributes (except images),
    and integrated it into Commissions (via a `UniqueIterationList`), and the UI, Model, and Storage components.
    * As the new `Iteration` model had to be integrated with the different components of the system,
    this enhancement was quite huge, and required a good understanding of how the various components of
    the system worked together.


* **Key Contribution**: GUI support for add commands (`addcus`, `addcom`, `additer`)
  [#114](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/114)
  * What it does: Allows users to also create a new Customer, Commission, and Iteration via the GUI. Also supports
    uploading of images to ArtBuddy via drag-and-drop, and file upload.
  * Justification: We recognise that executing add commands via the CLI might be a hassle, especially in the
    case of creating objects with extreme input parameters (like a Commission with a long description, or when dealing
    with long file paths when adding an image to an Iteration). Allowing users to also input `add` commands via
    the GUI accommodates to these extreme cases, enhancing user-friendliness.
  * Highlights:
    * Creating 3 new functional windows was a rather big challenge, especially in JavaFX. Issues with spacing
    and alignment kept cropping up between different OS systems and screen sizes, and it was difficult to
    find help on these bugs since JavaFX isn't exactly a popular frontend framework.
    * Keeping in mind possible future extensions to edit commands via GUI, each window had to be further abstracted
    out into its own class.
    * It was also fun trying to add the image upload and drag and drop features. The add tag support feature by GUI
    was especially fun to implement and design!


* **New Feature**: Added `Iteration` related commands
  * Highlights: New commands to support basic CRUD functionality for iterations
    * Added `additer` command to add an iteration to the currently selected commission
    [#84](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/84)
    * Added `deliter` command to delete a specified iteration from the currently selected commission
    [#84](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/84)
    * Added `edititer` command to edit a specified iteration from the currently selected commission
    [#123](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/123)


* **New Feature**: Statistics View in `CustomerDetailsPane` [#138](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/138)
  * What it does: Allows users to see statistics tied to a specific Customer. Includes a pie chart of the breakdown
  of commissions commissioned by that customer according to completion status.


* **Enhancements to existing features**:
  * Enhanced UI 'theme': Created a [mockup](https://www.figma.com/file/neC7oxzQ9L8R8NkfYdd2De/Untitled) of the UI
  using Figma, and updated the styling in AB according to the mockup designed
  ([#72](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/72) and
  [#93](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/93)).
  
  * Added UI Components `CommissionDetailsPane` [#84](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/84)
  and `IterationListItem` [#89](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/89): To display a view of a commission's details, and a list view of its iterations.
  
  * Added test cases for `Iteration` classes and its related commands (`additer`, `deliter`, and `edititer`)
  ([#100](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/100) and
  [#111](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/111)).


* **Documentation**:
    * User Guide:
      * Wrote the introduction sections of the User Guide [#146](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/146).
      * Added documentation for commands `additer`, `deliter`, and `edititer`
        ([#57](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/57) and
        [#136](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/136)).
    * Developer Guide:
      * Added implementation details of `Iteration` and `additer`
        ([#118](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/118) and
        [#133](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/133)).


* **Community**:
  * PRs reviewed (with non-trivial comment reviews): [#82](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/82),
    [#109](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/109),
    [#68](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/68),
    [#92](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/92).
  * Reported [bugs and suggestions](https://github.com/chloeelim/ped/issues) for other teams in the class.
  

* **Project management**:
  * Released [v1.2](https://github.com/AY2223S1-CS2103T-W11-3/tp/releases/tag/v1.2)
    and [v1.3.1](https://github.com/AY2223S1-CS2103T-W11-3/tp/releases/tag/v1.3.1) on GitHub.
  * Created a GitHub Gist page for [V1.2 Product Demo](https://gist.github.com/chloeelim/fba9321580577a27cb01d76f7edc07cd).


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=chloeelim)
