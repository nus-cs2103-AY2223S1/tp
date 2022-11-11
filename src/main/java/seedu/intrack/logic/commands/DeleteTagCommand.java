package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Set;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.tag.Tag;

/**
 * Deletes one or more tags from the internship application identified by the index number used in the displayed list.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deltag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes one or more tags from the internship "
            + "application identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive unsigned integer) TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " 1 Urgent";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag(s) from internship application: \n%1$s";

    public static final String DELETED_TAG_NOT_PRESENT =
            "One or more tags provided are not present in the specified internship application.";

    private final Index index;
    private final List<Tag> tags;

    /**
     * Creates an DeleteTagCommand to delete one or more existing tags from the specified {@code Internship}
     */
    public DeleteTagCommand(Index index, List<Tag> tags) {
        requireAllNonNull(index, tags);
        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Internship> lastShownList = model.getFilteredInternshipList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());

        if (!internshipToEdit.getTags().containsAll(tags)) {
            throw new CommandException(DELETED_TAG_NOT_PRESENT);
        }

        for (Tag tagToDelete : tags) {
            internshipToEdit.deleteTag(tagToDelete);
        }

        Set<Tag> newTagList = internshipToEdit.getTags();
        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(),
                internshipToEdit.getWebsite(), internshipToEdit.getTasks(), internshipToEdit.getSalary(),
                newTagList, internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, editedInternship));
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
        return index.equals(e.index)
                && tags.equals(e.tags);
    }
}
