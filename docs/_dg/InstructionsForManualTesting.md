<!-- markdownlint-disable-file first-line-h1 -->
Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on; testers are expected to do more *exploratory* testing.

</div>

### Launch and Shutdown

#### Initial launch

1. Download the jar file and copy into an empty folder

1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.


### Items

#### Adding an Item
1. Test case: `new n/Potato qty/2 u/kg bgt/10-10-2022  exp/11-11-2022 p/4 r/favourite` <br> Expected Output in Item List Box: New item added into the list. <br> Expected Output in Command Output Box: New item and its details.
2. Test case: `new qty/2` <br> Expected Output in Command Box: Error message for invalid command format.

#### Deleting an Item
Prerequisite: There is at least 1 item in the Item List Box.

1. Test Case: `del 1`<br> Expected Output in Item List Box: First item in list is deleted <br> Expected Output in Command Output Box: Deleted item and its details.
2. Test Case: `del 0`<br> Expected Output in Command Output Box: Error message for invalid command format.

### Tags

#### Creating a Tag
Test Case: `newtag n/Fruits`<br> Expected Output in Command Output Box: New tag added and its details.

Test Case: `newtag n/!`<br> Expected Output in Command Output Box: Error message for invalid name format.

#### Renaming a Tag
Prerequisite: Original tag to be renamed exists.

1. Test Case: `renametag n/Fruits n/Food`<br> Expected Output in Item List Box: The specified tags are renamed. <br> Expected Output in Command Output Box: Details of original and renamed tags.
2.  Test case: `renametag n/Food n/Food` <br>Expected Output in Command Output Box: Error message for renaming to duplicate name.

#### Listing tags
1. Test Case: `listtag` <br> Expected Output in Command Output Box: All current exisiting tags of FoodRem are listed.


#### Tagging an Item
Prerequisite: Tag and Item to be tagged exists.

1. Test Case: `tag 1 n/Food`<br> Expected Output in Item List Box: The specified item is tagged. <br> Expected Output in Command Output Box: Message indicating item has been successfully tagged.
2. Test Case: `tag n/Food`<br> Expected Output in Command Output Box: Error message for invalid command format.

#### Untagging an Item
Prerequisite: Tag and Item to be tagged exists. Tag is also currently tagged to the specified item.

1. Test Case: `untag 1 n/Food`<br> Expected Output in Item List Box: The specified item is untagged from the tag. <br> Expected Output in Command Output Box: Message indicating item has been successfully untagged.
2. Test Case: `untag n/Food`<br> Expected Output in Command Output Box: Error message for invalid command format.

#### Deleting a Tag
Prerequisite: Tag currently exists in FoodRem

1. Test case: `deletetag n/Food`<br> Expected Output in Item List Box: Specified tag if shown, is deleted. <br> Expected Output in Command Output Box: Tag deleted details.

### Help

1. Test Case: `help`<br> Expected Output: A pop-up window shows up with the help information.

1. Test Case: `help new`<br> Expected Output: A pop-up window shows up with the help information for `new` command.


### Reset
1. Test Case: `reset`<br> Expected Output in Item List Box: All items in Item List Box are cleared <br> Expected Output in Command Output Box: FoodRem reset success message.


### Exit
Test Case: `exit`<br> Expected Output: FoodRem application closes.
