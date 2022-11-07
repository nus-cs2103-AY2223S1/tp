---
layout: page
title: Project Portfolio Page for Naren Sreekanth
---
#### Overview
**RC4 Housing Database** offers a convenient and intuitive interface for RC4 housing management staff to streamline their daily operations.
#### Code contribution:
##### Enhancements to new and existing features
##### New Feature: `filter` command
- Created a new filter feature to filter residents according to the fields by adding and modifying classes such as `AttributesMatchAllKeywordsPredicate`, `AttributesMatchAnyKeywordsPredicate`, `FilterCommand`, `FilterCommandParser` and `Rc4hdbParser` classes.
- This required a deep level of understanding of each field and how the command can filter the list correctly. New predicate classes were created in order to build this feature.
- The implementation was also made to accept strings that contain the value which took a considerable amount of redesigning how tags are filtered. <br>
##### Major Enhancement: Adding specifiers to the `filter` command
- Enhanced the filter feature to filter according to different specifiers for multiple types of filters by adding `ResidentStringDescriptor` and `Specifier` classes and also modifying the above classes.
- A new structure of classes was made to store the descriptions within the command, including the specifiers, which took multiple levels of redesign throughout the project. <br>
##### New Feature: `remove` command
- Added a remove command to delete multiple residents according to filters indicated by users by adding classes including `RemoveCommand` and `RemoveCommandParser`.
- The new remove command was carefully implemented using the filter implementations to unify the functionalities of our features.
##### Testing
- Testing for filter and remove feature classes mentioned above.
- Testing for venue commands

You may view these contributions in more detail at [this link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nareus&breakdown=true).
#### Contributions to the UG:
- Added the filter and remove command sections for the user guide.
- Fixed UG bugs in file commands section and field details.
#### Contributions to the DG:
- Added use cases for venue add delete view book unbook.
- Added manual testing for modifying residents: add, delete, remove, edit
- Modified storage and logic components
- Added filter implementation in the implementation section

In particular, I added/updated the following UML diagrams:
[FilterCommandSequenceDiagram](../images/FilterCommandSequenceDiagram.png),
[StorageClassDiagram](../images/StorageClassDiagram.png), [LogicClassDiagram](../images/LogicClassDiagram.png),
[CommandDiagram](../images/CommandDiagram.png), [DeleteSequenceDiagram](../images/DeleteSequenceDiagram.png)

#### Community and Project Management:
- Reviewed 22 pull requests including major changes such as [#57](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/57), [#124](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/124) and [#55](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/55).
- Surfaced [8 potential bugs & suggestions](https://github.com/nareus/ped/issues) for other teams during PE-D.
- Supported and handled test workload for the team according to the progress and expectations of the project.

#### Contributions to the Developer Guide:
![](../images/FilterCommandSequenceDiagram.png)
![](../images/StorageClassDiagram.png)
![](../images/LogicClassDiagram.png)
![](../images/CommandDiagram.png)
![](../images/DeleteSequenceDiagram.png)
