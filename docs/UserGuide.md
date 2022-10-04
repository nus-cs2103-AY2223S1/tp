---
layout: page
title: User Guide
---
# FoodRem User Guide

## About

We want you to focus on what is important: serving your customers, improving menu recipes, and transforming your
business into the next successful story. FoodRem is an application that enables you to efficiently keep track of
perishable goods in your daily operations. It is a convenient and efficient administrative tool to ensure less food
wastage and a constant supply of fresh food to increase revenue and improve the quality of food. With a few quick
commands, you can have complete control of your perishable goods.

## Key Features

1. Add, update and delete inventory items
2. Search and sort food items by:
    - Name
    - Quantity
    - Bought date
    - Expiry date
3. Tag items to group them into categories

## Purpose, Scope and Audience

Something goes here...

# Tables of Contents

1. [How to use the user-guide](#how-to-use-the-user-guide)

2. [Quick Start](#quick-start)

3. [Ui Components](#ui-components)

4. [Items and Tags](#items-and-tags)

5. [Flags](#flags)

6. [Placeholders](#placeholders)

7. [Features](#features)

   6.1. [Item Features](#item-features)

   &emsp; 6.1.1. [Create a new item](#create-a-new-item)

   &emsp; 6.1.2. [List all items](#list-all-items)

   &emsp; 6.1.3. [Search for an item](#search-for-an-item)
   
   &emsp; 6.1.4. [Sort all items by an attribute](#sort-all-items-by-an-attribute)

   &emsp; 6.1.5. [View the information of an item](#view-the-information-of-an-item)

   &emsp; 6.1.6. [Increase the quantity of an item](#increase-the-quantity-of-an-item)

   &emsp; 6.1.7. [Decrease the quantity of an item](#decrease-the-quantity-of-an-item)

   &emsp; 6.1.8. [Update the information of an item](#update-the-information-of-an-item)

   &emsp; 6.1.9. [Delete an item](#delete-an-item)

   6.2. [Tag Features](#tag-features)

   &emsp; 6.2.1. [Create a new tag](#create-a-new-tag)

   &emsp; 6.2.2. [List all tags](#list-all-tags)

   &emsp; 6.2.3. [Tag an item](#tag-an-item)

   &emsp; 6.2.4. [Untag an item](#untag-an-item)

   &emsp; 6.2.5. [Rename a tag](#rename-a-tag)

   &emsp; 6.2.6. [Delete a tag](#delete-an-item)

   6.3. [Receive help during usage](#receive-help-during-usage)

   6.4. [Reset the application](#reset-the-application)

   6.5. [Exit the application](#exit-the-application)

8. [Command Summary](#command-summary)

9. [Troubleshooting](#troubleshooting)

10. [FAQ](#faq)

11. [Future Extensions](#future-extensions)

12. [Acknowledgements](#acknowledgements)

13. [Glossary](#glossary)

## How to use the user-guide

Something goes here...

## Quick Start

Something goes here...

## Items and Tags

Something goes here...

## Ui Components

{Image of entire application}

<table>
<thead>
  <tr>
    <th>Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Command Input</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>Command result</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>List Component</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>View Component</td>
    <td>{image}</td>
  </tr>
</tbody>
</table>

## Flags
Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

<table>
<thead>
  <tr>
    <th>Flags</th>
    <th>Related Placeholder</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>id/</td>
    <td>INDEX<br>INDEX_LIST</td>
  </tr>
  <tr>
    <td>n/</td>
    <td>ITEM_NAME<br>TAG_NAME</td>
  </tr>
  <tr>
    <td>qty/</td>
    <td>QUANTITY</td>
  </tr>
  <tr>
    <td>type/</td>
    <td>TYPE</td>
  </tr>
  <tr>
    <td>buy/</td>
    <td>BOUGHT_DATE</td>
  </tr>
  <tr>
    <td>exp/</td>
    <td>EXPIRY DATE</td>
  </tr>
</tbody>
</table>

## Placeholders
Placeholders are words in UPPER_CASE to show you what parameters you can supply to a command.
<table>
<thead>
  <tr>
    <th>Placeholders</th>
    <th>Related Flags</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>INDEX</td>
    <td>id/</td>
    <td>The INDEX of an item is the number to the left of the item name in the List Component.<br>INDEX is a whole number larger than 0. <br/><br/><br><strong>IMPORTANT</strong>: <br>The index of an item is dependent on the current search performed.<br>There is a limit of 10000 for the index.<br>Do not include thousands separators. <br><br/><strong>Valid Examples</strong>:<br>1<br>124<br><br/><strong>Invalid Examples:</strong><br>-1<br>1.5<br>1,132<br>1 132<br>10001</td>
  </tr>
  <tr>
    <td>INDEX_LIST</td>
    <td>id/</td>
    <td>The INDEX_LIST is a list of INDEX separated by commas. <br>All restrictions to INDEX are relevant.<br/><br><strong>IMPORTANT</strong>: <br>There is a limit of 20 indexes in the list.<br>Do not insert unnecessary commas or spaces.<br>Do not include duplicates in the list.<br><br/><strong>Valid Examples</strong>:<br>1<br>3214<br>3,2,1,4<br><br/><strong>Invalid Examples:</strong><br>,<br>3,2,1,4,<br>3,3,3,3<br>3,,2,1,,,4,<br>3, 2, 1, 4<br>3 2 1 4<br>3/2/1/4<br>1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21</td>
  </tr>
  <tr>
    <td>ITEM_NAME</td>
    <td>n/</td>
    <td>The ITEM_NAME is the term we use to identify an item.<br>ITEM_NAME is a short text.<br/><br><strong>IMPORTANT</strong>: <br>Only English characters, numbers, space, and the following symbols are accepted:<br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 150 characters. Below is a text that is 200 characters long:<br>“Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum vitae purus at nisi aliquet convallis. Aliquam scelerisque in erat ac sodales sed.”<br>ITEM_NAME is unique<br>ITEM_NAME cannot be blank <br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>Potatoes<br>Tomatoes! (Sauce :D)?<br>     <br><br/><strong>Invalid Examples:</strong><br>黃瓜<br>Carrots/Cucumbers<br>Carrots|Cucumbers<br>Carrots\Cucumbers</td>
  </tr>
  <tr>
    <td>TAG_NAME</td>
    <td>n/</td>
    <td>The TAG_NAME is the term we use to identify a tag.<br>TAG_NAME is a short text.<br/><br><strong>IMPORTANT</strong>:<br>Only English characters, numbers, space, and the following symbols are accepted: <br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 30 characters in a TAG_NAME. Below is a text that is 30 characters long:<br>“Lorem ipsum dolor sit posuere.”<br>TAG_NAME is unique<br>TAG_NAME cannot be blank <br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>Food<br>Yummy! (Delicious :D)?<br><br/><strong>Invalid Examples:</strong><br>Одноразовый<br>Food/Condiments<br>Food|Condiments<br>Food\Condiments<br>Food Food Food Food Food Food Food</td>
  </tr>
  <tr>
    <td>QUANTITY</td>
    <td>qty/</td>
    <td>The QUANTITY is the number representing the amount of an item.<br>QUANTITY is a number larger than 0. It has an accuracy of up to 4 decimal places.<br/><br><strong>IMPORTANT</strong>:<br>There is a limit of 10000000 for the quantity.<br>Do not include thousands separators. <br>Do not include mathematical symbols<br><br/><strong>Valid Examples</strong>:<br>12<br>12.1234<br>1234567<br><br/><strong>Invalid Examples:</strong><br>12.12345<br>1,234,567<br>1 + 1<br>1/2<br>π</td>
  </tr>
  <tr>
    <td>TYPE</td>
    <td>type/</td>
    <td>The TYPE is an optional text indicating the unit of an item.<br>TYPE is a short text.<br/><br><strong>IMPORTANT</strong>:<br>Only English characters, numbers, space, and the following symbols are accepted: <br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 20 characters in a TYPE.<br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>kg<br>Packets<br><br/><strong>Invalid Examples:</strong><br>Containers (1000 grams)<br>Containers/grams<br>Containers|grams<br>Containers\grams</td>
  </tr>
  <tr>
    <td>BOUGHT_DATE</td>
    <td>bgt/</td>
    <td>The BOUGHT_DATE is an optional date indicating when the item was bought.<br>BOUGHT_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
  </tr>
  <tr>
    <td>EXPIRY_DATE</td>
    <td>exp/</td>
    <td>The EXPIRY_DATE is an optional date indicating when the item will expire.<br>EXPIRY_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
  </tr>
</tbody>
</table>

## Features

### Item Features

{Insert an image of items}

#### Create a new item

Command: `item new ITEM_NAME`

> Description: Creates a new item with the provided item_name.

---

Example: 

Input
```
item new potato
```
Output
```
Item  “potato” successfully created
```

#### List all items

Command: `list`

> Description: Lists all the items in the inventory.

---

Example:

Input

```
list
```

Output

```
Here are the items in your inventory:
Onions
Details about onions
Tomatoes
Details about tomatoes
Chicken wings
Details about chicken wings
```

### Search for an item

Command: `find NAME`

> Description: Find an inventory item based on the given keywords
> The search is case-insensitive. (e.g. apples will match Apples)
> The order of the keyword does not matter e.g (rose apple will match apple rose)
> Only the item name is searched

---

Example:

Input

```
find apple
```

Output

```
Here are the results matching your search
Green apple
Rose apple
```

#### Sort all items by an attribute

#### View the information of an item

#### Increase the quantity of an item

#### Decrease the quantity of an item

#### Update the information of an item

#### Delete an item

Command: `[item] delete ITEM_INDEX`

> Description: Deletes a specified item. Returns a warning if the item does not exist. 

---

Example: 

Input
```
delete 1
```
Output
```
(Item exists): Item “potato” successfully deleted!
(Item does not exist): No item to be found at index 1. Use “list items” or “find NAME” to find the index of the item to be deleted.
```

### Tag Features

{Insert an image of tags}

#### Create a new tag

#### List all tags

Command: `list tags`

> Description: Lists all the tags that the user has created.

---

Example:

Input

```
list tags
```

Output

```
Here are the tags that are available:
Fruits
Vegetables
Spices
```

#### Tag an item

#### Untag an item

#### Rename a tag

#### Delete a tag

### Receive help during usage

Command: `help`

> Description: Displays a list of commands that can be used.

---

Example:

Input

```
help
```

Output:

```
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
    Exits Foodrem program.

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

```
bye
```

## Command Summary

### Item Commands

<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Create a new item</td>
    <td>new n/ITEM_NAME [qty/QUANTITY] [type/TYPE] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]<br>Valid Examples:<br>new n/Potato qty/70 type/kg bgt/22-02-11 exp/22-03/11</td>
  </tr>
  <tr>
    <td>List all items</td>
    <td>list<br>Valid Examples:<br>list</td>
  </tr>
  <tr>
    <td>Search for an item</td>
    <td>find n/ITEM_NAME<br>Valid Examples:<br>find n/Potato</td>
  </tr>
  <tr>
    <td>Sort an item by name, quantity, type, bought date or expiry date.</td>
    <td>sort [n/] [qty/] [type/] [bgt/] [exp/]<br>Valid Examples:<br>sort n/<br>sort qty/<br>sort qty/ bgt/<br>Invalid Examples:<br>sort</td>
  </tr>
  <tr>
    <td>View information about an item</td>
    <td>view id/INDEX<br>Valid Examples:<br>view id/1</td>
  </tr>
  <tr>
    <td>Increase the quantity of an item</td>
    <td>inc id/INDEX_LIST [qty/QUANTITY]<br>Valid Examples:<br>inc id/1 qty/100<br>inc id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Decrease the quantity of an item</td>
    <td>dec id/INDEX_LIST [qty/QUANTITY]<br>Valid Examples:<br>dec id/1 qty/100<br>dec id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Update the information of an item</td>
    <td>set id/INDEX_LIST [n/ITEM_NAME] [qty/QUANTITY] [type/TYPE] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]<br>IMPORTANT:<br>Do not update multiple items to have the same name<br>Valid Examples:<br>set id/1 n/Potatoes qty/60 type/kg<br>set id/1,2,3 qty/60</td>
  </tr>
  <tr>
    <td>Delete an item</td>
    <td>del id/INDEX_LIST<br>Valid Examples:<br>del id/1<br>del id/1,2,3</td>
  </tr>
</tbody>
</table>

### Tag Commands

<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Create a new item</td>
    <td>new n/ITEM_NAME [qty/QUANTITY] [type/TYPE] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]<br>Valid Examples:<br>new n/Potato qty/70 type/kg bgt/22-02-11 exp/22-03/11</td>
  </tr>
  <tr>
    <td>List all items</td>
    <td>list<br>Valid Examples:<br>list</td>
  </tr>
  <tr>
    <td>Search for an item</td>
    <td>find n/ITEM_NAME<br>Valid Examples:<br>find n/Potato</td>
  </tr>
  <tr>
    <td>Sort an item by name, quantity, type, bought date or expiry date.</td>
    <td>sort [n/] [qty/] [type/] [bgt/] [exp/]<br>Valid Examples:<br>sort n/<br>sort qty/<br>sort qty/ bgt/<br>Invalid Examples:<br>sort</td>
  </tr>
  <tr>
    <td>View information about an item</td>
    <td>view id/INDEX<br>Valid Examples:<br>view id/1</td>
  </tr>
  <tr>
    <td>Increase the quantity of an item</td>
    <td>inc id/INDEX_LIST [qty/QUANTITY]<br>Valid Examples:<br>inc id/1 qty/100<br>inc id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Decrease the quantity of an item</td>
    <td>dec id/INDEX_LIST [qty/QUANTITY]<br>Valid Examples:<br>dec id/1 qty/100<br>dec id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Update the information of an item</td>
    <td>set id/INDEX_LIST [n/ITEM_NAME] [qty/QUANTITY] [type/TYPE] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]<br>IMPORTANT:<br>Do not update multiple items to have the same name<br>Valid Examples:<br>set id/1 n/Potatoes qty/60 type/kg<br>set id/1,2,3 qty/60</td>
  </tr>
  <tr>
    <td>Delete an item</td>
    <td>del id/INDEX_LIST<br>Valid Examples:<br>del id/1<br>del id/1,2,3</td>
  </tr>
</tbody>
</table>

### Other Commands

<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Shows a help dialog with a list of available commands</td>
    <td>help<br>Valid Examples:<br>help</td>
  </tr>
  <tr>
    <td>Reset the application</td>
    <td>reset<br>Valid Example:<br>reset</td>
  </tr>
  <tr>
    <td>Exit the application</td>
    <td>exit<br>Valid Examples:<br>exit</td>
  </tr>
</tbody>
</table>

## Troubleshooting

Something goes here...

## FAQ

Something goes here...

## Future Extensions

(NOT COMPLETED)

1. Food expiring soon / Date food bought
   **Glorified search and sort**
   a. Upgrade sort and search b. Sort food items by quantity c. Sort food items by name d. Sort food items by expiry
   date e. Sort food items by purchase date

2. Food buffer a. Rainbow UI / Dashboard b. Optional : Minimum acceptable quantity c. Optional : Percentage of stock
   expiring

3. Purchasing (Hard -> Will not see benefit immediately)
   a. History + Statistics b. Inventory need a price of items

4. (Last priority) Order management a. Grouping of items b. Creation of menu with specific items c. Record menu items
   bought d. Statistics

## Acknowledgements

Something goes here...

## Glossary

Something goes here...
