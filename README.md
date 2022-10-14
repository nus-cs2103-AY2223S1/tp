[![CI Status](https://github.com/AY2223S1-CS2103T-W17-4/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2223S1-CS2103T-W17-4/tp/actions)
[![codecov](https://codecov.io/gh/AY2223S1-CS2103T-W17-4/tp/branch/master/graph/badge.svg?token=5KEF39JO86)](https://codecov.io/gh/AY2223S1-CS2103T-W17-4/tp)
![Ui](docs/images/Ui.png)


# Please Hire Us
Are you a CS student struggling to keep track of your internship?
Do you feel tired of using spreadsheets to keep track of your applications?
We have just the right tool for you!

Introducing PleaseHireUs (PHU), the internship tracking application made just for you!

Here are its main features:
* View the status of your individual application and assessment dates at a glance
* View the overall statistics of the status of all your internship applications in a stacked bar chart

Now you will never miss any internship application deadlines or interviews again!
PleaseHireUs has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PleaseHireUs can get your internship management tasks done faster than traditional GUI apps.
We hope you find PleaseHireUs to be very useful in your internship hunt!

* This is a brownfield project adapted from AddressBook 3(AB3) for **Software Engineering (SE) Students**

* The project simulates an ongoing software project for a desktop application used for managing internships details.
  * It is **written in OOP fashion**. It provides a **reasonably well-written** code base (around 6 KLoC) .
  * It comes with a **reasonable level of user and developer documentation**.
* For the detailed documentation of this project, see the **[PleaseHireUs Website](https://ay2223s1-cs2103t-w17-4.github.io/tp/)**.

## Features
- [x] Add internship details
- [x] Sort internships by categories
- [x] Filter relevant internships
- [x] Edit internship details
- [x] View internship details
- [x] Copy internship details to your clipboard easily
- [x] View the overall statistic of the application status of all your internships

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `PleaseHireUs.jar` from [here](https://github.com/AY2223S1-CS2103T-W17-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your application.

4. Double-click the file to start the app. The GUI similar to the above should appear in a few seconds. Note how the app contains some sample data.<br>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
  * **`list`** : Lists all internships.

  * **`add n/Grab p/software engineer pr/ASSESSMENT web/https://www.grab.com/sg/about`** : Adds a new internship to the internship tracker.

  * **`delete 3`** : Deletes the 3rd internship shown in the current list.

  * **`clear`** : Deletes all internships.

  * **`exit`** : Exits the app.
