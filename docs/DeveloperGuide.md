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
## Non-functional requirements

1. Should work on any **mainstream** OS (Windows, Linux, Unix, OS-X) as long as it has `Java 11` or above installed.
2. Should be able to hold up to 500 persons and/or 10 groups without noticeable sluggishness in performance.
3. Bulk assignments/removals should be able to handle up to 20 persons without noticeable sluggishness in performance.
4. Workload indicators, if using colours, should be easily distinguishable.
5. Content should be saved to a save file that can be opened and edited with mainstream text editors e.g. Notepad.
