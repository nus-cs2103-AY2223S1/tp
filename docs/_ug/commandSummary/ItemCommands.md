<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable no-space-in-emphasis -->
{% capture new %}{% include_relative _ug/commandSummary/itemCommands/new.md %}{% endcapture %}
{% capture list %}{% include_relative _ug/commandSummary/itemCommands/list.md %}{% endcapture %}
{% capture find %}{% include_relative _ug/commandSummary/itemCommands/find.md %}{% endcapture %}
{% capture sort %}{% include_relative _ug/commandSummary/itemCommands/sort.md %}{% endcapture %}
{% capture view %}{% include_relative _ug/commandSummary/itemCommands/view.md %}{% endcapture %}
{% capture inc %}{% include_relative _ug/commandSummary/itemCommands/inc.md %}{% endcapture %}
{% capture dec %}{% include_relative _ug/commandSummary/itemCommands/dec.md %}{% endcapture %}
{% capture set %}{% include_relative _ug/commandSummary/itemCommands/set.md %}{% endcapture %}
{% capture del %}{% include_relative _ug/commandSummary/itemCommands/del.md %}{% endcapture %}
<!-- markdownlint-enable no-space-in-emphasis -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                            | Format |
|-------------------------------------------------------------------|--------|
| Create a new item                                                 | :new:  |
| List all items                                                    | :list: |
| Search for an item                                                | :find: |
| Sort an item by name, quantity, type, bought date or expiry date. | :sort: |
| View information about an item                                    | :view: |
| Increase the quantity of an item                                  | :inc:  |
| Decrease the quantity of an item                                  | :dec:  |
| Update the information of an item                                 | :set:  |
| Delete an item                                                    | :del:  |
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
