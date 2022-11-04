---
layout: page
title: Hau Chong's Project Portfolio Page
---

### Project: Salesy

Salesy is a desktop Salesy helps food vendors in NUS manage details of their tasks, inventory and suppliers. It is optimised for CLI users so that tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Marking and un-marking tasks in the task list
  * Unit tests to ensure this command's logic is correct.

* **New Feature**: Editing an item's current stock in the inventory
  * Unit tests to ensure that this command's logic is correct.

* **New Feature**: Modify the UI of `MainWindow` to allow for more panels side by side

* **New Feature**: UI for Inventory Panel card
  * Developed the logic to determine whether a particular item in the inventory displays which particular color.
  * Made UI changes to the cards to support our color coded feature.
  * Wrote unit tests to test that logic of color determination is correct.

* **New Feature**: UI for Statistics Panel
  * Developed the logic to determine the number of un-done tasks in the task list.
  * Developed the logic to determine tasks that are past the deadline.
  * Developed the logic to determine the number of healthy, moderate and low stocks in the inventory.
  * Developed the UI to display the above metrics.
  * Unit tests to ensure that metrics displayed are correct.

* **New Feature**: Storage manager for tasks
  * This extension is a crucial portion of our project as it acts as the backbone of our
  task list panel.
  * Unit tests to test the functionality of task storage.

* **New Feature**: Storage manager for inventory items
  * This extension is a crucial portion of our project as it acts as the backbone of our inventory panel.
  * Unit tests to test the functionality of inventory storage.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=hauchongtang&breakdown=true)

* **Project management**:
  * Set up the project management panel for user stories
  * Set up the project management panel for bug triage
  * Maintain milestone tracker

* **Contribution to team-based Tasks**
  * Set up the Github team org/repo
  * Mantaining the issue tracker
  * Manage team [releases](https://github.com/AY2223S1-CS2103T-W08-4/tp/releases)
  * Done [code reviews](https://nus-cs2103-ay2223s1.github.io/dashboards/contents/tp-comments.html#22-tang-hong-hauchongtang-74-comments) for pull requests
  * Fix bugs for team mates

* **Documentation**:
  * User Guide:
    * Convert the user guide to numbering format for headers
    * Wrote the explanation for the statistics panel and provide pictorial guides
    * Ensure that quick links to other headers are working
    * Updated the appendix with more information for advanced users
    * Fix user guide bugs
  * Developer Guide:
    * Updated the diagram and description for `UI` component
    * Updated the diagram and description for `Model` component.
    * Updated the diagram and description for `Storage` component.
    * Updated the description for the implementation of the mark/ un-mark feature.
      * Provided object and activity diagrams to illustrate the feature more precisely.
      * Provided the design considerations and thought process when doing this implementation.

* **Community**:
  * Provided some help to other course mates in the [forum](https://nus-cs2103-ay2223s1.github.io/dashboards/contents/forum-activities.html#31-tang-hong-hauchongtang-11-posts)
