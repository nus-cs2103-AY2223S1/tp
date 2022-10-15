package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandWord.NEW_TAG_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Adds a tag to FoodRem.
 */
public class NewTagCommand extends Command {
    private static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    private static final String ERROR_DUPLICATE = "This tag already exists in FoodRem";

    private final Tag toAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public NewTagCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException(ERROR_DUPLICATE);
        }

        model.addTag(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    public static String getUsage() {
        return NEW_TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof NewTagCommand
                && toAdd.equals(((NewTagCommand) other).toAdd));
    }
}
