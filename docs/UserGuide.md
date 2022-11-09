---
layout: page
title: User Guide
---

NutriGoals is a desktop application for **NUS students who wish to improve their current lifestyle and adopt a healthier one**.
With NutriGoals, you can **keep track of your diet and calorie consumption**. Studies have shown the benefits of keeping 
track of your daily food consumption – the more consistent you are, the more likely you are to achieve the various 
fitness goals that you have set for yourself! However, without the right tools, tracking what you have consumed is 
tedious and disorganised. With NutriGoals, you can keep track of your consumption quickly and easily, without 
worrying about organising your data.

## Table of contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div markdown="block" class="index">

<div style="page-break-after: always;"></div>

## About the user guide

### Navigating the user guide

* If you are a **new user**, the [Quick start](#quick-start) section provides instructions for you on how to get started.

* Once you have set up NutriGoals, you can check out the [Screen layout](#screen-layout) section to get familiar with the different components of NutriGoals. To learn the basics of using NutriGoals, head over to the [Features](#features) section.

* If you are an **experienced user**, you can refer to the [Command summary](#command-summary) section for an overview of NutriGoals' commands.

* If you have any queries about using NutriGoals, you can check out the [FAQ](#faq) section. 

### Reading the user guide

#### Icons

This section will run you through the icons used in this guide.

| Icon                 | Meaning                                                          |
|----------------------|------------------------------------------------------------------|
| :information_source: | Extra information that you may find useful.                      |
| :exclamation:        | Information you should be aware of to avoid running into errors. |
| :fast_forward:       | Information about future updates to a feature.                   |

<div style="page-break-after: always;"></div>

#### Input parameters

This section provides a summary of the parameters used when inputting commands into the application.

| Prefix | Parameter      | Meaning                                             | Input                                                                        |
|--------|----------------|-----------------------------------------------------|------------------------------------------------------------------------------|
| `n/`   | `FOOD_NAME`    | Name of the food item                               | Alphanumeric value with 1 to 27 characters (inclusive)                       |
| `c/`   | `CALORIE`      | Number of calories                                  | Integer from 0 to 2147483647 (inclusive)                                     |
| `t/`   | `MEAL_TYPE`    | Meal that the food item belongs to                  | `breakfast`, `lunch` or `dinner` (not case sensitive)                        |
| `g/`   | `GENDER`       | Your gender                                         | `M` or `F` (not case sensitive)                                              |
| `w/`   | `WEIGHT`       | Your weight in kg                                   | Integer from 10 to 199 (inclusive)                                           |
| `h/`   | `HEIGHT`       | Your height in cm                                   | Integer from 100 to 219 (inclusive)                                          |
| `i/`   | `IDEAL_WEIGHT` | Your ideal weight in kg                             | Integer from 10 to 199 (inclusive)                                           |
| `a/`   | `AGE`          | Your age in years                                   | Integer from 1 to 99 (inclusive)                                             |
|        | `INDEX`        | Index of the food item in the list displayed        | Integer from 1 to the number of food items in the list displayed (inclusive) |
|        | `DATE`         | Date that the food items were added into NutriGoals | Valid date and in the format `yyyy-MM-dd`                                    |
|        | `LOCATION`     | Location in NUS                                     | `COM2`, `S13`, `S17`, `CLB`, `UHC`, `LT1`, `LT9`, `AS6` (not case sensitive) |

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. (_Unsure how to check?_ Click [here](#faq) for a guide.)

2. Download the latest `nutrigoals.jar` from [here](https://github.com/AY2223S1-CS2103T-T17-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for NutriGoals.

4. Double-click the file to start the application. A screen similar to the one below should appear in a few seconds. Note how the application contains some sample data.
![sampleUi](images/sampleUi.png)

5. Type the command in the command box and press **Enter** to execute it. For example, typing `help` and pressing **Enter** will open 
the help window.

6. Refer to the [Features](#features) below for detailed descriptions of each command.

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-info">

:information_source: **Note:**<br>

* For new users, the application will contain sample data for these 3 days: 15 September 2022, 23 October 2022 and the current day. 
* For example, you can try entering `list 2022-09-15` to view the sample food items on 15 September 2022. 
* To delete food items from all days, enter `clear`.
* If double-clicking `nutrigoals.jar` does not work,
  1. Search for "Command Prompt" or "Terminal" on your computer.
  2. Navigate to the location where `nutrigoals.jar` is saved via the terminal. (_Unsure how to navigate?_ You can try running this in your terminal: `cd Downloads`.)
  3. Run the following in the terminal: `java -jar nutrigoals.jar`.

</div>

<div markdown="block" class="alert alert-warning">

:exclamation: **Warning:**<br>

Upon launching the application, some files responsible for the storage of your data will be created in a folder called `data` located in the same folder as `nutrigoals.jar`. If you are a new user, you are advised not to edit these files. If the changes you made to the data file invalidates its format, NutriGoals will discard all your data and start with an empty data file.

</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Screen layout

![sampleUi](images/layout.png)

<div markdown="block" class="alert alert-info">

:information_source: **About Food List:**<br>

* Food items displayed in Food List are sorted according to meal type, in the order: breakfast, lunch, dinner.
* Upon launching the application, Food List will display food items recorded on the current day.

:information_source: **About Progress Bar:**<br>

* Progress Bar changes based on the total calorie intake for the current day as compared to the target calorie intake.
* To check the total calorie intake for the current day and the target calorie intake, refer to the `review` command [here](#viewing-a-summary-of-the-calorie-intake-progress-review).
* The Progress Bar percentage can go beyond 100%, but note that if the target calorie intake is set to 0, the percentage will be fixed at 100%.
* To set the target calorie intake, refer to the `target` command [here](#setting-a-target-calorie-intake-target).

</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: About the command format:**<br>

* Texts in `UPPER_CASE` in this guide should be replaced with user inputs.
  * E.g. for the function `add n/FOOD_NAME c/CALORIE t/MEAL_TYPE`, you may input `add n/sushi c/300 t/dinner`, where the parameters `FOOD_NAME`, `CALORIE` and `MEAL_TYPE` are replaced by`sushi`,  `300` and `dinner` respectively.
* Parameters in square brackets are optional.
* The parameters can be in any order.
  * E.g. `add n/bubble tea c/200 t/dinner` is the same as `add c/200 n/bubble tea t/dinner`.
* Commands that require an `INDEX` will only accept positive integer values as the `INDEX`.
  * Positive integer values refers to any whole number between 1 and 2147483647.
  * E.g. `edit 2147483648` is an invalid command regardless of the inputs provided.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.
  * E.g. `help 123` will be interpreted as `help`.

</div>

### Getting started

#### Setting up a user profile: `setup`

Sets up a user profile.

Format: `setup g/GENDER w/WEIGHT h/HEIGHT i/IDEAL_WEIGHT a/AGE`

* Sets up the user profile using the information provided by the user.
* `GENDER` can only take 2 values: `M` or `F`.
* `WEIGHT` and `IDEAL_WEIGHT` can only take on integer values between 10 and 199 (in kg).
* `HEIGHT` can only take on integer values between 100 and 219 (in cm).
* `AGE` can only take on integer values between 1 and 99 (in years).

Example:

* `setup g/f w/50 h/165 i/48 a/20` sets up a user profile for a 20-year-old female who is 50kg and 165cm, who has an ideal weight of 48kg.
* `setup g/m w/70 h/175 i/70 a/20` sets up a user profile for a 20-year-old male who is 70kg and 175cm, who has an ideal weight of 70kg.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameters:**<br>

`GENDER` is **not** case-sensitive.

* E.g. `setup g/f w/50 h/165 i/48 a/20` is the same as `setup g/F w/50 h/165 i/48 a/20`.

</div>

<div id="setup-eg">

Example after entering <code class="language-plaintext highlighter-rouge">setup g/m w/70 h/175 i/70 a/20</code>:

<img src="images/setup.png" />

</div>

#### Viewing the user's profile: `profile`

Displays the user's information stored after [setup](#setting-up-a-user-profile-setup).

Format: `profile`

Example after entering `profile`:

* The user's details are listed on the right.

![profile](images/profile.png)

<div markdown="block" class="alert alert-warning">

:exclamation: **Warning:**<br>

This feature is only available provided you have [setup](#setting-up-a-user-profile-setup) a profile beforehand.

</div>

### Managing the food list

#### Adding a food item : `add`

Adds a food item with its calorie content.

Format: `add n/FOOD_NAME c/CALORIE t/MEAL_TYPE`

* Adds a food item into the food list for the current day, together with its calorie content and meal type.
* Each field can only be specified once.
* `FOOD_NAME` should only contain alphanumeric characters and spaces, and should not be blank. Names should also not exceed 27 characters.
* `CALORIE` can only take in a non-negative integer that is less than 2147483648.
* `MEAL_TYPE` can only take on one of three values: breakfast, lunch or dinner.

Example:

* `add n/bread c/100 t/breakfast` adds bread into the food list, and tags it as a breakfast item with 100 calories.
* `add n/bubble tea c/300 t/lunch` adds bubble tea into the food list, and tags it as a lunch item with 300 calories.
* `add n/hotpot c/500 t/dinner` adds hotpot into the food list, and tags it as a dinner item with 500 calories.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameters:**<br>

`MEAL_TYPE` is **not** case-sensitive.

* E.g. `add n/hotpot c/500 t/dinner` is the same as `add n/hotpot c/500 t/dInNeR`.

**:information_source: Adding food items:**<br>

A food item will **not** be added into the food list if the resulting total calorie intake for the day exceeds 2147483647 calories.

</div>

<div markdown="block" class="alert alert-future">

**:fast_forward: Future update:**

* The user will be able to add a food item to a specific date.
* More meal categories will be added, e.g. `snack`.

</div>

#### Deleting a food item : `delete`

Removes a food item from the displayed list of foods.

Format: `delete INDEX`

* Deletes a food item at the specified index.
* The index refers to the index shown in the displayed food list.
* The index **must be a positive** integer.

Example:

* `delete 1` deletes the first item in the food list.
* `list 2022-10-23` followed by `delete 1` deletes the first food item recorded on 23 October 2022. Refer to the `list` command [here](#listing-all-foods-for-a-day-list) for more information.

#### Editing a food item : `edit`

Edits a food item from the displayed list of foods.

Format: `edit INDEX [n/FOOD_NAME] [c/CALORIES] [t/MEAL_TYPE]`

* Edits a food item at the specified index.
* The index refers to the index shown in the displayed food list.
* The index **must be a positive** integer.
* **At least one** of the optional fields must be provided.
* `FOOD_NAME` should only contain alphanumeric characters and spaces, and should not be blank. Names should also not exceed 27 characters.
* `CALORIE` can only take in a non-negative integer that is less than 2147483648.
* `MEAL_TYPE` can only take on one of three values: breakfast, lunch or dinner.

Example:

* `edit 2 n/rice c/300 t/dinner` edits the 2nd food item to rice with 300 calories, and tag it as a dinner item.
* `edit 2 n/noodles` edits the name of 2nd food item to noodles.
* `edit 2 c/100 n/bread` edits the name and calorie content of the first item to bread and 100 respectively.
* `list 2022-10-23` followed by `edit 1 n/sushi` edits the name of the first food item recorded on 23 October 2022 to sushi. Refer to the `list` command [here](#listing-all-foods-for-a-day-list) for more information.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameters:**<br>

`MEAL_TYPE` is **not** case-sensitive.

* E.g. `edit 2 t/dinner` is the same as `edit 2 t/dInNeR`.

**:information_source: About editing food items:**<br>

A food item will **not** be edited if the resulting total calorie intake for the day exceeds 2147483647 calories.

</div>

<div markdown="block" class="alert alert-future">

**:fast_forward: Future update:**

More meal categories will be added, e.g. `snack`.

</div>

<div style="page-break-after: always;"></div>

#### Listing all foods for a day: `list`

Shows a list of all food items and their calories for the specified day (if any).

Format: `list [DATE]`

* Shows the food list for the current day if no `DATE` is supplied.
* `DATE` must be in the format `yyyy-MM-dd` if supplied.

Example:

* `list` shows a list of all food items and their calories for the current day.
* `list 2022-10-23` shows a list of all food items and their calories recorded on 23 October 2022.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameter:**<br>

`DATE` **must be a valid date** for the command to be executed. Note that the year `0000` is considered invalid.

* E.g. `list 2022-02-31` is an invalid command.
* E.g. `list 0000-01-01` is an invalid command. 

</div>

### Managing calorie goals

#### Finding the calorie content of a food item: `find`

Finds the estimated calorie content of a food item.

Format: `find FOOD_NAME`

* By default, only the calorie contents of certain food items are included. Refer to the list of food items [here](#what-are-the-default-food-items-for-the-find-feature).
* If the user has entered a specific food item before, the `find` command will return the average calorie content of that food item. _(This includes entries on previous days, **on top of** the current day's entries.)_

Example:

* `find chicken rice` finds and displays the estimated calorie content of 1 plate of chicken rice.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameter:**<br>

`FOOD_NAME` is **not** case-sensitive.

* E.g. `find rice` is the same as `find rIcE`.

</div>

#### Setting a target calorie intake: `target`

Sets a target calorie intake for the current day.

Format: `target CALORIE`

* `CALORIE` can only take on integer values between 0 and 2147483647.
* The default target calorie intake is set at 2000 calories (this is based on the average Daily Recommended Calorie Intake across both genders).

Example:

* `target 2500` sets a target calorie intake of 2500 calories for the current day and displays the list of foods for the current day.

<div style="page-break-after: always;"></div>

Example after entering `target 2500`:

![target](images/target.png)

<div style="page-break-after: always;"></div>

#### Viewing a summary of the calorie intake progress: `review`

Shows the total calories consumed, the calorie target and the calorie deficit/surplus for the current day. The list of foods for the current day will also be displayed.

Format: `review`

Example after entering `review`:

![review](images/review.png)

#### Suggesting a daily calorie intake: `suggest`

Suggests an estimated daily calorie intake to allow the user to attain his/her ideal weight.

Format: `suggest`

<div markdown="block" class="alert alert-warning">

:exclamation: **Warning:**<br>

This feature is only available provided you have [setup](#setting-up-a-user-profile-setup) a profile beforehand.

</div>

Example after entering `suggest`:

![suggest](images/suggest.png)

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

The suggested amount of calorie shown above is based on the example user profile created [here](#setup-eg).

</div>

### Fitness and health tips

#### Locating the nearest gym in NUS: `locate`

Locates and ranks the gyms from the given location in NUS based on distance.

Format: `locate LOCATION`

* `LOCATION` can only take on one of these values: COM2, S13, S17, CLB, UHC, LT1, LT9, AS6.

Example:

* `locate S17` returns a list of gyms sorted from nearest to furthest from S17.

<div markdown="block" class="alert alert-info">

**:information_source: About the parameter:**<br>

`LOCATION` is **not** case-sensitive.

* E.g. `locate com2` is the same as `locate COM2`.

</div>

Example after entering `locate com2`:

![locate](images/locate.png)

<div markdown="block" class="alert alert-future">

:fast_forward: **Future update:**<br>

More locations in NUS will be added.

</div>

<div style="page-break-after: always;"></div>

#### Suggesting a healthy-lifestyle tip: `tip`

Suggests a random tip to help the user adopt a healthier lifestyle.

Format: `tip`

Example after entering `tip`:

![tip](images/tip.png)

<div style="page-break-after: always;"></div>

### General

#### Viewing help : `help`

Shows a message explaining how to access the help page and information on the usage of commands.

Format: `help`

Example after entering `help`:

![help message](images/helpMessage.png)

<div style="page-break-after: always;"></div>

#### Clearing all stored data: `clear`

Clears all stored data _(excluding gym locations)_ on all days from NutriGoals.

Format: `clear`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

When using the `clear` command, the target calorie intake will be reset to the default 2000 calories.

</div>

#### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

### How do I check if Java `11` is installed in my computer?

Launch your terminal/command prompt and type the following: `java -version`, and hit **Enter**. Java `11` is installed if a similar message to the one below is shown:

```
openjdk version "11.0.16.1" 2022-07-19 LTS
OpenJDK Runtime Environment Zulu11.58+23-CA (build 11.0.16.1+1-LTS)
OpenJDK 64-Bit Server VM Zulu11.58+23-CA (build 11.0.16.1+1-LTS, mixed mode)
```

<div style="page-break-after: always;"></div>

### I do not have Java `11` installed in my computer. How do I install it?

To download Java `11`, visit this [website](https://www.oracle.com/java/technologies/downloads/#java11) and download the appropriate file for your computer's system (such as macOS and Windows).

<div markdown="block" class="alert alert-warning">

**:exclamation: Warning**

For Mac users, if the text in the application appears unreadable, please download and install the Azul build of OpenJDK 11 version found [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).

</div>

### How do I save my data in NutriGoals?

NutriGoals saves data automatically after every command that changes the data. There is no need to save manually.

### Where is my NutriGoals data stored?

When you use the application, a folder named `data` will be created in the same folder as `nutrigoals.jar`. Your 
data is stored in the file named `nutrigoals.json`, which is located in the `data` folder.

### How do I transfer my data to another computer?

You may follow the steps below:

1. Install the application in the other computer.
2. Replace the empty data file created with your original NutriGoals data file.

### Does NutriGoals need an internet connection to work?

No, NutriGoals can still work normally without an internet connection.

### What are the default food items for the [`find`](#finding-the-calorie-content-of-a-food-item-find) feature?

The food items are:

* ban mian
* bubble tea
* chicken rice
* fried rice
* laksa
* nasi lemak
* potato chips
* rice
* wanton noodles
* white bread

<div markdown="block" class="alert alert-future">

:fast_forward: **Future update:**<br>

More default food items will be added.

</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format                                                  | Example                          |
|-------------|---------------------------------------------------------|----------------------------------|
| **Setup**   | `setup g/GENDER w/WEIGHT h/HEIGHT i/IDEAL_WEIGHT a/AGE` | `setup g/m w/70 h/175 i/70 a/20` |
| **Profile** | `profile`                                               | `profile`                        |
| **Add**     | `add n/FOOD_NAME c/CALORIE t/MEAL_TYPE`                 | `add n/donut c/300 t/breakfast`  |
| **Delete**  | `delete INDEX`                                          | `delete 1`                       |
| **Edit**    | `edit INDEX [n/FOOD_NAME] [c/CALORIES] [t/MEAL_TYPE]`   | `edit 2 n/rice c/300 t/dinner`   |
| **List**    | `list [DATE]`                                           | `list` or `list 2022-10-23`      |
| **Find**    | `find FOOD_NAME`                                        | `find chicken rice`              |
| **Target**  | `target`                                                | `target 2000`                    |
| **Review**  | `review`                                                | `review`                         |
| **Suggest** | `suggest`                                               | `suggest`                        |
| **Locate**  | `locate LOCATION`                                       | `locate CLB`                     |
| **Tip**     | `tip`                                                   | `tip`                            |
| **Help**    | `help`                                                  | `help`                           |
| **Clear**   | `clear`                                                 | `clear`                          |
| **Exit**    | `exit`                                                  | `exit`                           |

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Term                                   | Definition                                                                                                                                                                                                   |
|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Calories**                           | Unit of measurement for the amount of energy contained in a food item.                                                                                                                                       |
| **Daily Recommended Calorie Intake**   | The amount of calories recommended by experts for an individual to consume daily. According to Health Promotion Board Singapore, adult males and females should consume 2200 and 1800 calories respectively. |
| **Deficit**                            | The negative difference between the calories consumed in a day and the target daily intake. These calories are _not_ consumed to facilitate weight loss and fat burn.                                        |
| **Food item**                          | Refers to an individual item of food that is consumed.                                                                                                                                                       |
| **Gym**                                | For this user guide, gym refers to a gymnasium used for weight training. Other sports facilities will be considered for future updates.                                                                      |
| **Ideal weight**                       | The self-defined weight that users want to achieve for themselves.                                                                                                                                           |
| **Meal**                               | Comprises of multiple food items that is consumed at a particular time (e.g. breakfast, lunch, dinner, ...).                                                                                                 |
| **Surplus**                            | The positive difference between the calories consumed in a day and the target daily intake. These calories are consumed to facilitate weight gain and muscle growth.                                         |


</div>
