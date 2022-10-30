---
layout: page
title: FAQ
---

#### [Back to Menu](../UserGuide.md)

## FAQ

**Q: Can I search using fields other than the name?**

**A**: You can use the `filter` command to search for people using the other fields.

**Q: What is the difference between `list /i n p e` and `showonly n p e`?**

**A**: There are two differences. Firstly, calling `list` will result in *all* residents being listed. This means that
any effects of `find` or `filter` will be erased, and the full resident list will be displayed in the table. Secondly,
`list /i` (and any other version of `list`) will produce the same result *regardless* of the current state of the table.
This is unlike `showonly`, which can only restrict the table view based on columns that are already present.


[Back to Top](#back-to-menu)
