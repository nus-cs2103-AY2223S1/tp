---
layout: page
title: Kevin Chang's Project Portfolio Page
---

# Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.** The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

## Contributions

### Enhancements Implemented

- **New Features**

  - Implemented Employee ID system

    - Created an `EmployeeId` class to represent the unique ID of an employee in the company. The implementation can be seen [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/model/person/EmployeeId.java).
    - Use Case: User can distinguish employees through a distinct number.
    - Justification: This attribute of the employee completely removes any ambiguity of `Person` objects, as well as any possibility of duplicate employees recognised during commands.
    - Highlights: As a field with restrictions placed due to its uniquenes, it lacks modular depth as compared to other basic `Person` fields such as `Name` or `Phone`. It is also much harder to ensure smooth usage and testing due to its widespread implications.

  - Built home page
    - Created a simple home page with our logo.
    - Use Case: User will see the home page on startup, and for commands that do not need to display extra information.
    - Justification: Provides a nicer viewing for the user, and a smoother usage as opposed to blank screens.
    - Highlights: It was surprisingly difficult to adjust the positioning of the elements.

- **Existing Features**

  - Upgraded `find` function

    - Created a `Department` class for another dimension of categorization. Then modified `find` to build a search filter that can comprise of name, department, and/or job position.
    - Use Case: User can search for employees with varying levels of specificity.
    - Justification: With this command, users of Coydir will be able to look up information on specific employees or group of employees quickly.
    - Highlights: Relatively easier to implement than the Employee ID system.

  - Upgraded help window

    - Changed the default help window to provide a command summary. Additionally, instead of a button that copies the URL into register, the button opens the User Guide in the default browser of the user.
    - Use Case: User can now use the help window as a quick reference, and if need be, open up the User Guide for further information.
    - Justification: The help window now provides more utility, and accessing the User Guide is made faster and simpler.
    - Highlights: Implementing the function to open the User Guide in browser was slightly complex, and needed some workarounds. Building the interface for the help window was also particularly challenging.

  - Refactor `edit` function

  - Fixed multiple bugs (Refer to #150, #151, #232, #233).

### Contributions to the UG

- Restructured the document into current segments
- Added documentation for following parts:
  - Introduction and Logo
  - Employee Profile
  - `find` command
  - `edit` command
  - `help` command

### Contributions to the DG

- Edited implementation details for:
  - `find`
- Added implementation details for:
  - [To Be Updated]

### Contributions to team-based tasks

- Helped to create and distribute issues.
- Contributed to manual testing of application to find bugs.
- Oversaw backend development and upheld code quality.

### Reviewing Contributions

- Pull Requests reviewed:
  - [#40] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/40)
  - [#41] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/41)
  - [#42] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/42)
  - [#43] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/43)
  - [#49] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/49)
  - [#50] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/50)
  - [#51] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/51)
  - [#54] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/54)
  - [#57] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/57)
  - [#63] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/63)
  - [#64] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/64)
  - [#66] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/66)
  - [#67] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/67)
  - [#69] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/69)
  - [#71] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/71)
  - [#74] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/74)
  - [#82] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/82)
  - [#83] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/83)
  - [#87] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/87)
  - [#91] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/91)
  - [#92] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/92)
  - [#109] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/109)
  - [#110] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/110)
  - [#111] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/111)
  - [#112] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/112)
  - [#113] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/113)
  - [#114] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/114)
  - [#115] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/115)
  - [#118] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/118)
  - [#119] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/119)
  - [#120] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/120)
  - [#121] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/121)
  - [#122] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/122)
  - [#123] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/123)
  - [#126] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/126)
  - [#130] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/130)
  - [#133] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/133)
  - [#152] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/152)
  - [#221] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/221)
  - [#223] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/223)
  - [#227] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/227)
  - [#230] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/230)
  - [#231] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/231)
  - [#235] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/235)
  - [#236] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/236)
  - [#237] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/237)
  - [#239] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/239)
