---
layout: page
title: File Switch Command
---

### Switching to a different data file : `file switch`

Switches the current data file to the file specified.

Format: `file switch FILE_NAME`
* Does not create a new file if the specified file does not exist.
* The file must be a `.json` file.

:information source: `FILE_NAME` must follow this [format](FileCommands.html#format).

Examples:
* `file switch rc4_data_2022` will switch the current data file to `rc4_data_2022.json`.
* `file switch rc4_data_2022.json` will switch the current data file to `rc4_data_2022.json.json`.

### Related sections:
* [File commands](FileCommands.html)
* [Creating a new data file : file create](FileCreateCommand.html)
