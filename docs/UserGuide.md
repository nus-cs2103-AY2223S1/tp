---
layout: page
title: User Guide
---
Ever forgot a deadline? Or lost track of your customers? Many commission-based artists struggle to organise
their business. With so many things to keep track of, you might have found yourself wondering if there was a
better way to manage all of these.

That's why we built **ArtBuddy** (AB), a commission-based artist's best buddy, just for you. Developed with your
needs in mind, ArtBuddy is here to help you manage all your customers and commissions in one place. But that's
not all. With integrated support for tracking the progress of your commissions through iterations,
and generation of statistics, ArtBuddy is also here to help you grow as an artist.

So, focus on your art and leave the rest to ArtBuddy.

Just getting started with ArtBuddy? We recommend you check out the [Using this guide](#using-this-guide) section
to.
Eager to get started? You can refer to our [Quick Start](#quick-start) section to set ArtBuddy up, or
you can jump right into our [Features](#features) section to learn more about the features ArtBuddy offers.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# Using this guide

This guide walks you through all the features of ArtBuddy and can be used as a quick reference whenever you need any help.

If you're just getting started with ArtBuddy, we welcome you to start from our [Introduction](#introduction) section to
learn more about ArtBuddy. Or, if you're eager to hop right into setting up ArtBuddy, you might find the [Quick Start](#quick-start) section helpful.

Already set up ArtBuddy? You can check out our [Features](#features) section. For ease of reference, the
[Features](#features) section is divided into subsections corresponding to each main feature:
Customers, Commissions, Iterations and Statistics. Feel free to jump around as you explore ArtBuddy's amazing features!

You can also refer to our [Table of Contents](#table-of-contents) to navigate between the different sections of this guide.

### Conventions used in this guide

**Formatting conventions**  
As you read through this guide, you might come across text with special formatting. These specially formatted
text hold meaning, as described below. 

|                                    | Description                                                                                                                                                                                             |
|------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `code`                             | ArtBuddy commands appear in a monospaced font, with a grey background, as shown on the left. To learn more about ArtBuddy commands, refer to the [CLI section](#artbuddys-command-line-interface-cli).  |
| `CODE_UPPER_CASE`                  | Certain ArtBuddy commands contain words appear in upper case. These refer to [parameters](#command-parameters) (user input) to be supplied to ArtBuddy.                                                 |
| <code><em>code_italics</em></code> | ArtBuddy commands may also contain words that appear in italics. These refer to example command [parameters](#command-parameters), which you can replace with your own inputs.                          |
| [blue hyperlink]()                 | Hyperlinks appear in a blue font that is underlined when hovered over by your mouse. These hyperlinks bring you to another section of the user guide containing more relevant information to the topic. |
| **Bold Title Case**                | References to elements in ArtBuddy's [GUI](#glossary) (like buttons and text field labels) are formatted in **bold**, in Title Case.                                                                    |


**Conventions used in command formats**  
When talking about ArtBuddy command formats, we've adopted the following conventions to make explanations
more succinct. If you're not familiar with ArtBuddy's CLI, you might want to look at our
[ArtBuddy's CLI](#artbuddys-command-line-interface-cli) section to learn more.

|                                                         | Description                                                                                                                                                                                                                                                    | Example                                                                                                                                                                                                                                                                                           |
|---------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Optional command parameters                             | Certain ArtBuddy commands have optional command parameters. These command parameters may be specified, or be left out. You'll be able to identify optional command parameters as they will appear around square brackets when specified in the command format. | For instance, the command format of the Add Customer command is `addcus n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]...`. Notice that the address and tag command parameters appear around square brackets? That just means that you can choose whether to specify these command parameters! |
| Command parameters that can be specified more than once | You may also notice that certain command have `...` after certain command parameters. This simply means indicates that the input parameter be used multiple times (including zero times).                                                                      | Following the example of the Add Customer example above, where you'll notice the tag command parameter `[t/TAG]...` is followed by `...`, this just means that the tag command input can be specified any number of times!                                                                        |

**Call-out text box conventions**  
You might also notice call-out text boxes in this guide. These contain additional information tidbits that might be useful
for you.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** Call-outs like this contain information that you should know about ArtBuddy!
</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** Call-outs like this contain tips that you might find useful when using ArtBuddy.
</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** Call-outs like this contain warnings that we recommend you read through to avoid
any unexpected behaviour of ArtBuddy.
</div>

<div align="right">
<br>

[ <a href="#using-this-guide">To start section: Using this guide</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# Introduction

Made for commission-based artists, ArtBuddy is the easiest way to organise your customers and commissions.
Powerful features and intuitive design, all packaged into one neat desktop app. ArtBuddy is supported on all
major operating systems (Windows, macOS, Linux).

{: .no_toc}
#### Let ArtBuddy help you...

1. [**Manage your customers**](#customer-related-commands)  
ArtBuddy lets you store, edit and delete your customer's information. Consolidate all your customers'
contact details, so you never have to lose a contact again.  


2. [**Take charge of your commissions**](#commission-related-commands)  
Add commissions to a customer in ArtBuddy, just like in real life! ArtBuddy can store your
commission details, like fees, deadlines, and completion statuses. Easily update and delete
commissions as you wish.  


3. [**Keep track of your progress**](#iteration-related-commands)  
ArtBuddy wants you help you hone your craft, and learning from yourself is a critical part of that.
With ArtBuddy, keep track of the progress of your commissions using iterations. Each iteration is simply 
a version of your artwork. Learn from the feedbacks and your past work to continuously improve your craft.  


4. [**Supercharge your business**](#statistical-commands)  
Learn where your highest sales are coming from by leveraging on the powerful statistics ArtBuddy offers
so that you can accelerate your business growth.

{: .no_toc}
#### Getting things done fast
We get that your time is precious. So spend it on what you love doing, your art, and let ArtBuddy handle the
rest. With optimised support for use via a Command Line Interface (CLI), ArtBuddy can help you manage your
small business at the speed of your fingertips.

Not sure what a CLI is? Don't worry! It's easy to pick up, you can even refer to our short introduction to
[ArtBuddy's CLI](#artbuddys-command-line-interface-cli) to learn more.

More of a visual person? Don't sweat it! Unlike many CLI applications, ArtBuddy still has a sleek, intuitive
[Graphical User Interface (GUI)](#glossary). This means that you can also interact with ArtBuddy using visual controls
like buttons and pop-ups. There's something for everyone!

<div align="right">
<br>

[ <a href="#introduction">To start section: Introduction</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
# Quick start

Let's get you started with ArtBuddy! This section will help you get oriented with ArtBuddy, from installation
to a quick tutorial. Users already experienced with CLI might want to skip over the CLI section. So, what are you
waiting for? Let's go, go, go!

[Installation instructions](#installation-instructions)  
[Starting up ArtBuddy](#starting-up-artbuddy)  
[Starting up ArtBuddy (on macOS)](#starting-up-artbuddy-macos)  
[Getting familiar with ArtBuddy's interface](#getting-familiar-with-artbuddys-interface)  
[ArtBuddy's CLI](#artbuddys-command-line-interface-cli)  
[Quick tutorial](#quick-tutorial)

## Installation instructions

Don't have ArtBuddy installed on your computer yet? Check out our step-by-step installation instructions to
download ArtBuddy.

1. ArtBuddy is written in the programming language Java, so you'll need **Java 11 or above** installed in your computer
to run ArtBuddy.
   1. Not sure if you have a compatible Java version installed on your computer?
   Windows or macOS users might find [this guide](https://blog.hubspot.com/website/check-java-verison) useful.
   While Linux users can refer to [this guide](https://phoenixnap.com/kb/check-java-version-linux).
   2. Don't have a compatible Java version installed? Fret not, Java is free to install! Complete installation
   instructions can be found [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).

2. Download the latest version of ArtBuddy by downloading the `artbuddy.jar` file found [here](https://github.com/AY2223S1-CS2103T-W11-3/tp/releases).

![Download JAR](images/ab-quick-start-download.png)

4. Create a new empty folder (with any name you like) in your computer where you'd like to store ArtBuddy.

5. Copy the downloaded ArtBuddy file (`artbuddy.jar`) into the new folder.

6. And...that's it! You now have ArtBuddy installed on your laptop!

<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

## Starting up ArtBuddy

Nice! Now that you've downloaded ArtBuddy (if not, our [installation instructions](#installation-instructions)
might be useful), let's get ArtBuddy up and running.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** Due to the default privacy settings on macOS, certain features of ArtBuddy
may not work as expectedly. To avoid this, please refer to the section
[Starting up ArtBuddy (macOS)](#starting-up-artbuddy-macos) instead if you are a macOS user.
</div>

1. To start ArtBuddy, simply open up the folder where your downloaded `artbuddy.jar` file is and double-click the file to start the app.
2. And...ta-da! You should now see ArtBuddy running on your computer!

<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

## Starting up ArtBuddy (macOS)

Due to the default privacy settings on macOS computers, ArtBuddy might not be able to read from or write to
files on your macOS computer. Because of this, some features like uploading images to ArtBuddy might not
work properly. To get ArtBuddy working properly on your macOS computer, follow these instructions instead
to get ArtBuddy up and running!

1. Locate the folder where your downloaded `artbuddy.jar` file is stored, and right click (or do a 2-finger press on
your Apple trackpad) on your folder. Click on the **Services** option in the menu that pops up.
2. This should reveal a **New Terminal at Folder** option, as shown below.

![Starting up ArtBuddy (macOS) Step 1](images/start-ab-mac-step1.png)

3. Click on the **New Terminal at Folder** option. You should see a Terminal window open. The Terminal is
just macOS's own CLI that will allow you to communicate with your macOS computer.
4. To let your computer know you want to run ArtBuddy, simply paste this command: `java -jar artbuddy.jar` into
the Terminal window and hit **return** on your keyboard.

![Starting up ArtBuddy (macOS) Step 1](images/start-ab-mac-step2.png)

5. And...ta-da! You should now see ArtBuddy running on your computer!

<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

## Getting familiar with ArtBuddy's interface

Cool! You now have ArtBuddy up and running. Let's get you familiar with ArtBuddy's user interface.

ArtBuddy has two tabs: a [**Customers Tab**](#customers-tab), and a [**Commissions Tab**](#commissions-tab). Let's take a closer look at each tab individually.

{: .no_toc}
### Customers Tab

When you first open ArtBuddy, ArtBuddy will have the **Customers Tab** open by default. As its name implies,
the **Customers Tab** shows all your customer related information. Let's learn what each of these ArtBuddy components
in your **Customers Tab** does!

![ArtBuddy's UI](images/ab-user-interface.png)

|     | Component        | Description                                                                                                                                                                                                 |
|-----|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | Menu Bar         | The menu bar contains menu options to [exit](#exiting-the-program-exit) from ArtBuddy and to get [help](#viewing-help-help).                                                                                |
| 2   | Command Input    | The command input is where you can key in your commands to tell ArtBuddy what you want to get done. To learn more about ArtBuddy's CLI, refer to the [next section](#artbuddys-command-line-interface-cli). |
| 3   | Result Display   | The result display is where you'll see ArtBuddy's responses to your commands.                                                                                                                               |
| 4   | Customer List    | The default tab opened when you first start ArtBuddy is the customer tab. Notice how the **Customer** tab is highlighted? That indicates that ArtBuddy is currently showing you your list of Customers.     |
| 5   | Customer Details | When you select a customer from your **Customer List**, ArtBuddy will show you the details and [statistics](#statistics-view) of your selected customer in this pane.                                       |
| 6   | Saved Data File  | For advanced users, the [file path](#glossary) displayed here is where ArtBuddy stores your saved data (which you can [edit](#editing-the-data-file)).                                                      |

<div id="statistics-view"></div>

**Statistics View**

Let's take a closer look at the customer statistics ArtBuddy offers. Analyse your revenue streams and keep your top customers happy!

![Statistics View](images/statistics%20view.png)

|     | Component                       | Description                                                                                                        |
|-----|---------------------------------|--------------------------------------------------------------------------------------------------------------------|
| 1   | Total Revenue                   | Displays the total revenue you've generated from this customer.                                                    |
| 2   | Total Commission Count          | Displays the total number of commissions received from this customer.                                              |
| 3   | Commission Breakdown Pie Chart  | Provides a visual breakdown of the customer's commissions by their completion statuses in the form of a pie chart. |
| 4   | Commission Breakdown            | Breaks down the commissions made by the customer by their completion statuses.                                     |

<div style="page-break-after: always;"></div>

{: .no_toc}
### Commissions Tab

Notice that ArtBuddy also has a **Commissions Tab**? When you navigate to the **Commissions Tab**, this is what you'll see.
As you might've already guessed it, ArtBuddy's **Commissions Tab** shows all commission-related information!

![ArtBuddy's UI](images/ab-user-interface-commission-tab.png)

|     | Component          | Description                                                                                                                                                                                                 |
|-----|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | Commission List    | By default, all the commissions of your currently selected customer will be displayed in the Commission List here. If no customer is currently selected, all commissions stored in ArtBuddy will be shown.  |
| 2   | Commission Details | When you select a commission from your **Commission List**, ArtBuddy will show you the details of your selected commission in this pane.                                                                    |
| 3   | Iteration List     | Within the **Commission Details** pane, ArtBuddy will also display all your commission's iterations here.                                                                                                   |

<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

<div style="page-break-after: always;"></div>
## ArtBuddy's Command Line Interface (CLI)

ArtBuddy also has a Command Line Interface (CLI). This just means that besides interacting with ArtBuddy by
clicking on buttons, you can also tell ArtBuddy what you want to get done just by using text input (known as commands). Cool right?

Right now, ArtBuddy only understands certain commands (the complete list of commands can be found in
[Features](#features)). These commands must be formatted in a specific way. It's just the way ArtBuddy
is wired to think and listen. So let's learn how to talk to ArtBuddy!

{: .no_toc}
### Command Input  

The **Command Input** is where ArtBuddy's 'ears' are. This is the rectangular text input box outlined in red below.
To type in commands into the **Command Input**, just click on the **Command Input** and start typing away! Once you've
typed in your command, just press 'Enter' (or 'return' for Mac users) on your keyboard and... voilà!
ArtBuddy has just executed your command. It's that easy!

![ArtBuddy's UI](images/ab-cli-command-input.png)
<div style="page-break-after: always;"></div>

{: .no_toc}
### ArtBuddy Commands

An ArtBuddy command is typically made up of the following parts:

{: .no_toc}
##### Command keyword  
This is the keyword that tells ArtBuddy exactly what command you wish to execute. All
ArtBuddy commands have a unique command keyword so ArtBuddy knows what you want to do.  

{: .no_toc}
##### Index  
Some ArtBuddy commands have an [index](#glossary), this simply a number that uniquely identifies the
customer, commission, or iteration that you wish to execute your command on. A customer's or commission's index is
simply the number that appears beside your customer's name, or commission's title.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** For ArtBuddy to understand what you mean, all ArtBuddy commands must begin
with their command keyword. Also, if an ArtBuddy command accepts an index, **the index must appear immediately
after the command keyword**.
</div>

{: .no_toc}
##### Command parameters  
Some ArtBuddy commands also accept command parameters. These are just additional
inputs that ArtBuddy requires to execute some commands properly. For instance, to add a customer, ArtBuddy
needs to know the name of the customer which you'll need to pass into ArtBuddy as a command parameter.
<br><br>For ArtBuddy to understand these command parameters, you need to attach a ['prefix'](#glossary) in front of
each of these parameters.

<div markdown="block" class="alert alert-info">

:information_source: **Note:** Advanced details on ArtBuddy command parameters can be found in
[Appendix: Further notes on command parameters](#further-notes-on-command-parameters).
</div>


<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

<div style="page-break-after: always;"></div>
## Quick tutorial
Itching to get started with ArtBuddy? Let's run you through some simple commands to get you warmed up
before you dive right into ArtBuddy's full feature list!

<div markdown="block" class="alert alert-info">
:information_source: Note that the images used in this section are only meant to be used as references.
ArtBuddy might appear differently on your computer because as the data shown in this sample reference may differ from
the data stored in your application.
</div>

Let's start by getting our hands cracking with some simple customer-related commands! Make sure the
[**Customer Tab**](#customers-tab) is open before trying out the following steps so you can observe what happens!

![ArtBuddy's UI](images/ab-quick-start-customer-tab.png)

1. Suppose you've just received a new customer, Betsy, and you want to add her into ArtBuddy. Easy! Try
running the command <code>addcus n/Betsy Crowe e/betsycrowe@example.com p/1234586</code>.
   1. You'll notice that ArtBuddy has just created a new customer, Betsy Crowe!

![ArtBuddy's UI](images/ab-quick-start-addcus.png) 

{:start="2"}
2. Now suppose you want to delete a customer, Bernice, because she has decided to cancel her commission. This time, try
running <code>delcus 2</code>. 
   1. Ta-da! After executing the command, Bernice is now deleted from ArtBuddy.

![ArtBuddy's UI](images/ab-quick-start-delcus.png)

Now that you've been introduced to some customer-related commands, let's change things up by checking out some
commission commands.

![ArtBuddy's UI](images/ab-quick-start-commission-tab.png)

{:start="3"}
3. Suppose you want to manage Betsy's commissions. Let ArtBuddy know this by first 'opening' up Betsy's details in the
**Customers Tab**. You can do this by clicking on Betsy's entry in the **Customer List**, or by executing the command `opencus 2`.
   1. You should notice that Betsy's details appear in the **Customer Details** pane as shown below.

![ArtBuddy's UI](images/ab-quick-start-opencus.png)

{:start="4"}
4. Now, switch to the **Commissions Tab** where all of Betsy's commissions are displayed. This tab should be empty
right now because Betsy has no commissions yet. Let's change that!

5. Try running the command <code>addcom n/Fate Archer f/60 d/2022-10-15 s/true p/Archery t/night t/city</code>.
   1. Nice! Betsy should now have a new commission titled "Fate Archer". You should also see the details of the newly
   created commission appear in the **Commission Details** pane.

![ArtBuddy's UI](images/ab-quick-start-addcom.png)

Good job! Now that you're getting the hang of things, check out our [Features](#features) section to explore the full
capabilities of ArtBuddy.

<div align="right">
<br>

[ <a href="#quick-start">To start section: Quick start</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

# Features
Dive right into ArtBuddy's full list of features. This section guides you through all of ArtBuddy's commands.

For ease of reference, similar features are grouped into the individual subsections listed below:
* [Customer related commands](#customer-related-commands)
* [Commission related commands](#commission-related-commands)
* [Iteration related commands](#iteration-related-commands)
* [Statistical commands](#statistical-commands)
* [Miscellaneous](#miscellaneous)


## Customer related commands

Customers are at the heart of ArtBuddy. ArtBuddy will help you keep track of all your customers and their
details so you never have to lose a contact again.

ArtBuddy offers the following commands to help you manage your customers:
* [Viewing a customer: `opencus`](#viewing-a-customer-opencus)
* [Adding a customer: `addcus`](#adding-a-customer-addcus)
* [Editing a customer: `editcus`](#editing-a-customer-editcus)
* [Deleting a customer: `delcus`](#deleting-a-customer-delcus)

Customers stored in ArtBuddy have the following details:

| Detail           | Explanation                                                                                                        | Constraints                                                                                         |
|------------------|--------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| Name             | The name of the customer ([case sensitive, and must be unique](#unique-customer))                                  | Alphanumeric and space characters                                                                   |
| Phone number     | The phone number of the customer                                                                                   | At least 3 digits                                                                                   |
| Email            | The email of the customer                                                                                          | Email format with at sign and domain name                                                           |
| Optional address | The address of the customer                                                                                        | See [Appendix: Limitations of no-constraint text inputs](#limitations-of-no-constraint-text-inputs) |
| Optional tags    | A customer may be tagged with any number of tags (including 0) which can be used to help categorise your customers | Each tag at most 25 alphanumeric and space characters                                               |                                        

### Viewing a customer: `opencus`

Opens a customer at `INDEX` and shows customer details with [various analytics](#statistics-view) you can use. The commissions tab will be updated to show the commissions made by the customer you selected. If no `INDEX` is provided, you will just switch to the customers tab.

Format: `opencus [INDEX]`

Examples:
* <code>opencus <em>2</em></code>
  Shows details about the second customer and updates the commissions tab with the second customer's commissions.

![opencus](images/opencus.png)

### Adding a customer: `addcus`

Adds a customer to ArtBuddy.

Format: `addcus n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]...​`

Examples:
* <code>addcus n/<em>John Doe</em> p/<em>98765432</em> e/<em>johnd@example.com</em> a/<em>John street, block 123, #01-01</em> t/<em>animal cartoons</em> t/<em>vip</em></code>
Creates the customer entry for John Doe with his details including multiple tags.
* <code>addcus n/<em>Betsy Crowe</em> e/<em>betsycrowe@example.com</em> p/<em>12345867</em></code>
Creates the customer entry for Betsy Crowe with her email and phone number.

![addcus](images/addcus.png)

### Editing a customer: `editcus`

Edits the details of the customer at `INDEX`.

Format: `editcus INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`
* At least one field to edit must be provided.
* For tags, a replacement mechanism is in place. This means if you want to add a new tag `VIP` and retain the original tag `friend`, both must be included in the command, e.g. `editcus 2 t/VIP t/friend`.

Examples:
* <code>editcus <em>1</em> p/<em>91234567</em> e/<em>johndoe@example.com</em></code> Edits the first customer's phone number and email.

![editcus](images/editcus.png)

### Deleting a customer: `delcus`

Deletes the customer at `INDEX` from ArtBuddy.

Format: `delcus INDEX`

Examples:
* <code>delcus <em>2</em></code> Deletes the 2nd customer in the ArtBuddy and all commissions made by the customer.

![delcus](images/delcus.png)

<div align="right">
<br>

[ <a href="#customer-related-commands">To start of Customer related commands</a> | <a href="#features">To start section: Features</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>
<div style="page-break-after: always;"></div>

## Commission related commands

Being able to keep track of your customers is great, but that's not enough. Ever missed
a deadline for a commission? Well, not anymore. With ArtBuddy's ability to help you track
commissions made by each customer, say goodbye to missed deadlines.

ArtBuddy offers the following commands to help you manage your commissions:
* [Viewing a commission: `opencom`](#viewing-a-commission-opencom)
* [Adding a commission: `addcom`](#adding-a-commission-addcom)
* [Editing a commission: `editcom`](#editing-a-commission-editcom)
* [Deleting a commission: `delcom`](#deleting-a-commission-delcom)

Commissions stored in ArtBuddy have the following details:

| Detail               | Explanation                                                                                                            | Constraints                                                                                                                                                                               |
|----------------------|------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Title                | The name of the commission ([case sensitive, and must be unique](#unique-commission))                                  | Alphanumeric and space characters                                                                                                                                                         |
| Deadline             | The due date of the commission                                                                                         | [`YYYY-MM-DD`](#further-notes-on-command-parameters) format (four-digit year, followed by a 2-digit month, and 2-digit day number; so "7 November 2022" should be passed as `2022-11-07`) |
| Status               | The completion status of the commission                                                                                | `y`, `yes`, `t`, `true` for complete status <br> `n`, `no`, `f`, `false` for incomplete status                                                                                            |
| Optional description | A description of the commission (for instance, the commission requirements)                                            | See [Appendix: Limitations of no-constraint text inputs](#limitations-of-no-constraint-text-inputs)                                                                                       |
| Optional tags        | A commission may be tagged with any number of tags (including 0) which can be used to help categorise your commissions | Each tag at most 25 alphanumeric and space characters                                                                                                                                     |

### Viewing a commission: `opencom`

Opens a commission at `INDEX` and shows its relevant details and image. If no `INDEX` is specified, you will just switch to the commissions tab.

Format: `opencom [INDEX]`

Examples:
* `opencom` Switches to commissions tab.

* <code>opencom <em>2</em></code> When you run an `opencom` command with index <code>opencom <em>2</em></code>, you should be switched to the commissions tab to view commission details.

![opencom](images/opencom.png)

### Adding a commission: `addcom`

Adds a commission to the currently opened customer.

Format: `addcom n/TITLE f/FEE d/DEADLINE s/STATUS [p/DESCRIPTION] [t/TAG]...​`
* Fee is the amount you are charging the customer for this commission.
  * _Note: Though there is no limit to the number of decimal places for input, only up to two decimal places will be displayed on the graphical interface._
* Status states if the commission is done or not and can only take `y`, `yes`, `t`, `true` for complete status and `n`, `no`, `f`, `false` for incomplete status. It is case-insensitive.

Examples:
* <code>addcom n/<em>Rimuru</em> f/<em>40</em> d/<em>2022-11-01</em> t/<em>traditional</em> s/<em>Y</em> t/<em>chibi</em></code> creates the commission entry titled "Rimuru" with the given fee, due date, completion status and tags.
* <code>addcom n/<em>Fate Archer</em> f/<em>60</em> d/<em>2022-10-15</em> s/<em>false</em> p/<em>Archery</em> t/<em>night</em> t/<em>city</em></code> creates a commission entry titled "Fate Archer" with the given fee, due date, completion status, description and tags.

![addcom](images/addcom.png)

### Editing a commission: `editcom`

Edits a commission at `INDEX`.

Format: `editcom INDEX [n/TITLE] [f/FEE] [d/DEADLINE] [s/COMPLETION STATUS] [p/DESCRIPTION] [t/TAG]`
* At least one field to edit must be provided.
* For tags, a replacement mechanism is in place. This means if you want to add a new tag `watercolor` and retain the original tag `urgent`, both must be included in the command, e.g. `editcus 2 t/watercolor t/urgent`.

Example:
* <code>editcom <em>1</em> n/<em>Tokyo Ghoul Kaneki</em> f/<em>50</em> d/<em>2022-10-10</em> s/<em>False</em> p/<em>Unfamiliar, I will need to do up a reference board first.</em> t/<em>digital</em> t/<em>neon</em></code>
  Edits the first commission to have the above fields.
* <code>editcom <em>2</em> s/<em>True</em></code> Edits the second commission to be completed.

![editcom](images/editcom.png)

### Deleting a commission: `delcom`

Deletes a commission at `INDEX` and iterations related to the commission.

Format: `delcom INDEX`

Example:
* <code>delcom <em>14</em></code>

![delcom](images/delcom.png)

<div align="right">
<br>

[ <a href="#commission-related-commands">To start of Commission related commands</a> | <a href="#features">To start section: Features</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>
<div style="page-break-after: always;"></div>

## Iteration related commands

Each piece of art represents a journey of growth. From ideation, sketches, drafts, to constant refinements.
ArtBuddy wants to help you capture this process. That's why we created iterations, just for you.

Each iteration serves as a 'bookmark', representing a version of your artwork as you work on perfecting your commission.

ArtBuddy offers the following commands to help you manage your iterations:
* [Adding iteration to a commission: `additer`](#adding-an-iteration-to-a-commission-additer)
* [Editing an iteration from commission: `edititer`](#editing-an-iteration-from-commission-edititer)
* [Deleting an iteration from commission: `deliter`](#deleting-an-iteration-from-commission-deliter)

Iterations stored in ArtBuddy have the following details:

| Detail      | Explanation                                                                                                     | Constraints                                                                                                                                                                                  |
|-------------|-----------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Description | Describes the progress that was made in the iteration ([case sensitive, and must be unique](#unique-iteration)) | See [Appendix: Limitations of no-constraint text inputs](#limitations-of-no-constraint-text-inputs)                                                                                          |
| Date        | Records the date tied to the iteration (for instance, when the iteration was created)                           | In [`YYYY-MM-DD`](#further-notes-on-command-parameters) format (four-digit year, followed by a 2-digit month, and 2-digit day number; so "7 November 2022" should be passed as `2022-11-07`) |
| Image       | A picture that captures how the commission looks like in the current iteration                                  | File path (Refer to [Appendix](#what-are-file-paths-and-root-directories) for more info)                                                                                                     |
| Feedback    | Feedback attached to the current iteration (for instance, areas for improvement)                                | See [Appendix: Limitations of no-constraint text inputs](#limitations-of-no-constraint-text-inputs)                                                                                          |

### Adding an iteration to a commission: `additer`
Adds an iteration to the selected commission. This can be done either by the
[CLI](#adding-by-the-command-line-interface) or [GUI](#adding-by-graphical-interface).

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** There are some known limitations with images in ArtBuddy. Please refer
to [Appendix: Limitations with images in ArtBuddy](#limitations-with-images-in-artbuddy) for more details.
</div>

{: .no_toc}
##### Adding by the Command-Line Interface

Format: `additer n/DESCRIPTION d/DATE p/FILEPATH f/FEEDBACK`

* The file path specified should be an absolute path from your root directory. If you're not familiar with file paths
and root directories, you might find the explanation in the [Appendix](#what-are-file-paths-and-root-directories) helpful.
* The command requires a commission to be selected.
* Currently, only image file types .png, .jpg, .bmp and .gif are supported.

Example:
> Note that this example will probably not work for you because you'll need to replace the file path
with a valid file path.

* <code>additer n/<em>First Draft</em> d/<em>2022-10-28</em> p/<em>/Users/John/Downloads/Draft 1.png</em> f/<em>Looks great</em></code> creates an iteration
with the description "First Draft", date 28 October 2022, image at file path <code>p/<em>/Users/John/Downloads/Draft 1.png</em></code>,
and feedback "Looks great".


<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** There are some known limitations with specifying using the CLI. Please refer
to [Appendix: Limitations with file paths in CLI](#limitations-with-file-paths-in-cli) for more details.
</div>


{: .no_toc}
##### Adding by Graphical Interface

1. Click on the **Add Iteration** button inside the Commission you wish to add the iteration to.

2. Fill in the details of the new iteration in the window that pops up.
   * An image can be added to the iteration by selecting a file in your file manager by clicking on the
   **Add Image** button, or by dragging and dropping an image to the grey image drop area.

![Add Iteration](images/AddIteration.png)


<div markdown="span" class="alert alert-info">
**:information_source: Notes about images in ArtBuddy:**<br>

ArtBuddy creates a copy of each file you upload. This means that you can edit, delete, or
move your original copy of the file without affecting the uploaded image on ArtBuddy.
</div>

### Editing an iteration from commission: `edititer`
Edits an iteration at `INDEX` in the selected commission.

Format: `edititer INDEX [n/DESCRIPTION] [d/DATE] [p/FILEPATH] [f/FEEDBACK]`
* The command requires a commission to be selected.
* At least one field to edit must be provided.

Example:
* <code>edititer <em>2</em> n/<em>Sketch</em></code> Edits the description of the second iteration in the currently selected commission.

![Edit Iteration](images/EditIteration.png)

### Deleting an iteration from commission: `deliter`
Deletes an iteration at `INDEX` from the selected commission.

Format: `deliter INDEX`
* The command requires a commission to be selected.
* You may want to note that your local copy of the image will not be deleted.

Example:
* <code>deliter <em>2</em></code> Deletes the second iteration of the currently selected commission.

![Delete Iteration](images/DeleteIteration.png)


<div align="right">
<br>

[ <a href="#iteration-related-commands">To start of Iteration related commands</a> | <a href="#features">To start section: Features</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>
<div style="page-break-after: always;"></div>
## Statistical commands

Supercharge your business with ArtBuddy's integrated statistical commands. It's now possible to leverage on your business data at the snap of your fingers. 

ArtBuddy offers the following commands to help you navigate through and make sense of all your data:
* [List all the customers: `list`](#list-all-the-customers-list)
* [Find a customer: `find`](#find-a-customer-find)
* [Sorting the customer list: `sortcus`](#sorting-the-customer-list-sortcus)
* [List the commissions: `listcom`](#list-the-commissions-listcom)
* [Viewing all commissions: `allcom`](#viewing-all-commissions-allcom)
* [Find a commission: `findcom`](#find-a-commission-findcom)

### List all the customers: `list`
Lists all the customers.

Format: `list`

![list](images/list.png)

### Find a customer: `find`
Finds all the customers who satisfy keyword matching in the name and tag filters. The tag filter, more specifically will include customers who contain all tags under `-all` and at least one of the tags under `-any`.

Format: `find [k/KEYWORDS]... -all [t/TAGS]... -any [t/TAGS]...`
* The keywords, all, and any filters are all optional and can be omitted, but at least one should exist.

Examples:
* <code>find k/<em>Kevin</em></code> Finds customers who have the name Kevin.
* <code>find -all t/<em>friend</em> t/<em>colleague</em></code> Finds customers who are tagged both `friend` and `colleague`.
* <code>find -any t/<em>friend</em> t/<em>colleague</em></code> Finds customers who are either tagged `friend` or `colleague`.

![find](images/find.png)

### Sorting the customer list: `sortcus`

Sorts the displayed customer list by one of the following options:

Prefix:
- `n` (name)
- `d` (latest commission date)
- `c` (commission count)
- `r` (revenue)
- `a` (active commissions count)

Suffix: `+` (increasing) or `-` (decreasing)

Format: `sortcus PREFIX/SUFFIX`

Examples:
* `sortcus r/+` Sorts your customer list by the total revenue generated from each customer in increasing order.

![sortcus](images/sortcus.png)

### List the commissions: `listcom`
Lists all the commissions made by the selected customer.

Format: `listcom`

![listcom](images/listcom.png)

### Viewing all commissions: `allcom`

Displays all your commissions made by all customers in ArtBuddy.

Format: `allcom`

After running `allcom`, you should be switched to the commissions tab to view all commissions.
To view the commissions for a specific customer, return to the customer list ([`opencus`](#viewing-a-customer-opencus)) and select the customer from the list ([`opencus INDEX`](#viewing-a-customer-opencus)).

![allcom](images/allcom.png)

### Find a commission: `findcom`
Finds all the commissions in the list which satisfies your search query. You can also search for commissions by their tags using the `-all` and `-any` filters.

Format: `findcom [k/KEYWORDS]... -all [t/TAGS]... -any [t/TAGS]...`
* The keywords, all, and any filters are all optional and can be omitted, but at least one should exist.

Examples:
* <code>findcom k/<em>Tree Painting</em></code> Finds commissions which have the title `Tree Painting`.
* <code>findcom -all t/<em>oil painting</em> t/<em>canvas</em></code> Finds commissions that are tagged with both `oil painting` and `canvas`.
* <code>findcom -any t/<em>oil painting</em> t/<em>canvas</em></code> Finds commissions that are either tagged with `oil painting` or `canvas`.

![findcom](images/findcom.png)

<div align="right">
<br>

[ <a href="#statistical-commands">To start of Statistical commands</a> | <a href="#features">To start section: Features</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

<div style="page-break-after: always;"></div>

## Miscellaneous

### Viewing help: `help`

Shows you a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Clearing all the customers: `clear`
Clears all your customers from ArtBuddy, including their commissions and iterations.
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command cannot be undone. So only execute this command when you are 100% sure of executing it.
</div>

### Exiting the program: `exit`

Exits the program.

Format: `exit`

<div align="right">
<br>

[ <a href="#miscellaneous">To start of Miscellaneous commands</a> | <a href="#features">To start section: Features</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# Appendix

### Saving the data

ArtBuddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ArtBuddy data are saved as a JSON file `[JAR file location]/data/artbuddy.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ArtBuddy will discard all data and start with an empty data file at the next run.
</div>

### Further notes on command parameters
* When passing in dates in ArtBuddy as command parameters, they should be formatted in YYYY-MM-DD format.
    * This means that if you want to specify '4th October 2022' as a date command parameter, it should be
      formatted and passed into ArtBuddy as '2022-10-04'.

* Something cool about ArtBuddy is that command parameters can be specified in any order!
    * This means that if the command specifies `n/NAME p/PHONE_NUMBER`, ArtBuddy will also understand the
      input `p/PHONE_NUMBER n/NAME`.

* Also, if a parameter is expected only once in the command, but you specified it multiple times, ArtBuddy
  will only listen to the last occurrence of the parameter.
    * So, if you specify <code>p/<em>12341234</em> p/<em>56785678</em></code>, only <code>p/<em>56785678</em></code> will be taken.

* In addition, if you specify command parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`),
  ArtBuddy simply ignore these extra parameters.
    * Meaning that the command <code>help <em>123</em></code>, will be interpreted as `help`.

### Limitations of no-constraint text inputs
Command parameters like commission descriptions, iteration descriptions and iteration feedbacks that do not impose a
constraint on valid text-inputs may face the issue of ambiguity when dealing with inputs containing '/'. This is because
'/' are also used in ArtBuddy to denote prefixes. As such, descriptions or feedback that contains a prefix will unfortunately
not be parsed properly by ArtBuddy. One way you can overcome this by adding a space between the '/'s in your input. 

### Limitations with file paths in CLI
Due to file path naming and the command format, specifying a folder with a folder name that ends with a
**'n', 'd', 'f', or 'p'** would lead to an ambiguous command.

For instance, the command `additer d/2022-10-10 n/description f/actual feedback p//test f/image.png` is ambiguous
because ArtBuddy does not know whether <code>f/<em>image.png</em></code> is the feedback parameter specified by the user.

If you wish to upload the image, either rename the folder name, or [upload the image by the GUI](#adding-by-graphical-interface).

### Limitations with images in ArtBuddy
ArtBuddy might not properly handle extreme image sizes. If your images are too small, they might not render
properly on ArtBuddy, or might appear very pixelated. If your image is too big (**the recommended image
size limit is around 5MB**), ArtBuddy might not be able to open the image at all, or you may start to
experience significant lag with ArtBuddy.

### What are file paths and root directories?
Just like how we use addresses to tell specify locations when talking to people, computers
do the same! Each file in your computer has a unique address that can be used to identify the
exact location in your computer where the file is stored.

The address of each file in your computer can be viewed simply as "directions", guiding your
computer to get to the file. Think about how you would tell someone how to open a specific file
in your computer. You would probably say something along the lines of: "Go to the Downloads folder,
where you'll find an  Image folder. Click into the Images folder and open the file Draft1.png".

Well to computers, file paths are just like these guiding instructions that help them locate
a specific file! And your 'root directory' is simply a 'base point' that stores all your files in
your computer. For most users using a Windows or Mac computer, this root directory is simply
a folder named `/`.

So what a file path `/Users/John/Downloads/Draft 1.png` really means is just a way of telling
the computer, "Hey, from my root directory, you'll find a folder called Users, and in there a
folder called John. Open that up and you'll find another folder called Downloads.
Open the Downloads folder and you'll see the file I want called `Draft 1.png`".

To easily copy a filepath of a file:
* On Windows, in your File Explorer, hold shift down while you right-click on the file you want. Select the option <code>Copy as Path</code> and the filepath of your file will be copied!
* On Mac, in your Finder, click on the file you want to select it and press the <code>Option</code>, <code>Command</code>, <code>C</code> keys simultaneously. The filepath of your file is now copied!

<div align="right">
<br>

[ <a href="#appendix">To start section: Appendix</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# Glossary

Not sure what certain terms used in this user guide mean? Don't worry, we're here to help! 

| Detail                         | Explanation                                                                                                                                                                                                                                                                                                                               |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Commission                     | A commission is a piece of work that you would receive from a customer.                                                                                                                                                                                                                                                                   |
| Iteration                      | An iteration is a version of your commission. You can think of it as a bookmark on your process of making a piece of art.                                                                                                                                                                                                                 |
| Command Line Interface (CLI)   | An application with a Command Line Interface is one where the user interacts with the application using text-inputs. CLI apps typically have no visual interface.                                                                                                                                                                         |
| Graphical User Interface (GUI) | GUI applications are what you are most probably familiar with. These are applications with visual graphics, where you interact with the system by pressing buttons or clicking on things.                                                                                                                                                 |
| Java                           | Java is a popular programming language which ArtBuddy is written in!                                                                                                                                                                                                                                                                      |
| JAR file                       | A JAR file is basically a ZIP file that compresses all of ArtBuddy's inner working parts into one neat file that you can run on your computer.                                                                                                                                                                                            |
| Terminal                       | The terminal is a CLI for macOS computers that allows you to communicate directly with your computer.                                                                                                                                                                                                                                     |
| Index                          | The index is a customer's, commissions', or iteration's unique identifier. It is simply the number that appears next to the customer's name, the commission's title, or the iteration's description.                                                                                                                                      |
| Prefix                         | A 'prefix' is simply a single character with a forward slash (eg: "n/", "p/", "d/") that lets ArtBuddy know what the piece of data you've passed into ArtBuddy is. With a prefix attached to each command parameter, ArtBuddy now understands whether a command parameter is a customer's name, or a customer's phone number. Neat right? |
| File path                      | A string of words that represents where a file in your computer is located. You may refer to [Appendix: What are file paths and root directories?](#what-are-file-paths-and-root-directories) for a more detailed explanation.                                                                                                            |
| Root directory                 | The mother of all folders in your computer. You can think of the root directory as the folder that stores all of the folders in your computer.                                                                                                                                                                                            |

<div align="right">
<br>

[ <a href="#glossary">To start section: Glossary</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# FAQ

<details>
<summary><strong>Q</strong>: Why does my command not work?</summary>
<br>
<strong>A</strong>: First make sure that you have written one of the available commands.
Note that it is case-sensitive, which means <code>help</code> works but <code>Help</code> does not.
If you have written the correct command word, but it still does not work, then an error displayed will show you the correct usage
or why your command is not working. Note that the prefixes are case-sensitive too. If you want to know more details about the command, please refer to that command in the <a href="#features">Features</a> section.
</details>
<br>

<details>
<summary><strong>Q</strong>: How do I transfer my data to another Computer?</summary>
<br>
<strong>A</strong>: Install the app in the other computer and overwrite the default data folder it creates with the data folder that contains the data of your previous ArtBuddy home folder.
</details>
<br>

<details>
<summary><strong>Q</strong>: Why isn't my file path being recognised by ArtBuddy?</summary>
<br>
<strong>A</strong>: If you're sure that the file exists, one common mistake new users may make is omitting the root directory from your file path.
So do check that the file path you specified starts with a '/'. Please also make sure that your specified file path does
not fall under the limitations of file paths in ArtBuddy mentioned in the <a href="#limitations-with-file-paths-in-cli">Appendix: Limitations with file paths in CLI</a>.
If all else fails, you can try <a href="#adding-by-graphical-interface">uploading the image via GUI</a> instead!
</details>
<br>

<details id="unique-customer">
<summary><strong>Q</strong>: What makes two customers different when adding a customer by <code>addcus</code>?</summary>
<br>
<strong>A</strong>: We only check it by their name case-sensitive. That means you can have two customers with names "Sarah" and "sarah".
If this is still not enough to distinguish your customers with same name, you can even include numbers in the name field like "Sarah 1", "Sarah 2".
At the moment, we only support alphanumeric english characters with space for our early versions.
</details>
<br>

<details id="unique-commission">
<summary><strong>Q</strong>: What makes two commissions different when adding a commission by <code>addcom</code>?</summary>
<br>
<strong>A</strong>: We decided that to avoid confusion, for every customer the title of the commissions must be unique. Therefore, if the customer you
want to add a commission to already has the same title commission it won't be allowed.
</details>
<br>

<details id="unique-iteration">
<summary><strong>Q</strong>: What makes two iterations different when adding an iteration by <code>additer</code>?</summary>
<br>
<strong>A</strong>: We decided that for each commission the description of the iterations must be unique. Therefore, if the commission you
want to add an iteration to already has the same description iteration it won't be allowed.
</details>
<br>

<details>
<summary><strong>Q</strong>: Why am I allowed to insert more than 2 significant digits in commission fee?</summary>
<br>
<strong>A</strong>: Although our calculations are done in dollar amount, we don't expect you to make your deals in dollar.
So you can write the fee value in dollars but with more significant digits for more accurate calculations.
But in the GUI, for the ease of your eyes, we display it in 2 decimal digits only.
</details>
<br>

<details>
<summary><strong>Q</strong>: Why am I allowed to insert dates that are in the past?</summary>
<br>
<strong>A</strong>: We expect that you've successfully had customers and commissions before using our application.
We enabled adding past dates for archival purposes so that you can make the best out of our statistical features.
</details>
<br>

<details>
<summary><strong>Q</strong>: Why is my new commission not in the commission list?</summary>
<br>
<strong>A</strong>: You might have accidentally filtered out your new commission with a prior <code>findcom</code> command.
Try <code>listcom</code> to reset your commission search and see all your commissions!
</details>
<br>

<details>
<summary><strong>Q</strong>: What are file paths and root directories?</summary>
<br>
<strong>A</strong>: We don't expect you to have a lot of technical knowledge about a computer file system.
But we provide you a good explanation to answer your question in the <a href="#what-are-file-paths-and-root-directories">Appendix</a>.
</details>
<br>

<div align="right">
<br>

[ <a href="#FAQ">To start section: FAQ</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
# Command summary

| Action                                                                                          | Format, Examples                                                                                                                                                                                                                                                                                                                      |
|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Open customer/Switch to Customer Tab**](#viewing-a-customer-opencus)                         | `opencus [INDEX]`<br> e.g., <code>opencus <em>2</em></code>, <code>opencus</code>                                                                                                                                                                                                                                                     |                                                                                              |
| [**Add customer**](#adding-a-customer-addcus)                                                   | `addcus n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]...`  <br> e.g., <code>addcus n/<em>John Doe</em> p/<em>98765432</em> e/<em>johnd@example.com</em> a/<em>John street, block 123, #01-01</em> t/<em>animal cartoons</em></code>                                                                                               |
| [**Edit customer**](#editing-a-customer-editcus)                                                | `editcus INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...` <br> e.g., <code>editcus <em>1</em> p/<em>91234567</em> e/<em>johndoe@example.com</em></code>                                                                                                                                                                     |
| [**Delete customer**](#deleting-a-customer-delcus)                                              | `delcus INDEX`<br> e.g., <code>delcus <em>14</em></code>                                                                                                                                                                                                                                                                              |
| [**Open Commission/Switch to Commission Tab**](#viewing-a-commission-opencom)                   | `opencom [INDEX]`<br> e.g., <code>opencom <em>14</em></code>, <code>opencom</code>                                                                                                                                                                                                                                                    |
| [**Add commission**](#adding-a-commission-addcom)                                               | `addcom n/TITLE f/FEE d/DEADLINE [t/TAG]...`<br> e.g., <code>addcom n/<em>Rimuru</em> f/<em>40</em> d/<em>2022-11-01</em> t/<em>traditional</em> t/<em>chibi</em></code>                                                                                                                                                              |
| [**Edit commission**](#editing-a-commission-editcom)                                            | `editcom INDEX [n/TITLE] [f/FEE] [d/DEADLINE] [s/COMPLETION STATUS] [p/DESCRIPTION] [t/TAG]...` <br> e.g., <code>editcom <em>1</em> n/<em>Tokyo Ghoul Kaneki</em> f/<em>50</em> d/<em>2022-10-10</em> s/<em>False</em> p/<em>Unfamiliar, I will need to do up a reference board first.</em> t/<em>digital</em> t/<em>neon</em></code> |
| [**Delete Commission**](#deleting-a-commission-delcom)                                          | `delcom INDEX`<br> e.g., <code>delcom <em>14</em></code>                                                                                                                                                                                                                                                                              |
| [**Add Iteration**](#adding-an-iteration-to-a-commission-additer)                               | `additer n/DESCRIPTION d/DATE f/FEEDBACK p/FILEPATH`<br> e.g., <code>additer n/<em>Draft 1</em> f/<em>Good</em> d/<em>2022-10-28</em> p/<em>/Users/John/Downloads/Bread.jpeg</em></code>                                                                                                                                              |
| [**Edit Iteration**](#editing-an-iteration-from-commission-edititer)                            | `edititer INDEX [n/DESCRIPTION] [d/DATE] [f/FEEDBACK] [p/FILEPATH]`<br> e.g, <code>edititer <em>2</em> n/<em>Sketch</em></code>                                                                                                                                                                                                       |
| [**Delete Iteration**](#deleting-an-iteration-from-commission-deliter)                          | `deliter INDEX`<br> e.g., <code>deliter <em>1</em></code>                                                                                                                                                                                                                                                                             |
| [**List customers**](#list-all-the-customers-list)                                              | `list`                                                                                                                                                                                                                                                                                                                                |
| [**Find customers**](#find-a-customer-find)                                                     | `find [k/KEYWORD]... -all [t/TAG]... -any [t/TAG]...` <br> e.g. <code>find -all t/<em>friend</em> t/<em>colleague</em></code>                                                                                                                                                                                                         |
| [**Sort customers**](#sorting-the-customer-list-sortcus)                                        | `sortcus PREFIX/SUFFIX`<br> e.g., <code>sortcus n/<em>+</em></code>                                                                                                                                                                                                                                                                   |
| [**List commissions**](#list-the-commissions-listcom)                                           | `listcom`                                                                                                                                                                                                                                                                                                                             |
| [**View all Commissions**](#viewing-all-commissions-allcom)                                     | `allcom`                                                                                                                                                                                                                                                                                                                              |
| [**Find commissions**](#find-a-commission-findcom)                                              | `findcom [k/KEYWORD]... -all [t/TAG]... -any [t/TAG]...`                                                                                                                                                                                                                                                                              |
| [**Help**](#viewing-help-help)                                                                  | `help`                                                                                                                                                                                                                                                                                                                                |
| [**Clear everything**](#clearing-all-the-customers-clear)                                       | `clear`                                                                                                                                                                                                                                                                                                                               |
| [**Exit**](#exiting-the-program-exit)                                                           | `exit`                                                                                                                                                                                                                                                                                                                                |


<div align="right">
<br>

[ <a href="#command-summary">To start section: Command summary</a> | <a href="#table-of-contents">To table of contents</a> ]
</div>
