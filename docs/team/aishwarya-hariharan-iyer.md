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

* **Code Contributed**: The link to the tP dashboard can be found [here](https://nus-cs2103-ay2223s1.github.
  io/tp-dashboard/?search=aishwarya-hariharan-iyer&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16
  &timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New features implemented**: A summary of the enhancements you implemented.
    * **New Feature**: `Client` class with `add`, `delete` and `edit` commands
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
          and `Set<Tag>`

    * **New Feature**: `find` command
        * What it does: Finds items on the list that match the user's search criteria on various attributes and
          displays the corresponding filtered list.
        * Justification: The developer may want to filter and find items on the list based on various parameters.
        * Highlights: The intersection of the search criteria for different attributes are taken. For multiple
          arguments given to each parameter, the union of all arguments provided, with each argument taken for a
          tight-match, is taken.
        * Credits: The `find` command builds on the AB3 `find` functionality.

* **Enhancements to existing features**:
    * E.g. changes to GUI, wrote additional tests, etc.

* **Documentation**:
    * Contributions to the UG
        * Which sections did you contribute to the UG? (to be added soon)
    * Contributions to the DG
        * Which sections did you contribute to the DG? Which UML diagrams did you add/updated? (to be added soon)

* **Contributions to team-based tasks**:
    * (to be added soon)

* **Community**:
    * Review/mentoring contributions
        * Links to PRs reviewed, instances of helping team members in other ways
          (to be added soon)
    * Contributions beyond the project team
        * Evidence of helping others (e.g. responses on forums, bugs reported in other team's products)
        * Evidence of technical leadership (e.g. sharing useful info on forum)
          (to be added soon)
