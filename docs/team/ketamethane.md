---
layout: page title: Tan Cheong Hsien Ryan's Project Portfolio Page
---

### Project: SETA

SETA is a desktop application for CS2103T Teaching Assistants to track their students’ and tutorials’ details, and
questions asked by students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, SETA enables you to manage your tutorials and track your students
more effectively than traditional GUI apps. Given below are my contributions to the project.

**New Feature**: Added the ability to add a question.
* What it does: allows the user to add a question raised by a student during a Zoom session into the list of
      questions.
* Justification: This feature enables the user to keep track of the list of questions raised by students to address
      them. This is used especially when the user does not have time to answer the question during the tutorial or 
      if he needs to clarify the content with the professor before answering the question.
* Highlights: The implementation requires the addition of a new class (Question).

**New Feature**: Added the ability to mark a question.
* What it does: allows the user to mark a question raised by a student during a Zoom session as important. 
* Justification: This feature enables the user to prioritise which questions to address first. Such questions could be
  important or requires an urgent response.
* Highlights: The implementation requires the addition of a new class (ImportantTag).

**New Feature**: Added the ability to unmark a question.

* What it does: allows the user to mark a question raised by a student during a Zoom session as unimportant.
* Justification: This feature enables the user to undo the action of accidentally marking a particular question as
  important.
* Highlights: The implementation requires the addition of a new class (ImportantTag).


**New Feature**: Added the ability to delete a question.
* What it does: allows the user to delete a question in the list of questions.
* Justification: This feature enables the user to keep track of the list of questions raised by students to address
      them, removing the questions he has already answered.


* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ketamethane&breakdown=true)


* **Project management**:
    * 


* **Documentation**:
    * User Guide:
        * Added documentation for the features `addq`, `markq`, `unmarkq` and `deleteq` [\#19](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/19)
        * Added details for all the features, being explicit how they help the user [\#200](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/200)
        * Improved the clarity of guide, such as the explanation for a CLI [\#200](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/200)

    * Developer Guide:
        * Added implementation details of the `addq` feature [\#98](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/98)
        * Added use cases for the features implemented [\#23](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/23)
        * Updated the `model` component section [\#117](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/117)


* **Community**:
    * Helped to review PRs and suggested changes.
        * Add MarkTut [#55](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/55)
        * Update storage and ui [#62](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/62)
        * Add AddStuCommand and EditStuCommand Parser Tests [#113](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/113)
    * Answering questions in the CS2103T forum
        * Line ending issue for CI \r\n [#336](https://github.com/nus-cs2103-AY2223S1/forum/issues/336)


* **Tools**:
    * 
