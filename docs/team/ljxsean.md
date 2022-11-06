---
layout: page title: Sean Lam's Project Portfolio Page
title: Sean's Project Portfolio Page
---

### Project: Omnihealth

OmniHealth is a **Patient Management System** tailored to private clinicians to manage patients' details, records and upcoming appointments.
As a private clinician, you can manage and monitor your patient database all in one location.

Given below are my contributions to the project.

* **Enhancements Implemented**
    * Created the record list panel which is displayed in the GUI.
      * What it does: This UI component is linked to a list and automatically updates when the list is changed.
      * Justification: This is essential as each patient stores a list of records that needs to be displayed and changes are constantly being made to it.
    * **New Feature**: Add `findRecord` command.
        * What it does: Enables the user to find a record that matches the search parameters.
        * Justification: Omnihealth aims to manage the patient database of a clinic which can be assumed to have countless number of records.
      This command allows the user to effortlessly find the desired record out of all the other records.
    * **New Feature**: Add `showALl` command.
        * What it does: Clears the filter parameters set by `findRecord` command to display all records.
        * Justification: Makes Omnihealth easier to use since `find` and `rfind` commands can now be easily undone. Without this feature, 
      undoing a find command required multiple steps.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ljxsean&breakdown=true)

* **Documentation**:
    * User Guide:
        * Added documentation for the commands `rfind` and `showall`. [\#143](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/143)
    * Developer Guide:
        * Added implementation details of the `rfind` command. [\#170](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/170)
        * Added details of how to conduct manual testing.
        * Updated user stories, glossary, NFRs as well as product scope to better describe Omnihealth.
        * Updated UML component diagrams to reflect the current implementation. [\#145](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/145)
        * Formatted the Developer guide for a better reading flow.
        
* **Contributions to team-based tasks**:
    * Implemented the functionality of displaying either the record list screen or patient list screen which Omnihealth
      is built on.
    * Improved code coverage. [\#153](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/153)
    * Maintained the issue tracker
    * Documented the various steps to manual testing in the Developer guide. [\#170](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/170)   
    
* **Project Management**:
    * Used Github issue tracker.
    * Followed the branching workflow.
    * Contributed to meeting discussions
    
* **Review/mentoring contributions**:
    * Reviewed team members PR with helpful responses: [\#95](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/95), [\#72](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/72), [\#67](https://github.com/AY2223S1-CS2103T-T14-3/tp/pull/67)

* **Contributions beyond project team**:
    * Helped other teams by reporting bugs found [(ped)](https://github.com/LJXSean/ped/issues)
