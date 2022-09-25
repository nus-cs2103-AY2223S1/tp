
--------------------------------------------------------------------------------------------------------------------

## Command summary

```< >``` for mandatory arguments

```[ ]``` for optional arguments

### General 

|       Action        | Format          | Examples        |
|:-------------------:|-----------------|-----------------|
| **List Everything** | `list`<br/>`ls` | `list`<br/>`ls` |
|      **Help**       | `help`<br/>`h`  | `help`<br/>`h`  |
|      **Exit**       | `exit`          | `exit`          |

### Modules

|        Action        | Format                                              | Examples                                    |
|:--------------------:|-----------------------------------------------------|---------------------------------------------|
|   **Add a Module**   | `add module <mod. code>`<br/>`add -m <mod. code>`   | `add module CS2103T`<br/>`add -m CS2103T`   |
| **Remove a Module**  | `remove module <mod. code>`<br/>`rm -m <mod, code>` | `remove module CS2103T`<br/>`rm -m cS2103t` |
|  **Go to a Module**  | `cd <mod, code>`                                    | `cd CS2103T`                                |
| **List All Modules** | `list modules` <br/> `ls -m`                        | `list modules`<br/>`ls -m`                  |

### Tasks

|       Action       | Format                                                                              | Examples                                                                          |
|:------------------:|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
|   **Add a Task**   | `add task <description> [-d <deadline>]`<br/>`add -t <description> [-d <deadline>]` | `add task do ip tasks deadline 15/09/2022`<br/>`add -t do ip tasks -d 15/09/2022` |
| **Remove a Task**  | `remove task <index> `<br/>`rm -t <index>`                                          | `remove task 1`<br/>`rm -t 1`                                                     |
| **List All Tasks** | `list tasks` <br/> `ls -t`                                                          | `list tasks`<br/>`ls -t`                                                          |
