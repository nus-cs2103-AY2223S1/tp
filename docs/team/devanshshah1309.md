---
layout: page
title: Devansh Shah's Project Portfolio Page
---

### Project: TruthTable

TruthTable is a task-management software specially targeted towards tech-savvy university students leading teams in
software engineering modules to build projects. It helps track the team's progress and delegate tasks effectively.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=DevanshShah1309&tabRepo=AY2223S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **New Feature**: Added functionality to add, delete and list tasks. ([PR #23](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/23), [PR #46](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/46))
    * What it does: Allows the user to create, delete and view the tasks for a given team. It also stores the tasks automatically in a local file.
    * Justification: This feature is very important to the user as it empowers him/her to better manage the team's tasks using our application.
    * Highlights: Implementing this feature required an in-depth understanding of the execution pipeline for commands and making the right design decisions for associating tasks with a team. It also required knowledge on how the `Storage` component works.
* **UI Changes**: Revamped the existing UI to make it more user-friendly and allow the user to view all relevant information on the same screen, at a glance. ([PR #55](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/55), [PR #95](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/95), [PR #110](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/110))
  * What it does: Splits the screen into different panels that allow the user to view teams, tasks, members, and contacts. The color scheme and font also makes it more inviting for users to use the application.
  * Justification: Since our application focuses on productivity in managing tasks for teams, it was important for us to be able to show all the necessary data while making sure the screen does not look too cluttered. The new UI makes this possible.
  * Highlights: Implementing this UI change required a detailed knowledge of `JavaFX` and `CSS`, and how the UI updates when a command is executed.
* **New Feature**: Allowing users to toggle between light theme and dark theme ([PR #55](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/55)).
  * What it does: Gives users the option to change the application's theme.
  * Justification: We understand that different users have different preferences, and we wanted our users to be able to choose between a light theme and dark theme depending on their preference.
  * Highlights: Implementing this required a thorough understanding of how `CSS` styling can be modified on-the-fly and a good design sense.
* **Enhancements to existing features**:
    * Fixed bugs related to deleting of members from TruthTable ([PR #111](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/111))
    * Refactored the application to use `TruthTable` instead of `AddressBook` ([PR #179](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/179))
    * Improved exception handling and made the error messages shown to the user more helpful and easy to understand ([PR #192](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/192))
* **Project management**:
    * Managed the release of `v1.1`, `v1.3(final)`, `v1.3.1`, and `v1.3,2` on GitHub.
    * Assigned labels to all issues for v1.4 on GitHub to make task management and tracking easier.
* **Documentation**:
    * User Guide:
        * Added explanations about the `add task`, `delete task`, `list tasks` and `theme` commands ([PR #23](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/23), [PR #55](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/55)).
        * Reviewed the main changes to the User Guide and provided meaningful suggestions and improvements. 
    * Developer Guide ([PR #83](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/83), [PR #205](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/205)):
        * Added the introduction of the project along with the Acknowledgement section.
        * Added an explanation regarding how the `add task` command is implemented.
        * Updated the UI Class Diagram to indicate the different UI components and how they interact with one another.
        * Added the Glossary section and updated the Non-Functional Requirements.
        * Updated other UML diagrams based on changes made to the model and logic components, and improved language in several places.
* **Testing**
  * Wrote unit tests for features that I implemented ([PR #30](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/30))
  * Manual testing and reporting of bugs encountered in the application, as well as UG and DG ([Issues raised](https://github.com/AY2223S1-CS2103T-W13-4/tp/issues?q=is%3Aissue+author%3ADevanshshah1309+is%3Aclosed))
* **Reviewing and Mentoring Contributions**:
  * [Suggestions to User Guide](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/175)
  * [Suggestions to change in format of commands](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/75)
  * Other PRs reviewed by me can be found [here](https://github.com/AY2223S1-CS2103T-W13-4/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed)
* **Contributions Beyond Project Team**
  * Reported 14 bugs and suggestions for other teams in the class during the PE Dry Run: The bugs reported can be found [here](https://github.com/Devanshshah1309/ped/issues).
