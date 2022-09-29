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

## How to use the user-guide

Something goes here...

# Tables of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Information Stored](#information-stored)

   3.1 [Item](#info-Item)

   3.2 [Tag](#infoTag)

4. [Commands](#commands)

   4.1 [Items](#item)

   &emsp; 4.1.1 [Create a new item](#itemNew)

   &emsp; 4.1.2 [Increase the quantity of an item](#itemDec)

   &emsp; 4.1.3 [Decrease the quantity of an item](#itemInc)

   &emsp; 4.1.4 [Update an item](#itemSet)

   &emsp; 4.1.4 [Delete an item](#itemDelete)

   4.2 [Tags](#tag)

   &emsp; 4.2.1 [Create a tag](#tagCreate)

   &emsp; 4.2.2 [Rename a tag](#tagRename)

   &emsp; 4.2.3 [Tag an item](#tagItem)

   &emsp; 4.2.4 [Delete a tag](#tagDelete)

   4.3 [Listing](#list)

   &emsp; 4.3.1 [List all items](#list-items)

   &emsp; 4.3.2 [List all tags](#list-tags)

   4.4 [Searching](#find)

   &emsp; 4.4.1 [Search for an item](#itemFind)

   &emsp; 4.4.2 [Search for a tag](#tagFind)

   4.5 [Sorting](#sort)

   4.6 [Help](#help)

   4.7 [Exit the programme](#exit)
5. [Command Summary](#command-summary)
6. [Troubleshooting](#troubleshooting)
7. [FAQ](#faq)
8. [Future Extensions](#future-extensions)
9. [Acknowledgements](#acknowledgements)
10. [Glossary](#glossary)

## Quick Start

Something goes here...

## Features

Something goes here...

## Information Stored

### Item

### Tag

## Commands

#### List items

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

####  List tags

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

### Find

Command: `find NAME`

> Description: Find an inventory item based on the given keywords
> The search is case-insensitive. (e.g apples will match Apples)
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

#### Bye

Command: `bye`

> Description: Exits Foodrem program.

---

Example:
Input

```
bye
```

## Command Summary

<table>
<thead>
  <tr>
    <th>Category</th>
    <th colspan="2">Action</th>
    <th>Command</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td rowspan="2">Listing</td>
    <td colspan="2">List all items</td>
    <td>list items</td>
  </tr>
  <tr>
    <td colspan="2">List all tags</td>
    <td>list tags</td>
  </tr>
  <tr>
    <td rowspan="10">Items</td>
    <td colspan="2">New item</td>
    <td>[item] new n/&lt;NAME&gt;<br>e.g.: [item] new n/potato</td>
  </tr>
  <tr>
    <td colspan="2">Delete item</td>
    <td>[item] delete INDEX<br>e.g.: [item] delete 1</td>
  </tr>
  <tr>
    <td colspan="2">Add QUANTITY items to stock</td>
    <td>[item] inc INDEX QUANTITY<br>e.g.: [item] inc 1 10</td>
  </tr>
  <tr>
    <td colspan="2">Remove QUANTITY items from stock</td>
    <td>[item] dec INDEX QUANTITY<br>e.g.: [item] dec 1 10</td>
  </tr>
  <tr>
    <td colspan="2">Search for an item by name (case-insensitive)</td>
    <td>[item] find NAME<br>e.g.: [item] find potatoes</td>
  </tr>
  <tr>
    <td colspan="2">Update an item (multiple fields at once)</td>
    <td>[item] set INDEX ARGS</td>
  </tr>
  <tr>
    <td rowspan="4">ARGS is at least one of the following:<br><br>Where there are repeated arguments, the last one takes precedence.</td>
    <td>name/NAME</td>
    <td>e.g.: [item] set 1 n/potatoes</td>
  </tr>
  <tr>
    <td>qty/QUANTITY</td>
    <td>e.g.: [item] set 1 qty/2</td>
  </tr>
  <tr>
    <td>exp/DATE</td>
    <td>e.g.: [item] set 1 exp/2022-11-11</td>
  </tr>
  <tr>
    <td>buy/DATE</td>
    <td>e.g.: [item] set 1 buy/2022-11-11</td>
  </tr>
  <tr>
    <td rowspan="5">Tags</td>
    <td colspan="2">Create a new tag</td>
    <td>create TAG_NAME e.g.: tag create food</td>
  </tr>
  <tr>
    <td colspan="2">Delete an existing tag</td>
    <td>delete TAG_NAME<br>e.g: tag delete 1</td>
  </tr>
  <tr>
    <td colspan="2">Rename an existing tag</td>
    <td>rename TAG_NAME n/NEW_NAME<br>e.g.: tag rename Potato n/Potatoes</td>
  </tr>
  <tr>
    <td colspan="2">Tag an item with a specific tag</td>
    <td>item ITEM_INDEX TAG_NAME<br>e.g: tag 1 potato</td>
  </tr>
  <tr>
     <td colspan="2">Find a tag</td>
     <td>tag find TAG_NAME<br>e.g: tag find vegetable</td>
  </tr>
  <tr>
    <td rowspan="2">General</td>
    <td colspan="2">Shows a help dialog with a list of available commands</td>
    <td>help</td>
  </tr>
  <tr>
    <td colspan="2">Exits the application</td>
    <td>bye</td>
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
