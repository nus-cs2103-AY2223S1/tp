<!-- markdownlint-disable-file first-line-h1 -->

<!-- ===== DECLARE VARIABLES ===== -->
<!-- markdownlint-disable -->
{% capture PURCHASING_MANAGER %}{% include_relative _dg/userStories/PURCHASING_MANAGER.md %}{% endcapture %}
{% capture C_ADD_ITEM %}{% include_relative _dg/userStories/can/C_ADD_ITEM.md %}{% endcapture %}
{% capture C_CREATE_TAG %}{% include_relative _dg/userStories/can/C_CREATE_TAG.md %}{% endcapture %}
{% capture C_DEC_ITEM %}{% include_relative _dg/userStories/can/C_DEC_ITEM.md %}{% endcapture %}
{% capture C_DELETE_ITEM %}{% include_relative _dg/userStories/can/C_DELETE_ITEM.md %}{% endcapture %}
{% capture C_DELETE_TAG %}{% include_relative _dg/userStories/can/C_DELETE_TAG.md %}{% endcapture %}
{% capture C_EDIT_BOUGHT_DATE %}{% include_relative _dg/userStories/can/C_EDIT_BOUGHT_DATE.md %}{% endcapture %}
{% capture C_EDIT_EXPIRY_DATE %}{% include_relative _dg/userStories/can/C_EDIT_EXPIRY_DATE.md %}{% endcapture %}
{% capture C_EDIT_ITEM_QTY %}{% include_relative _dg/userStories/can/C_EDIT_ITEM_QTY.md %}{% endcapture %}
{% capture C_INC_ITEM %}{% include_relative _dg/userStories/can/C_INC_ITEM.md %}{% endcapture %}
{% capture C_LIST_TAGS %}{% include_relative _dg/userStories/can/C_LIST_TAGS.md %}{% endcapture %}
{% capture C_RENAME_ITEM %}{% include_relative _dg/userStories/can/C_RENAME_ITEM.md %}{% endcapture %}
{% capture C_RENAME_TAG %}{% include_relative _dg/userStories/can/C_RENAME_TAG.md %}{% endcapture %}
{% capture C_SEARCH_ITEM_NAME %}{% include_relative _dg/userStories/can/C_SEARCH_ITEM_NAME.md %}{% endcapture %}
{% capture C_SEARCH_ITEM_TAG %}{% include_relative _dg/userStories/can/C_SEARCH_ITEM_TAG.md %}{% endcapture %}
{% capture C_SORT_BOUGHT_DATE %}{% include_relative _dg/userStories/can/C_SORT_BOUGHT_DATE.md %}{% endcapture %}
{% capture C_SORT_EXPIRY_DATE %}{% include_relative _dg/userStories/can/C_SORT_EXPIRY_DATE.md %}{% endcapture %}
{% capture C_SORT_ITEM_NAME %}{% include_relative _dg/userStories/can/C_SORT_ITEM_NAME.md %}{% endcapture %}
{% capture C_SORT_PRICE %}{% include_relative _dg/userStories/can/C_SORT_PRICE.md %}{% endcapture %}
{% capture C_SORT_QTY %}{% include_relative _dg/userStories/can/C_SORT_QTY.md %}{% endcapture %}
{% capture C_SORT_REMARKS %}{% include_relative _dg/userStories/can/C_SORT_REMARKS.md %}{% endcapture %}
{% capture C_TAG_ITEM %}{% include_relative _dg/userStories/can/C_TAG_ITEM.md %}{% endcapture %}
{% capture C_UNTAG_ITEM %}{% include_relative _dg/userStories/can/C_UNTAG_ITEM.md %}{% endcapture %}
{% capture C_VIEW_ALL_ITEMS %}{% include_relative _dg/userStories/can/C_VIEW_ALL_ITEMS.md %}{% endcapture %}
{% capture C_VIEW_SINGLE_ITEM %}{% include_relative _dg/userStories/can/C_VIEW_SINGLE_ITEM.md %}{% endcapture %}

{% capture ST_ADD_ITEM %}{% include_relative _dg/userStories/soThat/ST_ADD_ITEM.md %}{% endcapture %}
{% capture ST_CREATE_TAG %}{% include_relative _dg/userStories/soThat/ST_CREATE_TAG.md %}{% endcapture %}
{% capture ST_DEC_ITEM %}{% include_relative _dg/userStories/soThat/ST_DEC_ITEM.md %}{% endcapture %}
{% capture ST_DELETE_ITEM %}{% include_relative _dg/userStories/soThat/ST_DELETE_ITEM.md %}{% endcapture %}
{% capture ST_DELETE_TAG %}{% include_relative _dg/userStories/soThat/ST_DELETE_TAG.md %}{% endcapture %}
{% capture ST_EDIT_BOUGHT_DATE %}{% include_relative _dg/userStories/soThat/ST_EDIT_BOUGHT_DATE.md %}{% endcapture %}
{% capture ST_EDIT_EXPIRY_DATE %}{% include_relative _dg/userStories/soThat/ST_EDIT_EXPIRY_DATE.md %}{% endcapture %}
{% capture ST_EDIT_ITEM_QTY %}{% include_relative _dg/userStories/soThat/ST_EDIT_ITEM_QTY.md %}{% endcapture %}
{% capture ST_INC_ITEM %}{% include_relative _dg/userStories/soThat/ST_INC_ITEM.md %}{% endcapture %}
{% capture ST_LIST_TAGS %}{% include_relative _dg/userStories/soThat/ST_LIST_TAGS.md %}{% endcapture %}
{% capture ST_RENAME_ITEM %}{% include_relative _dg/userStories/soThat/ST_RENAME_ITEM.md %}{% endcapture %}
{% capture ST_RENAME_TAG %}{% include_relative _dg/userStories/soThat/ST_RENAME_TAG.md %}{% endcapture %}
{% capture ST_SEARCH_ITEM_NAME %}{% include_relative _dg/userStories/soThat/ST_SEARCH_ITEM_NAME.md %}{% endcapture %}
{% capture ST_SEARCH_ITEM_TAG %}{% include_relative _dg/userStories/soThat/ST_SEARCH_ITEM_TAG.md %}{% endcapture %}
{% capture ST_SORT_BOUGHT_DATE %}{% include_relative _dg/userStories/soThat/ST_SORT_BOUGHT_DATE.md %}{% endcapture %}
{% capture ST_SORT_EXPIRY_DATE %}{% include_relative _dg/userStories/soThat/ST_SORT_EXPIRY_DATE.md %}{% endcapture %}
{% capture ST_SORT_ITEM_NAME %}{% include_relative _dg/userStories/soThat/ST_SORT_ITEM_NAME.md %}{% endcapture %}
{% capture ST_SORT_PRICE %}{% include_relative _dg/userStories/soThat/ST_SORT_PRICE.md %}{% endcapture %}
{% capture ST_SORT_QTY %}{% include_relative _dg/userStories/soThat/ST_SORT_QTY.md %}{% endcapture %}
{% capture ST_SORT_REMARKS %}{% include_relative _dg/userStories/soThat/ST_SORT_REMARKS.md %}{% endcapture %}
{% capture ST_TAG_ITEM %}{% include_relative _dg/userStories/soThat/ST_TAG_ITEM.md %}{% endcapture %}
{% capture ST_UNTAG_ITEM %}{% include_relative _dg/userStories/soThat/ST_UNTAG_ITEM.md %}{% endcapture %}
{% capture ST_VIEW_ALL_ITEMS %}{% include_relative _dg/userStories/soThat/ST_VIEW_ALL_ITEMS.md %}{% endcapture %}
{% capture ST_VIEW_SINGLE_ITEM %}{% include_relative _dg/userStories/soThat/ST_VIEW_SINGLE_ITEM.md %}{% endcapture %}

{% assign PURCHASING_MANAGER = PURCHASING_MANAGER | markdownify %}
{% assign C_ADD_ITEM = C_ADD_ITEM | markdownify %}
{% assign C_CREATE_TAG = C_CREATE_TAG | markdownify %}
{% assign C_DEC_ITEM = C_DEC_ITEM | markdownify %}
{% assign C_DELETE_ITEM = C_DELETE_ITEM | markdownify %}
{% assign C_DELETE_TAG = C_DELETE_TAG | markdownify %}
{% assign C_EDIT_BOUGHT_DATE = C_EDIT_BOUGHT_DATE | markdownify %}
{% assign C_EDIT_EXPIRY_DATE = C_EDIT_EXPIRY_DATE | markdownify %}
{% assign C_EDIT_ITEM_QTY = C_EDIT_ITEM_QTY | markdownify %}
{% assign C_INC_ITEM = C_INC_ITEM | markdownify %}
{% assign C_LIST_TAGS = C_LIST_TAGS | markdownify %}
{% assign C_RENAME_ITEM = C_RENAME_ITEM | markdownify %}
{% assign C_RENAME_TAG = C_RENAME_TAG | markdownify %}
{% assign C_SEARCH_ITEM_NAME = C_SEARCH_ITEM_NAME | markdownify %}
{% assign C_SEARCH_ITEM_TAG = C_SEARCH_ITEM_TAG | markdownify %}
{% assign C_SORT_BOUGHT_DATE = C_SORT_BOUGHT_DATE | markdownify %}
{% assign C_SORT_EXPIRY_DATE = C_SORT_EXPIRY_DATE | markdownify %}
{% assign C_SORT_ITEM_NAME = C_SORT_ITEM_NAME | markdownify %}
{% assign C_SORT_PRICE = C_SORT_PRICE | markdownify %}
{% assign C_SORT_QTY = C_SORT_QTY | markdownify %}
{% assign C_SORT_REMARKS = C_SORT_REMARKS | markdownify %}
{% assign C_TAG_ITEM = C_TAG_ITEM | markdownify %}
{% assign C_UNTAG_ITEM = C_UNTAG_ITEM | markdownify %}
{% assign C_VIEW_ALL_ITEMS = C_VIEW_ALL_ITEMS | markdownify %}
{% assign C_VIEW_SINGLE_ITEM = C_VIEW_SINGLE_ITEM | markdownify %}

{% assign ST_ADD_ITEM = ST_ADD_ITEM | markdownify %}
{% assign ST_CREATE_TAG = ST_CREATE_TAG | markdownify %}
{% assign ST_DEC_ITEM = ST_DEC_ITEM | markdownify %}
{% assign ST_DELETE_ITEM = ST_DELETE_ITEM | markdownify %}
{% assign ST_DELETE_TAG = ST_DELETE_TAG | markdownify %}
{% assign ST_EDIT_BOUGHT_DATE = ST_EDIT_BOUGHT_DATE | markdownify %}
{% assign ST_EDIT_EXPIRY_DATE = ST_EDIT_EXPIRY_DATE | markdownify %}
{% assign ST_EDIT_ITEM_QTY = ST_EDIT_ITEM_QTY | markdownify %}
{% assign ST_INC_ITEM = ST_INC_ITEM | markdownify %}
{% assign ST_LIST_TAGS = ST_LIST_TAGS | markdownify %}
{% assign ST_RENAME_ITEM = ST_RENAME_ITEM | markdownify %}
{% assign ST_RENAME_TAG = ST_RENAME_TAG | markdownify %}
{% assign ST_SEARCH_ITEM_NAME = ST_SEARCH_ITEM_NAME | markdownify %}
{% assign ST_SEARCH_ITEM_TAG = ST_SEARCH_ITEM_TAG | markdownify %}
{% assign ST_SORT_BOUGHT_DATE = ST_SORT_BOUGHT_DATE | markdownify %}
{% assign ST_SORT_EXPIRY_DATE = ST_SORT_EXPIRY_DATE | markdownify %}
{% assign ST_SORT_ITEM_NAME = ST_SORT_ITEM_NAME | markdownify %}
{% assign ST_SORT_PRICE = ST_SORT_PRICE | markdownify %}
{% assign ST_SORT_QTY = ST_SORT_QTY | markdownify %}
{% assign ST_SORT_REMARKS = ST_SORT_REMARKS | markdownify %}
{% assign ST_TAG_ITEM = ST_TAG_ITEM | markdownify %}
{% assign ST_UNTAG_ITEM = ST_UNTAG_ITEM | markdownify %}
{% assign ST_VIEW_ALL_ITEMS = ST_VIEW_ALL_ITEMS | markdownify %}
{% assign ST_VIEW_SINGLE_ITEM = ST_VIEW_SINGLE_ITEM | markdownify %}


<!-- markdownlint-restore -->

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

<!-- ===== CREATE TABLE FORMATTING IN NORMAL+ MARKDOWN ===== -->
<!-- WE USE :variable: FOR VALUES THAT ARE TO BE SUBSTITUTED -->
{% capture TABLE %}
| **Priority** |      **As a**        |      **I can**       |      **So That**      |
|--------------|----------------------|----------------------|-----------------------|
|    `***`     | :PURCHASING_MANAGER: | :C_ADD_ITEM:         | :ST_ADD_ITEM:         |
|    `***`     | :PURCHASING_MANAGER: | :C_DELETE_ITEM:      | :ST_DELETE_ITEM:      |
|    `***`     | :PURCHASING_MANAGER: | :C_RENAME_ITEM:      | :ST_RENAME_ITEM:      |
|    `***`     | :PURCHASING_MANAGER: | :C_EDIT_ITEM_QTY:    | :ST_EDIT_ITEM_QTY:    |
|    `***`     | :PURCHASING_MANAGER: | :C_EDIT_BOUGHT_DATE: | :ST_EDIT_BOUGHT_DATE: |
|    `***`     | :PURCHASING_MANAGER: | :C_EDIT_EXPIRY_DATE: | :ST_EDIT_EXPIRY_DATE: |
|    `***`     | :PURCHASING_MANAGER: | :C_INC_ITEM:         | :ST_INC_ITEM:         |
|    `***`     | :PURCHASING_MANAGER: | :C_DEC_ITEM:         | :ST_DEC_ITEM:         |
|    `***`     | :PURCHASING_MANAGER: | :C_VIEW_ALL_ITEMS:   | :ST_VIEW_ALL_ITEMS:   |
|    `***`     | :PURCHASING_MANAGER: | :C_VIEW_SINGLE_ITEM: | :ST_VIEW_SINGLE_ITEM: |
|    `***`     | :PURCHASING_MANAGER: | :C_SEARCH_ITEM_NAME: | :ST_SEARCH_ITEM_NAME: |
|    `***`     | :PURCHASING_MANAGER: | :C_SEARCH_ITEM_TAG:  | :ST_SEARCH_ITEM_TAG:  |
|    `***`     | :PURCHASING_MANAGER: | :C_SORT_ITEM_NAME:   | :ST_SORT_ITEM_NAME:   |
|    `***`     | :PURCHASING_MANAGER: | :C_SORT_QTY:         | :ST_SORT_QTY:         |
|    `***`     | :PURCHASING_MANAGER: | :C_SORT_BOUGHT_DATE: | :ST_SORT_BOUGHT_DATE: |
|    `***`     | :PURCHASING_MANAGER: | :C_SORT_EXPIRY_DATE: | :ST_SORT_EXPIRY_DATE: |
|    `***`     | :PURCHASING_MANAGER: | :C_SORT_PRICE:       | :ST_SORT_PRICE:       |
|    `**`      | :PURCHASING_MANAGER: | :C_SORT_REMARKS:     | :C_SORT_REMARKS:      |
|    `***`     | :PURCHASING_MANAGER: | :C_LIST_TAGS:        | :C_LIST_TAGS:         |
|    `***`     | :PURCHASING_MANAGER: | :C_CREATE_TAG:       | :C_CREATE_TAG:        |
|    `***`     | :PURCHASING_MANAGER: | :C_RENAME_TAG:       | :C_RENAME_TAG:        |
|    `***`     | :PURCHASING_MANAGER: | :C_DELETE_TAG:       | :C_DELETE_TAG:        |
|    `***`     | :PURCHASING_MANAGER: | :C_TAG_ITEM:         | :C_TAG_ITEM:          |
|    `***`     | :PURCHASING_MANAGER: | :C_UNTAG_ITEM:       | :C_UNTAG_ITEM:        |

{% endcapture %}

<!-- ===== RENDER THE ACTUAL TABLE ===== -->
{{ TABLE
| markdownify
| replace: ":PURCHASING_MANAGER:", PURCHASING_MANAGER
| replace: ":C_ADD_ITEM:", C_ADD_ITEM
| replace: ":C_CREATE_TAG:", C_CREATE_TAG
| replace: ":C_DEC_ITEM:", C_DEC_ITEM
| replace: ":C_DELETE_ITEM:", C_DELETE_ITEM
| replace: ":C_DELETE_TAG:", C_DELETE_TAG
| replace: ":C_EDIT_BOUGHT_DATE:", C_EDIT_BOUGHT_DATE
| replace: ":C_EDIT_EXPIRY_DATE:", C_EDIT_EXPIRY_DATE
| replace: ":C_EDIT_ITEM_QTY:", C_EDIT_ITEM_QTY
| replace: ":C_INC_ITEM:", C_INC_ITEM
| replace: ":C_LIST_TAGS:", C_LIST_TAGS
| replace: ":C_RENAME_ITEM:", C_RENAME_ITEM
| replace: ":C_RENAME_TAG:", C_RENAME_TAG
| replace: ":C_SEARCH_ITEM_NAME:", C_SEARCH_ITEM_NAME
| replace: ":C_SEARCH_ITEM_TAG:", C_SEARCH_ITEM_TAG
| replace: ":C_SORT_BOUGHT_DATE:", C_SORT_BOUGHT_DATE
| replace: ":C_SORT_EXPIRY_DATE:", C_SORT_EXPIRY_DATE
| replace: ":C_SORT_ITEM_NAME:", C_SORT_ITEM_NAME
| replace: ":C_SORT_PRICE:", C_SORT_PRICE
| replace: ":C_SORT_QTY:", C_SORT_QTY
| replace: ":C_SORT_REMARKS:", C_SORT_REMARKS
| replace: ":C_TAG_ITEM:", C_TAG_ITEM
| replace: ":C_UNTAG_ITEM:", C_UNTAG_ITEM
| replace: ":C_VIEW_ALL_ITEMS:", VIEW_ALL_ITEMS
| replace: ":C_VIEW_SINGLE_ITEM:", C_VIEW_SINGLE_ITEM
| replace: ":ST_ADD_ITEM:", ST_ADD_ITEM
| replace: ":ST_CREATE_TAG:", ST_CREATE_TAG
| replace: ":ST_DEC_ITEM:", ST_DEC_ITEM
| replace: ":ST_DELETE_ITEM:", ST_DELETE_ITEM
| replace: ":ST_DELETE_TAG:", ST_DELETE_TAG
| replace: ":ST_EDIT_BOUGHT_DATE:", ST_EDIT_BOUGHT_DATE
| replace: ":ST_EDIT_EXPIRY_DATE:", ST_EDIT_EXPIRY_DATE
| replace: ":ST_EDIT_ITEM_QTY:", ST_EDIT_ITEM_QTY
| replace: ":ST_INC_ITEM:", ST_INC_ITEM
| replace: ":ST_LIST_TAGS:", ST_LIST_TAGS
| replace: ":ST_RENAME_ITEM:", ST_RENAME_ITEM
| replace: ":ST_RENAME_TAG:", ST_RENAME_TAG
| replace: ":ST_SEARCH_ITEM_NAME:", ST_SEARCH_ITEM_NAME
| replace: ":ST_SEARCH_ITEM_TAG:", ST_SEARCH_ITEM_TAG
| replace: ":ST_SORT_BOUGHT_DATE:", ST_SORT_BOUGHT_DATE
| replace: ":ST_SORT_EXPIRY_DATE:", ST_SORT_EXPIRY_DATE
| replace: ":ST_SORT_ITEM_NAME:", ST_SORT_ITEM_NAME
| replace: ":ST_SORT_PRICE:", ST_SORT_PRICE
| replace: ":ST_SORT_QTY:", ST_SORT_QTY
| replace: ":ST_SORT_REMARKS:", ST_SORT_REMARKS
| replace: ":ST_TAG_ITEM:", ST_TAG_ITEM
| replace: ":ST_UNTAG_ITEM:", ST_UNTAG_ITEM
| replace: ":ST_VIEW_ALL_ITEMS:", ST_VIEW_ALL_ITEMS
| replace: ":ST_VIEW_SINGLE_ITEM:", ST_VIEW_SINGLE_ITEM
}}


