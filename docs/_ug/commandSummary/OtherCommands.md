<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture help %}{% include_relative _ug/commandSummary/otherCommands/help.md %}{% endcapture %}
{% capture reset %}{% include_relative _ug/commandSummary/otherCommands/reset.md %}{% endcapture %}
{% capture exit %}{% include_relative _ug/commandSummary/otherCommands/exit.md %}{% endcapture %}

{% capture helpexample %}{% include_relative _ug/commandSummary/otherCommandsExamples/help.md %}{% endcapture %}
{% capture resetexample %}{% include_relative _ug/commandSummary/otherCommandsExamples/reset.md %}{% endcapture %}
{% capture exitexample %}{% include_relative _ug/commandSummary/otherCommandsExamples/exit.md %}{% endcapture %}

<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                | Format  | Example        |
|-------------------------------------------------------|---------|----------------|
| Shows a help dialog with a list of available commands | :help:  | :helpexample:  |
| Reset the application                                 | :reset: | :resetexample: |
| Exit the application                                  | :exit:  | :exitexample:  |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":help:", help
  | replace: ":reset:", reset
  | replace: ":exit:", exit
  | replace: ":helpexample:", helpexample
  | replace: ":resetexample:", resetexample
  | replace: ":exitexample:", exitexample
}}
