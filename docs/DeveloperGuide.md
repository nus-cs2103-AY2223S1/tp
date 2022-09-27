# Developer Guide

---

## Product Scope
### Target user profile
Project team leaders with many projects, members and tasks to assign.

### Value proposition
Project team leaders can:
* view information of which group members are in their project.
* track which tasks have been assigned to which members.
* see an estimate of how much workload each member has.
* receive information regarding upcoming deadlines.

---

## User stories

####Priorities: High (must have) - ``* * *``, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority  | As a ...          | I want to ...                                                                  | So that I can ...                                                                      |
|:----------|:------------------|:-------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------|
| ``* * *`` | beginner user     | add contacts to my app                                                         ||     |
| `* * *`   | beginner user     | remove existing contacts on my app                                             | remove entries that I no longer need                                                   |
| `* * *`   | beginner user     | create a group                                                                 ||     |
| `* * *`   | beginner user     | add members to a group                                                         |                                                                                        |
| `* *`     | intermediate user | locate a particular contact                                                    | quickly find a member                                                                  |
| `* * *`   | intermediate user | assign tasks to members                                                        ||     |
| `* *`     | intermediate user | have a quick overview of tasks assigned to members in the group                ||     |
| `* *`     | intermediate user | create a tag specific to the group project                                     | quickly assign tags to members                                                         |
| `* *`     | intermediate user | place tags on group members                                                    | better identify their role                                                             |
| `* *`     | intermediate user | assign multiple tags to a user if needed                                       | identify their roles more specifically                                                 |
| `* *`     | intermediate user | filter and search for groups                                                   | quickly identify the one in particular                                                 |
| `* *`     | advanced user     | view deadlines for each project                                                | periodically use this for self-reminder                                                |
| `* *`     | advanced user     | have a rough sense of the workload of every member in the group                | assign future tasks with more confidence                                               |
| `*`       | advanced user     | view a member’s tasks in more detail                                           | assign future tasks to them with more confidence                                       |
| `* * *`   | advanced user     | add more tasks to a member                                                     ||
| `* * *`   | advanced user     | delete tasks from a member                                                     ||
| `*`       | advanced user     | categorise the tasks assigned into different levels of intensity               | not judge workload based solely on the number of tasks per member                      |
| `* * *`   | advanced user     | delete unused groups after the project is completed                            | declutter my app                                                                       |
| `* * *`   | advanced user     | delete existing tags if they are no longer relevant                            | declutter my app                                                                       |
| `*`       | advanced user     | reuse existing tags in groups for future projects                              | establish new projects under my management style                                       |
| `*`       | advanced user     | move tags and assignments from one user to another easily                      | ensure that members can ‘swap’ roles hassle-free                                       |
| `* *`     | expert user       | perform group-wide addition of tags and assignments                            | ensure that repetitive new assignments are made as quickly and accurately as possible. |
| `* *`     | expert user       | perform group-wide removal of tags and assignments                             | ensure that group members’ roles are quickly cleared owing to new demands              |
| `*`       | expert user       | be notified when a member completes his task or when a deadline is approaching | better manage my time                                                                  |
| `*`       | expert user       | create shortcuts and pin most important projects on the top of the app         | access these projects faster                                                           |
| `*`       | expert user       | have the choice of deleting users from the app when a project completes        | quickly declutter my app                                                               |
| `*`       | expert user       | set timers to add/delete groups after a project ends                           | ensure that I do not have too many groups cluttering the database                      |

---
## Use cases 

For all use cases below, the **System** is TABS and the **Actor** is the user, unless specified otherwise.

### UC1: Help

**MSS**

1. User indicates they want help.
2. TABS Displays UG link.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC2: Add a person

**MSS**

1. User requests add a person.
2. TABS display that person is successfully added.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact already exists in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1.a.1 TABS displays that the person already exists in the program.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC3: Edit a person’s details

**MSS**

1. User requests to edit an existing contact.
2. TABS displays the modified contact.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user and displays error messages.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC4: Find a person

**MSS**

1. User requests to find an existing contact.
2. TABS displays the contacts found.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions**

2a. The list is empty.

Use case ends.

### UC5: Delete a person

**MSS**

1. User requests to delete an existing contact.
2. TABS displays that the contact is successfully deleted.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user and indicates error messages.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC6: Add a group

**MSS**

1. User requests add a group.
2. TABS displays the added group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The group already exists in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS displays that the group already exists inside TABS.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC7: Display a group

**MSS**

1. User requests to display an existing group.
2. TABS displays the group with its members.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user and displays error messages.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC8: Find a group

**MSS**

1. User requests to find an existing group.
2. TABS displays the groups found.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The list is empty.

Use case ends.

### UC9: Delete a group

**MSS**

1. User requests to delete an existing group.
2. TABS displays that the group is successfully deleted.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC10: Add a person to group

**MSS**

1. User requests to add an existing contact to an existing group.
2. TABS displays that the contact specified is added to the group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC11: Remove a person from group

**MSS**

1. User requests to remove an existing contact from an existing group.
2. TABS displays that the contact specified is removed from the group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC12: Assign a task to a member

**MSS**

1. User requests to assign a task.
2. TABS displays that the task is tagged to the person specified under the group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC13: Remove a task from a person

**MSS**

1. User requests to remove a task from a person.
2. TABS displays that the task is successfully removed.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The person specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

3a. The person does not have the specified task.

&nbsp;&nbsp;&nbsp;&nbsp;3a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC14: Assign a tag to a person

**MSS**

1. User requests to assign a tag to a person in TABS.
2. TABS displays that the tag is added to the person specified.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC15: Remove a tag from a person

**MSS**

1. User requests to remove a tag from a person.
2. TABS displays that the tag is successfully removed.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The person specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

3a. The person does not have the specified tag.

&nbsp;&nbsp;&nbsp;&nbsp;3a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC16: Group assignment of task

**MSS**

1. User requests to add a task to a group.
2. TABS displays that the task is successfully added.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC17: Group removal of task

**MSS**

1. User requests to remove a task from a group.
2. TABS searches for the group.
3. TABS displays that the task is successfully removed.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

3a. The person does not have the specified task.

&nbsp;&nbsp;&nbsp;&nbsp;3a1. TABS moves on to the next person in the group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC18: Group assignment of tag

**MSS**

1. User requests to add a tag to a group.
2. TABS displays that the tag is successfully added.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

Extensions:

2a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC19: Group removal of tag

**MSS**

1. User requests to remove a tag from a group.
2. TABS displays that the tag is successfully removed.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

2a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

3a. The person does not have the specified tag.

&nbsp;&nbsp;&nbsp;&nbsp;3a1. TABS moves on to the next person in the group.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

### UC20: Notifications

**MSS**

1. User boots up the application/requests for notifications.
2. TABS provides notifications on upcoming deadlines/completed tasks.

&nbsp;&nbsp;&nbsp;&nbsp;Use case ends.

**Extensions:**

1a. List is empty.

Use case ends.

---
## Non-functional requirements

1. Should work on any **mainstream** OS (Windows, Linux, Unix, OS-X) as long as it has `Java 11` or above installed.
2. Should be able to hold up to 500 persons and/or 10 groups without noticeable sluggishness in performance.
3. Bulk assignments/removals should be able to handle up to 20 persons without noticeable sluggishness in performance.
4. Workload indicators, if using colours, should be easily distinguishable.
5. Content should be saved to a save file that can be opened and edited with mainstream text editors e.g. Notepad.
