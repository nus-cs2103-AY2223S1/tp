<!-- markdownlint-disable-file first-line-h1 -->
{Insert an image of tags}

#### Create a new tag

Command: `newtag n/TAG_NAME`
> Description: Creates a new tag with the provided TAG_NAME.

Example:

Input:
```text
newtag n/Vegetables
```

Output:
```text
New tag added: Vegetables
```
---

#### List all tags

Command: `listtag`

> Description: Lists all the tags that are currently in FoodRem.

Example:

Input

```text
listtag
```

Output

```text
Listed all tags:
Vegetables
```
---

#### Tag an item
Command: `tag n/TAG_NAME id/INDEX`
> Description: Tags an item (based on its index on the list) with a tag that exists in FoodRem.

Example:

Input:
```text
tag n/Vegetables id/1
```

Output:
```text
Item tagged succesfully
```
---

#### Untag an item
Command: `untag n/TAG_NAME id/INDEX`
> Description: Untags an item (based on its index on the list) from a tag that it is previously tagged to in FoodRem.

Example:

Input:
```text
untag n/Vegetables id/1
```

Output:
```text
Item untagged succesfully
```
___

#### Rename a tag

Command: `renametag n/TAG_NAME n/TAG_NAME`
> Description: Renames a tag currently in FoodRem.

❗ Note: The first TAG_NAME in the command refers to the current tag you wish to rename while the second TAG_NAME refers to the new name you wish to rename the current tag to.

Example:

Input:
```text
renametag n/Vegetables n/Veggies
```

Output:
```text
Renamed tag: Veggies
```
---

#### Delete a tag

Command: `deletetag n/TAG_NAME`
> Description: Deletes a tag that exists in FoodRem.

Example:

Input:
```text
deletetag n/Veggies
```

Output:
```text
Tag deleted: Veggies
```
---

#### Filter by a tag
Command: `filtertag n/TAG_NAME`
> Description: Filters and show items that contains a specific tag.

Example:

Input:
```text
filtertag n/fruits
```

Output:
```text
Items filtered by tag: fruits
```

