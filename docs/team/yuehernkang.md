# Condonery

##Overview:
Condonery is a desktop app made for property agents primarily used for managing client contacts and property listings. 
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 


## Summary of Contributions:

Code contributed: Here is a link to [my code](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yuehernkang&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) on the TP Code Dashboard

New Features implemented: 

- New Feature: Add **PropertyStatusEnum** as a Property Field
  - What it does: allows Properties to have PropertyStatus of `AVAILABLE`, `PENDING`, and `SOLD`
  - Justification: This allows the user to know a property's status which is important when doing recommendations to clients
  - Highlights: This enhancement allows users to 

- New Feature: Added the ability to filter by PropertyStatusEnum (Pull Request [#115](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/115))
  - What it does: allows the user to filter properties by their respective status (`AVAILABLE`, `PENDING`, `SOLD`). 
  - Justification: This feature improves the user experience of Condonery because it allows the user to 
  know which properties are `AVAILABLE`, `PENDING` or `SOLD` without scrolling through all the properties listed
  - Highlights: This enhancement creates a new command. It requires the parser to identify a new prefix and perform 
validation to ensure that the input provided is valid.

- Create `Add`, `Delete` and `Edit` commands for `Property` and `Client` classes
- Edit Property Details (Pull Request [#71](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/71))
- Edit Client Details (Pull Request [#84](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/84))
- Delete Property (Pull Request [#62](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/62))
- Delete Client (Pull Request [#86](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/86))


Enhancements:
- Consistently increased test coverage (Pull Requests [#238](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/238),
  [#200](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/200), [#70](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/70),
  [#50](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/50))

Contributions to the UG: `to be added soon`

Contributions to the DG:
- Added documentation for Filter by `PropertyStatusEnum` feature
- Added use cases
- Added instructions for manual testing 

Community:
- Reviewed PRs of peers
- Reported bugs and suggestions for other teams

### Contributions to team-based tasks
