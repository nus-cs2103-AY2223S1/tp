---
layout: page
title: Jason Ciu Putra Sung's Project Portfolio Page
---

### Project: InternConnect

InternConnect is a one-stop, convenient, and efficient platform to manage and empower how internship campus recruiters
who prefer CLI to GUI work with their applicants’ data. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Added a feature to import applicants from an external JSON file. (PR[#137](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/137))
  * What it does: allows the user to append their current list with applicants from an external JSON file without the
    need to input them into the command line one by one.
  * Justification: This feature improves the product significantly because a user may need mass add applicants in a 
    short period of time, and this feature allows the user to do exactly that.
  * Highlights: This enhancement added a completely new way for users to add applicants that can be done in a 2-word
    command. At first, difficulties were faced when I was figuring out how to append the new `AdressBook` object to the 
    current one, as there are lots of abstractions that need to be considered. In the end, without making the command go 
    through the internal attributes of model, I created a feature `appendAddressBook()` that accepts the `AddressBook` 
    that is going to be appended. Some guard statements are also placed, as input files can be of lots of forms which 
    may not comply with the requirements given.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jasonCP14&breakdown=true)

* **Project management**:
  * Created and assigned issues in the issue tracker for team members
  * Reviewed and approved other team members' PR
  * Frequently committed and pushed updates to Github
  * Organize issues and user stories inside the Project board

* **Enhancements to existing features**: 
  * Refined the add single applicant feature (PR[#113](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/113))
  * What it does: Now, instead of showing the details of added applicant inside the message bar, they are shown in the
    right panel instead,
  * Justification: This will give a clearer view for users to see the applicant that they just entered, instead of
    having it all in 1 sentence.
  * Highlights: The hardest part of this was incorporating the new `updateViewedPersonList` into the `addCommand`

* **Enhancements to existing features**:
  * Changed the definition of `isSamePerson` to be of same `email` and `jobID` (PR[#156](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/156))

* **Enhancements to existing features**:
  * Added `university` field (PR[#99](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/99))

* **Documentation**:
  * User Guide:
    * Added documentation for `AddCommand` and `ImportCommand` feature (PR[#169](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/169))
    * Added documentation for all applicant fields (PR[#186](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/186))
    * Added table of contents (PR[#169](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/169))
    * Finalized the UG for v1.3 (PR[#186](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/186))
  * Developer Guide:
    * Added user stories (PR[#80](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/80))
    * Added use cases for `AddCommand` and `ImportCommand` feature (PR[#155](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/155))
    * Added sequence diagrams and manual testings for `ImportCommand` (PR[#249](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/249))
    * Added table of contents (PR[#249](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/249))

* **Tools**:
  * Used Intellij to edit all files
  * Used Github and Git for version control
  * Used Microsoft Teams for team communication and notes taking
  * Used PlantUML to create diagrams in DG
