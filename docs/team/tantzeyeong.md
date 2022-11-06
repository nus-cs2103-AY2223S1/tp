---
layout: page
title: Tan Tze Yeong's Project Portfolio Page
---

### Project: myStudents

myStudent is **an easy-to-use, portable and aesthetically-pleasing desktop application for tuition centre admins
for managing the students, tutors and tuition classes of a tuition center**. 
It is optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 

As a tuition center admin, managing vast amounts of information can be daunting and prone to error using existing solutions 
such as Microsoft Excel, or traditional pen and paper. That is why we have developed myStudent as the one-stop solutions for effective data management,
in tuition centers. 

myStudent is adapted from an existing Java Application
[Address Book (Level 3)](https://se-education.org/addressbook-level3/), and was developed by 
my team of 5 NUS Computer Science students 

Given below are my contributions to the project.

### Code contributions ###
All of my code contributions can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tantzeyeong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16).


## Enhancements Implemented ##

### Major Enhancements ###
- Introduced the `Student` class to Model [#40](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/40)
  - Introduced individual field classes for the `Student` class
- Introduced the `Tutor` class to Model [#40](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/40)
  - Introduced individual field classes for the `Tutor` class
- Introduced the `TuitionClass` class to Model [#40](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/40)
  - Introduced individual field classes for the `TuitionClass` class
- Find by fields feature [#118](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/118)
  - Description: This feature allows users to search for a `Student`, `Tutor` or `TuitionClass` based on multiple fields. Previously, the find command could only search for `Persons` based only on their name. Now, the new find feature gives users the flexibility of searching by more many more fields at the same time.
  - Benefits: The enhancement added drastically improves the efficiency of the find feature as users now have more options to reduce displayed list to their desired outcome. Being able to input multiple keywords to match multiple fields of an entity at the same time also significantly increases the efficiency of the user's search.
  - Highlights: This feature required a series of modifications to allow for the command to except multiple `prefixes` and `keywords`, which includes the introduction of the `FindCommandParser`. Furthermore, the command had to account for the different entities involved (`Student`,`Tutor`, `TuitionClass`) and their respective `prefixes`. A substantial amount of tests codes was also needed to be designed ensure the feature was bug-free.

### Minor Enhancements ###
- Introduced the `NextOfKin` class to Model to allow `NextOfKin` to be assigned to a `Student` [#84](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/84)

## Other Contributions ##
  - Project-management:
    - Managed milestones for team repository.
    - Managed release [v1.3.trial](https://github.com/AY2223S1-CS2103T-F12-4/tp/releases/tag/v1.3.trial) on GitHub.
  - Documentation
    - User Guide
      - Added documentation for `find` command / search by multiple fields feature.
    - Developer Guide
      - Added documentation for search by multiple fields feature.
      - Added diagrams for search by multiple fields feature.
      - Added proposed implementations for search by multiple fields feature.
  - Team-based tasks
    - PRs reviewed: [#42](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/42), [#62](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/62), [#67](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/67), [#87](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/87), [#113](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/113), [#144](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/144), [#208](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/208), [#211](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/211)
    - The full list of PRs I have reviewed can be found [here](https://github.com/AY2223S1-CS2103T-F12-4/tp/pulls?q=is%3Apr+commenter%3Atantzeyeong).