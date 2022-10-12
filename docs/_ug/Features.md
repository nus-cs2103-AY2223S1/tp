<!-- markdownlint-disable-file first-line-h1 -->
This section covers how to use each command in detail.
Before continuing, ensure you have read the section on [Flags](#flags) and [Placeholders](#placeholders).

What you should expect to find:

* A description of the command
* The expected behaviour for the command
* A few valid and invalid examples of the command
* Important points to note

**IMPORTANT:**

* Square brackets indicate an optional parameter.
* For each command, "Format" indicates the syntax of the command.

### Item Features

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

### Tag Features

{Insert an image of tags}

#### Create a new tag

#### List all tags

Command: `list tags`

> Description: Lists all the tags that the user has created.

---

Example:

Input

```text
list tags
```

Output

```text
Here are the tags that are available:
Fruits
Vegetables
Spices
```

#### Tag an item

#### Untag an item

#### Rename a tag

#### Delete a tag

### Receive help during usage

Command: `help`

> Description: Displays a list of commands that can be used.

---

Example:

Input

```text
help
```

Output:

```text
list:
    Lists all the items/tags that the user has created.

    Usage:
        List items:  "list items"
        List tags:   "list tags"

item:
    Create / Delete / Increment quantity / Decrement quantity /
    Set quantity / Set expiry date / Set bought date, of an item.

    Flags:
        Name:        n/
        Quantity:    qty/
        Expiry Date: exp/
        Bought Date: bgt/

    Usage:
        Create:      "item new n/Potatoes"
        Delete:      "item del 1"
        Increment:   "item inc 1 10"
        Decrement:   "item dec 1 10"
        Set:         "item set 1 n/Potatoes qty/10"

find:
    Find an inventory item based on the given keywords.

    Usage:
        Find:        "find potato carrots"

tag:
    Create / Rename / Set item tied to / Delete, a tag.

    Flags:
        Name:        n/

    Usage:
        Create:      "tag create food"
        Rename:      "tag rename food n/foodie"
        Set item:    "tag 1 2 7 71 food"
        Delete:      "tag delete food"

bye:
    Exits FoodRem program.

    Usage:
        Exit:       "bye"
```

### Reset the application

### Exit the application

Command: `bye`

> Description: Exits FoodRem program.

---

Example:

Input

```text
bye
```
