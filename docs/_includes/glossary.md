<!-- markdownlint-disable-file blanks-around-headings -->
<!-- markdownlint-disable-file first-line-h1 -->
{% assign filtered = site.glossary | where_exp: "item", "item.show-in contains include.type" %}
{% assign groups = filtered | group_by_exp: "item", "item.name | truncate: 1, ''" %}

{% for group in groups %}
### {{ group.name }}
{: .no_toc}

{% for entry in group.items %}
#### {{ entry.name }}
{: .no_toc}

{{ entry }}
{% endfor %}

{% endfor %}
