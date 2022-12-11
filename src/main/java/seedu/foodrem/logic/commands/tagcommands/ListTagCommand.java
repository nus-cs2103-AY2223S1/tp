package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.LIST_TAG_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.TagsWithMessage;

/**
 * Lists all the tags available
 */
public class ListTagCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Listed all tags:";

    @Override
    public CommandResult<TagsWithMessage> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        return CommandResult.from(new TagsWithMessage(MESSAGE_SUCCESS, model.getFilteredTagList().toArray(Tag[]::new)));
    }

    /**
     * Returns a string representing how to use the command.
     *
     * @return a string representing how to use the command.
     */
    public static String getUsage() {
        return LIST_TAG_COMMAND.getUsage();
    }
}
