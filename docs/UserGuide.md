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
2. 


### 2.2 Symbols

| Symbol               | Meaning                                                                       |
|----------------------|-------------------------------------------------------------------------------|
| `command`            | Indicates some command or parameter that can be entered into MyInsuRec's CLI. |
| :information_source: | Additional information that can be helpful.                                   |
| :exclamation:        | Crucial information that you should be aware of.                              |


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

*MyInsuRec* eases a financial advisor's mental load by helping them track of any upcoming meetings with clients. You can do meeting-specific tasks such as:

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

{ replace with a diagram of MyInsuRec and arrows and notes describing the different components of MyInsuRec }

--------------------------------------------------------------------------------------------------------------------

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

1. Download the latest **MyInsuRec.jar** from [here](https://github.com/AY2223S1-CS2103T-W16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MyInsuRec.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

Ta-da! *MyInsuRec* is now installed on your system.

--------------------------------------------------------------------------------------------------------------------

## 4. Quick Start

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

And there we have it! We have just gone through the basic bookkeeping features of *MyInsuRec*. Beyond that, we also have more advanced features such as getting a quick glance of all your upcoming meetings, filtering clients by products. To find out and learn more about these features, head to the [Features section](#5-features) where all the commands and their details can be found.

To get started with an clean state of *MyInsuRec*, type in the command `clear`. This removes all the sample data from *MyInsuRec*, so don't get panicked! 

Welcome to your new and organized life, and happy (financial) advising!

--------------------------------------------------------------------------------------------------------------------

## 5. Features

This section guides you on how to use features available in *MyInsuRec*. We lay out the command and parameters needed to use the feature, show an example input as well as its expected behaviour. We also include some tips and tricks (yay!) on how you can better use the feature and *MyInsuRec*.

Do take some time to read the following note to better understand how you can use this section!

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

This subsection covers all client-related commands.

#### 5.1.1 Add client: `addClient`

Add a new client to MyInsuRec.

Format: `addClient n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]`

* A client must have a `NAME` and a `PHONE_NUMBER`.
* `EMAIL`, `BIRTHDAY`, `ADDRESS` and `PRODUCT` are optional.

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
In order to use `pd/PRODUCT` as a parameter, you must have already added that product into MyInsuRec via `addProduct`. 
See [addProduct](#331-adding-a-product-addproduct).
</div>

Examples:
* `addClient n/John Tan p/12345678`
* `addClient n/John Tan p/12345678 b/12122000`
* `addClient n/John Tan p/12345678 e/johntan@insurec.com a/123 ABC ROAD, #11-01 pd/Product1`

#### 5.1.2 List clients: `listClient`

List clients in MyInsuRec with a valid filter.

Format: `listClient [pd/PRODUCT || b/BIRTHDAY]`

* A valid filter can be clients who have bought the product `PRODUCT` or clients whose birthday is in range `BIRTHDAY`
* `BIRTHDAY` is specified by keywords. The possible keywords are:
  * `tomorrow` for a list of clients whose birthday is tomorrow;
  * `week` for a list of clients whose birthday is in the next week;
  * `month` for a list of clients whose birthday is in the next month.

Examples:
* `listClient`
* `listClient pd/Product1`
* `listClient b/week`

<div markdown="block" class="alert alert-info">:exclamation: **Caution:** Both filters cannot exist simultaneously. 
A user can only apply one filter at each time. For example, `listClient pd/Product1 b/week` is strictly not allowed.
</div>

#### 5.1.3 View client: `viewClient`

View details associated with a client, such as the client's name and phone number.

Format: `viewClient i/INDEX`

* Display information about the client at the specific `INDEX`.
* The index refers to the index number shown in the displayed clients' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewClient i/1`

#### 5.1.4 Delete client: `delClient`

Delete the specified client from MyInsuRec.

Format: `delClient i/INDEX`

* Delete the client at the specified `INDEX`.
* `INDEX` refers to the index number shown by executing [`listClient`](#512-list-clients-listclient) command.
* `Index` **must be a positive integer** 1, 2, 3, …​

Examples:
* `delClient i/2`

#### 5.1.5 Edit client: `editClient`

Edit detail of the specified client.

Format: `editClient i/INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]`

* Edit the client at the specified `INDEX`.
* `INDEX` refers to the index number shown by executing [`listClient`](#512-list-clients-listclient) command.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* At least one optional detail must be modified.
* Maintain value of details not edited by the command.

Examples:
Suppose MyInsuRec contains only one client 'John Tan' having phone number '0123456789':
* `editClient i/1 n/John Smith` changes the name of this client to 'John Smith'.
* `editClient i/1 e/johntan@insurec.com` adds the email 'johntan@insurec.com' to the client.

### 5.2 Meeting commands

This subsection covers all meeting-related commands.

#### 5.2.1 Add meeting: `addMeeting`

Add a new meeting to MyInsuRec.

Format: `addMeeting i/INDEX d/DATE st/START_TIME et/END_TIME dn/DESCRIPTION`

* `INDEX` refers to the number of the client you are meeting with,
as shown by executing the [`listClient`](#512-list-clients-listclient) command.
* `DATE` should be given in the format DDMMYYYY. For example, 01022022 represents
1 February 2022.
* `START_TIME` and `END_TIME` should be give in the format HHMM. For example, 
1234 represents the 12:34PM.
* A meeting contains the `INDEX` of the client in the clients list, the `DATE` and `TIME` for the meeting, and the `DESCRIPTION` of the meeting.

Examples:
* `addMeeting i/1 d/28092022 st/1400 et/1500 dn/Alex's Policy Renewal` 

<div markdown="span" class="alert alert-success">:information_source: **Friendly Tip:**
MyInsuRec can help you detect conflicting meeting times! For example, attempting
to add a meeting from 1330 to 1430 when you already have one scheduled for 
1300 to 1400 will display an error message.
</div>

#### 5.2.2 List meetings: `listMeeting`

Shows a list of meetings in MyInsuRec.

Format: `listMeeting [d/DATE]`

Use case:
1. Get an overview of all your upcoming meetings. This is especially useful for if you have a busy and packed schedule, and want to ease your mental load of having to recall all of your upcoming meetings!
2. Organize all your meetings in a single place, so you don't have to worry about missing a meeting ever again.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
* You can use the `d/DATE` parameter optionally to view the list of meetings happening in that time period!
* `DATE` is specified by keywords. The possible keywords are:
  * `tomorrow` for a list of meetings happening tomorrow;
  * `week` for a list of meetings happening in the next week;
  * `month` for a list of meetings happening in the next month.
* For example, `listMeeting d/week` will show a list of meetings happening in the next week.
* This is an excellent feature if you want to get a quick overview of your upcoming schedule!
</div>

<div markdown="span" class="alert alert-success">:exclamation: **Tips and tricks:**
This command is used to get the index of a meeting. In order to perform commands related to a particular meeting such as `editMeeting`, you will have to first get its index by running `listMeeting`. So, expect to use this command a lot!
</div>

Examples:

* List all meetings
    * `listMeeting`

* List meetings happening in the next month
    * `listMeeting d/month`

#### 5.2.3 View meeting: `viewMeeting`

View details associated with a meeting, such as meeting's date and time.

Format: `viewMeeting i/INDEX`

* Display information about the meeting at the specific `INDEX`.
* The index refers to the index number shown by executing [`listMeeting`](#522-list-meetings-listmeeting) command.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `viewMeeting i/1`

#### 5.2.4 Delete meeting: `delMeeting`

Delete a meeting from MyInsuRec.

Format: `delMeeting i/INDEX`

* Delete the meeting at the specified `INDEX`.
* `INDEX` refers to the index number shown by executing [`listMeeting`](#522-list-meetings-listmeeting) command.
* `INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
* `delMeeting i/2`

#### 5.2.5 Edit meeting: `editMeeting`

Edit details of the specified meeting.

Format: `editMeeting i/INDEX [d/DATE] [st/START TIME] [et/END TIME] [dn/DESCRIPTION]`

* Edit information about the meeting at the specific `INDEX`.
* The `INDEX` refers to the index number shown in the displayed meetings' list.
* The index **must be a positive integer** 1, 2, 3, …
* At least one optional detail must be modified.
* Details that are not edited will be kept as is.

Examples:
Suppose MyInsuRec contains only one meeting as created in the [`addMeeting`](#521-add-meeting-addmeeting) command:

* `editMeeting i/1 dn/Follow up team meeting` changes the description of this meeting.
* `editMeeting i/1 st/1500 et/1200` will show an error stating an invalid time, 
since the start time is later than end time.

### 5.3 Product commands

This subsection covers all product-related commands.

#### 5.3.1 Add product: `addProduct`

Add a new product to MyInsuRec.

Format: `addProduct pd/PRODUCT`

* Add a product having name `PRODUCT`.
* A product must have a product name which is `PRODUCT`.

Examples:
* `addProduct pd/Product1`

#### 5.3.2 List products: `listProduct`

Show a list of all products in MyInsuRec.

Format: `listProduct`

Use case:
1. Get an overview of all the products you are offering!
2. This feature is used to get the index of a product, which is needed for most product-related commands.

Examples:

* List all products
  * `listProduct`

#### 5.3.3 Delete a product : `delProduct`

Deletes a product from *MyInsuRec*.  
This command removes this product from all the clients as well.

Format: `delProduct i/INDEX`

Use case:
1. If you no longer offer this product and no clients have purchased it before, you can remove it from *MyInsuRec* and prevent it from cluttering up *MyInsuRec*!

<div markdown="span" class="alert alert-info">:information_source: **Note:**
* This command is usually preceded by `listProduct`. This is because the product's index number `INDEX` is required to use this command, and `listProduct` shows a list of all the products with their index numbers.
</div>

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
This feature should only be used if there is a need to delete a product, which is unlikely in most scenarios! Use with caution as this not only removes the product from MyInsuRec's product list, it also **removes the product from any association with your clients**.
</div>

Examples:

* Delete the product with index number 2
    * `delProduct i/2`

### 5.4 General commands

#### 5.4.1 View help: `help`

Show a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 5.4.2 Exit MyInsuRec: `exit`

Exit the program.

Format: `exit`

### 5.5 Others

#### 5.5.1 Save the data

MyInsuRec data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 5.5.2 Edit the data file

MyInsuRec data are saved as a JSON file **[JAR file location]/data/myinsurec.json**. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MyInsuRec will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app, then overwrite the **myinsurec.json** file created by the app with the version of the file from the previous computer. The **myinsurec.json** is located in **[JAR file location]/data/myinsurec.json**.

**Q**: I accidentally closed the app, will my data still be there?<br>
**A**: Yes, your data is saved automatically after every action.

**Q**: My computer does not recognise the file type jar. How do I open the app?<br>
**A**: Check if your computer already has Java 11 or above installed from [8.1 Checking your system's Java version](#81-checking-your-systems-java-version). If not, follow the instructions to install Java.

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

| Action                                               | Format                                                                                         | Examples                                                                                                                                         |
|------------------------------------------------------|------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Add client**](#511-add-client-addclient)          | `addClient n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]`              | • `addClient n/John Tan p/12345678` <br> • `addClient n/John Tan p/12345678 a/123 ABC ROAD, #11-01 e/johntan@insurec.com b/12122000 pd/Product1` |
| [**List clients**](#512-list-clients-listclient)     | <code>listClient [pd/PRODUCT &#124;&#124; b/BIRTHDAY]</code>                                   | • `listClient` <br> • `listClient pd/Product1` <br> • `listClient b/tomorrow` <br> • `listClient b/week` <br> • `listClient b/month`             |
| [**View client**](#513-view-client-viewclient)       | `viewClient i/INDEX`                                                                           | • `viewClient i/1`                                                                                                                               |
| [**Delete client**](#514-delete-client-delclient)    | `delClient i/INDEX`                                                                            | • `delClient i/1`                                                                                                                                |
| [**Edit client**](#515-edit-client-editclient)       | `editClient i/INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [b/BIRTHDAY] [pd/PRODUCT]` | • `editClient i/1 n/John Smith`                                                                                                                  |
| [**Add meeting**](#521-add-meeting-addmeeting)       | `addMeeting i/INDEX d/DATE t/TIME dn/DESCRIPTION`                                              | • `addMeeting i/1 d/28092022 t/1400 dn/Team meeting`                                                                                             |
| [**List meetings**](#522-list-meetings-listmeeting)  | `listMeeting [d/DATE]`                                                                         | • `listMeeting` <br> • `listMeeting d/tomorrow` <br> • `listMeeting d/week`  <br> • `listMeeting d/month`                                        |
| [**View meeting**](#523-view-meeting-viewmeeting)    | `viewMeeting i/INDEX`                                                                          | • `viewMeeting i/1`                                                                                                                              |
| [**Delete meeting**](#524-delete-meeting-delmeeting) | `delMeeting i/INDEX`                                                                           | • `delMeeting i/1`                                                                                                                               |
| [**Edit meeting**](#525-edit-meeting-editmeeting)    | `editMeeting i/INDEX [d/DATE] [st/START TIME] [et/END TIME] [dn/DESCRIPTION]`                  | • `i/1 dn/Follow up team meeting`                                                                                                                |
| [**Add product**](#531-add-product-addproduct)       | `addProduct pd/PRODUCT`                                                                        | • `addProduct pd/Product1`                                                                                                                       |
| [**List products**](#532-list-products-listproduct)  | `listProduct`                                                                                  | • `listProduct`                                                                                                                                  |
| [**Delete product**](#533-delete-product-delproduct) | `delProduct i/INDEX`                                                                           | • `delProduct i/1`                                                                                                                               |
| [**Help**](#541-view-help-help)                      | `help`                                                                                         | `help`                                                                                                                                           |
| [**Exit**](#542-exit-myinsurec-exit)                 | `exit`                                                                                         | `exit`                                                                                                                                           |

--------------------------------------------------------------------------------------------------------------------

## 8. Troubleshooting

This section covers technical issues you may run into while using *MyInsuRec*.

### 8.1 Checking your system's Java version

To check that your system has the correct Java version (Java 11 and above) to run *MyInsuRec*, you can follow the steps below:

1. Open your terminal.
   * Windows
     * Use **Win** + **S** to open search.
     * Type in 'Terminal' to search for it and click on it to launch.
   * macOS
     * Use **Cmd** + **Space** to open Spotlight search.
     * Type in 'Terminal' to search for it and click on it to launch.
   * Linux
     * Use **Ctrl** + **Alt** + **T** to open the Terminal.
2. In your terminal, type in **java --version** and click enter.
3. The following image shows an example what will show up in macOS, but you can expect a similar result in Windows.

![JavaVersionTroubleShoot](images/troubleshoot/JavaVersionTroubleShoot.png)

4. The number in the red highlight rectangle tells you the Java version installed. For example, the Java version installed on the example system is Java 11.0.16, which is sufficient to run *MyInsuRec* as it is greater than Java 11.

5. If you do not see a similar result in the terminal after Step 3, or have an earlier version of Java, head over to [Oracle's Java download page](https://www.oracle.com/java/technologies/downloads/) to install Java.

--------------------------------------------------------------------------------------------------------------------

## 9. Glossary

{ glossary placeholder }

--------------------------------------------------------------------------------------------------------------------

## 10. Contact Us

Our team is committed to delivering a high-quality product, so we'd love to hear from you if you have any feedback or enquiries! 

Please feel free to contact us at [myinsurec@gmail.com](myinsurec@gmail.com).
