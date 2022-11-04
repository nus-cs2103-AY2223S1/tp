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
    * A user can add tags to person of a specific tag type. CLInkedIn provides the user with 3 basic tag types: Skills, Degree, and Job Type. But if a user wish to add tags of other types they can create their own custom tag types (Example: if they want to add grades as tags they might want to create a tag type called Grade or something equivalent).
    * So using this feature a user can:
      1. Create a new tag type and provide a custom prefix for it. Example: `createTagType Grade gpa`, where gpa is the prefix for the Grade tag type.
      2. Once a tag type is created a user can add/edit/delete tags of the that tag type using another feature that provides the user with commands like `addTag`, `edit`, `deleteTag`. Sample usage: `addTag 1 gpa/4`, this adds a `Grade` type tag of `4` to user at index 1 in the addressbook. 
      3. Edit an existing tag type. Example: `editTagType Grade-Marks gpa-m`, this edits the existing Grade tag type to Marks and its prefix from gpa to m.
      4. Delete an existing tag type. Example: `deleteTagType Skills`, now Skills tag type will no longer be available for use.
  * Implementation specifications:
    * In order to keep track of the tag types available to the user and their respective prefix. A static field `prefixMap` of `Map` reference data type is created in the `UniqueTagTypeMap` class. The `prefixMap` maps each prefix for a tag type to its respective tag type. So to store a tag type a `TagType` class was also created that takes in a name for the tag type and its required prefix as parameters in the constructor. Moreover, to initially provide the user with 3 basic tag types another static field `initialTagTypeMap` of `Map` reference data type was initialised with the prefix and tag types for `Skills`, `Degree` and `Job Type` tag types. So when a user runs the application for the first time the `prefixMap` is initialised with this `initialTagTypeMap` and then as and when new tag types are created/edited/deleted it gets reflected in the `prefixMap`. Furthermore, to store and retrieve the available tag types from the storage a static variable `prefixMap` is added to the address book that shares its reference with the `prefixMap` in the `UniqueTagTypeMap` and the `JsonSerializableAddressBook` is also updated for the same.
    
* **New Feature**: Add/Delete Links
  * What it does?
    * A user can now also add/edit/delete links to various social media profiles/other websites to a candidate. Once the links are added an icon relevant to the link appears below the name of the candidate.
    * So using this feature a user can:
      1. Add 1 or more links to a candidate by providing a link with the protocol. Example: `addLink 1 l/https://instagram.com/username l/https://github.com/username` adds both the links to person at index 1 in the address book.
      2. Once the link is added a user can view the webpage by clicking on the icon of the relevant link for a candidate.
      3. Edit existing links by replacing all existing links with new links. Example: `edit 2 l/https://telegram.com` replaces all existing links of a candidate at index 2 with this new link.
      4. Delete links of a candidate. Example: `deleteLink 2` deletes all links of person at index 2 in the address book.
  * Implementation specifications:
    * In `ParserUtil` a parseLink method was created that converts user input for links in String format to a valid URL using a Java library for URL and creates a new instance of `Link` class for each URL.
    * A `Link` class was created that takes in a URL as a parameter in the constructor and generates a platform for the provided link if the link is for GitHub, LinkedIn, Telegram, Instagram, Discord, Twitter, Facebook, or Snapchat, or it initialises the platform as general (if the platform is not recognised by the application) to display the platform specific icon for the link in the GUI.
    * The `Person` class has a `Set<Link>` reference data type field `links` that stores a list of links added to a candidate. 
    * Furthermore, to store and retrieve links for a candidate from the storage, a `JsonAdaptedLink` class is created that stores the links in String format and while reloading the application converts the String to the required URL type.

* **Enhancements**:
  * Enhancements to existing features**:
    * Extended the `add` command to support the addition of links for a candidate while adding a new candidate.
    * Extended the `edit` command to support the modification of existing links of a candidate.
  * Added more tests for newly implemented features to ensure the features do not have any unwanted bugs.

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

* **Community**:

* **Tools**:

