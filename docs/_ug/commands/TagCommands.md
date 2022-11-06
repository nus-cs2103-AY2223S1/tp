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

```note
If you encounter an error message saying "This tag name already exists in FoodRem," it means that the new name you wish to rename the current tag to is already taken by another tag in FoodRem.
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

#### Filter by a tag: `filtertag`

**Format**: `filtertag n/TAG_NAME`

> Filters and shows items that contain a specific tag

**Example:**

{% capture notes %}
**Assumption:**

Initially, FoodRem only contains the following items:

1. Banana (tagged as "Fruits")
1. Carrot (tagged as "Vegetables")
1. Papaya (tagged as "Fruits")
1. Tomato (tagged as "Vegetables")
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="filtertag n/Fruits"
  itemListBox="images/tagCommands/itemListBox/filtertag.png"
%}

---

#### Delete a tag: `deletetag`

**Format**: `deletetag n/TAG_NAME`

> Deletes a tag that exists in FoodRem

```warning
* There is **no** additional confirmation for deleting a tag that is in use
* Deleting a tag that is already in use will also untag the items under that tag
```

```tip
You might find the abovementioned [Filtertag Command](#filter-by-a-tag-filtertag) useful to check that a tag is not in use before deleting it. 
```

**Example:**

{% capture notes %}
**Assumption:**

Initially, there already exists a tag called "Veggies".
{% endcapture %}
{%
  include command-format.md
  notes=notes
  input="deletetag n/Veggies"
  commandOutputBox="images/tagCommands/commandOutputBox/deletetag.png"
%}

---
