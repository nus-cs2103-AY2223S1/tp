<!-- markdownlint-disable-file first-line-h1 -->
### Item

An item is a food item that you would like to include in FoodRem.
The following are all the attributes store for each item:

* Item name
* Item quantity
* Item unit (Unit of measurement e.g. `kg`, `packets`)
* Item bought date
* Item expiry date

All items in FoodRem are unique. This means that no two items should have the same name.
Uniqueness is not case-sensitive. "potato" and "POTATO" are treated as identical.

FoodRem allows you to include an item that has an expiry date before a bought date.
However, it will warn you that you are including an expired item into the inventory.

Restrictions for other attributes can be found in [Placeholders](#placeholders).

### Tag

A tag serves as a means to categorise items. These tags are also unique and not case-sensitive.

We can tag multiple items with the same tag and each item can have multiple tags. Tags are optional
and serve as a means to easily categorise items.

Tags can be renamed and these changes would be reflected on all items immediately.
