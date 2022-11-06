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
{% capture rmk %}{% include_relative _ug/commandSummary/itemCommands/rmk.md %}{% endcapture %}
{% capture del %}{% include_relative _ug/commandSummary/itemCommands/del.md %}{% endcapture %}

{% assign new = new | markdownify %}
{% assign list = list | markdownify %}
{% assign find = find | markdownify %}
{% assign sort = sort | markdownify %}
{% assign view = view | markdownify %}
{% assign inc = inc | markdownify %}
{% assign dec = dec | markdownify %}
{% assign edit = edit | markdownify %}
{% assign rmk = rmk | markdownify %}
{% assign del = del | markdownify %}

{% capture newexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/new.md %}{% endcapture %}
{% capture listexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/list.md %}{% endcapture %}
{% capture findexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/find.md %}{% endcapture %}
{% capture sortexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/sort.md %}{% endcapture %}
{% capture viewexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/view.md %}{% endcapture %}
{% capture incexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/inc.md %}{% endcapture %}
{% capture decexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/dec.md %}{% endcapture %}
{% capture editexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/edit.md %}{% endcapture %}
{% capture rmkexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/rmk.md %}{% endcapture %}
{% capture delexample %}{% include_relative _ug/commandSummary/itemCommandsExamples/del.md %}{% endcapture %}

{% assign newexample = newexample | markdownify %}
{% assign listexample = listexample | markdownify %}
{% assign findexample = findexample | markdownify %}
{% assign sortexample = sortexample | markdownify %}
{% assign viewexample = viewexample | markdownify %}
{% assign incexample = incexample | markdownify %}
{% assign decexample = decexample | markdownify %}
{% assign editexample = editexample | markdownify %}
{% assign rmkexample = rmkexample | markdownify %}
{% assign delexample = delexample | markdownify %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                                             | Format | Example       |
|------------------------------------------------------------------------------------|--------|---------------|
| Create a new item                                                                  | :new:  | :newexample:  |
| List all items                                                                     | :list: | :listexample: |
| Search for an item                                                                 | :find: | :findexample: |
| Sort all items by name, quantity, unit, bought date, expiry date, price or remark  | :sort: | :sortexample: |
| View the information of an item                                                    | :view: | :viewexample: |
| Increase the quantity of an item                                                   | :inc:  | :incexample:  |
| Decrease the quantity of an item                                                   | :dec:  | :decexample:  |
| Edit the information of an item                                                    | :edit: | :editexample: |
| Add a remark to an item                                                            | :rmk:  | :rmkexample:  |
| Delete an item                                                                     | :del:  | :delexample:  |
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
  | replace: ":edit:", edit
  | replace: ":rmk:", rmk
  | replace: ":del:", del
  | replace: ":newexample:", newexample
  | replace: ":listexample:", listexample
  | replace: ":findexample:", findexample
  | replace: ":sortexample:", sortexample
  | replace: ":viewexample:", viewexample
  | replace: ":incexample:", incexample
  | replace: ":decexample:", decexample
  | replace: ":editexample:", editexample
  | replace: ":rmkexample:", rmkexample
  | replace: ":delexample:", delexample
}}
