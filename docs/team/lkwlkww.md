---
layout: page
title: Lee Kang Wei's Project Portfolio Page
---

### Project: Condonery

Condonery is a desktop app made for property agents primarily used for managing client contacts and condo listings.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Condonery can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Code contributed**:
  * Property base classes
  * add property
  * Interested clients for property
  * Interested properties for clients
  * Note to self: Include link to my code on the tP code dashboard

* **Enhancements implemented**:
  * refactor commands into propertycommand and clientcommand
    * includes stuff like regex

* **Contibutions to User Guide**:
  * Completed the 
  * Completed the section for describing features of Condonery's client directory

* **Contributions to Developer Guide**:
  * Completed the Glossary section for key terms worth defining

* **Other documentation contributions**:

* **Contributions to team-based tasks**:

* **Review/mentoring contributions**:

### Contributions to the Developer Guide (Extracts)

### Contributions to the User Guide (Extracts)
#### Contribution 2. Property and client attributes

In Condonery, properties and clients can hold many different attributes. These attributes are denoted by options (e.g. `n/` for name) when using commands like `add -p`, `add -c`, `edit -p` or `edit -c`.
This section gives a breakdown of each attribute and its option counterpart.

##### Shared attributes
###### Name
Refers to the name of a property or client.
Each property or client can only have one name.

Option `n/`
Example: `n/PINNACLE@DUXTON`

###### Tag
Refers to the given tags of a property or client. Can be used to label a property or client with important details.
The only requirement for tags is that they have to be alphanumerical.
Each property or client can have multiple tags.

Option `t/`
Example: `t/High-end`, `t/Friend`

#### Property attributes
##### Price
Refers to the general price to purchase a unit for a property.
Each property can only have one price.

Option `p/`
Example: `p/1,000,000`

##### Property type
Refers to the type of a property.
Each property can only have one property type.
Valid property types:
* HDB
* CONDO
* LANDED
Arguments for this option are case-insensitive.

Option: `h/`
Example: `h/HDB`, `h/Condo`

#### Client attributes
##### Interested properties
Refers to properties that a client is interested in.
Each client can be interested in multiple properties.
This option takes in **case-insensitive** arguments and attempts to link it to the name of a property.
Arguments will not be accepted if it links to no properties, or if it links to more than one property.

Option: `ip/`
Example: `ip/duxton`, `ip/rosewood`

