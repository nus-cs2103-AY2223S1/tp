---
layout: page
title: Glossary
---

#### [← Back to Menu](../UserGuide.md)

## Glossary of terms

### Idempotent

This term describes an operation which can be applied multiple times without changing the result beyond the initial
application.

For example:

- multiplying a number by 0 is *idempotent* as the first application sets the number to 0, and repeated applications
  do not change this value
- activating a crosswalk button is also *idempotent* as the initial activation causes the system to move into a
  requesting state, until the request is satisfied. Subsequent activations of the button have no further effect.
  (adapted from [Wikipedia](https://en.wikipedia.org/wiki/Idempotence#Applied_examples))

### State-independent

This term describes an operation whose result is not affected by current conditions.

For example:

- disconnecting a power plug from the wall can be considered *state-independent*, as it will force the appliance to be
  inactive regardless of whether it was active in the first place
- filling a (hole-free) bucket with a hose over a long period of time can also be considered *state-independent*,
  as the bucket will be ultimately filled regardless of the initial water level in the bucket

### CSV

This term is short for Comma-separated values. It is a common file type that can be identified through the `.csv` after a file's name.

### JSON

This term is short for JavaScript Object Notation. It is a common file type that can be identified through the `.json` after a file's name.

[↑ Back to Top](#back-to-menu)

