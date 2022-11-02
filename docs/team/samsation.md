---
layout: page
title: Samuel Koh's Project Portfolio Page
---

### Project: bobaBot

bobaBot is a desktop application for managing customers’ membership details. It is optimized for Command Line Interface (CLI) while retaining some benefits of the Graphical User Interface (GUI). If you are a cashier working at a bubble tea shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as compared to other GUI applications.

bobaBot contains a database of customers’ information and supports operations to add, update, delete and even find customers based on various inputs. Every entry in the database contains the customer's name, phone number, email address, birthday month, reward points and membership tags.

bobaBot is written in Java and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: `Increase` Command - Added the functionality for users to directly increase a Customer's Reward points.
  (Pull Requests [#138](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/138), [#205](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/205))
    * What it does: Allows the user to increase a Customer's Reward points without accessing the Edit Command. For example, the user choose to identify the Customer via Phone number
      or Email address to increase his/ her Reward points. The command will only increment the Reward points if the final value **DOES NOT** exceed the **MAXIMUM INTEGER VALUE (2147483647)**. If it is exceeded,
      an appropriate error message would be thrown.
    * Justification: This feature improves the product efficiency for the users (bubble tea shop cashiers), as this would be easily the most used Command during any transaction, along with the `Decrease` Command.
      Therefore, this functionality allows for quick and easy way to increment a Customer's Reward points without accessing the `Edit` Command, which is faster to enter into the CLI. Another added side benefit is that
      there would not be any accidental changes to other Customer fields since we are not directly calling the `Edit` Command.


* **New Feature**: `Decrease` Command - Added the functionality for users to directly decrease a Customer's Reward points.
  (Pull Requests [#138](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/138), [#205](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/205))
    * What it does: Similar to the above `Increase` Command, this allows the user to decrease a Customer's Reward points without accessing the `Edit` Command. For example, the user choose to identify the Customer via Phone number
      or Email address to decrease his/ her Reward points. The command will only decrement the Reward points if the final value **DOES NOT** become a **NEGATIVE VALUE**. If it becomes negative,
      an appropriate error message would be thrown.
    * Justification: This feature provides the same efficiency benefits as the `Increase` Command stated above.


* **New Feature**: `BirthdayMonth` Tag - Reflects whether it is currently a Customer's birthday month.
  (Pull Request [#146](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/146))
    * What it does: Automatically adds a `BDAY` tag to Customers whose `BirthdayMonth` is the current month (syncs with the local system's month). For example, if the Customer's birthday is in November, then
      from 1 November onwards, the `BDAY` tag would be automatically added to the Customer for the entire month. Once it becomes 1 December, this tag will then be automatically removed as it is synced with
      the local system.
    * Justification: This allows the users (bubble tea shop cashiers) to easily track who is eligible for any ongoing birthday promotions.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w09&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Samsation&tabRepo=AY2223S1-CS2103T-W09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
  * Refactored Person class to Customer class (Pull Request [#146](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/146))
  * Refactored Address class to Reward class (Pull Request [#76](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/76))
  * Created icon for bobaBot (Pull Request [#92](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/92))
  * Managed releases `v1.3.1` and `v1.3.2` (2 releases) on GitHub


* **Enhancement to existing features**: Improved the original `Edit` Command to allow users to identify Customers to edit via Phone number or Email address, instead of via Index.
  (Pull Requests [#68](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/68), [#76](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/76), [#82](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/82), [#205](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/205))
  * What it does: Allows users to identify Customers via Phone number or Email address, which are both unique identifiers for individuals, instead of using Index originally.
  * Justification: This feature increases the speed at which the user (bubble tea shop cashiers) can edit the Customer's fields. Originally, the user would have to scroll through the 
  entire list of Customers to find his/ her Index, then execute the `Edit` Command. With this improvement, this entire step is skipped, greatly improving the efficiency of this Command.
  We also thought of including Name as a unique identifier, but eventually decided against it since there is a high change of multiple same-named Customers appearing in the list, which does
  not help in achieving the desired efficiency.
  * Highlights: Customers' identification is now more flexible since we have 2 unique identifiers to aid in this process. This change would also later form the foundation of the `Increase` and
  `Decrease` Commands, as well as how other Commands deal with identifying unique Customers.


* **Enhancement to existing features**: Improved the original `Find` Command to allow users to find unique Customers via Phone number or Email address as well.
  (Pull Request [#80](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/80))
    * What it does: Allows users to find Customers via Phone number or Email address, which are both unique identifiers for individuals.
    * Justification: This feature ensures that only one unique Customer will be displayed after the `Find` Command has been executed, which is helpful when the user does not want to scroll
    through multiple Customers in the resultant list. 
    * Highlights: This is an alternative way to use the `Find` Command, the other being searching via keywords, which allows for greater flexibility for the users.


* **Enhancement to existing features**: Added appropriate colours for special Tags.
  (Pull Request [#146](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/146))
    * What it does: Updated GUI to display appropriate colours for different membership tiers and status e.g. `GOLD`, `BANNED`, `BDAY`


* **Documentation**:
  * User Guide:
    * Added documentation for the Commands `Edit`, `Increase`, `Decrease`
      (Pull Requests [#45](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/45), [#115](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/115), [#140](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/140))
    * Added table of contents
      (Pull Request [#165](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/165))
  * Developer Guide:
    * Added use cases
      (Pull Request [#42](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/42))
    * Added implementation details for the Commands `Edit`, `Increase`, `Decrease`
      (Pull Requests [#115](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/115), [#140](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/140/files))
    * Updated and added UML diagrams for `Edit` Command
      (Pull Request [#115](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/115))

* **Community**:
  * Reported [bugs](https://github.com/Samsation/ped/issues) for another team
  * PRs reviewed (with non-trivial comments): [#114](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/114), [#136](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/136)
  
