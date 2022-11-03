---
layout: page
title: User Guide
---

Healthcare Xpress is a **desktop app for managing patients that require home-visits and nurses, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Healthcare Xpress can get your contact management tasks done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest [healthcarexpress.jar](https://github.com/AY2223S1-CS2103-F13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Healthcare Xpress Record System.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/UiV1.3.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all contacts.

   - **`add`** `c/N n/Jane p/98723432 e/jason@example.com g/F t/Asthma` : Adds a nurse named `Jane` to the Healthcare Xpress book.

   - **`add`** `c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block 123, #01-01 t/Asthma ds/2022-12-12,3` : Adds a patient named **`John`** to Healthcare Xpress book.

   - **`assign`** `id/1 id/3 dsi/1` : Assigns the first dateslot of the patient with id of 3 to the nurse with id of 1.

   - **`delete`** `id/3` : Deletes the nurse of patient with an id of 3.

   - **`clear`** : Deletes all contacts.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Additional information:**<br>

- The HomeVisits is the homevisits that the nurse have to attend to and it is displayed in the format of `Date and Time : [UID] Patient Uid`. This cannot be added using the add command. It can only be added using the assign command.

- The HomeVisits DateSlot is the home visit date slot of the patient and it is displayed in the format of `[ ][ ] Date and Time`.

- The first bracket is to indicate whether this date slot has been assigned to a nurse. (Blank - not assigned, A - assigned).

- The second bracket is to indicate whether this date slot has pass and visited. (Blank - the date slot has not pass, V - the date slot has pass and autochange to visited, FV - the date slot has pass but fail to visit).

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

![help message](images/Help.png)

### Adding a nurse or patient: `add`

1. Adds a patient to the Healthcare Xpress Record System.

Format: `add c/P n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​ [ds/DATE_AND_SLOT]…​`

<div markdown="block" class="alert alert-primary">

:bulb:**Tips:**<br>

- A patient can have any number of tags (including 0).

- A patient can have any number of home-visit date and slot (including 0).

- Date and slot need to be in the format of `yyyy-MM-dd,SLOT_NUMBER`, eg `2022-11-11,2`.

- The slot timing is fixed and slots are only from 10am to 4pm.

- **Slot 1: 10am, Slot 2: 12pm, Slot 3: 2pm, Slot 4: 4pm.** The `SLOT_NUMBER` can only be from 1 to 4.

- To add a patient, type `c/P` specifically.

</div>

Examples:

- `add c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block 123, #01-01 t/asthma ds/2022-12-12,4`
- `add c/P n/Jackson g/M t/heartDisease a/Bishan Street 32, Singapore 291038 #04-11 p/9019390 e/jackson@gmail.com ds/2022-11-11,3 ds/2022-12-12,3`

![add patient](images/AddPatient.png)

2. Adds a nurse to the Healthcare Xpress Record System

Format:`add c/N n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​ [ud/UNAVAILABLE_DATE]…​`

<div markdown="span" class="alert alert-primary">

:bulb:**Tips**:

- A nurse can have any number of tags (including 0).
- A nurse can have any number of unavailable date (including 0).
- The unavailable date must be in `yyyy-MM-dd` format, eg `2022-11-11`.
- You may type it in any order.
- To add a nurse, type `c/N` specifically.

</div>

Examples:

- `add c/N n/Jason p/98723432 e/jason@example.com g/M a/Blk 855 Woodlands Street 83, Singapore 730855, block 123, #01-01 t/asthma ud/2022-11-11`
- `add c/N n/Lily g/F p/92091883 e/lily@gmail.com ud/2022-11-11 a/Woodland Street 21 Block 211 #01-02 t/heartDiseaseSpecialist`

![add nurse](images/AddNurse.png)

### Listing nurses or patients : `list`

Shows a list of specified nurses or patients, or all nurses and patients if no specifications were provided.

Format: `list [c/CATEGORY] [t/TAG] [g/GENDER] [a/ADDRESS] [as/ASSIGNED] [v/VISITSTATUS]`

Examples:

- `list c/P t/DIABETIC g/M` - Lists all the male diabetic patients enrolled in the database.
- `list c/P a/Bugis t/heartDisease` - Lists all the patients tagged with heart disease in the Bugis region.
- `list c/P as/true` - Lists all the patients that have all required timeslots assigned to a nurse.
- `list c/P as/false` - Lists all the patients that have at least 1 timeslot without an assigned nurse.
- `list c/N as/true` - Lists all the nurses that have all timeslots fully assigned to an appointment.
- `list c/N as/false` - Lists all the nurses that have at least 1 timeslot not assigned to an appointment.
- `list c/P v/true` - Lists all the patients that have been visited by nurses in all their scheduled appointments.
- `list c/P v/false` - Lists all the patients that have at least one appointment that has yet to be visited by a nurse.
- `list c/N v/true` - Lists all the nurses that have visited all scheduled appointments in the upcoming week.
- `list c/N v/false` - Lists all the nurses that have at least one scheduled appointment pending a visit in the upcoming week.
- `list c/N` - Lists all the nurses enrolled in the database.

![list nurse](images/ListNurse.png)

### Editing a person : `edit`

Edits an existing person in the Healthcare Xpress Record System.

Format: `edit id/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​ [ds/DATE_AND_SLOT]…​ [dsi/DATE_AND_SLOT_INDEX]…​ [ud/UNAVAILABLE_DATE]…​ [udi/UNAVAILABLE_DATE]…​`

- Edits the person with the specified `ID`.
- The ID refers to the unique ID number shown in the displayed person list.
- The ID **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When **editing tags**, the **existing tags of the person will be removed** i.e adding of tags is not cumulative.
- You can **remove all the person’s tags** by typing `t/` without specifying any tags after it.
- The `DATE_AND_SLOT_INDEX` is the index of the date and slot list of a patient starting from 1.
- When **editing date and slot**, there are **4 options**:
  1. To delete all the existing date and slot of a patient: you can type `ds/` or `dsi/` or `ds/ dsi/` without specifying a date and slot or its index after it.
  2. To delete a specific date and slot of a patient: you can type `ds/ dsi/TO_BE_DELETED_DATE_AND_TIME_INDEX` or `dsi/TO_BE_DELETED_DATE_AND_TIME_INDEX`. The to be deleted date and slot index is the index of the specific date and slot you want to delete.
  3. To add a new date and slot of a patient: you can type `ds/NEW_DATE_AND_SLOT dsi/` or `ds/NEW_DATE_AND_SLOT`.
  4. To change a specific date and slot of a patient: you can type `ds/UPDATE_DATE_AND_SLOT dsi/TO_BE_UPDATED_DATE_AND_SLOT_INDEX`. The date and slot at this index in the list will be updated to the new date and slot given by you.
- You can only use `ds/` and `dsi/` for **patient**. Nurse do not have any home-visit dates and slots.
- The unavailable date works similar to the date and time edit excepts using different indicator `ud/` and `udi/` to indicate the date and the index.
- The **unavailable date** is only applicable to **nurse**.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

- When changing a date slot in the existing list, if the old date slot is assigned, it will be deassigned and the new date slot will be in "not assigned status".

- When changing an unavailable date in the existing list or adding a new unavailable date in the existing list from a nurse, the date slot assigned to that nurse will be checked against the unavailable date. If the date slot is on the same day with the unavailable date, it will auto deassign that date slot from the nurse.
</div>

Examples:

- `edit id/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the nurse/patient with id 1 to be `91234567` and `johndoe@example.com` respectively.
- `edit id/2 n/Betsy Crower t/` Edits the name of the nurse/patient with id 2 to be `Betsy Crower` and clears all existing tags.
- `edit id/2 dti/1 dt/2022-12-11,1` Change the first date and time of the patient with id 2 to `2022-12-11,1`.
- `edit id/1 e/bsy@gmail.com ud/2022-12-10 p/9029901` Change the email to `bsy@gmail.com` and phone to `9029901` and add unavailable date `2022-12-10` to the nurse with id 1.

![edit](images/Edit.png)

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
- `find Jason` returns `Jason`

![find](images/Find.png)

### Deleting a nurse or a patient : `delete`

Deletes the specified nurse or patient from the records system.

Format: `delete id/ID`

- Deletes the nurse or patient with the specified `ID`.
- The ID refers to the unique ID number shown in the displayed person list.
- The ID **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete id/2` deletes the nurse/patient with an id of 2.
- `find Betsy` followed by `delete id/1` deletes the nurse/patient with an id of 1.
- `delete id/10` deletes the nurse/patient with an id of 10.

![delete](images/Delete.png)

### Assigning a patient's homevisit date slot to a nurse : `assign`

Assign a specific patient's date slot/date slots to a nurse.

Format `assign id/NURSE_ID id/PATIENT_ID [dsi/DATE_AND_SLOT_INDEX]…​`

- Assign the dateslots of the patient with the specified 'PATIENT_ID' to the nurse with the specified 'NURSE_ID'.
- The ID refers to the unique ID shown in the displayed person list.
- The ID **must be a positive integer** 1, 2, 3, ...
- There **must be 2 IDs (only 2), 1 indicating a patient and 1 indicating a nurse**.
- There is no specific order for the 2 IDs.
- The assign command can have any number of date and slot index (including 0).
- If the **'DATE_AND_SLOT_INDEX' is not indicated** (0), then all the date slot of the patients will be assigned to the nurse.
- If the **'DATE_AND_SLOT_INDEX(ES)' is indicated**, then the date slot with the respective index(es) in the displayed dateslot list will be assigned to the nurse.
- When assigning, it will check whether there are **time crashes** and check whether the nurse are **unavailable** on that day.

Examples:

- `assign id/1 id/2` assign all the date slots of patient with id of 2 to nurse with id of 1.
- `assign id/2 id/1 dsi/2` assign the date slot with index 2 in the dateslot list of patient with id of 2 to nurse with id of 1.
- `assign id/1 id/7 dsi/3` assign the third date slot in the dateslot list of patient with id 7 to nurse with id 1.

![assign](images/Assign.png)

### Deassigning a patient's homevisit date slot from a nurse : `deassign`

Deassign a specific patient's date slot from a nurse.

Format `deassign id/ID [dsi/DATE_AND_SLOT_INDEX]…​`

- The 'ID' can be either a patient id or nurse id.
- The deassign command can have any number of date and slot index (including 0).
- If it is a **patient id and no date and slot index** indicated, it will deassign all the date slots of the patient from the respective nurse.
- If it is a **patient id and date and slot index** indicated, it will deassign the specific date slots (with the date and slot index indicated) of the patient from the respective nurse.
- If it is a **nurse id and no date and slot index** indicated, it will deassign all the homevisits of the nurse and the respective patient's date slot will change `not assigned` status.
- If it is a **nurse id and no date and slot index** indicated, it will deassign the specific homevisits of the nurse (with the date and slot index indicated) and the respective patient's date slot will change `not assigned` status.

Examples:

- `deassign id/1` deassign all the date slots of nurse with id of 1.
- `deassign id/2 dsi/2` deassign the date slot with index 2 in the dateslot list of patient with id of 2.
- `deassign id/1 dsi/2` deasign the second home visits in the homevisit list of nurse with id of 1.

![deassign](images/Deassign.png)

### Unmarking a patient's dateslot as fail visited : `unmark`

Unmarks a specific patient's specific dateslot in the records system as failed visited.

Format: `unmark id/PATIENT_ID dsi/DATE_AND_SLOT_INDEX`

- Unmarks the patient with the specified 'ID' as having been failed visited.
- The ID refers to the unique ID shown in the displayed person list.
- The ID **must be a positive integer** 1, 2, 3, ...
- The ID **must belong to a patient**.
- The **DATE_AND_SLOT_INDEX must be indicated** and can have only **1**.
- The **date that is to be unmarked as failed visited must be a date that have passed**. eg, if today is 2022-11-11, the date to be unmark as fail visited must be the date before 2022-11-11.

Examples:

- `unmark id/7 dsi/1` unmarks the dateslots of index 1 in the dateslot list of the patient with id of 7 as having been failed visited.

![unmark](images/Unmark.png)

### Undo unmarking a patient as visited : `undounmark`

Undo Unmarks a specific patient's specific dateslot in the records system as having been visited.

Format: `undounmark id/ID dsi/DATE_SLOT_INDEX`

- Undo unmarks the specific dateslot with specified 'DATE_SLOT_INDEX' of a patient with the specified 'ID' as having been visited.
- This feature is for user to undo the unmark fail visit date slot so that if you accidentally unmark a wrong patient's dateslot as failed visited status, you can undo it using this command and the dateslot will change to visited status.
- The ID refers to the unique ID shown in the displayed person list.
- The ID **must be a positive integer** 1, 2, 3, ...
- `list` or `find` operations can be performed first to get the ID of the desired patient.
- If the 'Date_Slot_Index' gives a dateslot that has not pass or it is already in visit status, the system will not do anything.

Examples:

- `undounmark id/7 dsi/1` undo unmarks the first dateslot of the patient with id of 7 as having been visited.

![undounmark](images/UndoUnmark.png)

### Check for similar persons : `checkSimilar`

Checks and returns a list of similar persons so that the user can check if any errors has been made in entries 

Format: `checkSimilar`

> Tip: `checkSimilar` checks for `name`, `phone`, `email`, `gender`, `tags`, `address` and return both person if 
> they match 5 out of 6 conditions.

![checksimilar](images/checkSimilar.png)

### Updating a patient's emergency contact information: `updatecontact`

Updates a patient's contact information for next-of-kin or attending physician.

Format: `updatecontact id/PATIENT_ID c/CATEGORY n/CONTACT_NAME p/CONTACT_PHONE e/CONTACT_EMAIL`

- PATIENT_ID: Unique ID of the patient whose emergency contact is to be updated.
- CATEGORY: Only accepts `K` for next-of-kin or `D` for attending physician.
- CONTACT_NAME: Name of the emergency contact.
- CONTACT_PHONE: Phone number of the emergency contact.
- CONTACT_EMAIL: Email address of the emergency contact.
- Note: The UID must belong to a **patient**.
- Note: Only `K` or `D` are accepted.
- Note: This command replaces the current next-of-kin or attending physician contact info,
  - if there is already an existing one.

Examples:

- `updatecontact id/3 c/K n/John Doe p/81234567 e/johndoe@example.com`
  - updates NoK contact information for patient UID 3.
- `updatecontact id/3 c/D n/kw p/9013890 e/kw@gmail.com`
  - updates attending physician contact information for patient UID 3.

![updatecontact](images/updatecontact.png)

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Healthcare Xpress data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Healthcare Xpress data are saved as a JSON file `[JAR file location]/data/healthcarexpress.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

* If your changes to the data file makes its format invalid, Healthcare Xpress Record System will discard all data and start with an empty data file at the next run.

- NOT RECOMMENDED : It is not recommended to change the 'date slot' of a patient and 'homevisits', 'unavailable date' and 'fully scheduled date' of a nurse in the file. The system is unable to check the correctness and whether there is time crash and other issues if you change it manually in the data file.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

---

## Command summary

| Action            | Format, Examples                                                                                                                                                                                                               |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | --- |
| **Add (Nurse)**   | `add c/N n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​ [ud/UNAVAILABLE_DATE]…​ ` <br> e.g., `add c/N n/Jason p/98723432 e/jason@example.com g/M t/asthma a/Yishun Street 211, block 230, #03-03 ud/2022-11-11`    |
| **Add (Patient)** | `add c/P n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [ds/DATE_AND_SLOT]…​ [t/TAG]…​` <br> e.g., `add c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block 123, #01-01 t/Asthma ds/2022-12-12,2`            |
| **Assign**        | `assign id/PATIENT_ID id/NURSE_ID [dsi/DATE_SLOT_INDEX]…​` <br> e.g., `assign id/1 id/2 dsi/2`                                                                                                                                 |
| **Clear**         | `clear`                                                                                                                                                                                                                        |
| **Deassign**      | `deassign id/ID [dsi/DATE_SLOT_INDEX]…​` <br> e.g., `deassign id/1 dsi/2`                                                                                                                                                      |
| **Delete**        | `delete id/ID`<br> e.g., `delete id/3`                                                                                                                                                                                         |
| **Edit**          | `edit id/ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​ [ds/DATE_SLOT]…​ [dsi/DATE_SLOT_INDEX]…​ [ud/UNAVAILABLE_DATE]…​ [udi/UNAVAILABLE_DATE_INDEX]…​`<br> e.g.,`edit id/2 n/James Lee e/jameslee@example.com` |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                     |
| **Help**          | `help`                                                                                                                                                                                                                         |
| **List**          | `list [c/CATEGORY] [t/TAG] [g/GENDER] [a/ADDRESS] [as/ASSIGNED] [v/VISITSTATUS]`<br> e.g., `list c/NURSE`                                                                                                                      |     |
| **Unmark**        | `unmark id/PATIENT_ID dsi/DATE_SLOT_INDEX` <br> e.g., `unmark id/1 dsi/1`                                                                                                                                                      |
| **UndoUnmark**    | `undounmark id/PATIENT_ID dsi/DATE_SLOT_INDEX` <br> e.g., `undounmark id/1 dsi/1`                                                                                                                                              |
| **CheckSimilar**  | `checkSimilar`                                                                                                                                                                                                                 |
| **UpdateContact** | `updatecontact id/PATIENT_ID c/CATEGORY n/CONTACT_NAME p/CONTACT_PHONE e/CONTACT_EMAIL` <br> e.g., `updatecontact id/3 c/D n/Farihah p/2901939 e/hah@gmail.com`                                                                |
