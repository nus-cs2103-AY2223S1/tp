<!-- markdownlint-disable-file first-line-h1 -->
{Insert an image of tags}

#### Create a new tag: `newtag`

**Format**: `newtag n/TAG_NAME`
> Creates a new tag with the provided TAG_NAME.

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

> Lists all the tags that are currently in FoodRem.

**Example Input:**


```text
listtag
```

**Example Output:**

Command Output Box:
```text
Listed all tags:
Vegetables
```
---

#### Tag an item: `tag`
**Format**: `tag n/TAG_NAME id/INDEX`
> Tags an item (based on its index on the list) with a tag that exists in FoodRem.

**Example Input:**

```text
tag n/Vegetables id/1
```

**Example Output:**

Command Output Box:
```text
Item tagged succesfully
```
---

#### Untag an item: `untag`
**Format**: `untag n/TAG_NAME id/INDEX`
> Untags an item (based on its index on the list) from a tag that it is previously tagged to in FoodRem.

**Example Input:**
```text
untag n/Vegetables id/1
```

**Example Output:**

Command Output Box:
```text
Item untagged succesfully
```
___

#### Rename a tag: `renametag`

**Format**: `renametag n/TAG_NAME n/TAG_NAME`
> Renames a tag currently in FoodRem.

❗ Note: The first TAG_NAME in the command refers to the current tag you wish to rename while the second TAG_NAME refers to the new name you wish to rename the current tag to.

**Example Input:**
```text
renametag n/Vegetables n/Veggies
```

**Example Output:**

Command Output Box:
```text
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
> Filters and show items that contains a specific tag.

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
