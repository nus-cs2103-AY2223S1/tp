<!-- markdownlint-disable-file first-line-h1 -->

This section covers all you should know about FoodRem, as well as a [guided tutorial](#trying-your-first-command). Of special note is the [Key Definitions](#key-definitions) and [Command Format](#command-format) sections, which covers essential knowledge to using FoodRem's features.

### Layout

{% include_relative _ug/Layout.md %}

### Key Definitions

{% include_relative _ug/KeyDefinitions.md %}

### Command Format

You will encounter FoodRem commands throughout this User Guide. Before you delve into the different commands in [Commands](#commands), let’s learn what a command consists of.

Here is an example:

![CommandExample](images/CommandExample.png)

A command consists of:

1. Command Word: Tells FoodRem what action you wish to execute. These actions are covered in [Commands](#commands).
1. [Flags](#flags): Distinguishes between inputs. This follows before your Placeholder data.
1. [Placeholders](#placeholders): Represents data that you wish to input. Replace this with valid data. For example, `ITEM_NAME` in `n/ITEM_NAME` can be replaced with `n/Potato`. Placeholders should follow after a Flag.

### Trying your first command

{% include_relative _ug/TryingFirstCommand.md %}
