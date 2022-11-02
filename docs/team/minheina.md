---
layout: page
title: Min Hein Aung's Project Portfolio Page
---

### Project: FoodWhere

**FoodWhere is a desktop application for managing your food stall addresses and reviews.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Feature:** Added the ability to edit stall and review entries 
    * What is does: allows the user to edit created stall and review entries by using the edit commands, `sedit` and `redit` for editing stall and review entries respectively. ([#168](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/168), [#172](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/172))
    * Justification: This feature improves the product significantly because a user can make mistakes when creating a stall or review entry, this feature would be able to provide a convenient way to rectify them.
  

* **Enhancement:** Modified`Person` class in model to `Stall` in breath-first approach
    * First, data classes of `Person` that does fit the application needs are removed and affected code and tests refactored. ([#86](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/86))
    * Next, `Person` class is modified to `Stall` and affected functional code and tests in the codebase are refactored. ([#92](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/92))
    * Finally, reference to new data class `Review` is added and wrote test cases related to `Review` data class. ([#110](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/110), [#140](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/140))
  
* **Code Contributed**
    * [Link to RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=minheina&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16)
  
* **Project Management**
    * Lead bi-weekly team meeting as team lead.
    * Enforced deadlines by creating and managing milestones.
    * Ensured application is released, `v1.1` - `v1.3`, on time on GitHub.
    * Approved and merged PRs of implementations done by teammates.
  
* **Documentation**
    * User Guide
        * Created draft version and wrote skeleton for teammates to fill in ([Link](https://docs.google.com/document/d/1qKuFiblI87qOo_bwb8BVN7ceURORT2aTEqWB8rIz-FU/edit?usp=sharing))
    * Developer Guide
        * Added use cases writeup. ([#42](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/42), [#270](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/270))
        * Added implementation details of review add, delete, edit and list with appropriate supporting UML diagrams. ([#186](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/186), [#321](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/321), [#317](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/317), [#321](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/321))
        * Updated details of the model component and its corresponding UML diagrams to match the implementation of FoodWhere. ([#186](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/186))
        * Added manual testing instructions in appendix 
      
* **Community**
    * Set up tags in GitHub.
    * Reviewed PRs and provide non-trivial comments. ([#136](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/136), [#324](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/324))
    * Ensured README is updated with the latest feature additions to application .([#24](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/24), [#242](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/242), [#333](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/333))
    * Carried out UAT and took project demo screenshots for `v1.2`
    * Wrote and coordinated [UAT Plan](https://docs.google.com/document/d/1-yDp0FqUvktWX_HFn6x3ivM5DEfRS7Kja2buOSjwsJ0/edit?usp=sharing) for `v1.3`.
    
* **Tools**
    * IntelliJ IDEA
    * JavaFX Scene Builder
