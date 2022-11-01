---
layout: page
title: Shen Yi Hong's Project Portfolio Page
---

### Project: `CLIMods`

`CLIMods` is a native desktop application for NUS students to explore, manage and plan academic
modules that they are interested in taking. Power users of the command line/terminal will be able to
plan and manage their modules much faster than doing it on [nusmods.com](https://nusmods.com).

Given below are my contributions to the project.

- **New Feature**: Added the ability to find modules using keywords.
    - What it does: Allow users to find modules offered by NUS using keywords
    - Justification: This feature improves the product significantly because a user can easily
      search for a module using keywords without needing to know the exact module code.
    - Highlights: This feature allows for valid regex expressions to be used as keywords.
      Additionally, it also employs a simple sorting heuristics to rank the results based on
      relevance for a better user experience.

- **New Feature**: Added a history command that allows the user to navigate to previous commands
  using `<Up>/<Down>` arrow keys
    - What it does: Keeps track of a command history and allow users to traverse through the command
      history using arrow keys.
    - Justification: The features improves the user experience as a user can easily reuse previous
      commands without having to type it all over again.
    - Highlights: It required a careful consideration for various designs that are implemented in
      popular shells. Inspired by the `zsh` design, the command history does not keep consecutive
      duplicate commands to reduce clutter.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=shenyih0ng&breakdown=true)

- **Project management**:
  - Managed releases `v1.2` - `v1.3` (2 releases) on GitHub

- **Enhancements to existing features**:
  - TODO

- **Documentation**:
  - Developer Guide:
    - Added implementation details of the command history feature.

- **Community**:
  - PRs reviewed (with non-trivial review comments): [\#59](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/59), [\#82](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/82), [\#85](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/85)

- **Tools**:
  - Integrated a third party library (swagger-codegen/openapi-generator) to the project ([\#45](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/45))
