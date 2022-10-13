<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable no-space-in-emphasis -->
{% capture INDEX %}{% include_relative _ug/placeholders/INDEX.md %}{% endcapture %}
{% capture INDEX_LIST %}{% include_relative _ug/placeholders/INDEX_LIST.md %}{% endcapture %}
{% capture ITEM_NAME %}{% include_relative _ug/placeholders/ITEM_NAME.md %}{% endcapture %}
{% capture TAG_NAME %}{% include_relative _ug/placeholders/TAG_NAME.md %}{% endcapture %}
{% capture QUANTITY %}{% include_relative _ug/placeholders/QUANTITY.md %}{% endcapture %}
{% capture UNIT %}{% include_relative _ug/placeholders/UNIT.md %}{% endcapture %}
{% capture BOUGHT_DATE %}{% include_relative _ug/placeholders/BOUGHT_DATE.md %}{% endcapture %}
{% capture EXPIRY_DATE %}{% include_relative _ug/placeholders/EXPIRY_DATE.md %}{% endcapture %}
<!-- markdownlint-enable no-space-in-emphasis -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Placeholders | Related Flags | Description   |
|--------------|---------------|---------------|
| INDEX        | id/           | :INDEX:       |
| INDEX_LIST   | id/           | :INDEX_LIST:  |
| ITEM_NAME    | n/            | :ITEM_NAME:   |
| TAG_NAME     | n/            | :TAG_NAME:    |
| QUANTITY     | qty/          | :QUANTITY:    |
| UNIT         | unit/         | :UNIT:        |
| BOUGHT_DATE  | bgt/          | :BOUGHT_DATE: |
| EXPIRY_DATE  | exp/          | :EXPIRY_DATE: |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":INDEX:", INDEX
  | replace: ":INDEX_LIST:", INDEX_LIST
  | replace: ":ITEM_NAME:", ITEM_NAME
  | replace: ":TAG_NAME:", TAG_NAME
  | replace: ":QUANTITY:", QUANTITY
  | replace: ":UNIT:", UNIT
  | replace: ":BOUGHT_DATE:", BOUGHT_DATE
  | replace: ":EXPIRY_DATE:", EXPIRY_DATE
}}
