---
layout: page
title: Jessica Mary Listijo's Project Portfolio Page
---

### Project: InternConnect

InternConnect is a one-stop, convenient, and efficient platform to manage and empower how internship campus recruiters who prefer CLI over GUI work with their applicants’ data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New feature**: Added the ability to view the details of an applicant (PR [#116](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/116)).
    * What it does:

      Allows the user to view the details of an applicant in a separate panel.

    * Justification:
      
      This feature significantly improves the user experience as after the implementaiton, the currently displayed list is less cluttered that it only contains name, job, email, and tags of an applicant. This is considering the amount of details that one applicant has (name, email, address, gender, phone, job, CAP, major, university, graduation date, tags).

      This feature also improves the user experience not only upon viewing an applicant, but also upon adding and editing an applicant where the modified applicant will also be shown in the view panel.

    * Highlights:
      
      Difficulties were faced when I was trying to integrate `PersonViewPanel` to `MainWindow`, both in the respective `.java` and `.fxml` files. This is especially in the `.fxml` file, when I implemented the splitting of the panels in `MainWindow.fxml`. In the first iteration (`v1.2`), this worked out, but with some minor resizing where the application window cannot be maximized, and upon dragging the window to another screen (e.g. from laptop screen to a monitor screen) each panel was not resized, resulting in some white area. I managed to fix this resizing issue in the second iteration (`v1.3`).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=maryjess&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Contributed to issue creation for team members
    * Reviewed and approved team members' PRs

* **Enhancements to existing features**:
    * Added `Cap` field which represents the cummulative average point (CAP) of an applicant (PR [#102](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/102))
    * Refined `Email`, `Phone`, and `Name` fields (PR [#112](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/112), [#118](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/138))

* **Documentation**:
    * User Guide:
        * Added documentatin for `view` command (PR [#84](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/84/))
        * Refined the description for `Email` and `Cap` field (PR [#258](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/258))
    * Developer Guide:
        * Added user stories and use cases (PR [#83](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/83))
        * Added documentation for `view` command (PR [#149](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/149), [#275](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/275/))
    * Updated README.md (PR [#82](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/82))

* **Community**:
    * PRs reviewed ([#99](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/99), [#101](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/101), [#106](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/106), [#135](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/135), [#175](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/175))
    * Contributed in forum discussion ([#33](https://github.com/nus-cs2103-AY2223S1/forum/issues/33), [#140](https://github.com/nus-cs2103-AY2223S1/forum/issues/140))

* **Tools**:
    * Used PlantUML to create UML diagrams
    * Used GitHub and Git for version control
    * Used IntelliJ and Visual Studio Code to edit files
    * Used Microsoft Teams for team communication and notes taking
