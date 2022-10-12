<!-- markdownlint-disable-file first-line-h1 -->
(For all use cases below, the **System** is `FoodRem` and the **Actor** is the `purchasing manager`, unless specified otherwise)

#### UC1: Add Item to Inventory

Use Case: UC1 - Add Item to Inventory
MSS:

1. User adds an item to the inventory.
1. FoodRem adds the item into the inventory list.
   Use Case ends.

Extensions:

* 1a. If the item name already exists in the inventory, FoodRem will throw an error.
  * 1a1. User will re-enter command until the new item is correct.
    Use case resumes from Step 2.

#### UC2: Update Item in Inventory

Use Case: UC2 - Update Item in Inventory
MSS:

1. User updates an inventory item's detail.
1. FoodRem updates the detail of the inventory item.
   Use case ends.

Extensions:

* 1a. If the item does not exist in the inventory, FoodRem will throw an error.
  * 1a1. User will re-enter command until the correct item is given (i.e item exists in inventory).
    Use case resumes from Step 2.

#### UC3: Delete Item from Inventory

Use Case: UC2 - Delete Item from Inventory
MSS:

1. User deletes an item from inventory.
1. FoodRem removes item from inventory.
   Use case ends.

Extensions:

* 1a. Item does not exist in inventory.
  * 1a1. FoodRem displays error to user that item does not exist in inventory.
    Use case resumes from step 1.

#### UC4: Create Tag

Use Case: UC4 - Create Tag
MSS:

1. User creates a tag.

Extensions:

* 1a. Tag already exists.
  * 1a1. FoodRem displays error warning to user.
    Use case resumes from step 1.

#### UC5: Find Item

Use Case: UC5 - Find Item
MSS:

1. User searches for an Item using keywords.
1. FoodRem displays all items in inventory which name matches the given keyword.
   Use case ends.

#### UC6: Add Tag to Item

Use Case: UC6 - Add Tag to an Item
MSS:

1. User enters the command to find the item of interest.
1. FoodRem shows a list containing possible matching items.
1. User enters command to add a specified tag from the desired items.
1. FoodRem informs user that the tag has been updated successfully.
   Use case ends.

Extensions:

* 1a. FoodRem detects that there is an issue with the command entered.

  * 1a1. FoodRem requests for the command to be entered again.
  * 1a2. User re-enters the command.
  * Steps 1a1-1a2 are repeated until the command entered is correct. Use case resumes from step 2.

* 3a. FoodRem detects that there is an issue with the command entered.
  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct.
    Use case resumes from step 4.

#### UC7: Rename a tag

Use Case: UC7 - Rename a tag
MSS:

1. User chooses to view all tags.
1. FoodRem shows all tags.
1. User enters the command to rename desired tag.
1. FoodRem informs user that the tag has been updated successfully.

Extensions:

* 3a. FoodRem detects that the new tag name already exist.

  * 3a1. FoodRem requests for a new tag name that does not exist.
  * 3a2. User re-enters the command to rename the desired tag.
  * Steps 3a1-3a2 are repeated until the data entered are correct.
    Use case resumes from step 4.

* 3b. FoodRem detects that the name is in an incorrect format.
  * 3b1. FoodRem requests for a new tag name that follows an acceptable format.
  * 3b2. User re-enters the command to rename the desired tag.
  * Steps 3b1-3b2 are repeated until the command entered is correct.
    Use case resumes from step 4.

#### UC8: Removing a tag from an item

Use Case: UC8 - Removing a tag from an item
Preconditions: User knows the name of the tag they are removing from an item.

MSS:

1. User enters the command to find the item of interest.
1. FoodRem shows a list containing possible matching items.
1. User enters command to remove the tag from the desired items.
1. FoodRem informs user that the tag has been updated successfully.

Extensions:

* 1a. FoodRem detects that there is an issue with the command entered.

  * 1a1. FoodRem requests for the command to be entered again.
  * 1a2. User re-enters the command.
  * Steps 1a1-1a2 are repeated until the command entered is correct. Use case resumes from step 2.

* 3a. FoodRem detects that there is an issue with the command entered.
  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct.
    Use case resumes from step 4.

#### UC9: Increment/Decrement Quantity of Item

Use Case: UC9 - Increment/Decrement Quantity of Item
MSS:

1. User increases/decreases the amount of the item in the inventory
   Extensions:

* 1a. Item does not exist
  * 1a1. FoodRem displays an error.
  * 1a2. FoodRem asks the user if they want to try again

#### UC10: Sorting List of Items by Criteria

Use Case: UC10 - Sorting List of Items by Criteria
MSS:

1. User lists items
1. User selects a criteria to sort the list by
1. The list items are reordered according to the chosen criterion

_{More to be added}_
