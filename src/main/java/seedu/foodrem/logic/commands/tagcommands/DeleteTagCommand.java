package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Deletes an existing tag in FoodRem.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deletetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing tag in FoodRem. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Potatoes ";

    public static final String MESSAGE_SUCCESS = "Tag deleted: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "This tag does not exist in the FoodRem";

    private final Tag toDelete;

    /**
     * Creates an DeleteTagCommand to delete the specified {@code Tag}
     */
    public DeleteTagCommand(Tag tagToDelete) {
        requireNonNull(tagToDelete);
        toDelete = tagToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(toDelete)) {
            throw new CommandException(MESSAGE_TAG_NOT_FOUND);
        }

        model.deleteTag(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof DeleteTagCommand
                && toDelete.equals(((DeleteTagCommand) other).toDelete));
    }
}
