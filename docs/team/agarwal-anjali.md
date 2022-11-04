---
layout: page
title: Anjali Agarwal's Project Portfolio Page
---

### Project: CLInkedIn

CLInkedIn is a desktop address book application made for Recruiting and Hiring Managers.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Create/Edit/Delete TagTypes 
  * What it does?
    * CLInkedIn provides the user with 3 basic tag types: Skills, Degree, and Job Type. But if a user wish to add tags of other types they can create their own custom tag types.
    * So using this feature a user can:
      1. Create a new tag type and provide a custom prefix for it. Example: `createTagType Grade gpa`, where gpa is the prefix for the Grade tag type. 
      2. Edit an existing tag type. Example: `editTagType Grade-Marks gpa-m`, this edits the existing Grade tag type to Marks and its prefix from gpa to m.
      3. Delete an existing tag type. Example: `deleteTagType Skills`, now Skills tag type will no longer be available for use.
  * Implementation specifications:
    * In order to keep track of the tag types available to the user and their respective prefix a static field `prefixMap` of `Map` reference data type is created in the `UniqueTagTypeMap` class. The `prefixMap` maps each `Prefix` for a tag type to its respective `TagType`. Moreover, to initially provide the user with 3 basic tag types another static field `initialTagTypeMap` of `Map` reference data type was initialised with the prefix and tag types for `Skills`, `Degree` and `Job Type`. So when a user runs the application for the first time the `prefixMap` is initialised with this `initialTagTypeMap`. Furthermore, to store and retrieve the available tag types from the storage a static variable `prefixMap` is added to the address book that shares its reference with the `prefixMap` in the `UniqueTagTypeMap` and the `JsonSerializableAddressBook` is also updated for the same.
    
* **New Feature**: Add/Delete Links
  * What it does?
    * A user can now also add/edit/delete links to various social media profiles/other websites to a candidate and can view the webpage by clicking on the icon of the relevant link for a candidate.
    * So using this feature a user can:
      1. Add 1 or more links to a candidate. Example: `addLink 1 l/https://instagram.com/username l/https://github.com/username` adds the links to person at index 1.
      2. Edit existing links by replacing all existing links with new links. Example: `edit 2 l/https://telegram.com`.
      3. Delete all links of a candidate. Example: `deleteLink 2` deletes all links of person at index 2.
  * Implementation specifications:
    * The `Person` class has a field that stores a `Set` of links for the candidate.
    * The parser converts the input for links from String to a valid URL using java library for URL and creates a new instance of `Link` class for each URL and then the `Link` class generates the platform as the name of the platform if it is recognised by CLIinkedIn, or else as general so that it can display the platform specific icon for the link in the GUI.
    * Furthermore, to store and retrieve the links from the storage, a `JsonAdaptedLink` class is created that stores the links in String format and while reloading the application converts the String to the required URL type.


* **Enhancements to existing features**:
  * Extended the `add` command to support the addition of links for a candidate while adding a new candidate.
  * Extended the `edit` command to support the modification of existing links of a candidate.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=agarwal-anjali&breakdown=true)


* **Project management**: 
  * Pull requests reviewed and merged: [\#1](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/236), [\#2](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/224), [\#3](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/153), [\#4](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/150), [\#5](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/145), [\#6](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/139), [\#7](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/137), [\#8](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/129), [\#9](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/121), [\#10](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/119), [\#11](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/118), [\#12](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/112), [\#13](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/109), [\#14](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/90), [\#15](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/78), [\#16](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/74), [\#17](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/49), [\#18](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/48), [\#19](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/47), [\#20](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/43), [\#21](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/38), [\#22](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/32), [\#23](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/23)


* **Contributions beyond the project team**:
  * Bugs reported in Practical Exam Dry Run: [\#1](https://github.com/agarwal-anjali/ped/issues/1), [\#2](https://github.com/agarwal-anjali/ped/issues/2), [\#3](https://github.com/agarwal-anjali/ped/issues/3), [\#4](https://github.com/agarwal-anjali/ped/issues/4), [\#5](https://github.com/agarwal-anjali/ped/issues/5), [\#6](https://github.com/agarwal-anjali/ped/issues/6), [\#7](https://github.com/agarwal-anjali/ped/issues/7), [\#8](https://github.com/agarwal-anjali/ped/issues/8), [\#9](https://github.com/agarwal-anjali/ped/issues/9), [\#10](https://github.com/agarwal-anjali/ped/issues/10)


* **Documentation**:
    * User Guide:
      - Added documentation for the different kinds of `tag type` commands.
      - Added documentation for the different kinds of `link` commands.
      - Updated documentation for different kind of `note` and `rate` commands.
      - Updated images and command summary.
    * Developer Guide:
      - Added details for the Target User Profile, Value Proposition, and User Stories.
      - Added details for the Use Cases.
      - Added implementation details for the `Tag Types` feature. 
