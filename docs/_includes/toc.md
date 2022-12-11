<!-- markdownlint-disable-file -->
{% if include.header %}
{% if include.show-in-toc %}
## Table of Contents
{: #toc-heading}
{% else %}
## Table of Contents
{: .no_toc #toc-heading}
{% endif %}
{% endif %}

{% if include.ordered %}
1. Table of contents
{:toc}
{% else %}
* Table of contents
{:toc}
{% endif %}
