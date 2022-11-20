---
layout: page
title: Ang Ping Young (Kiyan)'s Project Portfolio Page
---


### Project: BookFace
BookFace replaces a paper-based system or manual tracking of books, providing greater speed/efficiency in identifying where a book is, or when it is due. It also adds a member-tracking system to handle an increasing number of library members.

Given below are my contributions to the project:

* **Summary of Contributions**:
  * **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kynapy&breakdown=true)

  * **New Feature**: Implemented the list all, loans and overdue feature ([\#163](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/163) & [\#164](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/164))
      * What it does: Allows the user to view all loaned and overdue books, as well as refresh the GUI to the default state.
      * Justification: The user would definitely want to be able to keep track of all the loaned out books, in particular the books that are overdue for returns. With that comes the need to refresh the GUI back to the default state after listing all the loans
      * Highlights: The user can specifically find loans that are overdue. The list loan and list overdue functionality has a O(Books + Users) time complexity due to having to parse through the entire list, so potential improvements to the implementation could include the use of `Loans` as an association class.

  * **Enhancements implemented**:
    * Added `delete book` functionality [\#117](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/117)

  * **PR Reviews**: [\#100](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/100), [\#142](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/142)

  * **Documentation**:
    * User Guide:
      * Added Table of Contents and the Quick Start guide.
      * Updated and maintained command summary secttion. 
      * Added documentation for the features `list all`, `list loans` and `list overdue`
    * Developer Guide:
      * Added and maintained the Table of Contents. [\#138](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/138)
      * Added and maintained the user stories. [\#79](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/79)
      * Added implementation details of the `find` feature. [\#138](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/138)
      * Added the Instructions for Manual Testing for Appendix [\#223](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/223)
