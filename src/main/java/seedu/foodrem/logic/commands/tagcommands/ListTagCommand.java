package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_TAGS;

import java.util.List;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Lists all the tags available
 */
public class ListTagCommand extends Command {
    public static final String COMMAND_WORD = "listtag";

    public static final String MESSAGE_SUCCESS = "Listed all tags:\n";
    private String tagList = "";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        List<Tag> allTags = model.getFilteredTagList();

        StringBuilder allTagsList = new StringBuilder();

        for (Tag tag: allTags) {
            allTagsList.append(tag.tagName.fullName);
            allTagsList.append(System.getProperty("line.separator"));
        }

        tagList = allTagsList.toString();

        return new CommandResult(MESSAGE_SUCCESS + tagList);
    }
}
