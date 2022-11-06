---
layout: page
title: Ng Shi Jun's Project Portfolio Page
---

### Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.** The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

### Summary of Contributions

---

### Code Contributed

#### Enhancements Implemented

- **New Features**
  - Implemented `view` feature
    - Created a `ViewCommand` class to handle the logic, `ViewCommandParser` class to handle the parsing of the user input, `PersonInfo.fxml` to organize the information to be presented, and `PersonInfo` class to adjust the necessary settings of the corresponding _fxml_ file.
    - Use Case: User can view the more detailed information of an employee on the right panel.
    - Why: If we include all the information of an employee on the left list panel, the information will be too cluttered, which will result in a bad user experience.
  - Implemented `view-department` feature
    - Created a `ViewDepartmentCommand` class to handle the logic, `ViewDepartmentCommandParser` class to handle the parsing of the user input, `DepartmentInfo.fxml` to organize the information to be presented, and `DepartmentInfo` class to adjust the necessary settings of the corresponding _fxml_ file.
    - Use Case: User can view a summary of a department, which contains the total number of employee in that department, how many employees are currently available, how many employees are currently on leave, and the performance rating of each employee.
    - Why: This will give HR a high level overview of the overall performance of a particular department, which can assist in making decisions such as department restructuring and employee promoting.
  - Implemented `light` and `dark` theme
    - Created a `LightTheme.css` file that contains the css information for light theme. Made adjustment to the `MainWindow` class so that the program can properly capture users' mouse click and change the theme accordingly.
    - Use Case: User can switch to a theme of their preference.
    - Why: Improve users' experiences.
  - Implemented a right panel
    - Created a right panel to display information from `view` and `view-department` command. Update the necessary parsers to send a signal to `MainWindow` class whenever there is an update to the information displayed. The right panel will then update to display the latest information.
    - Why: To ensure that all the information displayed is up-to-date. So users don't need to manually update, which brings about more convenience to the users.
  - Enhanced GUI settings
    - Make changes to GUI settings. Specifically:
      - Change the position of the `ResultDisplay` and `CommandBox`.
      - Change the minimum width and height of the program so that the content won't be blocked when users try to resize the window.
      - Slightly increase the height of the `ResultDisplay` to contains more result, hence less scrolling required.
    - Why: Enhance user experience
- **Existing Features**
  - Enhanced the `PersonListCard` class
    - Remove unnecessary information that were previously displayed on each of the `PersonListCard`.
    - Why: This enhances the user experience by removing additional unimportant information.

- **Contributions to the UG**
  - Added documentation for following parts:
    - `view` command.
    - `list` command.
    - `view-department` command.

- **Contributions to the DG**
  - Added implementation details for:
    - `view`.
    - [To Be Updated].

- **Reviewing Contributions**
  - Pull Requests reviewed:
  - [#82](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/82)
  - [#83](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/83)
  - [#92](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/92)
  - [#126](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/126)
  - [#244](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/244)
