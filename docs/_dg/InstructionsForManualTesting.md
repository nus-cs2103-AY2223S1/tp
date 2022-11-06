<!-- markdownlint-disable-file first-line-h1 -->
This section provides you with instructions to test the app manually. It also covers how to launch and shut the application down.

```note
These instructions only provide a starting point for testers to work on; testers are expected to do more _exploratory_ testing.
```

### Launch and Shutdown

#### Initial launch

1. Download the jar file and copy into an empty folder
1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

### Items

#### Create a new Item

1. Test case: `new n/Potato qty/2 u/kg bgt/10-10-2022  exp/11-11-2022 p/4 r/favourite` <br> Expected Output in Item List Box: New item added into the list. <br> Expected Output in Command Output Box: New item added message and its details.
1. Test case: `new qty/2` <br> Expected Output in Command Box: Error message for invalid command format.

#### List all items

Prerequisite: There is at least 1 item stored in FoodRem.

1. Test case: `list`<br> Expected Output in Item  List Box: All items stored in FoodRem displayed. <br>Expected Output in Command Output Box: Listed all items message.

#### Search for an Item

Prerequisite: Suppose there is only one item named `Potato` stored in FoodRem.

1. Test case: `find Potato`<br> Expected Output in Item  List Box: `Potato` item displayed. <br>Expected Output in Command Output Box: 1 item listed message.
1. Test case: `find strawberry` <br>Expected Output in Item  List Box: No items displayed. <br>Expected Output in Command Output Box: 0 item listed message.

#### Sort all items by an attribute

Prerequisite: Suppose there are a few items stored in FoodRem and shown in Item List Box with different names.

1. Test case: `sort n/` <br> Expected Output in Item  List Box: Items sorted in ascending alphabetical order based on name. <br>Expected Output in Command Output Box: Items sorted message.

#### View the information of an Item

Prerequisite: Suppose there is at least 1 item shown in the Item List Box.

1. Test case: `view 1` <br> Expected Output in Item  List Box: Still showing the same list as before. <br>Expected Output in Command Output Box: Details of first item in Item List Box shown. Displayed information includes the name, quantity, unit, bought date, expiry date, price, remarks and tags of items.

#### Increment the quantity of an item

Prerequisite: Suppose the first item in the Item List Box has a quantity of 1.

1. Test case: `inc 1 qty/2` <br> Expected Output in Item  List Box: Still showing the same list as before but with first item quantity increased by 2 <br>Expected Output in Command Output Box: Incremented item quantity message with details of first item in Item List shown.

#### Decrement the quantity of an item

Prerequisite: Suppose the first item in the Item List Box has a quantity of 3.

1. Test case: `dec 1 qty/2` <br> Expected Output in Item  List Box: Still showing the same list as before but with first item quantity decreased by 2 <br>Expected Output in Command Output Box: Decremented item quantity message with details of first item in Item List shown.
   `

#### Edit the information of an item

Prerequisite: Suppose the first item in the Item List Box has a name of `Potato`.

1. Test case: `edit 1 n/Tomato` <br> Expected Output in Item  List Box: The `Potato`item has been renamed to `Tomato` and this is now reflected in Item List Box<br> Expected Output in Command Output Box: Item edited message with details of the edited item shown.

#### Add a remark to an item

Prerequisite: Suppose the first item in the Item List Box has no remarks.

1. Test case: `rmk 1 r/For Party` <br> Expected Output in Item  List Box: Same list as shown before<br> Expected Output in Command Output Box: Remark updated message with details of the item that has remark updated shown.

#### Deleting an Item

Prerequisite: There is at least 1 item in the Item List Box.

1. Test Case: `del 1`<br> Expected Output in Item List Box: First item in list is deleted <br> Expected Output in Command Output Box: Deleted item message and its details.
1. Test Case: `del 0`<br> Expected Output in Command Output Box: Error message for invalid command format.

### Tags

#### Create a new tag

1. Test Case: `newtag n/Fruits`<br> Expected Output in Command Output Box: New tag added and its details.
1. Test Case: `newtag n/!`<br> Expected Output in Command Output Box: Error message for invalid name format.

#### List all tags

1. Test Case: `listtag` <br> Expected Output in Command Output Box: All current exisiting tags of FoodRem are listed.

#### Tag an Item

Prerequisite: Tag and Item to be tagged exists.

1. Test Case: `tag 1 n/Food`<br> Expected Output in Item List Box: The specified item is tagged. <br> Expected Output in Command Output Box: Message indicating item has been successfully tagged.
1. Test Case: `tag n/Food`<br> Expected Output in Command Output Box: Error message for invalid command format.

#### Untag an Item

Prerequisite: Tag and Item to be tagged exists. Tag is also currently tagged to the specified item.

1. Test Case: `untag 1 n/Food`<br> Expected Output in Item List Box: The specified item is untagged from the tag. <br> Expected Output in Command Output Box: Message indicating item has been successfully untagged.
1. Test Case: `untag n/Food`<br> Expected Output in Command Output Box: Error message for invalid command format.

#### Rename a Tag

Prerequisite: Original tag to be renamed exists.

1. Test Case: `renametag n/Fruits n/Food`<br> Expected Output in Item List Box: The specified tags are renamed. <br> Expected Output in Command Output Box: Details of original and renamed tags.
1. Test case: `renametag n/Food n/Food` <br>Expected Output in Command Output Box: Error message for renaming to duplicate name.

#### Delete a Tag

Prerequisite: Tag currently exists in FoodRem

1. Test case: `deletetag n/Food`<br> Expected Output in Item List Box: Specified tag if shown, is deleted. <br> Expected Output in Command Output Box: Tag deleted details.

### Statistics

#### Display statistics

1. Test case: `stats`<br> Expected Output in Item List Box: No change in displayed items <br> Expected Output in Command Output Box: Statistics which include total cost incurred due to food wastage, top 3 common tags and top 3 items with the highest value.

### Help

1. Test Case: `help`<br> Expected Output: A pop-up window shows up with the help information.
1. Test Case: `help new`<br> Expected Output: A pop-up window shows up with the help information for `new` command.

### Reset

1. Test Case: `reset`<br> Expected Output in Item List Box: All items in Item List Box are cleared <br> Expected Output in Command Output Box: FoodRem reset success message.

### Exit

1. Test Case: `exit`<br> Expected Output: FoodRem application closes.
