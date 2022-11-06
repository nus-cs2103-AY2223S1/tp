<!-- markdownlint-disable-file first-line-h1 -->
<!-- markdownlint-disable-next-line no-inline-html -->
<div class="command-container" markdown="1">
{% if include.input %}

<!-- markdownlint-disable-next-line no-inline-html -->
<div class="input-container" markdown="1">
**Command Input Box:**

```text
{{ include.input }}
```

{{ include.notes }}

</div>
{% endif %}
{% if include.commandOutputBox %}
![]({{ include.commandOutputBox }})
{: .output-image data-type="Command Output Box:" }
{% endif %}
{% if include.itemListBox %}
![]({{ include.itemListBox }})
{: .output-image data-type="Item List Box:" }
{% endif %}
</div>
