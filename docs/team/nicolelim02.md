---
layout: page
title: Nicole's Project Portfolio Page
---

### Project: NutriGoals

NutriGoals is a desktop app that tracks a user’s diet and calorie consumption. By offering users recommendations on exercise and healthier eating selections, NutriGoals also aids in the adoption of a healthy lifestyle. 

Given below are my contributions to the project.

* **New Feature**: Added the ability for the user to view the profile created (Pull request [\#65](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/65)).
  * What it does: Allows the user to view the profile created.
  * Justification: Every application that allows the user to set up a profile should allow the user to view the information keyed in previously.
  * Highlights: This allows the user to check if the current information stored is correct.

* **New Feature**: Added the ability to calculate the user's BMI (Pull request [\#65](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/65)).
  * What it does: Calculates the user's BMI immediately after setting up a profile.
  * Justification: This application aims to encourage healthy eating habits. The BMI is a useful indicator of the user's health status.
  * Highlights: This automatically calculates the user's BMI when the user sets up a profile or updates existing information.

* **New Feature**: Added the ability to suggest a recommended calorie intake for the user (Pull requests [\#67](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/67) and [\#107](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/107)). 
  * What it does: Suggests an estimated recommended daily calorie intake so that the user can reach his/her ideal weight.
  * Justification: The user can set a target calorie intake, but the user may not be aware of the amount of calories the user should be consuming to reach his/her goal. 
  * Highlights: This allows the user to get an estimate of the amount of calories to consume in a day and complements the `target` command implemented.

* **Code contributed**: 
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nicolelim02&breakdown=true)

* **Project management**: 
  * Refactored [AB3's](https://github.com/nus-cs2103-AY2223S1/tp) codebase (Pull request [\#36](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/36)).
  * Managed the release of `v1.3.1` on [GitHub](https://github.com/AY2223S1-CS2103T-T17-2/tp/releases/tag/v1.3.1).

* **Enhancements to existing feature**: Modify the design of the Help Window (Pull request [\#90](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/90)).
  * What is does: Includes information regarding the commands usage without having to open the user guide. 
  * Justification: The help window is meant to provide fast and easy access to the commands usages. Since the application is meant to be used without an internet connection, the help window should display basic information regarding the app's usage.
  * Highlights: Replaced the `copyButton` in the help window with a hyperlink that redirects the user to the user guide.

* **Enhancements to existing feature**: Wrote additional test cases for existing features.
  * What it does: Tests the modified functionalities.
  * Justification: Modifying the existing classes could introduce new bugs.
  * Highlights: This enhancement made would help to detect bugs introduced after modifications.

* **Enhancements to existing feature**: Restrict the number of tags allowed for each food item (Pull request [\#57](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/57)).
  * What it does: Restricts the number of tags allowed to one.
  * Justification: The tag is used to capture the meal type for each food item. The code base is modified to allow multiple food items with the same name and it would be more logical for each food item to be tagged once only.
  * Highlights: Tag is now compulsory for each food item.

* **Enhancements to existing feature**: Modify the type of arguments required for the `EditCommand` (Pull request [\#39](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/39)).
  * What it does: Modify the arguments needed for the `EditCommand` to suit the fields needed for each food item.
  * Justification: The fields for each food item have been changed, and it would be more logical to modify the arguments required for the `EditCommand`.
  * Highlights: Slight modification to suit a food item better.

* **Documentation**:
    * User Guide:
      * Add documentation for the `edit`, `profile` and `suggest` features.
      * Update documentation for the `help` feature.
    * Developer Guide:
      * Add use cases and user stories.
      * Modify existing UML diagrams and links to match the project.
      * Add implementation details for `edit`, `profile` and `suggest` features, together with activity, object and sequence diagrams.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#41](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/41), [\#73](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/73), [\#93](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/93).
  * Report [bugs](https://github.com/nicolelim02/ped/issues) found in CS2103T-F12-4's product during the practical exam dry run.
  * Fix issues that my team members face in the following pull requests: 
    * [\#48](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/48): Identify bugs in test cases. 
    * [\#1](https://github.com/ruiqi7/tp/pull/1): Fix the width of the components (result box and list container) to be 50% each, relative to the size of the main window. 
