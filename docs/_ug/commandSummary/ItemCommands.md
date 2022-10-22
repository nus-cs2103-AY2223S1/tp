<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture new %}{% include_relative _ug/commandSummary/itemCommands/new.md %}{% endcapture %}
{% capture list %}{% include_relative _ug/commandSummary/itemCommands/list.md %}{% endcapture %}
{% capture find %}{% include_relative _ug/commandSummary/itemCommands/find.md %}{% endcapture %}
{% capture sort %}{% include_relative _ug/commandSummary/itemCommands/sort.md %}{% endcapture %}
{% capture view %}{% include_relative _ug/commandSummary/itemCommands/view.md %}{% endcapture %}
{% capture inc %}{% include_relative _ug/commandSummary/itemCommands/inc.md %}{% endcapture %}
{% capture dec %}{% include_relative _ug/commandSummary/itemCommands/dec.md %}{% endcapture %}
{% capture edit %}{% include_relative _ug/commandSummary/itemCommands/edit.md %}{% endcapture %}
{% capture del %}{% include_relative _ug/commandSummary/itemCommands/del.md %}{% endcapture %}

{% capture new %}{% include_relative _ug/commandSummary/itemCommandsExample/new.md %}{% endcapture %}
{% capture list %}{% include_relative _ug/commandSummary/itemCommandsExample/list.md %}{% endcapture %}
{% capture find %}{% include_relative _ug/commandSummary/itemCommandsExample/find.md %}{% endcapture %}
{% capture sort %}{% include_relative _ug/commandSummary/itemCommandsExample/sort.md %}{% endcapture %}
{% capture view %}{% include_relative _ug/commandSummary/itemCommandsExample/view.md %}{% endcapture %}
{% capture inc %}{% include_relative _ug/commandSummary/itemCommandsExample/inc.md %}{% endcapture %}
{% capture dec %}{% include_relative _ug/commandSummary/itemCommandsExample/dec.md %}{% endcapture %}
{% capture set %}{% include_relative _ug/commandSummary/itemCommandsExample/edit.md %}{% endcapture %}
{% capture del %}{% include_relative _ug/commandSummary/itemCommandsExample/del.md %}{% endcapture %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                            | Format | Example       |
|-------------------------------------------------------------------|--------|---------------|
| Create a new item                                                 | :new:  | :newexample:  |
| List all items                                                    | :list: | :listexample: |
| Search for an item                                                | :find: | :findexample: |
| Sort an item by name, quantity, type, bought date or expiry date. | :sort: | :sortexample: |
| View information about an item                                    | :view: | :viewexample: |
| Increase the quantity of an item                                  | :inc:  | :incexample:  |
| Decrease the quantity of an item                                  | :dec:  | :decexample:  |
| Update the information of an item                                 | :edit: | :editexample: |
| Delete an item                                                    | :del:  | :delexample:  |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":new:", new
  | replace: ":list:", list
  | replace: ":find:", find
  | replace: ":sort:", sort
  | replace: ":view:", view
  | replace: ":inc:", inc
  | replace: ":dec:", dec
  | replace: ":set:", set
  | replace: ":del:", del
}}
