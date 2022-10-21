---
layout: page
title: Data Management
---

### Saving the data

RC4HDB data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

### Editing the data file

RC4HDB data are saved as a JSON file `[JAR file location]/data/FILE_NAME.json`, where `FILE_NAME` is the name of the current data file. Advanced users are welcome to update data directly by editing that data file.

:information_source: Do take note that this is not the recommended method to edit data.<br>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RC4HDB will discard all data and start with an empty data file at the next run.