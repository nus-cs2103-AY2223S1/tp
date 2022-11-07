---
layout: ppp
title: Tan Yi Xian's Project Portfolio Page
---

<!-- markdownlint-disable-next-line blanks-around-headers -->
### Project: FoodRem
{: .no_toc}

<!-- markdownlint-disable-next-line proper-names -->
{{ site.data.foodrem.about.summary }}

Given below are my contributions to the project.

* **Major Refactoring**: Refactor AB3 into FoodRem

  * What it does: Removes all occurrences of AB3. Converts the `Person` class into an `Item` class.
  * Justification: This was essential in creating the minimum viable product and laid the groundwork for further extensions.
  * Highlights:
    * The refactoring was a challenging process as the changes must be documented along with the test cases. It was critical to ensure the test cases were still relevant to the `Item` class rather than `Person` class.
    * The refactoring meant that all commands of AB3 were extensively modified to suit FoodRem:
      * Command to list all items
      * Command to find an item
      * Command to create a new item
      * Command to edit an existing item
      * Command to delete an item
    * Each Item field has a respective class
      * A class has to be created for each of the following fields: name, quantity, unit, bought date, expiry date, price, and remarks.
      * A validation class was created for each individual field to ensure easy extension.
    * Commands such as the help, exit and reset, commands required minimal changes.
  * Pull requests : Refactoring Person to Item [[PR#157]]

* **New Feature**: Added the ability to add a remark

  * What it does: Allows the user to add a remark to an item.
  * Justification: This feature improves the user's experience as they are now able to include notes specific to an item.
  * Pull requests : Add remarks fields and remarks command [[PR#254]]

* **New Feature**: Add the user interface for tags
  * What it does: Allows the user to view results of tag commands in a beautiful format rather than plain text.
  * Justification: This feature standardises the UI of Items and Tags.
  * The implementation was extended from the current UI of Items which was created by Richard Dominick.
  * Pull requests : Add UI for tags [[PR#340]]

* **Enhancements to the `help` command**:

  * Enhanced `help` command to provide help for individual commands rather than a generic message.
  * Enumeration of CommandWords was done.
  * Pull requests : Modify help command to provide help for each individual command [[PR#201]]

* **Enhancements to the `inc` and `dec` command**:

  * Enhanced `inc` command and `dec` command to have optional qty prefix.
  * This was done to make it easier to increment and decrement the quantity of an item.
  * Pull requests : Modify increment and decrement command to have optional qty prefix [[PR#183]]

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=yixiann&tabRepo=AY2223S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:

  * Created the following milestones `V1.1, V1.2, V1.3, V1.4`
  * These milestones were essential in ensuring the group on track in our tasking.

* **Documentation**:

  * User Guide:
    * Add skeleton for the User Guide [[PR#35]]
    * Add value preposition of FoodRem in the User Guide [[PR#147]]
    * Update Quick Start of User Guide, and reorganise content page [[PR#141]]
    * Add documentation for the `help` feature [[PR#90]]

  * Developer Guide:
    * Refactored AB3's Developer Guide to suit FoodRem. [[PR#233]]
    * Added "About FoodRem" section. [[PR#433]]
    * Added "How to use the Developer Guide" section. [[PR#433]]
    * Added "General Implementation Details" for Item-related features. [[PR#531]]
    * Added section on "Incrementing and Decrementing the quantity of an item". [[PR#531]]
    * Added section on "General Features". [[PR#531]]

* **Community**:

  * Reported bugs and suggestions for other teams in the class:
    * Stats command do not calculate amount wasted by performing qty * price [[Issue#449]]
    * Stats command expiry date do not count items that expire today [[Issue#448]]
    * Stats command fail on FoodRem having less than 3 items [[Issue#447]]
    * Rename tag does not modify tags in items [[Issue#293]]
    * Rename tag to same name is possible [[Issue#273]]

Other PRs:

* Update AboutUs page [[PR#14]]
* Add skeleton for User Guide [[PR#35]]
* Update aboutUs page to include information about Yi Xian [[PR#89]]
* Update userguide to include help command [[PR#90]]
* Add skeletal PPP [[PR#91]]
* Add skeletal for UC7 and UC8 in DeveloperGuide.md [[PR#92]]
* Add image for aboutUs page [[PR#96]]
* Remove boilerplate and update roles in About Us page [[PR#100]]
* Point CI banners to own project and remove traces of AB3 in README.md [[PR#102]]
* Add use case 7, use case 8 and user stories for inventory items [[PR#129]]
* Update Quick Start of User Guide, reorganise content page [[PR#141]]
* Change TYPE to UNIT in UserGuide.md [[PR#145]]
* Add value preposition [[PR#147]]
* Refactoring Person to Item 2 [[PR#157]]
* Convert Add command to New command [[PR#173]]
* Rename command word from \"delete\" to \"del\" [[PR#174]]
* Modify Find item messages and test to match FoodRem instead of AddressBook [[PR#175]]
* Rename \"Clear\" to \"Reset\" [[PR#176]]
* Modify increment and decrement command to have optional qty prefix [[PR#183]]
* Modify help command to provide help for each individual command [[PR#201]]
* Allow easier extension to help command by removing switch case [[PR#211]]
* Update help, edit and delete command [[PR#212]]
* Add price field in item [[PR#229]]
* Move enum package to commons and remove traces of AB-3 from DG [[PR#233]]
* Move enum package to commons and remove traces of AB-3 from DG [[PR#233]]
* Add UML diagram for HelpCommand [[PR#234]]
* Add remarks fields and remarks command [[PR#254]]
* Standardise help command [[PR#255]]
* Add test for Decrement, Increment, View, Sort, Help, Exit and Reset [[PR#257]]
* Improve UG and add remarks and price fields [[PR#272]]
* Fix \"Rename tag to same name is possible\" bug [[PR#274]]
* Fix index out of bound bug and standardise naming of index across item command [[PR#282]]
* Restructure expiry date and bought date to use singleton design pattern [[PR#290]]
* Fix rename tag not changing tags within items [[PR#296]]
* Fix storage modification causing bugs [[PR#298]]
* Create welcome message for user that shows error if datafile has issue [[PR#299]]
* Show empty FoodRem when data file is corrupted [[PR#303]]
* Add tag related images [[PR#314]]
* Centralise logic for validate string regex [[PR#316]]
* Add remarks to help [[PR#317]]
* Ensure all commands uses a parser [[PR#329]]
* Add UI for tags [[PR#340]]
* Prevent special chars in find command [[PR#355]]
* Modify view command to show error for negative index [[PR#357]]
* Modify price validator to not accept 3.e1 [[PR#358]]
* Update UG [[PR#359]]
* Add storage restriction to items and tags [[PR#362]]
* Apply negative index validation to all commands [[PR#365]]
* Update User Guide to match changes to codebase [[PR#368]]
* Fix date validation [[PR#370]]
* Update find to match new find [[PR#371]]
* Update help command to use ITEM_NAME and TAG_NAME instead of NAME [[PR#374]]
* Fix sorting by name [[PR#375]]
* Set height of text area to be 450 [[PR#376]]
* Add minor changes in the Userguide [[PR#429]]
* Fix broken glossary term for expiry date and broken link for flags [[PR#432]]
* Fix getColor for tags [[PR#434]]
* Use stats parser to restrict command arguments [[PR#435]]
* Ensure we cannot create an item with bought date after expiry date [[PR#450]]
* Fix error message that says \"the FoodRem\" [[PR#466]]
* Update PPP [[PR#504]]
* Improve code quality [[PR#517]]
* Fix minor errors [[PR#519]]
* Add reposense @@author tag [[PR#522]]
* Fix grammar in DG [[PR#524]]
* Add DG help command sequence diagram and activity diagram [[PR#527]]
* Fix bought date to be in future in test cases [[PR#465]]
* Fix error message that says \"the FoodRem\" [[PR#466]]
* Update PPP [[PR#504]]
* Improve code quality [[PR#517]]
* Fix minor errors [[PR#519]]
* Add reposense @@author tag [[PR#522]]
* Fix grammar in DG [[PR#524]]
* Add DG help command sequence diagram and activity diagram [[PR#527]]
