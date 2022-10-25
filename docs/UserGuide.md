---
layout: page
title: User Guide
---

NutriGoals is a desktop app that tracks a user’s diet and calorie consumption. Studies have shown the benefits of keeping track of your daily food consumption – the more consistent you are, the more likely you are to achieve the various fitness goals that you have set for yourself! However, without the right tools, tracking what you have consumed can be tedious and at times disorganised. With NutriGoals, you can keep track of your consumption quickly and easily, without worrying about organising your data.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `nutrigoals.jar` from [here](https://github.com/AY2223S1-CS2103T-T17-2/tp/releases).

3. Double-click the file to start the app.

4. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Texts in UPPER_CASE are arguments provided by the user.
* Arguments in square brackets are optional.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding the calorie content of a food item : `add`

Adds a food item with its calorie content.

Format: `add n/FOOD c/CALORIE t/MEAL_TYPE`

* Adds a food item into the food list for the day, together with its calorie content and meal type.
* Each field can only be specified once.
* `MEAL_TYPE` can only take on three values: breakfast, lunch or dinner (case-insensitive).

Example:

* `add n/bread c/100 t/breakfast` adds bread into the food list, and tags it as a breakfast item with 100 calories.
* `add n/bubble tea c/300 t/lunch` adds bubble tea into the food list, and tags it as a lunch item with 300 calories.
* `add n/hotpot c/500 t/dinner` adds hotpot into the food list, and tags it as a dinner item with 500 calories.

### Deleting a food item : `delete`

Removes a food item from the list of consumed food.

Format: `delete INDEX`

* Deletes a food item at the specified index.
* The index **must be a positive** number.

Example:

* `delete 1` deletes the first item in the food list.
* `list 2022-10-23` followed by `delete 1` will delete the first food item recorded on 23 October 2022.

### Editing a food item : `edit`

Edits a food item from the list of consumed food for the day.

Format: `edit INDEX n/UPDATED_FOOD c/CALORIES t/MEAL_TYPE`

* Edits a food item in the list displayed at the specified index.
* The index refers to the index shown in the displayed food lists.
* The index **must be a positive** number.
* Either the `UPDATED_FOOD`, `CALORIES`, or `MEAL_TYPE` must be supplied.

Example:

* `edit 2 n/rice c/300 t/dinner` edits the 2nd food item for dinner to rice with 300 calories.
* `edit 2 n/noodles` edits the name of 2nd food item to noodles.
* `edit 2 c/100 n/bread` edits the name and calorie content of the first item to bread and 100 respectively.
* `list 2022-10-10` followed by `edit 1 n/sushi` will edit the name of the first food item recorded on 2022-10-10
to sushi.

### Listing all foods for a day: `list`

Shows a list of all food items and their respective calories for the specified day (if any).

Format: `list [DATE]`

* Shows the food list for the current day if no `DATE` is supplied.
* `DATE` must be in the format **yyyy-MM-dd** if supplied.

Example:

* `list` shows a list of all food items and their calories for the current day.
* `list 2022-11-27` shows a list of all food items and their calories for 27 November 2022.

### Finding the calorie content of a food item: `find`

Finds the calorie content of a food item for 1 serving in kcal.

Format: `find FOOD`

* Only the calorie contents of some common food items are included.

Example:

* `find chicken rice` finds and displays the calorie content of 1 plate of chicken rice.

### Setting a target daily calorie intake: `target`

Sets a target daily calorie intake.

Format: `target CALORIE`

* `CALORIE` can only take on integer values.

Example:

* `target 2000` targets a daily calorie intake of 2000 calories.

### Viewing a summary of the daily calorie intake: `review`

Shows the total calories consumed, the calorie target and the deficient or excess amount of calories for the day.

Format: `review`

### Setting up a user profile: `setup`

Sets up a user profile.

Format: `setup g/GENDER w/WEIGHT h/HEIGHT i/IDEAL_WEIGHT a/AGE`

* Sets up the user profile using the information provided by the user.
* `GENDER` can only take 2 values: M or F (case-insensitive).
* `WEIGHT` and `IDEAL_WEIGHT` can only take on integer values less than 200 (in kg).
* `HEIGHT` can only take on integer values less than 220 (in cm).
* `AGE` can only take on integer values (in years).

Example:

* `setup g/f w/50 h/165 i/48 a/20` sets up a user profile for a 20-year-old female who is 50kg and 165cm, 
with an ideal weight of 48kg.
* `setup g/m w/70 h/175 i/70 a/20` sets up a user profile for a 20-year-old male who is 70kg and 175cm,
with an ideal weight of 70kg.

### Viewing the user's profile: `profile`

Displays the user's information stored during setup.

Format: `profile`

* A user can only view his/her profile after setup. 

### Suggesting a daily calorie intake: `suggest`

Suggests an estimated daily calorie intake to allow the user to attain his/her ideal weight.

Format: `suggest`

* A user can only get an estimated suggested daily calorie intake after setup.

### Locating the nearest gym in NUS: `locate`

Locates the nearest gym in NUS based on the input location. 

Format: `locate LOCATION`

### Clearing all entries: `clear`

Clears all entries from NutriGoals.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

NutriGoals data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NutriGoals home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format                                                  | Example                          |
|-------------|---------------------------------------------------------|----------------------------------|
| **Help**    | `help`                                                  | `help`                           |
| **Add**     | `add n/FOOD c/CALORIE t/MEAL_TYPE`                      | `add n/donut c/1000 t/breakfast` |
| **Delete**  | `delete INDEX`                                          | `delete 1`                       |
| **Edit**    | `edit INDEX n/UPDATED_FOOD c/CALORIES t/MEAL_TYPE`      | `edit 2 n/rice c/300 t/dinner`   |
| **Find**    | `find FOOD`                                             | `find chicken rice`              |
| **Target**  | `target`                                                | `target 2000`                    |
| **Review**  | `review`                                                | `review`                         |
| **List**    | `list [DATE]`                                           | `list`                           |
| **Setup**   | `setup g/GENDER w/WEIGHT h/HEIGHT i/IDEAL_WEIGHT a/AGE` | `setup g/m w/70 h/175 i/70 a/20` |
| **Profile** | `profile`                                               | `profile`                        |
| **Suggest** | `suggest`                                               | `suggest`                        |
| **Clear**   | `clear`                                                 | `clear`                          |
| **Exit**    | `exit`                                                  | `exit`                           |
