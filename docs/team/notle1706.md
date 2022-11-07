---
layout: page
title: Elton Chua's Project Portfolio Page
---

### Project: JeeqTracker

JeeqTracker is a desktop application created for **home-based business owners / resellers** that helps them manage the clients
that they interact with by keeping track of their **`BUY / SELL`** transactions, and **`REMARKS`** of clients.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `buy` and `sell` commands
  * What it does: allows the user to create `buy` and `sell` commands and link them to the specific client to keep track of their transactions
    with them.
  * Justification: This feature is essential for users to input transactions so that our application can keep track of them.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=notle1706&tabRepo=AY2223S1-CS2103T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed release `v1.3 PE` (1 release) on Github


* **Enhancements to existing features**:
  * Refactored multiple classes to transition from AB3 to JeeqTracker
    * Person to Company (Pull requests [#97](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/97), [#109](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/109), 
    [#120](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/120))
    * AddressBook to JeeqTracker (Pull request [#112](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/112))
    * POC to Company (Pull request [#173](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/173), [#177](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/177),
    [#183](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/183))
    * Company to Remarks (Pull request [#200](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/200))

  * Updated storage to include POCs which has been changed to remarks (Pull request [#138](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/138))
  * Updated `edit` command to allow users to have more freedom in entering phone number, and email. Instead of blocking user input like `+65 9876 5432 (HOME)`,
    a warning message will be given instead (Pull request [#222](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/222))
  * Upgraded `buy` and `sell` commands to allow easy continuous inputs. (Pull request [#151](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/151))
  * Removed `tags` from `Remark` as it was unnecessary to have tags in remarks. (Pull request [#278](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/278))
* **Documentation**:
  * Developer Guide:
    * Updated Storage component (Pull request [#300](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/300))
    * Updated buy/sell transactions feature implementation (Pull request [#308](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/308))
    * Added buy/sell transactions in `Appendix: Manual testing section` (Pull request [#308](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/308))
    * Added use cases for buy/sell transactions (Pull request [#308](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/308))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [#184](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/184), [#153](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/153), [#136](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/136), [#111](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/111)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues/156), [2](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues/146), [3](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues/114), [4](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues/109), [5](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues/106))
  * Contributed to a forum discussion. ([#64](https://github.com/nus-cs2103-AY2223S1/forum/issues/64))
