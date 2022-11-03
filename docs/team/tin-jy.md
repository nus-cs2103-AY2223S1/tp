---
layout: page
title: Tin Jingyao's Project Portfolio Page
---

### Project: SoCompiler

SoCompiler is the sole app that university students will need to streamline their everyday routines. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Local Storage for Module Class**
    * What it does: Our project introduces a new class Module in parallel with Person. This allows Module data to be stored locally as well.
    * Justification: Module data must be stored locally so that it will be saved even after the user closes the app. We used the same data file so that no additional data files and paths need to be created, making the code is less prone to bugs. It also improves readability.
    * Highlights: Data is stored in a single file, making it easy to read and access.
    * Credits: The way data is read and written is similar to the local storage of Persons in AB3.

* **Two Zoom Links**
    * What it does: Each module can now store 2 zoom links, one for the lecture and another for the tutorial.
    * Justification: From our personal experience, modules are not likely to have more than 2 zoom links. By fixing the number of addable zoom links, the GUI can display the zoom links in a more presentable manner.
    * Highlights: Zoom links are clearly labelled as lecture and tutorial zoom links so users can easily differentiate them.
    
* **Code contributed**:
    * https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tin-jy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other

* **Project management**:
    * _(to be added soon)_

* **Enhancements to existing features**: <br>

  *Make Module and Person fields optional*
    * What it does: In AB3, most of the Person fields are compulsory. We have modified it such that all fields are now optional except for name of Person and module code of Module.
    * Justification: Not every field may be present for every Person and Module. For example, a user may want to add a contact without an email or a module without a zoom link.
    * Highlights: With most fields being optional, adding Persons and Modules is easy and flexible. Empty fields can be filled in later with the edit commands.

* **Documentation**:
    * User Guide:
        * Updated the Persons and Module sections to support optional fields.
        * Added links throughout the document and to the summary tables.
        * Added FAQ
        * Fixed formatting errors with icons and PDF conversion
    * Developer Guide:
        * Updated UML class diagrams for Persons and Modules to display the updated fields
        * Added explanation for local storage of Module data.
        * Added explanation for optionality of fields.

* **Community**:
    * PRs reviewed (with non-trivial review comments): _(to be added soon)_
    * Contributed to forum discussions: _(to be added soon)_
    * Reported bugs and suggestions for other teams in the class: Found 11 bugs in mock PE peer review, including a number of non-trivial functionality bugs

* **Tools**:
