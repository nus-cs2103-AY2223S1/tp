---
layout: page
title: File Management
---

All file commands have a `FILE_NAME` field that requires you to input a name for the file you are targeting.

### File Command Format:
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

---

### Importing from CSV file : `import`

RC4HDB has the ability to import data through .csv files. In order for RC4HDB to find your files, place them in the data directory, `[JAR file location]/data`.

Format: `import FILE_NAME`

:information_source: The csv file that you want to have imported must follow this [format](#csv-file-format).<br>

Examples:
* `import students.csv`
* `import residents.csv`

---

### Exporting to CSV file : `export`

RC4HDB has the ability to export data to .csv files. The file will be safe to remove from the directory, `[JAR file location]/data`.

Format: `export FILE_NAME`

:information_source: The csv file will be exported in this [format](#csv-file-format).<br>

Examples:
* `export students.csv` will export the current data file into a csv file named `students.csv`

---

### CSV file format

| INDEX | NAME         | PHONE_NUMBER | EMAIL                 | FLOOR-UNIT | GENDER | HOUSE  | MATRIC_NUMBER | TAGS   |
|-------|--------------|--------------|-----------------------|------------|--------|--------|---------------|--------|
| 1     | John Doe     | 91234567     | johnDoe@gmail.com     |    5-8     | M      | D      | A9876543B     | -      |
| 2     | Maggie Smith | 98765432     | maggieSmith@gmail.com |    4-1     | F      | A      | A3456789B     | Friend |

---
