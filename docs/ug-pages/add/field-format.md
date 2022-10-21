---
layout: page
title: Format for resident fields
---

### Format for resident fields

`n/NAME`
* Must be a string
* Spaces are allowed

`p/PHONE_NUMBER`
* Must be an **8**-digit non-negative integer

`e/EMAIL`
* Can be any string, valid or invalid email

`r/FLOOR-UNIT`
* The floor number and unit number must be separated by a hyphen
* Both floor and unit number must be a non-negative integer
* e.g. `5-8`

`g/GENDER`
* `M` or `F`

`h/HOUSE`
* Represents the RC4 house that the resident is allocated to
* Must be either `D`, `U`, `L`, `A`, `N`
* `D` stands for **Draco**, `U` for **Ursa**, `L` for **Leo**, `A` for **Aquila**, `N` for **Noctua**

`m/MATRIC_NUMBER`
* Must be an uppercase `A`, followed by a **7**-digit non-negative integer and an uppercase alphabet.
* e.g. `A0123456A`

`t/TAG`
* Represents any other key that could be used to identify a resident
* Must be a string. No restrictions on formatting
* Optional. A resident can have any number of tags, including 0
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

_More details coming soon ..._