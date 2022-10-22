### **\[Developed\] Assign Task feature**

#### **Implementation**

This feature allows the user to assign task to a member in an existing group, using the `assigntask` command. This is facilitated by the `AssignTaskCommand` and `AssignTaskCommandParser` classes.

The `AssignTaskCommandParser` class parses the input entered by the user, which are the username, the group name, and the task name that the user wants to assign.

The validity of the inputs are checked and listed below :
- The username input by the user will be checked whether the person exists.
- The group name input by the user will be checked whether the group exists.
- The username will be checked again to ensure that the person is in the group.
- The task name input by the user will be checked whether it is empty.

If all the inputs are valid, the specified task will be assigned to the specified user in a specified group with the help of `Model#setPerson`.

The specified task will then be displayed under that user with the help of `Model#updateFilteredGroupList(predicate)`.

Given below is an example of how `AssignTaskCommand` is being executed.

**Steps**

Step 1. The user enters `assigntask [NAME OF GROUP]` command

Step 2. The  `DisplayGroupCommandParser` class parses the group name input and returns a `DisplayGroupCommand` object with a single `FullGroupNamePredicate` attribute, encapsulating the input group name.

Step 3. The `DisplayGroupCommand` object is executed. `FullGroupNamePredicate#test(group)` will be called to check against all the groups which are already present in TABS. These existing groups can be retrieved by calling `ObservableList#get()` method.

Step 4. If an existing group in TABS has name which matches exactly the name given by the user, then `Model#updateFilteredGroupList(predicate)` will be called, and this will display the group as specified.

Step 5. CommandResult is then returned, which provides a feedback to user that the specified group has been successfully
displayed.

**Activity Diagram**

The user flow of Assign Task can be illustrated in the Activity Diagram as shown below.

<img src="images/AssignTaskActivityDiagram-AssignTaskCommand.png" width="800" />

**Design Considerations**