package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.NEW_TAG_COMMAND;

import java.util.List;

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
    private static final String MESSAGE_SUCCESS = "New tag added:";

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

        model.addTag(toAdd);
        return CommandResult.from(new TagsWithMessage(List.of(toAdd), MESSAGE_SUCCESS));
    }

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
