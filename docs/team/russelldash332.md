---
layout: page
title: Nicholas Russell Saerang's Project Portfolio Page
---

### Project: JerylFypManager

JerylFypManager is a desktop application catered to professors to manage and track the progress for students’ final year project (FYP), as well as serving as a platform for professors to provide feedback on their students’ progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC. This project is based on AddressBook - Level 3.

Given below are my contributions to the project.

* **Enhancements to existing features**:
    * [Redefine equality on `Student`](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/134).
      * Rationale: Checking whether a student is a duplicate of another one must be determined on their student ID instead of name since there can be multiple individuals with the exact same name. The previous implementation checks on their name instead of their student ID.
    * [Support friendlier syntaxes](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/133).
      * Rationale: Adding a student and adding a deadline has an overlapping command word `add`, likewise for deleting. As such, the previous implementation changes the command word to accept strictly `add-s` and `add-d`. However, it would be better to accept any spaces between `add` and the flags, `-s` or `-d`, as well as providing a default choice if the flag is not specified.
    * [Extend `HelpCommand` to handle various commands](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/133).
      * Rationale: With more commands being added, it would be harder to maintain the help command since another new class must be added. Instead, I modified `HelpCommandParser` to create different `HelpCommand` instances. This approach will keep `HelpCommand` as its own class but with extended functionalities that do not need to increase the number of classes needed. 
    * [Enhance deadline parser to accept multiple datetime formats](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/131).
      * Rationale: Sometimes, the user might have different datetime formats as their preferred datetime format and might keep using this format instead of the specified one. Therefore, an adaptation is made such that multiple datetime formats supplied by the user will still mean the same thing when it comes to the parsing phase.
    * [Improve code quality on `EditCommand`](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/141).
      * Rationale: Used the Facade design pattern such that `EditCommandParser` does not access `EditCommand.EditStudentDescriptor` directly but rather use what has been provided in the `Model` interface.
    * Major UI changes, such as [the splitting of the FYP list into uncompleted and completed list](https://github.com/AY2223S1-CS2103-F09-1/tp/pull/130).

* **New Feature**:
  * Add student command - A feature to add a student to FypManager along with the particular details.
    * Rationale: Professors as users can add a student along with his/her FYP details.
    * Highlights: This command is a foundation of what constitutes a `Student`: student name, project name, student ID, etc.

* **Code contributed**: I personally contributed around 3 KLoC to FypManager, the RepoSense link can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=RussellDash332).

* **Project management**:
  * Created team organization, setup team repository, CodeCov, and GitHub Actions workflow.
  * Oversaw overall integration of features, especially on lint checks.
  * Renamed all packages to match FypManager, i.e. `seedu.address` to `jeryl.fyp`.
  * Managed the release of [`v1.2`](https://github.com/AY2223S1-CS2103-F09-1/tp/releases/tag/v1.2), [`v1.3`](https://github.com/AY2223S1-CS2103-F09-1/tp/releases/tag/v1.3), [`v1.3.1`](https://github.com/AY2223S1-CS2103-F09-1/tp/releases/tag/v1.3.1), [`v1.3.2`](https://github.com/AY2223S1-CS2103-F09-1/tp/releases/tag/v1.3.2), and [`v1.4`](https://github.com/AY2223S1-CS2103-F09-1/tp/releases/tag/v1.4) on GitHub by doing JAR release test.
  * Approved PRs and merging them.
  * Set up milestones on GitHub (v1.2, v1.2b, v1.3, v1.3b, v1.4, v1.4b) along with their deadlines.
  * Set up preliminary meeting minutes, such as post PE-D pre-triaging report.
  * Initiated Zoom meetings for team meeting, hosted them, and conduct debug sessions when needed.
  * Contributed to more than 40 PR review comments as shown on [this dashboard](https://nus-cs2103-ay2223s1.github.io/dashboards/contents/tp-comments.html).

* **Documentation**:
    * User Guide:
      * Added introduction, GUI overview and glossary.
      * Refactor numberings on the Features section.
      * Added details on [adding a students FYP](https://ay2223s1-cs2103-f09-1.github.io/tp/UserGuide.html#321-adding-students-fyp-add--s).
      * Added details on [sorting the FYP list](https://ay2223s1-cs2103-f09-1.github.io/tp/UserGuide.html#313-sorting-the-fyp-list-sort).

    * Developer Guide:
      * [Synced glossary with User Guide](https://github.com/AY2223S1-CS2103-F09-1/tp/commit/a4205f80642c8026ae096663bb12ce10e6412aa2).
      * Added Use Case UC01 - Adding FYP.
      * [Resolve issues in user stories](https://github.com/AY2223S1-CS2103-F09-1/tp/commit/2341e040c37332bdf712cf356355e815bb80c380).
      * [Updated class diagrams for UI](https://github.com/AY2223S1-CS2103-F09-1/tp/commit/9a0c7d2d6051875864bad26b71cdc1e70aa92735).
      * [Added AddStudentCommand implementation](https://github.com/AY2223S1-CS2103-F09-1/tp/commit/c52f5e7747925713795d66589753eadc497676c2).

* **Community**:
  * Reported significant number of bugs in other team's software product during the practical exam dry run as shown [here](https://github.com/RussellDash332/ped/issues).
  * Authored some helpful posts to help peers, such as [how to fix a non-deterministic CodeCov issue](https://github.com/nus-cs2103-AY2223S1/forum/issues/315).

* **Tools**:
  * Used SceneBuilder extensively to refactor the UI.
