---
layout: page
title: Elgin Lee's Project Portfolio Page
---

### Project: JeeqTracker

JeeqTracker is a desktop application created for **home-based business owners / resellers** that helps them manage the clients
that they interact with by keeping track of their **`BUY / SELL`** transactions, and **`REMARKS`** of clients.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added ability to `view` specific clients
    * What it does: allows the user to view the transactions and remarks of a specific client, which also filters the client list panel to
        left only that specific client.
    * Justification: This feature is essential for users to populate the transaction list panel and remark list panel according to the client that the user wants
  to see in details. 


* **New Feature**: Added ability to receive `help` within the application 
    * What it does: allows the user to have a detailed explanation of any commands within the application, or have a summary of all existing commands. This helps users who may
        be forgetful, and reduces the need to open up a separate browser to read the user guide.
    * Justification: This feature increases the convenience of users, and speeds up the process of recalling the usage of certain commands.


* **New Feature**: Added ability to `delete` a specific client, transaction, or remark
    * What it does: allows the user to delete a client, transaction, or remark of their choice.
    * Justification: Essential operation for the application to be useful, as users might add an entry wrongly. Providing a delete functionality will solve such problems.
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=ElginL&tabRepo=AY2223S1-CS2103T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
    * Managed release `v1.2` - `v1.3.trial` (2 releases) on Github


* **Enhancements to existing features**:
    * Updated the layout of the UI (Pull requests [#114](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/114),
        [#141](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/141),
        [#149](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/149),
        [#203](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/203),
        [#223](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/223))
    * Updated storage to include Transactions (Pull request [#179](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/179))
    * Updated `add` command to allow users to have more freedom in entering phone number, and email. Instead of blocking user input like `+65 9876 5432 (HOME)`,
        a warning message will be given instead (Pull request [#219](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/219))
    * Upgraded duplicate detection in `add` and `remark` command. Duplicate detection is upgraded to be case insensitive, and not affected by white spaces (Pull requests [#219](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/219), [#221](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/221))


* **Documentation**:
    * User Guide:
        * Added documentation for the `Introduction`, `Table of Contents`, `User Interface Overview` (Pull request [#253](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/253))
            * Image in `User Interface Overview` is created by `Joel`
        * Added `Prefix Table` (Pull request [#214](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/214))
        * Added documentation for commands `delete transaction`, `delete remark`, `edit transaction`, `edit remark`
    * Developer Guide:
        * `to be added soon`


* **Community**:
    * PRs reviewed (with non-trivial review comments): [#202](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/202), [#184](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/184), [#152](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/152), [#208](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/208#discussion_r1005513777)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S1-CS2103T-W15-2/tp/issues/122), [2](https://github.com/AY2223S1-CS2103T-W15-2/tp/issues/136), [3](https://github.com/AY2223S1-CS2103T-W15-2/tp/issues/117), [4](https://github.com/AY2223S1-CS2103T-W15-2/tp/issues/111), [5](https://github.com/AY2223S1-CS2103T-W15-2/tp/issues/140))
    * Contributed to forum discussions ([1](https://github.com/nus-cs2103-AY2223S1/forum/issues/328))
    

* **Team-based tasks contributions**
    * Updated `README.md` (Pull request [#206](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/206))
    * Updated landing page of project website (Pull request [#88](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/88))
    * Enabled assertions in `build.gradle` (Pull request [#180](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/180))

