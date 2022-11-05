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
[https://github.com/tantzeyeong/tp](https://github.com/tantzeyeong/tp)

All of my code contributions can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tantzeyeong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16).


### Enhancements implemented

## Major enhancements
- Introduced the `Student` class to Model
- Introduced the `Tutor` class to Model
- Introduced the `TuitionClass` class to Model
- Find by fields feature
  - Description: This feature allows users to search for a `Student`, `Tutor` or `TuitionClass` based on multiple fields. Previously, the find command could only search for `Persons` based only on their name. Now, the new find feature gives users the flexibility of searching by more many more fields at the same time.
  - Benefits: The enhancement added drastically improves the efficiency of the find feature as users now have more options to reduce displayed list to their desired outcome. Being able to input multiple keywords to match multiple fields of an entity at the same time also significantly increases the efficiency of the user's search.
  - Highlights: This feature required a series of modifications to allow for the command to except multiple `prefixes` and `keywords`, which includes the introduction of the `FindCommandParser`. Furthermore, the command had to account for the different entities involved (`Student`,`Tutor`, `TuitionClass`) and their respective `prefixes`. A substantial amount of tests codes was also needed to be designed ensure the feature was bug-free.
## Minor enhancements
- Introduced the `NextOfKin` class to Model

* **Project management**:
  * to be added soon

* **Enhancements to existing features**:
  * to be added soon

* **Documentation**:
  * User Guide:
    * to be added soon
  * Developer Guide:
    * Added user stories and use cases for the `delete` and `list` features.
    * Added non-functional requirements

* **Community**:
  * to be added soon

* **Tools**:
  * to be added soon
