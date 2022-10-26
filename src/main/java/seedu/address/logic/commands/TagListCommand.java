package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Tags a user with one or more tags.
 */
public class TagListCommand extends TagCommandGroup {
    public static final String COMMAND_SPECIFIER = "list";
    public static final String COMMAND_SPECIFIER_ALIAS = "l";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show all available tags";

    public static final String MESSAGE_SUCCESS = "Tags: %1$s";

    public TagListCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getAllTagsInPrintableFormat()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagListCommand)) {
            return false;
        }

        return true;
    }
}
