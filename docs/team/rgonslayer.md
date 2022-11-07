# Chew Yew Keng's Project Portfolio Page

 ## Project: Financial Advisor Planner

 Financial Advisor Planner is a desktop client management application used for financial advisors to manage their clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

 Given below are my contributions to the project.

- ### Feature: Added new `Tags`.
  - What it does: Allows Users to add information about their client through easy to view tags. Currently there are 3  Special Tags:
     - Risk Tag to indicate Client's Risk Appetite
     - Plan Tag to indicate Client's current Financial Plan
     - Client Tag to indicate Client's status
  - All Special Tags are colour coded for users to easily interpret the values even without reading the tags fully.
  - Allow users to find and sort based on Special Tags as well
  - Justification: Financial Advisors(FA) require a way to view their clients information easily, pulling out information that can only have fixed values helps to make reading the information easily.
- ### Code contributed: [reposense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rgonslayer&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- 
- ### Enhancements implemented:
  - Add Income field to contacts: Allows users to add yearly income of clients to the app
  - Add Monthly Contributions field to contacts: Allows users to add monthly contributions of clients to the app
  - Enhanced Find command to find by phone number: Allows users to find clients by phone number.
  - Enhanced Find command to find by range of monthly contributions: Allows users to find clients by monthly contribution.
  - Enhanced Find command to find by range of yearly income: Allows users to find clients by yearly income.
  - Enhanced Find command to find by client tags: Allow users to find clients using their client tag.
  - Enhanced Sort Command to sort by client tags: Allow used to sort clients based on their client tag.
  - Abstracted Tag and Special Tag to better represent new categories of tags.
  - Updated Find Command Hint to accurately represent correct command.
  - Updated Help Window to reflect all commands added.
   
- ### Contributions to the UG:
  - Conducted final vetting of UG, ensuring all commands are working and details are coherent.
  - Updated Ui.png
  - Updated helpMessage.png
  - Added missing commands to Command Summary
- ### Contributions to the DG:
  - Added implementation details and design consideration for `add` command feature. 
    - Added sequence diagram for `add` command.
  - Added implementation details and design consideration for `edit` command feature.
    - Added sequence diagram for `edit` command.
  - Added implementation details and design consideration for `delete` command feature.
  - Added implementation details and design consideration for `Model` component.
  - Added implementation details and design consideration for `NormalTag` and `SpecialTags`.
  - Added numerous user stories.
  - Updated ModelClassDiagram.
  - Updated BetterModelClassDiagram.
- ### Contributions to team-based tasks:
  - Ensured timely submission of team project deliverables.
  - Setting up the GitHub team org/repo
  - Reported bugs as issues for better tracking.
  - Maintaining the issue tracker 
    - Add issues for the team and allocated to individual members
  - Release management
    - Released v1.3.2 for the team.
- ### Review/mentoring contributions:
  - Reviewed and merge pull requests.
  - Helped resolve merge conflicts in other members' pull request.
  - Advised other members on how they can architect their solution when they were stuck
  - Helped other members debug.
- ### Community
  - Maintained issues and closed milestones.
  - Fixed bugs from PE-D.
