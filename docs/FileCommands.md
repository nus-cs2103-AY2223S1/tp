---
layout: page
title: File Commands
---

All file commands have a `FILE_NAME` field that requires you to input a name for the file you are targeting.

### Format:
* Do not include the file type in the `FILE_NAME`.
* The following symbols are not to be used in `FILE_NAME`.
    * Empty spaces
    * `.` dots
    * `/` forward slashes
    * `\ ` backslashes

### Example:
* `rc4_data_2022` is a valid `FILE_NAME`.
* `rc4_data_2022.json` is an invalid `FILE_NAME` due to the inclusion of the file type, `.json`.
* The following are invalid `FILE_NAME` due to the inclusion of restricted symbols.
  * `rc4 data 2022` has empty spaces.
  * `rc4.data.2022` has dots.
  * `rc4/data/2022` has forward slashes.
  * `rc4\data\2022` has backslashes.

### RC4HDB currently supports the following file commands:
* [Creating a new data file : file create](FileCreateCommand.html)
* [Deleting an existing data file : file delete](FileDeleteCommand.html)
* [Switching to a different data file : file switch](FileSwitchCommand.html)

### Related sections:
* [Editing the data file](UserGuide.html#editing-the-data-file)
* [Saving the data](UserGuide.html#saving-the-data)
