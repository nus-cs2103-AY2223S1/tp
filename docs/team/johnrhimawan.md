---
layout: page

title: John's Project Portfolio Page

---

### Project: InTrack

InTrack is a desktop application that helps Computer Science students who are applying for multiple internships to keep
track of their progress and deadlines across these applications. It is optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.
* **Code contributed**: 

    Can be accessed by this link: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=johnrhimawan&breakdown=true)

* **Enhancements implemented**:

    * New feature: `status` field (PR: [#53](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/53))
      * What it does: Allow users to be able to have their status of an internship application, where it could be Progress, Offered, or Rejected
      * Justification: Initially, InTrack did not have a status field. All internships were not distinguishable by status.
        This made it difficult for a user to determine their status of an internship application. It would be much more
        organized if they were given the chance to quickly identify the current status of each internship for them to know if they need to pay attention to a given internship application.
      * Highlights: Implementing this feature requires in-depth understanding how the various components in the
        application function and interact. It also requires significantly more modifications to the existing model.
    * New feature: `task` field (PR: [#80](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/80))
      * What it does: Allows users to be able to have their task associated to an internship application, being able to
        specify its description and deadline.
      * Justification: Initially, InTrack did not have a task field. Internships did not contain tasks which made it
        inconvenient for the user. Each internship application brings a set of tasks that need to be accomplished by the
        user. The task field allows the user to document what deliverables they need to do for each internship directly
        via InTrack.
      * Highlights: Implementing this feature requires in-depth understanding how the various components in the
        application function and interact. It also requires significantly more modifications to the existing model and json files.
    * New feature: `addtask` command (PR: [#80](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/80))
      * What it does: Allow users to be able to add their tasks associated to an internship application.
      * Justification: The user should be allowed to add a new task to a list of tasks associated to an internship application.
        This makes it easy for the user to keep adding new deliverables that they need to accomplish for a given internship
        application all through InTrack.
      * Highlights: Implementing this feature requires in-depth understanding how the various components in the
        application function and interact. It also requires significantly more modifications to the existing model and json files.
    * New feature: `website` field (PR: [#99](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/99))
      * What it does: Allows users to be able to add the websites from the various job portals which contain additional 
        details on the internship application, including various responsibilities and requirements.
      * Justification: The user should be allowed to add a website to their internship application since nowadays job search
        is done entirely online. With a hyperlink implemented, this makes it much easier for the user to immediately go
        to the relevant website when needing to recall the necessary information about the internship.
      * Highlights: Implementing this feature requires in-depth understanding how the various components in the
        application function and interact. It also requires significantly more modifications to the existing model.
    * Enhancement: `sort` command (PR: [#140](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/140))
      * What it does: Update the OOP of sort so that it can be used to sort by salary or time. Previously it was only by time.
      * Justification: The user should be allowed to use sort in various ways, they will likely be interested in comparing
        the different internships they have applied to. One factor is salary, and this would allow users to make a more informed decision on which internship to prioritize or accept.
      * Highlights: Implementing this feature requires in-depth understanding how the various components in the
        application function and interact. It also requires significantly more modifications to the existing model and OOP.
    * New feature: `mail` command (PR: [#140](https://github.com/AY2223S1-CS2103T-T11-2/tp/pull/146))
      * What it does: Launches the default mail app of the user and initializes a new email with the recipient email address being the email address of the company applied.
      * Justification: InTrack already has an email field. If the user wants to contact the company for updates, they
        would have to manually copy the email address to email the relevant person. It would be more convenient for the
        user if they could directly send an email after using InTrack.
      * Highlights: Implementing this feature requires in-depth understanding on how to launch the default mail application from a different application.

* **Contributions to the UG:**:
  * Added complete documentation for the following features: `addtask`, `sort`, `mail`, `deltask`
  * Updated documentation for other features.
  * Updated the missing features in the command summary
* **Contributions to the DG**:
  * Added explanation, implementation, diagrams and design considerations for the following feature: `addtask`.
* **Contributions to team-based tasks**:
  * Maintained the issue tracker and milestones for the team
  * Created the feature demo for v1.4
  * Managed release: [v1.1](https://github.com/AY2223S1-CS2103T-T11-2/tp/releases/tag/v1.1)
* **Review/mentoring contributions:**:
  * Reviewed team members' PRs
  * Helped in debugging others code
* **Contributions beyond the project team**:
  * Contributed in forum discussion (Examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/374)
, [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/352))
  