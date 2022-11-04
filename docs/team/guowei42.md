---
layout: page
title: Guo Wei's Project Portfolio Page
---

### Project: TAB

TAB is a desktop address book application used for helping Professors and TAs in their daily tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Creating New TAB
    * What it does: Allow users to create new TABs to store more names.
    * Justification: This is important as some TAs or professors might be teaching different modules or different tutorial groups. Therefore, this helps them to separate and differentiate them.
  This reflects similarly to features offered by Vim to easily create new tabs without needing to open separate shells.
    * Highlights: This enhancement's main challenge was that it is compulsory to integrate other core features, like swap and rename. 
* **New Feature**: Rename Current TAB
  * What it does: Allows users to rename the current TAB to something else.
  * Justification: This allows users to easily differentiate the address books created.
  * Highlights: This was challenging to correctly manage and store the changes information for future use.
* **New Feature**: Swapping between TAB
  * What it does: Allows users to swap between the TABs created with `ctrl+tab` shortcut or `swap` command.
  * Justification: Similar to Vim's tab creation, a quick way to swap between TABs created is needed.
  * Highlights: The main challenge for this was to correctly save the current TAB and swap between TABs. Also, there was a need to
  correctly keep track of where the user left off for a better experience.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=guowei42&breakdown=true)

* **Project management**:
    * Managed releases `v1.2`

* **Enhancements to existing features**:
    * _{WIP}_

* **Documentation**:
    * User Guide:
        * Added some FAQs
        * _{WIP}_
    * Developer Guide:
        * Added diagrams for Features implemented above
        * _{WIP}_

* **Community**:
    * Reviewed PRs #93, #92, #85, #75, #74, #61

