---
layout: page
title: User Guide
---
<div align="center">
<h1> PetCode User Guide</h1>
Welcome to the PetCode user guide!<br><br>

PetCode is a desktop app that helps <b>store and manage contact information for your pet sales coordination business</b>.
</div>

{:refdef: style="text-align: center;"}
![PetCode Logo](images/PetCode Logo.png)
{: refdef}

#### Using this guide
If this is the first time you are using this user guide, it is highly recommended for you to read the section on
[Introducing PetCode](#introducing-petcode). Otherwise,

* If you are setting up, please take a look at our [Quick Start guide](#quick-start).
* If you are unsure of how to use PetCode, the [Command Summary](#command-summary) table is a good starting point.
* If you are a developer and want to help out, please take a look at the [Developer Guide](DeveloperGuide.md).
* For quick navigation in this guide, click the hyperlink at the end of each command to go back to the table of contents, and navigate to other sections of this guide from there.

## Table of Contents
- **[Introducing PetCode](#introducing-petcode)**
    * [What is PetCode?](#what-is-petcode)
    * [Glossary](#glossary)
    * [How to interpret the display window](#how-to-interpret-the-display-window)
- **[Quick Start](#quick-start)**
- **[Commands](#commands)**
    * [Viewing help](#viewing-help--help)
    * [Listing contacts or items](#listing-contacts-or-items--list)
    * [Checking which item belongs to which contact](#checking-which-item-belongs-to-which-contact--check)
    * [Adding a contact or item](#adding-a-contact-or-item--add)
        + [Adding a buyer](#adding-a-buyer--add-b)
        + [Adding a deliverer](#adding-a-deliverer--add-d)
        + [Adding a supplier](#adding-a-supplier--add-s)
        + [Adding an order to a buyer](#adding-an-order-to-a-buyer--add-o)
        + [Adding a pet to a supplier](#adding-a-pet-to-a-supplier--add-p)
        + [Adding a contact with a popup window](#adding-a-contact-with-a-popup-window--add)
    * [Matching pets to an order](#matching-pets-to-an-order--match)
    * [Deleting a contact or item](#deleting-a-contact-or-item--delete)
    * [Editing attributes of a contact](#editing-attributes-of-a-contact--edit)
    * [Finding contact(s) using keywords](#finding-contacts-using-keywords--find)
        + [Finding a buyer](#finding-a-buyer--find-b)
        + [Finding a supplier](#finding-a-supplier--find-s)
        + [Finding a deliverer](#finding-a-deliverer--find-d)
    * [Filtering items by attributes](#filtering-items-by-attributes--filter)
        + [Filtering orders](#filtering-orders--filter-o)
        + [Filtering pets](#filtering-pets--filter-p)
    * [Sorting contacts](#sorting-contacts-and-items--sort)
    * [Clearing all contacts](#clearing-all-entries--clear)
    * [Exiting the program](#exiting-the-program--exit)
    * [Automation of information flow (future feature)](#automation-of-information-flow-coming-in-v20)
- **[How data is stored](#how-data-is-stored)**
    * [Saving contacts and items](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Archiving data files](#archiving-data-files-coming-in-v20)
- **[FAQ](#faq)**
- **[Summaries](#summaries)**
    * [List of prefixes](#list-of-prefixes)
    * [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Introducing PetCode

Whether you're new to PetCode, or just want to learn more about the details -- this section has you covered.
This section will provide an overview of PetCode and explain key terms.

### What is PetCode?

PetCode is a free, open-source application designed for pet sales coordinators for contact information management.

Due to the nature of a pet sales coordination business, you most likely have **a lot of information you need to deal with**.
For example, what orders have you received? Which orders have not been fulfilled? How should you match this order with
the pets available? What is the contact information of your pet buyers, pet suppliers and delivery services?

PetCode is designed specifically to **improve your workflow**, by managing all this information to efficiently close
deals and satisfy your customers. It can be used to offload information, categorise them more meaningfully, and match
your customers' requests to their dream pet.

### How to interpret the display window

When you first run the app, you may see a display window pop up similar to the one below. We call this window the **Main Window**.
![Starting Display Window](images/StartUIPage.png)

The following diagram below shows how you should interpret this display window.
* The **Command Box** refers to the text field where you can type commands in.
* The **Display List for Contacts / Items** refers to the list of contacts / items you are currently displaying.
  You may enter the following commands in the Command Box to see how the Display List changes:
    * `list buyer` lists all buyers.
    * `delete-b 1` deletes the buyer with index 1.
    * `list order` lists all orders.
      ![Interpreted Display Window](images/InterpretGUI.png)

### Glossary

In the user guide, you may come across some terms you do not understand. The following table provides clarification
of the terms commonly used in PetCode.

|       Term       | Description                                                                                                                                                                                                                                                                                                                                                  |
|:----------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Alphanumeric** | Digits and letters only. For example, `AB3`, `PetCode`, `coco123`, and `2103` are alphanumeric. `#01-04`, `email@domain.com`, and `white    spaces` are not.                                                                                                                                                                                                 |
|  **Attribute**   | Words that follow prefixes to describe properties, states, characteristics, and traits. Examples are price, weight, name, and order status.                                                                                                                                                                                                                  |
|   **Command**    | A command is a specific instruction you can give to PetCode to perform an action. You can view the list of commands available [here](#command-summary).                                                                                                                                                                                                      |
|   **Contact**    | A contact is an information entry in PetCode. There are three types of contacts you can add - `Buyer`, `Supplier` and `Deliverer`. You can add a contact with the [`add` command](#adding-a-contact-or-item-add).                                                                                                                                            |
|     **CLI**      | Command-Line Interface (CLI) receives commands from a user in the form of lines of text. It refers to the input textbox in this context.                                                                                                                                                                                                                     |
|     **GUI**      | GUI stands for Graphical User Interface. It refers to the display window of the PetCode application.                                                                                                                                                                                                                                                         |
|    **Index**     | The index of the contact or item in the display list for contacts/items.                                                                                                                                                                                                                                                                                     |
|   **Integer**    | Whole number                                                                                                                                                                                                                                                                                                                                                 |
|     **Item**     | An item refers to an `Order` or a `Pet`. An Order refers to the order placed by a buyer. A Pet refers to the pet available for sale.                                                                                                                                                                                                                         |
|  **Parameter**   | A parameter refers to the information you need to give to your command such that it can execute an action based on that information. <br/> For example, the [`list` command](#listing-contacts-or-items--list) requires a KEY parameter to know what kind of list to display. `list buyer` displays your list of buyers, where the KEY parameter is `buyer`. |
|    **Prefix**    | A prefix indicates the kind of information you are keying in. You can view the list of prefixes available [here](#list-of-prefixes).                                                                                                                                                                                                                         |
|  **Whitespace**  | An empty character, or a placeholder character ` `.                                                                                                                                                                                                                                                                                                          |

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. Please kindly refer
   [here](https://blog.hubspot.com/website/check-java-verison) for further instructions on how to do so.

2. Download the latest `petcode.jar` from [here](https://github.com/AY2223S1-CS2103T-T09-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your PetCode app.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note that the app
   contains some sample data and the pet images are unchangeable in the current version of PetCode. Clicking the hyperlinks under the pet image will just open your browser and does nothing more. <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press ENTER to execute it. e.g. typing **`help`** and pressing ENTER will
   open the help window.<br>
   Some example commands you can try:

    * **`list buyer`** : Lists all buyers.

    * **`add-b n/Hongyi ph/11223344 e/email@u.nus.edu a/UTR 138600 l/Singapore`** : Adds
      a pet buyer named `Hongyi` to the PetCode.

    * **`delete-b 1`** : Deletes the first contact from the buyer contacts list.

    * **`clear`** : Deletes all contacts. You can use this command to clear all the sample data provided. Be extra careful when using this command since **there is no `undo`**.

    * **`exit`** : Exits the app.

6. Refer to the [Commands](#commands) Section below for details of each command.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

:information_source: **How to interpret the Command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `p_n/PET_NAME [p_cert/CERTIFICATE]` can be used as `p_n/Page p_cert/USA Bureau of Exportation Certified` or as `p_n/Page`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[p_cert/CERTIFICATE]…​` can be used as ` ` (i.e. 0 times), `p_cert/noble blood`, `p_cert/noble blood p_cert/house-trained` etc.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `ph/12341234 ph/56785678`, only `ph/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If the command format contains round brackets `()`, it means the command format inside `()` is omitted and can be found in another command.<br>
  e.g. in `add-b n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION o/add-o(order1 prefixes and parameters)…​`, the detailed command format inside `()` is omitted and can be found in [Adding an order to a buyer](#adding-an-order-to-a-buyer--add-o).

* Unless otherwise specified, the order of prefixes does not matter.<br>
  e.g. if the command specifies `n/NAME ph/PHONE_NUMBER`, `ph/PHONE_NUMBER n/NAME` is also acceptable, unless stated otherwise in a particular command.


</div>

[Go back to [Table of Contents](#table-of-contents)]

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpWindow.png)

Format: `help`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Listing contacts or items : `list`

Displays the specified type of contacts or items. This command is especially useful when you want to view all contacts or items of the same type,
or when you want to set the display list to the correct list as required by other commands.

Format: `list KEY`

#### KEY Table

| Contact / Item to List |     KEY      |
|:----------------------:|:------------:|
|         Buyer          |   buyer, b   |
|        Supplier        | supplier, s  |
|       Deliverer        | deliverer, d |
|         Order          |   order, o   |
|          Pet           |    pet, p    |
|       All Person       |    all, a    |


Examples:
* `list buyer` or `list b`, lists all Buyer contacts with their orders.
* `list deliverer` or `list d`, lists all Deliverer contacts.
* `list supplier` or `list s`, lists all Supplier contacts with their pets.
* `list all` or `list a`, lists all Buyer, Deliverer, Supplier contacts and their respective pets and orders details.
* `list order` or `list o`, lists all Orders.
* `list pet` or `list p`, lists all Pets.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Checking which item belongs to which contact : `check`

Checks a contact at the specified index and shows his/her items, or checks an item at the specified index and shows the contact it belongs to.
This command is especially useful when you want to switch the display between related buyers and orders, or switch the display between related suppliers and pets.

Format: `check KEY INDEX`

#### KEY Table

| Contact / Item to Check |     KEY      |
|:-----------------------:|:------------:|
|          Buyer          |   buyer, b   |
|        Supplier         | supplier, s  |
|          Order          |   order, o   |
|           Pet           |    pet, p    |

#### Expected Behaviour for each type of the Check command

| Examples        | Expected behaviour                                                                        |
|-----------------|-------------------------------------------------------------------------------------------|
| `check buyer 1` | Shows the list of orders from the buyer at index 1, if at index 1 is a buyer.             |
| `check s 2`     | Shows the list of pets on sale from the supplier at index 2, if at index 2 is a supplier. |
| `check order 3` | Shows the buyer of the order at index 3, if at index 3 is an order.                       |
| `check p 4`     | Shows the supplier of the pet at index 4, if at index 4 is a pet.                         |

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** For the current version of PetCode, `check deliverer INDEX` will just display an empty list, since adding orders to deliverers is not yet implemented.
In the future, you may be able to transfer orders from buyers to deliverers and check out the deliverers' orders.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Adding a contact or item : `add`

Adds a contact or item to the address book.

* A contact can be of three categories: Buyer, Deliverer, and Supplier.
* An item can be either an Order or Pet.

General Format for add command: `add-KEY prefix/PARAMETERS…​`, where:

* `KEY` specifies what type of contact or item you want to add.
* `prefix` indicates the kind of information you are adding.
* `PARAMETER` is the content of the information you are adding.

Kindly refer to the [Summaries](#summaries) section for more information on the available prefixes and a summarised list
of add commands.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** If you are a beginner, we highly recommend you to use
the [Add Command using the popup window](#adding-a-contact-with-a-popup-window--add)
instead of the usual CLI interface.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a buyer : `add-b`

Adds a buyer to the contact list. A buyer is a person who would like to buy pet(s) and places one or more orders describing what kind
of pet(s) he/she would like.

You can add a buyer without orders as shown below.

Format: `add-b n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION`

Example:
* To add a single buyer: `add-b n/Hongyi ph/11223344 e/email@u.nus.edu a/UTR 138600 l/Singapore`

<div markdown="span" class="alert alert-info">

:information_source: **What is the difference between address and location?** <br>

 * **Address** is the specific street number and unit number of the place. <br>
 * **Location** is the country this person is based. <br>

Since PetCode caters to international pet sale, it is good to have location as a separate attribute.
Different countries have different regulations on pet sale, and you may need to filter persons by their locations for some reason.

</div>

What if the buyer that you want to add already has some orders?
**You can add a buyer and his/her orders in one shot!** Check it out below :point_down: <br><br>
Format: `add-b n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION o/add-o(order1 prefixes and parameters) o/add-o(order2 prefixes and parameters)…​`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can input as many `o/add-o` prefixes as you need. Each `o/add-o` creates an order under the buyer.
After each `o/add-o`, simply enter the details of the order without specifying the index of the associated buyer.
Don't know how to add an order properly? Refer to [Add Order](#adding-an-order-to-a-buyer--add-o) for more information.

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** For more details on what each prefix represents, kindly refer to [List of Prefixes](#list-of-prefixes).

</div>

Examples:
* To add a buyer with one
  order: `add-b n/Hongyi ph/11223344 e/hhygg@u.nus.edu a/UTR 138600 l/Singapore o/add-o o_st/Pending o_r/add-r o_a/1 o_sp/Siamese cat o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* To add a buyer with two
  orders: `add-b n/Hongyi ph/11223344 e/hhygg@u.nus.edu a/UTR 138600 l/Singapore o/add-o o_st/Pending o_r/add-r o_a/1 o_sp/Siamese cat o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery o/add-o  o_st/Negotiating o_r/add-r o_a/3 o_sp/Shih Tzu o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty`


<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Take note that the above added buyers are considered as the SAME, so if you try all these sample commands, PetCode will notify you that the buyer already exists in the list.
Check out [FAQ](#faq) on the concept of "How contacts and items are considered as duplicates".

</div>


<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** **Overwhelmed by the prefixes** when adding multiple orders?
Check out [Add Command using the popup window](#adding-a-contact-with-a-popup-window--add) to add multiple orders when adding a buyer **without prefixes**.

</div>

To help you better understand the hierarchy of the second sample command, we illustrate its structure as follows:

<img src="images/AddBuyerCommandIllustration.png" alt="drawing" width="550"/>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a deliverer : `add-d`

Adds a deliverer to your contact list. A deliverer delivers pets from suppliers to buyers.

Format: `add-d n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION`

Examples:

* To add a single deliverer: `add-d n/Lezheng ph/19657471 e/lez998@u.nus.edu a/PGP Residences 118429 l/Singapore`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a supplier : `add-s`

Adds a supplier to the contact list. A supplier feeds, trains, and takes care of pets for sale.

You can add a supplier without pets as shown below.

Format: `add-s n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION`

Example:
* To add a single supplier: `add-s n/Carol Pet House ph/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 l/USA`

Similar to the [Add Buyer](#adding-a-buyer-add-b) command, you may feel the need to add a supplier together with all the pets he/she sells in one shot.
Check it out below :point_down:

Format: `add-s n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION p/add-p(pet1 prefixeds and parameters) p/add-p(pet2 prefixeds and parameters)…​`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** Note that you can input as many `p/add-p` prefixes as you need. Each `p/add-p` creates an order under the buyer.
After each `p/add-p`, simply enter the details for the pet without specifying the index of the associated supplier.
Don't know how to add a pet properly? Refer to [Add Pet](#adding-a-pet-to-a-supplier--add-p) for more information.

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** For more details on what each prefix represents, kindly refer to [List of Prefixes](#list-of-prefixes).

</div>

Examples:
* To add a supplier with one pet for
  sale: `add-s n/Carol Pet House ph/17238965 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 l/Singapore p/add-p p_n/Luck p_d/2022-01-01 p_c/pink p_cp/pure pink p_h/41.2 p_s/Yorkshire pig p_cert/US certified p_v/true p_w/102.5 p_p/270.3`
* To add a supplier with two pets for
  sale: `add-s n/Carol Pet House ph/17238965 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 l/Singapore p/add-p p_n/Luck p_d/2022-01-01 p_c/pink p_cp/pure pink p_h/41.2 p_s/Yorkshire pig p_cert/US certified p_v/true p_w/102.5 p_p/270.3 p/add-p p_n/Snupy p_d/2021-05-31 p_c/white p_cp/dotted p_h/89.3 p_cert/US certified p_s/Californian rabbit p_v/false p_w/32.8 p_p/330.3`

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Take note that the above added suppliers are considered as the SAME, so if you try all these sample commands, PetCode will notify you that the supplier already exists in the list.
Check out [FAQ](#faq) on the concept of "What is being the same".

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** **Overwhelmed by the prefixes** when adding multiple pets?
Check out [Add Command using the popup window](#adding-a-contact-with-a-popup-window--add) to add multiple pets when adding a supplier **without prefixes**.

</div>

To help you better understand the hierarchy of the second sample command, we illustrate its structure as follows:

<img src="AddSupplierCommandIllustration.png" alt="drawing" width="550"/>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding an order to a buyer : `add-o`

Adds an order to a buyer contact. This is especially useful when an existing buyer has a new order, or when the buyer confirms the order some time after being added to the contacts.

Format: `add-o INDEX_OF_BUYER o_st/STATUS o_r/add-r o_a/AGE o_sp/SPECIES o_c/COLOR o_cp/COLOR_PATTERN o_p/PRICE o_pr/PRICE_RANGE o_d/DATE [o_ar/ADDITIONAL_REQUEST]…​`

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: `INDEX_OF_BUYER` should be immediately after `add-o`.
Ensure that at the index is a buyer before executing this command,
which can be achieved by executing the [List Command](#listing-contacts-or-items--list) or [Find buyer command](#finding-a-buyer--find-b) beforehand. <br>
</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Please ensure that `o_r/` is followed by `add-r` immediately and there are no other prefixes
between `o_r/`, `o_a/`, `o_c/`, `o_cp/`, and `o_sp/`. This is because they as a whole specify how the requested pet
should be like.

</div>

<div markdown="span" class="alert alert-info">

:information_source: **What is a request in an order?** <br>

The prefix `o_r/` specifies a request, which contains the **characteristics of a pet** that the buyer wants the pet to have,
including age, color, color pattern and species.
Other information in the order that is **not directly related to a pet**, such as the order status and the price range the buyer is willing to accept, **does not fall under request**.

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** For more details on what each prefix represents, kindly refer to [List of Prefixes](#list-of-prefixes).

</div>

Examples:

* To add an order to the buyer at index 1 of the display list: `add-o 1 o_st/Pending o_r/add-r o_a/1 o_sp/German shepherd o_c/golden o_cp/pure color o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* To add an order to the buyer at index 2 of the display list: `add-o 2 o_st/Negotiating o_r/add-r o_a/3 o_sp/chihuahua o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a pet to a supplier : `add-p`

Adds a pet to a supplier contact. This is especially useful when an existing supplier has a new pet for sale or when the supplier tells you what are the pets he/she owns some time after being added to the contacts.

Format: `add-p INDEX_OF_SUPPLIER p_n/PET_NAME p_d/DATE_OF_BIRTH p_c/COLOR p_cp/COLOR_PATTERN p_h/HEIGHT p_w/WEIGHT p_s/SPECIES p_v/VACCINATION_STATUS p_p/PRICE [p_cert/CERTIFICATE]…​`

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: `INDEX_OF_SUPPLIER` should be immediately after `add-p`.
Ensure that at the index is a supplier before executing this command,
which can be achieving by executing the [List Command](#listing-contacts-or-items--list) or [Find supplier command](#finding-a-supplier--find-s) beforehand.

</div>

Examples:

* To add a pet to the supplier at index 1 of the display list: `add-p 1 p_n/Kawaii p_d/2001-11-20 p_c/red p_cp/stripped p_h/39.5 p_s/Bengal cat p_v/true p_w/15.3 p_p/20 p_cert/GoodDog Cert p_cert/Royal Blood Cert`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** For more details on what each prefix represents, kindly refer to [List of Prefixes](#list-of-prefixes).

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a contact with a popup window : `add`

Adds a contact with a popup window that has prompt texts for what to input without the need to enter any
prefixes. This reduces the need to memorise prefixes.
Given below is the popup window for adding a supplier.

![pop up window for adding a supplier](images/AddSupplierWithPopup.png)

The followings are two ways to use this command:

* Adds a buyer to the contacts with or without any number of orders.

  Format: `add buyer`

* Adds a supplier to the contacts with or without any number of pets.

  Format: `add supplier`

<div markdown="1" class="alert alert-primary">

**:bulb: Tip:** **Useful keyboard shortcuts for the pop-up window:**

| Keyboard shortcut | Associated action                                                                      |
|:-----------------:|:---------------------------------------------------------------------------------------|
|      ESCAPE       | Closes the popup window **without saving**                                             |
|       ENTER       | Goes to the next input text field                                                      |
|     CTRL + A      | Adds an order/pet to the buyer/supplier                                                |
|     CTRL + D      | Deletes the last order/pet under the buyer/supplier in the popup window                |
|     CTRL + S      | Saves the inputs, adds the buyer/supplier to the contacts, and closes the popup window |
|     CTRL + D      | Deletes the last order/pet under the buyer/supplier in the pop-up window               |
|     CTRL + S      | Saves the inputs, adds the buyer/supplier to the contacts, and closes the pop-p window | <br> <br>

Note that some shortcuts are only **effective when a text field is in focus**.
When no text fields are highlighted (i.e. not in focus), **press TAB once (still no focus, press TAB again and again until the highlight appears)** to focus the cursor to a text field.
This ensures that you can use all the available shortcuts.

</div>

<div markdown="block" class="alert alert-info">

:information_source: **Note**: If a compulsory text field is ***empty*** or a text field ***starts with whitespace*** during saving, the cursor will be brought to that text field,
which will be highlighted in red.

</div>

<div markdown="block" class="alert alert-info">

:information_source:  **Note**: If the input of a text field is in the ***wrong format*** during saving, the person will not be added to
the contacts and the pop-up window will not close.
The error message and the correct format of the input will be shown in the **main window**.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: This command is only available for **adding a buyer or supplier** for the current version.
Note that the three buttons for adding pets to a supplier, which are `Upload photo`, `Upload pet certificate`, `Upload vaccination proof` (as seen in the image above),
only **open the file explorer** and **do nothing more**. You may be able to upload files from your local disk to the storage of PetCode in future versions.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Matching pets to an order : `match`

Matches the "best fit" pet to an order. This is especially useful when you receive an order
and want to find out **which pet(s)** on sale is the **best fit** (i.e. description of the pet matches as many requirements specified in the order as possible).
With this information, you may contact the suppliers who own these pets for further negotiation.

<div markdown="span" class="alert alert-info">

:information_source: **How does the match command work?** <br>

We have designed an algorithm to give each pet in the storage a score. Pets with descriptions that are closer to the requirements specified in the order will be given a higher score.
Pets with higher scores (i.e. more fitting to the order) are displayed on top. If you want to know how we design the algorithm, check out our [Developer Guide](DeveloperGuide.md). <br>

In the current version of PetCode, the score calculation in the algorithm uses a default set of weightages. In the future, you may be able to define your own
weightages for different parameters, such as price, age, species and so on.

</div>

Format: `match INDEX`

Example:
* To match the first order in the display list to pets in the storage: `match 1`

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Please ensure that at the index is an order, which can be achieved by executing the [List command](#listing-contacts-or-items--list) or [Filter order command](#filtering-orders--filter-o) beforehand.

</div>

Using the original set of sample data **without any modification**, the following picture shows the display list before the
match command is executed. The original order is `Shiro`, `Ashy`, `Plum`, `Page`, `Snowy`, and `Buddy`.

![before match command](images/BeforeMatch.png)

The following picture shows the display list after `match 1` is executed. Now the order is `Snowy`, `Page`, `Plum`
, `Ashy`, `Shiro`, and `Buddy`.

![after match command](images/AfterMatch.png)

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Deleting a contact or item : `delete`

Deletes a contact / item at the specified index of the respective contact / item list.

Format: `delete-KEY INDEX`

#### KEY Table

| Contact / Item to Delete | KEY |
|:------------------------:|:---:|
|          Buyer           |  b  |
|         Supplier         |  s  |
|        Deliverer         |  d  |
|          Order           |  o  |
|           Pet            |  p  |

Examples:
* `delete-b 1`, deletes `Buyer` contact at index 1 of the display list, if index is found.
* `delete-s 2`, deletes `Supplier` contact at index 2 of the display list, if index is found.
* `delete-d 1`, deletes `Deliverer` contact at index 1 of the display list, if index is found.
* `delete-o 1`, deletes `Order` at index 1 of the display list, if index is found.
* `delete-p 1`, deletes `Pet` at index 1 of the display list, if index is found.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Please ensure that you have the corresponding display list before you execute the delete command.
For example, if you want to delete a buyer by `delete-b 1`, do ensure at index 1 is a buyer.
This is to make sure you know what you are deleting and do not delete a contact or item by accident, since there is no `undo` command yet. <br>

 * To ensure at the index is a contact, use [List command](#listing-contacts-or-items--list) or [Find command](#finding-contacts-using-keywords--find). <br>
 * To ensure at the index is an item, use [List command](#listing-contacts-or-items--list) or [Filter command](#filtering-items-by-attributes--filter).

</div>

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** Want to delete an order or a pet when browsing through the list of buyer / supplier?
You **don't need to** list all orders / pets, find that particular order / pet and its index, and delete from there.
Instead, you can just use the [Check command](#checking-which-item-belongs-to-which-contact--check), `check buyer 1` for example, and delete from the order list of that buyer.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Editing attribute(s) of a contact : `edit`

Edits one or more attributes of a contact by the index number used in the displayed contacts list.
Existing values of that attribute will be overwritten by the input values.
Please provide **at least one** attribute.

Format: `edit-KEY INDEX [n/NAME] [ph/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/LOCATION]`

#### KEY Table

| Contact to Edit | Role |
|:---------------:|:----:|
|      Buyer      |  b   |
|    Deliverer    |  d   |
|    Supplier     |  s   |

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Please ensure that you have the corresponding display list before you execute the edit command.
For example, if you want to edit a buyer by `edit-b 1`, do ensure at index 1 is a buyer.

</div>

Examples:
* `edit-b 1 n/Alex`, modifies the name of the Buyer contact at index 1 of Buyer List to Alex, if index is found.
* `edit-s 3 n/Bobby ph/884321` modifies the name to Bobby and phone to 884321, of the Supplier contact at index 3 of Supplier List to Alex, if index is found.

<div markdown="span" class="alert alert-warning">

:exclamation: This command is only available for **editing the basic information** of a contact for the current version. In other words, **information regarding `Order`/`Pet`** that the `Buyer`/`Supplier` possess **cannot be modified**.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Finding contact(s) using keywords : `find`

Displays **all** contacts which match **one** specific attribute. This command is especially useful when you want to quickly
find contacts based on an attribute.

Format: `find PREFIX/ATTRIBUTE`

There are five possible attributes for finding contact(s):
address, email, location, name, phone. Please provide **one** out of the five when using this command.

#### Attributes and Their Corresponding Prefixes Table

| Attribute | Prefix | Format    | Example                |
|-----------|--------|-----------|------------------------|
| Address   | a      | a/KEYWORD | a/Wall Street          |
| Email     | e      | e/KEYWORD | e/whereisamy@gmail.com |
| Location  | l      | l/KEYWORD | l/Nova Scotia          |
| Name      | n      | n/KEYWORD | n/Amy Toh              |
| Phone     | ph     | ph/NUMBER | ph/81234567            |

Examples:

* `find a/6th College Ave West`, looks for and displays (if any) any person who lives at this address.
* `find e/blackball@furry.com`, looks for and displays (if any) any person who has this email address.
* `find ph/98986668`, looks for and displays (if any) any person whose phone number is that.

<div markdown="span" class="alert alert-info">

:information_source: **Notes**: <br>

 * Only **one** attribute is allowed. For example, `find a/6th College Ave West ph/98986668` and `find ph/98986668 ph/98986677` are not allowed.<br>
 * This command is case-insensitive, meaning `find a/Wall Street` is equivalent to `find a/wall street`. <br>
 * The above principles also apply to the sub-commands of `find` given below.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a buyer : `find-b`

Displays all buyers who match **one** specific attribute.

Format: `find-b PREFIX/ATTRIBUTE`

Check out the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information on prefixes and attributes.

Examples:

* `find-b a/6th College Ave West`, looks for and displays any `Buyer` who lives at this address.
* `find-b e/blackball@furry.com`, looks for and displays any `Buyer` who has this email address.
* `find-b ph/98986668`, looks for and displays any `Buyer` whose phone number is that.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a deliverer : `find-d`

Displays all deliverers who match **one** specific attribute.

Format: `find-d PREFIX/ATTRIBUTE`

Check out the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information on prefixes and attributes.

Examples:

* `find-d a/6th College Ave West`, looks for and displays any `Deliverer` who lives at this address.
* `find-d e/blackball@furry.com`, looks for and displays any `Deliverer` who has this email address.
* `find-d ph/98986668`, looks for and displays any `Deliverer` whose phone number is that.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a supplier : `find-s`

Displays all suppliers who match **one** specific attribute.

Format: `find-s PREFIX/ATTRIBUTE`

Check out the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information on prefixes and attributes.

Examples:

* `find-s a/6th College Ave West`, looks for and displays any `Supplier` who lives at this address.
* `find-s e/blackball@furry.com`, looks for and displays any `Supplier` who has this email address.
* `find-s ph/98986668`, looks for and displays any `Supplier` whose phone number is that.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Filtering items by attributes : `filter`

Displays items based on the specified attribute(s). This command is especially useful when you want to coordinate
sales between a Buyer and Supplier, by filtering out orders that are still "Pending", or filtering out pets with specific attributes.
Please provide **at least one** attribute when using this command.

<div markdown="span" class="alert alert-info">

:information_source: **What is the difference between find command and filter command?** <br>
 * Find command: Finds **contact(s)**; only **one** attribute is allowed. <br>
 * Filter command: Filters **item(s)**; **multiple** attributes are allowed.

</div>

<div markdown="span" class="alert alert-info">

:information_source: **Notes**: <br>

 * This command is case-insensitive, meaning `filter-o o_st/Pending` is equivalent to `filter-o o_st/pending`. <br>
 * Having multiple prefixes of the same type is allowed, but only the latest input will be taken.
  For example, `filter-o o_st/Pending o_st/Delivering` is equivalent to `filter-o o_st/Delivering`. <br>
 * When multiple attributes are given, items that fulfil **all** attributes are filtered out.

</div>

#### Filtering orders : `filter-o`

Displays Orders that satisfy the given attribute(s). There are three possible attributes to filter: additional
requests, order status, and price range.

Format: `filter-o PREFIX/ATTRIBUTE [PREFIX/ATTRIBUTE]…​`

| Attribute           | Prefix | Format                        | Example            |
|---------------------|--------|-------------------------------|--------------------|
| Additional requests | o_ar   | o_ar/KEYWORD                  | o_ar/free delivery |
| Order Status        | o_st   | o_st/KEYWORD                  | o_st/Negotiating   |
| Price Range         | o_pr   | o_pr/LOWER_BOUND, UPPER_BOUND | o_pr/100, 456      |

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution**: Order status can only be one of the following three: `Pending`, `Negotiating`, `Delivering`.

</div>

<div markdown="span" class="alert alert-info">

:information_source: Filtering based on price range will filter out orders with price ranges that are **subsets** of the price range specified by the user.
For example, the user input `100, 200` will filter out orders with price ranges `100, 150` and `180, 200`, but not `80, 140` and `175, 230`.

</div>

Examples:

* `filter-o o_st/Pending`, filters out orders with order status set to `Pending`
* `filter-o o_st/Negotiating o_pr/90, 900`, filters out orders with order status set to`Negotiating` and price ranges that are subsets of `90, 900`
* `filter-o o_ar/good o_st/Delivering o_pr/80, 100`, filters out orders with additional requests that contain the keyword `good`, order status set to `Delivering` and price ranges that are subsets of `80, 100`.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Filtering pets : `filter-p`

Displays Pets that satisfy the given attributes. There are five possible attributes to filter: color, name,
price, species, vaccination status.

Format: `filter-p PREFIX/ATTRIBUTE [PREFIX/ATTRIBUTE]…​`

| Attribute          | Prefix | Format      | Example        |
|--------------------|--------|-------------|----------------|
| Color              | p_c    | p_c/KEYWORD | p_c/pink       |
| Name               | p_n    | p_n/KEYWORD | p_n/snow white |
| Price              | p_p    | p_p/NUMBER  | p_p/209        |
| Species            | p_s    | p_s/KEYWORD | p_s/ostrich    |
| Vaccination Status | p_v    | p_v/KEYWORD | p_v/false      |

Examples:

* `filter-p p_c/white`, filters out pets of white color
* `filter-p p_c/black p_v/true`, filters out vaccinated pets of black color
* `filter-p p_c/grey p_n/doraemon p_p/50 p_s/cat p_v/true`, filters out vaccinated cats of grey color named doraemon sold at a price of $50

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Sorting contacts and items : `sort`

Sorts the specified list based on the default or given attribute(s) as sorting criteria.

Format: `sort KEY [ATTRIBUTE]…​`

#### KEY and ATTRIBUTE Table

| Contact / Item to Sort |     KEY      | Default Sorting Criteria | Possible ATTRIBUTE to Sort <br> (acceptable parameters)                                                                                                                                                           | Examples                                   |
|:----------------------:|:------------:|:------------------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------|
|         Buyer          |   buyer, b   |     Number of orders     | Name (name, n), <br> Phone (phone, ph), <br> Email (email, e), <br> Location (location, l), <br> Address (address, a), <br> Number of Orders(order) <br>                                                           | `sort buyer name`, <br> `sort b p n`       |
|        Supplier        | supplier, s  |      Number of pets      | Name (name, n), <br> Phone (phone, ph), <br> Email (email, e), <br> Location (location, l), <br> Address (address, a), <br> Number of Pets(pet) <br>                                                              | `sort supplier e`, <br> `sort s address`   |
|       Deliverer        | deliverer, d |           Name           | Name (name, n), <br> Phone (phone, ph), <br> Email (email, e), <br> Location (location, l), <br> Address (address, a), <br> Number of Orders(order) <br>                                                           | `sort d location`,  <br>`sort deliverer n` |
|         Order          |   order, o   |         Due date         | Due Date (duedate, d), <br> Price Range (pricerange, pr), <br> Settled Price (price, p), <br> Order Status (orderstatus, os) <br>                                                                                 | `sort order pr`, `sort o d p os`           |
|          Pet           |    pet, p    |          Price           | Price (price, p), <br> Name (name, n), <br> Color (color, c), <br> Color Pattern (colorpattern, cp), <br> Birth Date (birthdate, bd), <br> Species (species, s), <br> Height (height, h), <br> Weight (weight, w) | `sort pet color`, `sort p s cp`            |

<div markdown="span" class="alert alert-info">

:information_source: When no attributes are specified as sorting criteria, the list will be sorted based on the default sorting criteria given above.
When multiple attributes are provided, the list will be first sorted based on the first attribute, and then based on the second, and so on. For example, `sort pet price height` sorts the pet list by price first. Pets with the same price are sorted by their height.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: This command sorts the specified list in **ascending** order. For example, it will display the buyer with the **lowest** number
of orders at the **top** of the buyer list and the buyer with the **highest** number of orders at the **bottom** of the buyer list.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Clearing all entries : `clear`

Clears all entries from the PetCode.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** Be careful when using this command as there is no `undo` command implemented yet.

</div>

Format: `clear`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Automation of information flow `[coming in v2.0]`

In future versions of PetCode, some information will be auto-updated. For example, you will be able to upload vaccination proof and pet certificates to PetCode from your local disk.
`Vaccination status` will be set to `true` once the system detects the vaccination proof file, and `pet certificates` will be set to the titles of the pet certificate documents uploaded.
You will also be able to transfer orders from buyers to suppliers and then to deliverers. The `order status` will be auto-updated from `Pending` to `Negotiating` to `Delivering` in the process.

This is the reason why you are unable to modify some information or interact with certain UI elements, and why some information, although you can enter and store in PetCode, is not displayed in the UI, in the current version.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

## How data is stored
### Saving the data

PetCode data is saved into your computer's hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file

PetCode data is saved as a JSON file `[JAR file location]/data/addressbook.json`.
Advanced users are welcome to update the data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** Please do not edit the id and ids that are stored in the data file. These ids acted as primary keys and foreign keys and are used to recognise the relationship between order/pet and buyer/supplier.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** If your changes to the data file makes its format invalid, PetCode will discard all data and
start with an empty data file at the next run. For certain data changes, PetCode will discard the change and initialise the respective data value to its default value.

</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous PetCode home folder.

**Q**: What is being the same? Why does the app tell me that the buyer/deliverer/supplier already exits in the list? How
are contacts and items considered as duplicates?<br>
**A**: Unfortunately, we do not allow duplicate contacts or items in our app, otherwise you may mistakenly modify a
person that you don't intend to! For buyer/deliverer/supplier, if they are of the same person category and have the same
name and the same email address, they are considered as the same. However, we do allow a buyer and a deliverer to have
exactly the same name and the same email, even all attributes being identical. That is, we allow a person to have more
than one role in our app.
Two pets are considered as the same if they have all the same attributes. Two orders are different even they have all
the same attributes.
For example, `"John"` is the same as `"John"`, not the same as `"john"`, `"  john   "`, or `"jOhn"`.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## Summaries
### List of Prefixes

These prefixes are for you to indicate different parameters when you add a new [buyer](#adding-a-buyer-add-b), a new [deliverer](#adding-a-deliverer-add-d), a new [supplier](#adding-a-supplier-add-s), a new [order](#adding-an-order-to-a-buyer-add-o), or a new [pet](#adding-a-pet-to-a-supplier--add-p).

| Prefix    | Category        | Meaning                           | Usage                                                                                                                                                                                                                                                                                                                                       | Example                                         |
|-----------|-----------------|-----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------|
| `n/`      | General Person  | Name                              | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `n/John Halstead`                               |
| `ph/`     | General Person  | Phone number                      | Numbers only. Required.                                                                                                                                                                                                                                                                                                                     | `ph/80334455`                                   |
| `e/`      | General Person  | Email address                     | A string of characters. Must contain `@` and follow email format. Required.                                                                                                                                                                                                                                                                 | `e/1324@sample.com`                             |
| `a/`      | General Person  | Address                           | A string of any characters. Required.                                                                                                                                                                                                                                                                                                       | `a/36 College Ave East, Singapore 138600`       |
| `l/`      | General Person  | Location (country) of this person | A string of alphanumeric characters. Required.                                                                                                                                                                                                                                                                                              | `l/Singapore`, `USA`, `China`                   |
| `o/`      | Order           | Order                             | Always followed by `add-o`. Optional, if no orders to add when adding a buyer. Can have multiple.                                                                                                                                                                                                                                           | `o/add-o`                                       |
| `o_st/`   | Order           | Order status                      | `Pending`, `Negotiating`, or `Delivering`                                                                                                                                                                                                                                                                                                   | `o_st/Pending`                                  |
| `o_p/`    | Order           | Settled price                     | A non-negative decimal number. Use `-1` to indicate not settled price. Must be within the price range. Required.                                                                                                                                                                                                                            | `o_p/38.6`                                      |
| `o_pr/`   | Order           | Acceptable price range            | This is for you to use when negotiating with buyer -- the range the price is expected  to fall within. Two non-negative decimal numbers, separated by a comma `,`. The first must not be greater than the second unless the second one is `-1`, which is used to indicate that you haven't settled down one or two of the bounds. Required. | `o_pr/-1, -1`, `o_pr/2.9, -1`, `o_pr/4.3, 19.5` |
| `o_d/`    | Order           | Transaction (scheduled) date      | In the format `yyyy-MM-dd`.                                                                                                                                                                                                                                                                                                                 | `o_d/2022-10-28`, `o_d/2022-9-2`                |
| `o_r/`    | Order (Request) | Order request                     | Always followed by `add-r`. The Request group of prefixes describe what kind of pet this order seeks. Required.                                                                                                                                                                                                                             | `o_r/add-r`                                     |
| `o_a/`    | Order (Request) | Requested pet age                 | A non-negative whole number. Required.                                                                                                                                                                                                                                                                                                      | `o_a/5`                                         |
| `o_sp/`   | Order (Request) | Requested pet species             | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `o_sp/Chihuahua`, `o_sp/German shepherd`        |
| `o_c/`    | Order (Request) | Requested pet color               | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `o_c/red`                                       |
| `o_cp/`   | Order (Request) | Requested pet color pattern       | A string of alphanumeric and characters and whitespaces. This describes the appearance of the pet in more detail. Required.                                                                                                                                                                                                                 | `o_cp/white stripped`, `o_cp/black dotted`      |
| `o_ar/`   | Order           | Additional request of the order   | A string of alphanumeric and characters and whitespaces. Optional. Can have multiple.                                                                                                                                                                                                                                                       | `o_ar/free delivery`, `o_ar/arrive in 10 days`  |
| `p/`      | Pet             | Pet                               | Always followed by `add-p`. Optional, if no orders to add when adding a supplier. Can have multiple.                                                                                                                                                                                                                                        | `p/add-p`                                       |
| `p_n/`    | Pet             | Pet name                          | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `p_n/Page`                                      |
| `p_s/`    | Pet             | Species                           | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `p_s/Chihuahua`, `p_s/German shepherd`          |
| `p_d/`    | Pet             | Date of birth of the pet          | In the format `yyyy-MM-dd`. Cannot be a date in future. Required.                                                                                                                                                                                                                                                                           | `p_d/2020-3-29`                                 |
| `p_c/`    | Pet             | Color                             | A string of alphanumeric and characters and whitespaces. Required.                                                                                                                                                                                                                                                                          | `p_c/blue`                                      |
| `p_cp/`   | Pet             | Color pattern                     | A string of alphanumeric and characters and whitespaces. This describes the appearance of the pet in more detail. Required.                                                                                                                                                                                                                 | `p_cp/blue grids`                               |
| `p_h/`    | Pet             | Height                            | A non-negative decimal number. The unit is cm. Required.                                                                                                                                                                                                                                                                                    | `p_h/33.2`                                      |
| `p_w/`    | Pet             | Weight                            | A non-negative decimal number. The unit is kg. Required.                                                                                                                                                                                                                                                                                    | `p_w/58.2`                                      |
| `p_p/`    | Pet             | Price                             | A non-negative decimal number. This is the price the pet is to be sold at. Use `-1` to indicate not settled price. Required.                                                                                                                                                                                                                | `p_p/55.5`                                      |
| `p_v/`    | Pet             | Vaccination status                | `true` if the pet is vaccinated, otherwise `false`. Required.                                                                                                                                                                                                                                                                               | `p_v/false`                                     |
| `p_cert/` | Pet             | Certificate                       | A string of alphanumeric and characters and whitespaces. Other certificates this pet holds. Optional. Can have multiple.                                                                                                                                                                                                                    | `p_cert/US certified`, `p_cert/noble blood`     |

<div markdown="span" class="alert alert-info">

:information_source: `-0` is also considered as negative.

</div>

### Command Summary

|                                   Action                                   | Format                                                                                    | Examples                                                                |
|:--------------------------------------------------------------------------:|-------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
|                 **[Add](#adding-a-contact-or-item--add)**                  | `add-ROLE n/NAME b/BREED ph/PHONE_NUMBER e/EMAIL a/ADDRESS l/LOCATION`                    | `add-b n/Hongyi ph/11223344 e/hhygg@u.nus.edu a/UTR 138600 l/Singapore` |
| **[Add](#adding-a-contact-with-a-popup-window--add)** (using popup window) | `add buyer`, `add supplier`                                                               |                                                                         |
|     **[Check](#checking-which-item-belongs-to-which-contact--check)**      | `check KEY INDEX`                                                                         | `check buyer 1`                                                         |
|                 **[Clear](#clearing-all-entries--clear)**                  | `clear`                                                                                   |                                                                         |
|             **[Delete](#deleting-a-contact-or-item--delete)**              | `delete-KEY INDEX`                                                                        | `delete-b 1`, `delete-d 2`, `delete-s 3`, `delete-o 1`, `delete-p 2`    |
|             **[Edit](#editing-attributes-of-a-contact--edit)**             | `edit-KEY INDEX [n/NAME] [ph/PHONE] [e/EMAIL] [a/ADDRESS] [l/LOCATION]`                   | `edit-b 1 n/Alex`, `edit-s 3 n/Bobby ph/884321`                         |
|             **[Find](#finding-contacts-using-keywords--find)**             | `find PREFIX/ATTRIBUTE`                                                                   | `find n/James Jake`                                                     |
|                 **[Find Buyer](#finding-a-buyer--find-b)**                 | `find-b PREFIX/ATTRIBUTE`                                                                 | `find-b n/James Jake`                                                   |
|             **[Find Deliverer](#finding-a-deliverer--find-d)**             | `find-d PREFIX/ATTRIBUTE`                                                                 | `find-d n/James Jake`                                                   |
|              **[Find Supplier](#finding-a-supplier--find-s)**              | `find-s PREFIX/ATTRIBUTE`                                                                 | `find-s n/James Jake`                                                   |
|              **[Filter Orders](#filtering-orders--filter-o)**              | `filter-o PREFIX/ATTRIBUTE`                                                               | `filter-o o_ar/non-allergic o_pr/10-100`                                |
|                **[Filter Pets](#filtering-pets--filter-p)**                | `filter-p PREFIX/ATTRIBUTE`                                                               | `filter-p p_c/white p_s/capybara`                                       |
|                      **[Help](#viewing-help--help)**                       | `help`                                                                                    |                                                                         |
|                **[List](#listing-contacts-or-items--list)**                | `list all`, `list buyer`, `list supplier`, <br>`list deliverer`, `list order`, `list pet` |                                                                         |
|                    **[Sort](#sorting-contacts--sort)**                     | `sort KEY [ATTRIBUTE]…​`                                                                  | `sort pet price height weight`                                          |

[Go back to [Table of Contents](#table-of-contents)]
