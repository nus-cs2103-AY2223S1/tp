<!-- markdownlint-disable-file first-line-h1 -->
This section provides you with instructions to test the app manually. It also covers how to launch and shut down the application.

```note
These instructions only provide a starting point for testers to work on; testers are expected to do more _exploratory_ testing.
```

### Launch

#### Initial launch

1. Download the jar file and copy it into an empty folder
1. Double-click the jar file.
   * Expected output: Shows the GUI with a set of sample items.

### Items

#### Create a new Item

`new n/Potato qty/2 u/kg bgt/10-10-2022`

Expected Output in the [[ item-list-box:Item List Box ]]: 
New item added into the list.

Expected Output in the [[ command-output-box:Command Output Box ]]:
New item added message is displayed with the item details.

`new qty/2`

Expected Output in the [[ command-output-box:Command Output Box ]]:
Error message for invalid command format.

#### List all items

Prerequisite: 
There is at least 1 item stored in FoodRem.

`list`

Expected Output in the [[ item-list-box:Item List Box ]]: 
All items stored in FoodRem is displayed.

Expected Output in the [[ command-output-box:Command Output Box ]]:
Listed all items message.

#### Search for an Item

Prerequisite: 
There is only one item named `Potato` stored in FoodRem.

`find Potato`

Expected Output in the [[ item-list-box:Item List Box ]]:
`Potato` item is displayed.

Expected Output in the [[ command-output-box:Command Output Box ]]:
1 item listed message.

`find strawberry`

Expected Output in the [[ item-list-box:Item List Box ]]:
No items display 

Expected Output in the [[ command-output-box:Command Output Box ]]:
0 items listed message.

#### Sort all items by an attribute

Prerequisite:
There are a few items stored in FoodRem and shown in the [[ item-list-box:Item List Box ]] with different names.

`sort n/` 

Expected Output in the [[ item-list-box:Item List Box ]]: 
Items are sorted in ascending alphabetical order based on name.

Expected Output [[ command-output-box:Command Output Box ]]:
Items sorted message.

#### View the information of an Item

Prerequisite: 
There is at least 1 item shown in the [[ item-list-box:Item List Box ]].

`view 1` 

Expected Output in the [[ item-list-box:Item List Box ]]: 
Still showing the same list as before.

Expected Output [[ command-output-box:Command Output Box ]]:
Details of first item in Item List Box shown. 
Displayed information includes the name, quantity, unit, bought date, expiry date, price, remarks and tags of items.

#### Increment the quantity of an item

Prerequisite: 
The first item in the Item List Box has a quantity of 1.

`inc 1 qty/20` 

Expected Output in the [[ item-list-box:Item List Box ]]: 
Still showing the same list as before but with first item quantity increased by 20.

Expected Output [[ command-output-box:Command Output Box ]]:
Incremented item quantity message with details of first item in Item List shown.

`inc 1`

Expected Output in the [[ item-list-box:Item List Box ]]:
Still showing the same list as before but with first item quantity increased by 1.

Expected Output [[ command-output-box:Command Output Box ]]:
Incremented item quantity message with details of first item in Item List shown.

#### Decrement the quantity of an item

Prerequisite: 
The first item in the Item List Box has a quantity of 3.

`dec 1 qty/2` 

Expected Output in the [[ item-list-box:Item List Box ]]: 
Still showing the same list as before but with first item quantity decreased by 2.

Expected Output [[ command-output-box:Command Output Box ]]:
Decremented item quantity message with details of first item in Item List shown.

`dec 1`

Expected Output in the [[ item-list-box:Item List Box ]]:
Still showing the same list as before but with first item quantity decreased by 1.

Expected Output [[ command-output-box:Command Output Box ]]:
Decremented item quantity message with details of first item in Item List shown.

#### Edit the information of an item

Prerequisite: 
The first item in the [[ item-list-box:Item List Box ]] has a name of `Potato`.

`edit 1 n/Tomato` 

Expected Output [[ item-list-box:Item List Box ]]: 
The `Potato` item has been renamed to `Tomato` and this is now reflected in the [[ item-list-box:Item List Box ]].

Expected Output [[ command-output-box:Command Output Box ]]:
Item edited message with details of the edited item shown.

#### Add a remark to an item

Prerequisite: 
The first item in the [[ item-list-box:Item List Box ]] has no remarks.

`rmk 1 r/For Party` 

Expected Output in the [[ item-list-box:Item List Box ]]: 
Same list as shown before.

Expected Output [[ command-output-box:Command Output Box ]]:
Remark updated message with the details of the item that has remark updated shown.

#### Deleting an Item

Prerequisite:
There is at least 1 item in the [[ item-list-box:Item List Box ]].

`del 1`

Expected Output in the [[ item-list-box:Item List Box ]]:
First item in the list is deleted.

Expected Output [[ command-output-box:Command Output Box ]]:
Deleted item message and its details.

`del 0`

Expected Output [[ command-output-box:Command Output Box ]]:
Error message for invalid command format.

### Tags

#### Create a new tag

`newtag n/Fruits`

Expected Output [[ command-output-box:Command Output Box ]]:
New tag added and its details.

`newtag n/\`

Expected Output [[ command-output-box:Command Output Box ]]:
Error message for invalid name format.

#### List all tags

`listtag`

Expected Output [[ command-output-box:Command Output Box ]]:
All current exisiting tags of FoodRem are listed.

#### Tag an Item

Prerequisite: Tag and Item to be tagged exists.

`tag 1 n/Food`

Expected Output in the [[ item-list-box:Item List Box ]]:
The specified item is tagged.

Expected Output [[ command-output-box:Command Output Box ]]:
Message indicating item has been successfully tagged.

`tag n/Food`

Expected Output [[ command-output-box:Command Output Box ]]:
Error message for invalid command format.

#### Untag an Item

Prerequisite: Tag and Item to be tagged exists. Tag is also currently tagged to the specified item.

`untag 1 n/Food`

Expected Output in the [[ item-list-box:Item List Box ]]:
The specified item is untagged from the tag.

Expected Output [[ command-output-box:Command Output Box ]]:
Message indicating item has been successfully untagged.

`untag n/Food`

Expected Output [[ command-output-box:Command Output Box ]]:
Error message for invalid command format.

#### Rename a Tag

Prerequisite:
A tag with a tag name `Fruits` exist and a tag with a tag name `Food` does not exist.

`renametag n/Fruits n/Food`

Expected Output in the [[ item-list-box:Item List Box ]]:
The `Fruits` tag is renamed to `Food`.

Expected Output [[ command-output-box:Command Output Box ]]:
Details of original and renamed tags.

`renametag n/Fruits n/Fruits`

Expected Output [[ command-output-box:Command Output Box ]]:
Error message for renaming to duplicate name.

#### Delete a Tag

Prerequisite: 
A tag with a tag name `Food` currently exists in FoodRem.

`deletetag n/Food`

Expected Output in the [[ item-list-box:Item List Box ]]:
`Food` tags if shown, are deleted.

Expected Output [[ command-output-box:Command Output Box ]]:
Tag deleted details.

#### Filter by a Tag

Prerequisite: 
A tag with a tag name of `fruits` currently exists in FoodRem and is tagged to a few items.

`filtertag n/fruits`

Expected Output in the [[ item-list-box:Item List Box ]]:
Items with the `fruits` tag are shown.

Expected Output [[ command-output-box:Command Output Box ]]:
Details of the tag and number of items filtered.

### Statistics

#### Display statistics

`stats`

Expected Output in the [[ item-list-box:Item List Box ]]:
No changes in the displayed items.

Expected Output [[ command-output-box:Command Output Box ]]:
Statistics which include total cost incurred due to food wastage, top 3 common tags and top 3 items with the highest value.

### Help

`help`

Expected Output: 
The [[ help-window:Help Window ]] pops up and shows a general help message.

`help new`

Expected Output: 
The [[ help-window:Help Window ]] pops up and shows a general help message with the help information for the `new` command.

### Reset

`reset`

Expected Output in the [[ item-list-box:Item List Box ]]:
All items in the [[ item-list-box:Item List Box ]] are cleared.

Expected Output in the [[ command-output-box:Command Output Box ]]:
FoodRem reset success message.

### Exit

`exit`

Expected Output: 
FoodRem application closes.
