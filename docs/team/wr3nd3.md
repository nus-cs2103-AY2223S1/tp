---
layout: page
title: Wong Rende's Project Portfolio Page
---

### Project: PayMeLah

PayMeLah is a desktop app for **keeping track of** and **managing the debts** your friends owe you. 
It can also help **do simple calculations** for you, such as including GST or splitting debts amongst your friends. 
What’s more, it is optimised for you to do everything from just your keyboard! <br>
This app interacts with users using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.


Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://ay2223s1-cs2103t-w13-3.github.io/tp/team/wr3nd3.html)

* **New Feature**:
  * Implementation of Delete Debts feature [#83](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/83), [#122](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/122), [#123](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/123)
    * What it does: It enables users to not only specify a given person stored in PayMeLah, but to also specify
      which of the debts to delete.
    * Justification: Alongside our Add Debt feature, this Delete Debts feature forms the core of our enhancements to the
    original AB3 program where users can both add and remove debts from Persons stored in PayMeLah to keep track of debts.
    * Highlights: This feature allows users to specify more than one debt to delete from the person. Combined with the 
    Clear Debts feature, this enables users full control over how to remove debts with convenience. The implementation was
    challenging as it required modification and thorough understanding of the Parser component of the AB3.
  
  * Implementation of Clear Debts feature [#69](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/69), [#121](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/121)
    * What it does: It enables a user to specify a given Person stored in PayMeLah and delete all the Debts 
    stored for that person. 
    * Justification: It increases the convenience for users greatly for when they want to delete all the debts of a Person 
    (E.G. when the person paid of many debts together in one sitting) Instead of using the Delete Debts feature, this would be
    far easier and faster for them than specifying the index of each and every debt to delete.

  * Implementation of Split Debt feature [#120](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/120)
    * What it does: It enables a user to split a debt over several persons whereby the cost of the debt is divided
    by the number of persons and each person will have a debt with the same description and divided cost.
    * Justification: When it comes to multiple persons sharing items in a transaction, it can be inconvenient to 
    personally split the cost of the items evenly. In this case, the regular Add Debt feature would not be enough
    so this Split Debt feature adds significant value by achieving this.
    * Highlights: This feature's implementation has the prerequisite of allowing users to specify as many persons
    as they want to split the debt over. In addition, this feature allows users to specify themselves with the 
    person index 0. This poses increased difficulty since AB3's Parser component regards the 0 index as an exception
    event. Thus, this implementation required modification and thorough understanding of the Parser component of AB3.

* **Contributions to team-based tasks**:
    * Refactor `Email` class to `Telegram` field [#154](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/154).
      * Justification: For our target audience, students, their means of peer-to-peer communication revolves around 
      telegram more so than email.
      * Highlights: This implementation required the use of generating a new Regex check for the requirements of a valid
      telegram handle.
    * Fix bug in self-restoring data JSON file when debt fields are tampered with 
  [#181](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/181).

* **Documentation**:
    * User Guide:
        * Added documentation for the features `cleardebts`, `deletedebt` and `splitdebt` 
      [#276](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/276).
        * Added installation section for the PayMeLah application with diagrams and tutorial links
      [#269](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/269).
      
    * Developer Guide:
        * Updated Model and Storage component diagrams to accommodate `Telegram` field 
      [#194](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/194).
        * Added implementation details for the features `cleardebts`, `deletedebt` and `splitdebt`
      [#194](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/194).
        * Initial drafting of skeleton of Developer Guide user profile, user story, and user value sections
      [#31](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/31).


* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [#66](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/66),
    [#127](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/127)
  
  * Reported bugs and suggestions for other teams (accepted):
    [#118](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/118), 
  [#125](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/125), 
  [#128](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/128), 
  [#132](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/132), 
  [#137](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/137),
  [#139](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/139),
  [#142](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/142),
  [#148](https://github.com/AY2223S1-CS2103-F14-1/tp/issues/148)

