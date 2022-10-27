---
layout: page
title: User Guide
---
# HealthContact User Guide

HealthContact is a software for **XXX**.

* Table of Contents
  {:toc}

---

# 1. Features

## 1.1 Adding

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

`*`  Must have(Accept last one when have multiple)

`+`  Accept multiple

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

`*`  Must have(Accept last one when have multiple)

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

## 1.2 Editing

### 1.2.1 Editing a patient

### 1.2.2 Editing an appointment of a patient

### 1.2.3 Editing a bill of an appointment

## 1.3 Deleting

### 1.3.1 Deleting a patient

### 1.3.2 Deleting an appointment of a patient

### 1.3.3 Deleting a bill of an appointment

## 1.4 Finding

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

### 1.4.2 Finding an appointment of a patient `findappointment` `fa`

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

### 1.4.3 Finding a bill of an appointment

## 1.5 Sorting

### 1.5.1 Sorting patients

### 1.5.2 Sorting appointments

### 1.5.3 Sorting bills

## 1.6 Selecting

### 1.6.1 Selecting a patient

### 1.6.2 Selecting an appointment

## 1.7 Setting Bill Payment Status

### 1.7.1 Setting Bill As Paid

### 1.7.2 Setting Bill As Unpaid

## 1.8 Remarking

## 1.9 Undoing

## 1.10 Redoing

## 1.11 Clearing

## 1.12 Listing

## 1.13 Exiting

## 1.14 Helping


# 2. Commands Reference Sheet


| Feature      |                                      | Command Word        | Shortcut | Parameters                                                                                                                                                                                                                                                                                                                                                                                                        |
|--------------|--------------------------------------|---------------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Adding       | a patient                            | `addpatient`        | `ap`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`p/` Phone number of patient in at least than 3 digits<br/>*`e/` Email address of patient in `local-part@domain`<br/>*`a/` Home address of patient in non-empty characters<br/>`r/` Remark of patient in any characters<br/>+`t/` Tag(s) of patient in one alphanumeric word                                                           |
|              | an appointment of a patient          | `addappointment`    | `aa`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`s/` Slot of appointment in `yyyy-MM-dd HH:mm`<br/>*`d/` Doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>*`t/` Medical test of patient in non-empty characters                                                                                                                                      |
|              | a bill to an appointment             | `addbill`           | `ab`     | **`INDEX` Index of appointment<br/>*`a/` Amount of bill in a positive number with at most two decimal places<br/>*`d/` Bill date of bill in `yyyy-MM-dd`                                                                                                                                                                                                                                                          |
| Editing      | a patient                            | `editpatient`       | `ep`     | **`INDEX` Index of target patient<br/>`n/` New name of patient in non-empty alphanumeric characters and spaces<br/>`p/` New phone number of patient in at least than 3 digits<br/>`e/` New email address of patient in `local-part@domain`<br/>`a/` New home address of patient in non-empty characters<br/>`r/` New remark of patient in any characters<br/>+`t/` New tag(s) of patient in one alphanumeric word |
|              | an appointment of a patient          | `editappointment`   | `ea`     | **`INDEX` Index of target appointment<br/>`n/` Name of another patient in non-empty alphanumeric characters and spaces<br/>`s/` New slot of appointment in `yyyy-MM-dd HH:mm`<br/>`d/` New doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>`t/` New medical test of patient in non-empty characters                                                                            |
|              | a bill to an appointment             | `editbill`          | `eb`     | **`INDEX` Index of target bill<br/>`a/` New amount of bill in a positive number with at most two decimal places <br/>`d/` New bill date of bill  in `yyyy-MM-dd`                                                                                                                                                                                                                                                  |
| Deleting     | a patient                            | `deletepatient`     | `dp`     | **`INDEX` Index of patient                                                                                                                                                                                                                                                                                                                                                                                        |
|              | an appointment of a patient          | `deleteappointment` | `da`     | **`INDEX` Index of appointment                                                                                                                                                                                                                                                                                                                                                                                    |
|              | a bill to an appointment             | `deletebill`        | `db`     | **`INDEX` Index of bill                                                                                                                                                                                                                                                                                                                                                                                           |
| Finding      | a patient                            | `findpatient`       | `fp`     | `n/` Name of patient<br/>`p/` Phone number of patient <br/>`e/` Email address of patient <br/>`a/` Home address of patient <br/>`r/` Remark of patient <br/>+`t/` Tag(s) of patient                                                                                                                                                                                                                               |
|              | an appointment of a patient          | `findappointment`   | `fa`     | `n/` Name of patient<br/>`s/` Slot of appointment<br/>`d/` Doctor name of the appointment<br/>`t/` Medical test of patient                                                                                                                                                                                                                                                                                        |
|              | a bill to an appointment             | `findbill`          | `fb`     | `n/` Name of patient<br/>`a/` Amount of bill<br/>`d/` Bill date of bill                                                                                                                                                                                                                                                                                                                                           |
| Sorting      | a patient                            | `sortpatient`       | `sop`    |                                                                                                                                                                                                                                                                                                                                                                                                                   |
|              | an appointment of a patient          | `sortappointment`   | `soa`    |                                                                                                                                                                                                                                                                                                                                                                                                                   |
|              | a bill to an appointment             | `sortbill`          | `sob`    |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Selecting    | a patient                            | `selectpatient`     | `slp`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                 |
|              | an appointment                       | `selectappointment` | `sla`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                 |
| Setting bill | as Paid                              | `setpaid`           | `sp`     | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                    |
|              | as Unpaid                            | `setunpaid`         | `sup`    | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                    |
| Remarking    | a patient                            | `remark`            | `r`      | **`INDEX` Index of target patient <br/> *`r/` New remark of patient in any characters                                                                                                                                                                                                                                                                                                                             |
| Undoing      | the last change                      | `undo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Redoing      | the last undone change               | `redo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Clearing     | all the data saved in the software   | `clear`             |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Listing      | all patients, appointments and bills | `list`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Exiting      | the program                          | `exit`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Helping      | the user with user guide             | `help`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                   |

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
