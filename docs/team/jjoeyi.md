---
layout: page title: Low Joe Yi's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a desktop address book application designed for National University of Singapore (NUS) Computer Science
Undergraduates to keep track of their University social circle which includes peers, Teaching Assistants and Professors.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10
kLoC.

Given below are my contributions to the project.

* **Fast Template Feature**: Added the ability to get a template command to add a professor / teaching assistant /
  student into the address book.

    * What it does: allows the user to get a correct template command with all the fields provided for a given person.

    * Justification: This feature improves the product significantly because a new user can quickly use our product
      without having to remember all the fields for each person, easily add a new Person and make less errors. For
      experienced users, they may use this feature as a shortcut to access all the fields of a Person quickly without
      having to type them out, so that they can add a Person quicker.

    * How to use: Simply use the command word `tt` and then the command for the person you wish to add. I.e. all valid
      commands are: `tt prof`, `tt ta`, `tt student`, for getting a template for adding a professor, teaching assistant
      and student respectively

    * Highlights: The templates provided can be easily updated for any potential new fields added in the future, so it
      is future proof.


* **New Field**: Added a `Location` field to all persons.

    * What it does: allows the user to add a location to a person to add into the address book. This field is optional,
      and its default value is `NUS` since SoConnect is meant for NUS students.

    * Justification: This feature improves the product because a user can add useful information about their contacts'
      location. E.g. A common meeting place, a classroom, lecture hall etc.

    * How to use it: Simply use the field `l/` (lowercase L) when adding a person.

    * Highlights: The location provided is shown on our GUI.


* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=%20AY2223S1-CS2103T-W08-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JJoeYi&tabRepo=AY2223S1-CS2103T-W08-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:

    * Set up the project repository
    * Set up the project website
    * Managed releases `v1.3` - `v1.4` on GitHub


* **Enhancements to existing features**:

    * Updated the GUI to fit Location information (Pull
      request [\#37](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/37))

    * Wrote additional tests for existing features to increase coverage (Pull
      request [\#102](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/102))


* **Documentation**:

    * User Guide:

        * Added documentation for the feature `Fast Template`

        * Added the `Command Summary` Section and `FAQ` Section and filled them with appropriate
          information. [\#103](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/103)

    * Developer Guide:

        * Added implementation details of the `Fast Template` feature, including a detailed sequence diagram.
        * Added Use cases and User Stories for `location` field and `Fast Template`
          feature [\#103](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/103)


* **Community**:

    * Reviewed over 19 PRs

    * PRs reviewed (with non-trivial review comments): [\#111](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/111)
      , [\#106](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/106)
      , [\#54](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/54)
      , [\#48](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/48)
      , [\#39](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/39)

    * Contributed to discussions (
      examples: [1](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/37#discussion_r986722158)
      , [2](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/106#discussion_r1005330464)
      , [3](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/39#discussion_r986739463)
      , [4](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/37#discussion_r986723329))

    * Reported bugs and suggestions for other teams in the class (
      examples: [1](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/111)
      , [2](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/54#discussion_r996404612)
      , [3](https://github.com/AY2223S1-CS2103T-W08-3/tp/pull/39#discussion_r986739463))
