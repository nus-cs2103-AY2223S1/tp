---
layout: page
title: Wee Xin Yang, Markus' Project Portfolio Page
---

### Project: Teacher’s Address Book (TAB)

Teacher’s Address Book (TAB) is a **desktop app made for teachers, teaching assistants(TA) and professors for managing
contacts of each other, as well as their students, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks
done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented the `Professor` class
    * What it does: It encapsulates the details of a Professor (i.e. their roles).
    * Justification: Allows each person in TAB to be differentiated by their roles, with Professor being one of them
    * Highlights: The `Professor` class extends the abstract `Position` class. Roles such as `Coordinator`, `Lecturer`, `Tutor` and `Advisor` can be added so the user can know the roles of each Professor
      
* **New Feature**: Implemented the `Assignment` class
    * What it does: It encapsulates the name, grade and weightage of an assignment.
    * Justification: Each student will be given graded assignments in the course of their learning and as such it is of absolute necessity that this is implemented
    * Highlights: With its encapsulated grade and weightage, we can gauge how well the student is doing academically. This is the part of the AddAssignments feature which will be explained later
* **New Feature**: Added the `AddAssignmentsCommand` command
    * What it does: It adds a list of `Assignment` to every `Student` in TAB
    * Justification: Assignments can be added to every `Student` after they are added. This saves the trouble of adding assignments to each `Student` individually, which can be time consuming
    * Highlights: This command only needs to be ran once. Since each TAB consists of students of the same module, subsequent students added to TAB will be automatically instantiated with assignments if the AddAssignmentsCommand has already been ran before

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yellow-294&breakdown=true)

* **Enhancements to existing features**:
    * AB3 only checks for similarity in `Name` when determining duplicate `Person`. I made changes to check for `Phone` and `Email` as well as it is only logical that these field should also be unique for each `Person`:
      [#156](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/156)

* **Documentation**:
    * User Guide:
        * Fixed UG related bugs after PED: [#148](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/148)
    * Developer Guide:
        * Added implementation details of the AddAssignments feature: [#72](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/72)

* **Community**:
    * PRs reviewed: 
      [#34](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/34),
      [#37](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/37),
      [#70](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/70), 
      [#81](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/81),
      [#155](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/155),
            
    


