<!-- markdownlint-disable-file -->
{% if include.header %}
{% if include.show-in-toc %}
## Table of Contents
{% else %}
## Table of Contents
{: .no_toc}
{% endif %}
{% endif %}

{% if include.ordered %}
1. Table of contents
{:toc}
{% else %}
* Table of contents
{:toc}
{% endif %}
