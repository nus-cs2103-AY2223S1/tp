---
layout: page
title: Yuchen's Project Portfolio Page
---

## Project: checkUp

"checkUp" is a desktop address book application used for teaching Software Engineering principles.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 15 kLoC.

Given below are my contributions to the project.

### Code contributed

RepoSense: [link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nehcuy&breakdown=true)  
Personal merged PRs: [link](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?q=is%3Apr+author%3A%40me+is%3Aclosed)

### Features implemented

* Filter list of patients by Ward Number: `get /wn`
    * What it does: Displays filtered list of patients in the query ward number.
    * Justification: Doctors can easily locate a patient by a given ward number.

* Filter list of patients by Floor Number: `get /fn`
    * What it does: Displays filtered list of patients on the query floor number.
    * Justification: Doctors can easily locate a patient by a given floor number.

* Filter list of patients by Name: `get /n`
    * What it does: Allows the user to find patients according to the given name.
    * Justification: Rework existing `find` feature to fit under `get` commands.

* Filter list of patients by Long-term Medication: `get /m`
    * What it does: Allows the user to find patients by long-term medication.
    * Justification: Doctors may want to find patients who are under a type of long-term medication.

### Other code contributions

* Lay out foundations for the `get` commands by implementing the `GetCommand` class and its subclasses.
    * What it does: Allows the user to filter the list of patients according to the given prefix.
    * Justification: This feature improves the product significantly because doctors can easily find a patient by a given category.
    * Highlights: This generic implementation allows future developers to easily add more prefixes for more filtering criteria.
* UI changes to PersonViewPanel by adding in icons for every patient details.

### Contributions to the UG

* Added documentation for the features `get /fn` and `get /wn`.
* Run thorough checking to ensure consistency and correctness of the UG.

### Contributions to the DG

* Added implementation details of the `get` feature, including detailed explanations of each prefixed command of:
    * `get /fn`
    * `get /hw`
    * `get /nok`
    * `get /wn`
* Include detailed sequence diagram of the `GetCommand` class.

### Contributions to team-based tasks

* Enforced good quality code by reviewing PRs containing major code changes
* Ensured the team adheres to strict coding standards.
* Provided suggestions for ideas to improve the product.
* Worked closely with the team lead to ensure completion of tasks before the deadline.

### Review/mentoring contributions

* Actively reviewed [PRs](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?page=1&q=is%3Apr+reviewed-by%3Anehcuy) from other team members.
* Provided insightful comments and suggestions to improve the quality of the code:
    * PR [#65](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/65)
    * PR [#105](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/105)
    * PR [#110](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/110)
    * PR [#187](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/187)
    * PR [#189](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/189)

### Contributions beyond the project team

* Reported bugs and errors on the CS2103T website:
    * Issue [#168](https://github.com/nus-cs2103-AY2223S1/forum/issues/168)
    * Issue [#332](https://github.com/nus-cs2103-AY2223S1/forum/issues/332)
    * Issue [#342](https://github.com/nus-cs2103-AY2223S1/forum/issues/342)
* Actively participated in [forum discussions](https://github.com/nus-cs2103-AY2223S1/forum/issues?q=is%3Aissue+commenter%3Anehcuy) to answer queries from other students:
    * Forum Issue [#326](https://github.com/nus-cs2103-AY2223S1/forum/issues/326)
    * Forum Issue [#376](https://github.com/nus-cs2103-AY2223S1/forum/issues/376)
