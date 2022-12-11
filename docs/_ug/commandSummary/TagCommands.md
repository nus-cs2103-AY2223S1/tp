<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture newtag %}{% include_relative _ug/commandSummary/tagCommands/newtag.md %}{% endcapture %}
{% capture listtag %}{% include_relative _ug/commandSummary/tagCommands/listtag.md %}{% endcapture %}
{% capture tag %}{% include_relative _ug/commandSummary/tagCommands/tag.md %}{% endcapture %}
{% capture untag %}{% include_relative _ug/commandSummary/tagCommands/untag.md %}{% endcapture %}
{% capture renametag %}{% include_relative _ug/commandSummary/tagCommands/renametag.md %}{% endcapture %}
{% capture deletetag %}{% include_relative _ug/commandSummary/tagCommands/deletetag.md %}{% endcapture %}
{% capture filtertag %}{% include_relative _ug/commandSummary/tagCommands/filtertag.md %}{% endcapture %}

{% assign newtag = newtag | markdownify %}
{% assign listtag = listtag | markdownify %}
{% assign tag = tag | markdownify %}
{% assign untag = untag | markdownify %}
{% assign renametag = renametag | markdownify %}
{% assign deletetag = deletetag | markdownify %}
{% assign filtertag = filtertag | markdownify %}

{% capture newtagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/newtag.md %}{% endcapture %}
{% capture listtagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/listtag.md %}{% endcapture %}
{% capture tagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/tag.md %}{% endcapture %}
{% capture untagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/untag.md %}{% endcapture %}
{% capture renametagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/renametag.md %}{% endcapture %}
{% capture deletetagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/deletetag.md %}{% endcapture %}
{% capture filtertagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/filtertag.md %}{% endcapture %}

{% assign newtagexample = newtagexample | markdownify %}
{% assign listtagexample = listtagexample | markdownify %}
{% assign tagexample = tagexample | markdownify %}
{% assign untagexample = untagexample | markdownify %}
{% assign renametagexample = renametagexample | markdownify %}
{% assign deletetagexample = deletetagexample | markdownify %}
{% assign filtertagexample = filtertagexample | markdownify %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action              | Format      | Example            |
|---------------------|-------------|--------------------|
| Create a new tag    | :newtag:    | :newtagexample:    |
| List all tags       | :listtag:   | :listtagexample:   |
| Tag an item         | :tag:       | :tagexample:       |
| Untag an item       | :untag:     | :untagexample:     |
| Rename a tag        | :renametag: | :renametagexample: |
| Delete a tag        | :deletetag: | :deletetagexample: |
| Filter items by tag | :filtertag: | :filtertagexample: |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":newtag:", newtag
  | replace: ":listtag:", listtag
  | replace: ":tag:", tag
  | replace: ":untag:", untag
  | replace: ":renametag:", renametag
  | replace: ":deletetag:", deletetag
  | replace: ":filtertag:", filtertag
  | replace: ":newtagexample:", newtagexample
  | replace: ":listtagexample:", listtagexample
  | replace: ":tagexample:", tagexample
  | replace: ":untagexample:", untagexample
  | replace: ":renametagexample:", renametagexample
  | replace: ":deletetagexample:", deletetagexample
  | replace: ":filtertagexample:", filtertagexample
}}
