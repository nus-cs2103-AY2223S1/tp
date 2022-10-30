---
layout: page
title: Data Management
---

#### [Back to Menu](../UserGuide.md)

### Finding your data file

**RC4HDB resident** and **venue** data is all stored locally on your hard disk in the main data folder at `[JAR file location]/data`. This folder contains all the data folders which store the **resident** and **venue** data files. The default data folder that all copies of RC4HDB start out with is the `rc4hdb` folder.

Inside your data folder, there should be two [JSON](glossary.md#json) files, `resident_data.json` and `venue_data.json`, which store your **resident** and **venue** data respectively.

---

### Editing the data file

After familiarising yourself with **RC4HDB**, you are welcome to update data directly by editing the `resident_data.json` and `venue_data.json` found in your data folder. <span style="color:red">However, do take note that this is not the recommended method to edit data.</span>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RC4HDB will discard all data and start with an empty data 
file at the next run.
</div>

---

### Saving the data

RC4HDB saves your data after every command. There is no need to save manually.

---

[↑ Back to Top](#back-to-menu)
