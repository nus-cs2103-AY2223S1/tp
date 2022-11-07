---
layout: page
title: Tiang Hui Zheng's Project Portfolio Page
---

# Overview
ModQuik is a desktop app that allows Teaching Assistants to keep track of their responsibilities, students’ progress and schedules for their semester.

## Summary of contributions
Given below are my contributions to the project.

* **Code contributed**: [heyzec's tP Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=heyzec&breakdown=true)

* **Enhancements implemented**:
  * Added finding students command
    * What it does: Via the `find` command, the TA is able to see a selected list of students based a search criteria, e.g. student name,
student ID, module and tutorial.
    * Justification: As there are many students, having such a feature allows the TA easily narrow down to specific students.
    * Highlights: By using the Java's Predicate class, it is very easy for future developers to extend the code to introduce more filtering criteria.
  * Added extracting emails command
    * What it does: After using the `find` command, the TA can then use the `extract emails` command, to quickly send mass emails to selected students.
ModQuik generates a deeplink URL which, when pasted into the browser, redirects the user to NUS WebMail with a new email draft with the intended recipients.
    * Highlights: This feature was interesting to implement as it required searching online to figure out the format of the deeplink to redirect to a
Microsoft Web Outlook. It also required more testing to ensure the behaviour is the intended one across various operating systems.
  * Ability for ModQuik to recognise date and time
    * What it does: The newly created internal Date and Time classes make use of Java's datetime packages in order to parse user input.
    * Justification: This is required so that other entities are able to sorted. Moreover, it required for further enhancements, such as ability to detect conflicting schedules.
    * Highlights: This enhancement was a challenging one as it required multiple rounds of refactoring in order to adhere to the software design principles of DRY and SLAP.

* **Documentation**:
  * User Guide:
    * `find` command
    * `extract emails` command
    * Added links to other sections
    * Added prefix summary
    * Formatting and phrasing of the user guide
  * Developer Guide:
    * `find` command
    * `extract emails` command
    * Logic subsection of the architecture section

* **Contributions to team-based tasks**:
  * Organised issue tracker by tagging bugs with the appropriate labels for easier identification.
