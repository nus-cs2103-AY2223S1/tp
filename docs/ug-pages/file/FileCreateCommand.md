---
layout: page
title: File Create Command
---

### Creating a new data file : `file create`

Creates a new data file with the specified `FILE_NAME`, if such a file does not exist.

Format: `file create FILE_NAME`
* Does not create a new file if the file already exists.
* The file must be a `.json` file.

:information source: `FILE_NAME` must follow this [format](FileCommands.html#format).

Examples:
* `file create rc4_data_2022` will create a new file named `rc4_data_2022.json`.
* `file create rc4_data_2022.json` will create a new file named `rc4_data_2022.json.json`.

### Related sections:
* [File commands](FileCommands.html)
