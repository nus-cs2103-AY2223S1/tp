---
layout: page
title: Ryan Tan's Project Portfolio Page
---

# Project: CS2103 tP

## Overview

checkUp is a desktop patient medical record management system. The user interacts with it using a CLI, and it has a GUI 
created with JavaFX. It is written in Java, and has about 15 kLoC.

## Summary of contributions

### Code contributed

[RepoSense report](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ryan-tan00&breakdown=true)

### Enhancements implemented

* Get patients by their patient type: `get /inp` & `get /outp`
  * Description: Filters patients by their patient type (inpatient & outpatient)
  * Justification: Easier for hospital staff to identify which patients are staying in the hospital

* Get the past appointments of patients: `get /appt`
  * Description: Obtains all past appointments of a specified patient
  * Justification: Hospital staff can view the medical history of a patient

* Implemented sorting for past appointments
  * Description: Past appointments will be arranged from the most recent to oldest when added to the list of past 
                 appointments
  * Justification: Instead of sorting appointments by date whenever `get /appt` is called, sorting the appointments as 
                   it is added makes it more efficient as we foresee more `get /appt` commands than `appt` commands

* Implemented the ability to click on patients in the person list panel
  * Description: Clicking on a person card in the person list panel updates their details on the person view panel
  * Justification: Easier access to patient's details, rather than typing in the `view` command

### Contributions to the UG

* Added documentation for `get /inp` & `get /outp`
* Added documentation for `edit`
* Added 'restrictions' column to the table for `add` and `edit` command
* Added a `get` command summary table

### Contributions to the DG

* Added documentation for `get /inp` & `get /outp`
  * Created a sequence diagram for `get /inp` to illustrate how the `Logic` component executes a `get /inp` command
* Added documentation for `get /appt`
* Edited `Logic` component to reflect the logic flow of `GetCommand`
* Edited `get` command implementation
  * Edited overarching Sequence Diagrams for `get` command (only prefix & prefix + parameters)
  * Edited `get /appton` implementation to fit the format of other `get` command implementations
* Add use cases for `get /inp`, `get /outp`, `get /appt` and sorting of appointments when added

### Contributions to team-based tasks

* Edited all previous mentions to AB3 to checkUp in the DG
* Looked through UG to ensure consistent formatting for all parts
  * correcting weird phrasing
  * removing any typos
  * ensuring consistent formatting

### Review/mentoring contributions

* [Reviewed other member's PRs](https://github.com/AY2223S1-CS2103T-W16-3/tp/pulls?q=is%3Apr+reviewed-by%3Aryan-tan00)
* Left comments on PRs, giving advice to fellow members
  * PR [#126](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/126),
       [#130](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/130),
       [#131](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/131),
       [#184](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/184),
       [#199](https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/199)

