<!-- markdownlint-disable-file first-line-h1 -->
Hello! This is the command box where we key in our commands.

{Image of a command box}

([Navigating around the application](#navigating-around-the-application))

The format for commands are not identical. One command in FoodRem is the command to create a new item.

Format: `new n/ITEM_NAME [qty/QUANTITY] [unit/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]`

The first word of every command allows FoodRem to distinguish different commands.
`new` tells FoodRem that this is the command to create a new item.
[Flags](#flags) such as `n/` and `qty/` are delimiters that enable FoodRem to distinguish different parameters
supplied by you without ambiguity. [Placeholders](#placeholders) such as `ITEM_NAME` and `QUANTITY` shows you
what you should place in each portion of the command.

Suppose you just bought 30 kg of potatoes, today is 5th September 22, and you do not feel the need to record
an expiry date for the potatoes.

`ITEM_NAME`: Potatoes

`QUANTITY`: 30

`UNIT`: kg

`BOUGHT_DATE`: 05-09-22

Note:

* The [Placeholder](#placeholders) section covers the restrictions for respective placeholders.
  For example, the date format of BOUGHT_DATE, certain characters you cannot use and the limit and precision of numbers.

The command you would like to enter into the command box would be:

`new n/Potatoes qty/30 unit/kg bgt/05-09-22`

Alternatively these commands would do the same thing:

* `new n/Potatoesqty/30unit/kgbgt/05-09-22` (Omitting space between tags)
* `new qty/30 n/Potatoes bgt/05-09-22 unit/kg` (Reordering the flags)

These commands are invalid:

* `newn/Potatoesqty/30unit/kgbgt/05-09-22` (Removing space between command identifier and flag)
* `new qty/-48 n/PÖtátÖes bgt/05/09/22 unit/|kg|` (Restrictions of placeholders not followed)

Find out more about restrictions in the sections [Flags](#flags), [Placeholders](#placeholders) and
[Features](#features).

Notice that there is a pair of square brackets [] surrounding some parameters like `qty/QUANTITY`
in the format. This indicates that the parameter is optional. Each of these placeholders have a default value
based on the commands. These are documented in the [Features](#features) section for each command.

Let us try another command!

After creating the potatoes item, you decided to buy 40 kg more of potatoes.

Format: `inc id/INDEX_LIST [qty/QUANTITY]`

`inc` tells FoodRem that this is the command to increment the quantity of an item.
Suppose the `INDEX` for potatoes is `12` in the application, the command you
would like to enter into the command box is `inc id/12 qty/30`.

Note: `INDEX_LIST` can be an `INDEX` (More information in [Placeholders](#placeholders))

Now you should have a general sensing of how commands are used and how to interpret formats. All commands are
consolidated in [Command Summary](#command-summary). However, it is **highly recommended** that you read through
the User Guide in a **sequential order** up until the section [Features](#features) where you can find all the
information you need for each command. This covers more details on syntax and common errors. Before using any
command, take note of the behaviour when certain tags are not included and restrictions.

Checklist before using a command:

* [ ] I know the restrictions of the command
* [ ] I know what flags are supplied to the command
* [ ] I know the restrictions of each placeholder
* [ ] I know the effects of not specifying each optional flag.
