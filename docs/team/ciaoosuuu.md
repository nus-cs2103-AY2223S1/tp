---
layout: page
title: Chen Shun's Project Portfolio Page
---

### Project: Waddle

Waddle is a simple, no-frills travel planning application catered to people who love doing everything on their keyboards.

Given below are my contributions to the project.

* **New Feature**: Add an export to PDF command.
  * What it does: This command allows users to export the itinerary they have planned into an offline PDF document.
  * Justification: This feature improves the product significantly as users usually travel with other people, hence
there is most likely a need to share itinerary with others. This feature allows users to have an offline PDF document
to send to their travel buddies.
  * Highlights: The implementation was challenging as it involved incorporating external libraries to our project, and 
it was necessary to look through the heavy documentation to implement this feature.
  * Credits: This feature heavily relies on [Apache PDFBox](https://pdfbox.apache.org/)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ciaoosuuu&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `Waddle v1.2` and `v1.2.1` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Added `Itinerary` and relevant classes ([#28](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/28))
    * `Country`, `People`, `Description`, `Date`
  * Implemented essential helper methods in `Itinerary` for `plan` and `unplan` commands
    * `Itinerary#removeItem`, `Itinerary#setItem`, `Itinerary#planItem`, `Itinerary#unplanItem`,
  * Added the calculation logic for Budget
  * Edited various command's parser to allow optional fields
  * Constantly improving existing commands and fixing bugs
    ([#77](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/77),
    [#81](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/81),
    [#82](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/82),
    [#88](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/88),
    [#89](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/89),
    [#90](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/90),
    [#93](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/93),
    [#113](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/113),
    [#126](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/126),
    [#190](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/190),
    [#192](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/192),
    [#210](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/210),
    [#234](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/234),
    [#245](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/245),)

* **Documentation**:
  * User Guide:
    * Edited documentation for the features
    * Vetted the documentation for any broken links
  * Developer Guide:
    * Added design details of the Model class
     ([#56](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/56),
      [#57](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/57))
    * Added Model class UML diagram ([#59](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/59))
    * Added Logic and Parser class UML diagram ([#257](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/257))
    * Added implementation details for export to PDF command ([#245](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/245))

* **Team-based tasks**:
  * Enabled assertion in gradle ([#89](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/89))
  * Created milestone v1.4
  * Made use of issue tracker to create issues and assign issues
  * Provided pull request reviews for teammates

* **Community**:
  * Assisted other teams by reporting more than average number of bugs for PE Dry run
  
* **Tools**:
  * Integrated a third party library (Apache PDFBox) to the project
   ([#97](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/97), 
    [#118](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/118),
    [#126](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/126))
