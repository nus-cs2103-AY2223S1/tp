---
layout: page
title: lolfoollors's Project Portfolio Page
---

### Project: HealthCare Xpress

HealthCare Xpress is a desktop application that is to be used by medical administrator for managing patients that require home-visits and nurses. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 16 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add nurse.
    * What it does: It allows the app to add nurses into HealthCare Xpress.
    * Justification: This is critical in letting the application run as intended. The medical administrator would be able to add nurse and view which nurse might be available for different patients.
    * Highlights: This feature was done mainly by refactoring existing code

* **Major Enhancement**: Overhauled the entire help window.
    * What it does: It allows the user to look in the help window to understand the functions more.
    * Justification: It provides a quick and easily access to a help book in case of emergency when there is no internet available. Furthermore, it also provides a sample input and output to show to the user how to use it and the expected outcome.
    * Highlights:
      * There is a mini app display to show sample input and output for the command selected.
      * The text is animated to show the user how it is typed in.
      * There is a search bar for the user to search for the command easily.
      * There is a link to our GitHub page and User guide if the user still does not understand how to use it.
      * There is a help command at the bottom right to show more details on how to use it.
      * Operates in a different thread so you need not wait for the animation to finish before using the app.

* **Minor Enhancements**: Overhauled Date checking for date class.
    * What it does: It now not only checks for valid date format but also checks for leap year and valid dates.
    * Justification: It is critical to Healthcare Xpress working properly as date can only be written in 1 format. If written other than designated format before, the app will just totally ignore the user.
    * Highlights: 
      * Now checks for valid dates properly.

* **Minor Enhancements**: Updated GUI of the app.
    * What it does: Improved looks of program.
    * Justification: To differentiate 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ay2223s1-cs2103-f13-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=lolfoollors&tabRepo=AY2223S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Code Review
    * Testing of Code

* **Documentation**:
    * User Guide:
        * Add Nurse
        * Help Window
        * Fixed documentation bugs and edits. [#220](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/220/files) [#246](https://github.com/AY2223S1-CS2103-F13-4/tp/pull/246)

* **Community**:
    * Reported 15 bugs during PE-D exercise.


