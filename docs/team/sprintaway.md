---
layout: page
title: Ng Yan Jia's Project Portfolio Page
---

### Project: BookFace

* **Overview**: BookFace replaces a paper-based system or manual tracking of books and patrons, providing greater speed/efficiency to librarians.

* **Summary of Contributions**:
  * **Code contributed**: [Link to code](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sprintaway&breakdown=true)
  * **New Feature**: Implemented the loan feature.
    * What it does: Allows the user to loan books to a patron.
    * Justification: The user, presumably a librarian, would want to have a feature that allows them to loan out books to patrons who visit their library
    so that he/she can keep track of books that are loaned out.
    * Highlights: The user can specify return dates that books are supposed to return by, and has a default date of 14 days.The loan
      functionality was difficult to implement due to the high number of bugs that came up relating to
      1. The difficulty of implementing the `PrettyTimeParser` library as it had no documentation or user guide, and commands it supported had to be
     tried out manually, and it did not parse certain date formats correctly.
      2. It was an entirely new feature that had associations between the `Person` and the `Book` class.
    * Credits: `PrettyTimeParser` was used for its Natural Language Processing feature which supports a variety of formats for parsing.

  * **Enhancements implemented**: Most of the loan feature including return date functionality, most of the GUI,
some refactoring of codebase (mostly renaming and editing of preexisting methods/classes or adding in needed classes to support `Book` class, such as addition of `JsonAdaptedBook`), and tests for loan and return commands.
  * **Contributions to the UG**: Added documentation of `return`, `loan`, `find user`, `find book`, `list users`, `list books` features.
  * **Contributions to the DG**: Update of Model diagram, addition of `loan` and `return` implementation (as well as UML diagrams inside)
  * **Contributions to team-based tasks**: Renaming the product, slight refactoring of codebase, added `PrettyTimeParser` (external library),
some reminder of incoming deadlines in group chat, tagging of some issues with correct tags (eg. v1.3 milestone)
  * **Review/mentoring contributions**: Examples of PR review: [PR review 1](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/160), [PR review 2](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/158)
  * **Contributions beyond the project team**: Reported 24 bugs for PE-D
  * **Tools**: Integrated a third party library (`PrettyTimeParser`) to the project


