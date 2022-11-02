---
layout: page
title: Kee Seow Han's Project Portfolio Page
---

<div markdown="block" class="no-num">

### Project: NUScheduler

NUScheduler is a desktop app for managing contacts, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: `profile -d` (Pull Request [#66](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/66))
  * What it does:
    Allow users to delete a profile from NUScheduler by specifying the index of profile to delete from the displayed list
  * Justification:
    This is needed for users who wish to delete profiles that are no longer relevant so that the displayed profile list is free from clutter.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: `event -v` (Pull Request [#90](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/90))
  * What it does:
    Allow users to view all events in NUScheduler. 
  * Justification:
    This brings convenience to users who wish to have an overview of all events they have added.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: `event -u`(Pull Request [#93](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/93))
  * What it does:
    Allows users to view all upcoming events which are events that starts on the next specified number of days.
  * Justification:
    For users with a long list of events, this feature enables them to easily check what events they have in the next few days.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kshan29&breakdown=true)

* **Enhancements to existing features**: Updated the handling of `Email` inputs to accept only NUS email domains. (Pull Request [#67](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/67))
  * What it does: Changed the regex for email domain to accept only specific NUS emails.
  * Justification: Our application is catered for NUS students hence only profiles with NUS emails are valid.

* **Enhancements to existing features**: Changed the handling of duplicate profiles from comparing `Name` to comparing `Email`, `Phone` and `Telegram` instead. (Pull Request [#75](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/75))
  * What it does: Allow `Profile`s to have the same `Name` but not that same `Email`, `Phone` or `Telegram`.
  * Justification: Different students might have the same `Name` but should not share the same contact information.

* **Documentation**:
  * User Guide:
    * Added documentation for `event -v` and `event -u` (Pull Request [#54](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/54), [#55](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/55))
    * Added use case for  `profile -d` (Pull Request [#59](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/59))
  * Developer Guide:
    * Updated NFR and Glossary to cater to our project (Pull Request [#56](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/56))
    * Updated architecture diagrams to reflect our project design (Pull Request [#99](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/99), [#188](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/188))
    * Added implementation details for delete commands. (Pull Request [#95](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/95))

* **Community**:
  * Pull Requests reviewed: 11 ([GitHub](https://github.com/AY2223S1-CS2103T-T17-3/tp/pulls?q=is%3Apr+commenter%3AKSHan29+is%3Aclosed+-author%3AKSHan29))
  * Pull Request reviewed with non-trivial comments: [#83](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/83)
  * Found 8 bugs in team [CS2103T-W15-4](https://github.com/KSHan29/ped/issues)'s product during PE-D.
  
</div>
