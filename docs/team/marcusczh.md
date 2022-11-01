---
layout: page
title: Marcus's Project Portfolio Page
---

# Project: Long Time No See (LTNS)

Long Time No See (LTNS) is a desktop task tracking app made for financial advisors to manage their clients, policies and events. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

## Summary of Contributions:

### Code contributed:
[tP Code Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=marcusczh&breakdown=true)

### Features implemented:

Implemented all search related functionalities:
- Searching for a client: `findClient` 
  - What it does:
    - Allow users to search for Clients in the application, based off the different metrics that can be specified

- Searching for a policy: `findPolicy`
  - What it does:
      - Allow users to search for Policies in the application, based off the different metrics that can be specified

- Searching for an event: `findEvent`
  - What it does:
      - Allow users to search for Events in the application, based off the different metrics that can be specified

- Justification:
    - They are all important functionalities for the target user as being able to search for entries in the application based off their information is what sets the application apart from more traditional means like pen and paper. These all provide the target user with an easier way to retrieve information from the application whenever needed
- Highlights:
    - The search features are implemented in such a way that extension can be easily achieved without modification, whereby more metrics can be easily added for the user to search, without any modification of other classes
    - As the enhancement required the use of all the individual Person/Policy/Event attributes, it required a significant understanding of how each attribute was implemented and their constraints
    - As an added touch, it is also possible to specify more than one metric in each search, to provide the user with the possibility to narrow down the search range

### Contributions to the UG:
- Added documentation for the following features
  - Searching for a client: `findClient`
  - Searching for a policy: `findPolicy`
  - Searching for an event: `findEvent`
- Created a summary table to orientate readers on all the possible prefixes, along with their input constraints.

### Contributions to the DG:
- Added Implementation details for all `find` related commands 
- Added use cases

### Contributions to team-based tasks:
- Set up the GitHub team organisation and repo
- Maintained GitHub tools
  - Created issue labels
  - Created and maintained milestones
- Managed all releases on GitHub

### Review/mentoring contributions:
- Main reviewer for Reuben

### Contributions beyond the project team:
- Reported an above average number of bugs in the [Practical Exam Dry Run](https://github.com/marcusczh/ped/issues)
