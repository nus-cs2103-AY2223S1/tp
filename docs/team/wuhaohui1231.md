# Wu Haohui's Project Portfolio Page


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
  * What it does: Allows the cashiers to perform arithmetic calculation (+, -, *, /) within bobaBot
  * Justification: This feature improves the product significantly because cashiers can type arithmetic in command line much more faster than on normal calculators.
  * Highlights: It supports multiple combined arithmetic operations and precedence (parenthesis in the expression). 
      The implementation involved lexical analysis and calculator language parsing. It parse the expression string into a 
      list of tokens with a certain order that the program can understand and perform calculation correctly base on it
  * Credits: Reused the logic from https://www.daniweb.com/programming/software-development/threads/442690/java-expression-parser-calculator

* **New Feature**: GUI Calculator
  * What it does: Launch a build-in calculator in GUI. The calculator is a normal one with keys of numbers and operators.
    * Justification: This feature improves the product significantly because cashiers do not need to switch their hands to a 
        separate physical calculator. This speed up the process of calculating order amount and changes in reward
  * Highlights: The implementation involved works on JavaFX such as creating new controller, fxml for the new calculator window
  * Credits: Reused GUI layout from https://gist.github.com/argius/08834fab73b91de8d79b with modifications

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wuhaohui1231&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Reviewed the following PRs
    * [PR #219](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/219),
      [PR #137](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/137),
      [PR #90](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/90),
      [PR #76](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/76),
      [PR #67](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/67),
      [PR #53](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/53),
      [PR #52](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/52),
      [PR #47](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/47),
      [PR #16](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/16),
      [PR #15](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/15),
  * Created the following issues to divide and manage tasks
    * [Issue #132](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/132),
      [Issue #124](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/124)
      [Issue #105](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/105),
      [Issue #73](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/73),
      [Issue #33](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/33),
      [Issue #29](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/29),
      [Issue #23](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/23),
      [Issue #10](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/10),
      [Issue #5](https://github.com/AY2223S1-CS2103T-W09-1/tp/issues/5),

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#153](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/153))
  * Wrote additional tests for existing features to increase coverage from xx% to xx% (to be added) (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `calc` and `calc-gui` [\#164](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/164)
  * Developer Guide:
    * Draw overall class diagram for commands using plantUML

* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/261), 
                                                [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/25))
  * Reported bugs and suggestions for other teams in the class during PE dry run
