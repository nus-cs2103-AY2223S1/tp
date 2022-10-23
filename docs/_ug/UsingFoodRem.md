<!-- markdownlint-disable-file first-line-h1 -->
Here is an overview of what FoodRem consists of and how you can perform a command.
### Items and Tags

{% include_relative _ug/ItemsTags.md %}

### Command Format

You will encounter FoodRem commands throughout this User Guide. Before you delve into the different commands in [Features](#features), let’s learn what a command consists of.

Here is an example:
![CommandExample](images/CommandExample.png)
A command consists of:
1. Command Word to tell FoodRem what action you wish to execute. These actions are covered in [Commands](#commands)
2. [Flags](#flags) to distinguish parameters
3. [Placeholders](#placeholders) that you can replace with your parameter inputs


### Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

| Flags | Related Placeholder   |
|-------|-----------------------|
| id/   | INDEX                 |
| n/    | ITEM_NAME<br>TAG_NAME |
| qty/  | QUANTITY              |
| unit/ | UNIT                  |
| buy/  | BOUGHT_DATE           |
| exp/  | EXPIRY_DATE           |
| p/    | PRICE                 |
| r/    | REMARKS               |


### Placeholders

Placeholders are words in UPPER_CASE to show you what parameters you can supply to a command.

{% include_relative _ug/Placeholders.md %}

### Trying your First Command

{% include_relative _ug/TryingFirstCommand.md %}
