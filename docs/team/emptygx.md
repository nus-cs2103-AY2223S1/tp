---
layout: page
title: Marcus Tan's Project Portfolio Page
---

### Project: CLInkedIn

CLInkedIn is a desktop address book application made for Recruiting and Hiring Managers.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=emptygx&breakdown=true)


* **New Feature**:
  * **Find feature**:
    * What it does: Allows the user to find a candidate by name, phone number, email, address, status, notes, tags.
    * Justification: This feature improves the product significantly because a user can look for specific candidates without having to go through the entire list.
    * Highlights: This is an enhancement to the existing find command. Further details on changes found below in the Enhancements section.

  * **Undo/redo feature**:
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This feature only works for commands that modify the state of the list of candidates. The model is used to detect changes in the list of candidates.

  * **Statistics feature**:
    * What it does: Allows the user to view the statistics of the ratings of candidates in the current filtered list through a pie chart.
    * Justification: This feature improves the product significantly because a user can view a quick summary of the ratings of candidates, which can be useful for ranking candidates proportionately.
    * Highlights: This feature uses the JavaFX library to create a pie chart. It required an analysis of design alternatives on where to implement calculate the statistics. In the end, it was decided to implement it in the model component.
    * Credits: *AY2122S1-CS2103T-W08-4 / tp
      https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/188/files# (For piechart UI implementation)*


* **Enhancements to existing features**:
  * **Find feature**: Updated the find feature to have a general and a prefix-based `find` command.
    * Previously: 
      * The find command only allowed the user to find a candidate by name.
      * The find command was case-sensitive.
      * The find command only allowed the user to find a candidate by the exact name.
    * Now:
      * The find command allows the user to find a candidate by name, phone number, email, address, status, ratings, notes and tags.
        * Prefix-based find command allows the user to specify the field to search for when finding a candidate.
        * General `find` command allows the user to find a candidate by searching in all fields.
      * The find command is case-insensitive.
      * The find command allows the user to find a candidate by partial keywords.
    * Justification: This feature improves the product significantly because it makes the find command more flexible and user-friendly.
    * Highlights: This enhancement affects existing commands and commands to be added in future. The implementation too was challenging as it required changes to existing commands.


* **Documentation**:
    * **User Guide**:
        - Added documentation for the adding of optional information.
        - Added documentation for the `find` feature.
        - Added documentation for the `stats` feature.
        - Added documentation for the `undo` and `redo` feature.
    * **Developer Guide**:
        - Added implementation details of the `find` feature.
            - Added sequence diagram for the `find` feature.
            - Added activity diagram for the `find` feature.
    * **Readme**:
        - Designed and added UI mockup.
        - Added acknowledgements.


* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): 
[\#1]( https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/71 ),
[\#2]( https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/80 ),
[\#3]( https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/102 ),
[\#4]( https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/107 )


* **Contributions beyond the project team**: 
  * Bug reports in PED:
[\#1]( https://github.com/emptygx/ped/issues/1 ),
[\#2]( https://github.com/emptygx/ped/issues/2 ),
[\#3]( https://github.com/emptygx/ped/issues/3 ),
[\#4]( https://github.com/emptygx/ped/issues/4 ),
[\#5]( https://github.com/emptygx/ped/issues/5 ),
[\#6]( https://github.com/emptygx/ped/issues/6 ),
[\#7]( https://github.com/emptygx/ped/issues/7 )
