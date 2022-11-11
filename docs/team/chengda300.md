---
layout: page
title: Lee Cheng Da's Project Portfolio Page
---

### Project: InterNUS

InterNUS is a convenient and powerful desktop app, created to help **NUS CS students manage their internship applications.**
It is optimized for use via a Command Line Interface (CLI),
and complemented with a simple yet intuitive Graphical User Interface (GUI)
designed to help you keep track of all your applications at a glance.

Below are my contributions to the project.

* **New Feature**: Added the ability to sort person list by name or company name
  * What it does: Sorts the list of persons in the app in alphabetical order
  * Justification: It is easier for the user to search manually
  * Credits: [Java's Comparator class document](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#comparing-java.util.function.Function-java.util.Comparator-)

* **New Feature**: Added the ability to show the full internship list stored in the app
  * What it does: Shows the list containing all internships stored in the app
  * Justification: There should be a way to revert filters. With the recent relaxation of command inputs however, this feature feels redundant since users can simply search with an empty filter.
  * Credits: Existing code's "list persons" feature

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=chengda300)

* **Enhancements to existing features**:
  * Relaxed input syntax to also accept blanks even if specified for optional fields

* **Documentation**:
  * About Us:
    * Added my own details
  * User Guide:
    * Added skeleton for "find internship"
    * Added "list internship"
    * Added "sort person"
  * Developer Guide:
    * Added "sort person" implementation, activity diagram, sequence diagram, use case and manual test cases.

* **Code Reviews**: [#145](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/145)
