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

#### View the information of an item

#### Increase the quantity of an item

#### Decrease the quantity of an item

#### Update the information of an item

#### Delete an item

Command: `[item] delete ITEM_INDEX`

> Description: Deletes a specified item. Returns a warning if the item does not exist.

---

Example:

Input

```text
delete 1
```

Output

```text
(Item exists): Item “potato” successfully deleted!
(Item does not exist): No item to be found at index 1. Use “list items” or “find NAME” to find the index of the item to be deleted.
```
