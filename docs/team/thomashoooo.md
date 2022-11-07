---
layout: page
title: Thomas Ho's Project Portfolio Page
---

### Project: MyInsuRec

*MyInsuRec* is a desktop app for financial advisors to manage and organize their clients and meetings, helping
financial advisors stay connected with their clients. *MyInsuRec* is best suited for the quick typist, as it is
optimized for use via a Command Line Interface (CLI), with all the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to view a client's details
  * Justification: A new command to view client's details is added. New classes and test cases also have to be implemented to ensure that the command doesn't fail. It also has to return the correct client's details when the user enters a filter to the client list.

* **New Feature**: Added the ability to view a meeting's details
  * Justification: A new command to view meeting's details is added. New classes and test cases also have to be implemented to ensure that the command doesn't fail. It also has to return the correct meeting's details when the user enters a filter to the meeting list.

* **Enhancements to existing features**: Added the ability for users to tag a product to a client
  * Justification: This implementation is challenging as it requires a new model of `Product` to be added and new testcases have to be added as well.

* **Enhancements to existing features**: Added the ability for users to filter clients based on the products they bought

* **Enhancements to existing features**: Added the ability for user to specify the ending time for a meeting
  * Justification: Due to the newly added ending time for a meeting, it requires more checks on the time period for meetings to see whether those meetings clash and it requires more changes to other commands that involve meetings as well.

* **Project management**:
  * Assigned tasks and bugs to group members to ensure that project's objectives are met
  * Performed smoke testing before each release and reported bugs when necessary

* **Tests**:
  * Wrote extensive test cases for `addClient` command, `addMeeting` command, `viewClient` command, `AddClientCommandParser`, `NoConflictMeetingList` and `Meeting`

* **Documentation**:
  * User Guide:
    * Added documentation for `listClient`, `addProduct`, `viewClient`, `viewMeeting`: [#196](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/196), [#284](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/284)
    * Added hyperlinks to improve navigability: [#203](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/203)
  * Developer Guide:
    * Added documentation for `viewMeeting`, `viewClient` and `addProduct`: [#330](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/330)

* **Community**:
  * Contributed to forum discussions : [#121](https://github.com/nus-cs2103-AY2223S1/forum/issues/121#issuecomment-1235101343), [#42](https://github.com/nus-cs2103-AY2223S1/forum/issues/42#issuecomment-1221382154)
  * Reported a bug on the official CS2103T website [#143](https://github.com/se-edu/addressbook-level3/issues/143)
  * PRs reviewed within the team (with non-trivial review comments): [#304](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/304), [#178](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/178), [#183](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/183), [#270](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/270),

* **Team tasks**:
  * Set up the Github team organisation and repository
  * Integrated CodeDev into the team repo
  * Set up Github pages for Jekyll
  * Maintain the issue tracker

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ThomasHoooo&tabRepo=AY2223S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
