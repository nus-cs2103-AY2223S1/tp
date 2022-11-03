---
layout: page
title: Meng Qiaoran's Project Portfolio Page
---

### Project: Duke The Market

Duke The Market is a desktop application used for managing customer contacts and events in department stores.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Meng%20Qiaoran&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Qiaoran-M&tabRepo=AY2223S1-CS2103-F09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Enhancements implemented**:
    * **New Feature**: Added gender field to a person
        * What it does: 
          1. Allows the user to add a person with gender field to contact list
          2. Allows the user to edit the gender of a person after adding the person to contact list.
        * Justification: Gender is a common field in personal profile, and it can be used in demographic
      analysis for marketing event attendees (related command `makeStats` is implemented by another team member).
      
    * **New Feature**: Added the feature to tag persons to an event
       * What it does: 
           1. Allows the user to tag 1 or more persons in the contact list to an event in the event list by `tagEvent` command. 
           2. Allows the user to untag from an event in the event list 1 or more persons tagged to the event previously by `untagEvent` command.
      * Justification: Tagging persons to event allows the user to easily keep track of all attendees in the contact list for a marketing event in the event list.
      * Highlights: This feature requires storing reference of `Person` to an `Event` in storage,
      to minimize duplications in storage, a new field `Uid` is added to `Person` object to identify unique person.
      This feature also requires immediate GUI display update of event attendees after tagging and editing related commands.

  * **New Feature**: Added the feature to create mailing list for an event
      * What it does: Allows the user to create mailing list containing names and emails of all event attendees for an
    event in the event list, the mailing list is in `CSV` format.
      * Justification: This feature helps the user to collect event attendee emails and send out post-event feedback surveys and notifications easily.
      * Highlights: This feature requires writing and storing to `CSV` file, which is an unfamiliar storage file format (data storage uses `JSON` file format in this application).

  * **Testing**: Added unit tests for gender field, added gender related unit tests for add and edit person feature.
  * **Refactoring**: Refactored the original `delete` command to `deletePerson` to distinguish it from the `deleteEvent` command.
* **Documentation**:
    * Contributions to the User Guide:
        * Added documentation for the `tagEvent` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#tag-persons-to-an-event--tagevent)),
      `untagEvent` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#untag-persons-from-an-event--untagevent)),
      and `mailEvent` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#create-mailing-list-for-an-event--mailevent)) features.
        * Modified `addPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#adding-a-contact-addperson))
      and `editPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#editing-a-contact--editperson)) feature slightly to include new gender field.
    * Contributions to the Developer Guide:
        * Added implementation details of gender field in `addPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#add-gender))
      and `editPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#edit-gender)) feature.

* **Contributions to team-based tasks**:
    * Did smoke test for product releases v1.2 and v1.3 ([Link](https://github.com/AY2223S1-CS2103-F09-2/tp/releases))
    * Released JAR file of v1.2.1

* **Community**:
    * Review/Mentoring contributions:
        * PRs reviewed (with non-trivial review comments): [\#55](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/55), [\#113](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/113),
      [\#206](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/206), [\#207](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/207).
        * Reported bugs and suggestions for other teams in the class: (examples: [\#144](https://github.com/AY2223S1-CS2103T-W10-3/tp/issues/144), [\#153](https://github.com/AY2223S1-CS2103T-W10-3/tp/issues/153),
      [\#172](https://github.com/AY2223S1-CS2103T-W10-3/tp/issues/172), [\#178](https://github.com/AY2223S1-CS2103T-W10-3/tp/issues/178))
        
