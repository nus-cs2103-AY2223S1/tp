<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture stats %}{% include_relative _ug/commandSummary/statisticsCommands/stats.md %}{% endcapture %}
{% assign stats = stats | markdownify %}
{% capture statsexample %}{% include_relative _ug/commandSummary/statisticsCommandsExamples/stats.md %}{% endcapture %}
{% assign statsexample = statsexample | markdownify %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Action                                                | Format  | Example        |
|-------------------------------------------------------|---------|----------------|
| Displays relevant statistics tracked by FoodRem       | :stats: | :statsexample: |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
| markdownify
| replace: ":stats:", stats
| replace: ":statsexample:", statsexample
}}
