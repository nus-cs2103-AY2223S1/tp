---
layout: page
title: Kavan Tan's Project Portfolio Page
---

### Project: Gim

Gim is a **desktop app for managing gym exercises, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Gim allows you to keep track your exercises and Personal Records in an efficient way.

Example usages:
* as a tracking tool to keep track of completed exercises
* as a tracking tool to keep track of your personal records
* as a workout generator to generate exercises based on your personal records


Given below are my contributions to the project.

* **New Addition**: Exercise Hashmap ([PR #85](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/85))
  * What it does: Stores exercise data in the form of a hashmap, where the key is the exercise name and the value is a list of exercises with that exercise name.
  * Justification: This hashmap is crucial because it creates precedence for the implementation for many of our other features. For instance, we can now easily find all the exercises (for a certain exercise name) and find the exercise instance with the highest weight amongst them.
  * Highlights: The exercise hashmap was very challenging to implement, because a strong understanding of how data is loaded/saved is necessary. Furthermore, upon tracing the code to learn of this system, it took meticulous effort to ensure that the existing commands/features work in conjunction with the exercise hashmap as well (e.g. add, delete, clear etc.). 

* **New Feature**: Personal Record Listing ([PR #114](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/114) and [PR #117](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/117))
  * What it does: Finds the personal record of certain exercises
  * Justification: With this command, users will be able to keep track of their progression of certain exercises in the gym.
  * Highlights: This command was tricky to implement, because of the nature of how the command is parsed, hence there were many considerations to be made (E.g. Name not in system, multiple similar entries etc.)

* **New Feature**: Confirm flag for 'clear' function([PR #138](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/138))
  * What it does: Mandates that a user types in a 'confirm' parameter before clearing his exercise tracker.
  * Justification: For new users that may be unfamiliar with the 'clear' command, they might not understand what it does and may accidentally clear their database. The confirm flag at least gives the user some form of warning before he performing this clear.
  * Highlights: This command was not very difficult to implement. Having implemented the PR command prior, I already had a good understanding of how the parser works (with the prefixes).

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kavantan&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Allow addition of duplicate exercises ([PR #69](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/69))
    * What I did:
      * Allow users to add 'duplicate' exercises into the system.
    * Justification:
      * Unlike in the original address book (where users cannot add duplicate names), users should be able to add the same exercise into the system to signify that they performed that exercise on multiple occasions.

* **Documentation**:
  * User Guide:
    * Notes about command format ([PR #124](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/124))
    * Useful notations ([PR #195](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/195))

  * Developer Guide:
    * Exercise Model ([PR #98](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/98))
    * Listing of Personal Records ([PR #120](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/120))
