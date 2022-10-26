package seedu.intrack.logic.commands;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;
import java.util.Set;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.tag.Tag;

/**
 * Deletes a Tag from the selected Internship.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deltag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes one or more tags of the internship"
            + " with the " + "selected index.\n" + "Parameters: INDEX (must be a positive integer) TAG \n"
            + " or to delete all tags, use the Parameters: clear\n"
            + "Example 1: " + COMMAND_WORD + " 1 URGENT\n"
            + "Example 2: " + COMMAND_WORD + " 1 clear.";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag/tags of internship successfully";
    public static final String MESSAGE_CLEAR_TAGS_SUCCESS = "Cleared tag/tags of internship successfully";

    public static final String DELETED_TAG_NOT_PRESENT = "There are one or more tags listed that are not in the list"
            + " of tags!";

    public static final String DELETE_TAG_CONSTRAINTS = "No tag detected, please include a tag to delete from the "
            + "indicated internship";

    private final Index index;
    private final List<Tag> tags;
    private final String command;

    /**
     * Generates a command execution success message based on whether a tag is added deleted/cleared to the taglist
     * {@code internshipToEdit}.
     */
    public DeleteTagCommand(Index index, List<Tag> tags, String command) {
        requireAllNonNull(index, tags);

        this.index = index;
        this.tags = tags;
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());

        if (!internshipToEdit.getTags().containsAll(tags) && !command.equals("CLEAR")) {
            throw new CommandException(DELETED_TAG_NOT_PRESENT);
        }

        if (command.equals("CLEAR")) {
            //if is clear, delete all
            internshipToEdit.clearTag();
        } else {
            //else just delete tags by normal
            for (int i = 0; i < tags.size(); i++) {
                Tag tagToAdd = tags.get(i);
                internshipToEdit.deleteTag(tagToAdd);
            }
        }
        Set<Tag> newTagList = internshipToEdit.getTags();
        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(),
                internshipToEdit.getWebsite(), internshipToEdit.getTasks(), internshipToEdit.getSalary(),
                newTagList, internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        if (command.equals("CLEAR")) {
            return new CommandResult(MESSAGE_CLEAR_TAGS_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_DELETE_TAG_SUCCESS);
        }
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
                && tags.equals(e.tags)
                && command.equals((e.command));
    }
}
