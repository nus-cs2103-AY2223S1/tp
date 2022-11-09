---
layout: page
title: 👾 Minefriends Developer Guide
---

<div markdown="block" class="alert alert-info">

:bulb: Before we begin, it is important to note that:
* Minefriends is not affiliated with Minecraft, Mojang Studios or Microsoft in any way.
* Minefriends is an independently developed software. It is not a product owned by Mojang Studios or Microsoft.
* Team Minefriends do not own Minecraft, in whole or in part.

</div>

### What is Minefriends

Minefriends is an address book for Minecraft players to find friends to play Minecraft multiplayer with,
at the right time, with the right game modes and on the right servers.

### What is this guide about

This developer guide provides readers with the understanding of the motivations for the development of
Minefriends together with its technical design and implementation details. A broad view of the architecture of 
Minefriends is given and selected implementation details of important features are given as well.

### Who is this guide for

This guide is written for
* Maintainers who wish to extend/alter the features of Minefriends
* Developers who wish to morph Minefriends for their own use
* Minecraft players who wish to adapt Minefriends to better suit their own needs

### How to use this guide

#### Organization

The guide is organized in a way that it starts with the motivations of building Minefriends, followed
by a big-picture design overview of the various components before diving into individual feature implementations.
* To get a good general understanding of Minefriends, it is recommended to read the guide from top to bottom.
* However, you can also jump to a certain part using the [table of contents](#table-of-contents).

#### Legend

* Text in [blue](#how-to-use-this-guide) are links that you can use to navigate to another part of the document or to an external document/website.
* Text in **bold** are used to emphasize an important point.
* Text in `this` are used to refer to code or names related to the codebase.

<div markdown="span" class="alert alert-primary">
:bulb: Text in a blue box are tips; they provide additional information about a particular point.
</div>

--------------------------------------------------------------------------------------------------------------------

## **Table of contents**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

### External libraries
* [ControlsFX](https://github.com/controlsfx/controlsfx)
* [TestFX](https://github.com/TestFX/TestFX)

### Graphics and assets
* App Icon - [Minecraft Book & Quill icon](https://minecraft.fandom.com/wiki/Book_and_Quill)
* Font - [Minecraft font](https://fontmeme.com/fonts/minecraft-font/)
* Background textures - [Dirt block](https://minecraft.fandom.com/wiki/Dirt)
* Background textures - [Grass block](https://minecraft.fandom.com/wiki/Grass_Block)
* Background textures - [Bedrock](https://minecraft.fandom.com/wiki/Bedrock)
* [Creeper texture](https://minecraft.fandom.com/wiki/Creeper?file=Charged_Creeper_JE1_BE1.png)
* [Creeper explosion texture](https://toppng.com/show_download/244692/explosion-pixel-art/large)
* Creeper explosion audio: Self-recorded in game

These assets are copyright Mojang Studios, which are available for non-commercial use.
The terms of use can be found [here](https://www.minecraft.net/en-us/terms).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [Setting up and getting started](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Conceptualization of Minefriends**

#### Target user persona

A male 14 year old teenager who plays Minecraft multiplayer with his friends. 
He is an expert Minecraft player. He has school and extracurricular commitments 
but always finds time to play Minecraft. He plays a variety of multiplayer game modes 
(eg. creative, survival games, skyblock, KitPvP etc.) and he has different friends who play
Minecraft with him in different ways. He is familiar with Minecraft commands so he 
is comfortable with the command line interface (CLI).

#### Target user profile

* plays Minecraft multiplayer with friends regularly
* has many Minecraft friends (online and offline) from all over the world
* prefers desktop apps over other types
* prefers typing to mouse interactions and types fast
* is comfortable using the Minecraft command line, and by hence extension, using CLI apps

#### Value proposition

There are many servers and multiplayer game modes in Minecraft, and players have different schedules to when they can play.
We want to help players find the right people to play the right game mode with at the right time.

#### User stories and use cases

The user stories can be found in [Appendix A](#appendix-a-user-stories) and use cases in [Appendix B](#appendix-b-use-cases).

--------------------------------------------------------------------------------------------------------------------

## **Design of Minefriends**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="700" height="250" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Selected implementation details**

This section describes some noteworthy details on how certain features are implemented.

### *Edit a Friend*

The `edit` feature allows users to edit details of their friends.

#### Implementation

The edit feature is facilitated through the `EditCommand` and `EditCommandParser` classes. First, the friend to 
be edited is identified through the index of the person in the list of friends. Then, in
the `EditCommand` class, there is a `EditPersonDescriptor` nested class which takes in all
the updated details of the friend to be edited. For the fields that are not updated, the 
original fields will be used in the `EditPersonDescriptor` class.

Minefriends will then call the `createEditedPerson` method which will create a new person
with new details. This will then invoke a call to the `ModelManager` class to replace the target person
with the edited person in the addressBook.

The following class diagram shows the organization of the classes for `edit`.

<img src="images/editCommand.png" width="250" />

The following sequence diagram shows the flow of the execution of the
edit command. Some details related to the general parsing and execution of 
commands are omitted as they have been explained under [logic](#logic-component).

<img src="images/EditCommandSequenceDiagram.png" height="350"/>

### *Suggest a Friend*

The `suggest` feature suggests a friend for the user to play Minecraft with based on a given
set of constraints. A detailed description of its usage can be found [here](https://ay2223s1-cs2103t-t10-4.github.io/tp/UserGuide.html#suggest-me-a-friend-suggest) in the user guide.

#### Implementation

The class diagram below shows the organization of the classes of the `suggest` feature, with explanation provided after.

<img src="images/SuggestFriendClassDiagram.png" width="300" />

The `suggest` feature is facilitated through the `SuggestCommand`, `SuggestCommandParser`
and `PersonSuggestionPredicate` classes. Once parsed, every `SuggestCommand` will contain
a `PersonSuggestionPredicate` such that the predicate can be used to filter
through the list of all friends to get a list of suggested friends.

The `PersonSuggestionPredicate` is made up of two parts, a collection of `DayTimeInWeek` and a collection of `Keyword`. 
Minefriends will find all friends that are available for **any** of the `DayTimeInWeek` and contains **all** the keywords in 
the collection of `Keyword`.

The following sequence diagram shows the flow of the execution of the suggest command.
Some details related to the general parsing and execution of commands are omitted
as they have been explained under [logic](#logic-component).

<img src="images/SuggestFriendSequenceDiagram.png" width="550" />

The `filteredPersons` in the instance of `Model` will be filtered according to the `PersonSuggestionPredicate`
to contain only the friends who satisfy the predicate. More information about `Model` is available [here](#model-component).

#### Rationale for this implementation

Using predicates allows us to decouple the `SuggestCommand` and `Model` instances, by containing the logic
for deciding who to filter in a separate class `PersonSuggestionPredicate`.

In addition, the use of predicate allows us to exploit the power of Java streams, which makes it easier
to write simpler and cleaner code.

#### Alternatives considered

The `Model` instance can be passed directly to the `SuggestCommand` in which the `SuggestCommand` can
modify the `filteredPersons` list directly. However, this leads to tighter coupling which reduces the 
maintainability of the code.

### *Autocomplete Commands*

The autocomplete feature matches the current text to all the commands for the user when the user types in the command box.

#### Implementation

The autocomplete feature is facilitated through the `TextFields` class under the ControlsFX library.
The `TextFields` class provides a static method `bindAutoCompletion` that will create a new autocompletion binding between
the given TextField using the given autocomplete suggestions.

Everytime the user modifies the input, a `AutoCompletePopup` object, which is a `PopupWindow`, will appear below the `CommandBox`.
The object will display a list of suggestions that matches the current text in the text field.

Alternative implementations of coming up with our own classes were considered aside from using the ControlsFX library. 
However, coming up with the solution requires a great amount of effort for the same amount of functionality. 
Hence, the decision was made to use the ControlsFX library.

The following activity diagram shows the workflow for the autocomplete feature.

<img src="images/AutoCompleteActivityDiagram.png" width="750" />

### *Available timings*

The user can save the available timings of a friend to their profile, as a time interval during the week. 

#### Implementation

The available timings of a friend are stored as `TimeInterval` attributes in the `Person` class. 
Each `TimeInterval` object will have two `DayTimeInWeek` fields, representing its start time and its end time.

The time stamp that is parsed into the `DayTimeInWeek` class is then saved as the number of elapsed minutes since Monday 12am.

#### Additional details

To support our `Suggest` command in finding friends who are available at a certain time, the `TimeInterval` class implements the `isAvailable()` method in the `ITimesAvailable` interface. 
The `isAvailable()` method takes in a time stamp as a `DayTimeInWeek` object, and calculates if it lies within the available time interval of the friend.

The following class diagram shows the relationship between the classes:

<img src="images/TimeIntervalClassDiagram.png" width="275" />

Furthermore, in order to use the `Suggest` command to find friends who are playing at the
current moment, the `SuggestCommandParser` invokes the `parseDayTimeInWeek` method of the 
`ParserUtil` class to convert the current time to a `DayTimeAndWeek` object. Inbuilt Java APIs
such as `LocalDateTime` and `DayOfWeek` are used to get the current time.

The following sequence diagram explains how the current time is parsed into
a `DayTimeInWeek` object, starting from the `parseDayTimeInWeek` method 
of `ParserUtil`. Following which, you may refer to the [Suggest](#suggest-a-friend) section which
explains in greater detail how the `Suggest` command functions to find friends who are available at 
certain timings.

<img src="images/SuggestNowSequenceDiagram.png" />

### *Servers, GameTypes and Socials as Coloured Tags in Friend's profile*
Whenever the user updates the in-game preferences or social handles of a friend, they are displayed as 
distinct coloured tags under the friend's profile.

#### Implementation

The display feature is facilitated through the `PersonCard`, `PersonListPanel` and `UIPart` classes, 
and configured using the `DarkTheme.css` file.

The `PersonCard` class inherits from the `UIPart` class, representing the panel that displays each friend's profile. 
Inside the `PersonCard` class, there are multiple `FlowPane` fields, each representing a type of user preference.

Everytime the user updates a friend's information, a new `PersonCard` object is created. The `PersonCard` object 
will retrieve the corresponding friend's information using the `toDisplayString()` method in the `Person` class, and add
their in-game preferences and social handles as labelled tags under the friend's profile.

Each type of user preference is then tagged with a different colour, specified in the `DarkTheme.css` file, for 
easy differentiation of information.

The following class diagram shows the relationship between the classes in the UI system:

<img src="images/PersonTagClassDiagram.png" width="250" />

#### Design Consideration

#### Which user information should be shown as tags?

* **Alternative 1 (current choice):** Only in-game preferences (such as preferred game types and Minecraft servers) and social handles. 
  * Pros:
    * Strategic display of only the more important user details.
    * Less cluttered, cleaner user interface.
  * Cons: 
    * We have to decide which user details are of higher priority to Minefriends users, which may differ from user to user.
* Alternative 2: Show all information as tags.
  * Pros:
    * Easy to implement as the format is consistent for all information.
  * Cons:
    * Too many tags displayed can make it harder to users to find the information they need in one glance.

### User-Friendly Representation of Various Servers

The representation of various Minecraft servers is in the format
of `server name@server address`. Each server can have duplicate names but each
server will have a unique server address.

#### Reason for Implementation
The previous representation of Minecraft servers was in the format of solely
a server address e.g. `111.111.111.111`

This is much less user-friendly as compared to the new representation
where users are able to remember various servers by their server names,
and distinguish servers with the same names by their server addresses
e.g. `Mineplex@111.111.111.111`

#### Implementation

The server class currently only allows the server to be documented in the
format of an IP address.

With the input `ms/111.111.111.111`, <br>
1) The `Parse` method in `AddCommandParser` will recognize the `prefix_minecraft_server`.<br>
2) The method will then call `parseServers` method of `ParserUtil`. <br>
3) `parseServers` method of `ParserUtil` will examine the validity of the
   server name by calling the `parseServer` method of `ParserUtil`. <br>
4) If it is a valid server name, a new server class will be created and
   added into the set of Server. <br>
5) The set of Server will then be an attribute of the new `Person` created.

![ServerRepresentationSequenceDiagram](images/ServerRepresentationSequenceDiagram.png)


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix A: User stories**

Priority legend
* High: Must have
* Medium: Good to have
* Low: Unlikely to have

| Priority | As a             | I want to                                          | so that I can                                                               |
|---------|------------------|----------------------------------------------------|-----------------------------------------------------------------------------|
| High    | new user         | see usage instructions                             | learn how to use Minefriends                                                |
| High    | Minecraft player | add my friends to Minefriends                      | remember information about them                                             |
| High    | Minefraft player | remove a friend from Minefriends                   | keep an accurate list of my Minecraft friends                               |
| High    | Minecraft player | view all my friends                                | have an overview of my Minecraft social contacts                            |
| High    | Minecraft player | save all my friends' information                   | retrieve them on subsequent uses of Minefriends                             |
| High    | Minecraft player | receive suggestions on which friends to play with  | play Minecraft with the right friend at the right time                      |
| High    | Minecraft player | know my friend's name                              | address them correctly                                                      |
| High    | Minecraft player | know my friend's Minecraft username                | recognize them on Minecraft servers                                         |
| High    | Minecraft player | know my friend's phone number                      | call/text them when I want to play with them                                |
| High    | Minecraft player | know my friend's social media handles              | contact them when I want to play with them                                  |
| High    | Minecraft player | know my friend's preferred game modes              | find friends with compatible game type interests                            |
| High    | Minecraft player | know my friend's preferred Minecraft servers       | find friends with compatible server interests with me                       |
| High    | Minecraft player | know my friend's preferred play timings            | find friends who are free at a particular time                              |
| Medium  | Minecraft player | know my friend's email address                     | contact them when I want to play with them                                  |
| Medium  | Minecraft player | know my friend's physical address                  | go to their houses to play together                                         |
| Medium  | Minecraft player | know my friend's country                           | be mindful of timezone differences when playing                             |
| Medium  | Minecraft player | note down additional information about my friends  | remember other noteworthy information about them                            |
| Medium  | Minecraft player | know what servers my friends have been banned from | avoid those servers when playing with them                                  |
| Low     | Minecraft player | secure my information behind a password            | other people cannot intrude upon my privacy                                 |
| Low     | Minecraft player | know my friend's in-game skin                      | I can recognize them in game                                                |
| Low     | Minecraft player | find new friends through Minefriends               | have more friends to play Minecraft with                                    |



--------------------------------------------------------------------------------------------------------------------

## **Appendix B: Use cases**

(For all use cases below, the **System** is the `Minefriends` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a friend**

**MSS**

1.  User requests to add a specific friend in the list
2.  Minefriends adds the friend to the list

    Use case ends.

**Extensions**

* 1a. The format of the given command is invalid.

    * 1a1. Minefriends shows an error message.
  
      Use case ends.
    
* 1b. The friend already exists in the friend list.

    * 1b1. Minefriends shows an error message.
      
      Use case ends.

**Use case: Delete a friend**

**MSS**

1.  User requests to list friends
2.  Minefriends shows a list of friends
3.  User requests to delete a specific friend in the list
4.  Minefriends deletes the friend

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Minefriends shows an error message.

      Use case resumes at step 2.

**Use case: Edit a friend**

**MSS**

1.  User requests to list friends
2.  Minefriends shows a list of friends
3.  User requests to edit a specific friend in the list
4.  Minefriends edits the friend

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Minefriends shows an error message.

      Use case resumes at step 2.
  
* 3b. The format of the given field to edit is invalid.

    * 3b1.  Minefriends shows an error message.
  
      Use case resumes at step 2.

* 3c. User requests to edit a friend's username to one that belongs to another friend in the list.

  * 3b1.  Minefriends shows an error message.

    Use case resumes at step 2.

**Use case: Find a friend**

**MSS**

1. User requests to find a friend in the list using their name as a keyword.
2. Minefriends shows a list of the friends matched.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2b. The search does not match the given name to any friend in the list.

  * 2b1. Minefriends returns an empty friend list.
  
    Use case ends.
  
**Use case: Suggest friends**

**MSS**

1. User requests to suggest friends in the list who matches the given keywords and time intervals.
2. Minefriends shows the list of friends matched.

   Use case ends.

**Extensions**

* 1a. The format of the given command is invalid.

    * 1a1. Minefriends shows an error message.

      Use case resumes at step 2.

* 2a. The search does not match the given fields to any friend in the list.

    * 2a1. Minefriends returns an empty friend list.

      Use case ends.

**Use case: Enter a command**

**MSS**

1. User attempts to enter a command.
2. Minefriends shows a drop-down list of auto-completed command suggestions.

   Use case ends.

**Extensions**

* 1a1. User enters an input that does not match any part of the correct commands.

  Use case ends.



--------------------------------------------------------------------------------------------------------------------

## **Appendix C: Non-functional requirements**

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 100 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Not suitable for platforms with on-screen keyboards as the keyboard popup may block the screen view.
5.  Should be able to launch multiple instance of the app on the same device.
6.  Should be able to be used by a person who has never used a CLI program before.
7.  Not required to handle the sending of messages between friends.
8.  Not required to handle the app on mobile platform.
9.  Not required to handle the display of the User Interface properly if the window is smaller than 1280 x 720 pixels in resolution.

--------------------------------------------------------------------------------------------------------------------

## **Appendix D: Glossary**

### Minecraft-related terminologies


| Terminology        | Definition                                                                                       |
|--------------------|--------------------------------------------------------------------------------------------------|
| Minecraft          | An open world sandbox game, [official website](https://www.minecraft.net/en-us)                  |
| Minefriends        | The name of our app                                                                              | 
| Username           | The uniquely identifiable Minecraft username of each player                                      |                                                  
| Server             | A multiplayer Minecraft server                                                                   |
| Player             | A person who plays Minecraft                                                                     |
| Mojang Studios     | The company that created and owns Minecraft                                                      |
| Microsoft          | The company that bought over Mojang Studios in 2014                                              |  
| Game mode          | There are many ways to enjoy Minecraft, and the game mode describes how the game is being played |
| Game type          | A synonym for game mode                                                                          | 

For a complete glossary of Minecraft terms, please visit this page on the
[Minecraft wiki](https://minecraft.fandom.com/wiki/Tutorials/Game_terms).

### Other terminologies

| Terminology   | Definition                                                                                                         |
|---------------|--------------------------------------------------------------------------------------------------------------------|
| Mainstream OS | A mainstream desktop operating system, such as Windows, Linux, OS-X                                                |
| Socials       | A person's social media account information, such as their Telegram handle, Instagram username or Twitter username |
| CLI           | An acronym for "command line interface"                                                                            |
| GUI           | An acronym for "graphical user interface"                                                                          |



--------------------------------------------------------------------------------------------------------------------

## **Appendix E: Instructions for manual testing**

Given below are instructions to test the app manually. 
These instructions only provide a starting point for testers to work on;
testers are expected to do more exploratory testing.

<div markdown="span" class="alert alert-info">:information_source: 

**Note:** Please follow the test cases **in order** as some of the later test cases
depend on the state resulting from the earlier test cases.

</div>

### Launch

Initial launch
1. Download the jar file and copy into an empty folder
2. Double-click the jar file

**Expected:** Shows a window with an empty contact list, and a help window too if the app is being used for the first time.

### Adding a friend

Run `add n/Victoria Tan m/vicky12345 ms/myserver@127.0.0.1 gt/Minecraft BTS gt/survival s/fb@Victoria Tan s/ig@vicky1234 t/amiga 
p/+3412345678901 e/victoria.tan@gmail.com a/500 Calle de las Flores, Madrid c/Spain ti/sun@0900-sun@2300 ti/mon@1500-mon@1900`

**Expected:** Creates a new person called `Victoria Tan` in the friend list, with all the above-listed attributes. 

Run `add n/Alma m/almaaaa1`

**Expected:** Creates a new person called `Alma` in the friend list, with all the above-listed attributes.

### Deleting a friend

Run `delete 2`

**Expected:** Removes the contact with name `Alma` from the friend list.

### Editing a friend

Run `edit 1 a/Plaza Mayor, Sta. Cruz de Tenerife, Canary Islands`

**Expected:** The address for `Victoria Tan` is replaced with `Plaza Mayor, Sta. Cruz de Tenerife, Canary Islands`.

Run `edit 1 t/spanish t/french t/noEnglish`

**Expected:** The tags for `Victoria Tan` are replaced with the aforementioned three. `amiga` is no longer there.

### Suggesting friends

Run `add n/Alma m/almaaaa1`

Run `suggest k/Alma`

**Expected:** Only `Alma` shows up.

Run `suggest dt/mon@1855`

**Expected:** Only `Victoria Tan` shows up.

Run `list`

**Expected:** Both friends show up.

### Data persistence

Exit Minefriends, then reopen it.

**Expected:** The data from the last session remains the same.

### Help Screen

Initial launch: `data/addressbook.json` is not present.

**Expected:** The help window is opened. The help window should contain a list of commands and their descriptions.

Run `help`

**Expected:** Help window opens with a list of commands and their descriptions.

## **Appendix F: Effort**

If the effort taken to implement AB3 was 100, then the effort we put into Minefriends would be 150.

While we did not change the design and architecture that was laid out by AB3 drastically, we ended up modifying the 
behaviour of most of its commands, as well as adding many new and unique features to our software.

This includes, but is not limited to:

* Redesigning the entire UI to fit the theme of Minecraft
* Redesigning the `Person` model to include Minecraft-related fields
* Redesigning the `Parser` to parse using regex
* Redesigning the `add` command to include more fields
* Redesigning the `edit` command to include more fields
* Adding a new command to `suggest` friends
* Adding and designing a help window to show the user a list of commands
* Adding the autocomplete feature to the command box

Amongst these changes, we had the hardest time redesigning the `Person` model, as we encountered various bugs after
we had modified it to include additional fields, thus in the end we took many hours to fix more than 200 test cases related to it. 
Due to our iterative approach, we had to redesign other features to fit the needs of a redesigned `Person` model.
