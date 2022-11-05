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
  * Description: Filters patients by their patient type (inpatient & outpatient).
  * Justification: Easier for hospital staff to identify which patients are staying in the hospital.

* Get the past appointments of patients: `get /appt`
  * Description: Obtains all past appointments of a specified patient.
  * Justification: Hospital staff can view the medical history of a patient.

* Implemented sorting for past appointments
  * Description: Past appointments will be arranged from the most recent to oldest when added to the list of past 
                 appointments
  * Justification: Instead of sorting appointments by date whenever `get /appt` is called, sorting the appointments as 
                   it is added makes it more efficient as we foresee more `get /appt` commands than `appt` commands.

### Contributions to the UG



### Contributions to the DG

### Contributions to team-based tasks

### Review/mentoring contributions

### Contributions beyond the project team
