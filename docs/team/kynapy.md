---
layout: page
title: Ang Ping Young (Kiyan)'s Project Portfolio Page
---


### Project: BookFace
BookFace replaces a paper-based system or manual tracking of books, providing greater speed/efficiency in identifying where a book is, or when it is due. It also adds a member-tracking system to handle an increasing number of library members.

Given below are my contributions to the project:

* **Summary of Contributions**:
  * **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kynapy&breakdown=true)
  
  * **New Feature**: Implemented the list all, loans and overdue feature
      * What it does: Allows the user to view all loaned and overdue books, as well as refresh the GUI to the default state.
      * Justification: The user would definitely want to be able to keep track of all the loaned out books, in particular the books that are overdue for returns. With that comes the need to refresh the GUI back to the default state after listing all the loans
      * Highlights: The user can specifically find loans that are overdue. The list loan and list overdue functionality has a O(Books + Users) time complexity due to having to parse through the entire list, so potential improvements to the implementation could include the use of `Loans` as an association class.

  * **Enhancements implemented**:
    * ---To be added soon---

  * **Documentation**:
    * User Guide:
      * Added Table of Contents and the Quick Start guide.
    * Developer Guide:
      * Added and maintained the Table of Contents.
      * Added the user stories.
      * Added implementation details of the `find` feature.