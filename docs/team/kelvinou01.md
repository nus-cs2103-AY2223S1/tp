---
layout: page
title: Kelvin's Project Portfolio Page 
---

# Rapportbook

Financial advisors (FA) rely on a large client base for their job. Rapportbook helps new FAs be effective in managing and engaging their clients, by solving the following problems:  

- Inefficient use of time  
- Motivational issues  
- Tracking information about clients  
- Hard to promote success status online  

Given below are my contributions to the project.  

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kelvinou01&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)  
- **New feature**: Messages
  - What it does: Allows the user to generate personalised messages for clients. The messages are generated using message templates containing the `{name}` key word, which are substituted for the client's full name. 
  - Justification: Helps financial advisors cut down on time spent writing repetitive messages. 
- **Enhancement**: Tagging 
  - What it does: Revamped the tagging system from a `List<String>` to an object-oriented tagging system. 
  - Justification: Helps the user avoid tag duplication (e.g. `family` vs `Family`, `friend` vs `buddy`). Also enhances extensibility: future developers can integrate new features with the tagging system easily.
  - Highlights: Implemented `TagCommandGroup`, which served as a template for future implementations of compound commands (e.g. `tag create`, `reminder create`, `message create`).
- **Tests**: Written test cases for Tagging feature
  - Increased code coverage by 7.36%
- **Contribution to team-based tasks**  
  - Updated issue tracker with deliverables and user stores 
  - Fixed bugs found in the PED [#152](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/152), [#153](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/153), [#154](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/154), [#155](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/155), [#156](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/156)
  - Integrated messages with UI [#150](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/105)
- **Review/mentoring contributions**:  
  - Reviewed and provided nontrivial comments: [#27](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/27), [#52](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/52), [#62](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/62), [#68](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/68)
- **Contributions to the DG**:  
  - Updated section for message command. [#77](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/77)
- **Contributions to the UG**:  
  - Modified sections for message command. [#108](https://github.com/AY2223S1-CS2103T-T13-2/tp/pull/108)
- **Contributions beyond the project team**
  - Reported bugs and suggestions for another team ([link](https://github.com/kelvinou01/ped/issues))
