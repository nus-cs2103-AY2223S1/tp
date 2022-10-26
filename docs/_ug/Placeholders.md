<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture INDEX %}{% include_relative _ug/placeholders/INDEX.md %}{% endcapture %}
{% capture ITEM_NAME %}{% include_relative _ug/placeholders/ITEM_NAME.md %}{% endcapture %}
{% capture TAG_NAME %}{% include_relative _ug/placeholders/TAG_NAME.md %}{% endcapture %}
{% capture QUANTITY %}{% include_relative _ug/placeholders/QUANTITY.md %}{% endcapture %}
{% capture UNIT %}{% include_relative _ug/placeholders/UNIT.md %}{% endcapture %}
{% capture BOUGHT_DATE %}{% include_relative _ug/placeholders/BOUGHT_DATE.md %}{% endcapture %}
{% capture EXPIRY_DATE %}{% include_relative _ug/placeholders/EXPIRY_DATE.md %}{% endcapture %}
{% capture PRICE %}{% include_relative _ug/placeholders/PRICE.md %}{% endcapture %}
{% capture REMARKS %}{% include_relative _ug/placeholders/REMARKS.md %}{% endcapture %}
{% capture COMMAND_WORD %}{% include_relative _ug/placeholders/COMMAND_WORD.md %}{% endcapture %}
{% capture KEYWORD %}{% include_relative _ug/placeholders/KEYWORD.md %}{% endcapture %}

{% assign INDEX = INDEX | markdownify %}
{% assign ITEM_NAME = ITEM_NAME | markdownify %}
{% assign TAG_NAME = TAG_NAME | markdownify %}
{% assign QUANTITY = QUANTITY | markdownify %}
{% assign UNIT = UNIT | markdownify %}
{% assign BOUGHT_DATE = BOUGHT_DATE | markdownify %}
{% assign EXPIRY_DATE = EXPIRY_DATE | markdownify %}
{% assign PRICE = PRICE | markdownify %}
{% assign REMARKS = REMARKS | markdownify %}
{% assign COMMAND_WORD = COMMAND_WORD | markdownify %}
{% assign KEYWORD = KEYWORD | markdownify %}
<!-- markdownlint-restore -->

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| Placeholders | Related Flags | Description    |
|--------------|---------------|----------------|
| INDEX        |               | :INDEX:        |
| ITEM_NAME    | n/            | :ITEM_NAME:    |
| TAG_NAME     | n/            | :TAG_NAME:     |
| QUANTITY     | qty/          | :QUANTITY:     |
| UNIT         | u/            | :UNIT:         |
| BOUGHT_DATE  | bgt/          | :BOUGHT_DATE:  |
| EXPIRY_DATE  | exp/          | :EXPIRY_DATE:  |
| PRICE        | p/            | :PRICE:        |
| REMARKS      | r/            | :REMARKS:      |
| COMMAND_WORD |               | :COMMAND_WORD: |
| KEYWORD      |               | :KEYWORD:      |
{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
  | markdownify
  | replace: ":INDEX:", INDEX
  | replace: ":ITEM_NAME:", ITEM_NAME
  | replace: ":TAG_NAME:", TAG_NAME
  | replace: ":QUANTITY:", QUANTITY
  | replace: ":UNIT:", UNIT
  | replace: ":BOUGHT_DATE:", BOUGHT_DATE
  | replace: ":EXPIRY_DATE:", EXPIRY_DATE
  | replace: ":PRICE:", PRICE
  | replace: ":REMARKS:", REMARKS
  | replace: ":COMMAND_WORD:", COMMAND_WORD
  | replace: ":KEYWORD:", KEYWORD
}}
