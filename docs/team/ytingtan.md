---
layout: page
title: Tan Ying Ting's Project Portfolio Page
---

### Project: CLInkedIn

CLInkedIn is a desktop application made for recruiters to manage candidates.
CLInkedIn operates mainly using a Command Line Interface (CLI), but still has a Graphical User Interface (GUI) made with JavaFX for improved user experience.

Given below are my contributions to the project.

* **New Feature**: Status
    * What it does: 
      * Indicates which stage of application the candidate is at. 
      * As an applicant tracking system, a crucial feature is to enable recruiters to view the application stage of each candidate at a glance.
      * Some example statuses include: Application Received, OA in Progress, Shortlisted for Interview, Accepted, Rejected
      * It is a compulsory attribute of a candidate, as every candidate has an application status. 
      * The Status attribute can be used in two ways: 
        1. Adding a new candidate `add n/John Doe p/10384280 e/john@mail.com s/Application Received` adds a new candidate with name `John Doe`, phone number `10384280`, email `john@mail.com`, and status of `Application Received`.
        2. Editing the status of a candidate `edit 1 s/OA in Progress` edits `Status` field of the 1st candidate in CLInkedIn to `OA in Progress`.
    * Implementation specifications: 
      * Status is internally implemented as a String. 
      * Status is implemented as a compulsory attribute under a `Person` object. Thus, a `Status` object class is created and added to `Person` as a field. 

* **New Feature**: Rating
    * What it does: 
      * A numerical representation of desirability of candidates. 
      * There are some candidates that are more preferable than others. Thus, the Rating feature is added as part of efforts to implement a numerical system to compare between candidates to decide who should progress to the next stage. 
      * Rating is an integer between 1 and 10 inclusive.
      * It is a non-compulsory attribute of a candidate, as the recruiter might not have enough information to rate the candidate at the point of application. 
      * The Rating attribute can be used in three ways: 
        1. Adding a new candidate `add n/John Doe p/10384280 e/john@mail.com s/Application Received rate/9` adds a new candidate with name `John Doe`, phone number `10384280`, email `john@mail.com`, status of `Application Received` with rating of `9`.
        2. Editing the rating of a candidate `edit 2 rate/9` edits `Rating` of the 2nd candidate in CLInkedIn to `9`.
        3. Deleting rating of a candidate `deleterate 1` deletes rating of the 1st candidate in CLInkedIn. 
    * Implementation specifications: 
      * Rating is internally implemented as an Integer. 
      * Rating is implemented as a non-compulsory attribute under a `Person` object. Thus, a `Rating` object class is created and added to `Person` as a field.
      * For candidates with no rating, it is internally represented as having a `Rating` of `0`. However, it is not shown in the external interface.

* **New Feature**: Sort 
    * What it does: 
      * Order all candidates from the highest rating to the lowest rating.
      * Recruiters can view all candidates in order of desirability.
      * Example: `sort` will return the list of candidates sorted from the highest to the lowest rating. 
      * The candidates with no rating will be placed at the bottom, as there is not enough information to compare them with the rest of the applicant pool.
    * Implementation specifications: 
      * A `Comparator<Person>` is created to compare `Rating` between 2 candidates. 
      * All candidates go through the comparator, and a sorted list is obtained. 


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ytingtan&breakdown=true)


* **Project management**:
  * PRs reviewed and merged: 
    * [\#40] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/40), [\#42] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/42), [\#52] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/52), [\#53] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/53), [\#66] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/66), [\#68] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/68), [\#71] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/71), [\#77] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/77), [\#83] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/83), [\#85] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/85), [\#86] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/86), [\#103] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/103), [\#105] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/105), [\#109] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/109), [\#138] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/138), [\#141] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/141), [\#142] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/142), [\#205] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/205), [\#207] (https://github.com/AY2223S1-CS2103T-T13-3/tp/pull/207)

* **Enhancements to existing features**:


* **Documentation**:
    * Readme:
        - Added the project description and the user guide and developer guide link.
        - Added explanation of the target user profile and value proposition.
        - Added example usages.
    * Developers Guide:
        - Added Glossary
        - Added Non-functional requirements.
        - Added Status, Rating, Sort feature.
    * User Guide: 
        - Added Status, Rating, Sort feature

* **Community**:


* **Tools**:
