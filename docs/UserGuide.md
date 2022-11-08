---
layout: page
title: User Guide
---

<p align="center">
    <img src="images/LTNS_logo.png" width="200" height="200">
</p>

Hello there! Long Time No See! Having stumbled upon this user guide, you are likely a financial advisor looking for easier life, well, at financial advising! Look no further, as the LongTimeNoSee (LTNS) app is just the right tool you need!

With LTNS, gone are the days of frantically searching through stacks and stacks of paper, just for that one client information! All you have to do, is simply type a few words into our revolutionary **[Command Line Interface](#command-line-interface-cli) (CLI)** and voilà! The information you need appears right in front of you, it's that easy!

Furthermore, LTNS is not just any other tool, it is a smart tool. Having surveyed countless financial advisors, we sympathize with your needs of something, not just to manage clients, but policies and even events! Therefore, LTNS strategically revolves around three stunning features! You've probably guessed it, they are **client, policy and event management!** Exactly the tools you need to be the best financial advisor out there. 

So, what are you waiting for? Start revolutionizing your financial advising journey [here](#tutorial)!

P.S. This document is the official user guide of the Long Time No See (LTNS) app. This guide serves as an introduction to LTNS and a help menu should you have any doubts while using the app.

# Table of Contents
1. [Quickstart](#quick-start)
2. [Tutorial](#tutorial)
3. [Features](#features)<br>
    * [Common Features](#common-features)<br>
        * [Viewing Help](#viewing-help--help)
        * [Clearing Entries](#clearing-all-entries--clear)
        * [Exiting the Program](#exiting-the-program--exit)
        * [Saving Data](#saving-the-data)
        * [Manually Editing Data File](#editing-the-data-file)
    * [Client Features](#client-features)<br>
        * [Adding a Client](#adding-a-client--addclient)
        * [Switching to Client View](#switching-to-the-view-of-currently-listed-clients--clients)
        * [Listing all Clients](#listing-all-clients--allclients)
        * [Editing a Client](#editing-a-client--editclient)
        * [Searching for a Client](#searching-for-clients--findclient)
        * [Deleting a Client](#deleting-a-client--deleteclient)
        * [Sorting all Clients](#sorting-all-clients--sort)
        * [Pin a Client](#pin-a-client-pin)
    * [Policy Features](#policy-features)<br>
        * [Adding a Policy](#adding-a-policy--addpolicy)
        * [Switching to Policy View](#switching-to-the-view-of-currently-listed-policies--policies)
        * [Listing all Policies](#viewing-all-policies-allpolicies)
        * [Deleting a Policy](#deleting-a-policy--deletepolicy)
        * [Searching for a Policy](#searching-for-a-policy--findpolicy)
        * [Assigning a Policy to a Client](#assigning-a-policy-to-a-client--assign)
        * [Listing all Assigned Policies](#listing-out-policies-assigned-to-a-client--listassigned)
        * [Deleting Assigned Policies from a Client](#deleting-assigned-policies-from-a-client--deleteassigned)
        * [Tracking your Income](#tracking-your-income---viewincome)
    * [Event Features](#event-features)
        * [Adding an Event](#adding-an-event-addevent)
        * [Switching to Event View](#switching-to-the-view-of-currently-listed-events--events)
        * [Listing all Events](#viewing-all-events--allevents)
        * [Searching for an Event](#searching-for-an-event--findevent)
        * [Viewing all Events in the next 7 days](#viewing-all-events-in-the-next-7-days--calendar)
4. [Frequently Asked Questions](#faq)
5. [Command Summary](#command-summary)
6. [Glossary](#glossary)


--------------------------------------------------------------------------------------------------------------------

## Quick start
1. Ensure you have Java `11` or above installed on your Computer. (Installation Guides: [Mac User](https://www.geeksforgeeks.org/how-to-install-java-on-macos/), [Windows User](https://phoenixnap.com/kb/install-java-windows))

2. Download the latest `longtimenosee.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-2/tp/releases).

3. Move the file to a folder of your choice, in particular where you want the application to be stored on your computer.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
![Ui.png](./images/Ui.png)

5. Not sure where to start? The following section is a detailed Tutorial on how to use the Application. Feel free to [skip](#features) this section if you are confident in using the application.

--------------------------------------------------------------------------------------------------------------------

## Tutorial 

Welcome to the LongTimeNoSee Tutorial! Here we will guide you through your journey of learning how to use your App!

LongTimeNoSee works by giving your App commands through the keyboard. Let us first start by learning what's what. 

Before we begin, be sure to follow the [Quick Start](#quick-start) section above to install and run the Application.

The diagram below shows an example of how the Application will look like: 
![UiTutorial](./images/UiTutorial.png)
A description of the different parts of the Application is given below: 
1. **Client Index Number**: This represents the index number of a client, in commands that require `INDEX_NUMBER`, this will be the number to use 
2. **Client Name**: This is your client's name
3. **Application Status Box**: After entering commands, the Application will reply with status messages in this box eg. whether the command was successful or not
4. **Client Information**: Information related to your client will be displayed here, under the Client's _Index Number_ and _Name_
5. **Command Box**: Type your commands here

Now that you are familiar with your Application's layout, it's time to learn how to give it commands.

#### Learning to Use Commands 
As shown in the diagram above, the command box, also known as the [Command Line Interface (CLI)](#command-line-interface-cli) is where you will type your commands.

But what commands can you type? We will now learn how to understand the [Features](#features) section below.

Each Feature section begins with an **introduction**, followed by the **command format**. 

Each Command begins with a **Command Word**, followed by accompanying **tags**, if applicable.

For example, when [Adding A Client](#adding-a-client--addclient), this is what you will see:
Format: `addClient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ b/BIRTHDAY i/INCOME ra/RISK_APPETITE`

Let us understand how to read this. Firstly, `addClient` is the **Command Word**. This is followed by some tags, such as the `n/` tag which represents a **Name**.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A tag wrapped in square brackets, such as `[t/TAG]` are optional tags! Tags with ellipses behind such as `[t/TAG]...` indicate that there can be multiple tags
</div>

An example of how we can use the `addClient` command is shown below: 
* **`addClient`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-301 b/2000-01-01 i/90000.0 ra/M` 

<div markdown="span" class="alert alert-primary">:white_check_mark: **Give it a try!:**
Try typing the command above into the command box and hit `enter`. Now observe the changes! John Doe with his corresponding information should now be in your Application.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Notice that `[t/TAG]` are not required as they are optional. Also notice how we type the tag first before the client information eg. `b/2000-01-01` indicates a birthday on 1st January 2000
</div>

#### Last Word of Advice 
Congratulations! You are now ready to begin your journey of using LongTimeNoSee! 

When you are ready, go to the [Feature](#features) section to find out about the commands you can use. 

For ease of reading, each feature follows the following format:
1. Description
2. Command Format
3. Additional Pointers and Tips
4. Example Usages

You may also refer to the [Command Format Table](#command-format-table) to find detailed descriptions of each **command tags**. 

Additionally, refer to the [Command Summary Page](#command-summary) for a brief overview of every command.

Like all things, learning can take some time, but you'll soon realise that each Command is very intuitive! If you still find yourself confused, fret not! Feel free to [contact us](#contact-us). We will be more than happy to assist you.


--------------------------------------------------------------------------------------------------------------------

# Features Overview

**Long Time No See (LTNS)** allows you to track your clients, policies and events.
As such, the features available to you are split into **4** main features:
1. [Common Features](#common-features): Here you can find basic Application features
2. [Client Features](#client-features): Here you can find all features related to keeping track of all your clients 
3. [Policy Features](#policy-features): Here you can find all features related to keeping track of all your policies 
4. [Event Features](#event-features): Here you can find all features related to keeping track of all your important appointments

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the [parameters](#parameter) by the user.<br>
  e.g. in `addClient n/NAME`, `NAME` is a parameter which can be used as `addClient n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or `n/John Doe`.

* Items with `…` after them can be used multiple times.<br>
  e.g. `[t/TAG]…​` can be used as `​` (i.e. 0 times).<br>
  and e.g. `c/cov…​` can be used 1 or more times.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected once in the command, but has been specified multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`. 

* Click [here](#command-format-table) for a list of all possible parameters and their constraints

</div>

#### _______________________________________________________________________________________________________________________________________________________________________________

## Common Features

#### __________________________

### Viewing help : `help`

Help! I have no idea what's going on? When in doubt, just type `help` and help is on the way! Upon entering `help`, LTNS shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

Example Usage: `help`

Example Result: Help message pop up appears

#### __________________________

### Clearing all entries : `clear`

Are you sure you want to do this? This clears all data from your application. 

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This action is irreversible! 
</div>


Format: `clear`

Example Usage: `clear`

Example Result: Clears all Application data.

#### __________________________

### Exiting the program : `exit`

Take a break! Financial advisors are people too! This exits the program.

Format: `exit`

Example Usage: `exit`

Example Result: Exits the app

#### __________________________

### Saving the data

Don't worry! LTNS application data is saved in the hard disk automatically after any command that changes the data. You don't have to do anything!

#### __________________________

### Editing the data file (For the pros)

Psst! Wondering if you can do more? You could edit the data files straight away to mass edit client data! But, with great power comes great responsibility, use it carefully!

LTNS application data is saved as a [JSON](#json-file) file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

Below is an example of what your JSON File should look like: 
<br>
<p align="center">
<img src="./images/JsonExample.png" width="230" height="450">
</p>


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LTNS will discard all data and start with an empty data file at the next run.
</div>

#### _______________________________________________________________________________________________________________________________________________________________________________

## Client Features

#### __________________________

### Adding a client : `addClient`

Adds a client to the LTNS.

Format: `addClient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ b/BIRTHDAY i/INCOME ra/RISK_APPETITE`

Find a detailed explanation of different input restrictions [here](#command-format-table).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The format for entering a birthday is "YYYY-MM-DD". The dates entered must be valid.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The `Risk Appetite` can be classified as High, Medium or Low. They are represented by characters "H", "M" and
"L" respectively. e.g: "ra/H". All other values will be ignored.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The income added is based on an individual's Yearly income.

An income added is based on the **tax income brackets** set up by [IRAS for Financial Year 2022-2023.](https://www.iras.gov.sg/taxes/individual-income-tax/basics-of-individual-income-tax/tax-residency-and-tax-rates/individual-income-tax-rates)

This information will be displayed in the app
</div>

| Income Bracket | Yearly Income range                                 |
|----------------|-----------------------------------------------------|
| First          | Less than or equal to S$30,000                      |
| Second         | More than S$30,000, less than or equal to $40,000   |
| Third          | More than S$40,000, less than or equal to $80,000   |
| Fourth         | More than S$80,000, less than or equal to $120,000  |
| Fifth          | More than S$120,000, less than or equal to $160,000 |
| Sixth          | More than S$160,000, less than or equal to $200,000 |
| Seventh        | More than S$200,000, less than or equal to $240,000 |
| Eight          | More than S$240,000, less than or equal to $280,000 |
| Ninth          | More than S$280,000, less than or equal to $320,000 |
| Tenth          | More than $320,0000                                 |

Example Usage:
* `addClient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01, b/2000-05-05 i/5000.0 ra/L` adds John Doe into the LTNS.
* `addClient n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal b/1920-05-06 i/1000000 ra/H` adds Betsy Crowe into the LTNS.

#### __________________________

### Switching to the view of currently listed clients : `clients`
Switches the display to show the current list of clients.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you previously filtered the client list and switched to view events/policy/income, this command will allow you to return to the filtered list of clients. 
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you wish to view the **full** list of clients, you may do so with the command `allClients`. 
</div>

Format: `clients`

Example Usage: `clients`

#### __________________________

### Listing all clients : `allClients`

Shows a list of all clients in the LTNS, without any filters.

Format: `allClients`

Example Usage: `allClients`

#### __________________________

### Editing a client : `editClient`

Edits an existing client in the LTNS.

Format: `editClient CLIENT_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [b/BIRTHDAY] [i/INCOME] [ra/RISK_APPETITE] ​`

* Edits the client at the specified `CLIENT_INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it. 

Example Usage:
*  `editClient 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `editClient 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.
*  `editClient 3 b/2000-01-01` Edits the birthday of the 3rd client to be the 1st January 2000.

#### __________________________

### Searching for Clients : `findClient`

Search for clients based on certain [metrics](#metric)

Format: `findClient [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​ [b/BIRTHDAY] [i/INCOME] [ra/RISK_APPETITE] [ti/TITLE] [cov/COVERAGE]…​ [cmp/COMPANY_CODE]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
At least one metric has to be specified for the command to be valid
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Title, Coverage and Company Code refer to the Assigned Policy information of the client. More details can be found in the [Policies](#policy-features) section
</div>

The following table illustrates the details of each metric and their search range:

| Metric        | Prefix | Search range                                                                                                                                                                                                                                                                                                                                              |
|---------------|--------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name          | n/     | - The search is case-insensitive. e.g `hans` will match `Hans`<br/> - The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`<br/> - Only full words will be matched e.g. `Han` will not match `Hans`<br/> - clients matching at least one keyword will be listed. <br/> e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`       |
| Phone         | p/     | - All clients whose phone number contains the exact specified numbers will be listed                                                                                                                                                                                                                                                                      |
| Email         | e/     | - The search is case-insensitive. e.g `alice@example.com` will match `Alice@example.com` <br/> - All clients whose email matches the input will be listed                                                                                                                                                                                                 |
| Address       | a/     | - The search is case-insensitive. e.g `Bedok` will match `bedok`<br/> - All clients whose address contains the input will be listed                                                                                                                                                                                                                       |
| Tag           | t/     | - The search is case-insensitive. e.g `friends` will match `Friends`<br/> - Only full words will be matched e.g. `colleagues` will not match `colleague`<br/> - More than one tag can be specified to list all clients that have all the specified tags. <br/> e.g. `friends` and `family` will list all clients with both the `friends` and `family` tag |
| Birthday      | b/     | - Only clients whose birthday falls on the specified date will be listed                                                                                                                                                                                                                                                                                  |
| Income        | i/     | - Any valid income value can be entered and it will be matched with the corresponding income bracket<br/> - All clients whose income falls under the same income bracket as the specified income will be listed <br/> e.g `15000` will be matched with `12000`                                                                                            |
| Risk Appetite | ra/    | - All clients whose risk appetite matches the specified risk level will be listed                                                                                                                                                                                                                                                                         |
| Title         | ti/    | - The search is case insensitive. e.g `Health Plan` will match `health plan`<br/> - Clients who are covered by a policy of which title contains at least one keyword will be listed                                                                                                                                                                       |
| Coverage      | cov/   | - More than one coverage can be specified to list all clients with all of the specified coverage types. <br/> e.g. `HEALTH` and `LIFE` will list all clients with both the `HEALTH` and `LIFE` coverage type <br/> - Clients who are covered by policies which covers all the specified coverage types will be listed                                     |
| Company Code  | cmp/   | - Clients who are covered by a policy belonging to the specified company will be listed                                                                                                                                                                                                                                                                   |

Example Usage:

* `findClient n/John` returns `john` and `John Doe`
* `findClient n/alex david` returns `Alex Yeoh`, `David Li`
* `findClient p/8743` returns clients with that contain `8743` in their phone number
* `findClient t/colleagues t/friends` returns clients with both the `colleagues` and `friends` tag
* `findClient n/alex t/friends` returns clients with the name `alex` and tagged with a `friends` tag

Below is an example of what you will expect to see when you call `findClient n/alex`:
![findClientExample](./images/findClientExample.png)

#### __________________________

### Deleting a client : `deleteClient`

Deletes the specified client from the LTNS.

Format: `deleteClient CLIENT_INDEX`

* Deletes the client at the specified `CLIENT_INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Example Usage:
* `allClients` followed by `deleteClient 2` deletes the 2nd client in the LTNS.
* `findClient n/Betsy` followed by `deleteClient 1` deletes the 1st Client in the results of the `findClient` command.

#### __________________________

### Sorting all Clients : `sort`

This allows you to sort your clients in the LTNS based on a specified metric.

Format: `sort KEYWORD`

* Sorts the list of clients based on specified keyword
* Keyword must be from client details


Sorting **KEYWORDS** are shown in the table below: 

| KEYWORD          | Function                                                                                                             |
|:-----------------|:---------------------------------------------------------------------------------------------------------------------|
| `default`        | Based on when a client was added, from oldest to newest                                                              |
| `name`           | By alphabetical order ie. "a" to "z"                                                                                 |
| `email`          | Clients using the same email platform are grouped together, thereafter, alphabetical order is used within each group |
| `phone`          | By numerical order of the phone number                                                                               |
| `birthday`       | From oldest to youngest                                                                                              |                                                                                             
| `income`         | From lowest to highest income                                                                                        |
| `risk appetite`  | From lowest to highest ie. "L" to "M" to "H"                                                                         |


<div markdown="span" class="alert alert-primary">:warning: **Warning:**
If your client's name contains numbers, sorting by name might behave incorrectly!
</div>

Example Usage: 
* `sort name` will display your Client list view in alphabetical order based on their name
* `sort phone` will display your Client list view in numerical order based on their phone numbers


Below is an example of what you will expect to see when you call `sort email`:

![SortEmailExample](./images/SortEmailExample.png)

#### __________________________

### Pin a Client: `pin`
Allows pin and unpin of important clients to be viewed separately with command `viewPin`
* Format: `pin INDEX` <br>

Example Usage:
* `pin 1` pins the first client on list

#### __________________________

### Assigning a Client to an Event/Policy 
An existing client in your Client list can be assigned to an Event or Policy.<br>
To find out how to do so, you can refer to the [Adding an Event](#adding-an-event-addevent) guide to assign a Client to an Event or refer to the [Assigning a Policy to a Client](#assigning-a-policy-to-a-client--assign) guide to find out how you can assign a Client to a Policy.

#### _______________________________________________________________________________________________________________________________________________________________________________

## Policy Features

Store policies from a large assortment of companies, with different coverages and customisable yearly commissions! Assign them to a client when you secure a deal with ease.

#### __________________________

### Adding a policy : `addPolicy`

Adds a policy to the LTNS.

Format: `addPolicy ti/TITLE cmp/COMPANY_CODE cms/Y1COMMISION% Y2COMISSION% Y3ONWARDS% cov/COVERAGE...`

Find a detailed explanation of different input restrictions [here](#command-format-table).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A policy must have one or more coverages.
</div>  

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
These are the valid coverages and their corresponding codes.
</div>

| Coverage Code | Coverage Name        |
|---------------|----------------------|
| LIFE          | Life Insurance       |
| MOTOR         | Motor Insurance      |
| HEALTH        | Health Insurance     |
| TRAVEL        | Travel Insurance     |
| PROPERTY      | Property Insurance   |
| MOBILE        | Mobile Insurance     |
| BITE          | Bite-sized Insurance |
| INVESTMENT    | Investment based     |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
These are the valid companies and their corresponding codes.
</div>

| Company Code | Company Name                       |
|--------------|------------------------------------|
| MNF          | Manulife Financial Private Limited |
| PRU          | Prudential Assurance Company       |
| AXA          | AXA Insurance Private Limited      |
| GEL          | Great Eastern Life                 |
| NTU          | NTUC Income Insurance              |
| ETQ          | Etiqa Insurance                    |
| TML          | Tokio Marine Life Insurance        |
| AIA          | AIA Singapore Private Limited      |
| AVI          | Aviva Limited                      |
| FWD          | FWD Singapore Private Limited      |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Commissions must take positive percentages within 0% to 100%! (e.g. 10% 3.5% 1.0%)
</div>  

Example Usage:
* `addPolicy ti/PruShield cmp/PRU cms/10% 5% 1% cov/LIFE`
* `addPolicy ti/ManuInvest Duo cmp/MNF cms/6% 3% 1.5% cov/INVESTMENT cov/LIFE`

#### __________________________

### Switching to the view of currently listed policies : `policies`
Switches the display to show the current list of policies.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you previously filtered the policy list and switched to view events/clients/income, this command will allow you to return to the filtered list of policies. 
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you wish to view the **full** list of policies, you may do so with the command `allPolicies`. 
</div>

Format: `policies`

Example Usage: `policies`

Example Result: Shows the currently listed policies on the screen.
![Policies](./images/policies.png)

#### __________________________

### Viewing all policies: `allPolicies`
Switches the display to show **all** the policies stored.

Format: `allPolicies` <br />
Example Usage: `allPolicies` <br />
Example Result: Shows all policies stored in LTNS, on the screen.

#### __________________________

### Deleting a policy : `deletePolicy`
Deletes the policy at the specified `INDEX`.
The index refers to the index number shown in the displayed list of policies.
The index **must be a positive integer** 1, 2, 3, …​

Format: `deletePolicy POLICY_INDEX`

Example Usage:
* `allPolicies` followed by `deletePolicy 2` deletes the 2nd policy in LTNS.
* `findPolicy ti/PruShield` followed by `deletePolicy 1` deletes the 1st policy in the results of the `findPolicy` command.

#### __________________________

### Searching for a Policy : `findPolicy`

Search for policies based on certain [metrics](#metric)

Format: `findPolicy [ti/TITLE] [cov/COVERAGE]…​ [cmp/COMPANY_CODE]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
At least one metric has to be specified for the command to be valid
</div>

The following table illustrates the details of each metric and their search range:

| Metric       | Prefix | Search range                                                                                                                                                                                                                                                                                |
|--------------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Title        | ti/    | - The search is case insensitive. e.g `Health Plan` will match `health plan`<br/> - Policies which titles contains at least one keyword will be listed                                                                                                                                      |
| Coverage     | cov/   | - More than one coverage can be specified to list all policies with all of the specified coverage types. <br/> e.g. `HEALTH` and `LIFE` will list all policies with both the `HEALTH` and `LIFE` coverage type <br/> - Policies which cover all the specified coverage types will be listed |
| Company Code | cmp/   | - Policies which belong to the specified company will be listed                                                                                                                                                                                                                             |

Example Usage:
* `findPolicy ti/Health plan` returns policies with the title `health plan` and `life plan`
* `findPolicy cov/LIFE cov/HEALTH` returns policies that cover both `LIFE` and `HEALTH`
* `findPolicy cmp/PRU` returns policies that belong to `Prudential Assurance Company`
* `findPolicy ti/Shield cov/LIFE` returns polices with `Shield` in its title and covers the `LIFE` coverage type

#### __________________________

### Assigning a Policy to a Client : `assign` 

If you have successfully sealed a deal with a client, you can keep track of this by assigning the policy to your client! 

If you have yet to add either your Client or Policy to the Application, you can refer to the [Adding a Client](#adding-a-client--addclient) or [Adding a Policy](#adding-a-policy--addpolicy) guides.

Format: `assign CLIENT_INDEX POLICY_INDEX pr/PREMIUM sd/STARTDATE ed/ENDDATE`

Find a detailed explanation of different input restrictions [here](#command-format-table).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The start and end dates should be of the format "YYYY-MM-DD", and within the years 1900 - 2100.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Premium should be any positive numeric value below `1000000000` with or without 2 decimal places.
</div>

Example Usage:

* Step 1: `findPolicy cmp/PRU` to filter the list of policies that belong to Prudential Assurance Company.
* Step 2: `findClient n/John Doe` to filter the list of clients to find 'John Doe'.
* Step 3: `assign 1 1 pr/2000 sd/2010-10-10 ed/2021-10-12` to assign the first policy in the list from Step 1, to the 
first client in the list from Step 2, with the following details:
  1. Yearly premium of $2000
  2. Start date of 10th October 2010
  3. End date of 12th October 2021

#### __________________________

### Listing out policies assigned to a client : `listAssigned`

If you'd like to recall which policies a client has taken up, this command lists out the policies 
that have been assigned to a specific client, in the status message.

Format: `listAssigned CLIENT_INDEX`

Example Usage:

1. `findClient n/John Doe` to filter the list of clients to find `John Doe`.
2. `listAssigned 1` to list out the policies assigned to the first client in the list from Step 1.

#### __________________________

### Deleting assigned policies from a client : `deleteAssigned`

Unfortunate, but it happens. If a client cancels their policy prematurely, reflect the deletion of their assigned policy
in the LTNS using this command.

Format: `deleteAssigned CLIENT_INDEX ASSIGNED_POLICY_INDEX`

Example Usage:
1. Similar to [listAssigned](#listing-out-policies-assigned-to-a-client--listassigned), filter the list for the desired client and find out which policies they have been assigned to.
2. `deleteAssigned 1 1` to delete the first assigned policy in the assigned policy list from Step 1, of the first client obtained from filtering the client list.

#### __________________________

### Tracking your Income  : `viewIncome`

Of course, as a financial advisor you would want to know your income! Automatically calculate your 3 year expected income (with selected year as starting year)

Format: `viewIncome YEAR`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Year chosen must be between 1900 and 2100
</div>

Example Usage:
* `viewIncome 2000` will display the expected income for years 2000, 2001 and 2002
* `viewIncome 1899` will not be allowed since 1899 is not between 1900 and 2200

![result for 'viewIncome 2000'](images/viewIncome.png)

(Result after typing the command `viewIncome 2000`, which displays three year expected income starting from year 2000)

#### _______________________________________________________________________________________________________________________________________________________________________________

## Event Features

#### __________________________

### Adding an Event: `addEvent`

Find a detailed explanation of different input restrictions [here](#command-format-table).

An `Event` represents an appointment with an existing Client. It has the duration (represented by the start and end time) and
the date of the appointment. It also has a handy description to briefly describe the contents of the meeting.

Never miss another meeting today!

Format: `addEvent desc/EVENT_DESCRIPTION n/CLIENT_NAME date/EVENT_DATE st/START_TIME et/END_TIME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    START_TIME must be chronologically before END_TIME. All times are in the format: `HH:MM`.
</div>


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    EVENT_DATE follows a standard date format for our app (i.e: YYYY-MM-DD).
</div>


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    CLIENT_NAME indicates the name of a valid client within the Client List. All Events must be tagged to a single client.
</div>

If you have not added this Client to your Client list, you can refer to the [Adding a Client](#adding-a-client--addclient) guide to add your Client first.


* Example Usage: `addEvent desc/CS101 Consultation n/Ben Leong date/2023-01-01 st/12:00 et/13:00`
* Example Result: add an event with `Ben Leong` from `12:00` to `13:00` for the `1st January 2023` for a CS101 consultation.

#### __________________________

### Deleting an Event: `deleteEvent`

Deletes the event at the specified `EVENT_INDEX`. The index refers to the index number shown in the displayed event list.
The index **must be a positive integer** 1, 2, 3, …​

Format: `deleteEvent EVENT_INDEX`

* Example Usage: `deleteEvent 2`
* Example Result: deletes the 2nd event from the list.

#### __________________________

### Switching to the view of currently listed events : `events`
Switches the display to show the current list of events
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you previously filtered the event list and switched to view policy/income, this command will allow you to return to the filtered list of events. 
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you wish to view the **full** list of events, you may do so with the command `allEvents`. 
</div>

Format: `events`

Example Usage: `events`

#### __________________________

### Viewing all Events : `allEvents`

This allows you to view all Events that have been previously added.
* Note that events are automatically sorted in chronological order.

Format: `allEvents` <br>
Example Usage: `allEvents` <br>
Example Results: Lists all events added to the app, automatically sorted in chronological order.

![result for 'allEventsSample'](images/allEventsSample.png)

#### __________________________

### Searching for an Event : `findEvent`

Search for events based on certain [metrics](#metric)

Format: `findEvent [desc/EVENT_DESCRIPTION] [n/NAME] [date/EVENT_DATE]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
At least one metric has to be specified for the command to be valid
</div>

The following table illustrates the details of each metric and their search range:

| Metric                                                                     | Prefix | Search range                                                                                                                                                                                                                                                                                                                                                                                            |
|----------------------------------------------------------------------------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Event Description                                                          | desc/  | - The search is case-insensitive. e.g `Meeting to discuss plans` will match `meeting to discuss plans`<br/> - Only full phrases will be matched e.g. `Meeting to discuss plans` will not match `Plans`<br/> - Only events for which description contains the complete specified phrase will be listed. <br/> e.g. `discuss plans` will return `Meeting to discuss plans` and `Discuss plans`            |
| Name<br/><br/> Note: Name here refers to the name of the event participant | n/     | - The search is case-insensitive. e.g `John` will match `john`<br/> - The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`<br/> - Only full words will be matched e.g. `Han` will not match `Hans`<br/> - Events with the participant name containing at least one keyword will be listed. <br/> e.g. `Hans Bo` will return events which involve `Hans Gruber` or `Bo Yang`   |
| Event Date                                                                 | date/  | - Only events which occur on the specified date will be listed                                                                                                                                                                                                                                                                                                                                          |

Example Usage:

* `findEvent desc/coffee break meeting` returns events `morning coffee break meeting` and `afternoon coffee break meeting`
* `findEvent n/Bernice` returns all events for which participant name contains `Bernice`
* `findEvent date/2022-12-30` returns all events which occurs on `30th December 2022`
* `findEvent n/Alice date/2022-11-15` returns all events for which participant name contains `Alice` and occurs on `15th November 2022`

#### __________________________

### Viewing all Events in the next 7 days : `calendar`

This allows you to view all Events that you have in the next 7 days, allowing you to keep track of any upcoming important appointments you have scheduled. 

Format: `calendar`

--------------------------------------------------------------------------------------------------------------------

## FAQ

Q: How do I transfer my data to another Computer? <br/>
A : Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LTNS home folder.

Q: Why am I seeing `The person you specified doesn't exist` when trying to add an event?<br/>
A : When specifying the Client that you're meeting for this event, this Client must exist in your client list first.

Q: Why am I seeing `This Event overlaps with another event....` when trying to add an event? <br/>
A: When adding an event, it's date and time cannot overlap with another event! But fret not, we've listed all overlapping events for your convenience, 
so you can view other events were causing the clash :) 

Q: Why am I seeing a `Sorting metric does not exist` when trying to sort my client list? <br/>
A : You may be typing a wrong <b>keyword</b> or calling a sorting metric which is not supported. You may refer to [this list](#sorting-all-clients--sort) for a list of supported **keywords**. If you would like to suggest more sorting **keywords**, do feel free to [contact us](#contact-us)! 

Q: Why is a blank screen shown when I use the `calendar` feature? <br/>
A: You may not have added any upcoming events in the next 7 days! You may want to [Add an Event](#adding-an-event-addevent) first. Alternatively, you can choose to [view all Events](#viewing-all-events--allevents) instead.

Q: What if a company whose policy I want to add is not available in the given list of company codes? <br/>
A: Let us know which company you require by [contacting us](#contact-us). If we get enough requests, we might add yours in the next version!

Q: What if a coverage I want to add is not available in the list of coverages? <br/>
A: Similarly, let us know which coverage you require by [contacting us](#contact-us). If we get enough requests, we might add yours in the next version!

Q: What do I do if the user guide does not address my concern? <br/>
A: Fret not! Simply [reach out to us](#contact-us), and we will reply within three working days!


--------------------------------------------------------------------------------------------------------------------


## Command format table

| Parameter Name      | Prefix | Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|---------------------|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name                | n/     | - Only alphanumeric characters and spaces can be specified, and it should not be blank<br/> - Only a maximum of 60 characters is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| Phone               | p/     | - Only numbers are allowed<br/>e.g `3.3` is not valid<br/> - At least 3 numbers have to be specified<br/> - Only a maximum of 25 digits is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| Email               | e/     | <!--- Emails should be of the format `local-part@domain`<br/> - The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-)<br/> - The local-part cannot start or end with any special characters<br/> - The domain name is made up of domain labels separated by periods<br/> - The domain name must end with a domain label at least 2 characters long, have each domain label start and end with alphanumeric characters and have each domain label consist of alphanumeric characters, separated only by hyphens, if any --> - Only valid emails are allowed<br/> e.g `Alice` is not valid while `Alice@email.com` is valid |
| Address             | a/     | - Any length of textual input except for an empty space is valid                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| Tag                 | t/     | - Only single word, alphanumeric inputs are allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| Birthday            | b/     | - Only valid date inputs of the YYYY-MM-DD format are allowed. e.g `1990-12-30`<br/> - Birthdays cannot be set in the future or before the 20th century (i.e 1900)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| Income              | i/     | - Only positive decimal numbers are allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| Risk Appetite       | ra/    | - Only one of the 3 levels, {H, M, L}, is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| Title               | ti/    | - Only alphanumeric characters and spaces can be specified, and it should not be blank<br/> - Only a maximum of 40 characters is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| Coverage            | cov/   | - Only inputs of the valid coverage type options are allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Company Code        | cmp/   | - Only one of the valid company codes is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| Commission          | cms/   | - Has to be in the format of (Y1COMMISSION)% (Y2COMMISSION)% (Y3ONWARDS)%, where the commission values and the brackets are to be replaced with numeric values between 0 and 100 inclusive, with up to 5 decimals.                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| Premium             | pr/    | - Only positive numbers below 1000000000, with or without 2 decimal places are allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Start date          | sd/    | - Only valid date inputs of the YYYY-MM-DD format are allowed. e.g `2020-12-30`<br/> - Only dates that fall between the 20th and 21th century are allowed (i.e between the years 1900 and 2100)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| End date            | ed/    | - Only valid date inputs of the YYYY-MM-DD format are allowed. e.g `2020-12-30`<br/> - Only dates that fall between the 20th and 21th century are allowed (i.e between the years 1900 and 2100) <br/> - Should be later than the associated start date                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Event Description   | desc/  | - Only alphanumeric characters and spaces can be specified, and it should not be blank<br/> - Only a maximum of 175 characters is allowed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Event Date          | date/  | - Only valid date inputs of the YYYY-MM-DD format are allowed. e.g `2020-12-30`<br/> - Only dates that fall between the 20th and 21th century are allowed (i.e between the years 1900 and 2100)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| Start time          | st/    | - Only valid time inputs of the HH:mm format are allowed. e.g `23:59`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| End time            | et/    | - Only valid time inputs of the HH:mm format are allowed. e.g `23:59` <br/> - Should be later than the associated start time                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |

--------------------------------------------------------------------------------------------------------------------

## Command summary

What's a guide without a summary? Here you go! Everything you need, summarised into one section!

### General Commands

| Action                      | Format, Examples                                                                                                                                                                                              |
|-----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clear Pre-existing Data** | `clear`                                                                                                                                                                                                       |                                                                                                                                                                                                      
| **Help**                    | `help`                                                                                                                                                                                                        |
| **Exit Application**        | `exit`                                                                                                                                                                                                        |


### Commands For Clients

| Action                    | Format, Examples                                                                                                                                                                                                          |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Find Client**           | `findClient [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [b/BIRTHDAY] [i/INCOME] [ra/RISK_APPETITE] [ti/TITLE] [cov/COVERAGE]…​ [cmp/COMPANY_CODE]` <br><br> e.g: `findClient n/Jim p/98765432`                      |
| **Add Client**            | `addClient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS t/TAG…​`  <br><br> e.g: `addClient n/Clement Tan p/98765432 e/clementTan@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney b/2019-05-05 i/10000.00 ra/M` |
| **Delete Client**         | `deleteClient (INDEX of CLIENT)` <br><br> e.g: `deleteClient 3`                                                                                                                                                           |
| **Edit Client**           | `editClient (INDEX of CLIENT) [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br><br> e.g: `editClient 2 n/James Lee e/jameslee@example.com`                                                                  |
| **View All Clients**      | `allClients`                                                                                                                                                                                                              |
| **View Filtered Clients** | `clients`                                                                                                                                                                                                                 |                                                              
| **Sort Clients**          | `sort SORTING_METRIC`<br> eg. `sort name`                                                                                                                                                                                 |
| **Pin**                   | `pin (INDEX of CLIENT)` <br><br> e.g: `pin 1`                                                                                                                                                                             |
| **View Pinned**           | `viewPin`                                                                                                                                                                                                                 |

Note: `allClients` shows all existing clients inside LTNS, while `clients` show all clients based on filter metric placed previously.

### Commands For Policy

| Action                     | Format, Examples                                                                                                                                              |
|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Find Policy**            | `findPolicy [ti/TITLE] [cov/COVERAGE]…​ [cmp/COMPANY_CODE]` <br/><br>  e.g: `findPolicy cov/LIFE`                                                             |        
| **Add Policy**             | `addPolicy ti/POLICY_TITLE cmp/POLICY_COMPANY cms/POLICY_COMMISSION cov/POLICY_COVERAGE...` <br><br> e.g: `addPolicy ti/Health cmp/MNF cms/4% 3% 2% cov/LIFE` |                                                                                                                         
| **Delete Policy**          | `deletePolicy (INDEX of POLICY)` <br> <br> e.g: `deletePolicy 1`                                                                                              |                                                                                                                                                                                             
| **View All Policies**      | `allPolicies`                                                                                                                                                 |
| **View Filtered Policies** | `policies`                                                                                                                                                    |
| **Assign Policies**        | `assign (INDEX of CLIENT) (INDEX of POLICY) pr/PREMIUM_PAID sd/START_DATE ed/END_DATE` <br> <br> e.g: `assign 1 1 pr/10000 sd/2000-01-02 ed/2000-02-01`       |
| **Delete Assigned**        | `deleteAssigned (INDEX of CLIENT) (INDEX of POLICY)` <br><br> e.g: `deleteAssigned 1 1`                                                                       |                                                                                                                                                                                                               
| **List Assigned**          | `listAssigned (INDEX of CLIENT) ` <br> <br> e.g: `listAssigned 1`                                                                                             |
| **View Income**            | `viewIncome YEAR` <br><br> e.g: `viewIncome 2000`                                                                                                             |                                                                                                                                                                                                                                                                                                                                                                           

Note: `allPolicies` shows all existing policies inside LTNS, while `policies` show all policies based on filter metric placed previously.

### Commands For Events


| Action                   | Format, Examples                                                                                                                                                                       |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Find Event**           | `findEvent [desc/EVENT_DESCRIPTION] [n/NAME] [date/EVENT_DATE]` <br/><br> e.g: `findEvent date/2022-05-05`                                                                             |
| **Add Event**            | `addEvent desc/EVENT_DESCRIPTION n/NAME date/EVENT_DATE st/START_TIME et/END_TIME` <br><br>  e.g: `addEvent desc/Meet Clement at Noon n/Clement Tan date/2022-10-10 st/12:00 et/13:00` |
| **Delete Event**         | `deleteEvent (INDEX of EVENT)` <br><br>  e.g: `deleteEvent 1`                                                                                                                          |
| **View All Events**      | `allEvents`                                                                                                                                                                            |
| **View Filtered Events** | `events`                                                                                                                                                                               |
| **View Calendar**        | `calendar`                                                                                                                                                                             |                                                                                                                                                                                                     

Note: `allEvents` shows all existing events inside LTNS, while `events` show all events based on filter metric placed previously.

#### Additional Notes:
* For better clarity, `INDEX` has been placed in parentheses to denote clearly which index is being referred to (either CLIENT, POLICY or EVENT). At the same time, it is compulsory to include `INDEX`.
  <br> E.g: `(INDEX of EVENT)` refers to index from a list of events.

--------------------------------------------------------------------------------------------------------------------

## Glossary 

#### Parameter
Parameters refer to the information you type into the app to be stored. For example, `n/John` where John is the parameter.

#### Policy
An insurance or investment package entailing the details of the insurance and/or investment.

#### Coverage 
Specific to an insurance policy, coverage describes the amount of risk or liability which is covered by the insurance policy.

#### Graphic User Interface (GUI)
A User Interface that allows users to interact with the application through visual components.

#### Command-Line Interface (CLI)
A Text-Based User Interface which allows you to use the application in the form of typing out "commands".

#### JSON File 
A file which is used to store data in text form. 

#### Metric 
A standard of measurement. In the case of LTNS, a metric in sorting or searching tells the application how to compare the clients.

#### Income Bracket
Various income level ranges categorised by levels. LTNS uses the Inland Revenue Authority of Singapore's (IRAS) measurement of an income bracket.

--------------------------------------------------------------------------------------------------------------------

## Contact Us 
You may contact us at the following email: `e0725346@u.nus.edu`

<br>    
<br>
<br>
