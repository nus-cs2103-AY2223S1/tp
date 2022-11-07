---
layout: page
title: Yu-chuan Liao's Project Portfolio Page
---

### Project: FABook

FABook is your **dependable assistant** who **reminds you of meetings** and **consolidates crucial information** like financial plans and client information right at your fingertips! You can now focus on giving your full attention to your clients without having to worry about things slipping your mind.

**FABook is optimized for a financial adviser to use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: `sync` feature to remove past meetings: PR [#101](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/101)
  * What it does: Removes all meetings with meeting the before the current time.
  * Justification: To reduce clutter and allow FAs to focus on future meetings.
  * Credits: N/A 

* **New Feature**: `add meeting` feature: PRs [#120](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/120) [#131](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/131)
  * What it does: Assigns one or more new meetingtimes to a client.
  * Supports undo/redo.
  * Justification: To let FAs manage their meetings when new ones are scheduled.
  * Credits: Reused idea to use `editedPerson` from AB3 tutorial for `RemarkCommand`. 
  
* **New Feature**: `delete meeting` feature: PRs [#123](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/123) [#131](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/131)
  * What it does: Removes a specific meeting from a client.
  * Supports undo/redo.
  * Justification: To reflect a meeting being canceled or to correct a mistake input.
  * Credits: Reused idea to use `EditedPerson` from AB3 tutorial for `RemarkCommand`. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=isanidiot&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=isanidiot&tabRepo=AY2223S1-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Update public document for team deliverables
  * [PRs reviewed by me](https://github.com/AY2223S1-CS2103T-T10-2/tp/pulls?q=commenter%3Aisanidiot).
  * [Issues created by me](https://github.com/AY2223S1-CS2103T-T10-2/tp/issues?q=is%3Aissue+author%3Aisanidiot).

* **Enhancements to existing features**:
  * New filter client by tag command. PR [#137](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/137)
  * New find client by phone command. PR [#41](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/41)
  * Changed remark to description. PR [#84](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/84)
    * Changed update and create commands to allow description to be updated along with other fields. PR [#85](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/85)
    * Implemented undo and redo features for DescriptionCommand. PR [#127](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/127)
  * Enhanced create command to make only name and phone number compulsory. PR [#89](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/89)
    * Justification: It's often difficult for an FA to get all the information of the client at first.
  * Enhanced FindName command to match partial names. PR [#212](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/212)
    * Justification: Allows using the hypocoristics that the FAs might be more familiar with. E.g. Client is store as Alexander, but FA calls him Alex.
  * Updated isSamePersonCheck to check for name and phone number only. PR [#84](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/84)
    * Justification: email is no longer compulsory for client creation.
  * Assisted with debugging of Eugenes addition of Networth field. PR [#36](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/36)
  * Removed reference to AB3 in savefiles. PR [#193](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/193)
  * Contributed to the design of undo feature.

* **Documentation**:
  * User Guide: PRs [#24](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/24) [#31](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/31) [#103](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/103) [#135](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/135) [#138](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/138) [#193](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/193)
  
    * Rebranded AB3 to FABook, along with new input design choices.
    * Updated formats of create, update, find, and others.
    * Added guides for sync, meeting, deletemeeting, and find by tag commands.
    * Added content to command summary.
    * Updated FAQ for save files.
    
  * Developer Guide: PRs [#31](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/31) [#79](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/79) [#240](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/240) [#249](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/249) [#256](https://github.com/AY2223S1-CS2103T-T10-2/tp/pull/256)
  
    * Appendix for manual testing.
    * Added use case for description command.
    * Removed reference to AB3.
    * Contributed to design and alternatives for sync command and meetingtime. All credits to Shaune for the diagrams.
    
* **Community**:
  * Reported bugs and suggestions for other teams in the class: [PE-D Issues Link](https://github.com/isanidiot/ped/issues)
