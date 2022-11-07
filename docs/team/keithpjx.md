---
layout: page
title: Keith's Project Portfolio Page
---

### Project: Survin

Survin is a desktop application for surveyors to use to keep track of people they have surveyed. The surveyor can easily follow up with people they have surveyed for additional information or for confirmation. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.
* **New Feature**: Update model to include 5 new attributes of a person
  * What it does: Allows the application to keep track of 5 new attributes in a person.
  * Justification: Since the application is for surveyors to keep track of their surveyees, there is a need to add more attributes to a person that surveyors will need.

* **Updated Feature**: `add` command
  * What it does: Allows the user to add new surveyees using a text command.
  * Justification: While the feature was already included in v1.0, it had to be updated to suit the updated model.
  * Highlights: Updated the command to accept these new attributes as parameters.

* **Updated Feature**: `edit` command
  * What it does: Allows the user to edit existing surveyees using a text command.
  * Justification: While the feature was already included in v1.0, it had to be updated to suit the updated model.
  * Highlights: Updated the command to accept these new attributes as parameters.

* **New Feature**: Change themes
  * What it does: Allows the user to switch between a light and dark theme using either the GUI or a text command.
  * Justification: Users can have a more comfortable view of the GUI be it in the day or at night.
  * Highlights: Created a light themed stylesheet that the application can switch to when commanded and implemented the logic to switch stylesheets.

* **New Feature**: Compacted and Expanded PersonCards
  * What it does: Allows the user to hide more detailed information of surveyees. This can be done from the GUI or a text command.
  * Justification: Users do not have to be afraid of shoulder surfers. It also serves as a way to declutter the GUI.
  * Highlights: Implemented the logic to hide more detailed information of surveyees.

* **New Feature**: `append` and `unappend` commands
  * What it does: Allows the user to add on or remove surveys and tags from existing surveyees.
  * Justification: Users could only add surveys or tags by typing out all existing ones and then the new ones. Users could only remove surveys or tags by deleting all of them then adding back the ones they did not want to remove. This was very tedious.
  * Highlights: Implemented 2 new commands to append and unappend surveys and tags.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=keithpjx&breakdown=true)

* **Project management**:
  * Did most of the administrative work

* **Documentation**:
  * User Guide:
    * Added documentation for the features:
      * `edit` [\#57](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/57)
      * `theme` and `toggle-list-mode` [\#145](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/145)
      * `append` and `unappend` [\#151](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/151)
    * Added 'Parameters format' [\#204](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/204)
  * Developer Guide:
    * Added implementation description for the features:
      * Changing themes [\#210](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/210)
      * Compactable and expandable PersonCard [\#123](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/123)
    * Added use cases for, Change theme, Show/Hide more detailed information, Append surveys/tags to a surveyee, and Unappend surveys/tags to a surveyee [\#210](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/210)
    * Added instructions for manual testing for, Appending surveys/tags to a surveyee, Changing theme, and Showing/Hiding more detailed information [\#210](https://github.com/AY2223S1-CS2103-F13-2/tp/pull/210)
