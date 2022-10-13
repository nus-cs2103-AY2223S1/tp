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

{% include_relative _ug/features/ItemFeatures.md %}

### Tag Features

{% include_relative _ug/features/TagFeatures.md %}

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
