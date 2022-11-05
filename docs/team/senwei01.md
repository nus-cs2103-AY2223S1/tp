---
layout: page
title: Lee Sen Wei's Project Portfolio Page
---

## Project: Financial Advisor Planner

Financial Advisor Planner is a desktop client management application used for financial advisors to manage their clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- ### Code contributed: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=senwei01&tabRepo=AY2223S1-CS2103T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- ### Feature: Added the `sort` feature.
  - What it does: The `sort` feature allows user to naivgate and manage their contacts easily and efficiently. 
  - Currently, there are 6 keywords that we can `sort` by:
    - `Appointment`: sorts the clients by appointment dates.
    - `ClientTag` : sorts the clients by whether they are a potential or current client.
    - `Income` : sorts the client by their income.
    - `Monthly` : sorts the client by their monthly contribution.
    - `Name` : sorts the client by alphabetical order.
    - `RiskTag` : sorts the client by their risk appetite.
  - Justification: FAs require a method to identify contacts based on a given keyword, for example they may want to know who are the clients with higher income so that they can better suggest an investment plan.
- ### Enhancements implemented: Enhanced searching by investment plan, normal tags and risk tags for `find` command. Furthermore, I was the first one who implemented this enhancement so I had to ensure OOP was done well so my teammates that were enhancing this `find` command further could just follow my structure and modify it to the given condition.
  - What it does: Allows the user to find for clients according to investment plan, normal tags and risk tags.
  - FAs may require to search for clients based on how high their risk appetite are, what investment plans are they on etc.
- ### Enhancements implemented: UI changes.
  - What it does: 
    - Changed risk tag and investment plan tag colours and shape
    - Revamped the help window to include all the basic commands.
- ### Contributions to the UG:
    - Main person for the documentation of the UG. 
    - Added documentation for command `sort`.
    - Updated the formatting of all sections and subsections.
    - Ensure all sections of the user guide is coherent by making sure all the commands were updated regularly.
    - Added UI image for the quick start section of the UG.
    - 
- ### Contributions to the DG:
- ### Contributions to team-based tasks
  - Ensured timely submission of team project deliverables.
  - Reported bugs as issues for better tracking.
- ### Review/mentoring contributions:
  - Reviewed and merge pull requests.
  - Helped resolve merge conflicts in other members' pull request.
- ### Community
- Maintained issues and closed milestones.
- Fixed bugs from PE-D.
