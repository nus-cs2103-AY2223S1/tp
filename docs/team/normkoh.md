---
layout: page
title: Koh Wei Quan Norman's Project Portfolio Page
---

### Project: SoConnect

<<<<<<< HEAD
#### Overview : to be added soon
=======
#### Overview : 

SoConnect is a desktop address book application designed for National University of Singapore (NUS) Computer Science Undergraduates to keep track of their University Social Circle. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

>>>>>>> dfc46836bc054cea3f9264f49a7bd58252aa9ad7
#### Summary of contributions :

Given below are my contributions to the project.

- **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=normkoh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- **New Feature**: Added a pie chart to the app.
  - What it does: It integrates with the contact list to provide a visual overview of the user's social network.
  - Justification: This feature improves the product as it provides a convenient way for users to get a sense of the spread and size of their social network without having to scroll through the contact list.
  - Highlights: The implementation of this feature was challenging as it required deep knowledge of how to use javafx, in particular the javafx PieChart class. It was challenging to create a custom PieChart class from the javafx API that can integrate with the data in the contacts list.
  - Credits: One of the methods in the custom PieChart class was reused with minor modifications from [this StackOverflow post](https://stackoverflow.com/questions/35479375)
- **Project Role**
  - In charge of managing the UI for most of the features.
- **Enhancements to existing features**:
  - Modified delete command to allow for multiple inputs (Pull request [#48](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/48))
  - Added a new field that can be used when adding new contacts. (Pull request [#54](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/54))
  - Updated GUI to accommodate new fields that were added. (Pull requests [#33](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/33) [#63](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/63) [#100](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/100))
  - Wrote additional tests for existing features. (Pull requests[#191](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/191))
- **Documentation**:
  - User Guide:
    - Added documentation for the features `student` `prof` `ta` `delete` and pie chart feature. [#104](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/104)
  - Developer Guide:
    - Added implementation details of the `delete` feature. [#69](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/69)
    - Wrote use cases for adding a student and deleting a person from contact list. [#104](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/104)
- **Community**:
  - PRs reviewed(with non-trivial review comments): [#31](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/31) [#37](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/37)
