---
layout: page
title: Project Portfolio Page for Nicholas Seah
---

### Overview

**RC4 Housing Database** offers a convenient and intuitive interface for RC4 housing management staff to 
streamline their daily operations.


### Summary of Contributions

#### Code contributed:

1. `ResidentField.java`, and its subclasses `Gender.java`, `House.java`, `MatricNumber.java`, `Name.java`, 
   `Phone.java`, and `Room.java`
   
2. `ColumnManipulatorCommand.java`, and its subclasses `ListCommand.java`, `ResetCommand.java`, `ShowOnlyCommand.java` and `HideOnlyCommand.java`
   
3. `Rc4hdbParser.java`, `ColumnManipulatorCommandParser.java`, and its subclasses `ShowOnlyCommandParser.java` and `HideOnlyCommandParser.java`

4. `Model.java`, and its subclass `ModelManager.java`

5. `Logic.java`, and its subclass `LogicManager.java`

6. `Venue.java`, `Booking.java`, and its subclass `RecurrentBooking.java`

7. The UI classes `MainWindow.java` and `ResidentTableView.java`


You may view these contributions in more detail at [this link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nseah21&breakdown=true). 
The test code written can also be found at the same link.

#### Enhancements implemented:

1. Reworked the UI to use a `TableView` instead of a `ListView`

2. Reworked the UI to allow for the use of `CTRL-TAB` for switching between `ResidentTableView` and `VenueTableView` 
    
3. Added the feature for users to hide unwanted columns to de-clutter their screen


#### Contributions to the UG:

1. [Listing all residents : `list`](https://ay2223s1-cs2103t-w12-3.github.io/tp/UserGuide.html#listing-all-residents--list)
   
2. [Showing resident fields : `showonly`](https://ay2223s1-cs2103t-w12-3.github.io/tp/UserGuide.html#showing-only-some-columns--showonly)
   
3. [Hiding resident fields : `hideonly`](https://ay2223s1-cs2103t-w12-3.github.io/tp/UserGuide.html#hiding-only-some-columns--hideonly)
   
4. [Resetting hidden resident fields : `reset`](https://ay2223s1-cs2103t-w12-3.github.io/tp/u#resetting-hidden-columns--reset)
   
5. [Locating residents by name : `find`](https://ay2223s1-cs2103t-w12-3.github.io/tp/UserGuide.html#locating-residents-by-name--find)

#### Contributions to the DG:

1. [Acknowledgements](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#acknowledgements)

3. [Design for Model component](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#model-component)
   
4. [Implementation of column hiding feature](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#showhide-feature-for-resident-fields)
   
5. [Use cases](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#use-cases)
   
6. [User stories](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#user-stories)

7. [Manual testing section for viewing residents](https://ay2223s1-cs2103t-w12-3.github.io/tp/DeveloperGuide.html#viewing-residents)


#### Contributions in terms of UML diagrams: 


1. Class diagram for Model component

2. Class diagram for possible extension of Model component with better OOP

3. Diagram illustrating the reference relationships of MainWindow and its subclasses

Please refer to the appendix for the UML diagrams mentioned in this section. 


<!-- Provide links to the diagrams in the appendix at the bottom of the page -->

#### Contributions to team-based tasks:

1. Added the skeletal project portfolio page for my team
   
2. Created the UI mockup for RC4HDB
   
3. Completed the demonstrations for both v1.2 and v1.3. The demonstrations can be found [here](https://docs.google.com/presentation/d/1Rn9v81qwurx_IT_5V9oPZOge1VDZUPD1IxE3DUWkpYg/edit?usp=sharing).


#### Review/mentoring contributions:

I reviewed and commented on the following pull requests, listed in reverse-chronological order:

1. [Add Sample Data #182](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/182)

2. [UG reorganisation #132](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/132)
   
3. [Implement command history #122](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/122)

4. [Implement file commands #98](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/98)

5. [Remove person logic #73](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/73)

6. [Add resident field #72](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/72)
   
7. [Classify commands into subtypes #57](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/57)

8. [Update Use Cases and DG Format #55](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/55)

9. [Update UG and DG #30](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/30)



I also gave guidance to my team by:

1. Providing feedback and enforcing internal code quality standards within our team

2. Highlighting and correcting logical errors in our code 
      
3. Giving advice on how to use streams in our code (in order to make our code more declarative)


#### Contributions beyond the project team:

Beyond the project team, I also participated actively in the forum. These are some threads in which I clarified my doubts:

1. [Dropping (half) a feature #401](https://github.com/nus-cs2103-AY2223S1/forum/issues/401)

2. [Do reducing or adding more constraints on user input allowed in v1.4? #383](https://github.com/nus-cs2103-AY2223S1/forum/issues/383)


<!-- Provide links to the threads here -->

For the practical examination dry run, I also surfaced critical bugs in the other team's product. Some examples of these are:

1. [Unable to add patients with duplicate name #328](https://github.com/AY2223S1-CS2103T-T12-4/tp/issues/328)

2. [Adding a patient task with invalid time for description turns out to be successful #315](https://github.com/AY2223S1-CS2103T-T12-4/tp/issues/315)

3. [Able to add patients with duplicate phone numbers #310](https://github.com/AY2223S1-CS2103T-T12-4/tp/issues/310)

### Appendix for UML diagrams

#### Colour coding guide

![Colour coding guide](../images/ColourCoding.png)

<br>

#### Class diagram for Model component

![Class diagram for Model component](../images/LatestModelClassDiagram.png)

<br>

#### Class diagram for possible extension of Model component with better OOP

![Class diagram with better OOP](../images/UpdatedModelClassDiagram.png)

<br>

#### Diagram illustrating the reference relationships of MainWindow and its subclasses

![MainWindow reference relationships](MainWindowRelationships.png)

<br>

<!-- Embed the diagrams here -->

[comment]: <> (### Contributions to the User Guide:)


<!-- Embed the diagrams here -->
