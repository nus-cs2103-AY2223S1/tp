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

**Example:**

<!-- Hardcoding due to unique command format -->
<!-- markdownlint-disable no-inline-html -->
<!-- markdownlint-disable blanks-around-fences -->
<div class="command-container" markdown="1">
<div class="input-container" markdown="1">

**Command Input Box:**

Possible inputs:

```text
help
```
{: style="margin-bottom: 0.5rem" }

```text
help del
```
{: style="margin-bottom: 0.5rem" }

```text
help help
```
{: style="margin-bottom: 0.5rem" }

</div>
<div class="input-container" markdown="1">

**Help Window:**

The [[ help-window:Help Window ]] will open showing the instructions.

If no `COMMAND_WORD` was specified, only a general help message will be provided. The general help message shows a list of commands available to the user and a [[ url:URL ]] to this User Guide.

If a `COMMAND_WORD` was specified, additional help for that command will be provided.

</div>
</div>
<!-- markdownlint-restore -->

---

#### Reset the application: `reset`

**Format**: `reset`

> Clears all items and tags in FoodRem

```tip
You may find this command useful when you start FoodRem for the first time. Initially, FoodRem starts up with some sample data, which, once you have figured out how to use FoodRem, may want to clear so that you can start using it for your own business!
```

**Example:**

{% capture notes %}
**Assumptions:**

_None_
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="reset"
  commandOutputBox="images/generalCommands/commandOutputBox/reset.png"
%}

---

#### Exit FoodRem: `exit`

**Format**: `exit`

> Exits FoodRem

```warning
This command is the only guaranteed way for the data file to be saved when you exit the application To prevent, always exit the application using this command instead of any other way.
```

**Example:**

<!-- Hardcoding due to unique command format -->
<!-- markdownlint-disable no-inline-html -->
<div class="command-container" markdown="1">
<div class="input-container" markdown="1">

**Command Input Box:**

Possible inputs:

```text
exit
```

</div>
<div class="input-container" markdown="1">

**Expected Outcomes:**

* All FoodRem application windows will close
* Your inventory data is saved.

</div>
</div>
<!-- markdownlint-restore -->

```note
If your inventory data cannot be saved successfully, FoodRem will not close in order to prevent data loss.
```
