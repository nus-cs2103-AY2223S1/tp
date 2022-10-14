<!-- markdownlint-disable-file first-line-h1 -->
{Insert an image of items}

#### Create a new item

<!--- TODO: emember to warn users if expiry date < bought date-->

Command: `item new ITEM_NAME`

> Description: Creates a new item with the provided item_name.

---

Example:

Input

```text
item new potato
```

Output

```text
Item  “potato” successfully created
```

#### List all items

Command: `list`

> Description: Lists all the items in the inventory.

---

Example:

Input

```text
list
```

Output

```text
Here are the items in your inventory:
Onions
Details about onions
Tomatoes
Details about tomatoes
Chicken wings
Details about chicken wings
```

### Search for an item

<!--- Remember to implement find by tags-->

Command: `find NAME`

> Description: Find an inventory item based on the given keywords
> The search is case-insensitive. (e.g. apples will match Apples)
> The order of the keyword does not matter e.g (rose apple will match apple rose)
> Only the item name is searched

---

Example:

Input

```text
find apple
```

Output

```text
Here are the results matching your search
Green apple
Rose apple
```

#### Sort all items by an attribute

---

#### View the information of an item

---

#### Increase the quantity of an item

---

#### Decrease the quantity of an item

---

#### Update the information of an item
Command: `edit ITEM_INDEX [n/ITEM_NAME] [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]`

> Description: Updates the details of the item identified.

Example:

```text
edit 1 qty/1000
edit 10 n/Potatoes qty/1000
edit 100 bgt/11-11-2022 exp/11-30-2023
```

Output:
```text
Edited Item:
(A description of the item being edited)
```

Common errors:
1) If the item index is not shown in the current list an error message will be thrown.
    ```
    The item index provided is invalid.
    ```

   Remedy:
    ```
    Use the list command or find command to find out what is the index of the item to be deleted.
    ```
   
2) If no fields to edit was provided.
   ```text
   At least one field to edit must be provided.
   ```

   Remedy:
   ```
   Include at least one field using the prefix as shown in the command format.
   ```

3) If the item name is set to a name similar to an item in FoodRem.
   ```text
   This item already exists in the FoodRem.
   ```

   Remedy:
   ```
   Choose a different name while editing the item.
   ```
---

#### Delete an item

Command: `del ITEM_INDEX`

> Description: Deletes an item from FoodRem permanently. 

Example:
```text
del 1
del 10
```

Output:
```text
Deleted Item:
(A description of the item being deleted)
```

Possible errors:
1) If the item index is not shown in the current list an error message will be thrown.
    ```
    The item index provided is invalid.
    ```

    Remedy:
    ```
    Use the list command or find command to find out what is the index of the item to be deleted.
    ```
