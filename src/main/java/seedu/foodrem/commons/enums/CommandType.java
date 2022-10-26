package seedu.foodrem.commons.enums;

import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.DEFAULT_HELP_MESSAGE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_PRICE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_REMARKS;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Represents the command word to be keyed in by a user to execute a command.
 */
public enum CommandType {
    // General Commands
    EXIT_COMMAND("exit") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Exits FoodRem.\n\n"
                    + "Example:\n"
                    + getCommandWord();
        }
    },
    HELP_COMMAND("help") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Displays help for FoodRem.\n\n"
                    + "Example:\n"
                    + getCommandWord();
        }
    },
    RESET_COMMAND("reset") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Clears all data in FoodRem. "
                    + "This includes all items and tags currently stored.\n\n"
                    + "Example:\n"
                    + getCommandWord();
        }
    },


    // Item Commands
    DECREMENT_COMMAND("dec") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Decrements the quantity of " + THE_ITEM_IN_LIST
                    + "If a quantity is not provided, the item quantity will be decremented by 1.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " INDEX [" + PREFIX_ITEM_QUANTITY + "QUANTITY]\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " 10\n"
                    + getCommandWord() + " 10 " + PREFIX_ITEM_QUANTITY + "100";
        }
    },
    DELETE_COMMAND("del") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Deletes " + THE_ITEM_IN_LIST
                    + "\n"
                    + "Format:\n"
                    + getCommandWord() + " INDEX\n\n"
                    + "Example:\n"
                    + getCommandWord() + " 1";
        }
    },
    EDIT_COMMAND("edit") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Updates the details of " + THE_ITEM_IN_LIST
                    + "Existing values will be overwritten by the input values.\n\n"
                    + "Format (At least one optional prefix must be present):\n"
                    + getCommandWord() + " INDEX "
                    + "[" + PREFIX_NAME + "NAME] "
                    + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY] "
                    + "[" + PREFIX_ITEM_UNIT + "UNIT] "
                    + "[" + PREFIX_ITEM_BOUGHT_DATE + "BOUGHT_DATE] "
                    + "[" + PREFIX_ITEM_EXPIRY_DATE + "EXPIRY_DATE] "
                    + "[" + PREFIX_ITEM_PRICE + "PRICE]\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " 1 "
                    + PREFIX_ITEM_QUANTITY + "1000 "
                    + PREFIX_ITEM_UNIT + "grams "
                    + PREFIX_ITEM_PRICE + "10.2\n"
                    + getCommandWord() + " 2 "
                    + PREFIX_NAME + "Potatoes";
        }
    },
    FIND_COMMAND("find") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Finds all items in FoodRem whose names contain any of "
                    + "the specified keywords (case-insensitive).\n\n"
                    + "Format:\n"
                    + getCommandWord() + " KEYWORD [KEYWORDS]...\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " Potatoes\n"
                    + getCommandWord() + " Potatoes Carrots Cucumbers";
        }
    },
    INCREMENT_COMMAND("inc") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Increments the quantity of " + THE_ITEM_IN_LIST
                    + "If a quantity is not provided, the item quantity will be incremented by 1.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " INDEX [" + PREFIX_ITEM_QUANTITY + "QUANTITY]\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " 10\n"
                    + getCommandWord() + " 10 " + PREFIX_ITEM_QUANTITY + "100";
        }
    },
    LIST_COMMAND("list") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": List all items in FoodRem.\n\n"
                    + "Example:\n"
                    + getCommandWord();
        }
    },
    NEW_COMMAND("new") {
        // TODO: Ensure remarks is added
        @Override
        public String getUsage() {
            return getCommandWord() + ": Creates a new item with the provided item name. "
                    + "All fields apart from ITEM_NAME are optional.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "NAME "
                    + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY" + "] "
                    + "[" + PREFIX_ITEM_UNIT + "UNIT" + "] "
                    + "[" + PREFIX_ITEM_BOUGHT_DATE + "BOUGHT_DATE" + "] "
                    + "[" + PREFIX_ITEM_EXPIRY_DATE + "EXPIRY_DATE" + "] "
                    + "[" + PREFIX_ITEM_PRICE + "PRICE" + "]\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "Potatoes\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "Potatoes "
                    + PREFIX_ITEM_QUANTITY + "10 "
                    + PREFIX_ITEM_UNIT + "kg "
                    + PREFIX_ITEM_BOUGHT_DATE + "11-11-2022 "
                    + PREFIX_ITEM_EXPIRY_DATE + "21-11-2022 "
                    + PREFIX_ITEM_PRICE + "10";
        }
    },
    REMARK_COMMAND("rmk") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Adds a remark to " + THE_ITEM_IN_LIST
                    + "Parameters: "
                    + PREFIX_ITEM_REMARKS
                    + "Example: " + getCommandWord() + " 1" + PREFIX_ITEM_REMARKS + "For oranges";
        }
    },
    SORT_COMMAND("sort") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Sorts all items in FoodRem according to the specified criteria.\n"
                    + "Available criteria includes sorting by name, quantity, unit, bought date, expiry date, "
                    + "price and remarks.\n\n"
                    + "Format (Only one of the optional prefix should be present):\n"
                    + getCommandWord() + " "
                    + "[" + PREFIX_NAME + "] "
                    + "[" + PREFIX_ITEM_QUANTITY + "] "
                    + "[" + PREFIX_ITEM_UNIT + "] "
                    + "[" + PREFIX_ITEM_BOUGHT_DATE + "] "
                    + "[" + PREFIX_ITEM_EXPIRY_DATE + "] "
                    + "[" + PREFIX_ITEM_PRICE + "]"
                    + "[" + PREFIX_ITEM_REMARKS + "]\n\n"
                    + "Examples:\n"
                    + getCommandWord() + " " + PREFIX_NAME + "\n"
                    + getCommandWord() + " " + PREFIX_ITEM_UNIT;
        }
    },
    VIEW_COMMAND("view") {
        // TODO: Ensure remarks is added
        @Override
        public String getUsage() {
            return getCommandWord() + ": Displays the information of " + THE_ITEM_IN_LIST
                    + "The name, quantity, bought date, expiry date, unit, price and associated tags "
                    + "of the items will be displayed.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " INDEX\n\n"
                    + "Example:\n"
                    + getCommandWord() + " 1";
        }
    },
    FILTER_TAG_COMMAND("filtertag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Filters all items in FoodRem by the specified tag"
                    + "\n\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "Potatoes ";
        }
    },

    // Tag Commands
    DELETE_TAG_COMMAND("deletetag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Deletes an existing tag in FoodRem.\n"
                    + "\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "Potatoes ";
        }
    },
    LIST_TAG_COMMAND("listtag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": List all tags in FoodRem.\n\n"
                    + "Example:\n"
                    + getCommandWord();
        }
    },
    NEW_TAG_COMMAND("newtag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Creates a tag in FoodRem.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "Potatoes ";
        }
    },
    RENAME_TAG_COMMAND("renametag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Renames an existing tag in FoodRem.\n\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "ORIGINAL_TAG_NAME "
                    + PREFIX_NAME + "NEW_TAG_NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + PREFIX_NAME + "vegetables "
                    + PREFIX_NAME + "fruits";
        }
    },
    TAG_COMMAND("tag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Tags " + THE_ITEM_IN_LIST
                    + "\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + "INDEX "
                    + PREFIX_NAME + "TAG_NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + "1 "
                    + PREFIX_NAME + "Condiments";
        }
    },
    UNTAG_COMMAND("untag") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Untags " + THE_ITEM_IN_LIST
                    + "\n"
                    + "Format:\n"
                    + getCommandWord() + " "
                    + "INDEX "
                    + PREFIX_NAME + "TAG_NAME\n\n"
                    + "Example:\n"
                    + getCommandWord() + " "
                    + "1 "
                    + PREFIX_NAME + "Condiments";
        }
    },

    // INVALID
    DEFAULT("default") {
        @Override
        public String getUsage() {
            return DEFAULT_HELP_MESSAGE;
        }
    };

    private static final String THE_ITEM_IN_LIST =
            "the item at the specified index.\n";

    private final String commandWord;

    /**
     * Constructs a CommandWord enum.
     *
     * @param commandWord the value representing the string value of the enum.
     */
    CommandType(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Returns the string value of a CommandWord.
     *
     * @return the string representation of a CommandWord.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Returns the string value of a help message for the CommandWord.
     *
     * @return the string representation a help message for a CommandWord.
     */
    public abstract String getUsage();

    /**
     * Returns the CommandWord object from the string value of a command word.
     *
     * @param word a string value of the enum represented by the value provided.
     * @return a CommandWord object of the command represented by the string.
     */
    public static CommandType parseWord(String word) {
        return Arrays.stream(values())
                .filter(type -> type.commandWord.equals(word))
                .findFirst().orElse(DEFAULT);
    }

    /**
     * Returns the list of all command words separated by commas
     */
    public static String listAllCommandWords() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (CommandType type : values()) {
            if (type.equals(DEFAULT)) {
                continue;
            }
            stringJoiner.add(type.commandWord);
        }
        return stringJoiner.toString();
    }
}
