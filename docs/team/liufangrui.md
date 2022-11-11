---
layout: page
title: Liu Fangrui's Project Portfolio Page
---

### Project: InterNUS

InterNUS is a convenient and powerful desktop app, created to help **NUS CS students manage their internship applications.**
It is optimized for use via a Command Line Interface (CLI),
and complemented with a simple yet intuitive Graphical User Interface (GUI)
designed to help you keep track of all your applications at a glance.

Below are my contributions to the project.

* **New Feature**: `Link Command`
  * What it does: Allows the user to link a person and an internship together, specified person will then be displayed as contact person of the specified internship, and specified internship will be displayed as the internship of the person.
  * Justification: When applying for internships, there is often a contact person that students will contact. It is important that we display this relationship between the person and the internship to allow easy navigation when students intend to follow up on their internships

* **New Feature**: `Unlink Command`
  * What it does: Allows the user to unlink a person and an internship that was previously linked together.
  * Justification: There may be errors when students attempt to link a person and an internship together. In addition, students might decide to unlink the contact person after being accepted or rejected by the relevant internship. 
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=liufangrui&breakdown=true)

* **Project management**:
  * Created and assigned issues to team members. 
  * Reviewed team members’ pull requests.

* **Enhancements to existing features**:
  * Updated parser to be able to parse flags in addition to the command word 
  * Updated parser to throw exceptions when certain command words are used without flags

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#281](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/281),
  [#191](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/191),
  [#141](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/141),
  [#91](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/91), 
  [#61](https://github.com/AY2223S1-CS2103T-F11-1/tp/pull/61), 
  * Reported [bugs and made suggestions](https://github.com/liufangrui/ped/issues)

--------------------------------------------------------------------------------------------------------------------
* **Documentation**:
  * User Guide:
    * Added "Linking a person and an internship"
    * Added "Unlinking a person and an internship"
    * Added "Listing all persons"
  * Developer Guide:
    * Added "Linking a person and an internship" implementation
