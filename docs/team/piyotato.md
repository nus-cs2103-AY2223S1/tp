---
layout: page
title: Sheyuan's Project Portfolio Page
---

### Project: StudMap

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=piyotato)

* **Project management**:
  * Helped to coordinate multiple team meetings

* **Enhancements to existing features**:
  * Proposed and implemented `StudentData` parameter object for easy extension of `Student` class so that features can be more easily implemented.
    * Features hinging on this infrastructure include attendance/participation/assignment tracking, and new fields added to `Student`, including Telegram Handle, GitHub username.
    * This infrastructure also eased the deletion of unneeded features, such as the `Address` field in `Student`.
  * Planned and wrote the abstraction of student editing via multiple abstract classes, including `IndexCommand`, `EditStudentCommand`, and `StudentEditor` in order to unify behaviour. 
    * Features hinging on this infrastructure include the commands `edit`, `mark`, `unmark`, `grade`, `ungrade`, `participate`, `unparticipate`, `delete`.
  * Abstracted the idea of commands that require parsing an `index` in `IndexCommand`, so that all index-related behaviour can be changed in one place.
    * This allowed the syntax of `all` to be used in all such commands, including those mentioned above.
    * This improved the extensibility of the code to possibly support more sophisticated syntax for specifying `index` in commands without major refactors.
  * Added the ability for some fields in `Student` such as `Email` and `Phone` to be left optional.
  * Added `MultiStateAttribute` to abstract the commonalities betweeen `Attendance`, `Assignment`, and `Participation`.

* **Documentation**:
  * Wrote the preface for User Guide
  * Planned and organized the User Guide.
  * Prepared the UI screenshots for the User Guide.
  * Did general formatting of both User Guide and Developer Guide.

* **Community**:
  * Reviewed most functional pull requests from team members.
  * Ensured good code quality by suggesting appropriate implementation strategies.

