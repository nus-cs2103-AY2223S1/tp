---
layout: page
title: Aishwarya's Project Portfolio Page
---

### Project Overview: DevEnable
DevEnable is a product for developers who have to manage different projects spread across multiple GitHub
repositories. It helps developers organize information about different projects they are working on in one place so
that they may prioritize and have an overview. It removes the hassle of having to navigate to our/organization’s
GitHub repo every time and manually check different pages to see which tasks require immediate attention.

Given below are my contributions to the project.

* **Code Contributed**: The link to the tP dashboard can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=aishwarya-hariharan-iyer&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New features implemented**: A summary of the enhancements you implemented.
    * **New Feature**: `Client` class with `add`, `delete` and `edit` commands (Pull requests [\#83](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/83), [\#128](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/128))
        * What it does: The `Client`, which can be a person or an organization, supervises one or more projects.
          Client have attributes such as name, phone number, email and the list of projects they supervise. We can
          add a client to the `ClientList`, edit an existing client by specifying the attributes to be modified and
          delete clients as well.
        * Justification: Each project can be tied to at most one client so that developers can know who is in charge
          of their work. Moreover, the feature can later be expanded to have attributes such as importance of the
          client for the developer. For example, a client who is a potential employer might be more important than a
          friend for whom the developer is developing a project.
        * Highlights: As with all other classes in `DevEnable` clients can be found and filtered based on their
          attributes, sorted, pinned and much more.
        * Credits: The `Client` class is based on the AB3's `Person` class and replaces it in DevEnable. However,
          unlike the `Person` class in AB3, the `Client` has mutable fields and do not have `Address`
          and `Set<Tag>`. Initially, the `Client` package PR contained other `Client` attributes but we chose to
          keep that for a future iterations in interest of UI/UX considerations. (Pull requests [\#72](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/72), [\#66](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/66))

    * **New Feature**: `find` command (Pull requests [\#100](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/100), [\#129](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/129), [\#138](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/138))
        * What it does: Finds items on the list that match the user's search criteria on various attributes and
          displays the corresponding filtered list. It matches keywords with targets on an exact-word basis such
          that at least one word form the target must match exactly with at least one word from the keyword. 
        * Justification: The developer may want to filter and find items on the list based on various parameters.
        * Highlights: The find command takes the intersection of all keywords from different prefixes to increase 
          the specificity of the search. It takes the union of all such keywords of the same (repeated) prefix so as 
          increase the flexibility of use in being able to search for multiple keywords at once under the same field.
          It also validates the inputs entered for each prefix to prevent users from getting confused between no 
          items being listed because of an incorrect input (i.e. such an item can never exist) and because of no 
          such item existing on the list at the time of search.
        * Credits: The `find` command builds on the AB3 `find` functionality.

* **Enhancements to existing features**:
    * Wrote parser tests for edit commands of `Client`, `Project` and `Issue` (Pull request [\#248](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/248))
    * Wrote parser tests for the different `find` commands and for the `AddClientCommand` (Pull requests [\#248](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/248), [\#94](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/94))

* **Documentation**:
    * Contributions to the UG
        + Help set up the initial UG with all content planned in first iteration (Pull request [\#23](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/23))
        + Added documentation and images for client command features: `add`, `delete`, `edit` (Pull request [\#130](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/130))
        + Added documentation and images for find command features under `Client`, `Project` and `Issue`(Pull request [\#145](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/145))
    * Contributions to the DG
        * Write-up for Delete Command Feature consisting of Delete Project Command, Delete Issue Command, and Delete
          Client Command (Pull request [\#236](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/236))
        * Write-up for Find Command Feature consisting of Find Project Command, Find Issue Command, and Find Client 
          Command (Pull request [\#244](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/244))
        * UML Sequence Diagram for deleting a client from the app
        * UML Sequence Diagram for finding a client in the app
        * Design considerations for Delete Command Feature
        * Design considerations for Find Command Feature 
        * Instructions for Manual Testing of all find commands and all client commands (Pull request [\#240](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/240))
        * User Stories for all find commands and all client commands (Pull request [\#115](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/115))

* **Contributions to team-based tasks and beyond**:
    * Reviewed some team pull requests and offered suggestions
    * Created some issues for the weekly deliverables
    * Completed the assigned issues each week, including bug-fixing from the dry-run
    * Helped manage weekly meetings by setting Zoom links and taking notes
    * Reported bugs and suggestions for other teams in the class [(Link)](https://github.com/Aishwarya-Hariharan-Iyer/ped/issues)

