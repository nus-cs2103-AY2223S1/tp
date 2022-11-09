---
layout: page
title: Developer Guide
---

## Table of contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div markdown="block" class="index">

<div style="page-break-after: always;"></div>
## **Acknowledgements**

This project is based on the [AddressBook-Level3](https://github.com/se-edu/addressbook-level3) project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2223S1-CS2103T-T17-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>

For sequence diagrams, the lifeline of an object should end at the destroy marker (X). However, due to a limitation of 
PlantUML, the lifeline reaches the end of the diagram.

</div>

<div style="page-break-after: always;"></div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

A quick overview of main components and how they interact with each other is given below.

#### Main components

**`Main`** has two classes
called [`Main`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/Main.java)
and [`MainApp`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

#### Interactions between components

The *Sequence Diagram* below shows how the components interact with each other in the scenario: user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent external components being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

**API**: [`Ui.java`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

<div style="page-break-after: always;"></div>

The UI consists of a `MainWindow` that is made up of the following parts:
* `ResultDisplay`
* `CommandBox`
* `HelpWindow`
* `ProgressBarController`
* `StatusBarFooter`
* `FoodListPanel`

All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in the matching `.fxml` files that
are found in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component:

* executes user commands using the `Logic` component.
* listens for changes to data in `Model` so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, as the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays the `Food` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `NutriGoalsParser` class to parse the user command.
2. This results in a `Command` object (specifically: an object of one of its subclasses, e.g., `AddCommand`) which is
   executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a food).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

<div style="page-break-after: always;"></div>

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `NutriGoalsParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specified command name e.g., `AddCommandParser`). The `XYZCommandParser` object then uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which `NutriGoalsParser` then returns as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/model/Model.java)

<img src="images/ModelClassDiagram.png" width="550" />


The `Model` component:

* stores NutriGoals' data, which includes the following:
  * a `FoodList` object that contains all `Food` objects
  * a `List` of `Location` objects, each representing a NUS gym location
  * a `User` object that contains the user's profile details (e.g. height, weight, ideal weight, gender, age, BMI)
  * a `Calorie` object that represents the target calorie intake
  * a `FoodCalorieList` object that contains information about the calorie contents of default food items
  * a `TipList` object that contains `Tip` objects
* stores the currently 'selected' `Food` objects (e.g., results of a search query) as a separate _filtered_ list. This filtered list is exposed to outsiders as an unmodifiable `ObservableList<Food>` that can be 'observed' e.g. the UI can be bound to this list so that the UI updates automatically whenever the data in the list changes.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (since the `Model` represents data entities in the domain, they
   make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T17-2/tp/blob/master/src/main/java/seedu/nutrigoals/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component:

* can save both NutriGoals data and user preference data in the json format, and read them into corresponding objects.
* inherits from both `NutriGoalsStorage` and `UserPrefStorage`. This means that it can be treated as either format (when the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`).

<div style="page-break-after: always;"></div>

### Common classes

Classes used by multiple components can be found in the `seedu.nutrigoals.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes the noteworthy details on how certain features are implemented.

### Setup feature

#### Implementation

The setup mechanism is facilitated by `SetupCommand`, which extends `Command`. It overrides the following
operation:

* `SetupCommand#execute()`: Sets up the user profile.

#### Example usage

Given below is an example usage scenario of how the setup mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user inputs `setup h/170 w/65 i/60 g/m a/20`, which calls `LogicManager#execute()`. `NutriGoals#parseCommand()`
is subsequently called, creating a `SetupCommand` object.

Step 3. The `SetupCommand` created is executed by `SetupCommand#execute()`.

Step 4. `SetupCommand#execute()` calls the following methods from `Model`:

* `Model#setUserDetails()`
* `Model#getUserDetails()`

Step 5. `SetupCommand#execute()` returns a `CommandResult` which displays the user's information.

The following diagram illustrates how the `setup` operation works.

![SetupSequenceDiagram](./images/SetupSequenceDiagram.png)

### Profile feature

#### Implementation

The profile mechanism is facilitated by `ProfileCommand`, which extends `Command`. It overrides the
following operation:

* `ProfileCommand#execute()`: Retrieves and displays the user's information.

#### Example usage

Given below is an example usage scenario and how the profile mechanism behaves at each step.

Step 1. The user launches the application on 19 October 2022.

Step 2. The user executes `profile`, which calls `LogicManager#execute()`. `NutriGoals#parseCommand()` is subsequently called, creating a `ProfileCommand` object.

Step 3. The `ProfileCommand` created is executed by `ProfileCommand#execute()`.

Step 4. `ProfileCommand#execute()` calls the following methods from `Model`:

* `Model#isUserCreated()`
* `Model#getUserDetails()`

Step 5. `ProfileCommand#execute()` returns a `CommandResult` which displays the user's information.

The following activity diagram outlines the process when the user executes the `ProfileCommand`:

![ProfileCommandActivityDiagram](./images/ProfileCommandActivityDiagram.png)

### Edit feature

#### Implementation

The edit mechanism is facilitated by `EditCommand`, which extends `Command`. It overrides the following operation:

* `EditCommand#execute()`: Edits the food name, meal type or calories associated with the food at the specified index.

<div style="page-break-after: always;"></div>

#### Example usage

Given below is an example usage scenario and how the edit mechanism behaves at each step.

Step 1. The user launches the application on 19 October 2022. Suppose the foods added for the day are:

1. bread: 100 calories, breakfast
2. milk tea: 300 calories, lunch
3. sushi: 500 calories, lunch

Step 2. The user executes `edit 2 n/honey milk tea c/310`, which calls `LogicManager#execute()`.
`NutriGoals#parseCommand()` is subsequently called, creating an `EditCommandParser` object.
`EditCommandParser#parse()` is then called to make sense of the arguments supplied by the user.

Step 3. The `EditCommand` is created, and then executed by `EditCommand#execute()`.

Step 4. `EditCommand#execute()` calls the following methods from `Model`:

* `Model#setFood(foodToEdit, editedFood)` replaces `foodToEdit` to `editedFood`
* `Model#updateFilteredFoodList(predicate)` filters the food list based on the given predicate

Step 5. `EditCommand#execute()` returns a `CommandResult` with the following result displayed:

```
Food item edited!

Food name: honey milk tea
Calorie content: 310 calories
Meal type: lunch
```

<div style="page-break-after: always;"></div>

The following diagram illustrates how the `edit` operation works:

![EditSequenceDiagram](./images/EditSequenceDiagram.png)

### List feature

#### Implementation

The list mechanism is facilitated by `ListCommand`, which extends `Command`. It overrides the following operation:

* `ListCommand#execute()`: Shows a list of all food items and their respective calories for the specified day (if any).

#### Example usage

Given below is an example usage scenario and how the list mechanism behaves at each step.

Step 1. The user launches the application on 19 October 2022. NutriGoals initially displays all foods added on the
current day, 19 October 2022.

Step 2. The user executes `list 2022-07-29`, which calls `LogicManager#execute()`.
`NutriGoals#parseCommand()` is subsequently called, creating a `ListCommandParser` object.
`ListCommandParser#parse()` is then called to make sense of the date argument supplied by the user.

Step 3. A `ListCommand` object is created with an `IsFoodAddedOnThisDatePredicate` object. The predicate is initialised with 29 July 2022 as the date.

The following object diagram illustrates this.

![ListObjectDiagram](./images/ListObjectDiagram.png)

Step 4. `ListCommand` is then executed by `ListCommand#execute()`, which calls the following methods from `Model`:

* `Model#updateFilteredFoodList(IsFoodAddedOnThisDatePredicate)` filters the food list for foods added on 29 July 2022
* `Model#isFilteredFoodListEmpty()`

Step 5. The filtered food list is shown to the user and `ListCommand#execute()` returns a `CommandResult` with a message
indicating the successful execution of the `list` command.

The following activity diagram summarizes what happens when a user executes the `list` command:

![ListActivityDiagram](images/ListActivityDiagram.png)

### Find feature

#### Implementation

The find mechanism is facilitated by `FindCommand`, which extends `Command`. It overrides the following operation:

* `FindCommand#execute()`: Looks through all previously consumed food items and searches for those whose name matches the predicate. Returns the average calorie content for that particular food item if it is inside the list. If it is not in the list, checks if the food item is in the default list provided, and returns the suggested calorie content from the default list.

#### Example usage

Given below is an example usage scenario and how the find mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user executes `find Banana`, which calls `LogicManager#execute()`.
`NutriGoals#parseCommand()` is subsequently called, creating a `FindCommandParser` object.
`FindCommandParser#parse()` is then called to make sense of the food name supplied by the user.

Step 3. A `FindCommand` object is created and then executed by `FindCommand#execute()`.

Step 4. The `FindCommand#execute()` then calls the following methods from `Model`:

* `Model#getFoodCalorieList()`
* `Model#getUnfilteredFoodList()`

Step 5. The `FindCommand#execute()` returns a `CommandResult` that displays the calorie content of the food item specified by the user (which in this case is Banana).

<div style="page-break-after: always;"></div>

The following diagram illustrates how the `find` operation works:

![FindSequenceDiagram](images/FindSequenceDiagram.png)

### Target feature

#### Implementation

The target mechanism is facilitated by `TargetCommand`, which extends `Command`. It overrides the following operation:

* `TargetCommand#excecute()`: Sets the calorie target for the day.

#### Example usage

Given below is an example usage scenario and how the target mechanism behaves at each step.

Step 1. The user launches the application today.

Step 2. The user executes `target 2103`, which calls `LogicManager#execute()`. 
`NutriGoals#parseCommand()` is subsequently called, creating a `TargetCommandParser` object.
`TargetCommandParser#parser()` is then called to make sense of the arguments supplied by the user.

Step 3. The `TargetCommand` is created, and then executed by `TargetCommand#execute()`.

<div style="page-break-after: always;"></div>

Step 4. The `TargetCommand#execute()` calls the following methods from `Model`:
* `Model#setCalorieTarget(calorieTarget)`
* `Model#getCalorieTarget()`

Step 5. `TargetCommand#execute()` returns a `CommandResult` with the following result displayed:

```
Your calorie target set for today: 2103 calories
```

The following diagram illustrates how the `target` operation works:

![TargetSequenceDiagram](./images/TargetSequenceDiagram.png)

### Review feature

#### Implementation

The review mechanism is facilitated by `ReviewCommand`, which extends `Command`. It overrides the following operation:

* `ReviewCommand#execute()`: Calculates the total calories, the calorie target and the calorie deficit/surplus for the current day.

<div style="page-break-after: always;"></div>

#### Example usage

Given below is an example usage scenario and how the review mechanism behaves at each step.

Step 1. The user launches the application on 19 October 2022. Suppose the foods added for the day are:

1. bubble tea: 232 kcal
2. chicken rice: 702 kcal
3. wanton noodles: 409 kcal

Step 2. The user executes `review` command, creating a `ReviewCommand` object. 

Step 3. The `ReviewCommand` created is executed by `ReviewCommand#execute()`. 

Step 4. `ReviewCommand#execute()` creates an `IsFoodAddedOnThisDatePredicate` object with 19 October 2022 as the date.

Step 5. `ReviewCommand#execute()` then calls the following methods from `Model`:

* `Model#updateFilteredFoodList(IsFoodAddedOnThisDatePredicate)` filters the food list for foods added on 19 October 2022
* `Model#getTotalCalorie()`
* `Model#getCalorieTarget()`
* `Model#getCalorieDifference()`

Step 6. `ReviewCommand#execute()` returns a `CommandResult` with the following information displayed to the user:

```
Your calorie intake for today: 1343 calories
Your calorie target for today: 2000 calories
You should consume 657 more calories to reach your calorie target for today!
```

<div style="page-break-after: always;"></div>

The following sequence diagram illustrates how the `review` operation works:

![ReviewSequenceDiagram](images/ReviewSequenceDiagram.png)

### Suggest feature

#### Implementation

The suggest mechanism is facilitated by `SuggestCommand`, which extends `Command`. It overrides the following operation:

* `SuggestCommand#execute()`: Calculates a suggested amount of calories a user should consume per day.

#### Example usage

The following illustrates what objects are created when `suggest` is executed.

![SuggestObjectDiagram](images/SuggestObjectDiagram.png)

Given below is an example usage scenario and how the suggest mechanism behaves at each step.

Step 1. The user launches the application today.

Step 2. The user executes `suggest` command, creating a `SuggestCommand` object.

Step 3. The `SuggestCommand` created is executed by `SuggestCommand#execute()`.

Step 4. `SuggestCommand#execute()` then calls the following methods from `Model`:

* `Model#isUserCreated()`
* `Model#calculateSuggestedCalorie()`

Step 5. `SuggestCommand#execute()` returns a `CommandResult` that displays an estimated suggested amount of calories the user should consume per day.

The following activity diagram outlines what happens when a user executes the `suggest` command:

![SuggestActivityDiagram](images/SuggestActivityDiagram.png)

### Locate gym feature

#### Implementation

The locate gym mechanism is facilitated by `LocateGymCommand`, which extends `Command`. It overrides the following operation:

* `LocateGymCommand#execute()`: Sorts and returns a list of gyms in NUS that are closest to the given location.

#### Example usage

Given below is an example usage scenario and how the locate gym mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user inputs `locate CLB`, which calls `LogicManager#execute()`. `NutriGoals#parseCommand()` is subsequently called, creating a `LocateGymCommand` object.

Step 3. The `LocateGymCommand` created is executed by `LocateGymCommand#execute()`.

Step 4. `LocateGymCommand#execute()` calls the following method from `Model`:

* `Model#getNusGymLocations()`

The following activity diagram outlines what happens when a user executes the `locate` command:

![LocateCommandActivityDiagram](images/LocateCommandActivityDiagram.png)

<div style="page-break-after: always;"></div>

### Tip feature

#### Implementation

The tip mechanism is facilitated by `TipCommand`, which extends `Command`. It overrides the following operation:

* `TipCommand#execute()`: Returns a randomly-drawn tip to help users adopt a healthier lifestyle.

#### Example Usage

Given below is an example usage scenario and how the tip mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user inputs `tip`, creating a `TipCommand` object.

Step 3. The `TipCommand` object created is executed by `TipCommand#execute()`.

Step 4. `TipCommand#execute()` calls the following method from `Model`:

* `Model#getTip()`

The following activity diagram outlines what happens when a user executes the `tip` command:

![TipCommandActivityDiagram](images/TipCommand.png)

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile:**

* NUS students
* Wishes to get healthier or fitter
* Wishes to track their daily calorie intake
* Wants to know how many calories they should consume daily
* Wants to know how many calories are in their food
* Is reasonably comfortable using CLI apps

**Value proposition:** Help users manage and calculate their calorie intake quickly, get recommendations on their daily calorie intake, learn about the calorie content of food items, find the nearest gyms in NUS and learn useful tips in order to meet their health and fitness goals

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a            | I want to                                                         | So that I can…                                                        |
|----------|-----------------|-------------------------------------------------------------------|-----------------------------------------------------------------------|
| `* * *`  | careless user   | delete a wrongly added meal                                       | fix my food records easily                                            |
| `* * *`  | user            | add my daily calorie intake                                       | know how much I am eating                                             |
| `* * *`  | food enthusiast | calculate my daily calorie intake                                 | know how nutrient dense my food is                                    |
| `* * *`  | careless user   | edit a meal wrongly recorded                                      | change my food records easily                                         |
| `* * *`  | forgetful user  | find my list of foods consumed for any day                        | get a better understanding of my eating habits                        |
| `* *`    | user            | key in the calorie intake for each type of food                   | remember how many calories a particular food I have consumed contains |
| `* *`    | user            | specify my body composition                                       | find out how many calories I should be consuming based on my profile  |
| `* *`    | user            | calculate my BMI                                                  | know if my current weight is in a healthy range                       |
| `* *`    | sedentary user  | get information on healthy lifestyle habits                       | adopt a more active lifestyle                                         |
| `* *`    | nus student     | find the nearest gym in school based on where I am at             | know where to go if I want to exercise                                |
| `* *`    | user            | get a suggested daily calorie intake based on my body composition | know what would be a reasonable calorie target                        |
| `* *`    | user            | find the calorie content of a food item                           | know how many calories I am consuming for a particular food           |
| `*`      | forgetful user  | receive information about my calorie deficiency / excess          | know if I should consume more / less calories                         |

### Use cases

For all use cases below, the **System** is the `NutriGoals` application and the **Actor** is the `user`, unless specified otherwise.

#### UC-1: List food items
**Use case:** List food items

**MSS**

1. User requests to list the food items consumed on a particular date.
2. NutriGoals shows the list of food items consumed on the specified date.

   Use case ends.

**Extension**

* 1a. No food item recorded on the specified date.

    * 1a1. NutriGoals displays a default message.

      Use case ends.

* 1b. The date provided is invalid.

    * 1b1. NutriGoals shows an error message.
  
      Use case ends.

#### UC-2: Add a meal
**Use case:** Add a meal

**MSS**

1. User enters the command to add a food item.
2. NutriGoals shows the new list of food items.

   Use case ends.

**Extension**

* 1a. The information provided is invalid.

    * 1a1. NutriGoals shows an error message.

      Use case ends.

#### UC-3: Delete a meal
**Use case:** Delete a meal

**MSS**

1. User requests to <ins>list the meals recorded (UC-1)</ins>.
2. NutriGoals shows a list of meals.
3. User requests to delete a specific meal in the list.
4. NutriGoals deletes the meal.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The specified food item is invalid.

    * 3a1. NutriGoals shows an error message.

      Use case resumes at step 2.

#### UC-4: Edit a meal
**Use case:** Edit a meal

**MSS**

1. User requests to <ins>list all the meals recorded (UC-1)</ins>.
2. NutriGoals shows a list of meals.
3. User requests to edit a specific meal in the list.
4. NutriGoals edits the meal.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The edited food item is invalid.

    * 3a1. NutriGoals shows an error message.

      Use case resumes at step 2.

#### UC-5: Set up a profile
**Use case:** Set up a profile

**MSS**

1. User requests to set up his / her profile.
2. NutriGoals creates and saves the user's profile.

   Use case ends.

**Extensions**

* 1a. The information provided is invalid.

    * 1a1. NutriGoals shows an error message.

      Use case resumes at step 1.

#### UC-6: View the profile created
**Use case:** View the profile created

**MSS**

1. User requests to view his / her profile.
2. NutriGoals shows the user's profile.

   Use case ends.

**Extensions**

* 1a. User profile is not created.

    * 1a1. NutriGoals requests the user to set up a profile.

    * 1a2. User requests to <ins>set up his / her profile (UC-5)</ins>.

      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### UC-7: View a summary of the daily calorie intake
**Use case:** View a summary of the daily calorie intake

**MSS**

1. User requests to view a summary of the daily calorie intake.
2. NutriGoals shows the user's total calories consumed, the calorie target and the deficient/excess amount of
   calories for the day.

   Use case ends.

#### UC-8: Find the calorie content of a food item
**Use case:** Find the calorie content of a food item

**MSS**

1. User requests to find the calorie content of a food item.
2. NutriGoals shows the calorie content of the specified food item.

   Use case ends.

**Extensions**

* 1a. The specified food item is invalid.

    * 1a1. NutriGoals shows an error message.

      Use case ends.

* 1b. There is no calorie content information for the specified food item.

    * 1b1. NutriGoals displays a default message.

      Use case ends.

#### UC-9: Get a suggested amount of calorie to consume daily
**Use case:** Get a suggested amount of calorie to consume daily

**MSS**

1. User requests to get an estimated suggested amount of calorie to consume daily.
2. NutriGoals shows the suggested amount of calorie to consume.

   Use case ends.

**Extensions**

* 1a. User profile is not created.

  * 1a1. NutriGoals requests user to <ins>create a profile first (UC-5).
    
    Use case resumes at step 1.

#### UC-10: Find the nearest gym
**Use case:** Find the nearest gym 

**MSS**

1. User requests to find the nearest gym to a specified location.
2. NutriGoals returns a list of gyms, sorted by distance from closest to furthest.

    Use case ends.

**Extensions**

* 1a. The specified location is invalid.

  * 1a1. NutriGoals shows an error message.
        
    Use case ends.

#### UC-11: Get a healthy lifestyle tip
**Use case:** Get a tip to help with healthier living

**MSS**

1. User requests to view a tip to help with his healthy living.
2. NutriGoals returns a randomly drawn tip.

    Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 foods without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. The system should respond to a command within two seconds.
5. Project scope:
    * The system only handles information regarding the calorie intake of a food and no other nutrients.

### Glossary

* **Calorie(s)**: Unit of measurement for amount of food consumed.

* **Daily Recommended Calorie Intake**: The amount of calories recommended by experts for an individual to consume daily (According to Health Promotion Board Singapore, adult males should consume 2200 calories, adult females should consume 1800 calories).

* **Deficit**: The negative difference between the calories consumed in a day and the target daily intake. These calories are _not_ consumed to facilitate weight loss and fat burn.

* **Food item**: Refers to an individual item of food that is consumed by the user.

* **Gym**: Refers to a gymnasium used for weight training.

* **Ideal weight**: The self-defined weight that users want to achieve for themselves.

* **Meals**: Breakfast, Lunch, Dinner e.t.c., categories that consist of multiple food items and is usually consumed at a particular time.

* **Surplus**: The positive difference between the calories consumed in a day and the target daily intake. These calories are consumed to facilitate weight gain and muscle growth.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

<div style="page-break-after: always;"></div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder.

    2. Double-click the jar file Expected: Shows the GUI with a set of sample food items. The window size may not be
       optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a food

1. Adding a food item into the list

    1. Test case: `add n/Sushi c/400 t/lunch`<br />
       Expected: Sushi is added into the food list, and it is tagged as a lunch item with 400 calories.

    2. Test case: `add n/Sushi c/400`<br />
       Expected: Sushi is not added into the food list as there are missing fields. Error details are shown on the result display.

    3. Test case: `add n/Sushi t/lunch`<br />
       Expected: Similar to #2.

    4. Test case: `add n/Sush! t/breakfast c/300`<br />
       Expected: Sush! is not added into the food list as the food name (Sush!) is invalid. Error details are shown on the result display.

    5. Other invalid add commands to try: `add n/Sushi c/300.5 t/lunch`, `add n/Sushi c/300 t/supper`, `...` (where any field specified does not conform to the required format).

<div style="page-break-after: always;"></div>

### Deleting a food

1. Deleting a food item while all foods on a particular day are being shown

    1. Prerequisites: List all foods on a particular day using the `list` command. Multiple foods in the list.

    2. Test case: `delete 1`<br>
       Expected: First food item is deleted from the list. Details of the deleted food shown in the status message.
       Timestamp in the status bar is updated.

    3. Test case: `delete 0`<br>
       Expected: No food is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Editing a food

1. Editing a food item while all foods on a particular day are being shown

    1. Prerequisites: List all foods on a particular day using the `list` command. Multiple foods in the list.

    2. Test case: `edit 1 n/Pancake c/250`<br />
       Expected: The name and calorie of the first food item is changed to Pancake and 250 respectively.

    3. Test case: `edit 0 n/Pancake c/250`<br />
       Expected: No food is edited. Error details are shown on the result display.

    4. Test case: `edit 1 n/P@ncake`<br />
       Expected: No food is edited. Error details are shown on the result display.

    5. Test case: `edit 4`<br />
       Expected: No food is edited. Error details are shown on the result display.

    6. Other invalid edit command formats to try: `edit 1 n/Pancake t/snack`, `edit 1 c/150.2`, `...` (where any field specified does not conform to the expected format).<br />
       Expected: Similar to previous.

### Setting a target calorie

1. Setting a target calorie to consume daily

    1. Test case: `target 2500`<br />
       Expected: The target calorie is set to 2500 calories. Success message is displayed on the result display.

    2. Test case: `target 2500.5`<br />
       Expected: Target calorie is not set. Error details are shown on the result display.

    3. Other invalid target command formats to try: `target x` (where x is not an integer value).

### Setting up a user profile

1. Setting up a user profile

    1. Test case: `setup g/m a/20 w/70 i/70 h/175`<br />
       Expected: The user profile is created successfully.

    2. Test case: `setup g/m a/20 w/70.5 i/70 h/175`<br />
       Expected: The user profile is not created. Error details are shown on the result display.

    3. Other invalid setup command formats to try: `setup g/s a/20 w/70 i/70 h/175`, `setup g/f a/20 w/70 i/70 h/175.5`, `...` (where any field specified does not conform to the expected format.)

### Saving data

1. Dealing with corrupted data files

   1. To simulate a corrupted file,
   
      1. Launch and close the application.
      
      2. Ensure the `./data/nutrigoals.json` is created.
      
      3. Corrupt the file by adding a '@' character in any of the food's name.
      
   2. Relaunch the application.<br />
      Expected behaviour: The GUI is launched without any data.
   
   3. Delete the `nutrigoals.json`.
   
   4. Relaunch the application.<br />
      Expected behaviour: The GUI is launched with the sample data.

2. Dealing with missing data files

    1. To simulate a missing file
        
        1. Ensure that `./data/nutrigoals.json` exists.

        2. Delete `./data/nutrigoals.json`.
    
    2. Relaunch the GUI. <br />
       Expected behaviour: The GUI is launched with the sample data.

## Appendix: Effort

When we embarked on this project, NutriGoals was projected to be a morph of the original AB3 application.
It would be a complete wraparound where even the base purpose was different - from an address book to a calorie tracker.
This design decision then lead to many hiccups along the way, but they were nonetheless instrumental in teaching us about
good software engineering practices that we need moving ahead.

Initially, the choice to morph the application led to the refactoring of the codebase to suit our purpose. This
unfortunately caused certain bugs and many errors in not just the test cases, but also the core program itself. While this
was troublesome, confusing and tedious, it allowed us to understand the code base on a deeper level and we were able to appreciate
certain software engineering skills (such as defensive coding).

Another major hiccup and learning lesson came from designing features that not only work, but also suit the user needs. We
admittedly had some trouble coming up with ideas for new features to implement. Eventually, using customer-oriented thinking,
we were able to come up with unique and useful features to add, which we did so successfully. Yet again, we were able to practice
the skills taught extensively as we tried to code comprehensively and defensively, and come up with test cases to ensure extensive
coverage of our code.

To summarise, while the project itself was arduous with multiple setbacks, we were successful in developing an application that
is both useful and easy-to-use for users. Additionally, as mentioned, we were able to put into practice what we had learnt as 
software engineering students and develop a product with good software engineering practices.

</div>
