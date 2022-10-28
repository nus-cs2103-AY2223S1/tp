---
layout: page
title: User Guide
---
* Table of Contents
{:toc}
---

![banner](images/checkUp_banner.png)

**checkUp** is a **desktop app for managing patient details, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, checkUp can get your patient management
tasks done faster than traditional GUI apps.

checkUp stores patient details directly on your device, allowing you to keep track of your patients' details without an
internet connection. You can store details such as patient name, next-of-kin details, phone number, email, medical history, upcoming
appointments and past appointments. You can also add medication tags to your patients to keep track of their long-term
medication history.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `checkUp.jar` from [here](https://github.com/AY2223S1-CS2103T-W16-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your checkUp instance.

4. Run `java -jar checkUp.jar` to start the app. The GUI similar to the below image should appear after startup. 
5. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`add`**`n/Amy Toh p/98765432 e/johnd@example.com nok/Jane Doe, Wife, 82858285 pt/inpatient hw/south fn/3 wn/D690 m/panadol m/ibuprofen ` :
      Adds a contact named `Amy Toh` to checkUp. 

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`get`** `/n Alex`: Retrieve contact's information based on the prefix you provided. In this case, patients
    with the name `Alex` will be retrieved.

    * **`view`** `1` : Views the 1st contact shown in the current list.

7. Refer to the [Features](#features) below for details of each command.

---
## Glossary

| Term              | Definition                                                                      |
|-------------------|---------------------------------------------------------------------------------|
| **Appointment**   | An arrangement to consult the doctor at a particular time.                      |
| **Diagnosis**     | The identification of diseases by the examination of symptoms and signs.        |                                                                                                                                                                                                                                                                                    |
| **Floor Number**  | The floor where the inpatient is on.                                            |
| **Hospital Wing** | The section of the hospital where the inpatient is in.                          |
| **Inpatient**     | A patient who lives in hospital while under treatment.                          |                                                                                                                                                                                                                                                                          |
| **Medication**    | A drug or other form of medicine that is used to treat or prevent disease.      |
| **Next-Of-Kin**   | The patient's closest living relative.                                          |
| **Outpatient**    | A patient who attends a hospital for treatment without staying there overnight. |
| **Ward Number**   | The ward where the inpatient is in.                                             |

                                                                                                                                                                                                                                                                                                                                  



## Features

<div markdown="block" class="alert alert-info">

**:notebook: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `get /n NAME`, `NAME` is a parameter which can be used as `get /n John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [m/MEDICATION]` can be used as `n/John Doe m/Ibuprofen` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MEDICATION]…​` can be used as ` ` (i.e. 0 times), `m/ibuprofen`, `m/ibuprofen m/lozenges` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

**The features of CheckUp can be split into 3 main categories:**

* Creating Patient Info
* Retrieving Patient Info
* General Commands

---
## Creating Patient Info

### Adding a patient: `add`

Adds a patient to checkUp.

Format: `add n/NAME p/PHONE e/EMAIL nok/NEXT-OF-KIN_NAME, RELATIONSHIP, CONTACT pt/PATIENT_TYPE hw/HOSPITAL_WING fn/FLOOR_NUMBER wn/WARD_NUMBER [ua/UPCOMING_APPOINTMENT] [m/MEDICATION]…​`

* Medication tags are for assigning long-term medication to patients.
* Use multiple m/ prefixes if multiple medicines are prescribed.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of medications (including 0)!
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If patient type is outpatient, user should not supply any values to
hospital wing, floor number, and ward number. 
</div>

Examples:

If patient type is inpatient: <br>
`add n/John Doe p/98765432 e/johnd@example.com nok/Jane Doe, Wife, 82858285 pt/inpatient hw/south fn/3 wn/D690 m/panadol m/ibuprofen`
<br>

If patient type is outpatient: <br>
`add n/John Doe p/98765432 e/johnd@example.com nok/Jane Doe, Wife, 82858285 pt/outpatient m/panadol m/ibuprofen`

### Editing a patient: `edit`

Edits a patient in checkUp.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [nok/NEXT-OF-KIN_NAME, RELATIONSHIP, CONTACT] [pt/PATIENT_TYPE] [hw/HOSPITAL_WING]
[fn/FLOOR_NUMBER] [wn/WARD_NUMBER] [ua/UPCOMING_APPOINTMENT] [m/MEDICATION]...`

* Edits the details of the patient at the specified `INDEX`. The index refers to the index number shown in the
  displayed patient list. The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing medication, the existing medication of the person will be removed i.e adding of medication is not
  cumulative.
* You can remove all the person’s tags by typing `m/` without specifying any medication after it.
* When editing upcoming appointment dates, the date must be in `dd-MM-yyyy` format, eg. `12-06-2022`.
* To remove upcoming appointments, just type `ua/` without specifying any date after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st patient to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower m/` edits the name of the 2nd patient to be `Betsy Crower` and clears all existing medication.

#### GUI Integration:
* The individual fields on the Detailed Person View Panel are clickable to streamline the editing process.
* e.g.: If the first person is `Alex Yeoh` Click on his name will update the Command Input to `edit 1 n/`.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `get /n Betsy` followed by `delete 1` deletes the 1st person in the results of the `get /n` command.

### Creating past appointment for patient: `appt`

Creates a past appointment for the specified patient in checkUp.

Format: `appt INDEX on/DATE diag/DIAGNOSIS [m/MEDICATION]...`

* Past appointment is created for a person at the specified `INDEX`.
* The index refers to the index number showed in the displayed person list.
* The index **must be a positive integer**, eg. 1, 2, 3...
* The date can only be input in the `dd-MM-yyyy` format.
* The `DIAGNOSIS` field **cannot be empty**.
* The `MEDICATION` field(s) is/are optional. Use multiple `m/` prefixes if multiple medications are prescribed.

Examples:

* `get /n John` returns `John` at index 1 and `John Doe` at index 2.
* Following this, `appt 1 on/12-06-2022 diag/Common cold, viral flu m/Panadol m/Lozenges` will create a past appointment
  for John.

### Deleting past appointment for patient: `delappt`

Deletes the specified patient most recent past appointment in checkUp.

Format: `delappt INDEX`

* Past appointment is removed for a person at the specified `INDEX`.
* The index refers to the index number showed in the displayed person list.
* The index **must be a positive integer**, eg. 1, 2, 3...

Examples:

* `get /n John` returns `John` at index 1 and `John Doe` at index 2.
* Following this, `delappt 1` will remove John's most recent past appointment.

---
## Retrieving Patient Info

### Listing all patients: `list`

Lists all the patients in checkUp.

Format: `list`

* This command resets any filters applied via the `get` command below.

### Locating patients: `get`

#### by name: `/n`

Finds patients whose names contain any of the given keywords.

Format: `get /n NAME`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `get /n john` returns `john` and `John Doe`
* `get /n alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### by next-of-kin data: `/nok`

Finds next-of-kin data for patients matching the input `PATIENT_NAME`.

Format: `get /nok PATIENT_NAME`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* The patient's next-of-kin details matching the keyword will be returned. e.g. `Hans Bo` will return `Sarar, 12345678, Mom`

#### by hospital wing: `/hw`

Finds all the patients in that particular hospital wing.

Format: `get /hw HOSPITAL_WING`

* `HOSPITAL_WING` only allows the following values: South, North, West, East
* The search is case-insensitive. e.g `souTh` will match `South`
* Patients matching at least one keyword will be returned (i.e. `OR` search). e.g. `South No` will match `south`
* `get /hw south /hw north` matches `get /hw south north`.
* All the patients in that hospital wing will be returned. e.g. `get /hw SOUTH` will return `John` `Peter` `Mary`

#### by floor number: `/fn`

Finds all the patients in that particular floor number.

Format: `get /fn FLOOR_NUMBER`

* `FLOOR_NUMBER` only allows positive numbers. 
* Patients matching at least one keyword will be returned (i.e. `OR` search). e.g. `1 3` will match `1 OR 3`
* `get /fn 1 /fn 3` matches `get /fn 1 3`.
* All the patients in that floor number will be returned. e.g. `get /fn 2` will return `John` `Peter` `Mary`


#### by ward number: `/wn`

Finds all the patients in that particular ward number.

Format: `get /wn WARD_NUMBER`

* Patients matching at least one keyword will be returned (i.e. `OR` search). e.g. `D1 E31` will match `D1 OR E31`
* `get /wn D12 /wn E13` matches `get /wn D12 E13`. 
* All the patients in that ward number will be returned. e.g. `get /wn D12` will return `John` `Peter` `Mary`

#### by medication: `/m`

Finds all the patients by medication.

Format: `get /m MEDICATION`

All the patients being prescribed that medication will be returned.
e.g. `get /m ibuprofen` will return `John` `Peter` `Mary`

#### by patient type: `/inp`

Finds all the inpatients in checkUp.

Format: `get /inp`

Example:
* `get /inp` returns `Alex`, `Charlotte` and `Roy`

#### by patient type: `/outp`

Finds all the outpatients in checkUp.

Format: `get /outp`

Example:
* `get /outp` returns `Bernice`, `David` and `Irfan`

#### by appointments: `/appt`

Finds all the appointments of a certain patient.

Format: `get /appt INDEX`

Example: `get /appt 3` will return <br>
`On: 12 Jun 2022; Diagnosis: Common viral flu; Prescribed Medication: [lozenges][panadol]`  <br>
`On: 01 Jan 2001; Diagnosis: headache, medicine given for 3 days; Prescribed Medication: [ibuprofen]`


#### by appointment date: `/appton`

Finds all the patients that has appointment in a particular date.

Format: `get /appton APPOINTMENT_DATE`

* `Appointment Date` must be in `dd-MM-yyyy` format.
* * The appointment date refers to date the patient has an appointment with the clinic / hospital. 
* All the patients having appointments on that date will be returned. e.g. `get /appton 12-12-2020` will return `John` `Peter` `Mary`

### Obtaining total patient count: `count`

Gets total number of patients.

Format: `count`

* A count of all existing patients in the hospital will be displayed, along with a list of long-term medications that
  are being prescribed to patients, with the number of patients being prescribed each medication.
* The count will be a non-negative number (>= 0). eg. `count` displays `452` when there are 452 patients noted within
  the hospital. If 32 of those patients are on long-term antidepressants, the count will also display
  `antidepressants: 32`.

---
## General Commands

### Viewing a Patient : `view`

Displays the detailed information of a patient in the Detailed Person View Panel.

Format: `view INDEX`

* Displays the detailed information of a person at the specified `INDEX`.
* The index refers to the index number showed in the displayed person list.
* The index **must be a positive integer**, eg. 1, 2, 3...

#### GUI Integration:
* On startup, the Detailed Person View will always default to the first patient if present. 
* It will also focus onto the most recent patient added / edited.
* Clicking on patients in the Person List Panel will automatically open their info in the Detailed Person View Panel.

### Clearing all entries : `clear`

Empties checkUp of all patients stored.

Format: `clear`

* All patients will be removed from storage.
* This command is **nuclear**, and cannot be reversed. It should only be executed when absolutely necessary.
* This command is provided for privacy reasons, or to start afresh.

### Open the Help Page : `help`

Opens the Help Window.

Format: `help`

### Exiting the program : `exit`

Exits checkUp.

Format: `exit`

* GUI settings (window height and width) are reset during this process.

### Saving the data

checkUp has been created in such a manner that you do not need to manually save data. Simply executing commands saves
any data created or deleted from the application!

### Editing the data file

* checkUp stores data in the JSON format, improving readability and allowing for manually editing the data file.
* The data file can be found in `data/checkup.json` in the home folder where checkUp's `jar` file is stored.
* Care needs to be taken to follow data storage formats properly, or else the application will **reject** the data file.

### Keyboard Shortcuts
* Similar to other CLI interfaces, CheckUp supports a few keyboard shortcuts:
  * Navigate past commands with the `UP` and `DOWN` arrow keys.
  * Clear the text currently in the command box with `Ctrl + Shift + C` .

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous checkUp home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                                                                                                                                                                                                                                             |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL nok/NEXT-OF-KIN_NAME, RELATIONSHIP, CONTACT pt/PATIENT_TYPE [hw/HOSPITAL_WING] [fn/FLOOR_NUMBER] [wn/WARD_NUMBER] [ua/UPCOMING_APPOINTMENT] [m/MEDICATION]…`<br> e.g., `add n/John Doe p/98765432 e/johnd@example.com nok/Jane Doe, Wife, 82858285 pt/inpatient hw/south fn/3 wn/2 m/panadol m/ibuprofen` |                                                                                                                                                                                                                                                                          |
| **edit**        | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [nok/NEXT-OF-KIN_NAME, RELATIONSHIP, CONTACT] [pt/PATIENT_TYPE] [hw/HOSPITAL_WING] [fn/FLOOR_NUMBER] [wn/WARD_NUMBER] [ua/UPCOMING_APPOINTMENT] [m/MEDICATION]...`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                             |
| **delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                                          |
| **appt**        | `appt INDEX on/DATE diag/DIAGNOSIS [m/MEDICATION]...` <br> e.g., `appt 1 on/12-06-2022 diag/Common cold, viral flu m/panadol m/lozenges`                                                                                                                                                                                                     |                                                                                                                                                                                                                                                                                    |
| **delappt**     | `delappt INDEX` <br> e.g., `delappt 1`                                                                                                                                                                                                                                                                                                       |
| **list**        | `list`                                                                                                                                                                                                                                                                                                                                       |
| **get /n**      | `get /n NAME`<br> e.g., `get /n John`                                                                                                                                                                                                                                                                                                        |
| **get /nok**    | `get /nok PATIENT_NAME`<br> e.g., `get /nok John`                                                                                                                                                                                                                                                                                            |
| **get /hw**     | `get /hw HOSPITAL_WING`<br> e.g., `get /hw South`                                                                                                                                                                                                                                                                                            |
| **get /fn**     | `get /fn FLOOR_NUMBER` <br> e.g., `get /fn 2`                                                                                                                                                                                                                                                                                                |
| **get /wn**     | `get /wn WARD_NUMBER` <br> e.g., `get /wn D12`                                                                                                                                                                                                                                                                                               |
| **get /inp**    | `get /inp`                                                                                                                                                                                                                                                                                                                                   |
| **get /outp**   | `get /outp`                                                                                                                                                                                                                                                                                                                                  |
| **get /m**      | `get /m MEDICATION` <br> e.g., `get /m ibuprofen`                                                                                                                                                                                                                                                                                            |
| **get /appt**   | `get /appt INDEX` <br> e.g., `get /appt 3`                                                                                                                                                                                                                                                                                                   |
| **get /appton** | `get /appton APPOINTMENT_DATE` <br> e.g., `get /appton 21-05-2020`                                                                                                                                                                                                                                                                           |
| **count**       | `count`                                                                                                                                                                                                                                                                                                                                      |
| **view**        | `view INDEX` <br> e.g., `view 1`                                                                                                                                                                                                                                                                                                             |
| **clear**       | `clear`                                                                                                                                                                                                                                                                                                                                      |
| **help**        | `help`                                                                                                                                                                                                                                                                                                                                       |
| **exit**        | `exit`                                                                                                                                                                                                                                                                                                                                       |
