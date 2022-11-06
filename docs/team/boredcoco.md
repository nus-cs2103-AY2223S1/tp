---
layout: page
title: Faith Chua's Project Portfolio Page
---

### Project: PetCode

PetCode - is a desktop address book application used for coordinating pet deliveries between Pet Suppliers and Pet Customers.

Given below are my contributions to the project.

* **New Feature**: *Filter for relevant Pets*.
    * What it does: *Using specific attributes, the user can find Pets matching those attributes*.
    * Justification: *The user may want to take a quick look at Pets that have specific attributes so that he or she can bulk buy from a Supplier*
    * Highlights: *This feature supports entering multiple fields*.

* **New Feature**: *Filter for relevant Orders*.
    * What it does: *Using specific attributes, the user can find Orders matching those attributes*.
    * Justification: *The user may want to take a quick look at Orders that have specific attributes so that he or she can have an overview of similar orders.*
    * Highlights: *This feature supports entering multiple fields*.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=boredcoco&breakdown=true)

* **Functionality**: Predicates
  * Wrote Predicates (ie. PetNameContainsKeywordsPredicate) for Find and Filter functions
  * Predicates pertain to Buyers, Deliverers, Suppliers, Pets and Orders
  * These predicates help in the implementation of Find and Filter commands.

* **Functionality**: Implemented some parser utility methods for predicates.
  * Wrote PredicateParser class which generates Predicates pertaining to Buyer, Suppliers, Deliverers, Pets or Orders from the user input.

* **Project management**:
    * *Managed testing and writing test code [\#280](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/280#issue-1428340017)*.

* **Enhancements to existing features**:
  * Find a Buyer, Supplier or Deliver instead of simply finding a person.

* **Documentation**:
    * User Guide:
        * Contributed to the "Finding a Contact using Keywords" section of the User guide.
          * This includes the following sub-segments:
            * Finding a Buyer
            * Finding a Supplier
            * Finding a Deliverer
        * Contributed to the "Filter lists by attributes" section of the User guide.
          * This includes the following sub-segments:
            * Filtering Orders
            * Filtering Pets
    * Developer Guide:
        * Contributed to explaining how the Logic works.
        * Added use cases relevant to the features implemented by me.
        * Added the content page of the Developer Guide.

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [/#4](https://github.com/boredcoco/ped/issues/1#issue-1426879221).)
      * Most of the bugs were found through writing JUnit tests.
      * Some manual testing was done to ensure that UI worked fine.
    

