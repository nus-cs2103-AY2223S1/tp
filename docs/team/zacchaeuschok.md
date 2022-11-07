---
layout: page
title: Zacchaeus' Project Portfolio Page
---

### Project: Condonery

Condonery is a desktop app made for property agents primarily used for managing client contacts and condo listings.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Condonery can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **New Feature**: Add `find` command for clients and properties (PR [#76](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/76))
  - What it does: Allows user to find property and clients by name.
  - Justification: This feature enables the property agent to search for listings or clients by name easily.

- **New Feature**: Add `filter` by tag commands for clients and properties (PR [#53](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/53))
  - What it does: Allows user to filter the property or client directory based on tag attributes.
  - Justification: This feature enables the property agent to easily identify properties or clients with the same tag.

- **New Feature**: Add `range` command for properties (PR [#88](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/88))
  - What it does: Allows user to filter properties within a lower and upper bound of price range.
  - Justification: This feature enables the property agent to identify suitable properties within budget for their clients.
  - Highlights: This enhancement required an in-depth analysis of design alternatives.
  The implementation too was challenging as it required a user-friendly implementation of the price attribute in the property class.

- **New Feature**: Add `type` command for properties (PR [#111](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/111))
  - What it does: Allows user to filter properties by type.
  - Justification: This feature enables the property agent to identify properties by their type e.g. HDB, Condo.

- **Code Contributed**:[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zacchaeuschok&breakdown=true)

- **Enhancements to existing features**:
  - Updated the GUI property and client cards to clip long text (PR [#211](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/211))
  - Refactored command parser to handle both property and client commands (PR [#224](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/224))
  - Refactored test cases for modified features to maintain robust test coverage (PRs [#67](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/67))

- **Contributions to the UG**:
  - Updated first draft of user guide to contain all property and client commands (PR [#103](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/103))
  - Formatted user guides with tables for clear user instruction. (PR [#234](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/234))
  - Wrote detailed instructions for usage of `range -p` and `filter` commands (PR [#105](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/105))

- **Contributions to the DG**:
  - Added documentation for the commands `range -p` and `filter -p`.
  - Added sequence and activity diagrams for `range -p` command (PR [#214](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/214))
  - Added user stories (PR [#23](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/23))

- **Project management**:
  - Managed release v1.3 (trial)

- **Community**:
  - PRs reviewed (with non-trivial review comments): [#57](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/57)
  - Reported bugs and suggestions for other teams in the class

- **Tools**:
  - Integrated a third party library (Apache Commons Lang 3) to the project ([#211](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/211))
