<!-- markdownlint-disable-file first-line-h1 -->
#### Item

An Item in FoodRem represents something in your inventory. This can be an ingredient, a piece of equipment, and more. Feel free to include or exclude certain attributes for each item, although you must minimally provide a name for the item.

The following are the attributes stored for each item:

* Item name
* Item quantity
* Item unit (Unit of measurement e.g. `kg`, `packets`)
* Item bought date
* Item expiry date
* Item price
* Item remark
* Item tags

FoodRem Items are unique by name and case-sensitive. This means you cannot add two or more items of the same name.

Restrictions for all attributes can be found in the [Placeholders](#placeholders) section.

#### Tag

A Tag in FoodRem serves as a means to categorise items. These tags are also unique and case-sensitive.

We can tag multiple items with the same tag and each item can have multiple tags. These Tags are optional.

Feel free to add tags as you see fit to organize your inventory. Examples of how you may use a tag can include:

* Categorizing food items, e.g. `Vegetable`, `Herb`, `Condiment`, `Meat`.
* Marking where the item is stored, e.g. `Fridge`, `Cupboard`, `Shelf`
* Noting its perishability, e.g. `Perishable`, `Non-Perishable`

Tags can be renamed and these changes would be reflected on all items immediately.

FoodRem Tags are unique by name and case-sensitive. This means you cannot add two or more tags of the same name.
