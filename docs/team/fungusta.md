---
layout: page
title: Peter Fung's Project Portfolio Page
---

### Project: FABook

FABook is your **dependable assistant** who **reminds you of meetings** and **consolidates crucial information** like financial plans and client information right at your fingertips! You can now focus on giving your full attention to your clients without having to worry about things slipping your mind.

**FABook is optimized for a financial adviser to use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: PDF Access
    * What it does: Users are able to assign PDFs absolute path in computer to client profiles.
    * Justification: Users are able to access their clients current plans and other information in an instant.
    * Highlights: By inputting the command, the PDF will open on the User's browser
    * Credits: Reused code from [Stackoverflow](https://stackoverflow.com/questions/2546968/open-pdf-file-on-the-fly-from-a-java-application)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=fungusta&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=fungusta&tabRepo=AY2223S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Set up GitHub Issues and Milestones for tasks to be completed before certain deadlines.
    * Contributed in reviewing Pull Requests made by other members of the team.

* **Enhancements to existing features**:
    * Contact Finding by Address
      * Finds and lists out all people in FABook that address contains any of the keywords input by user.
      * Improved polymorphism of `Command` class by adding an abstract child `FindCommand` for find commands.

* **Documentation**:
  * User Guide:
    * Added information for setting client PDF files and opening client PDF files.
    * Fixed typos and mistakes that occurred.
    * Updated format for commands.

