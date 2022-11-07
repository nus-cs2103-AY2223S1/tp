---
layout: page
title: Gareth Ong's Project Portfolio Page
---
### Project: SoConnect

SoConnect is a desktop address book application designed for National University of Singapore (NUS) Computer Science Undergraduates. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=GarethOng&breakdown=true)


* **New Feature**: Added the ability to sort contacts according to specified fields with a Command

  * What it does: Allow the user to sort the contacts either by their name or module code in ascending or descending order using `sort A-Z n/`(sort by name) or `sort A-Z m/` (sort by module code).

  * Justification: This feature allows user to organise the contact information for faster retrieval.

* **New Feature** Added the ability to export contacts within the address book as a comma-separated values (CSV) file.

  * What it does: Allows the user to convert the entire address book into a CSV file.
  
  * Justification: This feature allows user to share contact information quickly and also serves as a way to back up their data.
  
  * Credits: Tutorial on using Jackson to convert JSON into CSV and vice versa from [here](https://www.baeldung.com/java-converting-json-to-csv)

* **New Feature** Added the ability to import a comma-separated values (CSV) file containing contact information into the application.

  * What it does: Allows the user to convert a CSV file containing contact information into the address book.

  * Justification: This feature intention is to be used with the export function whereby user can import the CSV file created by another user, furthermore, it allows the user to quickly add in multiple contacts.

  * Credits: Tutorial on using Jackson to convert JSON into CSV and vice versa from [here](https://www.baeldung.com/java-converting-json-to-csv)

* **New Field** Added new `ModuleCodes` field for Student

  * What it does: Allows user to create a Student object with multiple module codes.

  * Justification: Users can keep track of friends that are taking multiple similar modules together.

* **New Field** Added new `OfficeHour` field for Professor

  * What it does: Allows user to create a Professor object with office hour in day and time format.

  * Justification: Users can track their Professor's office hour to better prepare for consultation.

* **Project management**:

    * Set up initial structure of User Guide
  
    * Managed releases `v1.3 - v1.4` on GitHub


* **Documentation**:

    * User Guide:
  
      * Added documentation for the features `sort`, `export` and `import`
      
    * Developer Guide:
      * Added implementation details for `sort`, `export` and `import` with sequence and activity diagrams

* **Community**:

    * Reviewed over 24 PRs
  
    * Initiated design-related discussions
  
    * Contributed to discussions
