<!-- markdownlint-disable-file first-line-h1 -->
Example of an [Item](#item):

![Item](images/ItemImage.png)

#### Create a new item: `new`

**Format**: `new n/ITEM_NAME [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

> Creates a new item with the provided information

```info
* All fields apart from `ITEM_NAME` are optional.
* The `BOUGHT_DATE` should not be after the `EXPIRY_DATE`.
* The format for `BOUGHT_DATE` and `EXPIRY_DATE` should follow: "dd-mm-yyyy".
  * dd: Day of the month. For example, "10" would represent the 10th day of the month.
  * mm: Month of the year, ranging from 1 to 12. This represents the months from January to December. For example, "01" would represent January.
  * yyyy: The current year. For example, "2019" would represent the year 2019.
* The value of `BOUGHT_DATE`, `EXPIRY_DATE` will be `Not Set` if it is not provided.
* The default values for `QUANTITY` and `PRICE` is `0`.
* The default values for `UNIT` is blank.
* The value of `REMARKS` will be `-` if is it not provided.
* `PRICE` do not require you to include the currency. Only include the value.
* You cannot create an item with a tag immediately.
* If two or more of the same parameters are provided, the last parameter will be taken.
```

**Example Input:**

```text
new n/Potato qty/70 u/kg bgt/22-02-2022 exp/22-03-2022
```

**Expected Output:**<br>Command Output Box:

```text
New item added:
Name: Potato
Quantity: 70 kg
Bought Date: 22-02-2022
Expiry Date: 22-03-2022
Price: $0
Remarks: -
Tags: {}
```

---

#### List all items: `list`

**Format**: `list`

> List all items in FoodRem

```info
* This command is useful to view all items again after using the [Find Command](#Find)
```

**Example Input:**

```text
list
```

**Expected Output:**<br>Command Output Box:

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

> Finds all items in FoodRem whose names contain substrings of the KEYWORDS

```info
* The notation `[KEYWORDS]...` means that we can take in multiple keywords. In this case, at least one `KEYWORD` is required.
* The `KEYWORDS` are case-insensitive. (e.g. "apples" will match "Apples").
* The result will be items in which any of the words in `ITEM_NAME` contains a substring of the `KEYWORDS`. (e.g. "c e" will match "Carrot Cake", "cereal", "Cold Escargo" and "eclairs")
```

```tip
* You can use the [List Command](#list-all-items-list) to display all items again!
```

**Example Input:**

```text
find potato carrot cucumbers
```

**Expected Output:**<br>Command Output Box:

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

```note
* `\n` : Sort by item name
* `\qty` : Sort by item quantity
* `\u` : Sort by item unit
* `\bgt` : Sort by item bought date
* `\exp` : Sort by item expiry date
* `\p` : Sort by item price
* `\r` : Sort by item remarks
```

```warning
* You should only provide one sorting criteria.
* The sort can only be done in an ascending order.
```

**Example Input:**

```text
sort n/
```

**Expected Output:**<br>Command Output Box:

```text
3 items sorted!
```

Item List Box:

```text
1. Onions 8 kg $1 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
2. Chicken 30 kg $4.20 (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
3. Carrots 11 kg $0.60 (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)
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

**Expected Output:**<br>Command Output Box:

```text
Name: Onions
Quantity: 8 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: -
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

**Expected Output:**<br>Command Output Box:

```text
Incremented Item:
Name: Onions
Quantity: 11 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: -
Tags: {vegetables}
```

---

#### Decrement the quantity of an item: `dec`

**Format:**: `dec INDEX [qty/QUANTITY]`
> Decrements the quantity of the item at the specified index

```info
* If a quantity is not provided, the item quantity will be decremented by 1.
* If two or more `QUANTITY` are provided, the last `QUANTITY` will be taken.
```

**Example Input:**

```text
dec 1 qty/4
```

**Expected Output:**<br>Command Output Box:

```text
Decremented Item:
Name: Onions
Quantity: 7 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: -
Tags: {vegetables}
```

---

#### Edit the information of an item: `edit`

**Format**: `edit INDEX [n/ITEM_NAME] [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

> Updates the details of the item at the specified index

```info
* All fields are optional. However, you need to include at least one parameter.
* The `BOUGHT_DATE` should not be after the `EXPIRY_DATE`.
* The format for `BOUGHT_DATE` and `EXPIRY_DATE` should follow: "dd-mm-yyyy".
  * dd: Day of the month. For example, "10" would represent the 10th day of the month.
  * mm: Month of the year, ranging from 1 to 12. This represents the months from January to December. For example, "01" would represent January.
  * yyyy: The current year. For example, "2019" would represent the year 2019.
* `PRICE` do not require you to include the currency. Only include the value.
* If two or more of the same parameters are provided, the last parameter will be taken.
```

**Example Input:**

```text
edit 1 qty/100 n/Potatoes
```

**Expected Output:**<br>Command Output Box:

```text
Edited Item:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: -
Tags: {vegetables}
```

---

#### Add a remark to an item: `rmk`

**Format**: `rmk INDEX [r/REMARKS]`

> Add a remark to the item at the specified index

```info
* If no remark is provided, the current remark will be cleared.
* If two or more `REMARKS` are provided, the last `REMARKS` will be taken.
```

**Example Input:**

```text
rmk 1 r/For Party
```

**Expected Output:**<br>Command Output Box:

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

**Format**: `del INDEX`

> Deletes the item at the specified index

**Example Input:**

```text
del 1
```

**Expected Output:**<br>Command Output Box:

```text
Deleted Item:
Name: Onions
Quantity: 100 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: -
Tags: {vegetables}
```

---
