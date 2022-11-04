---
layout: page
title: Zacchaeus' Project Portfolio Page
---

### Project: Condonery

Condonery is a desktop property directory application for property agents to manage listings and clients.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Features Developed

- Add `find` command for clients and properties (Pull request [#76](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/76))
  - What it does: Allows user to find property and clients by name.
  - Justification: This feature enables the property agent to search for listings or clients by name easily.

- Add `filter` by tag commands for clients and properties (Pull request [#53](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/53))
  - What it does: Allows user to filter the property or client directory based on tag attributes.
  - Justification: This feature enables the property agent to easily identify properties or clients with the same tag.

- Add `range` command for properties (Pull request [#88](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/88))
  - What it does: Allows user to filter properties within a lower and upper bound of price range.
  - Justification: This feature enables the property agent to identify suitable properties within budget for their clients.
  - Highlights: This enhancement required an in-depth analysis of design alternatives.
  The implementation too was challenging as it required a user-friendly implementation of the price attribute in the property class.

- Add `type` command for properties (Pull request [#111](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/111))
  - What it does: Allows user to filter properties by type.
  - Justification; This feature enables the property agent to identify properties by their type e.g. HDB, Condo.

## Code Contributed

[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zacchaeuschok&breakdown=true)

## Enhancements to existing features:

- Updated the GUI property and client cards to clip long text (Pull request [#211](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/211))
- Refactored test cases for modified features to maintain robust test coverage (Pull requests [#67](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/67))

## Contributions to the UG

- Updated first draft of user guide to contain all property and client commands (Pull request [#103](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/103))
- Wrote detailed instructions for usage of `range -p` and `filter` commands (Pull request [#105](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/105))

## Contributions to the DG

- Added documentation for the commands `range -p`, `filter -p` and `filter -c` 
- Added sequence and activity diagrams for `range -p` command (Pull request [#214](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/214))
- Added user stories (Pull request [#23](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/23))

## Project management:

- Managed release v1.3 (trial)

## Community:

- PRs reviewed (with non-trivial review comments): [#57](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/57)
- Reported bugs and suggestions for other teams in the class

## Tools:

- Integrated a third party library (Apache Commons Lang 3) to the project ([#211](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/211))
