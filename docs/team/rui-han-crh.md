---
layout: page
title: Chen Ruihan's Project Portfolio Page
---

### Project: SectresBook

SectresBook helps secretaries to maintain all the information of the members of their club by collating a list of identifiable information, past records and future tasks. <br>Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=rui-han-crh&tabRepo=AY2223S1-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**New Features Implemented**

1. **_Loan Property of a Person_**
  * **What it does**: Implements the ability to track monetary amounts that are represented as loaned amounts. The loan amounts must be between -1 trillion to 1 trillion inclusive.
  * **Justification**: A treasurer requires the need to keep track of monetary transactions. A loan is a data type created to serve this purpose.
  
2. **_User Interface Design_**
   * **What it does**: Remodel and redesigned the user interface as shown in the landing page. Various small icons, images, alignment details and transitions are also applied for visual enhancement.
   * **Justification**: The previous UI design did not look appealing, so a more visually appealing design was created. This will help the product's publicity and attractiveness to clients.
   * **Highlights**: Event based triggers (space to start typing, esc to exit typing), transition effects and embellishments.

3. **_Inspect command_**
   * **What it does**: Inspects a person in the person's list. Inspection is a UI-centric command that updates UI values. It does not mutate any data in the model. This is also equivalent to just click on the person card.
   * **Justification**: The UI requires more flexibility when coupled with the CLI, there shouldn't be things that the GUI can do that the CLI cannot, so the `inspect` command was created.

4. **_Show and Hide Notes Panel Command_**
   * **What it does**: Shows/hides the notes panel by applying a translational transition with a fade transition to the StackPane containing the notes panel with anchor points on an AnchorPane. 
   * **Justification**: It is difficult to view more than 6 people in the list at the same time especially if working on a monitor with a smaller resolution. By hiding the notes panel, more screen real estate can be given to the person list and inspect panel.

5. **_Edit By Name_**
   * **What it does**: Adding onto the ability to edit by index, I implemented an ability to edit by any keyword of the person's name. This makes it more convenient to specify edit operations without checking for name
   * **Justificaton**: It is easier to recall a person's name than to read the index from the list.

**Project management**:

* Called meetings to discuss plans.
* Created milestones and description of milestones and provided feedback on Github PRs and organised issues with tags.

**Contributions to team-based tasks**:

* Setting up the GitHub team org/repo
* Changed the product icon to the current version (ledger with pen) using GIMP
* Released version v1.3.1 for PE-D and fixed 16 bugs after PE-D

**Review/mentoring contributions**:

* Reviewed contributions by other group members, sometimes suggesting other alternatives for better maintainability.
* Gave comments on suggestions for bug fixes in the issues tab and published issues based on more bugs found in the issues sections.

**Contributions beyond the project team**:

* Frequently posted in the forums to help other on iP related issues, such as setting the background, smoke testing and basic feature development.
* During the PE-D, authored issues based on the structure of `Description`, `Steps to reproduce` and `Suggestion`, making the issues clear and reproducible

**Contributions to the UG/DG**:

- UG
  1. Added images and icons
  2. Added the User Interface section of the UG with pointers to which part corresponds to what function and usage. [Link to UI section](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#user-interface)
  3. Added description of properties for both people and notes, found [here](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#properties) and description of commands [`inspect`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#inspecting-a-person--inspect), [`hideNotes`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#hiding-notes-panel--hidenotes) and [`showNotes`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#showing-notes-panel--shownotes).
  4. Added [Glossary](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#glossary) terms.
- DG
  1. Added Edit _Sequence Diagram_ involving either searching by index or by name and _Sequence Diagram_ for the `inspect` command
  2. Updated the UI _Class Diagram_ to reflect the current state of the UI organisation and the Person _Class Diagram_ by adding the loans property to the Person _Class Diagram_
  3. Wrote the sections involving [UI design](https://ay2223s1-cs2103t-w12-2.github.io/tp/DeveloperGuide.html#ui-features) and the implementation details of [`inspect`](https://ay2223s1-cs2103t-w12-2.github.io/tp/DeveloperGuide.html#inspect-feature), and `showNotes` and `hideNotes`, found [here](https://ay2223s1-cs2103t-w12-2.github.io/tp/DeveloperGuide.html#showing-and-hiding-the-notes-panel-feature).
  4. UI activity diagram that describes how a user would interact with the UI
