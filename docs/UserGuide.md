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

### 1.1.1 Adding a patient

### 1.1.2 Adding an appointment of a patient

### 1.1.3 Adding a bill of an appointment

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

### 1.5.1 Sorting patients `sortpatient` 

Sorts patients by a single field

Format:
```sortpatient <prefix><input> ...```

* The command word is `sortpatient`.
* The prefixes are c/ for Criteria and o/ for Order.
* The criteria can be Name of patient (name), Phone number of patient (phone), Email address of patient (email), Address of patient (address).
* The order can be Ascending (asc) or Descending (desc).
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.

Examples:
* ```sortpatient c/name o/asc``` returns patients sorted by name in ascending order.

<img src="images/sortpatient1.png" width="800px" height ="400px">

* ```sortpatient c/phone o/desc``` returns patients sorted by phone number in descending order.

<img src="images/sortpatient2.png" width="800px" height ="400px">

### 1.5.2 Sorting appointments `sortappointment`

Sorts appointments by a single field

Format:
```sortappointment <prefix><input> ...```

* The command word is `sortappointment`.
* The prefixes are c/ for Criteria and o/ for Order.
* The criteria can be Name of patient (name), Medical Test of appointment (test), Slot of appointment (slot) and Doctor of appointment (doctor).
* The order can be Ascending (asc) or Descending (desc).
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.

Examples:
* ```sortappointment c/test o/asc``` returns appointments sorted by medical test in ascending order.

<img src="images/sortappointment1.png" width="800px" height ="400px">

* ```sortappointment c/doctor o/desc``` returns appointments sorted by doctor in descending order.

<img src="images/sortappointment2.png" width="800px" height ="400px">

### 1.5.3 Sorting bills `sortbill`

Sorts bills by a single field

Format:
```sortbill <prefix><input> ...```

* The command word is `sortbill`.
* The prefixes are c/ for Criteria and o/ for Order.
* The criteria can be Name of patient (name), Amount (amount), Date (date), Payment status (status).
* The order can be Ascending (asc) or Descending (desc).
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.
* If the criteria is Payment status, Ascending will show bills which are paid first and Descending will show bills which are unpaid first.
* If there are no prefixes keyed in, an error message will be shown with the correct command format.
* If the input after a prefix is empty/invalid, an error message with the constraint of the field will be shown.


Examples:
* ```sortbill c/amount o/asc``` returns bills sorted by amount in ascending order.

<img src="images/sortbill1.png" width="800px" height ="400px">

* ```sortbill c/status o/desc``` returns bills sorted by payment status in descending order.

<img src="images/sortbill2.png" width="800px" height ="400px">

## 1.6 Selecting

### 1.6.1 Selecting a patient

### 1.6.2 Selecting an appointment

## 1.7 Setting Bill Payment Status

### 1.7.1 Setting Bill As Paid

### 1.7.2 Setting Bill As Unpaid

## 1.8 Remarking

## 1.9 Undoing `undo`

Reverses the most recent command.

Format:
```undo```

* The command word is `undo`.
* The command can be used multiple times to undo multiple commands.
* If there are no commands to undo, an error message will be shown.

Examples:
* ```undo``` undoes the most recent command.

<img src="images/undo1.png" width="800px" height ="400px">

## 1.10 Redoing `redo`

Reverses the most recent undo command.

Format:
```redo```

* The command word is `redo`.
* The command can be used multiple times to redo multiple commands.
* If there are no commands to redo, an error message will be shown.

Examples:
* ```redo``` redoes the most recent undo command.

<img src="images/redo1.png" width="800px" height ="400px">


## 1.11 Clearing

## 1.12 Listing

## 1.13 Exiting

## 1.14 Helping



# 2. Commands Reference Sheet


| Feature      |                                      | Command Word        | Shortcut | Parameters                                                                                                                                                                                                                                                                                                                                                                                                              |
|--------------|--------------------------------------|---------------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Adding       | a patient                            | `addpatient`        | `ap`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`p/` Phone number of patient in at least than 3 digits<br/>*`e/` Email address of patient in `local-part@domain`<br/>*`a/` Home address of patient in non-empty characters<br/>`r/` Remark of patient in non-empty characters<br/>+`t/` Tag(s) of patient in one alphanumeric word                                                           |
|              | an appointment of a patient          | `addappointment`    | `aa`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`s/` Slot of appointment in `yyyy-MM-dd HH:mm`<br/>*`d/` Doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>*`t/` Medical test of patient in non-empty characters                                                                                                                                            |
|              | a bill to an appointment             | `addbill`           | `ab`     | **`INDEX` Index of appointment<br/>*`a/` Amount of bill in a positive number with at most two decimal places<br/>*`d/` Bill date of bill in `yyyy-MM-dd`                                                                                                                                                                                                                                                                |
| Editing      | a patient                            | `editpatient`       | `ep`     | **`INDEX` Index of target patient<br/>`n/` New name of patient in non-empty alphanumeric characters and spaces<br/>`p/` New phone number of patient in at least than 3 digits<br/>`e/` New email address of patient in `local-part@domain`<br/>`a/` New home address of patient in non-empty characters<br/>`r/` New remark of patient in non-empty characters<br/>+`t/` New tag(s) of patient in one alphanumeric word |
|              | an appointment of a patient          | `editappointment`   | `ea`     | **`INDEX` Index of target appointment<br/>`n/` Name of another patient in non-empty alphanumeric characters and spaces<br/>`s/` New slot of appointment in `yyyy-MM-dd HH:mm`<br/>`d/` New doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>`t/` New medical test of patient in non-empty characters                                                                                  |
|              | a bill to an appointment             | `editbill`          | `eb`     | **`INDEX` Index of target bill<br/>`a/` New amount of bill in a positive number with at most two decimal places <br/>`d/` New bill date of bill  in `yyyy-MM-dd`                                                                                                                                                                                                                                                        |
| Deleting     | a patient                            | `deletepatient`     | `dp`     | **`INDEX` Index of patient                                                                                                                                                                                                                                                                                                                                                                                              |
|              | an appointment of a patient          | `deleteappointment` | `da`     | **`INDEX` Index of appointment                                                                                                                                                                                                                                                                                                                                                                                          |
|              | a bill to an appointment             | `deletebill`        | `db`     | **`INDEX` Index of bill                                                                                                                                                                                                                                                                                                                                                                                                 |
| Finding      | a patient                            | `findpatient`       | `fp`     | `n/` Name of patient<br/>`p/` Phone number of patient <br/>`e/` Email address of patient <br/>`a/` Home address of patient <br/>`r/` Remark of patient <br/>+`t/` Tag(s) of patient                                                                                                                                                                                                                                     |
|              | an appointment of a patient          | `findappointment`   | `fa`     | `n/` Name of patient<br/>`s/` Slot of appointment<br/>`d/` Doctor name of the appointment<br/>`t/` Medical test of patient                                                                                                                                                                                                                                                                                              |
|              | a bill to an appointment             | `findbill`          | `fb`     | `n/` Name of patient<br/>`a/` Amount of bill<br/>`d/` Bill date of bill                                                                                                                                                                                                                                                                                                                                                 |
| Sorting      | a patient                            | `sortpatient`       | `sop`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
|              | an appointment of a patient          | `sortappointment`   | `soa`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
|              | a bill to an appointment             | `sortbill`          | `sob`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Selecting    | a patient                            | `selectpatient`     | `slp`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                       |
|              | an appointment                       | `selectappointment` | `sla`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                       |
| Setting bill | as Paid                              | `setpaid`           | `sp`     | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                          |
|              | as Unpaid                            | `setunpaid`         | `sup`    | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                          |
| Remarking    | a patient                            | `remark`            | `r`      | **`INDEX` Index of target patient <br/> *`r/` New remark of patient in non-empty characters                                                                                                                                                                                                                                                                                                                             |
| Undoing      | the last change                      | `undo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Redoing      | the last undone change               | `redo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Clearing     | all the data saved in the software   | `clear`             |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Listing      | all patients, appointments and bills | `list`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Exiting      | the program                          | `exit`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Helping      | the user with user guide             | `help`              |          |
| Sorting      | patient list                         | `sortpatient`       |          | `c/` Criteria (Name of patient (name), Phone number of patient (phone), Email address of patient (email), Address of patient (address)) <br/> `o/` Order (Ascending (asc), Descending (desc))                                                                                                                                                                                                                           |
|              | appointment list                     | `sortappointment`   |          | `c/` Criteria (Name of patient (name), Medical test (test), Time slot (slot), Doctor (doctor)) <br/> `o/` Order (Ascending (asc), Descending (desc))                                                                                                                                                                                                                                                                    |
|              | bill list                            | `sortbill`          |          | `c/` Criteria (Name of patient (name), Amount (amount), Date (date), Payment status (status)) <br/> `o/` Order (Ascending (asc), Descending (desc))                                                                                                                                                                                                                                                                     |


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
