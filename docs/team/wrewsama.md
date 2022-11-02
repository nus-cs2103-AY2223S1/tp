---
layout: page
title: Andrew Lo Zhi Sheng's Project Portfolio Page
---

### Project: AddressBook Level 3

GithubContact is an address book **integrated with Github's Public API**, targeted at software engineering project
managers to help them communicate and collaborate with their teams smoothly.

Given below are my contributions to the project.

* **New Feature**: Added the `set` command that allows users to both edit an existing attribute(e.g. phone number,
                   timezone, name, etc.) of a person or add a new attribute to that person.
  * What it does: Allows the user to `set` multiple different attributes in 1 command. If a particular attribute already
                  exists, that attribute's value will be updated to the user's input. If it does not exist, the attribute
                  will be added to the user with the user's specified value.
  * Justification:
    * Our users often need to add new attributes to existing persons within the address book. For example, when someone
      in the team creates a Slack account, or when someone's telegram handle is not known until after they were added to
      GithubContact. In these cases, it is necessary for the project manager to be able to add in that information to
      their existing profiles.
    * Our users may also need to change existing attributes. Some possible cases include someone in the team changing
      their email address, or the project manager adding a person with the wrong phone number. In these cases, the user
      needs to be able to edit the person's attributes accordingly.
    * The addition of the set command meets the above two use cases in one convenient command. It serves as an intuitive
      way to handle the attributes of a person.
  * Highlights: This feature involves all the possible attributes of a person in the address book. As our project involved
                the addition of many additional attributes(e.g. role, github account, etc), it was essential for this
                feature to be as extensible as possible, allowing for team members to easily add in the attributes that
                they needed to add.
  * Credits: The `SetPersonDescriptor` nested class was inspired by the `EditPersonDescriptor` from AB3. This provided
             the necessary abstraction to facilitate the easy addition of new attributes.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=wrewsama&breakdown=true)

* **Documentation**:
  * User Guide:
    * Added example screenshots for every command.
    * Added Table of Contents and set up hyperlinks to each section.
    * Added table for attribute prefix aliases.
    * Added table for parameter input formats.
    * Translated content from the draft UG on Google Docs to markdown.
    * Reorganised content (moved Command Summary to the start of the UG, sorted out order of features).
    * Added line breaks to improve pdf formatting.
  * Developer Guide:
    * Added use cases for the Contact Detail Page.
    * Added use cases for the `set` Command.
    * Wrote the implementation for the `set` command.
    * Added sequence diagram for `set` command.
* **Community**:
  * PRs reviewed (with non-trivial review comments): [#139](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/139), 
                                                     [#79](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/79)
  * Reported bugs during PED: [link](https://github.com/wrewsama/ped/issues)