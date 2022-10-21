---
layout: page
title: File Delete Command
---

### Deleting an existing data file : `file delete`

Deletes the specified data file if it exists.

Format: `file delete FILE_NAME`
* Does not delete the file if it is the data file that is currently open. You may switch to a different file before deleting the previously open data file.
* The file must be a `.json` file.

:information source: `FILE_NAME` must follow this [format](FileCommands.html#format).

Examples:
* `file delete rc4_data_2022` will delete the `rc4_data_2022.json` file.
* `file delete rc4_data_2022.json` will delete the `rc4_data_2022.json.json` file.

### Related sections:
* [File commands](FileCommands.html)
* [Switching to a different data file : file switch](FileSwitchCommand.html)
