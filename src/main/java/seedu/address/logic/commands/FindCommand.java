package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Finds and lists all persons in address book whose name/address contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names/address contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Or \n"
            + PREFIX_ADDRESS + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

}
