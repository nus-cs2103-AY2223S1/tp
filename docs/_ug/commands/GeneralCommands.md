<!-- markdownlint-disable-file first-line-h1 -->

#### Receive help during usage: `help`

**Format**: `help [COMMAND_WORD]`

> Displays help for FoodRem

```note
COMMAND_WORD is strictly any of the following:
* exit
* help
* reset
* dec
* del
* edit
* find
* inc
* list
* new
* rmk
* sort
* view
* deletetag
* filtertag
* listtag
* newtag
* renametag
* tag
* untag
```

**Example Input:**

```text
help
help del
help help
```

**Expected Output:**<br>A new window will open showing the instructions.

If no `COMMAND_WORD` was specified, only a general help message will be provided. The general help message shows a list of commands available to the user and a URL to this User Guide.

If a `COMMAND_WORD` was specified, additional help for that command will be provided.

---

#### Reset the application: `reset`

**Format**: `reset`

> Clears all items and tags in FoodRem

**Example Input:**

```text
reset
```

**Expected Output:**<br>Command Output Box:

```text
FoodRem has been reset!
```

---

#### Exit FoodRem: `exit`

**Format**: `exit`

> Exits FoodRem

```warning
The data file is only saved when you exit the application using this command.
```

**Example Input:**

```text
exit
```

**Expected Output:**<br>FoodRem application will close.

---
