# User Guide

## Features
* Adding a patient (add)
* Search for a patient (search)
* Edit patient details (edit)
* Find patients (findpatient/fp)
* Find appointments (findappointment/fa)
* Find bills (findbill/fb)
* Sort (sort)


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

### Editing a patient: `edit`

Edit a patient’s information, such as age, contact number, appointment date and doctor’s notes.

Format: `edit Patient Title: content`

* If there is no such patient or task to edit, it will show an error.
* Existing values will be updated to the input values.

Examples:
* `edit John name: Jack` John’s name has been changed to Jack!
* `edit John number: 12345678` John’s number has been changed to 12345678.
* `edit John date: 2019-12-25` John’s appointment date has been changed to Dec 25th, 2019.
* `edit John note: use medicine` Doctor’s notes for John has been changed to use medicine.

### Finding patients `findpatient` `fp`

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

# 5. Commands Reference Sheet


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
| Filtering    | a patient                            | `findpatient`       | `fp`     | `n/` Name of patient<br/>`p/` Phone number of patient <br/>`e/` Email address of patient <br/>`a/` Home address of patient <br/>`r/` Remark of patient <br/>+`t/` Tag(s) of patient                                                                                                                                                                                                                                     |
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
| Helping      | the user with user guide             | `help`              |          |                                                                                                                                                                                                                                                                                                                                                                                                                         |

Notes on symbols in parameters column:

`**` Must be directly after command word

`*`  Must have(Accept last one when have multiple)

`+`  Accept multiple
