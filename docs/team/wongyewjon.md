---
layout: page
title: Wong Yew Jon's Project Portfolio Page
---

## Project: Financial Advisor Planner

Financial Advisor Planner is a desktop client management application used for financial advisors to manage their clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- ### Feature: Add a new model `Calendar`
  - What it does: Each `Appointment` models a scheduled meeting with a client, and it stores attributes including date, time and location.
  - Justification: Financial Advisors(FA) require a method to view all their current appointments with their details, hence an abstraction is needed to model the FA's appointments.
  - ### Code contributed:
  - ### Feature: Added new model `Calendar` and its logic.
    - What it does: It displays  all the appointments for the clients in a calendar  format in another tab.
    - Justification: Financial Advisors(FA) require a method to view all their current appointments with ease, hence a calendar is needed to make scheduling of appointments simple.
  - ### Feature: Implemented the `Jump Box` 
    - What it does: It allows user to jump to the month that the user wants to view in the Calendar directly.
    - Justification: Financial Advisors(FA) may require to view the appointments in the far future, so this feature allows them to view it immediately without having to use the `Next Button` multiple times.
  - ### Feature: Implemented the `Next Button`
    - What it does: It shows the next month in the Calendar.
    - Justification: It allows user to see the appointments scheduled for the next month to plan ahead for future appointments.
  - ### Feature: Implemented the `Prev Button`
    - What it does: It shows the previous month in the Calendar.
    - Justification: It allows user to see the appointments scheduled for the previous month, this is usually to return to the current month after using the `Next Button`.
  - ### Enhancements implemented: 
    - Added new UI for the `Appointment` model and their attributes .
  - ### Contributions to the UG:
    - Added documentation for the Calendar features section.
  - ### Contributions to the DG:
    - Added implementation details and design consideration for `Calendar` features.
    - Added activity diagrams for `Calendar` features.
    - Added Use Cases for Add a client, Edit client details, Clear Financial Advisor Planner and Add and appointment.
  - ### Contributions to team-based tasks
    - Reported bugs as issues for better tracking.
  - ### Review/mentoring contributions:
    - Reviewed and merged pull requests
    - Helped to resolve merge conflicts in other members’ pull requests
  - ### Community
    - Fixed bugs from PE-D.

