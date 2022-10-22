<!-- markdownlint-disable-file first-line-h1 -->
#### Create a new item: `new`

<!-- TODO: YX -->
**Format**: `new n/ITEM_NAME [qty/QUANTITY] [type/TYPE] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]`

> Creates a new item with the provided item name. Other than the item name, all other fields are optional.

* The bought date should not be after the expiry date. 
* Format for Bought Date and Expiry Date should follow: "dd-mm-yyyy".
  * d: Day of the month, and the last day of the month depends on that month. For example, the 10th day would be "10".
  * m: Month of the year, ranging from 1 to 12. This represents the months from January to December. For example, January would be "01".
  * y: The current year. For example, "2019" would represent the year 2019.  

**Example Input:**

```text
new n/Potato qty/70 type/kg bgt/22-02-2022 exp/22-03-2022
```

**Example Output:**

Command Output Box:
```text
Item  “potato” successfully created
```

---

#### List all items: `list`
TODO:YX (add price, remarks to displayed items)
**Format**: `list`

> Lists all the items in the inventory.

**Example Input:**

```text
list
```

**Example Output:**

Command Output Box:
```text
Listed all items
```

List Box:
```text
1. Onions 8 kg (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
2. Chicken 30 kg (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
3. Carrots 11 kg (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)
```


---

#### Search for an item: `find`
TODO:YX (ditto as list)
**Format:** `find ITEM_NAME`

> Finds an inventory item based on the given keywords
* It is not necessary to include the "n/" delimiter in front of the item name.
* The search is case-insensitive. (e.g. apples will match Apples)
* The order of the keyword does not matter e.g ("rose apple" will match "apple rose")
* Only the item name is searched


**Example Input:**

```text
find potato
```

**Example Output:**

Command Output Box:
```text
Listed all items
```

List Box:
```text
1. Potato 6 kg (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
```
---

#### Sort all items by an attribute: `sort`
TODO:YX
**Format:**: `sort [n/] [qty/] [type/] [bgt/] [exp/]`
_NOTE: At least one of the sorting criteria must be provided._

> Sorts the list of currently displayed items by one or more criteria in order.
* The list of currently selected items can be modified by other Commands, e.g. the [Find Command](#Find)
* Sorting criteria are applied in the order they were provided, from left-to-right. For example, `sort n/ qty/` would sort the list of currently selected items by name first, then by quantity.
* (!!) After sorting, you can call the [List Command](#List) to re-display all items.

**Example Input:**
```text
sort n/ qty/
```

**Example Output:**
Command Output Box:
```text
3 items sorted!
```

List Box:
```text
1. Carrots 11 kg (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)
2. Chicken 30 kg (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
3. Onions 8 kg (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
`
```
---

#### View the information of an item: `view`
TODO:YX
**Format:**: `view INDEX`
> Displays information about the specified item.
* Displayed information includes the name, quantity, bought date, expiry date, price, and tags of items.

**Example Input:**
```text
view 1 
```

**Example Output:**
Command Output Box:
```text
Name: Onions
Quantity: 8 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Tags: {vegetables}
```
---

#### Increment the quantity of an item: `inc`
**Format:**: `inc INDEX [qty/QUANTITY]`
> Increments the item at the specified index by an optional quantity. 
* Quantity to increment by will default to 1 if it is not specified.

**Example Input:**
```text
inc 1 qty/3
```

**Example Output:**
Command Output Box:
```text
Incremented Item: 
Name: Onions
Quantity: 11 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Tags: {vegetables}
```
---

#### Decrement the quantity of an item: `dec`
**Format:**: `dec INDEX [qty/QUANTITY]`
> Decrements the item at the specified index by an optional quantity.
* Quantity to decrement by will default to 1 if it is not specified.

**Example Input:**
```text
dec 1 qty/4
```

**Example Output:**
Command Output Box:
```text
Decremented Item: 
Name: Onions
Quantity: 7 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Tags: {vegetables}
```
---

#### Edit the information of an item: `edit`
TODO:YX
Command: `edit ITEM_INDEX [n/ITEM_NAME] [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]`
NOTE: _At least one field to edit must be provided._

> Edit the details of the item identified.

**Example Input:**
```text
edit 1 qty/100
```

**Example Output:**
Command Output Box:
```text
Edited Item: 
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Tags: {vegetables}
```
---

#### Delete an item: `del`

Command: `del ITEM_INDEX`

> Deletes an item from the item list. 

**Example Input:**
```text
del 1
```

**Example Output:**
Command Output Box:
```text
Deleted Item:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Tags: {vegetables}
```
---


