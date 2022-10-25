---
layout: page
title: Glossary
---

### Idempotent

This term describes an operation which can be applied multiple times without changing the result beyond the initial application.

For example:

- multiplying a number by 0 is *idempotent* as the first application sets the number to 0, and repeated applications do not change this value
- activating a crosswalk button is also *idempotent* as the initial activation causes the system to move into a requesting state,
  until the request is satisfied. Subsequent activations of the button have no further effect. (adapted from [Wikipedia](https://en.wikipedia.org/wiki/Idempotence#Applied_examples))