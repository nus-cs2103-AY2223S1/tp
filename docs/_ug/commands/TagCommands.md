<!-- markdownlint-disable-file first-line-h1 -->
{Insert an image of tags}

#### Create a new tag: `newtag`
**Format**: `newtag n/TAG_NAME`

> Creates a new tag with the provided tag name

**Example Input:**
```text
newtag n/Vegetables
```

**Example Output:**

Command Output Box:
```text
New tag added: Vegetables
```
---

#### List all tags: `listtag`
**Format**: `listtag`

> List all tags in FoodRem

**Example Input:**
```text
listtag
```

**Example Output:**

Command Output Box:
```text
Listed all tags:
Vegetables
Carrots
```
---

#### Tag an item: `tag`
**Format**: `tag INDEX n/TAG_NAME `

> Tags the item at the specified index

**Example Input:**
```text
tag 1 n/Vegetables
```

**Example Output:**

Command Output Box:
```text
Item tagged successfully.
Name: Onions
Quantity: 8 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {Vegetables}
```
---

#### Untag an item: `untag`
**Format**: `untag n/TAG_NAME id/INDEX`

> Untags the item at the specified index

**Example Input:**
```text
untag 1 n/Vegetables
```

**Example Output:**

Command Output Box:
```text
Item untagged successfully
Name: Onions
Quantity: 8 kg
Bought Date: 10-10-2022
Expiry Date: 10-11-2022
Price: $6
Remarks: No Remarks
Tags: {}
```
___

#### Rename a tag: `renametag`
**Format**: `renametag n/TAG_NAME n/TAG_NAME`

> Renames an existing tag in FoodRem

**Note:**
* The first `TAG_NAME` in the command refers to the current tag you wish to rename while the second `TAG_NAME` refers to the new name you wish to rename the current tag to.

**Example Input:**
```text
renametag n/Vegetables n/Veggies
```

**Example Output:**

Command Output Box:
```text
Original tag: Vegetables
Renamed tag: Veggies
```
---

#### Delete a tag: `deletetag`
**Format**: `deletetag n/TAG_NAME`

> Deletes a tag that exists in FoodRem.

**Example Input:**
```text
deletetag n/Veggies
```

**Example Output:**

Command Output Box:
```text
Tag deleted: Veggies
```
---

#### Filter by a tag: `filtertag`
**Format**: `filtertag n/TAG_NAME`

> Filters and show items that contain a specific tag.

**Example Input:**
```text
filtertag n/fruits
```

**Example Output:**

Command Output Box:
```text
Items filtered by tag: fruits
```

List Box:
```text
1. Apples 8 kg (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
```
