<!-- markdownlint-disable-file first-line-h1 -->

#### UC1: Add Item to Inventory

**Use Case: UC1 - Add Item to Inventory**

Preconditions: Item to add does not yet exist in inventory.

MSS:

1. User adds a new Item to the inventory with the specified arguments.
1. FoodRem informs user that item has been successfully created.

Extensions:

* 1a. If the item name already exists in the inventory, FoodRem will throw an error.

  * 1a1. FoodRem requests for the command to be entered again.
  * 1a2. FoodRem re-enters the command.
  * Steps 1a1-1a2 are repeated until the command entered is correct. <br> Use case resumes from step 2.

* 1b. FoodRem detects that there is an issue with the command entered.
  * 1b1. FoodRem requests for the command to be entered again.
  * 1b2. User re-enters the command.
  * Steps 1a1-1a2 are repeated until the command entered is correct. <br> Use case resumes from step 2.

---

#### UC2: Update Item in Inventory

**Use Case: UC2 - Update Item in Inventory**

Preconditions: Item to edit exists in inventory.

MSS:

1. User chooses to view all items.
1. FoodRem shows all items.
1. User enters the command to edit the item with new fields.
1. FoodRem informs user that item has been successfully edited.

Extensions:

* 3a. If the item name does not exist in the inventory, FoodRem will throw an error.

  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. FoodRem re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br> Use case resumes from step 3.

* 3b. FoodRem detects that there is an issue with the command entered.
  * 3b1. FoodRem requests for the command to be entered again.
  * 3b2. User re-enters the command.
  * Steps 3b1-3b2 are repeated until the command entered is correct. <br> Use case resumes from step 3.

---

#### UC3: Delete Item from Inventory

**Use Case: UC3 - Delete Item from Inventory**

Preconditions: Item to delete exists in inventory.

MSS:

1. User chooses to view all items.
1. FoodRem shows all items.
1. User enters the command to delete an item.
1. FoodRem informs user that item has been successfully deleted.

Extensions:

* 3b. FoodRem detects that there is an issue with the command entered.
  * 3b1. FoodRem requests for the command to be entered again.
  * 3b2. User re-enters the command.
  * Steps 3b1-3b2 are repeated until the command entered is correct. <br> Use case resumes from step 3.

---

#### UC4: Create Tag

**Use Case: UC4 - Create Tag**

Preconditions: Tag does not yet exist in FoodRem.

MSS:

1. User adds a new Tag to the inventory with the specified arguments.
1. FoodRem informs user that tag has been successfully created.

Extensions:

* 3a. If the tag name already exists in the inventory, FoodRem will throw an error.

  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. FoodRem re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br> Use case resumes from step 2.

* 3b. FoodRem detects that there is an issue with the command entered.
  * 3b1. FoodRem requests for the command to be entered again.
  * 3b2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br> Use case resumes from step 2.

---

#### UC5: Find Item

**Use Case: UC5 - Find Item**

MSS:

1. User chooses to view items.
1. FoodRem shows a list containing the items.
1. User searches for an Item with one or more criteria.
1. FoodRem displays all items in inventory which matches the given criteria.
   Use case ends.

* 3a. FoodRem detects that there is an issue with the command entered.
  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br> Use case resumes from step 4.

---

#### UC6: Add Tag to Item

**Use Case: UC6 - Add Tag to an Item**

Preconditions: Tag has been created in FoodRem.

MSS:

1. User chooses to view items.
1. FoodRem shows a list containing the items.
1. User enters command to add a specified tag to a desired item.
1. FoodRem informs user that the tag has been tagged to the item updated successfully.

   Use case ends.

Extensions:

* 3a. FoodRem detects that there is an issue with the command entered.
  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br> Use case resumes from step 4.

---

#### UC7: Rename a tag

**Use Case: UC7 - Rename a tag**

Preconditions: Tag has been created in FoodRem.

MSS:

1. User chooses to view all tags.
1. FoodRem shows all tags.
1. User enters the command to rename desired tag with a new tag name.
1. FoodRem informs user that the tag has been updated successfully.

Use case ends.

Extensions:

* 3a. FoodRem detects that the new tag name already exist.
  * 3a1. FoodRem requests for user to rename tag with a new tag name.
  * 3a2. User re-enters the command to rename the desired tag.
  * Steps 3a1-3a2 are repeated until the command entered is correct.<br> Use case resumes from step 4.

---

#### UC8: Removing a tag from an item

**Use Case: UC8 - Removing a tag from an item**

Preconditions: The tag to be removed is tagged to the item currently.

MSS:

1. User chooses to view items.
1. FoodRem shows a list containing the items.
1. User enters command to remove the tag from the desired item.
1. FoodRem informs user that the tag has been removed from the item successfully.

Use case ends.

Extensions:

* 3a. FoodRem detects that there is an issue with the command entered.
  * 3a1. FoodRem requests for the command to be entered again.
  * 3a2. User re-enters the command.
  * Steps 3a1-3a2 are repeated until the command entered is correct. <br>Use case resumes from step 4.

---

#### UC9: Increment/Decrement Quantity of Item

**Use Case: UC9 - Increment/Decrement Quantity of Item**

MSS:

1. User chooses to view items.
1. FoodRem shows a list containing the items.
1. User increases/decreases the quantity of a specified item in the inventory.
1. FoodRem informs user that item quantity has been incremented/decremented successfully.

Use case ends.

Extensions:

* 3a. Item quantity cannot be incremented/decremented further
  * 3a1. FoodRem displays an error.
  * 3a2. FoodRem asks the user if they want to try again

---

#### UC10: Sorting List of Items by Criteria

**Use Case: UC10 - Sorting List of Items by Criteria**

MSS:

1. User chooses to view items.
1. FoodRem shows a list containing the items.
1. User selects a criteria to sort the list by.
1. The list items are reordered according to the chosen criterion.

Use case ends.
