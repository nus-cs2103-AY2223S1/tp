# Max Tan's Project Portfolio Page

## Project: Class-ify

Class-ify is a class management application built specially for Ministry of Education (MOE) teachers to easily monitor their students’ academic progress.
Teachers can generate exam statistics for each class, and Class-ify quickly flags out students who require more support for contacting.

It is written in Java and the GUI is built using JavaFX.

### Summary of Contributions

#### Code Contributed
- Check out this [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=maxtance)

#### Enhancement Added
- [ToggleViewCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/ToggleViewCommand.java)
  - Implemented a new command that toggles between showing and hiding parent details from the Student Card (UI component)
  - See PR [#93](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/93) and [#191](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/191)
- [EditCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/EditCommand.java)
  - Caught and fixed a bug where there was an error with checking for a duplicate student 
  - See PR [#197](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/197)
- User Interface (UI)
  - Enhanced the previous design of AB3 to Class-ify's current design
  - Update the Student Card to show the new fields implemented  
  - See PR [#135](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/135) and [#165](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/165)

#### Contributions to the UG
- [Application layout](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#2-class-ifys-application-window)
  - Introduced the components of the UI and their functionalities
- [Toggling view](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#434-toggling-view--toggleview)
  - Explained the functionality and usage of the command

#### Contributions to the DG
- [Toggle View Command](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#427-toggleview-command)
  - Added implementation details and design considerations
  - Added activity diagram to help readers understand the process flow
- [View All Command](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#425-viewall-command)
  - Added implementation details
  - Added sequence diagram to explain the internal interactions of the system

#### Review/mentoring contributions
- [#91](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/91): Suggested to use Optional class instead of assigning null to a variable
- [#99](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/99): Suggested a more appropriate method name
