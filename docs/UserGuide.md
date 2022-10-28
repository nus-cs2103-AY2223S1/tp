---
layout: page
title: User Guide
---

* Table of Contents:
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

*MyInsuRec* is **the desktop app for financial advisors**. If you are a financial advisor looking for an app to better manage your clients, meetings and products details, then look no further. *MyInsuRec* can also boost your productivity with features to quickly look up client and meetings details. Beyond that, *MyInsuRec* also has features to help you improve your customer relations.

*MyInsuRec* is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, *MyInsuRec* can get your client, meeting and product management tasks done faster than traditional GUI apps.

*MyInsuRec* is available for the Windows, macOS and Linux operating systems.

--------------------------------------------------------------------------------------------------------------------

## 2. About

### 2.1 Using this User Guide

Welcome to *MyInsuRec User Guide*!

This guide explains how you can use *MyInsuRec* to organize and manage your clients, meetings and products. This guide has been structured for users to quickly find and understand what they need.

Click on the links below to quickly get to what you need.

{ fill with instructions linking to different sections of the user guide }

e.g.
1. xyz (link)


### 2.2 Symbols

| Symbol               | Meaning                                                                         |
|----------------------|---------------------------------------------------------------------------------|
| `command`            | Indicates some command or parameter that can be entered into *MyInsuRec*'s CLI. |
| :information_source: | Additional information that can be helpful.                                     |
| :exclamation:        | Crucial information that you should be aware of.                                |


### 2.3 Terminologies

The following explains some terminology and main features of *MyInsuRec*:

#### 2.3.1 Clients

At the heart of *MyInsuRec* is the ability to organize and keep track of clients. You can do client-specific tasks such as:

* add a client and their details into *MyInsuRec*.
* view a client and their details, such as any upcoming meetings and purchased products.
* update a client's details.
* delete a client from *MyInsuRec*.

Beyond individual clients, you can also:

* show a summarized list of all clients with their important details such as contact number, products bought.
* filter and display a summarized list of clients with upcoming birthdays to help boost your customer relations.
* filter and display a summarized list of clients who purchased a specific product.

#### 2.3.2 Meetings

*MyInsuRec* eases a financial advisor's mental load by helping them track any upcoming meetings. You can do meeting-specific tasks such as:

* add a meeting to a client in *MyInsuRec*.
* view a meeting in greater details.
* update a meeting's details.
* delete a meeting from a client in *MyInsuRec*.

Beyond individual meetings, you can also:

* show a summarized list of all meetings with their important details such as meeting date and time.
* quickly get a glance of tomorrow's meetings, and also in the coming week and month.

#### 2.3.3 Products

In order to help track a client's purchases, *MyInsuRec* also includes the ability for users to define products that a client might have purchased. You can do product-specific tasks such as:

* add a product to *MyInsuRec*.
* delete a product from *MyInsuRec*.

Beyond individual products, you can also:

* show a summarized list of all products.

### 2.4 User Interface

{ replace with a diagram of *MyInsuRec* and arrows and notes describing the different components of *MyInsuRec* }

--------------------------------------------------------------------------------------------------------------------

<<<<<<< HEAD
1. Ensure you have Java `11` or above installed on your computer.
=======
## 3. Installation Guide

### 3.1 System Requirements

Here is everything you need to install and set up *MyInsuRec*. For the best possible experience, we recommend that you use *MyInsuRec* on the following supported operating systems:

* Windows
* macOS
* Linux

You will also require Java 11 or above to run *MyInsuRec*. If you don't already have Java 11 or above on your system, head over to [Oracle's Java download page](https://www.oracle.com/java/technologies/downloads/). To tell if you already have the correct version of Java installed on your system, refer to [8.1 Checking your system's Java version](#81-checking-your-systems-java-version).

### 3.2 Installation Instructions

To install *MyInsuRec*, simply follow the steps below:

1. Ensure that your system meets the [system requirements](#31-system-requirements).
>>>>>>> 7d672637eb1a8fa1fb12674eb0297799592356dd

1. Download the latest `MyInsuRec.jar` from [here](https://github.com/AY2223S1-CS2103T-W16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your *MyInsuRec*.

1. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds.
   ![Ui](images/Ui.png)

Ta-da! *MyInsuRec* is now installed on your system.

--------------------------------------------------------------------------------------------------------------------

## 4. Quick start

### 4.1 Basic features

Are you new to *MyInsuRec*? This section is the place to start! 

Here, we will be guiding you on the basic features of *MyInsuRec* and help you get familiarized with it.

1. When we first launch *MyInsuRec*, *MyInsuRec* will have already been preloaded with some sample data. We will be using this sample data to get familiar with *MyInsuRec*!

2. Type in `addClient n/Tommy Tan p/81234567` to add a client named 'Tommy Tan' and has a contact number '81234567'. We will see the list of clients update to include this newly added client, with his phone number! The image below shows *MyInsuRec* after adding Tommy.

![AddClientTommy](images/quick-start/AddClientTommy.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Both client name and their phone number are **needed** to add the client into *MyInsuRec*.
</div>

3. Tommy has also given us his email address, his address as well as his birthday, as such we would like to update Tommy's record. To do that, we first need to get Tommy's index from the list of clients, which is 7. Type in `editClient i/7 e/tommytan21@gmail.com a/167 Canning Road bd/25081982` and hit enter. Tommy's record in the list of clients will update with the newly inputted details. The image below shows *MyInsuRec* after updating Tommy with the additional details.

![EditClientTommy](images/quick-start/EditClientTommy.png)

4. We can also directly add these optional fields (email, address, etc.) when we are adding the client for the first time! So, `addClient n/Tammy Lim p/90127654 e/tammylim@hotmail.com b/09091973` also adds in the client's email and birthday directly.

<div markdown="span" class="alert alert-success">:exclamation: **Tips and tricks:**
See [addClient](#311-adding-a-client-addclient) for more variety of optional fields!
</div>

5. Now we would like to add in an upcoming meeting with our client 'Bernice Yu' (index 2 in our sample data). We can do that by doing `addMeeting i/2 d/27102022 st/1400 et/1600 dn/Review Product 2` (You can replace `d/27102022` with today's date in the DDMMYYYY format)! When we hit enter, *MyInsuRec* will show us the list of meetings, including the one we just added. The image below shows the list of meetings after adding the meeting with Bernice.

![AddMeetingBernice](images/quick-start/AddMeetingBernice.png)

6. We would like to get Bernice's contact number and message about her upcoming meeting! We can call `viewMeeting i/1` to help us. This brings up a more detailed view of the meeting, with information such as description of the meeting and Bernice's contact number. The image below shows *MyInsuRec* after using the `viewMeeting` command.

![ViewMeetingBernice](images/quick-start/ViewMeetingBernice.png)

7. Now, to go back to the list of clients, simply type in `listClient`. This brings us back to the list of clients. The image below shows the current state of *MyInsuRec* after following all the previous steps.

![ListClient](images/quick-start/ListClient.png)

8. Suppose your company introduced a new product 'MyInsureCare' that you are interested to sell, and would like to add it into *MyInsuRec*! We can do so via `addProduct pd/MyInsureCare`. This adds a product 'MyInsureCare' and also brings us to the list of product with the newly added product. The image below shows *MyInsuRec* after adding the product.

<div markdown="span" class="alert alert-success">:exclamation: **Tips and tricks:**
You can use `listClient`, `listMeeting` and `listProduct` to traverse between the different lists accordingly.
</div>

![AddProductMyInsureCare](images/quick-start/AddProductMyInsureCare.png)

9. Now, we want to add a client named who has already purchased 'MyInsureCare'. To do that, we can once again type in `addClient n/Ng Jun Yi p/81230987 pd/MyInsureCare` to add a client and indicate that he has bought 'MyInsureCare'. The image below shows *MyInsuRec* after adding the client with the product.

![AddClientJunYiWithProduct](images/quick-start/AddClientJunYiWithProduct.png)

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
Only products added already via `addProduct` can be used! This is to help ensure the cleanliness of *MyInsuRec*. So, add your product via `addProduct` before using it to add a client with that product! See [addProduct](#331-adding-a-product-addproduct).
</div>

10. Unfortunately 'Alex Yeoh' is no longer our client, and so we want delete him from our records. To do that, type in `delClient i/1`. The image below shows *MyInsuRec* after deleting the client.

![DelClientAlex](images/quick-start/DelClientAlex.png)

And there we have it! We have just gone through the basic bookkeeping features of *MyInsuRec*. Beyond that, we also have more advanced features such as getting a quick glance of all your upcoming meetings, filtering clients by products. To find out and learn more about these features, head to the [Features section](#3-features) where all the commands and their details can be found.

To get started with an clean state of *MyInsuRec*, type in the command `clear`. This removes all the sample data from *MyInsuRec*, so don't get panicked! 

Welcome to your new and organized life, and happy (financial) advising!

--------------------------------------------------------------------------------------------------------------------

## 5. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `addClient n/NAME p/PHONE_NUMBER`, `NAME` and `PHONE_NUMBER` are parameters which can be used as `addClient n/John Tan p/12345678`.

* Items in square brackets are optional, while those not in square brackets are compulsory.<br>
  e.g. `addClient n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]` can be used as <br>
  * `addClient n/John Tan p/12345678`
  * `addClient n/John p/12345678 e/John@abc.com b/12122000`

* This symbol `||` indicates that only one of the optional parameters can be used. <br> 
  **Using more than one optional parameter is strictly not allowed.** <br>
  e.g. `listClient [pd/PRODUCT || b/BIRTHDAY]` can be used as 
  * `listClient pd/Product1`
  * `listClient b/week`

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/28092022 d/30092022`, only `d/30092022` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `help`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### 5.1 Client commands

#### 5.1.1 Adding a client: `addClient`

Adds a new client to **MyInsuRec.

Format: `addClient n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]`

* A client must have a `NAME` and a `PHONE_NUMBER`.
* `EMAIL`, `BIRTHDAY`, `ADDRESS` and `PRODUCT` are optional.

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
In order to use `pd/PRODUCT` as a parameter, you must have already added that product into *MyInsuRec* via `addProduct`. See [addProduct](#331-adding-a-product-addproduct).
</div>

Examples:
* `addClient n/John Tan p/12345678`
* `addClient n/John Tan p/12345678 b/12122000`
* `addClient n/John Tan p/12345678 e/johntan@insurec.com a/123 ABC ROAD, #11-01 pd/Product1`

#### 5.1.2 Listing all clients : `listClient`

* List clients in *MyInsuRec* with a valid filter.
* A valid filter can be clients who have bought the product `PRODUCT` or clients whose birthday is in range `BIRTHDAY`
* `BIRTHDAY` is specified by keywords. The possible keywords are:
    * `tomorrow` for a list of clients whose birthday is tomorrow;
    * `week` for a list of clients whose birthday is in the next week;
    * `month` for a list of clients whose birthday is in the next month.

Format: `listClient [pd/PRODUCT || b/BIRTHDAY]`

Examples:
* `listClient`
* `listClient pd/Product1`
* `listClient b/week`

<div markdown="block" class="alert alert-info">:exclamation: **Caution:** Both filters cannot exist simultaneously. A user can only apply one filter at each time. For example, `listClient pd/Product1 bd/week` is strictly not allowed.
</div>

#### 5.1.3 Viewing a client: `viewClient`

View details associated with a client, such as the client's name and phone number.

Format: `viewClient i/INDEX`

* Displays information about the client at the specific `INDEX`.
* The index refers to the index number shown in the displayed clients' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewClient i/1`

#### 5.1.4 Deleting a client : `delClient`

Deletes the specified client from *MyInsuRec*.

Format: `delClient i/INDEX`

* Deletes the client at the specified `INDEX`.
* `INDEX` refers to the index number shown by executing [`listClient`](#312-listing-all-clients--listclient) command.
* `Index` **must be a positive integer** 1, 2, 3, …​

Examples:
* `delClient i/2`

#### 5.1.5 Editing a client: `editClient`

Edits detail of the specified client.

Format: `editClient i/INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]`

* Edits the client at the specified `INDEX`.
*`INDEX` refers to the index number shown by executing [`listClient`](#312-listing-all-clients--listclient) command.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* At least one optional detail must be modified.
* Maintains value of details not edited by the command.

Examples:
Suppose *MyInsuRec* contains only one client 'John Tan' having phone number '0123456789':
* `editClient i/1 n/John Smith` changes the name of this client to 'John Smith'.
* `editClient i/1 e/johntan@insurec.com`adds the email 'johntan@insurec.com' to the client.

### 5.2 Meeting commands

#### 5.2.1 Adding a meeting : `addMeeting`

Adds a new meeting to *MyInsuRec*.
DATE should be in DDMMYYYY format and TIME should be in 24-hour format.

Format: `addMeeting i/INDEX d/DATE t/TIME dn/DESCRIPTION`

* Adds a meeting.
* A meeting contains the `INDEX` of the client in the clients list, the `DATE` and `TIME` for the meeting, and the `DESCRIPTION` of the meeting.

Examples:
* `addMeeting i/1 d/28092022 t/1400 dn/Team meeting`

#### 5.2.2 List meetings: `listMeeting`

Shows a list of meetings in *MyInsuRec*.
If used with optional parameter `[d/DATE]`, *MyInsuRec* will show a list of meetings happening in that time period.

Format: `listMeeting [d/DATE]`

* Time periods are specified by keywords. The possible keywords are:
  * `tomorrow` for a list of meetings happening tomorrow;
  * `week` for a list of meetings happening in the next week;
  * `month` for a list of meetings happening in the next month.

Examples:
* `listMeeting d/tomorrow`
* `listMeeting d/month`

#### 5.2.3 Viewing a meeting: `viewMeeting`

View details associated with a meeting, such as meeting's date and time.

Format: `viewMeeting i/INDEX`

* Displays information about the meeting at the specific `INDEX`.
* The index refers to the index number shown in the displayed meetings' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewMeeting i/1`

#### 5.2.4 Deleting a meeting : `delMeeting`

Deletes a meeting from *MyInsuRec*.

Format: `delMeeting i/INDEX`

* Deletes the meeting at the specified `INDEX`.
* `INDEX` refers to the index number shown by executing [`listMeeting`](#322-listing-meetings-listmeeting) command.
* `INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `delMeeting i/2`

#### 5.2.5 Editing a meeting: `editMeeting`

Edits details of the specified meeting.

Format: `editMeeting i/INDEX [d/DATE] [st/START TIME] [et/END TIME] [dn/DESCRIPTION]`

* Edits information about the meeting at the specific `INDEX`.
* The `INDEX` refers to the index number shown in the displayed meetings' list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one optional detail must be modified.
* Maintains values of details not edited by the command.

Examples:
Suppose *MyInsuRec* contains only one meeting as created in the [`addMeeting`](#adding-a-meeting--addmeeting) command:
* `editMeeting i/1 dn/Follow up team meeting` changes the description of this meeting.
* `editMeeting i/1 st/1500 et/1200` will show error saying invalid time since start time is later than end time.

### 5.3 Product commands

#### 5.3.1 Adding a product: `addProduct`

Adds a new product to *MyInsuRec*.

Format: `addProduct pd/PRODUCT`

* Adds a product having name `PRODUCT`.
* A product must have a product name which is `PRODUCT`.

Examples:
* `addProduct pd/Product1`

#### 5.3.2 Listing products: `listProduct`

Shows a list of all products in *MyInsuRec*.

Format: `listProduct`

#### 5.3.3 Deleting a product : `delProduct`

Deletes a product from *MyInsuRec*.  
This feature will remove the product from association with any client.

Format: `delProduct i/INDEX`

* Deletes the product at the specified `INDEX`.
* The index refers to the index number shown in the displayed product list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
This feature should only be used if there is a need to delete a product, which is unlikely in most scenarios! Use with caution as this not only removes the product from *MyInsuRec*'s product list, it also **removes the product from any association with your clients**.
</div>

Examples:
* `delProduct i/2`

### 5.4 General commands

#### 5.4.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 5.4.2 Exiting MyInsuRec : `exit`

Exits the program.

Format: `exit`

<<<<<<< HEAD
#### Clear data:

Format: `clear`

This is a one-shot way of removing all data stored in *MyInsuRec*.
Only clear when you are sure you do not need the data anymore, or else make a [backup](#backup-data).

Example usage: 
* Clearing default data shipped with *MyInsuRec*
* Restarting the app from an empty version

### 3.5 Others
=======
### 5.5 Others
>>>>>>> 7d672637eb1a8fa1fb12674eb0297799592356dd

#### 5.5.1 Saving the data

*MyInsuRec* data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 5.5.2 Editing the data file

*MyInsuRec* data are saved as a JSON file `[JAR file location]/data/myinsurec.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, *MyInsuRec* will discard all data and start with an empty data file at the next run.
</div>

<<<<<<< HEAD
#### 3.5.3 Creating and using data file backups
=======
#### 5.5.3 Archiving data files `[coming in v2.0]`
>>>>>>> 7d672637eb1a8fa1fb12674eb0297799592356dd

This is the recommended method of storing backups of the `[JAR file location]/data/myinsurec.json` file.
First, create a copy of the original file in the `[JAR file location]/data` folder and rename it to `myinsurec_date.json` where date identifies when the file was created.

If you want to start *MyInsuRec* using a backup file, rename the backup to `myinsurec.json`.
Then launch the app as usual.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Remember to save the original `myinsurec.json` file before renaming the backup!
</div>

--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app, then overwrite the `myinsurec.json` file created by the app with the version of the file from the previous computer.

**Q**: I accidentally closed the app, will my data still be there?<br>
**A**: Yes, your data is saved automatically after every action.

**Q**: My computer does not recognise the file type jar. How do I open the app?<br>
**A**: Install Java version 11 and above from the official Java website.

**Q**: Why can't I add a product to my client?<br>
**A**: First check that the product has been created (use the [`listProduct` command](#332-listing-products-listproduct)). 
Create the product suing the [`addProduct`](#331-adding-a-product-addproduct) command if it is not there.
Add the product to the client using the [`editClient`](#315-editing-a-client-editclient)  or [`addClient`](#311-adding-a-client-addclient) command.

**Q**: Where is my data file located?<br>
**A**: The data file is located at `[JAR file location]/data/myinsurec.json`. 

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

| Action                | Format, Examples                                                                                                                                                                                                                                   |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add client**        | `addClient n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]` <br> e.g., <br> • `addClient n/John Tan p/12345678` <br> • `addClient n/John Tan p/12345678 a/123 ABC ROAD, #11-01 e/johntan@insurec.com b/12122000 pd/Product1` |
| **List all clients**  | `listClient`                                                                                                                                                                                                                                       |
| **View client**       | `viewClient i/INDEX` <br> e.g., <br> • `viewClient i/1`                                                                                                                                                                                            |
| **Delete client**     | `delClient i/INDEX` <br> e.g., <br> • `delClient i/2`                                                                                                                                                                                              |
| **Edit client**       | `editClient i/INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]` <br> e.g., <br> • `editClient i/1 n/John Smith`                                                                                                     |
| **Add meeting**       | `addMeeting i/INDEX d/DATE t/TIME dn/DESCRIPTION` <br> e.g., <br> • `addMeeting i/1 d/28092022 t/1400 dn/Team meeting`                                                                                                                             |
| **List all meetings** | `listMeeting`                                                                                                                                                                                                                                      |
| **View meeting**      | `viewMeeting i/INDEX` <br> e.g., <br> • `viewMeeting i/1`                                                                                                                                                                                          |
| **Delete meeting**    | `delMeeting i/INDEX` <br> e.g., <br> • `delMeeting i/2`                                                                                                                                                                                            |
| **Edit meeting**      | `editMeeting i/INDEX [d/DATE] [st/START TIME] [et/END TIME] [dn/DESCRIPTION]` <br> e.g., <br> • `i/1 dn/Follow up team meeting`                                                                                                                    |
| **Help**              | `help`                                                                                                                                                                                                                                             |
| **Exit**              | `exit`                                                                                                                                                                                                                                             |                                                                                                                                               |

--------------------------------------------------------------------------------------------------------------------

<<<<<<< HEAD
## Troubleshooting

### I cannot see any data

Here are a few reasons why this may have occurred:

1. The data file is empty:
   * Locate the [date file](#4-faq).
   * If the file is empty ,i.e. contains no data, then *MyInsuRec* did not load any data on start up.
   * How to recover? :
   * [Swap the file with your backup](#353-creating-and-using-data-file-backups). 
2. The data file was corrupt before starting the app:
   * This issue is harder to detect since the app will only empty the file if the data in it was corrupt before starting. 
   * How to avoid this issue? :
   * [Create backups](#353-creating-and-using-data-file-backups) of your `myinsurec.json` file and ensure that they are not corrupt. 
   * See the [section below](#my-data-file-is-corrupt) on how to detect and recover from file corruption.

### My data file is corrupt

Do not launch the app if you detect this issue, since the `myinsurec.json` file will be cleared of all data.
To recover: 

1. [create a backup](#353-creating-and-using-data-file-backups) of the file.
2. Edit all invalid fields and format issues manually in the backup file. 
   * What should I look out for while editing? :
   * Check that the structure of the file is correct (no missing semicolons, commas, brackets, and data fields). <br> It helps to be familiar with the [JSON file](#json-file) format here, but most users can quickly pick up the basics by referring to a working copy of the data file.
   * Some invalid data values to look out for :
   * invalid dates for client birthday, or meeting date e.g. 00000000 (invalid DDMMYYYY), 1212202020 (too many digits),
   * Invalid meeting start time or end time e.g. 2500 (invalid HHMM).
3. [Use this edited file](#353-creating-and-using-data-file-backups) when launching *MyInsuRec*.
   
## 5. Acknowledgements

This project is based on the [AB3 project template](https://github.com/se-edu/addressbook-level3) by [se-education.org](https://se-education.org/).

We thank the CS2103T and CS2101 teaching team and all our classmates for supporting us in this project!

## 6. Glossary
=======
## 8. Troubleshooting

This section covers technical issues you may run into while using *MyInsuRec*.

### 8.1 Checking your system's Java version

To check that your system has the correct Java version (Java 11 and above) to run *MyInsuRec*, you can follow the steps below:

1. Open your terminal.
   * Windows
     * Use `Win` + `S` to open search.
     * Type in 'Terminal' to search for it and click on it to launch.
   * macOS
     * Use `Cmd` + `Space` to open Spotlight search.
     * Type in 'Terminal' to search for it and click on it to launch.
   * Linux
     * Use `Ctrl` + `Alt` + `T` to open the Terminal.
2. In your terminal, type in `java --version` and click enter.
3. The following image shows an example what will show up in macOS, but you can expect a similar result in Windows.

![JavaVersionTroubleShoot](images/troubleshoot/JavaVersionTroubleShoot.png)

4. The number in the red highlight rectangle tells you the Java version installed. For example, the Java version installed on the example system is Java 11.0.16, which is sufficient to run *MyInsuRec* as it is greater than Java 11.

5. If you do not see a similar result in the terminal after Step 3, or have an earlier version of Java, head over to [Oracle's Java download page](https://www.oracle.com/java/technologies/downloads/) to install Java.

--------------------------------------------------------------------------------------------------------------------

## 9. Glossary
>>>>>>> 7d672637eb1a8fa1fb12674eb0297799592356dd

### Quick Reference

- [CLI](#cli)
- [GUI](#gui)
- [INDEX](#)
- [JSON file](#json-file)
- [JAR file](#jar-file)
- [Parameter](#parameter)

### *C*

##### CLI 

Command Line Interface, user interface that accepts input as lines of text.

### *G*

##### GUI

Graphical User Interface, user interface that accepts input in means other than text, such as mouse clicks.

### *I*

##### INDEX

Number indicating the position of a client, meeting, or product in their respective lists, e.g.  Product6 has position of 5 in the shown [product list](#332-listing-products-listproduct):
![](images/listProduct.png)

### *J*

##### JSON file

A file that stores data that has been structured according to the JSON data format.

##### JAR file

A file having the extension .jar which is usually used to share applications written in Java.
*MyInsuRec* can be downloaded and executed as a JAR file.

### *P*

##### Parameter

Value that should be provided to a command for it to execute.
e.g. the `delClient` command requires the `INDEX` value identifying a client in order to delete that client.

