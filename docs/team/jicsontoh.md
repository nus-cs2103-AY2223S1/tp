---
layout: page
title: Jicson Toh's Project Portfolio Page
 ---

### Project: JeeqTracQer

JeeqTracker is a desktop application created for **home-based business owners / resellers** that helps them manage the clients
that they interact with by keeping track of their **`BUY / SELL`** transactions, and **`REMARKS`** of clients.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added ability to `filter` all the transactions by all the clients
  * What it does: allows the user to view all `buy` or `sell` transactions made by all the clients.
  * Justification: This feature is useful for the user to look at the `buy` or `sell` transactions made by all the clients at one glance.


* **New Feature**: Added the `create` function which was previously used to create a point of contact. The command is now refactored to create a new remark for the specified client.
  * What it does: allows the user to create a new remark that is linked to the specified client
  * Justification: This feature is helpful in allowing users to add remark for a client.


* **New Feature**: Added ability to open the `user_guide` url
    * What it does: allows the user to copy the url of the user guide
    * Justification: This feature is essential for users to access the user guide to learn how to use the application effectively.
    

* **New Model**: Created `Transaction Model` which includes `Transaction`, `TransactionLog`, `Goods`, `Price`, `Quantity`, `BuyTransaction` and `SellTransaction`
  * What it does: allow the application to use the model to store the transactions by the clients.
  * Justification: This model is necessary for the creation of the transaction commands.
  

* **Code contributed**: [RepoSense link](
  https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jicsontoh&tabRepo=AY2223S1-CS2103T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements to existing features**:
  * Added data structure to store all remarks in client model (Pull request [#121](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/121))
  * Refactored `Company` to `Client` class for the entire project (Pull request [#159](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/159))
  * Added `Client Phone` and `Client Email` attributes to client (Pull request [#178](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/178))
  * Update the `help` command output messages (Pull request [#205](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/205))
  * Minor fix to the UI (Pull request [#216](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/216))
  * Updated `help` command to not change UI after using (Pull request [#217](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/217))
  * Fixed input for `sort` command to allow spaces between the arguments (Pull request [#217](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/217))
  * Updated error messages for `edit`, `sort`, `buy` and `sell` commands (Pull request [#217](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/217))


* **Documentation**:
  * User Guide:
    * Edited documentation `Create`, `Add`, `Help` `Find` commands (Pull requests [#69](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/69), 
    [#70](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/70), [#146](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/146))
    * Added documentation for the `Filter`, `Sort`, `Delete`, `User_guide`, `Remark` and `Edit` commands.
    Also edited and standardised the user guide for peer user guide review (Pull requests [#110](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/110),
    [#189](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/189), [#208](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/208))
    * Added documentation for the parameters of `sort` and `create remark` commands (Pull request [#273](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/273))
    * Added links to allow readers to easily return to the table of contents (Pull requests [#254](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/254),
    [#256](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/256), [#264](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/264))
  * Developer Guide:
    * Added documentation for the `filter` command implementation (Pull request [#192](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/192))
    * Created the activity diagram for the `filter` command (Pull request [#192](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/192))
    * Created the sequence diagram for the `filter` command (Pull request [#192](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/192))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [#111](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/111), [#202](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/202), 
  [#191](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/191), [#112](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/112), [#113](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/113)
  * Reported bugs and suggestions for other teams in the class (medium severity and above): [Issue 1](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/181),
    [Issue 2](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/167), [Issue 3](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/160), [Issue 4](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/154),
    [Issue 5](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/148), [Issue 6](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/141), [Issue 7](https://github.com/AY2223S1-CS2103T-W08-3/tp/issues/137))
