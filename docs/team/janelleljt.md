---
layout: page
title: Janelle Loh Jen Teng's Project Portfolio Page
---
### Overview
**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory efficiently
in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the application via a
CLI, and the application responds with its GUI, created with JavaFX.

My role in this project was to design features related to order management. The following sections will give a more in-depth 
illustration of my contributions as well as the relevant documentation I have added to the user guide and developer guide.

### Summary of Contributions
- **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=melissaharijanto&breakdown=true) <br/>

- **Features and Enhancements implemented**:
  - Feature: **List Order Command**
    - Function: Lists all orders.
    - Purpose: Helps user to see all their orders in one place.
    - Relevant pull request(s): [#66](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/66)
  
  - Feature: **Find Order Command**
    - Function: Finds orders by given keywords or flags.
    - Purpose: Allows the user to search by different fields, helping them better manage closely related orders.
    - Enhancement: The find order command was originally implemented with the ability to only search by items 
    ordered (PR #66), the find order command was enhanced by adding the functionality of searching by more than one field at a time. 
    This enhancement was added in PR #125 and now allows users to search by payment and delivery status as well as by other relevant fields
    such as customer name, address and name of item ordered. 
    - Implications: This provides a more robust search functionality which allows the user to 
    better manage their orders.
    - Relevant pull request(s): 
      - [#66](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/66), [#125](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/125)
  
  - Enhancement: **Price field to items**
    - Function: Field representing the price of a particular item.
    - Purpose: Allows the user to keep track of the sell price and cost price of their inventory items.
    - Relevant pull request(s): [#103](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/103)
    
- **Contributions to the User Guide**:
  - Features added:
    - Listing all orders
    - Finding order(s)
  - Wrote **Using this guide** portion of User Guide.
  - Wrote **Tutorial** portion of the User Guide.
  - Relevant pull request(s): 
    - [#39](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/39), [#136](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/136), [#199](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/199)

- **Contributions to the Developer Guide**:
  - Added implementation details for the find order command as well as a sequence diagram depicting the program flow.
  - Added design considerations for the find order command.
  - Added **user stories** with differing priorities. 

- **Contributions to team-based tasks**:
  - Facilitated team discussions and delegation of work.
  - Provided insights on potential implementations to certain features.
  - Assisted in fixing various bugs detected. 
  - Increased test coverage of the application.

- **Review/mentoring contributions:**: 
  - Provided timely feedback and suggestions to the pull requests of teammates.
  - Suggested alternative implementation details.
            
- **Contributions beyond the project team:**:
  - Assisted in detecting an above average number of bugs for other teams and offered solutions to resolving the bugs 
  detected during the [Practical Exam Dry Run](https://github.com/janelleljt/ped)
