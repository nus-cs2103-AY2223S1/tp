---
layout: page
title: Kee Seow Han's Project Portfolio Page
---

<div markdown="block" class="no-num">

### Project: NUScheduler

NUScheduler is a desktop app for managing contacts, optimised for use via a Command Line Interface (CLI) while
still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your
contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: `profile -d`
  * What it does:
    Allow users to delete a profile from NUScheduler by specifying the index of profile to delete from the displayed list
  * Justification:
    This is needed for users who wish to delete profiles that are no longer relevant so that the displayed profile list is free from clutter.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: `event -v`
  * What it does:
    Allow users to view all events in NUScheduler. 
  * Justification:
    This brings convenience to users who wish to have an overview of all events they have added.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: `event -u`
  * What it does:
    Allows users to view all upcoming events which are events that starts on the next specified number of days.
  * Justification:
    For users with a long list of events, this feature enables them to easily check what events they have in the next few days.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kshan29&breakdown=true)

* **Project management**:
  * To be added soon

* **Enhancements to existing features**:
  * Improved the handling of error inputs in delete commands to specify the cause of wrong command formats.
  * Updated the handling of `Email` inputs to accept only NUS email domains.
  * Changed the handling of duplicate profiles from comparing `Name` to comparing `Email`, `Phone` and `Telegram` instead.

* **Documentation**:
  * User Guide:
    * Added documentation for `event -u` and `event -v`
    * Added use case for  `profile -d`
  * Developer Guide:
    * Updated NFR and Glossary to cater to our project
    * Updated architecture diagrams to reflect our project design
    * Added implementation details for delete commands.

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [#83](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/83)


</div>
