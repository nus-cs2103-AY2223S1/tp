---
layout: page
title: Kang Qiao's Project Portfolio Page
---

### Project: Intrack

InTrack helps Computer Science students who are applying for multiple internships to keep track of their progress and deadlines across their applications.
Intrack optimised for use via Command Line Interface(CLI) and is written in Java, with about 10k lines of code.

Given below are my contributions to the project.

* ***Code contributed:*** My contributions can be accessed via [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kangqiao322&breakdown=true)


* **New Feature**: `status` command(PR:[#63](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/63))
    * What it does: Allows users to update the status of an internship between 3 possible states, namely `Offered`, `Progress` and `Rejected`.
    * Justification: This is to allow users to monitor the progress of their internship applications and allow easier filtering of them later on.
    * Highlights: This is one of the first newly added commands in Intrack, and requires an in-depth understanding of the application parses user inputs and run commands. 
    Many hours were required to trace through the codebase in order to understand how all the objects interact and couple
    with one another.
  

* **New Feature**: `remark` command(PR:[#79](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/79))
    * What it does: Allows users to add a remark or note to an internship application.
    * Justification: This is to allow users to add a note to an internship application, be it something to reflect on their experience in the interview or to note something important for that application.
    * Highlights: This is one of the easier commands to add as it follows the format of `status` command.
  
    
* **New Feature**: `sort` command(PR:[#101](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/101))
    * What it does: Allows users to sort the list of internship applications by the earliest upcoming task of each internship.
    * Justification: This is to allow users to sort the internships to improve user experience, for example to see what internship interview is coming up.
    * Highlights: This command is slightly harder to implement as it requires one of have a deeper understanding of how the different classes couples with another.
  this command is further enhanced later on by my teammates and is able to sort internships by their salaries as well.


* **New Feature**: `addtag` command(PR:[#118](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/118))
    * What it does: Allows users to add one or more tags to an internship application.
    * Justification: This is to enhance user experience, as they are able to use tags to remind themselves of key attributes of an internship application.
    * Highlights: Previously, there is no way for the user to add a tag to an existing application without overriding the old tags, so this enhances the user experience.


* **New Feature**: `deltag` command(PR:[#118](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/118))
    * What it does: Allows users to delete one or more existing tags from an internship application.
    * Justification: This is to allow users to delete tags they no longer want or need from applications.

* **Project management**:
    * Created Milestone v1.1 and created some issues for the team.
    * Created  the tags to be used for labelling issues.

* **Enhancements to existing features**:
    * [To be added soon]
    * [To be added soon]

* **Documentation**:
    * User Guide:
        * [To be added soon]
        * [To be added soon]
    * Developer Guide:
        * [To be added soon]

* **Community**:
    * [To be added soon]
* **Tools**:
    * [To be added soon]

* _{you can add/remove categories in the list above}_
