---
layout: page
title: Ek Wei Rui's Project Portfolio Page
---

### Project: Plannit

**Plannit** is an **all-in-one application** that streamlines the execution of module
deliverables by **empowering NUS students** with the ability to manage **tasks**, **links** and
**module-mates** (i.e., students in the same module) to increase their productivity.

Given below are my contributions to the project.


* **New Feature:** Added the ability to display persons associated to a module upon navigating to it, ie a module-person
  association ([#79](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/79))
    * What it does: <br>
      Allows users to view all the persons associated to a module.
    * Justification: <br>
      This feature allows forgetful users to know which of their friends take a certain module so that they know who to
      approach when they need help with the module's work
      ([user story](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/80)).
    * Highlights: <br>
      This enhancement was not easy because it involved interactions between two
      different kinds of `Model` objects, namely `Person` and `Module`, which required intensive
      considerations on the design to ensure there were as little dependencies as there could be. Due to that and the
      bug-prone nature of the feature, it required an in-depth analysis of design alternatives. The implementation was
      also challenging because existing commands had to be modified. This was previously not in the design of AB3.
      (see [#98](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/98)).


* **New Feature:** Added `delete-person-from-module` command
  ([#100](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/100))
    * What it does: <br>
      Allows users to remove the association between a person and a module (if it exists).
    * Justification: <br>
      This feature allows users to maintain their person-module associations to be updated with real world events. For
      example, users can delete a person from a module if the person dropped out halfway, or when a new semester
      arrives ([user story](https://github.com/AY2223S1-CS2103T-T10-1/tp/issues/99)).
    * Highlights: <br>
      Same as the new feature before.


* **Enhancements/modifications to existing features**:
    * Modified AB3's `add` command to better suit project needs
        * Removed address and tags field as they were deemed irrelevant to our target audience and value proposition
          ([#62](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/62)).
    * Enhanced AB3's `edit` command to work with newly implemented features
        * Added the ability for editing of person to work seamlessly with the module-person association, ie editing a
          person also edits all instances of the person in each module (if it exists)
          ([#98](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/98)).
    * Modified AB3's `delete` command to use new parameters
        * Modified the parameter used for deletion of a contact to be from index to name so that it is consistent with
          other commands and also to make it more intuitive to the user
          ([#92](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/92)).


* **Highlights and minor features:**
    * Spearheaded discussions and laid foundation down for the module-person association and all of its related commands
      ([some discussions found here](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/79)).
    * Led discussions related to UI display issues after execution of commands and implemented the final behaviour
      ([#157](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/157)).
    * Added the ability to display the modules and persons list in sorted order
      ([#106](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/106)).


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ekweirui&breakdown=true)


* **Documentation**:
    * User Guide:
        * Added documentation for the following features:
            * `add-person` and `delete-person` ([#49](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/49))
            * `edit-person` ([#62](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/62))
            * `delete-person-from-module` ([#111](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/111))
    * Developer Guide:
        * Added documentation for the contacts-related section and the `delete-person` command
          ([#88](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/88)).
        * Added documentation for the person-module association and the `add-person-to-module` command
          ([#190](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/190)).
        * Added user stories and use cases related to contacts.


* **Review contributions**:
    * PRs reviewed (with non-trivial review comments):
      [#90](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/90),
      [#101](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/101),
      [#151](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/151),
      [#63](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/63)
    * Full list of PRs reviewed
      [here](https://github.com/AY2223S1-CS2103T-T10-1/tp/pulls?q=is%3Apr+reviewed-by%3Aekweirui)


* **Team based tasks**:
    * Necessary general code enhancements:
        * Refactored `contact` commands to `person` after feedback from teammates
          ([#86](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/86))
    * Maintaining the issue tracker:
        * Created and assigned the majority of labels and milestones.
        * Resolved issues after a relevant PR has been merged.


* **Community**:
    * Contributed to forum discussions: (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/370),
      [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/389))
