package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandWord.DELETE_TAG_COMMAND;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Deletes an existing tag in FoodRem.
 */
public class DeleteTagCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Tag deleted: %1$s";
    private static final String ERROR_NOT_FOUND = "This tag does not exist in the FoodRem";

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
            throw new CommandException(ERROR_NOT_FOUND);
        }

        model.deleteTag(toDelete);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    public static String getUsage() {
        return DELETE_TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof DeleteTagCommand
                && toDelete.equals(((DeleteTagCommand) other).toDelete));
    }
}
