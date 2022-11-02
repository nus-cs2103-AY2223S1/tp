package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.tag.Tag;

/**
 * Adds one or more tags to the internship application identified by the index number used in the displayed list.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds one or more tags to the internship application "
            + "identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive unsigned integer) TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " 1 Urgent";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added new tag(s) to internship application: \n%1$s";

    public static final String MESSAGE_ADD_TAG_DUPLICATE = "Duplicated tag(s) detected, tags are case sensitive and"
           + " duplicated tag(s) will not be added to an internship, however,"
           + " non-duplicated tag(s) in the command will still be added.";

    private final Index index;
    private final List<Tag> tags;

    /**
     * Creates an AddTagCommand to add one or more tags to the specified {@code Internship}
     */
    public AddTagCommand(Index index, List<Tag> tags) {
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
        Set<Tag> initialTagList = new HashSet<>();

        // create duplicate of old tags
        for (Tag tag : internshipToEdit.getTags()) {
            initialTagList.add(tag);
        }

        for (Tag tagToAdd : tags) {
            internshipToEdit.addTag(tagToAdd);
        }

        Set<Tag> newTagList = internshipToEdit.getTags();

        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(),
                internshipToEdit.getWebsite(), internshipToEdit.getTasks(), internshipToEdit.getSalary(),
                newTagList, internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);

        //diff commandresult where new tags are added but duplicates arent'
        for (Tag tagToAdd : tags) {
            if (initialTagList.contains(tagToAdd)) {
                return new CommandResult(String.format(MESSAGE_ADD_TAG_DUPLICATE, editedInternship));
            }
        }

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedInternship));
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

