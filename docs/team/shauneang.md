---
layout: page
title: Shaune Ang's Project Portfolio Page
---

## Project: FABook

FABook is your **dependable assistant** who **reminds you of meetings** and **consolidates crucial information** like financial plans and client information right at your fingertips! You can now focus on giving your full attention to your clients without having to worry about things slipping your mind.

**FABook is optimized for a financial adviser to use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### Product Enhancements
* **Undo Redo Feature**
  * What it does: Allows user to undo and redo certain commands that were executed.
  * Justification: Users do not have to type 2 or more commands just to rectify an erroneous command.
  * Highlights: Feature is implemented through polymorphism where each undoable command implements its own undo method and commands are managed using an undoStack and redoStack.
  * Credits:
    * The feature Undo and Redo was reused with minimal changes from a tutorial called [Implementing Undo and Redo With The Command Design Pattern by ArjanCode](https://youtu.be/FM71_a3txTo).

* **Person Profile UI**
  * What it does: Displays the comprehensive information of clients, including an open pdf button, when the user clicks on a client's person card.
  * Justification: The person card was too little space to display the amount of information we wanted.
  * Highlights: Opening the person profile on the main display panel upon clicking a person card
  * Improvements: Needs an intuitive way to update the person profile when client information is updated.

### Code contributed
This display my code contributions for the project: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=shaune&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=shauneang&tabRepo=AY2223S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project management
Reviewed and merged teammates Pull Requests.

### Documentation
* **User Guide**
  * Added documentation for the feature `find`.
  * Added documentation for the features `undo` and `redo`.
  * Added common field formats table.

* **Developer Guide**
  * Added documentation for the Undo/Redo Command.
  * Added documentation for the Sync Meetings Command.
  * Contributed to the documentation for the Upcoming Meeting feature.
  * Contributed to the documentation for the Set File Path feature.
  * Created activity diagrams and sequence diagrams for undo/redo, sync and setFilePath commands.
