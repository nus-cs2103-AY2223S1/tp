---
layout: page
title: Tan Jia Rong's Project Portfolio Page
---

### Project: Plannit

**Plannit** is an **all-in-one application** that streamlines the execution of module
deliverables by **empowering NUS students** with the ability to manage **tasks**, **links** and
**module-mates** (i.e., students in the same module) to increase their productivity.

Given below are my contributions to the project.

#### Navigation ([#64](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/64))

* **New Features:** Added `goto` and `home` commands.
  * What it does: <br>
    To allow users to navigate to specific module to view information relevant to the particular module and
    navigate back to the home page to have an overview of details stored in Plannit.
  * Justification: <br>
    Initial implementation of AB3 consisted of only contacts, while Plannit features
    many more components, with the main components being `Module` and `Person`.
    As our application aims to streamline the execution of module deliverables,
    the `goto` command is implemented to allow users to view precise information
    relevant to the particular module such as the tasks, links and person which
    are tied to that module, which is critical for our value proposition.

**Highlights** <br>
* The navigation function introduces a new concept of home page and module page which is previously not found
  in AB3. Hence, deliberate considerations have to be put into designing the behaviour to maximise user experience.

* The implementation of the navigation system involves constant updating of `UniqueModuleList`, `UniquePersonList`
  and `isHome` status. Hence, it is not trivial as stringent checks are required to ensure the proper integration
  between `Logic`, `Model` and `Ui` to ensure what is executed matches what is seen by the user and that `isHome`
  status is set to the correct state as an incorrect state would jeopardize a user's experience.

* To ensure proper integration, extensive manual testing was required to ensure that the feature was bug-free.

#### Search ([#81](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/81))

* **Enhancements to existing features:** `find` and `list` commands.
  * Implemented `find-module`, `find-person`, `list-module` and `list-person` commands.
  * Modified existing AB3's predicate to allow users to search by module and person name prefixes to better suit our project needs.
  * Added constraints that users can only use `find` and `list` at the home page for better user experience.

**Highlights** <br>
* Much thought have to be put into considering whether to implement search functionalities, as navigation
  functionalities such as `home` and `goto` command are sufficient in showing the modules.
  However, we chose to include it to improve user experience by allowing them to search for modules and contacts
  from the home page.

* Additionally, it is a design decision to disable `find` and `list` commands after usage of `goto` command as 
  * for modules: `find` and `list` is irrelevant when there is only one module and may result in confusion instead.
  * for persons: Relevant person associated with a module is likely to be small as it should only comprise of 
  people whom the user needs to interact with in the module (i.e. friends, project mates, teaching assistant, professors).

**Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tan-jia-rong&breakdown=true)

### Other contributions
**Documentation:**

* **User Guide:**
  * Added documentation for the following features:
    * `home` and `goto` ([#50](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/50))
    * `find-module`, `list-module`, `find-person` and `list-person` ([#81](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/81))
  * Updated UG with tips and notes and fixed some minor issues ([#161](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/161))

* **Developer Guide ([#87](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/87)):**
  * Added implementation details for `goto` command.

**Contribution to team-based-tasks:**
* Created a Telegram channel to serve as the team's main medium of communication.
* Facilitated team discussions and weekly meetings by setting up a Discord channel, along with various text channels for note-taking.
* Created first draft of `README.md` to match Plannit ([#32](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/32))
* Implemented initial model framework for Link, Task and Module ([#59](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/59))

**Review contributions:**
* List of PRs reviewed can be found [here](https://github.com/AY2223S1-CS2103T-T10-1/tp/pulls?q=is%3Apr+reviewed-by%3ATan-Jia-Rong).
* PRs reviewed (with non-trivial review messages):
  [#83](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/83),
  [#89](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/89),
  [#104](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/104),
  [#114](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/114),
  [#160](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/160),
  [#164](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/164),
  [#167](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/167).

**Contributions beyond project team:**
* Sent 15 bug [reports](https://github.com/Tan-Jia-Rong/ped/issues) in
  [Pupilist](https://github.com/AY2223S1-CS2103T-W09-4/tp)
