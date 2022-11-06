<!-- markdownlint-disable-file first-line-h1 -->
#### Display statistics: `stats`

**Format**: `stats`

> Displays all statistics collected by FoodRem

```note
These statistics include:
* **Total cost** of wasted [[ item:items ]]
* Top 3 **most commonly** used [[ tag:tags ]] in your inventory
* Top 3 **most costly** items in your inventory

The cost of an item is the price of the item multiplied by the quantity of the item in your inventory.
```

**Example:**

{% capture notes %}
**Assumptions:**

* Initially, FoodRem has a few items, one of which is:
  * Tomato
* Initially, FoodRem has a few tags, three of which are:
  * Fruits
  * Meat
  * Vegetables
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="stats"
  commandOutputBox="images/statisticsCommands/commandOutputBox/stats.png"
%}

```info
The above picture only shows a partial view of the Command Output Box. On FoodRem, you can simply scroll down to see the full output! This is unfortunately the one instance where you need to use a mouse to interact with FoodRem.
```
