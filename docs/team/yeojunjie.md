---
layout: page
title: Yeo Jun Jie's Project Portfolio Page
---

### Project: Teaching Assistant Assistant

Teaching Assistant Assistant (TAA) is a desktop app for Teaching Assistants (TAs). It keeps track of TAs’ students,
tutorial groups, and tasks.

Given below are my contributions to the project.

- **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yeojunjie&breakdown=true)

- **New Feature**: Clicking on a task in the graphical user interface shows more information about the task.

  - This feature was implemented in [pull request #100](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/).
  
  - This feature allows the Teaching Assistant to view the students for which the task has to be done without having to type in a command.
  
  - This feature was challenging to implement correctly because JavaFX behaves differently across platforms. This lead to bugs that I had to [fix](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/194).
  
  - I wrote about the implementation and design considerations of this feature in [this section](https://ay2223s1-cs2103t-t13-1.github.io/tp/DeveloperGuide.html#expanding-tasklistcard-feature) of our developer guide.

- **Enhancements implemented**: I enhanced the user interface (UI) by simplifying the underlying code, and standardising margins between UI elements.

  - This was accomplished by using a minimal amount of the appropriate UI elements.
  
    - In [pull request #74](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/74), I reduced `CommandInput.fxml` from 22 lines of code down to 11 lines of code.
  
    - In [pull request #94](), I reduced `StudentCard.fxml` from 35 lines of code down to 21 lines of code.

- **Documentation**:

    - User Guide:
  
        - In the early stages of development, I designed the [initial mock-up of the user interface](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/34).
      
        - During the dry run of the Practical Examinations, my team's user guide received eleven bug reports. I managed and resolved them in pull requests [#150](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/150), [#151](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/151), [#197](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/197), [#198](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/198), [#199](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/199), and [#200](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/200).

    - Developer Guide:
  
        - I updated the class diagrams in pull requests [#125](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/125) and [#131](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/131).

- **Reviewing/mentoring contributions**:

    - I made sure that the user guide was worded clearly by carefully reviewing proposed changes to `UserGuide.md`. An example would be the suggestions I made to [pull request #124](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/124).

