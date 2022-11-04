---
layout: page
title: Tan Yuan Zheng's Project Portfolio Page
---

### Project: FinBook
to be added soon

### Overview

to be added soon

### Summary of Contributions

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yztangent&breakdown=true)

* **Enhancements implemented**:
    * **Update feature**: Change edit command to add onto tags, plans and notes instead of overwriting them
      * What is does: allow the user to add onto the current tags, plans, and notes of a client more easily 
      * Justification: Often users would want to add tags, plans or notes to their clients as they engage with them. Hence, it makes more sense for the edit comamnd to add onto current tags, plans, and notes, as it would be a huge hassle to have to copy over the client's the current tags, plans, and notes on top of what they plan to add.
      * Highlight:
        * To allow the user to remove tags, plans, and notes that are no longer relevant, the remove feature was added.
        * Any tags, plans or notes that the client already possess are ignored to prevent duplicates.
    * **New feature**: Remove command 
        * What it does: Allows the user to remove tags, plans, and notes from clients.
        * Justification: Lets the user remove unwanted tags, plans, and notes from clients, as the edit command now adds onto current tags, plans and notes without overwriting them.
        * Highlights:
          * The user can remove any amount of tags, plans or notes in any combination.
          * Any tags, plans, or notes passed into the command that is not present on the client will simply be ignored.
    * **Update feature**: Add client to include meeting location attribute, and refactor all meeting related fields into the encapsulating meeting field
      * What is does: allow the user to add meeting location(optional) to the client
      * Justification: Meeting location is an import information for users to capture as well on top of the next meeting date. Hence, this field has been added to be stored as part of a client's attributes in Finbook.
      * Highlight:
        * Meeting location is made optional because there could be instances where a meeting location has not been
        settled among the user and client.
        * Meeting location will be automatically deciphered as online or in-person by Finbook using URL recognition. Any addressed that is passed to Finbook that is a valid URL will be categorised as an online meeting.


* **Contributions to the UG**:
    * Add remove command and its usage details to the UG
    * Update list of prefixes to include meeting location
    * Update edit command to reflect its new behaviour
    * Add labelled diagrams to illustrate the difference between hidden and shown mode and highlight its toggle button
    * Add labelled diagrams to highlight the  toggle button to switch between dark and light mode

* **Contributions to the DG**:
    * Add user stories for the meeting location feature
    * Add user stories for the remove feature
    * Update UML class diagram to include meeting location

* **Contributions to team-based tasks**:
    * to be added soon

* **Review/mentoring contributions**:
    * to be added soon

* **Contributions beyond the project team**:
    * Evidence of helping others
        * to be added soon
    * Evidence of technical leadership
        * to be added soon

### Contributions to the Developer Guide (Extracts)

to be added soon

### Contributions to the User Guide (Extracts)

to be added soon
