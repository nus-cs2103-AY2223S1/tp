---
layout: page title: Gregg's Project Portfolio Page
---

### Project: Watson, The Teacher's Companion

Watson is a desktop database application used to reduce the administrative workload of teachers greatly. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
    * `Subject`: Added a Subject class.
        * What it does: allows subjects to be tagged to SubjectHandler which is tagged to Student.
        * Justification: Since our application is targeted at reducing the administrative workload of teachers, it is
          essential for the application to be able to keep track of the Subjects taken by each student.
    * `Grades`: Added a Grades class.
        * What it does: allows Grades to be tagged to Subject.
        * Justification: Since our application is targeted at reducing the administrative workload of teachers, it is
          essential for the application to be able to keep track of the Grades for each Subject taken by each student.

* **Code contributed**:
    * `Pull Requests`:
        * [#15]: Added a skeletal Personal Portfolio Page
        * [#37], [#41]: Updated User Guide
        * [#49]: Added Subject and Grades class for Person
        * [#72]: Added to Developer Guide
        * [#84]: Fixed json to store and read Subject and Grades in a Person
        * [#102]: Updated User Guide
        * [#106], [#107]: Changed format for Subject, Grades and Attendance in the GUI
        * [#109]: Modifications to the Sort Command
        * [#119]: Refactored various classes in the Person model
        * [#187]: Added constraints to the Grades class
        * [#192], [#195]: Fixed some issues with Subject and Grades

* **Project management**:
    * Group Allocator 

* **Enhancements to existing features**:
    * `to be added soon`

* **Documentation**:
    * User Guide:
        * [#37], [#41], [#102]: Updated and added to the User Guide. 
    * Developer Guide:
        * [#72]: Added to the Developer Guide
* **Community**:
    * `to be added soon`

* **Tools**:
    * `to be added soon`
