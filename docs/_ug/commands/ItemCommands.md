<!-- markdownlint-disable-file first-line-h1 -->
Example of an [Item](#item):

![Item](images/ItemImage.png)
{: style="margin-left: auto; margin-right: auto; padding: 20px; max-width: 50%; border: 1px solid black; border-radius: 4px" }

{% include page-break.html %}

#### Create a new item: `new`

**Format**: `new n/ITEM_NAME [qty/QUANTITY] [u/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

> Creates a new item with the provided information

```info
* All fields apart from `ITEM_NAME` are optional.
* {{ site.data.constraints.boughtNotAfterExpiry }}
* {{ site.data.constraints.dateFormat.summary }}
  * {{ site.data.constraints.dateFormat.day }}
  * {{ site.data.constraints.dateFormat.month }}
  * {{ site.data.constraints.dateFormat.year }}
* The value of `BOUGHT_DATE`, `EXPIRY_DATE` will be `Not Set` if it is not provided.
* The default value for `QUANTITY` and `PRICE` is `0`.
* The default value for `UNIT` is blank.
* The value of `REMARKS` will be `-` if is it not provided.
* {{ site.data.constraints.priceIsWithoutSymbol }}
```

```note
* You cannot create an item and tag it at the same time.
* {{ site.data.constraints.lastValueOfDuplicates }}
```

**Example:**

{% capture notes %}
**Assumption:**

FoodRem does not already contain an item with the name "Potato".
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

> Finds all items in FoodRem whose names contain [[ substring:substrings ]] of the KEYWORDS

{% include page-break.html %}

```info
* The notation `[KEYWORDS]...` means that we can take in multiple keywords. In this case, at least one `KEYWORD` is required.
* The `KEYWORDS` are case-insensitive. (e.g. "apples" will match "Apples").
* The result will be items where each of the `KEYWORDS` are present in the `ITEM_NAME` as a substring. (e.g. "c e" will match "Carrot Cake", "cereal", "Cold Escargo" and "eclairs")
```

```tip
* You can use the [List Command](#list-all-items-list) in the next section to display all items again!
```

```note
* The `find` command only finds `Items` which has a name that partially or fully matches the specified search by name! 
* This means that if the `Items` `Brown Sugar` and `White Sugar` is in FoodRem, executing `find Sugar` will find these two `Items`. 
* However, if you try to find an `Item` `Potato` by executing the command `find potatoes carrots celery`, it will not work!
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
* `sort n/` : Sort by item name
* `sort qty/` : Sort by item quantity
* `sort u/` : Sort by item unit
* `sort bgt/` : Sort by item bought date
* `sort exp/` : Sort by item expiry date
* `sort p/` : Sort by item price
* `sort r/` : Sort by item remarks
```

```tip
You may find this command useful when you need to quickly dispose of expiring items or to check item stocks, but the sky is the limit! You can use FoodRem in creative ways to take advantage of this! For example, if you use the `PRICE` field to store each item's profit per unit sold, you can use this command to see which items are giving you the most profits!
```

```warning
* You should only provide one sorting criteria.
* The sort can only be done in ascending order.
```

**Example:**

{% capture notes %}
**Assumption:**

FoodRem contains the following items:

1. Sugarcane Juice Box
1. Brown Sugar
1. Tomato
1. Carrot
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
* If two or more `QUANTITY` values are provided, only the last `QUANTITY` will be taken.
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
* If two or more `QUANTITY` values are provided, only the last `QUANTITY` will be taken.
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
* {{ site.data.constraints.boughtNotAfterExpiry }}
* {{ site.data.constraints.dateFormat.summary }}
  * {{ site.data.constraints.dateFormat.day }}
  * {{ site.data.constraints.dateFormat.month }}
  * {{ site.data.constraints.dateFormat.year }}
* {{ site.data.constraints.priceIsWithoutSymbol }}
```

```note
* {{ site.data.constraints.lastValueOfDuplicates }}
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
* If two or more `REMARKS` values are provided, only the last `REMARKS` value will be taken.
* If an item already has a remark, this command will overwrite the old remark with the new remark.
* The new remark can overwrite the old remark even if they are the same. This is to give the user flexibility in editing remarks.
```

**Example:**

{% capture notes %}
**Assumption:**

The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="rmk 1 r/For party!"
  commandOutputBox="images/itemCommands/commandOutputBox/rmk.png"
%}

---

{% include page-break.html %}

#### Delete an item: `del`

**Format**: `del INDEX`

> Deletes the item at the specified index

**Example:**

{% capture notes %}
**Assumption:**

The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Onion" at INDEX value 1.
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="del 1"
  commandOutputBox="images/itemCommands/commandOutputBox/del.png"
%}

---
