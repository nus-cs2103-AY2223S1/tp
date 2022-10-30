---
layout: page
title: FAQ
---

#### [Back to Menu](../UserGuide.md)

## FAQ

**Q: Can I search using fields other than the name?**

**A**: You can use the `filter` command to search for people using the other fields.

**Q: Why are the inputs for `showonly` and `hideonly` single letters, but slash-separated prefixes and values for `add`, `edit`, `filter`?**

**A**: This is because in `add`, `edit` and `filter`, we need to make sure the values entered by the user are attached 
to the correct fields. For example, in `edit 2 n/Tan Yong Xun e/tyx@email.com`, the prefixes are used to tell us that 
the new value for `name` is `Tan Yong Xun` and the new value for `email` is `tyx@email.com`.
For `showonly` and `hideonly`, however, there is no such value tied to each column name. All we need is to know the 
unique first letters of each field name to determine which columns to show or hide. We ***could have*** used 
standalone prefixes for the command format, but we found single letters to be more intuitive (and easier to type)!

[comment]: <> (**Q: What is the difference between `list /i n p e` and `showonly n p e`?**)

[comment]: <> (**A**: There are two differences. Firstly, calling `list` will result in *all* residents being listed. This means that )

[comment]: <> (any effects of `find` or `filter` will be erased, and the full resident list will be displayed in the table. Secondly, )

[comment]: <> (`list /i` &#40;and any other version of `list`&#41; will produce the same result *regardless* of the current state of the table. )

[comment]: <> (This is unlike `showonly`, which can only restrict the table view based on columns that are already present.  )


[Back to Top](#back-to-menu)
