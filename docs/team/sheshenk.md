---
layout: page
title: Shashank Acharya's Project Portfolio Page
---

<div markdown="block" class="no-num">

### Project: NUScheduler

NUScheduler is a desktop app for managing contacts, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added option flags to command parsers. (Pull Request [#57](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/57))
  * What it does: Adding a character flag (e.g. `-a`) after a common command (e.g. `profile`) specifies the action to be taken.
  * Justification: Using option flags makes commands less complicated (especially since NUScheduler is used for profiles and events) and more CLI-like.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3).

* **New Feature**: Implemented all event classes. (Pull Request [#63](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/63))
  * What it does: Event classes, including the `Event` model, containing `Title` and `Datetime` properties, as well as `UniqueEventList`, `JsonAdaptedEvent`, etc. that are used by the event commands.
  * Justification: The implementation of these classes is necessary in order to manage events.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3).

* **New Feature**: Added the ability to add `Profile`s and `Event`s. (Pull Requests [#57](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/57), [#85](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/85))
  * What it does: Allows the user to add new profiles and events into the application.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3).
  
* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sheshenk&breakdown=true)

* **Project management**:
  * Set up team organisation and repo.
  * Set up issue tracker.
  * Set up project website.
  * Created [team PR](https://github.com/nus-cs2103-AY2223S1/tp/pull/5) to upstream repo.

* **Enhancements to existing features**: Added tag shortcuts functionality. (Pull Request [#102](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/102))
  * What it does: Uses preset short-forms for common tags that an NUS student may use and auto-expands it when setting the tag.  
  * Justification: Improves the speed and efficiency of adding these common tags to an event or profile.

* **Enhancements to existing features**: Multiple UI updates. (Pull Request [#76](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/76), [#103](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/103), [#116](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/116))
  * What it does: Menu bar links to important NUS resources, light mode implementation with NUS colors, event duration labels.
  * Justification: Since the target users are NUS students, so the product UI should reflect this. 

* **Documentation**:
  * User Guide:
    * Updated Quick Start and Introduction sections.
    * Added Command Summary table.
    * Added valid date-time formats for event commands.
  * Developer Guide:
    * Added implementation details for add commands. (Pull Request [#84](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/84))

* **Community**:
  * Pull Requests reviewed: 11
  * Found 8 bugs in team [CS2103T-T14-1](https://github.com/sheshenk/ped)'s product during PE-D.

</div>
