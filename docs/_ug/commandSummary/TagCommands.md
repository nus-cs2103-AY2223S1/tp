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


{% capture newtagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/newtag.md %}{% endcapture %}
{% capture listtagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/listtag.md %}{% endcapture %}
{% capture tagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/tag.md %}{% endcapture %}
{% capture untagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/untag.md %}{% endcapture %}
{% capture renametagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/renametag.md %}{% endcapture %}
{% capture deletetagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/deletetag.md %}{% endcapture %}
{% capture filtertagexample %}{% include_relative _ug/commandSummary/tagCommandsExamples/filtertag.md %}{% endcapture %}

<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                          | Format      | Example            |
|---------------------------------|-------------|--------------------|
| Create a new tag                | :newtag:    | :newtagexample:    |
| List all tags available         | :listtag:   | :listtagexample:   |
| Tag items with a specific tag   | :tag:       | :tagexample:       |
| Untag items with a specific tag | :untag:     | :untagexample:     |
| Rename a tag                    | :renametag: | :renametagexample: |
| Delete a tag                    | :deletetag: | :deletetagexample: |
| Filter items by tag             | :filtertag: | :filtertagexample: |
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
