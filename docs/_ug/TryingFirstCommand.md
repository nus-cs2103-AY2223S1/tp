<!-- markdownlint-disable-file first-line-h1 -->

Now, let us try out a command.

The format for commands are not identical. One command in FoodRem is the command to create a new item.

Format: `new n/ITEM_NAME [qty/QUANTITY] [unit/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE] [p/PRICE] [r/REMARKS]`

The first word of every command allows FoodRem to distinguish different commands. `new` tells FoodRem that this is the command to create a new item. [Flags](#flags) such as `n/` and `qty/` are delimiters that enable FoodRem to distinguish different parameters supplied by you without ambiguity. [Placeholders](#placeholders) such as `ITEM_NAME` and `QUANTITY` shows you what you should place in each portion of the command.

Notice that there is a pair of square brackets [] surrounding some parameters like `qty/QUANTITY`
in the format. This indicates that the parameter is **optional**. Each of these placeholders have a default value
based on the commands. These are documented in the [Commands](#commands) section for each command.

Suppose you just bought 30 kg of potatoes, today is 5th September 2022, and you do not feel the need to record
an expiry date, price or remarks for this item.

`ITEM_NAME`: Potatoes

`QUANTITY`: 30

`UNIT`: kg

`BOUGHT_DATE`: 05-09-2022


Note:

* The [Placeholder](#placeholders) section covers the restrictions for respective placeholders.
  For example, the date format of BOUGHT_DATE, certain characters you cannot use and the limit and precision of numbers.

The command you would like to enter into the command box would be:

`new n/Potatoes qty/30 u/kg bgt/05-09-2022`

Alternatively this command would do the same thing:

* `new qty/30 n/Potatoes bgt/05-09-2022 u/kg` (Reordering the flags)

These commands are invalid:

* `newn/Potatoesqty/30u/kgbgt/05-09-2022` (Removing space between command identifier and flags)
* `new qty/-48 n/PÖtátÖes bgt/05/09/22 u/|kg|` (Restrictions of placeholders not followed)

_Find out more about restrictions in the sections [Flags](#flags), [Placeholders](#placeholders) and [Commands](#commands)._

Let us try another command!

After creating the potatoes item, you decided to buy 40 kg more of potatoes.

Format: `inc id/INDEX [qty/QUANTITY]`

`inc` tells FoodRem that this is the command to increment the quantity of an item.
Suppose the `INDEX` for potatoes is `1` in the application, the command you
would like to enter into the command box is `inc id/12 qty/40`.

You should now have a better understanding of how commands are formatted and used. All commands are
consolidated in [Command Summary](#command-summary). 

Here is a checklist you can use before running a command:

* [ ] I know the restrictions of the command
* [ ] I know what flags are supplied to the command
* [ ] I know the restrictions of each placeholder
* [ ] I know the effects of not specifying each optional flag.
