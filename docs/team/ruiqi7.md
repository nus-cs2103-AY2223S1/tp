---
layout: page
title: Ruiqi's Project Portfolio Page
---

### Project: NutriGoals

NutriGoals is a desktop app that tracks a user’s diet and calorie consumption. It is targeted at NUS students who wish
to be more mindful about their calorie intake and adopt a healthier lifestyle.

Given below are my contributions to the project.

* **New feature**: Added the ability to show a summary of the calorie intake progress (Pull request [#56](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/56)).
  * What it does: Allows the user to view the total calories consumed, the calorie target and the deficient or excess 
  amount of calories for the current day. 
  * Justification: This feature provides a convenient way for users to view all the details about their calorie intake 
  progress for the day. Users do not have to manually calculate their total calorie intake and can track their progress more easily.
  * Highlights: The implementation of this feature involved summing up the calorie content of each food item recorded on 
  the current day, retrieving the calorie target set and calculating the difference between the two values.

* **New feature**: Added the ability to find the calorie content of a food item (Pull request [#73](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/73)). [Done partially with Xavier]
  * What it does: Allows the user to find the calorie content of a food item by specifying its name.
  * Justification: This feature allows users to check the calorie content of a food item so that they can gain more 
  knowledge about their calorie consumption and make better food choices.
  * Highlights: This feature relies on a hashmap to store and retrieve the calorie content of a food item. Currently, 
  there is a limited number of food items included. In future releases, calorie content data could be read from a larger 
  food database to enhance the effectiveness of this feature.

* **New feature**: Added a progress bar display (Pull request [#91](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/91)).
  * What it does: Shows the user's calorie intake progress for the current day by taking the total calorie intake for the 
  day as a percentage of the target calorie intake set.
  * Justification: This GUI feature provides a quick way for users to estimate their calorie intake progress for the day 
  without having to run any commands.
  * Highlights: The implementation involved binding the JavaFX `ProgressBar` to an observable double value representing the 
  calorie intake progress. The progress bar is updated whenever the total calorie intake or target calorie intake changes.

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ruiqi7&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed [releases](https://github.com/AY2223S1-CS2103T-T17-2/tp/releases) `v1.2` and `v1.2.1` on GitHub.
  * Created and assigned issues, created and closed milestones.

* **Enhancements to existing feature**: Modified the `list` feature to filter the food list by date and sort it by meal type (Pull request [#41](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/41)). 
  * What it does: Allows the user to view the food items recorded on any day by specifying the desired date.
  * Justification: This enhancement allows users to track their meals and calorie consumption for each day separately so 
  that they can better work towards their target calorie intake for each day.
  * Highlights: The implementation involved creating a `DateTime` field for each food item when it is added or edited. 
  The filtering of the food list relies on a custom `IsFoodAddedOnThisDatePredicate` class, while the sorting is done 
  using a custom `FoodComparator` class.

* **Enhancements to existing feature**: Modified the GUI layout and color scheme, except for the list display (Pull request [#91](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/91)). [With Nicole's help]
  * Rearranged the result display to be horizontally aligned with the food list.
  * Changed dark theme to light theme.

* **Documentation**:
  * User Guide:
    * Added documentation for the `list`, `review` and `find` features.
    * Updated documentation for the `Quick start` and `FAQ` sections.
    * Added the `About the user guide` and `Screen layout` sections.
  * Developer Guide:
    * Added implementation details of `list`, `review` and `find` features with sequence, activity and object diagrams.
    * Added user stories and use cases.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#60](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/60), 
  [#87](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/87), [#107](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/107),
  [#146](https://github.com/AY2223S1-CS2103T-T17-2/tp/pull/146).
  * Reported [bugs and suggestions](https://github.com/ruiqi7/ped/issues) for CS2103T-W08-3 during the PE-D.
