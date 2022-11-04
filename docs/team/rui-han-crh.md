---
layout: page
title: Chen Ruihan's Project Portfolio Page
---

### Project: SectresBook

SectresBook helps secretaries to maintain all the information of the members of their club by collating a list of identifiable information, past loan records and future tasks.

Given below are my contributions to the project.

* **New Features Implemented**
  1. **_Loan Property of a Person_**
     * **What it does**: Implements the ability to track monetary amounts that are represented as loaned amounts. If the amount is positive, the amount is to be paid _by_ the person. If the amount is negative, the amount is to be paid _to_ the person.
     * **Justification**: A treasurer requires the need to keep track of details of monetary transactions.
     * **Highlights**: When combined with the Loans History, containing the records of increments and UI Inspect section of loan history, the loan object can be represented to show monetary amounts. 
     * **Credits**: Loan (Rui Han), Loan Histories (Neethesh), Loan Inspection in UI (Rui Han)
  
  <br>

  2. **_User Interface Design_**
     * **What it does**: Remodels the user interface as shown in the landing page. It contains 4 main section, the text field, the horizontal person card list, the vertical notes list and the inspection panel which updates based on the person inspected. Various small icons, images, alignment details and transitions are also applied for visual enhancement.
     * **Justification**: The previous UI design did not look appealing, so a more visually appealing design was created
     * **Highlights**: When combined with the Loans History, containing the records of increments and UI Inspect section of loan history, the loan object can be represented to show monetary amounts. Other features I implemented work on top of the same UI that I designed.
     * **Credits**: Rui Han

  <br>

  3. **_Inspect command_**
     * **What it does**: Inspects a person in the person's list. Inspection is a UI-centric command that updates the UI values shown. It does not mutate any data in the model. This is also equivalent to just click on the person card, which does exactly the same thing. Inspection can be performed using NAME or INDEX.
     * **Justification**: The UI requires more flexibility when coupled with the CLI, there shouldn't be things that the GUI can do that the CLI cannot, so the `inspect` command was created.
     * **Highlights**: `inspect Alex` or `inspect 1` where Alex is at the first index will populate the Inspection Panel with data of the person, such as name, phone number, birthday, etc.
     * **Credits**: Rui Han

  <br>

  4. **_Hide Notes Panel Command_**
     * **What it does**: Hides the notes panel by applying a translational transition with a fade transition to the StackPane containing the notes panel. The UI uses anchor pane with a Vertical anchor point and a horizontal anchor point shared by all 3 components of the UI (People List, Inspect Panel and Notes Panel). The translation is applied through interpolating the vertical anchor point from 0.6 to 1 such that it moves from the original position to the right side of the screen. This pushes the notes panel to the right side and, at the same time, elongates and pulls the people panel and inspect panel to the right. <p> To maintain the aspect of the panel, a difference in initial left and right anchor point of the Notes Stackpane is calculated and maintained through the translation. The fade transition simply reduces the opacity of the entire pane to zero. This creates the effect of hiding the panel.
     * **Justification**: Because the person panel is horizontally scrolling, it is difficult to view more than 6 people in the list at the same time especially if working on a monitor with a smaller resolution. By hiding the notes panel and pulling the vertical anchor to the right, the people panel becomes wider, allowing for the use of more relevant screen real estate. The inspect panel is also elongated but is also an anchorpane itself, so it maintains equal division between the basic information and the loan history by a ratio.
     * **Highlights**: The transition effect looks nice. All other elements maintain its aspect through ratio instead of absolute values. `hideNotes` to hide the notes panel
     * **Credits**: Rui Han

  <br>

  5. **_Show Notes Panel Command_**
     * **What it does**: The opposite of hiding the notes panel. Fades the notes panel in while translating the vertical anchor to the left from 1.0 to 0.6 to slide the notes panel in.
     * **Justification**: If this did not exist, short of restarting the program, there is no way to bring the notes panel back. This provides the inverse functionality of hiding the notes panel.
     * **Highlights**: The transition effect looks nice. `showNotes` to show the notes panel.
     * **Credits**: Rui Han
     
  <br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=rui-han-crh&tabRepo=AY2223S1-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

  <br>

* **Project management**:
  * Called meetings to discuss plans. 
  * Provided feedback on Github PRs.
  * Organised issues and distributed them to group members.
  * Created milestones and description of milestones.
  * Applied strong version control through the use of Pull Requests and merging into an upstream repo.

  <br>

* **Enhancements implemented**:
  * **Feature**: Edit By Name
  * What it does: Adding onto the ability to edit by index, I implemented an ability to edit by any keyword of the person's name. This uses the `FindCommand` to first find by name and ensure that only 1 person exists from the result of finding the keyword. After this, the first index is fetched, which must correspond to a unique person found. Otherwise, if more than 1 person is found or no persons are found, the edit command will halt its operation, display the filtered list of persons returned and feedbacks to the user the people found.
  * Justificaton: It is easier to recall a person's name than to read the index from the list.
  * Highlights: `edit NAME ` + any field specifier and change of property will edit by name just as easily as edit by index. The concept introduced to create this ability is ricocheted in many other features authored by other members, which now also include the ability to perform operations by name.
  * Credits: Rui Han

  <br>

* **Contributions to the UG**:
  * Added images and icons (UI image, person card image, note card image, icons used throughout the program and the filtered icon)
  * Added the User Interface section of the UG with pointers to which part corresponds to what function and usage. [Link to UI section](#https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#user-interface)
  * Added description of command [`inspect`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#inspecting-a-person--inspect), [`hideNotes`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#hiding-notes-panel--hidenotes) and [`showNotes`](https://ay2223s1-cs2103t-w12-2.github.io/tp/UserGuide.html#showing-notes-panel--shownotes)
  * Improved description and detail of the UG by writing more complete sentences
  * Corrected some English terms
  * Added Glossary terms

  <br>

* **Contributions to the DG**:
  * Added 2 diagrams:
    * Edit sequence diagram involving either searching by index or by name
    * UI activity diagram that describes how a user would interact with the UI
  * Updated 1 diagram:
    * Added the loans property to the Person Class Diagram
  * Wrote the section involving `inspect`, `showNotes` and `hideNotes`

  <br>

* **Contributions to team-based tasks**:
  * Setting up the GitHub team org/repo
  * Changed the product icon to the current version (ledger with pen) using GIMP
  * Managed the usage of fonts (Bender and MinionPro)
  * Maintaining the issue tracker, marked duplicate bugs after the PE-D, separated true bugs from feature flaws, and trimmed the bug count to related to unique issues only.
  * Released version v1.3.1 for PE-D
  * Fixed 16 bugs after PE-D

  <br>

* **Review/mentoring contributions**:
  * Reviewed contributions by other group members, giving suggestions for improvement
    * Correcting regex expressions
    * Suggesting a better way to provide the same functionality by refactoring code
    * Suggesting the usage of streams over declarative loops to increase readability
  * Gave comments on suggestions for bug fixes in the issues tab
    * Wrote the probable cause of bug issues
    * Wrote the suggested fix for bug issues
    * Tested for bugs myself, using the ideas for the same bugs found during the PE-D test for another group, and published issues based on bugs found in the issues sections (not opening on Ubuntu 22.04, double parsing issue for values greater than or equals to 1.0E^7)

  <br>

* **Contributions beyond the project team**:
  * Frequently posted in the forums during the month of September to help other on iP related issues, such as setting the background, smoke testing and basic feature development.
  * During the PE-D, authored issues based on the structure of `Description`, `Steps to reproduce` and `Suggestion`, making the issues clear and reproducible and providing a starting point for any potential fixes.
