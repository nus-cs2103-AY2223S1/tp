<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable no-space-in-emphasis -->
{% capture help %}{% include_relative _ug/commandSummary/otherCommands/help.md %}{% endcapture %}
{% capture reset %}{% include_relative _ug/commandSummary/otherCommands/reset.md %}{% endcapture %}
{% capture exit %}{% include_relative _ug/commandSummary/otherCommands/exit.md %}{% endcapture %}
<!-- markdownlint-enable no-space-in-emphasis -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                | Format  |
|-------------------------------------------------------|---------|
| Shows a help dialog with a list of available commands | :help:  |
| Reset the application                                 | :reset: |
| Exit the application                                  | :exit:  |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":help:", help
  | replace: ":reset:", reset
  | replace: ":exit:", exit
}}
