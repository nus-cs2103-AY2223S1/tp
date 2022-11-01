---
layout: page
title: Tan Jia Rong's Project Portfolio Page
---

## Project: Plannit

### Overview
**Plannit** is an **all-in-one application** that streamlines the execution of module
deliverables by **empowering NUS students** with the ability to manage **tasks**, **links** and
**module-mates** (i.e., students in the same module) to increase their productivity.

### Summary of contributions
Given below are my contributions to the project.

#### Navigation ([#64](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/64))

* **New Feature:** Added `goto` command.
  * What it does: <br>
    Allows users to navigate to specific module to
    view information relevant to the particular module.
  * Justification: <br>
    Initial implementation of AB3 consisted of only contacts, while Plannit features
    many more components, with the main components being `Module` and `Person`.
    As our application aims to streamline the execution of module deliverables,
    the `goto` command is implemented to allow users to view precise information
    relevant to the particular module such as the tasks, links and person which
    are tied to that module, which is critical for our value proposition.

* **New Feature:** Added `home` command.
  * What it does:
    Allows users to navigate back to home page after using `goto` to
    have an overview of details stored in Plannit.
  * Justification: <br>
    A consequence of `goto` so that users can return to the home page
    to have an overview of all modules and persons.

**Highlights** <br>
* The navigation function introduces a new concept of home page and module page which is previously not found
  in AB3. Hence, deliberate considerations have to be put into designing the behaviour to maximise user experience.

* The implementation of the navigation system involves constant updating of `UniqueModuleList`, `UniquePersonList`
  and `isHome` status. Hence, it is not trivial as stringent checks are required to ensure the proper integration
  between `Logic`, `Model` and `Ui` to ensure what is executed matches what is seen by the user and that `isHome`
  status is set to the correct state as an incorrect state would jeopardize a user's experience.

* To ensure proper integration, extensive manual testing was required to ensure that the feature was bug-free.

#### Search ([#81](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/81))

* **New Feature:** Added `find-module` command.
  * What it does: <br>
    Allows users to search for modules on the home page.
  * Justification: <br>
    All modules are listed in the home page. Hence, `find-module` is implemented to allow users to filter
    modules by module code prefix. <br>
    Additionally, it is a design decision to disable it after using `goto` command to prevent
    confusion between usage of `goto` command and `find-module` command.


* **New Feature:** Added `list-module` command.
  * What it does: <br>
    Allows users to list every module on the home page.
  * Justification: <br>
    A consequence of `find-module` so that users can reset the list of modules back to its
    original state.


* **New Feature:** Added `find-person` command.
  * What it does: <br>
    Allows users to find persons on the home page.
  * Justification: <br>
    Similar to `find-module`, all person details are listed in
    the home page. Hence, `find-person` is implemented to allow users to filter the persons
    list by the person name prefix. <br>
    Additionally, it is a design decision to disable it after using `goto` command
    as relevant person added to a module is likely to be small as it should only comprise of
    people whom the user needs to interact with in the module
    (i.e. friends, project mates, teaching assistant, professors).


* **New Feature:** Added `list-person` command.
  * What it does: <br>
    Allows users to list every person's details on the home page.
  * Justification: <br>
    A consequence of `find-person` so that users can reset the list of persons back to its
    original state.

**Highlights** <br>
* Slight modification to existing AB3's predicate to allow users to search by prefixes for better user experience.
* Much thoughts have to be put into considering on whether to implement search functionalities as `home` and `goto`
  command is sufficient in showing the modules. However, we chose to include it to improve user experience by allowing
  them to search for modules and contacts from the home page.

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