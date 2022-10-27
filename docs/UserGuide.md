---
layout: page
title: User Guide
---
# HealthContact User Guide


## Features
* Adding a patient (add)
* Search for a patient (search)
* Edit patient details (edit)
* Find patients (findpatient/fp)
* Find appointments (findappointment/fa)
* Find bills (findbill/fb)
* Delete patients (deletepatient/dp)
* Delete appointments (deleteappointment/da)
* Delete bills (deletebill/db)
* Sort (sort)

HealthContact is a software for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track of patient data, patient appointments and patient bills for the family clinic.
* Table of Contents
  {:toc}

---
# Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest HealthContact.jar from [here](https://github.com/AY2223S1-CS2103T-W08-1/tp/releases).

3. Copy the file to the folder you want to use as the home folder for your HealthContact application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

<img src="images/Ui.png" width="800px" height ="400px">

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

6. Refer to the [Features](#1.-Features) below for details of each command.

# 1. Features

## 1.1 Add

### 1.1.1 Adding a patient `addpatient`, `ap`

Adds a patient to the address book with input information including name, phone number,
email address, home address, remarks and tags. 

* Name must be different from existing patient. 

* Remark and tags are optional. 

* A patient can be added with multiple tags.

#### Command word 

`addpatient`, `ap`

#### Format

`Command word + <prefix><input> ...`

#### Parameter List

|     | Prefix  | Meaning       | Input Constraint                                                                                       |
|-----|---------|---------------|--------------------------------------------------------------------------------------------------------|
| `*` | `n/`    | Name          | 1. Non-empty alphanumeric characters and spaces<br/> 2. Must be different from existing patient's name |
| `*` | `p/`    | Phone number  | Numbers with at least 3 digits                                                                         |
| `*` | `e/`    | Email address | `local-part@domain`                                                                                    |
| `*` | `a/`    | Home address  | Non-empty characters                                                                                   |
|     | `r/`    | Remark        | Any characters                                                                                         |
| `+` | `t/`    | Tag           | One alphanumeric word                                                                                  |

Notes on symbols in first column:

`*`  Must have(If they are duplicate prefixes, only the last one will be taken into account)

`+`  Can have multiple

#### Examples:

* `ap n/Bernice Yu a/#11-330, blk 775, Bishan e/b.yu@nus.edu.sg p/80880011 t/NUS t/staff` adds such patient.

<img src="images/addcommand/ap1.png" width="800px" height ="400px">

* `ap n/Bernice Yu a/#01-01, blk 1, Changi Villege e/b.yu@ntu.edu.sg p/80880011 t/NTU t/staff` is unable
* to add such patient because Bernice Yu already exists in the address book.

<img src="images/addcommand/ap2.png" width="800px" height ="400px">


### 1.1.2 Adding an appointment of a patient `addappointment`, `aa`

Adds an appointment to the address book with input information including patient name, medical test,
slot, and doctor.

* Name must be the name of an existing patient.

* Slot must be in the format `yyyy-MM-dd HH:mm`, eg. `2022-11-12 13:00`.

* If slot is entered with time `24:00`, it will be saved as the `00:00` of the next date.

* The input of four parameters must be different with the combination in other appointments.

#### Command word

`addpatient`, `ap`

#### Format

`Command word + <prefix><input> ...`

#### Parameter List

|     | Prefix | Meaning      | Input Constraint                                                                        |
|-----|--------|--------------|-----------------------------------------------------------------------------------------|
| `*` | `n/`   | Name         | 1. Non-empty alphanumeric characters and spaces<br/> 2. must be existing patient's name |
| `*` | `s/`   | Slot         | Valid date and time in format `yyyy-MM-dd HH:mm`                                        |
| `*` | `d/`   | Doctor name  | `local-part@domain`                                                                     |
| `*` | `t/`   | Home address | Non-empty characters                                                                    |

Notes on symbols in first column:

`*`  Must have(If they are duplicate prefixes, only the last one will be taken into account)

`+`  Can have multiple

#### Examples:

* `aa n/Bernice Yu s/2021-10-11 12:00 d/Dioni Yong t/X-Ray` adds such appointment.

<img src="images/addcommand/aa1.png" width="800px" height ="400px">

* Executing `aa n/Bernice Yu s/2021-10-11 12:00 d/Dioni Yong t/X-Ray` again is unable
* to add such appointment because the appointment with the combination of the four inputs
* parameters already exists in the address book.

<img src="images/addcommand/aa2.png" width="800px" height ="400px">

* `aa n/Bernice Yu s/2022-01-23 09:00 d/Dioni Yong t/CT` adds another appointment for Bernice Yu.

<img src="images/addcommand/aa3.png" width="800px" height ="400px">

### 1.1.3 Adding a bill of an appointment `addbill`, `ab`

Adds a bill attached to an appointment with input information including amount and bill date.

* Amount must be positive number with at most 2 decimal places.

* One appointment can be attached to no more than one bill.

* Bill date must be in the format `yyyy-MM-dd`, eg. `2022-11-12`.

#### Command word

`addbill`, `ab`

#### Format

`Command word + <index of appointment> + <prefix><input> ...`

#### Parameter List

|      | Prefix  | Meaning              | Input Constraint                                                                                                 |
|------|---------|----------------------|------------------------------------------------------------------------------------------------------------------|
| `**` | NA      | Index of appointment | 1. Positive integer <br/> 2. Appears in the appointment list<br/>3. The indicated appointment does not have bill |
| `*`  | `a/`    | Amount               | Positive number with at most 2 decimal place                                                                     |
| `*`  | `d/`    | Bill Date            | Valid date in format `yyyy-MM-dd`                                                                          |

Notes on symbols in first column:

`**` Must be directly after command word

`*`  Must have(Accept last one when have multiple)

#### Examples:

* `ab 1 a/1200.00 d/2021-11-11` adds such bill to the first appointment in the displayed list.

<img src="images/addcommand/ab1.png" width="800px" height ="400px">

* Executing `ab 1 a/1500.00 d/2021-11-13` is unable to add such bill because the first appointment
* in the displayed list already has an attached bill.

<img src="images/addcommand/ab2.png" width="800px" height ="400px">

## 1.2 Edit

### 1.2.1 Editing a patient `editpatient` `ep`

Edits a patient's information, such as name, phone number, address, email, remarks, and tags.

Format: `editpatient INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`
         `ep INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list. 
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `editpatient 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be 
   `91234567` and `johndoe@example.com` respectively.
<img src = "images/editpatient.png" width="800px" height ="400px">

* `editpatient 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.
<img src = "images/editpatient2.png" width="800px" height ="400px">

### 1.2.2 Editing an appointment of a patient

Edits an appointment of a patient, such as name, medical test, slot, and doctor.

Format: `editappointment INDEX [n/NAME] [t/MEDICAL_TEST] [s/SLOT<yyyy-MM-dd HH:mm>] [d/DOCTOR]`
         `ea INDEX [n/NAME] [t/MEDICAL_TEST] [s/SLOT<yyyy-MM-dd HH:mm>] [d/DOCTOR]`

* Edits the appointment at the specified `INDEX`.
  The index refers to the index number shown in the displayed appointment list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `editappointment 1 n/zanw t/CT Scan s/2021-03-01 10:00 d/Tan` Edits the name, medical test, slot, 
   and doctor of the 1st appointment to be `zanw`, `CT Scan`, `2021-03-01 10:00`, and `Tan` respectively.
<img src = "images/editappointment.png" width="800px" height ="400px">

### 1.2.3 Editing a bill of an appointment

Edits a bill of an appointment.

Format: `editbill INDEX [a/amount] [d/bill date]` `eb INDEX [a/amount] [d/bill date]`

* Edits the bill at the specified `INDEX`. The index refers to the index number shown in the displayed bill list. 
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `editbill 1 a/100` Edits the amount of the 1st bill to be `100`.
<img src="images/editbill.png" width="800px" height ="400px">

* `editbill 1 d/2020-10-10` Edits the bill date of the 1st bill to be `2020-10-10`.
<img src="images/editbill2.png" width="800px" height ="400px">

## 1.3 Delete

### 1.3.1 Deleting a patient `deletepatient` `dp`

Deletes a patient by the index number of the patient in the list.

Format:
```deletepatient <targetindex>``` or ```dp <targetindex>```

* The command words are `deletepatient` or `dp`.
* The patient to be deleted is identified by using the index in the displayed list.
* Deleting a patient deletes their related appointments and bills.
* If there is no index keyed in or the command word is followed by non-numeric characters, an error message will be
  shown with the correct command format.
* If the index provided is negative or greater than the number of patients in the list, an error message will be shown
  saying the index is invalid.

Examples:

### 1.3.2 Deleting an appointment of a patient `deleteappointment` `da`

Deletes an appointment by the index number of the appointment in the list.

Format:
```deleteappointment <targetindex>``` or ```da <targetindex>```

* The command words are `deleteappointment` or `da`.
* The appointment to be deleted is identified by using the index in the displayed list.
* Deleting an appointment deletes its related bills.
* If there is no index keyed in or the command word is followed by non-numeric characters, an error message will be
  shown with the correct command format.
* If the index provided is negative or greater than the number of patients in the list, an error message will be shown
  saying the index is invalid.

Examples:

### 1.3.3 Deleting a bill of an appointment `deletebill` `db`

Deletes a bill by the index number of the bill in the list.

Format:
```deletebill <targetindex>``` or ```db <targetindex>```

* The command words are `deletebill` or `db`.
* The bill to be deleted is identified by using the index in the displayed list.
* If there is no index keyed in or the command word is followed by non-numeric characters, an error message will be
  shown with the correct command format.
* If the index provided is negative or greater than the number of patients in the list, an error message will be shown
  saying the index is invalid.

Examples:

## 1.4 Find

### 1.4.1 Finding patients `findpatient` `fp`

Filters patients by one or more fields using their prefixes, and their corresponding inputs (numbers, letters,
special characters).

Format:
```findpatient <prefix><input> ...``` or ```fp <prefix><input>...```

* The command words are ``findpatient`` or ``fp``.
* The prefixes are n/ for Name, p/ for Phone, e/ for Email, a/ for Address, r/ for Remark and t/ for Tags.
* The filter is case-insensitive. e.g. han will match Han
* Can filter using full words or partial words. e.g. han will match Hannah
* Can filter using a combination of inputs for a field, according to the constraints of the field.
  e.g. e/@gmail.com
* Can filter using one field or multiple fields at once. e.g. n/John p/91234567
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.

Examples:
* ```findpatient n/ale``` returns ``Alex Yeoh`` and ``alex tan``.

<img src="images/findpatientAlex.png" width="800px" height ="400px">

* ```fp t/friends t/colleagues n/bernice``` returns only ``Bernice Yu`` with the tags ``friends`` and ``colleagues``.

<img src="images/findpatientBernice.png" width="800px" height ="400px">

### 1.4.2 Finding appointments `findappointment` `fa`

Filters appointments by one or more fields using their prefixes, and their corresponding inputs (numbers, letters,
special characters).

Format:
```findappointment <prefix><input> ...``` or ```fa <prefix><input>...```

* The command words are `findappointment` or `fa`.
* The prefixes are n/ for Name, t/ for Medical Test, s/ for Slot and d/ for Doctor.
* The filter is case-insensitive. e.g. han will match Han
* Can filter using full words or partial words. e.g. han will match Hannah
* Can filter using a combination of inputs for a field, according to the constraints of the field.
  e.g. s/x-ray
* Can filter using one field or multiple fields at once, but each field can only be used once in a single command.
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.

Examples:
* ```findappointment t/x-ray``` returns ``Bernice Yu`` with "X-ray" appointment.

<img src="images/findappointmentXray.png" width="800px" height ="400px">

* ```fa d/Dr Tan n/Alex``` returns only ``Alex Yeoh``'s appointment with "Dr Tan".

<img src="images/findappointmentAlex.png" width="800px" height ="400px">


### 1.4.3 Finding bills `findbill` `fb`

Filters bills by one or more fields using their prefixes, and their corresponding inputs (numbers, letters,
special characters).

Format:
```findbill <prefix><input> ...``` or ```fb <prefix><input>...```

* The command words are `findbill` or `fb`.
* The prefixes are n/ for Name, p/ for Payment Status, d/ for Date and a/ for Amount.
* The filter is case-insensitive. e.g. han will match Han
* Can filter using full words or partial words. e.g. han will match Hannah
* Can filter using a combination of character(s) for a field, according to the constraints of the field.
  e.g. a/23.45
* Can filter using one field or multiple fields at once, but each field can only be used once in a single command.
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.

Examples:


### 1.4.3 Finding a bill of an appointment

## 1.5 Sort

### 1.5.1 Sorting patients

### 1.5.2 Sorting appointments

### 1.5.3 Sorting bills

## 1.6 Select

Select commands are the fast way to show the relative information to the selected item.

### 1.6.1 Selecting a patient `selectpatient`, `slp`

Selects a patient by index in the patient list. Filter the appointment list and bill list
so that these two lists shows the appointments and bills for the selected patient only.

#### Command word

`selectpatient`, `slp`

#### Format

`Command word + <index of patient>`

#### Parameter List

|      | Prefix  | Meaning          | Input Constraint                                           |
|------|---------|------------------|------------------------------------------------------------|
| `**` | NA      | Index of patient | 1. Positive integer <br/> 2. Appears in the patient list   |

Notes on symbols in first column:

`**` Must be directly after command word

#### Examples:

* `slp 1` shows the appointments and bills for the first patient in the patient list.

<img src="images/selectcommand/slp1.png" width="800px" height ="400px">

### 1.6.2 Selecting an appointment `selectappointment`, `sla`

Selects an appointment by index in the appointment list. Filter the bill list
so that bill list shows the bill for the selected appointment only.

#### Command word

`selectappointment`, `sla`

#### Format

`Command word + <index of appointment>`

#### Parameter List

|      | Prefix  | Meaning              | Input Constraint                                             |
|------|---------|----------------------|--------------------------------------------------------------|
| `**` | NA      | Index of appointment | 1. Positive integer <br/> 2. Appears in the appointment list |

Notes on symbols in first column:

`**` Must be directly after command word

#### Examples:

* `sla 1` shows the bill for the first appointment in the appointment list.

<img src="images/selectcommand/sla1.png" width="800px" height ="400px">

## 1.7 Setting Bill Payment Status

### 1.7.1 Setting Bill As Paid `setpaid` `sp`

Sets the payment status of a bill to "paid".

Format:
```setpaid <index>``` or ```sp <index>```

* The command words are `setpaid` or `sp`.
* The index refers to the index number of the bill shown in the displayed bill list.
* The index must be a valid positive integer 1, 2, 3, …​

Example:
* ```setpaid 1``` sets the first bill in the displayed bill list as paid, in this case, `Bernice Yu`'s bill.

Before:
<img src="images/setpaidcommand.png" width="800px" height ="400px">

After:
<img src="images/setpaidcommandafter.png" width="800px" height ="400px">

### 1.7.2 Setting Bill As Unpaid `setunpaid`, `sup`

Sets the payment status of a bill to "unpaid".

Format:
```setunpaid <index>``` or ```sup <index>```

* The command words are `setunpaid` or `sup`.
* The index refers to the index number of the bill shown in the displayed bill list.
* The index must be a valid positive integer 1, 2, 3, …​

Example:
* ```setunpaid 1``` sets the first bill in the displayed bill list as unpaid, in this case, `Bernice Yu`'s bill.

Before:
<img src="images/setunpaidcommand.png" width="800px" height ="400px">

After:
<img src="images/setunpaidcommandafter.png" width="800px" height ="400px">

## 1.8 Remark

## 1.9 Undo

## 1.10 Redo

## 1.11 Clear

Deletes all patients, appointments and bills from the HealthContact.

### Format


`clear`

### Example

* Executing `clear`, all data is deleted.

<img src="images/othercommands/clear.png" width="800px" height ="400px">


## 1.12 List `list`, `ls`

Removes all conditions previously applied to the list and shows all patients, appointments and bills.

### Format

`list` or `ls`

### Example

* Executing `list`, the program shows all patients, appointments and bills.

<img src="images/othercommands/ls1.png" width="800px" height ="400px">

## 1.13 Exit `exit`

Quits the HealthContact.

### Format

`exit`

### Example

* Executing `exit`, the program closes.

## 1.14 Help `help`

Opens the Help Window.

### Format

`help`

### Example

* Executing `help`, the help window pops.

<img src="images/othercommands/help.png" width="800px" height ="150px">


# 2. Commands Reference Sheet

| Feature  |                                      | Command Word        | Shortcut | Parameters                                                                                                                                                                                                                                                                                                                                                                                                   |
|----------|--------------------------------------|---------------------|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add      | a patient                            | `addpatient`        | `ap`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`p/` Phone number of patient in at least 3 digits<br/>*`e/` Email address of patient in `local-part@domain`<br/>*`a/` Home address of patient in non-empty characters<br/>`r/` Remark of patient in any characters<br/>+`t/` Tag(s) of patient in one alphanumeric word                                                           |
|          | an appointment of a patient          | `addappointment`    | `aa`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`s/` Slot of appointment in `yyyy-MM-dd HH:mm`<br/>*`d/` Doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>*`t/` Medical test of patient in non-empty characters                                                                                                                                 |
|          | a bill to an appointment             | `addbill`           | `ab`     | **`INDEX` Index of appointment<br/>*`a/` Amount of bill in a positive number with at most two decimal places<br/>*`d/` Bill date of bill in `yyyy-MM-dd`                                                                                                                                                                                                                                                     |
| Edit     | a patient                            | `editpatient`       | `ep`     | **`INDEX` Index of target patient<br/>`n/` New name of patient in non-empty alphanumeric characters and spaces<br/>`p/` New phone number of patient in at least 3 digits<br/>`e/` New email address of patient in `local-part@domain`<br/>`a/` New home address of patient in non-empty characters<br/>`r/` New remark of patient in any characters<br/>+`t/` New tag(s) of patient in one alphanumeric word |
|          | an appointment of a patient          | `editappointment`   | `ea`     | **`INDEX` Index of target appointment<br/>`n/` Name of another patient in non-empty alphanumeric characters and spaces<br/>`s/` New slot of appointment in `yyyy-MM-dd HH:mm`<br/>`d/` New doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>`t/` New medical test of patient in non-empty characters                                                                       |
|          | a bill to an appointment             | `editbill`          | `eb`     | **`INDEX` Index of target bill<br/>`a/` New amount of bill in a positive number with at most two decimal places <br/>`d/` New bill date of bill  in `yyyy-MM-dd`                                                                                                                                                                                                                                             |
| Delete   | a patient                            | `deletepatient`     | `dp`     | **`INDEX` Index of patient                                                                                                                                                                                                                                                                                                                                                                                   |
|          | an appointment of a patient          | `deleteappointment` | `da`     | **`INDEX` Index of appointment                                                                                                                                                                                                                                                                                                                                                                               |
|          | a bill to an appointment             | `deletebill`        | `db`     | **`INDEX` Index of bill                                                                                                                                                                                                                                                                                                                                                                                      |
| Find     | a patient                            | `findpatient`       | `fp`     | `n/` Name of patient<br/>`p/` Phone number of patient <br/>`e/` Email address of patient <br/>`a/` Home address of patient <br/>`r/` Remark of patient <br/>+`t/` Tag(s) of patient                                                                                                                                                                                                                          |
|          | an appointment of a patient          | `findappointment`   | `fa`     | `n/` Name of patient<br/>`s/` Slot of appointment<br/>`d/` Doctor name of the appointment<br/>`t/` Medical test of patient                                                                                                                                                                                                                                                                                   |
|          | a bill to an appointment             | `findbill`          | `fb`     | `n/` Name of patient<br/>`a/` Amount of bill<br/>`d/` Bill date of bill                                                                                                                                                                                                                                                                                                                                      |
| Sort     | a patient                            | `sortpatient`       | `sop`    |                                                                                                                                                                                                                                                                                                                                                                                                              |
|          | an appointment of a patient          | `sortappointment`   | `soa`    |                                                                                                                                                                                                                                                                                                                                                                                                              |
|          | a bill to an appointment             | `sortbill`          | `sob`    |                                                                                                                                                                                                                                                                                                                                                                                                              |
| Select   | a patient                            | `selectpatient`     | `slp`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                            |
|          | an appointment                       | `selectappointment` | `sla`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                            |
| Set bill | as Paid                              | `setpaid`           | `sp`     | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                               |
|          | as Unpaid                            | `setunpaid`         | `sup`    | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                               |
| Remark   | a patient                            | `remark`            | `r`      | **`INDEX` Index of target patient <br/> *`r/` New remark of patient in any characters                                                                                                                                                                                                                                                                                                                        |
| Undo     | the last change                      | `undo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                              |
| Redo     | the last undone change               | `redo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                              |
| Clear    | all the data saved in the software   | `clear`             |          |                                                                                                                                                                                                                                                                                                                                                                                                              |
| List     | all patients, appointments and bills | `list`              | `ls`     |                                                                                                                                                                                                                                                                                                                                                                                                              |
| Exit     | the program                          | `exit`              |          |                                                                                                                                                                                                                                                                                                                                                                                                              |
| Help     | the user with user guide             | `help`              |          |                                                                                                                                                                                                                                                                                                                                                                                                              |


Notes on symbols in parameters column:

`**` Must be directly after command word

`*`  Must have(Accept last one when have multiple)

`+`  Accept multiple

# 3. Outdated Stuffs
### Adding a patient: `add` [coming soon]

Adds a patient and his/her details, such as age, contact number and appointment date, to the app.

Format with example of usage: (in Command Terminal)

````
> add

Enter patient’s name:

> Mrs Angeline Tan

Enter patient’s age:

> 35

Enter patient’s contact number:

> 91234567

Enter patient’s next appointment date:

> 5/10/22

Mrs Angeline Tan has been added!
Name: Mrs Angeline Tan
Age: 35
Contact number: 91234567
Appointment Date: 5/10/22
````

### Editing a patient: `editpatient`, `ep`

Edit a patient’s information, such as age, contact number, appointment date and doctor’s notes.

Format: `edit Patient Title: content`

* If there is no such patient or task to edit, it will show an error.
* Existing values will be updated to the input values.

Examples:
* `edit John name: Jack` John’s name has been changed to Jack!
* `edit John number: 12345678` John’s number has been changed to 12345678.
* `edit John date: 2019-12-25` John’s appointment date has been changed to Dec 25th, 2019.
* `edit John note: use medicine` Doctor’s notes for John has been changed to use medicine.


### Sorting by criteria: `sort` [coming soon]

Sorts the patients according to name or appointment date.

Format :
```
sort by <criteria>
```
where `criteria` can be either `name` or `appointment date`.

Examples :
```
> sort by appointment date

Patients sorted by their appointment dates -
Jack Oct 12, 2022
John Oct 20, 2022
Peter Nov 1, 2022
Lara Jan 6, 2023
```

```
> sort by name

Patients sorted by name -
Alice
Bob
Jack
John 
Peter
```
