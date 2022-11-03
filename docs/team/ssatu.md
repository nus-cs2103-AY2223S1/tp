---
layout: page
title: Damien's Project Portfolio Page
---

### Project: LongTimeNoSee(LTNS)

Long Time No See (LTNS) is a desktop task tracking app made for financial advisors to manage their contact base and stay connected with their clients. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

Given below are my contributions to the project.

* **New Feature**: Added the main representation of our users' available policies, i.e: The `Policy` class.
  * Justification: This feature allows us to store policies sold by the user.
  * Highlights:  It stores information about a given policy, like policy duration, premium and coverage type.

* **New Feature**: Added commands to create, delete, list policy information.
  * Justification: This features allows users to store, delete and interact with policies that they would want to add.
  * Highlights: Creation of policies include creation of Company, Coverage, Title and Commission classes and parsing them.

* **New Feature**: Added logic for users to switch between different views of information stored, via JavaFX's cards and panels.
  * Justification: This features allows users to switch between views of policy, clients etc. whilst avoiding cluttering the screen with all the information.
  * Highlights: This feature was made with extensibility to any future features created (i.e. Viewing income, events).

* **New Feature**: Added the main representation of policies assigned to users, i.e.: The `AssignedPolicy` class.
  * Justification: This features allows users to store policy sales made to each of their clients.
  * Highlights: It stores information relevant to the user for a policy sold, like premium, starting date and end date.

* **New Feature**: Added functionality to assign, delete, list and view policies to users.
  * Justification: This feature allows users to track policy sales made to their clients.
  * Highlights: It utilises the indexing multiple lists of data (policy list and client list), and the functionality of switching between different views.


* **Code contributed**: [https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ssatu&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other]()

* **Project management**:
  * TODO

* **Documentation**:
  * User Guide:
    * Updated segments on adding, deleting, listing and viewing a policy.
    * Updated segments on assigning policies to clients.
    * Updated segments on deleting, listing and viewing assigned policies.
    * Standardisation and editing of user commands.

  * Developer Guide:
    * Updated Storage class diagrams for Storage component.
    * Added implementation for PolicyAssignCommand and Sequence diagram.
    * Updated use cases and Non-functional Requirements.

* **Community**:
  * PRs reviewed (with non-trivial review comments): 7
  * Contributed to forum discussions:  1 original post
  * Reported bugs and suggestions for other teams in the class: For UserGuide and DeveloperGuide

* **Tools**:
  * Git / Github
  * Intelli-J
  * Sourcetree


