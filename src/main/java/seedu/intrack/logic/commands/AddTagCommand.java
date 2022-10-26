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
 * Adds a new Tag to the selected Internship.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds one or more tags to the internship application with the " +
            "selected index.\n" + "Parameters: INDEX (must be a positive integer) TAG \n"
            + "Example: " + COMMAND_WORD + " 1 URGENT\n" +
            "or " + COMMAND_WORD + " 1 URGENT HARD HELP";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to internship successfully";
    public static final String ADD_TAG_CONSTRAINTS = "No tag detected, please include a tag to add to the indicated" +
            " internship";

    private final Index index;
    private final List<Tag> tags;

    public AddTagCommand(Index index, List<Tag> tags) {
        requireAllNonNull(index, tags);

        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        for (int i = 0; i < tags.size(); i++) {
            Tag tagToAdd = tags.get(i);
            internshipToEdit.addTag(tagToAdd);
        }
        Set<Tag> newTagList = internshipToEdit.getTags();
        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(),
                internshipToEdit.getWebsite(), internshipToEdit.getTasks(), internshipToEdit.getSalary(),
                newTagList, internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(MESSAGE_ADD_TAG_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return index.equals(e.index)
                && tags.equals(e.tags);

    }
}

