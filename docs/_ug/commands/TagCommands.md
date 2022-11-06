<!-- markdownlint-disable-file first-line-h1 -->
Example of a [Tag](#tag):

![Tags](images/TagImage.png)

#### Create a new tag: `newtag`

**Format**: `newtag n/TAG_NAME`

> Creates a new tag with the provided tag name

**Example:**

{% capture notes %}
**Assumption:**

FoodRem does not already contain a tag called "Vegetables".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="newtag n/Vegetables"
  commandOutputBox="images/tagCommands/commandOutputBox/newtag.png"
%}

---

#### List all tags: `listtag`

**Format**: `listtag`

> List all tags in FoodRem

**Example:**

{% capture notes %}
**Assumption:**

Initially, FoodRem contains only the following three tags:

* Fruits
* Meat
* Vegetables
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="listtag"
  commandOutputBox="images/tagCommands/commandOutputBox/listtag.png"
%}

---

#### Tag an item: `tag`

**Format**: `tag INDEX n/TAG_NAME`

> Tags the item at the specified index

**Example:**

{% capture notes %}
**Assumptions:**

* The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Carrot" at INDEX value 1.
* There exists a tag called "Vegetables".
* The "Carrot" item has not already been tagged under "Vegetables".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="tag 1 n/Vegetables"
  commandOutputBox="images/tagCommands/commandOutputBox/tag.png"
%}

---

#### Untag an item: `untag`

**Format**: `untag INDEX n/TAG_NAME`

> Untags the item at the specified index

**Example:**

{% capture notes %}
**Assumptions:**

* The currently displayed [[ item-list-box:Item List Box ]] in FoodRem shows the item named "Carrot" at INDEX value 1.
* The "Carrot" item is currently tagged under "Vegetables".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="untag 1 n/Vegetables"
  commandOutputBox="images/tagCommands/commandOutputBox/untag.png"
%}

---

#### Rename a tag: `renametag`

**Format**: `renametag n/TAG_NAME n/TAG_NAME`

> Renames a tag currently in FoodRem

```info
The first `TAG_NAME` in the command refers to the current tag you wish to rename while the second `TAG_NAME` refers to the new name you wish to rename the current tag to.
```

**Example:**

{% capture notes %}
**Assumptions:**

* Initially, there exists a tag called "Vegetables".
* FoodRem does not already contain a tag called "Veggies".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="renametag n/Vegetables n/Veggies"
  commandOutputBox="images/tagCommands/commandOutputBox/renametag.png"
%}

---

#### Delete a tag: `deletetag`

**Format**: `deletetag n/TAG_NAME`

> Deletes a tag that exists in FoodRem

**Example Input:**

```text
deletetag n/Veggies
```

**Example Output:**<br>Command Output Box:

```text
Tag deleted: Veggies
```

---

#### Filter by a tag: `filtertag`

**Format**: `filtertag n/TAG_NAME`

> Filters and shows items that contain a specific tag

**Example Input:**

```text
filtertag n/fruits
```

**Example Output:**<br>Command Output Box:

```text
Filtered by tag: fruits
2 items left after filtering!
```

Item List Box:

```text
1. Apples 8 kg (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
2. Onions 8 kg $1 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
```
