---
layout: page title: Kok Chun Khai's Project Portfolio Page
---

### Project: SoCompiler

SoCompiler is the sole app that university students will need to streamline their everyday routines. The user interacts
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contribution**: [RepoSense Summary](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=avock&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=avock&tabRepo=AY2223S1-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&authorshipSortBy=fileName)
  <br><br/>
* **Features Contributed**:
  <br><br/>
    * **UI/UX Design**:
        * What it does: Enhances user experience and general improvement of usability.
        * Highlights:
            * Rearranged structure of javafx to better fit our product.
            * Overhauled outlook of software for a more modern, sleek look with an updated color scheme.
            * Redesigned UI layout to accommodate for the newly introduced module list.
            * Included features to improve user experience such as a copy button for long links and email addresses
            * Redesigned sizing of app window and list widths to be able to accommodate for general user inputs
              as well as the average lengths of module coded and titles.
            * Fixed bugs from AB3's UI such as overlapping tags and overflowing texts
              <br><br/>
    * **NUSModule Data Fetching and Verification**:
        * What it does: Automatically matches user's valid desired module code to the respective module title.
        * Justification: Increases recognizability of modules whilst allowing it so that users do not need to manually key
          in long module titles.
        * Credits: Integration of functionality was done with the help of `@Yingming23`.
        * To Note: Currently only works for modules in the _current semester_, will be implementing it such that users can
          select their preferred academic year to query from, as well as input custom modules.
          <br><br/>
    * **Add Module Command**:
        * What it does: Adds a module to the SoCompiler data archive, similar to the add-command introduced in AB3.
        * Justification: Allows user to add modules to a separate list from the Person for a better overview and clarity.
          <br><br/>
    * **Edit Module Command**:
        * What it does: Allows user to edit details of previously entered modules.
        * Justification: We felt that a separate command for editing modules would simplify the user experience,
          preventing them from editing details contents the wrong list.
          <br><br/>
* **Tests**:
    * Written test cases for `add module` and `edit module` commands, along with the relavent utility classes. [#69](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/69), [#243](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/243), [#245](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/245)
    * Test cases collectively increased code coverage by 11.29%, as seen from [CodeCov](https://app.codecov.io/gh/AY2223S1-CS2103T-W12-1/tp/commits?hideFailedCI=true)
      <br><br/>
* **Project management**:
    * Set up Codecov code coverage tracking for project repo.
    * Initial set up and transition of project documentations and website from AB3 to SoCompiler.
    * Managed releases `v1.2.1` and `v1.3` on GitHub.
    * Set up and managed meeting documents and other important information [here](https://docs.google.com/document/d/1OFhvvTXxh97xsj_ng3f3Gmx66HFJV9Pazy5_gCdhT4o/edit?usp=sharing).
      <br><br/>

* **Documentation**:
    * User Guide:
        * Initial update of UG to fit basic SoCompiler commands [#36](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/36)
        * Refactored contact and module field summary into a table format [#233](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/233)
    * Developer Guide:
        * Added implementation details for the add-module-command [#104](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/104/commits/c9883309cc242a53004f8e01cbabedb37db75e69).
          <br><br/>

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#109](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/109), [#119](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/119), [#235](https://github.com/AY2223S1-CS2103T-W12-1/tp/pull/235)<sup>*</sup>
    * Reported bugs and suggestions for other teams in the class: Reported bugs and provided suggestions for another team ([link](https://github.com/avock/ped/tree/main/files))<br>
      <font size = "2">_<sup>*</sup>note that the majority of bugs/issues discovered are discussed during our weekly meeting, and this list does not include bugs/issues/suggestions during such sessions._</font>
      <br><br/>
