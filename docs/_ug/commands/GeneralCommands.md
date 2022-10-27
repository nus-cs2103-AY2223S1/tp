<!-- markdownlint-disable-file first-line-h1 -->

#### Receive help during usage: `help`

**Format**: `help [COMMAND_WORD]`

> Displays help for FoodRem.

**Example Input:**

```text
help
help del
help help
```

**Expected Output:**

A new window will open showing instructions.

If no `COMMAND_WORD` was specified, only a general help message will be provided. The general help message shows a list of commands available to the user and a URL to this User Guide.

If a `COMMAND_WORD` was specified, additional help for that command will be provided.

---

#### Reset the application: `reset`

**Format**: `reset`

> Clears all data in FoodRem. This includes all items and tags currently stored.

**Example Input:**

```text
reset
```

**Expected Output:**

Command Output Box:

```text
FoodRem has been reset!
```

---

#### Exit FoodRem: `exit`

**Format**: `exit`

> Exits FoodRem.

**Example Input:**

```text
exit
```

**Expected Output:**

FoodRem application will close.

---
