---
layout: page
title: Tan Ying Ting's Project Portfolio Page
---

### Project: CLInkedIn

CLInkedIn is a desktop application made for recruiters to manage candidates.
CLInkedIn operates mainly using a Command Line Interface (CLI), but still has a Graphical User Interface (GUI) made with JavaFX for improved user experience.

Given below are my contributions to the project.

* **Add Status attribute of a Person**
    * Added ability to indicate which stage of application the candidate is at. 
    * Purpose: As an applicant tracking system, a crucial feature is to enable recruiters to view the application stage of each candidate at a glance.
    * It is a compulsory attribute of a candidate, as every candidate has an application status. 
    * Implementation specifications: 
      * Status is internally implemented as a String. 
      * Status is implemented as a compulsory attribute under a `Person` object. Thus, a `Status` object class is created and added to `Person` as a field. 

* **Add Rating attribute of a Person**
    * Added ability to indicate a numerical representation of desirability (ie Rating) of candidates. 
    * Purpose: There are some candidates that are more preferable than others. Thus, the Rating feature is added as part of efforts to implement a numerical system to compare between candidates to decide who should progress to the next stage. 
    * Rating is an integer between 1 and 10 inclusive.
    * It is a non-compulsory attribute of a candidate, as the recruiter might not have enough information to rate the candidate at the point of application. 
    * Implementation specifications: 
      * Rating is internally implemented as an Integer. 
      * Rating is implemented as a non-compulsory attribute under a `Person` object. Thus, a `Rating` object class is created and added to `Person` as a field.
      * For candidates with no rating, it is internally represented as having a `Rating` of `0`. However, it is not shown in the external interface.

* **Add Sorting feature**
    * Allows ordering of all candidates from the highest rating to the lowest rating.
    * Purpose: Recruiters can view all candidates in order of desirability.
    * The candidates with no rating will be placed at the bottom, as there is not enough information to compare them with the rest of the applicant pool.
    * Implementation specifications: 
      * A `Comparator<Person>` is created to compare `Rating` between 2 candidates. 
      * All candidates go through the comparator, and a sorted list is obtained. 
      
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ytingtan&breakdown=true)

* **Project management**:
  * PRs reviewed and merged: 
    * [40](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/40), [42](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/42), [52](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/52), [53](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/53), [66](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/66), [68](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/68), [71](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/71), [77](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/77), [83](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/83), [85](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/85), [86](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/86), [103](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/103), [105](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/105), [109](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/109), [138](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/138), [141](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/141), [142](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/142), [205](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/205), [207](https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/207)

* **Documentation**:
    * Readme:
        - Added the project description and the user guide and developer guide link.
        - Added explanation of the target user profile and value proposition.
        - Added example usages.
    * Developers Guide:
        - Added Glossary
        - Added Non-functional requirements
        - Added Rating feature
        - Added Sort feature
    * User Guide: 
        - Created Introduction section with summary table
        - Added Rating feature
        - Added Sort feature 

* **Community**:
    * Contributed to forum discussions, [313](https://github.com/nus-cs2103-AY2223S1/forum/issues/313), [189](https://github.com/nus-cs2103-AY2223S1/forum/issues/189)

* **Tools**:
