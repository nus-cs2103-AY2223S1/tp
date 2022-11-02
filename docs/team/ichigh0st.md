---
layout: page
title: Xinbei's Project Portfolio Page
---

<div markdown="block" class="no-num">

### Project: NUScheduler

NUScheduler is a desktop app for **NUS students who have a large network of contacts to manage, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your contact management tasks done faster than traditional GUI apps.

With NUScheduler, you can store the contact details of the NUS community around you and manage your classes and events effectively.

#### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ichigh0st&breakdown=true)

* **New Feature**: Added the ability to view all `Profile`s listed. (Pull Requests [#69](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/69), [#92](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/92))
    * What it does: Allows the user to view a list of all profiles.
    * Justification: This feature allows the user to see all the profiles added in the case that they have previously applied a search filter over the list of profiles.
    * Highlights: This feature includes the automatic sorting of `Profile`s by alphabetical order so that the list is more organized and easy to navigate.
    * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: Added the ability to add `Profile`s to and remove `Profile`s from an `Event`. (Pull Requests [#118](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/118), [#127](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/127))
    * What it does: Allows the user to track an event's attendees.
    * Justification: This feature improves the user experience significantly as a user can link profiles to events and record down the attendees of an `event.
    * Highlights: This feature affects the way data is stored and greatly increases dependency between classes so it required an in-depth analysis of design alternatives. It was challenging to write unit tests because of the dependencies.
    * Credits: Co-authored with [Kee Seow Han](https://github.com/KSHan29)

* **Enhancements to existing features**: Added a data field for a telegram username to `Profile`s. (Pull Request [#70](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/70))
  * Justification: Many NUS students are frequent users of Telegram and many group chats for modules are also based in Telegram. This enhancement will cater to our target users better and allow them to contact their connections via Telegram. 
  * Credits: Regex for the Telegram user name is adapted from [UniGenda](https://github.com/AY2122S2-CS2103T-W09-1/tp/blob/master/src/main/java/seedu/address/model/person/Telegram.java)

* **Enhancements to existing features**: Updated the UI to create space for a list of `Event`s (Pull Request [#63](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/63))
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **Project management**:
    *

* **Documentation**:
    * User Guide:
        * Added documentation for the features `event -d`, `event -ap` and `event -dp` (Pull Requests [#48](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/48), [#117](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/117))
        * Fix minor grammatical and notation errors (Pull Request [#128](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/128))
    * Developer Guide:
        * Tweaked target user profile, added user stories and use cases for deleting a profile, adding an event and viewing upcoming events (Pull Request [#51](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/51))
        * Added implementation details of `event -ap` (Pull Request [#94](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/94))

* **Contributions to team-based tasks**:
    * Updated site-wide settings using Jekyll (Pull Request [#35](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/35))

* **Community**:
    * Reviewed 29 Pull Requests on [GitHub](https://github.com/AY2223S1-CS2103T-T17-3/tp/pulls?q=is%3Apr+commenter%3Aichigh0st+is%3Aclosed+-author%3Aichigh0st)
    * PRs reviewed (with non-trivial review comments): [#57](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/57), [#97](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/97), [#99](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/99)
    * Found 13 bugs in team [CS2103-F12-4](https://github.com/ichigh0st/ped)'s product during PE-D.

</div>
