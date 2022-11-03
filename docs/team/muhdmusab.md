---
layout: page
title: Muhammad Mus'ab Bin Mustaffa's Project Portfolio Page
---

## Project: Financial Advisor Planner

Financial Advisor Planner is a desktop client management application used for financial advisors to manage their clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- ### Code contributed: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=muhdmusab&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=MuhdMusab&tabRepo=AY2223S1-CS2103T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- ### Feature: Added new model `Appointment` and all its attributes.
  - What it does: Each `Appointment` models a scheduled meeting with a client, and it stores attributes including date, time and location.
  - Justification: Financial Advisors(FA) require a method to view all their current appointments with their details, hence an abstraction is needed to model the FA's appointments. 
- ### Feature: Implemented `aa` (add appointment) command. 
  - What it does: Allows the user to add an appointment to the application
  - Justification: FAs require a method to note down all their scheduled appointments, hence adding an appointment for a client is necessary.
- ### Feature: Implemented `Calendar Popup`.
  - What it does: Allows the user to view the full `Appointment` details in the form of a popup whenever the user interacts with a Calendar Button in the Calendar.
  - Justification: As appointment details may potentially be large, e.g. long length of location, the Calendar Display may not fit the full details. Hence, a Calendar Popup is needed to view the full details when in the Calendar Display.  
- ### Feature: Implemented mouse-less `Calendar Navigation`.
  - What it does: Allows the user to toggle the next/previous month in the Calendar Display using the `N` or `B` key respectively and also allows the user to navigate through the appointments in the Calendar Display using either the `Shift`/`Shift` + `Tab` keys or `Up`/`Down`/`Left`/`Right` keys.
  - Justification: FAs require a quick way to navigate the Calendar and also the appointments within the Calendar, hence a mouse-less method is necessary for expert users to navigate quickly. 
- ### Enhancements implemented: Enable searching for multiple fields in `find` command
  - What it does: Allows the user to find for clients according to multiple fields (using an "OR" search).
  - Justification: As FAs may require to perform broad searches for clients according to multiple criteria, this feature is needed.
- ### Contributions to the UG:
  - Added documentation for commands `aa` (add appointment)
  - Added Navigating the User Guide and Tips for reading the User Guide subsections and the Glossary section
  - Updated the formatting of all the sections and subsections
  - Added Ui images for the Graphical User Interface overview, Adding an appointment, Calendar Display, Calendar Navigation subsections.  
- ### Contributions to the DG:
  - Added implementation details and design consideration for `aa` command feature.
    - Added sequence diagram for `aa` command.
  - Added Use Cases for Add a client, Edit client details, Clear Financial Advisor Planner and Add and appointment.
  - Added numerous user stories.
  - Updated BetterModelClassDiagram. 
- ### Contributions to team-based tasks
  - Ensured timely submission of team project deliverables.
  - Reported bugs as issues for better tracking.
- ### Review/mentoring contributions:
  - Reviewed and merged pull requests
  - Helped to resolve merge conflicts in other members’ pull requests
- ### Community
  - Released JAR v1.2 and V1.3.1.
  - Maintained issues and closed milestones.
  - Fixed bugs from PE-D.
