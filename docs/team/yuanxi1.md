---
layout: page
title: Zhu Yuanxi's Project Portfolio Page
---

### Project: PennyWise

PennyWise is a desktop application that **empowers students with the ability to make informed financial decisions**, by providing a graphical analysis of their financial activities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature**: Added the ability to handle both expenditure and income in PennyWise.
  * **Justification**: Different from AddressBook Level-3 (the sample project that PennyWise evolves from) which only handles person entries,
    there are two different types of entries: expenditure and income in PennyWise.
  * **Highlights**: This enhancement updates the data storage format to work with both expenditure and income by creating `JsonAdaptedExpenditure` and `JsonAdaptedIncome` classes,
  and updates the state stored in PennyWise model. 
  * PR: [\#34](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/34)


* **Feature**: Added the ability to add and delete entries.
  * **What it does**: allows the user to add entries and delete entries from PennyWise.
  * **Justification**: Adding and deleting is the most basic and essential features for our budgeting app. It allows users to log their expense or income
    and remove any mistakes. 
  * PR: [\#45](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/45)


* **New Feature**: Added the ability to view line graphs of the expenditure and income data.
  * **What it does**: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * **Justification**: This feature is PennyWise's key selling point that differentiates it from applications that does basic logging only, by making
it easy for users to visualise the trend and pattern of their expenses and income with graphs.
  * **Highlights**: The `view by month` command will filter the entry list by month and generate a line graph.
  * PR: [\#73](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/73)

* **Enhancements**:
  * Added a no entry view for the graph panel when there is no data. This makes it more clear to the user that there is no data
to generate a graph compared with showing a blank screen. 
  * PR: [\#87](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/87)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yuanxi1&breakdown=true)

* **Project management**:
  * [PRs Reviewed by me](https://github.com/AY2223S1-CS2103T-W17-2/tp/pulls?page=1&q=is%3Apr+reviewed-by%3Ayuanxi1)

* **Documentation**:
  * User Guide:
    * Added documentation for the quick start and adding income portion in early development stages. PR: [\#22](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/22)
    * Added enhancements such as tips and info boxes for view features. PR: [\#178](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/178)
  * Developer Guide:
    * Added documentation for the implementation details for the view feature. PR: [\#178](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/178)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#30](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/30), [\#35](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/35), [\#82](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/82), [\#171](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/171)
  * Reported bugs and suggestions for other teams in the class [PE-D Issues Link](https://github.com/yuanxi1/ped/issues)
