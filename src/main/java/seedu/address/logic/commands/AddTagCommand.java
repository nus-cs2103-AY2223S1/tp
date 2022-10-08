package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Add tag for an existing person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tags for a person in the address book.\n"
        + "Parameters:\n"
        + "  INDEX (must be a positive integer or use \"all\" to add tags for everyone in the list) \n"
        + "  [" + PREFIX_TAG + "TAG]...\n"
        + "Example:\n" + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "lab "
        + PREFIX_TAG + "goodProgress"
        + "\nor\n" + COMMAND_WORD + " all "
        + PREFIX_TAG + "tutorial "
        + PREFIX_TAG + "needRemedial";

    /** 
     * Index of the person to add the tags for. 
     * If index is null, the tags will be added for every person in the list.
     */
    private Index index;
    
    /** The tags to be added. */
    private Set<Tag> tags;

    /**
     * @param index of the person in the filtered person list to add the tag
     * @param tags of the person to be added
     */
    public AddTagCommand(Index index, Set<Tag> tags) {
        requireNonNull(tags);

        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) {
        String indexStr = this.index == null ? "all" : String.valueOf(index.getOneBased());
        return new CommandResult(String.format(MESSAGE_ARGUMENTS, indexStr, this.tags));
    }
}
