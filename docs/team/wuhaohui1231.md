---
layout: page
title: Wu Haohui's Project Portfolio Page
---


### Project: bobaBot

bobaBot is a desktop application for managing customers’ membership details. It is optimized for Command Line Interface 
(CLI) while retaining some benefits of the Graphical User Interface (GUI). If you are a cashier working at a bubble tea 
shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as compared 
to other GUI applications.

bobaBot contains a database of customers’ information and supports operations to add, update, delete and even find customers 
based on various inputs. Every entry in the database contains the customer's name, phone number, email address, birthday month, 
reward points and membership tags.

bobaBot is written in Java and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: CLI Calculator
  * In: [PR #151](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/151),
        [PR #247](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/247)
  * What it does: Allows the cashiers to perform arithmetic calculation (+, -, *, /) within bobaBot
  * Justification: This feature improves the product significantly because cashiers can type arithmetic in command line much more faster than on normal calculators.
  * Highlights: It supports multiple combined arithmetic operations and precedence (parenthesis in the expression). 
      The implementation involved lexical analysis and calculator language parsing. It parse the expression string into a 
      list of tokens with a certain order that the program can understand and perform calculation correctly base on it
  * Credits: Adapted from logic from https://www.daniweb.com/programming/software-development/threads/442690/java-expression-parser-calculator

* **New Feature**: GUI Calculator
  * In: [PR #153](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/153),
        [PR #261](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/261) 
  * What it does: Launch a build-in calculator in GUI. The calculator is a normal one with keys of numbers and operators.
    * Justification: This feature improves the product significantly because cashiers do not need to switch their hands to a 
        separate physical calculator. This speed up the process of calculating order amount and changes in reward
  * Highlights: The implementation involved works on JavaFX such as creating new controller, fxml for the new calculator window
  * Credits: Reused from https://gist.github.com/argius/08834fab73b91de8d79b with modifications and bug fixes

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wuhaohui1231&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Reviewed the following PRs
    * [#259](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/259),
      [#253](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/253),
      [#250](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/250),
      [#246](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/246),
      [#219](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/219),
      [#137](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/137),
      [#90](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/90),
      [#76](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/76),
      [#67](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/67),
      [#53](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/53),
      [#52](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/52),
      [#47](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/47),
      [#16](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/16),
      [#15](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/15)
  * Created the following issues to divide and manage tasks
    * [#132](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/132),
      [#124](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/124),
      [#105](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/105),
      [#73](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/73),
      [#33](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/33),
      [#29](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/29),
      [#23](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/23),
      [#10](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/10),
      [#5](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/5)

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests: [\#153](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/153))
  * Wrote additional tests for existing features to increase coverage from 69.67% to 73.20% (Pull requests: [\#245](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/245), [\#247](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/247))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `calc` and `calc-gui`. [PR \#164](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/164)
  * Developer Guide:
    * Draw overall class diagram for commands using plantUML. [PR \#116](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/116)

* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/261), 
                                                [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/25))
  * Reported bugs and suggestions for other teams in the class during PE dry run. [\#Bugs Reported](https://github.com/WuHaohui1231/ped/issues)
