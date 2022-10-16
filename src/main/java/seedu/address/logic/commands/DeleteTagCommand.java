package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 *  Deletes tag(s) in the address book.
 */
public class DeleteTagCommand extends TagCommandGroup {

    public static final String COMMAND_SPECIFIER = "delete";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes tags with the names given. "
            + "Parameters: "
            + "[TAG1] [TAG2] ... \n"
            + "Example: " + COMMAND_WORD + " tag1 tag2 tag3";

    public static final String MESSAGE_SUCCESS = "Tags deleted: %1$s";
    public static final String MESSAGE_DUPLICATE_TAGS = "Tag(s) %1$s does not exist in Rapportbook!";

    private final Set<Tag> tagsToDelete;

    /**
     * @param tagsToDelete tags to add to the address book
     */
    public DeleteTagCommand(Set<Tag> tagsToDelete) {
        requireNonNull(tagsToDelete);
        this.tagsToDelete = tagsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Set<Tag> deletedTags = model.deleteTags(tagsToDelete);

        Set<Tag> undeletedTags = new HashSet<>(tagsToDelete);
        undeletedTags.removeAll(deletedTags);

        String undeletedMessage = undeletedTags.isEmpty()
                ? ""
                : String.format(MESSAGE_DUPLICATE_TAGS, Tag.toString(undeletedTags)) + "\n";
        String successMessage = deletedTags.isEmpty()
                ? ""
                : String.format(MESSAGE_SUCCESS, Tag.toString(deletedTags));

        return new CommandResult(undeletedMessage + successMessage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        // state check
        DeleteTagCommand e = (DeleteTagCommand) other;
        return tagsToDelete.equals(e.tagsToDelete);

    }
}
