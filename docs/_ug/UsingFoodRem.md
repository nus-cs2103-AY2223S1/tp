<!-- markdownlint-disable-file first-line-h1 -->
Here is an overview of what FoodRem consists of and how you can perform a command.

### Layout

{% include_relative _ug/Layout.md %}

### Items and Tags

{% include_relative _ug/ItemsTags.md %}

### Command Format

You will encounter FoodRem commands throughout this User Guide. Before you delve into the different commands in [Commands](#commands), let’s learn what a command consists of.

Here is an example:

![CommandExample](images/CommandExample.png)

A command consists of:

1. Command Word to tell FoodRem what action you wish to execute. These actions are covered in [Commands](#commands)
1. [Flags](#flags) to distinguish parameters
1. [Placeholders](#placeholders) that you can replace with your parameter inputs

### Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

| Flags | Related Placeholder   |
|-------|-----------------------|
| n/    | ITEM_NAME<br>TAG_NAME |
| qty/  | QUANTITY              |
| u/    | UNIT                  |
| buy/  | BOUGHT_DATE           |
| exp/  | EXPIRY_DATE           |
| p/    | PRICE                 |
| r/    | REMARKS               |

### Placeholders

Placeholders are words in uppercase to show you what type of parameters you can supply to a command.

{% include_relative _ug/Placeholders.md %}

### Trying your first command

{% include_relative _ug/TryingFirstCommand.md %}
