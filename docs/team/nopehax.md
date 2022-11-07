---
layout: page
title: Teng Wye's Project Portfolio Page
---

### Project: myStudent

myStudent is **an easy-to-use, portable and aesthetically-pleasing desktop application for tuition centre admins
for managing the students, tutors and tuition classes of a tuition center**.
It is optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nopehax&breakdown=true)


* **Enhancements to existing features**: Save data for students, tutors and tuition classes separately.
  * What it does: The data for each entity is saved to their own individual .json files
  * Justification: Since the application handles data of 3 different entities, it will be messy and complicated to have all the data in a single file. When a user only wants to swap out the data of a certain entity they can just change the respective files instead of the entire thing.
  * Highlights: Requires the knowledge of how the data is being converted from a java object to a json-friendly format to be written to the .json file, and to convert them back during loading of the saved data. Implementation was  challenging as it requires the changing of existing commands from both the Storage and Model components of the application. There was also a challenge when adding the separate data into a single address book.


* **New Feature**: Export the data into .csv files.
    * What it does: The data for each entity is converted to csv format to be used in other applications.
    * Justification: Since myStudent is only a data management application, users will have to use other applications such as Microsoft Excel to do data analysis tasks. Since csv is a common file format, it can be used with many applications.
    * Highlights: Requires the knowledge of how to convert the data from json format to csv format.


* **New Feature**: Sort the list of students, tutors or tuition classes.
  * What it does: Sorts the list of the different entity by alphabetical order, from oldest to newest updated and reverse order.
  * Justification: Allows the user to better organize the lists and make searching for entries easier.
  * Highlights: Implementing the sort functions was a challenge as I encountered many problems I had not thought of initially. For example, after sorting the list alphabetically, the list couldn't be sorted back to its original state as the information was gone. I had to find a method to store that data and there were many alternatives to do that.


* **Project management**:
    * Managed releases  


* **Documentation**:
    * User Guide:
        * Quality check
        * Product description
        * How to use the user guide
        * Sort Command
        * Export feature  

    * Developer Guide:
        * Sort Command


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/60), [\#241](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/241)
