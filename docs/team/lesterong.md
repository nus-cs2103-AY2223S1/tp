---
layout: page
title: Lester Ong's Project Portfolio Page
---

<div markdown="block" class="no-num">

### Project: NUScheduler

NUScheduler is a desktop app for managing contacts, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit `Profile`s and `Event`s. (Pull Request [#64](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/64), [#89](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/89))
  * What it does: Allows the user to edit details of existing profiles and events in the application.
  * Justification: This feature allows the user to update details of their profiles and events if they have made a mistake when adding it.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3).

* **New Feature**: Added the ability to find `Event`s by keywords or dates. (Pull Request [#115](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/115))
  * What it does: Allows the user to find existing events by providing a set of keywords and dates.
  * Justification: This feature allows the user to easily find the details of the events that they are concerned about.
  * Credits: This feature is adapted from code in [AddressBook Level-3](https://github.com/se-edu/addressbook-level3).

* **New Feature**: Added a session-based command history feature that allows the user to navigate to the previous command using the up and down arrow keys. (Pull Request [#104](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/104))
  * What it does: Allows the user to navigate between the previous successful commands.
  * Justification: This feature allows the user to reduce the amount of typing if the user is doing successive, similar commands, without the need to type the whole command again.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lesterong&breakdown=true)

* **Project management**:
    * Managed 3 releases `v1.2`, `v1.2.1` and `v1.3.1` and on [GitHub](https://github.com/AY2223S1-CS2103T-T17-3/tp/releases).

* **Enhancements to existing feature**: Refactored the `ProfileCommandParser` class to better parse user input. (Pull Request [#64](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/64))
    * What it does: Improves the handling of user input using regular expressions, and to provide more descriptive error messages for incorrect command formats.
    * Credits: Built upon the initial version of the `ProfileCommandParser` class by [Shashank Acharya](https://github.com/sheshenk).

* **Enhancements to existing feature**: Build upon the help window. (Pull Request [#78](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/78), [#124](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/124))
  * What it does: Added a command summary into the help window, and a hyperlink to the user guide.
  * Justification: This feature provides quick and convenient access to the command usage without accessing the full user guide. If the user requires more details, a hyperlink to the full user guide is provided.
  * Credits: Co-authored with [Shen Xinbei](https://github.com/ichigh0st).

* **Enhancements to existing feature**: Refactored the `DateTime` class to better handle the various date and time formats. (Pull Request [#100](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/100))
  * What it does: Improves the error handling of dates and times using regular expressions, and improve code quality.
  * Credits: Built upon the initial version of the `DateTime` class by [Shashank Acharya](https://github.com/sheshenk).

* **Documentation**:
    * User Guide:
        * Added documentation for the profile commands, including `profile -a`, `profile -d` and `profile -f`. (Pull Request [#38](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/38), [#62](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/62))
        * Added documentation for the event commands, including `event -e` and `event -f`. (Pull Request [#62](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/62), [#117](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/117))
        * Added documentation for session-based advanced features, including session-based command history and quick links. (Pull Request [#117](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/117))
        * Added section numbers using CSS and reorganise sections. (Pull Request [#117](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/117))
        * Fixed formatting and minor typographical errors. (Pull Request [#128](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/128))
    * Developer Guide:
        * Added implementation details for edit commands. (Pull Request [#83](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/83))

* **Reviewing and mentoring contributions**:
  * Reviewed 37 Pull Requests on [GitHub](https://github.com/AY2223S1-CS2103T-T17-3/tp/pulls?q=is%3Apr+commenter%3Alesterong+is%3Aclosed+-author%3Alesterong).
  * Helped to ensure issues and milestones are on schedule.
  * Pull Requests reviewed with non-trivial comments: [#57](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/57), [#71](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/71), [#119](https://github.com/AY2223S1-CS2103T-T17-3/tp/pull/119).

* **Community**:
    * Contributed to the [CS2103-AY2223S1 forums](https://github.com/nus-cs2103-AY2223S1/forum): Issue [#62](https://github.com/nus-cs2103-AY2223S1/forum/issues/62), [#288](https://github.com/nus-cs2103-AY2223S1/forum/issues/288).
    * Found 10 bugs in team [CS2103-W14-1](https://github.com/lesterong/ped)'s product during PE-D.

</div>
