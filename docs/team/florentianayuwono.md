---
layout: page
title: Florentiana Yuwono's Project Portfolio Page
---

# Project: SoConnect

SoConnect is a **desktop app for managing contacts and tasks**. It aims to help NUS SoC students stay better connected to their school life, in terms of social connections and school tasks. SoConnect is also **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 42 kLoC.

## Contribution

### New Feature: Advanced Search Functionality

**What it does**: Allows user to have a mini Google inside SoConnect. The search command can generate relevant search result, even in the event of mistyping or only partial information is available.

**Justification**: This feature improves the user productivity significantly because a user will not need to provide full information upon searching and the app could still return accurate and relevant result based on the query. 

**Highlight**: This enhancement replaces the previous `find` functionality. The search feature itself requires careful design on how the algorithm works, especially on the relevancy reduction level.

**Credit**: This feature is adapted from the `find` feature, which only allows full word match result to be returned.

### Code Contribution

Up until 2 November 2022, I have contributed 1781 LoC (roughly 4,185 additions and 2,481 deletions), changed 36 files and pushed 132 commits. For most recent update, refer to this [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=florentianayuwono&breakdown=true).

### Project Management

**Releases and Milestones**: 
* `v1.1`: 1 issue and 1 PR.
* `v1.2`: 8 issues and 4 PRs.
* `v1.3`: 18 issues and 6 PRs.
* `v1.4`: 17 issues and 2 PRs.

**Enhancement to existing features**: 
* Revamped the GUI theme and CSS structure (Pull requests [\#142](), [\#128]()).

**Documentation**:
* User Guide:
  * Added documentation for the `search` feature (Pull requests [\#152](), [\#125](), [\#75]()).
  * Updated links and overall project documentation (Pull requests [\#152](), [\#125](), [\#82](), [\#12]()).
* Developer Guide:
  * Added implementation details and sequence diagram for the `search` feature (Pull requests [\#152](), [\#75]()).

**Community**:
* 24 PRs reviewed (with 64 review comments).
* Contributed to 4 posts in forum discussions.
* Reported 26 bugs and suggestions for other teams in the class.

**Tools**:
* Coming soon.
