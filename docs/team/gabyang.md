---
layout: page
title: Gabriel Yang's Project Portfolio Page
---

### Project: Friendnancial

Friendnancial is a desktop contact management application used by
financial advisors to manage their clients and contacts. The user
interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java and has about 10 kLoC.

**Summary of Contributions:**

- **New Feature**: Integrate `Insurance` and `Reminders` into the GUI
  * What it does: display critical information on the GUI.
  * Justification: This feature extends the functionality of the CLI application as a user can immediately view upcoming appointments and contact's
  insurance at a glance.
  * Highlights:  There were issues with how the information can be displayed appropriately because of the bug with relation to how JavaFX's
  SplitPane deals with resizing of the window. This was highly challenging because there was not enough documentation that gives explicit reasons
  as to why this is the case. Thus, the SplitPane had to be fixed in place.

- **Documentation**
  **READ ME**:
  * Add useful information to the project for prospective users and employers to read it and understand the project
in a few minutes
  * Add hooks to attract prospective users to use the application

  **User Guide**: 
  * Add Quick Glossary Table for slightly more technical terms to allow users to quickly understand some terms before reading
  * Updated the screenshots of the UI in order to keep up with the changes
  * Updated information on the edit function that was changed

  **Developer Guide**:
  * Add implementation information for Edit and Add
  * Add Sequence Diagram for Edit and Add
  * Reformatted Use Cases and User Stories
  * Add Full Glossary Table

- **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on Github
  * Coordinated forking workflow
  * Recovered lost data from GitHub flow control mistakes
  * Coordinated weekly tasks and delegated to individual members

- **Enhancements to existing features**:
  * Completely revamped the GUI with modern UI trends and colours
  * Formatted all aspects of GUI with standardised colour format
  * Wrote additional tests for features
  * Handled most of the UI changes and UI debugging

- **Review/mentoring contributes**: 
  * Reviewed PRs with non-trivial comments on how to improve styles and deleting redundant code. [\100](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/100)
  * Reported bugs and suggestions of the team and provided simple solutions to fix it 
  * Helped team members with git flow control to maintain clean commits [\#175](https://github.com/AY2223S1-CS2103T-W10-2/tp/pull/175)
