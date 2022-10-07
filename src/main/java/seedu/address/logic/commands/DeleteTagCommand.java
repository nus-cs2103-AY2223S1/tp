package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEGREETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTYPETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLTAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Deletes a specified tag of a person.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag of the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILLTAG + "TAG] "
            + "[" + PREFIX_DEGREETAG + "TAG] "
            + "[" + PREFIX_JOBTYPETAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_SKILLTAG + "Java ";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag of the person: %1$s";

    private final Index targetIndex;
    private final Tag toDelete;

    /**
     * Creates an DeleteTagCommand to delete the specified {@code Tag} of a person at the specified {@code Index}
     */
    public DeleteTagCommand(Index targetIndex, Tag toDelete) {
        this.targetIndex = targetIndex;
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTagCommand) other).targetIndex)
                && toDelete.equals(((DeleteTagCommand) other).toDelete));
    }
}
