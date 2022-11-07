---
layout: page
title: Shan Hashir's Project Portfolio Page
---

### Project: GithubContact


GithubContact is an address book **integrated with Github's Public API**, targeted at software engineering project
managers to help them communicate and collaborate with their teams smoothly.

Given below are my contributions to the project.

* **New Feature**: Added the UI for the Help Page.
    * What it does: Shows the user with the commands that can be used and a short description in a tabular form when
     the user enters the help command.
    * Justification: The commands and their description in a tabular form that can be accessed at any time just by
     entering help command as opposed to reading through the user guide greatly improves the user experience of our 
    app.
    * Credits: [Tex](https://github.com/Tex-Tang) helped change the font of the commands in the help page 
   and enable navigation between panels in the app.
* **Enhancements to existing features**: Added the ability to find contacts by `name`, `address`, `role`, `github user` 
and `tags` and also implemented fuzzy search which is immune to minor spelling errors for the find command.
  * What it does: Allows the user to find contacts using various fields (`name`, `address`, `role`, `github user`
    and `tags`)
  
  * Justification: Previously, the find command only supported the name field. This was not very useful, since each 
   person in the contact book has several attributes linked to them. By allowing the user to find contacts by several
   fields, the find command becomes a lot more powerful and user friendly.
  
  * Highlights: The implementation is done such that the find command is as extendable as possible as our project 
   involved the addition of many additional attributes (e.g. role, github account, etc). This allows our team to add
   whatever attributes they want. The implementation also involved the use of the Levenshtein Algorithm to find the 
   similarity between to strings in order to do the fuzzy search. This was a challenging yet fun exercise.
  * Credits: The algortihm to find the levenshtein distance between 2 strings was adapted from the GeeksForGeeks page:
    bit.ly/3DSiDPA. The `PersonMatchesKeywordsPredicate` class was inspired from the `NameContainsKeywordsPredicate` 
    class from AB3. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sh4nh&breakdown=true)
* **Documentation**: 
  * User Guide: 
    * Added documentation for the `find` feature
  * Developer Guide: 
      * Added use cases for the `find` Page.
      * Wrote the implementation for the `find` command.
      * Added sequence diagram for `find` command.
      * Wrote design considerations for `find` command.
* **Contributions to team-based tasks**: 
  * Helped create the issue labels for the team repository's issue tracker.
  
* **Community**:
    * Reported bugs during PED: [link](https://github.com/sh4nH/ped/issues)


