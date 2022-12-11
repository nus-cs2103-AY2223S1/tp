package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.NEW_TAG_COMMAND;

import seedu.foodrem.commons.exceptions.TagStorageFullException;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.TagsWithMessage;

/**
 * Adds a tag to FoodRem.
 */
public class NewTagCommand extends Command {
    private final Tag toAdd;

    /**
     * Creates a NewTagCommand to add the specified {@code Tag}
     *
     * @param tag the tag that will be added to foodRem.
     */
    public NewTagCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult<TagsWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException("This tag already exists in FoodRem");
        }

        try {
            model.addTag(toAdd);
        } catch (TagStorageFullException sfe) {
            throw new CommandException(sfe.getMessage());
        }

        return CommandResult.from(new TagsWithMessage("New tag added:", toAdd));
    }

    /**
     * Returns a string representing how to use the command.
     *
     * @return a string representing how to use the command.
     */
    public static String getUsage() {
        return NEW_TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NewTagCommand
                && toAdd.equals(((NewTagCommand) other).toAdd));
    }
}
