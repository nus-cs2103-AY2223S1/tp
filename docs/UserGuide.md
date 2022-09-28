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

2. Download NutriGoals.jar.

3. Double-click the file to start the app.

4. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Texts in UPPER_CASE are arguments provided by the user.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding the calorie content of a food item : `add`

Adds a food item with its calorie content.
Format: `add FOOD_ITEM CALORIES MEAL_TYPE`

### Deleting a food item : `delete`

Removes a food item from the list of consumed food for the day.

Format: `delete FOOD_ITEM MEAL_TYPE`

### Editing a food item : `edit`

Edits a food item from the list of consumed food for the day.

Format: `edit MEAL_TYPE INDEX UPDATED_FOOD_ITEM CALORIES`

* Edits a food item in the specified list (given by `MEAL_TYPE`) and index.
* The index refers to the index shown in the displayed food lists.
* The index **must be a positive** number.

Example:

* `edit dinner 2 rice 300` edits the 2nd food item for dinner to rice with 300 calories. 

### Showing the daily calorie intake: `review`

Shows the total calories consumed and the list of food items recorded for the day.

Format: `review`

### Exiting the program : `exit`

Exits the program.

Format: `exit`


_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NutriGoals home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

 Action     | Format                             | Examples
------------|----------------------------------- | --------------------------------------------------------------------  
 **Add**    | `add FOOD_ITEM CALORIES MEAL_TYPE` | ` add donut 1000 breakfast`
 **Delete** | `delete MEAL_TYPE FOOD_ITEM`       |  `delete lunch cake`                                                                         
 **Edit**   | `edit MEAL_TYPE INDEX UPDATED_FOOD_ITEM CALORIES`         | `edit dinner 2 rice 300 ` 
 **review** | `review`                           | `review`                                                                  
 **exit**   | `exit`                             |    `exit`                                                                                                     


