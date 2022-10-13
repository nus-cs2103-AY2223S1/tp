<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture newtag %}{% include_relative _ug/commandSummary/tagCommands/newtag.md %}{% endcapture %}
{% capture listtag %}{% include_relative _ug/commandSummary/tagCommands/listtag.md %}{% endcapture %}
{% capture tag %}{% include_relative _ug/commandSummary/tagCommands/tag.md %}{% endcapture %}
{% capture untag %}{% include_relative _ug/commandSummary/tagCommands/untag.md %}{% endcapture %}
{% capture renametag %}{% include_relative _ug/commandSummary/tagCommands/renametag.md %}{% endcapture %}
{% capture deletetag %}{% include_relative _ug/commandSummary/tagCommands/deletetag.md %}{% endcapture %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                          | Format      |
|---------------------------------|-------------|
| Create a new tag                | :newtag:    |
| List all tags available         | :listtag:   |
| Tag items with a specific tag   | :tag:       |
| Untag items with a specific tag | :untag:     |
| Rename a tag                    | :renametag: |
| Delete a tag                    | :deletetag: |
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
}}
