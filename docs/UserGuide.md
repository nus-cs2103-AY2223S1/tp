---
layout: page
title: User Guide
---
# HealthContact User Guide

HealthContact is a software for the receptionist of a family clinic who arranges telemedicine services between doctors and patients.
It helps to keep track of patient data, patient appointments and patient bills for the family clinic.

* Table of Contents
  {:toc}

---
# Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest HealthContact.jar from here.

3. Copy the file to the folder you want to use as the home folder for your HealthContact application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

<img src="images/Ui.png" width="800px" height ="400px">

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

6. Refer to the [Features](#1.-Features) below for details of each command.

# 1. Features

## 1.1 Add

### 1.1.1 Adding a patient

### 1.1.2 Adding an appointment of a patient

### 1.1.3 Adding a bill of an appointment

## 1.2 Edit

### 1.2.1 Editing a patient

### 1.2.2 Editing an appointment of a patient

### 1.2.3 Editing a bill of an appointment

## 1.3 Delete

### 1.3.1 Deleting a patient

### 1.3.2 Deleting an appointment of a patient

### 1.3.3 Deleting a bill of an appointment

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

### 1.4.3 Finding a bill of an appointment

## 1.5 Sort

### 1.5.1 Sorting patients

### 1.5.2 Sorting appointments

### 1.5.3 Sorting bills

## 1.6 Select

### 1.6.1 Selecting a patient

### 1.6.2 Selecting an appointment

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

### 1.7.2 Setting Bill As Unpaid

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

## 1.12 List

## 1.13 Exit

## 1.14 Help


# 2. Commands Reference Sheet


| Feature  |                                      | Command Word        | Shortcut | Parameters                                                                                                                                                                                                                                                                                                                                                                                                              |
|----------|--------------------------------------|---------------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add      | a patient                            | `addpatient`        | `ap`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`p/` Phone number of patient in at least than 3 digits<br/>*`e/` Email address of patient in `local-part@domain`<br/>*`a/` Home address of patient in non-empty characters<br/>`r/` Remark of patient in non-empty characters<br/>+`t/` Tag(s) of patient in one alphanumeric word                                                           |
|          | an appointment of a patient          | `addappointment`    | `aa`     | *`n/` Name of patient in non-empty alphanumeric characters and spaces<br/>*`s/` Slot of appointment in `yyyy-MM-dd HH:mm`<br/>*`d/` Doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>*`t/` Medical test of patient in non-empty characters                                                                                                                                            |
|          | a bill to an appointment             | `addbill`           | `ab`     | **`INDEX` Index of appointment<br/>*`a/` Amount of bill in a positive number with at most two decimal places<br/>*`d/` Bill date of bill in `yyyy-MM-dd`                                                                                                                                                                                                                                                                |
| Edit     | a patient                            | `editpatient`       | `ep`     | **`INDEX` Index of target patient<br/>`n/` New name of patient in non-empty alphanumeric characters and spaces<br/>`p/` New phone number of patient in at least than 3 digits<br/>`e/` New email address of patient in `local-part@domain`<br/>`a/` New home address of patient in non-empty characters<br/>`r/` New remark of patient in non-empty characters<br/>+`t/` New tag(s) of patient in one alphanumeric word |
|          | an appointment of a patient          | `editappointment`   | `ea`     | **`INDEX` Index of target appointment<br/>`n/` Name of another patient in non-empty alphanumeric characters and spaces<br/>`s/` New slot of appointment in `yyyy-MM-dd HH:mm`<br/>`d/` New doctor name of the appointment in non-empty alphanumeric characters and spaces<br/>`t/` New medical test of patient in non-empty characters                                                                                  |
|          | a bill to an appointment             | `editbill`          | `eb`     | **`INDEX` Index of target bill<br/>`a/` New amount of bill in a positive number with at most two decimal places <br/>`d/` New bill date of bill  in `yyyy-MM-dd`                                                                                                                                                                                                                                                        |
| Delete   | a patient                            | `deletepatient`     | `dp`     | **`INDEX` Index of patient                                                                                                                                                                                                                                                                                                                                                                                              |
|          | an appointment of a patient          | `deleteappointment` | `da`     | **`INDEX` Index of appointment                                                                                                                                                                                                                                                                                                                                                                                          |
|          | a bill to an appointment             | `deletebill`        | `db`     | **`INDEX` Index of bill                                                                                                                                                                                                                                                                                                                                                                                                 |
| Find     | a patient                            | `findpatient`       | `fp`     | `n/` Name of patient<br/>`p/` Phone number of patient <br/>`e/` Email address of patient <br/>`a/` Home address of patient <br/>`r/` Remark of patient <br/>+`t/` Tag(s) of patient                                                                                                                                                                                                                                     |
|          | an appointment of a patient          | `findappointment`   | `fa`     | `n/` Name of patient<br/>`s/` Slot of appointment<br/>`d/` Doctor name of the appointment<br/>`t/` Medical test of patient                                                                                                                                                                                                                                                                                              |
|          | a bill to an appointment             | `findbill`          | `fb`     | `n/` Name of patient<br/>`a/` Amount of bill<br/>`d/` Bill date of bill                                                                                                                                                                                                                                                                                                                                                 |
| Sort     | a patient                            | `sortpatient`       | `sop`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
|          | an appointment of a patient          | `sortappointment`   | `soa`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
|          | a bill to an appointment             | `sortbill`          | `sob`    |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Select   | a patient                            | `selectpatient`     | `slp`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                       |
|          | an appointment                       | `selectappointment` | `sla`    | **`INDEX` Index of target patient                                                                                                                                                                                                                                                                                                                                                                                       |
| Set bill | as Paid                              | `setpaid`           | `sp`     | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                          |
|          | as Unpaid                            | `setunpaid`         | `sup`    | **`INDEX` Index of target bill                                                                                                                                                                                                                                                                                                                                                                                          |
| Remark   | a patient                            | `remark`            | `r`      | **`INDEX` Index of target patient <br/> *`r/` New remark of patient in non-empty characters                                                                                                                                                                                                                                                                                                                             |
| Undo     | the last change                      | `undo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Redo     | the last undone change               | `redo`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Clear    | all the data saved in the software   | `clear`             |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| List     | all patients, appointments and bills | `list`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Exit     | the program                          | `exit`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Help     | the user with user guide             | `help`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |

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
