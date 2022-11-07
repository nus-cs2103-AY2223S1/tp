---
layout: page
title: Arnav Aggarwal's Project Portfolio Page
---

### Project: GithubContact

GithubContact is an address book **integrated with Github's Public API**, targeted at software engineering project
managers to help them communicate and collaborate with their teams smoothly.

Given below are my contributions to the project.

* **New Feature**: Added `Telegram` and `Slack` contact classes
  * What it does: Allows user to add new contact types
  * Justification: This feature allows user to keep track of more useful contact methods in the software engineering industry, which is in line with our product goals to empower product managers in managing their teams.
  * Highlights: 
    * This feature was part of refactoring the contact classes we had from AB3, and required understanding the way telegram and slack usernames are structured. Changes to all commands and Person classes had to be made as well to accomodate for these new contact methods.
    * This feature allows people to easily extend our app as we have created a new `Contact` interface that has all the necessary methods to work with the app. They would simply need to specify the validation methods and RegEx, and make the corresponding changes to the commands thereafter.
* **New Feature**: Allow user to pull user information from GitHub
  * What it does: Allows user to pull information of GitHub user from the API service
  * Justification: This feature improves the user experiences because it eases the obtaining of a lot of information, such as the profile picture, their location, their email and their repositories.
  * Highlights: 
    * This feature required knowledge in utilising Unirest to make API calls, handling the numerous exceptions that can come up from the call.
    * The Unirest calls required custom handling of API rate limit errors.
    * This feature required a wrapper that could easily process the resultant JSON output from the API call if successful, which is written in separate `UserInfoWrapper` and `UserRepoWrapper` classes. This also has fallback logic for when fields are not present or are null, in case of incomplete response.
    * This feature required the parsing of the information retrieved from `UserInfoWrapper` and `UserRepoWrapper` classes into the actualy `User` and `Repo` classes that we hold locally for each person.
    * This feature was also written while keeping in mind that the program should work if there is no internet access on the machine.
    * Image storage is done in an `images` directory under the same folder as the data file to keep storage folders consistent.
* **New Feature**: Added hyperlinks to contacts, GitHub profile and GitHub repositories
  * What it does: Allows the user to easily access the contacts, profile or repositories by simply clicking the hyperlink in the UI.
  * Justification: This feature improves the user experiences because it allows users to get in touch with their contacts instantly, as the hyperlink leads directly to the communication method with the user. They can also access all relevant GitHub information without having to copy from our app, then going to GitHub and searching for the information.
  * Highlights: 
    * This fearture required the custom handling of errors in the parsing of the URL to be opened, as well as any errors the OS might face in opening the browser.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=arnav-ag)
* **Project management**:
  * Managed release `v1.3beta` ([1 releases](https://github.com/AY2223S1-CS2103T-W08-2/tp/releases)) with 
    ChangeLog.
* **Documentation**:
  * User Guide
    * Added Documentation for `add`, specifying GitHub parameters.
    * Added instructions for `set`, specifying GitHub parameters
    * Added brief command usages and examples for every command in order to allow team to modify further.
  * Developer Guide
    * Added implementation detail (e.g. explanation and UML diagram) for GitHub API calls
    * Updated Person and User's model UML Diagram (Added extra attributes such as `user`, `repo`, etc.)
* **Community**:
  * PRs reviewed with in-depth discussion (with non-trivial reviews): [144](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/144), [57](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/57), [79](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/79), [81](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/81)
  * Reported bugs and suggestions for other teams in the class: [PED](https://github.com/arnav-ag/ped/issues)

- **Tools**
  - Integrated `Unirest` for API calls into the project [141](https://github.com/AY2223S1-CS2103T-W08-2/tp/pull/141)
