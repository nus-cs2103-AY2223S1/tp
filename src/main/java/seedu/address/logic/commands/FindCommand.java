package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

/**
 * Finds and lists all persons in address book whose name/address contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_SHORTCUT = "f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose name/address/etc contain any "
            + "of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Use the prefix to specify the field to search in.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME [MORE_NAMES]...\n"
            + "Or \n"
            + PREFIX_ADDRESS + "ADDRESS [MORE_ADDRESSES]...\n"
            + "Or \n"
            + PREFIX_PHONE + "PHONE NUMBER [MORE_NUMBERS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

}
