<!-- markdownlint-disable-file first-line-h1 -->
Example of [Item](#item):

![Item](images/ItemImage.png)

#### Create a new item: `new`

**Format**: `new n/ITEM_NAME [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

> Creates a new item with the provided item name

```info
* All fields apart from `ITEM_NAME` are optional.
* The `BOUGHT_DATE` ideally should not be after the `EXPIRY_DATE` but we will allow that. 
* The format for `BOUGHT_DATE` and `EXPIRY_DATE` should follow: "dd-mm-yyyy".
  * dd: Day of the month. For example, "10" would represent the 10th day of the month.
  * mm: Month of the year, ranging from 1 to 12. This represents the months from January to December. For example, "01" would represent January.
  * yyyy: The current year. For example, "2019" would represent the year 2019.  
* The default values for `QUANTITY` and `PRICE` is `0`.
* The default values for `UNIT` is blank.
* The value of `BOUGHT_DATE`, `EXPIRY_DATE` will be `Not Set` if not provided.
* The value of `REMARKS` will be `No Remarks` if not provided.
* `PRICE` do not require you to include the currency. Only include the value.
* You cannot create an item with a tag immediately.
* If two or more of the same parameters are provided, the last parameter will be taken.
```

**Example Input:**

```text
new n/Potato qty/70 u/kg bgt/22-02-2022 exp/22-03-2022
```

**Expected Output:**

Command Output Box:

```text
New item added:
Name: Potato
Quantity: 70 kg
Bought Date: 22-02-2022
Expiry Date: 22-03-2022
Price: $0
Remarks: No Remarks
Tags: {}
```

---

#### List all items: `list`

**Format**: `list`

> List all items in FoodRem.

```info
* This command is useful to view all items again after using the [Find Command](#Find)
```

**Example Input:**

```text
list
```

**Expected Output:**

Command Output Box:

```text
Listed all items
```

Item List Box:

```text
1. Onions 8 kg $1 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
2. Chicken 30 kg $4.20 (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
3. Carrots 11 kg $0.60 (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)
```

---

#### Search for an item: `find`

**Format:** `find KEYWORD [KEYWORDS]...`

> Finds all items in FoodRem whose names contain any of the specified keywords

```info
* The notation `[KEYWORDS]...` means that we can take in multiple keywords. In this case, at least one `KEYWORD` is required.
* The `KEYWORDS` are case-insensitive. (e.g. "apples" will match "Apples").
* The order of the `KEYWORDS` do not matter (e.g "rose apple" will match "apple rose").
* The result will be items in which `ITEM_NAME` contain any one of the `KEYWORDS` provided.
* You can use the [List Command](#List) to display all items again.
```

**Example Input:**

```text
find potato carrot cucumbers
```

**Expected Output:**

Command Output Box:

```text
1 item listed!
```

Item List Box:

```text
1. Potato 6 kg $2.40 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
```

---

#### Sort all items by an attribute: `sort`

**Format:** `sort [n/] [qty/] [u/] [bgt/] [exp/] [p/] [r/]`

> Sorts the list of currently displayed items by the provided criteria

```info
* Only one sorting criteria is to be provided.
```

**Example Input:**

```text
sort n/
```

**Expected Output:**

Command Output Box:

```text
3 items sorted!
```

Item List Box:

```text
1. Onions 8 kg $1 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
1. Chicken 30 kg $4.20 (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
1. Carrots 11 kg $0.60 (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)
```

---

#### View the information of an item: `view`

**Format:** `view INDEX`

> Displays the item at the specified index

```info
* Displayed information includes the name, quantity, unit, bought date, expiry date, price, remarks and tags of items.
```

**Example Input:**

```text
view 1
```

**Expected Output:**

Command Output Box:

```text
Name: Onions
Quantity: 8 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {vegetables}
```

---

#### Increment the quantity of an item: `inc`

**Format:**: `inc INDEX [qty/QUANTITY]`

> Increments the quantity of the item at the specified index

```info
* If a quantity is not provided, the item quantity will be incremented by 1.
* If two or more `QUANTITY` are provided, the last `QUANTITY` will be taken.
```

**Example Input:**

```text
inc 1 qty/3
```

**Expected Output:**

Command Output Box:

```text
Incremented Item:
Name: Onions
Quantity: 11 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {vegetables}
```

---

#### Decrement the quantity of an item: `dec`

**Format:**: `dec INDEX [qty/QUANTITY]`
> Decrements the quantity of the item at the specified index.

```info
* If a quantity is not provided, the item quantity will be decremented by 1.
* If two or more `QUANTITY` are provided, the last `QUANTITY` will be taken.
```

**Example Input:**

```text
dec 1 qty/4
```

**Expected Output:**

Command Output Box:

```text
Decremented Item:
Name: Onions
Quantity: 7 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {vegetables}
```

---

#### Edit the information of an item: `edit`

Command: `edit ITEM_INDEX [n/ITEM_NAME] [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

> Updates the details of the item at the specified index

```info
* All fields are optional. However, you need to include at least one parameter.
* The format for `BOUGHT_DATE` and `EXPIRY_DATE` should follow: "dd-mm-yyyy".
  * dd: Day of the month. For example, "10" would represent the 10th day of the month.
  * mm: Month of the year, ranging from 1 to 12. This represents the months from January to December. For example, "01" would represent January.
  * yyyy: The current year. For example, "2019" would represent the year 2019.
* The default values for `QUANTITY` and `PRICE` is `0`.
* The default values for `UNIT` is blank.
* The value of `BOUGHT_DATE`, `EXPIRY_DATE` will be `Not Set` if not provided.
* The value of `REMARKS` will be `No Remarks` if not provided.
* `PRICE` do not require you to include the currency. Only include the value.
* You cannot create an item with a tag immediately.
* If two or more of the same parameters are provided, the last parameter will be taken.
```

**Example Input:**

```text
edit 1 qty/100 n/Potatoes
```

**Expected Output:**

Command Output Box:

```text
Edited Item:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {vegetables}
```

---

#### Add a remark to an item: `rmk`

Command: `rmk ITEM_INDEX [r/REMARKS]`

> Adds a remark to the item at the specified index

```info
* If no remark is provided, the current remark will be cleared.
* If two or more `REMARKS` are provided, the last `REMARKS` will be taken.
```

**Example Input:**

```text
rmk 1 r/For Party
```

**Expected Output:**

Command Output Box:

```text
Remark Added:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: For Party
Tags: {vegetables}
```

---

#### Delete an item: `del`

Command: `del ITEM_INDEX`

> Deletes the item at the specified index.

**Example Input:**

```text
del 1
```

**Expected Output:**

Command Output Box:

```text
Deleted Item:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {vegetables}
```

---
