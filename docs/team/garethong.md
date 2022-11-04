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

* **New Feature** Added the ability to export contacts within the address book as a comma-separated values (CSV) file in.

  * What it does: Allows the user to convert the entire address book into a CSV file.
  
  * Justification: This feature allows user to share contact information quickly and also serves as a way to back up their data.
  
  * Credits: Tutorial on using Jackson to convert JSON into CSV and vice versa from [here](https://www.baeldung.com/java-converting-json-to-csv)

* **New Feature** Added the ability to a comma-separated values (CSV) file containing contact information into the application.

  * What it does: Allows the user to convert a CSV file containing contact information into the address book.

  * Justification: This feature intention is to be used with the export function whereby user can import the CSV file created by another user, furthermore, it allows the user to quickly add in multiple contacts.

  * Credits: Tutorial on using Jackson to convert JSON into CSV and vice versa from [here](https://www.baeldung.com/java-converting-json-to-csv)

* **Documentation**:

    * User Guide:
  
      * Added documentation for the features `sort`, `export` and `import`
      
    * Developer Guide:
      * Added implementation details for `sort`, `export` and `import` with sequence and activity diagrams