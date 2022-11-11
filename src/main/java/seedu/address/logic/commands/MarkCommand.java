package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Marks the status of an existing internship in findMyIntern.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer) s/APPLICATION_STATUS\n"
            + "Example: " + COMMAND_WORD + " 1 s/Rejected";

    public static final String MESSAGE_NOT_EDITED = "Please enter an application status to mark the internship with.";
    public static final String MESSAGE_SAME_APPLICATION_STATUS = "This is the current application status.";
    public static final String MESSAGE_MARK_INTERNSHIP_SUCCESS = "Marked internship: %1$s";

    private final Index index;
    private final ApplicationStatus applicationStatus;

    /**
     * @param index of the internship in the filtered internship list to mark
     * @param applicationStatus status of the internship
     */
    public MarkCommand(Index index, ApplicationStatus applicationStatus) {
        requireNonNull(index);
        requireNonNull(applicationStatus);
        assert index.getOneBased() > 0 : "index should be a positive integer";

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

        if (internshipToMark.equals(markedInternship)) {
            throw new CommandException(MESSAGE_SAME_APPLICATION_STATUS);
        }

        model.setInternship(internshipToMark, markedInternship);
        model.updateFilteredInternshipList(model.getCurrentPredicate());
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
        Link updatedLink = internshipToMark.getLink();
        Description updatedDescription = internshipToMark.getDescription();
        AppliedDate updatedAppliedDate = internshipToMark.getAppliedDate();
        InterviewDateTime updatedInterviewDateTime = internshipToMark.getInterviewDateTime();
        Set<Tag> updatedTags = internshipToMark.getTags();

        return new Internship(updatedCompany, updatedLink, updatedDescription,
                updatedApplicationStatus, updatedAppliedDate, updatedInterviewDateTime, updatedTags);
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
