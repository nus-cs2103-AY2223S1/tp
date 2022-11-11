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
- **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=janelleljt&breakdown=true) 

- **Features and Enhancements Implemented**:
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
      - Highlights: Implementing a versatile predicate was challenging as I needed to take into account the different fields and consider each possible case for the find order command.
    - Implications: This provides a more robust search functionality which allows the user to 
    better manage their orders.
    - Relevant pull request(s): [#66](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/66), [#125](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/125)
  
  - Enhancement: **Price field to items**
    - Function: Field representing the price of a particular item.
    - Purpose: Allows the user to keep track of the sell price and cost price of their inventory items.
    - Relevant pull request(s): [#103](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/103) 
    
- **Contributions to the User Guide**:
  - Features added:
    - Listing all orders
    - Finding order(s)
  - Wrote **Using this guide** and **Tutorial** portion of User Guide with the intention of it being as user-friendly as possible.
  - Relevant pull request(s): [#39](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/39), [#136](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/136), [#199](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/199) 

- **Contributions to the Developer Guide**:
  - Added implementation details and design considerations for the find order command as well as an activity and sequence diagram depicting the program flow.
    - Relevant pull request(s): [#206](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/206), [#224](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/224)
  - Added user profile, value proposition and user stories with differing priorities. 
    - Relevant pull request(s): [#43](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/43)
  - Added instructions for testing for `findo`, `listo` and `sorto` commands.
    - Relevant pull request(s): [#224](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/224) 

- **Contributions to team-based tasks**:
  - Facilitated team discussions and delegation of work.
  - Provided insights on potential implementations to certain features.
  - Assisted in fixing various bugs detected. 
    - Relevant pull request(s): [#117](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/117), [#199](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/199)
  - Wrote test cases to test coverage of the application.
    - Contributed to the following test files: `FindOrderCommandParserTest`, `PriceTest`, `AddressTest`, `EmailTest`, `NameTest`, `PhoneTest`, `OrderDateTimeComparator` and `OrderMatchesFlagsAndPrefixPredicateTest`
    - Relevant pull request(s): [#209](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/209) 
  - Updated `AboutUs.md` file with relevant roles and responsibilities.
    - Relevant pull request(s): [#206](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/206)
- **Review/mentoring contributions**:
  - Provided timely feedback and suggestions to the pull requests of teammates.
    - Relevant pull requests: [#211](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/211)
  - Suggested alternative implementation details. 
            
- **Contributions beyond the project team**:
  - Assisted in detecting an above average number of bugs for other teams and offered solutions to resolving the bugs 
  detected during the [Practical Exam Dry Run](https://github.com/janelleljt/ped).
