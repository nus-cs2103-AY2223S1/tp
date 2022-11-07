---
layout: page
title: Shaune Ang's Project Portfolio Page
---

## Project: FABook

FABook is your **dependable assistant** who **reminds you of meetings** and **consolidates crucial information** like financial plans and client information right at your fingertips! You can now focus on giving your full attention to your clients without having to worry about things slipping your mind.

**FABook is optimized for a financial adviser to use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### Product Enhancements
* **Undo Redo Feature:**[#51](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/51) [#53](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/53) [#55](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/55) [#200](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/200) [#205](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/205) [#209](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/209)
  * What it does: Allows user to undo and redo certain commands that were executed.
  * Justification: Users do not have to type 2 or more commands just to rectify an erroneous command.
  * Highlights: Feature is implemented through polymorphism where each undoable command implements its own undo method and commands are managed using an undoStack and redoStack.
  * Credits:
    * The feature Undo and Redo was reused with minimal changes from a tutorial called [Implementing Undo and Redo With The Command Design Pattern by ArjanCode](https://youtu.be/FM71_a3txTo).

* **Person Profile UI:** [#93](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/93) [#98](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/98) [#105](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/105) [#106](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/106) [#125](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/125) [#128](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/128) [#202](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/202) [#229](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/229)
  * What it does: Displays the comprehensive information of clients, including an open pdf button, when the user clicks on a client's person card.
  * Justification: The person card was too little space to display the amount of information we wanted.
  * Highlights: Opening the person profile on the main display panel upon clicking a person card
  * Improvements: Needs an intuitive way to update the person profile when client information is updated.

* **Abstracted EMPTY_FIELD_VALUES used in Person Model:** [#109](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/109)
  * What it does: Abstracts out all empty field values used in person model.
  * Justification: Abstraction makes it easier for developers to set the default empty field value that is used to instantiate non-compulsory fields.
  * Highlights: All empty field values draw their value from a single source, `Person` model.

* **Colour Code Client Status Tag:** [#132](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/132)
  * What it does: Displays `SECURED` and `POTENTIAL` client status tags in blue and yellow respectively. 
  * Justification: The user can more easily identify the status of the client with just one look.
  * Highlights: The styling is changed by setting the ID of the Tag to `securedTags` and `potentialTags`.

### Code contributed
This display my code contributions for the project: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=shaune&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=shauneang&tabRepo=AY2223S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project management
* [PRs Reviewed by me](https://github.com/AY2223S1-CS2103T-T10-2/tp/pulls?q=is%3Apr+reviewed-by%3Ashauneang)
* [Issues created by me](https://github.com/AY2223S1-CS2103T-T10-2/tp/issues?q=is%3Aissue+author%3Ashauneang)

### Documentation
* **User Guide:** [#187](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/187) [#189](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/189) [#207](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/207)
  * Added documentation for the feature `find`.
  * Added documentation for the features `undo` and `redo`.
  * Added common field formats table.

* **Developer Guide:** [#74](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/74) [#226](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/226) [#236](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/236)
  * Added documentation for the Undo/Redo Command.
  * Added documentation for the Sync Meetings Command.
  * Contributed to the documentation for the Upcoming Meeting feature.
  * Contributed to the documentation for the Set File Path feature.
  * Created activity diagrams and sequence diagrams for undo/redo, sync and setFilePath commands.
