# User Guide

## Features
* Adding a patient (add)
* Search for a patient (search)
* Edit patient details (edit)
* Filter patients (filter)
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

### Editing a person: `edit`

Edit a patient’s information, such as age, contact number, appointment date and doctor’s notes.

Format: `edit Person Title: content`

* If there is no such person or task to edit, it will show an error.
* Existing values will be updated to the input values.

Examples:
* `edit John name: Jack` John’s name has been changed to Jack!
* `edit John number: 12345678` John’s number has been changed to 12345678.
* `edit John date: 2019-12-25` John’s appointment date has been changed to Dec 25th, 2019.
* `edit John note: use medicine` Doctor’s notes for John has been changed to use medicine.

### Filtering a person: `filter` [comming soon]
Shows the patients that satisfy given condition

Format:
```
filter <Patient Information Attribute> <operator> <value>
```
where `operator` supports `>`, `<`, `>=`, `<=`, `==`

If there is no such information attributes, shows error message.

Examples:
```
> filter age > 25

Here are the patient that age > 25:
    1. Jack 
    2. Peter
    3. Larry
```
```
> filter appointment date > 2019-12-25

Here are the patient that date > 2019-12-25:
    1. Jack 
    2. Larry
```
```
> filter favourite food == Chicken Masala

Unable to filter patients by favourite food

```
