package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Address;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Email;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Phone;
import seedu.address.model.tag.Tag;

/**
 * Mark the status of an existing internship in the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_NOT_EDITED = "Please enter a status to mark the internship with.";
    public static final String MESSAGE_MARK_INTERNSHIP_SUCCESS = "Marked Internship: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer) [s/APPLICATIONSTATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 s/Rejected";

    private final Index index;
    private final ApplicationStatus applicationStatus;

    /**
     * @param index of the internship in the filtered internship list to edit
     * @param applicationStatus status of the internship
     */
    public MarkCommand(Index index, ApplicationStatus applicationStatus) {
        requireNonNull(index);
        requireNonNull(applicationStatus);

        this.index = index;
        this.applicationStatus = applicationStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToMark = lastShownList.get(index.getZeroBased());
        Internship markedInternship = createMarkedInternship(internshipToMark, applicationStatus);

        model.setInternship(internshipToMark, markedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(String.format(MESSAGE_MARK_INTERNSHIP_SUCCESS, markedInternship));
    }


    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToMark}
     * marked with {@code applicationStatus}.
     */
    private static Internship createMarkedInternship(Internship internshipToMark,
                                                     ApplicationStatus updatedApplicationStatus) {
        assert internshipToMark != null;

        Company updatedCompany = internshipToMark.getCompany();
        Phone updatedPhone = internshipToMark.getPhone();
        Email updatedEmail = internshipToMark.getEmail();
        Address updatedAddress = internshipToMark.getAddress();
        Set<Tag> updatedTags = internshipToMark.getTags();

        return new Internship(updatedCompany, updatedPhone, updatedEmail, updatedApplicationStatus, updatedAddress,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return index.equals(e.index)
                && applicationStatus.equals(e.applicationStatus);
    }
}
