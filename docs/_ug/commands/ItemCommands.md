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

**Example:**

{% capture notes %}
**Assumption:**

Initially, FoodRem does not contain an item with the name "Potato".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="new n/Potato qty/70 u/kg bgt/22-02-2022 exp/22-03-2022"
  commandOutputBox="images/itemCommands/commandOutputBox/new.png"
%}

---

#### Search for an item: `find`

**Format:** `find KEYWORD [KEYWORDS]...`

> Finds all items in FoodRem whose names contain [[ substring:substrings]] of the KEYWORDS

<!-- TODO: Remove duplicate autoglossary (just pick one) -->
```info
* The notation `[KEYWORDS]...` means that we can take in multiple keywords. In this case, at least one `KEYWORD` is required.
* The `KEYWORDS` are case-insensitive. (e.g. "apples" will match "Apples").
* The result will be items where each of the `KEYWORDS` are present in the `ITEM_NAME` as a [[ substring ]]. (e.g. "c e" will match "Carrot Cake", "cereal", "Cold Escargo" and "eclairs")
```

```tip
* You can use the [List Command](#list-all-items-list) in the next section to display all items again!
```

**Example:**

{% capture notes %}
**Assumption:**

Initially, FoodRem only contains the following items:

1. Sugarcane Juice Box
1. Brown Sugar
1. Tomato
1. Carrot
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="find b sug"
  itemListBox="images/itemCommands/itemListBox/find.png"
%}

---

#### List all items: `list`

**Format**: `list`

> List all items in FoodRem

```info
* This command is useful to view all items again after using the [Find Command](#search-for-an-item-find).
```

**Example:**

{% capture notes %}
**Assumption:**

FoodRem contains the following items, each with their own attributes:

1. Sugarcane Juice Box
1. Brown Sugar
1. Tomato
1. Carrot
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="list"
  itemListBox="images/itemCommands/itemListBox/list.png"
%}

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

**Example:**

{% capture notes %}
**Assumption:**

FoodRem contains the following items:

1. Sugarcane Juice Box
1. Brown Sugar
1. Tomato
1. Carrot

The command will sort the items by their names.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="sort n/"
  itemListBox="images/itemCommands/itemListBox/sort.png"
%}

---

#### View the information of an item: `view`

**Format:** `view INDEX`

> Displays the item at the specified index

```info
* Displayed information includes the name, quantity, unit, bought date, expiry date, price, remarks and tags of items.
```

**Example:**

{% capture notes %}
**Assumption:**

The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.

The command will produce a detailed view of this item.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="view 1"
  commandOutputBox="images/itemCommands/commandOutputBox/view.png"
%}

---

#### Increment the quantity of an item: `inc`

**Format:**: `inc INDEX [qty/QUANTITY]`

> Increments the quantity of the item at the specified index

```info
* If a quantity is not provided, the item quantity will be incremented by 1.
* If two or more `QUANTITY` values are provided, the last `QUANTITY` will be taken.
```

**Example:**

{% capture notes %}
**Assumptions:**

* The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.
* Initially, the "Onion" item has a "Quantity" of 8.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="inc 1 qty/3"
  commandOutputBox="images/itemCommands/commandOutputBox/inc.png"
%}

---

#### Decrement the quantity of an item: `dec`

**Format:**: `dec INDEX [qty/QUANTITY]`
> Decrements the quantity of the item at the specified index

```info
* If a quantity is not provided, the item quantity will be decremented by 1.
* If two or more `QUANTITY` are provided, the last `QUANTITY` will be taken.
```

**Example:**

{% capture notes %}
**Assumptions:**

* The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.
* Initially, the "Onion" item has a "Quantity" of 11.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="dec 1 qty/4"
  commandOutputBox="images/itemCommands/commandOutputBox/dec.png"
%}

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

**Example:**

{% capture notes %}
**Assumptions:**

* The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.
* Initially, the "Onion" item has the following values:
  * Unit: kg
  * Bought Date: 10-10-2022
  * Expiry Date: 10-11-2022
  * Price: 6.00
  * Remarks: -
  * Tags: Vegetables
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="edit 1 qty/100 n/Spring Onion"
  commandOutputBox="images/itemCommands/commandOutputBox/edit.png"
%}

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
