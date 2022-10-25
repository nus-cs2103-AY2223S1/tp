package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Pin;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.tag.Tag;

/**
 * Pins an applicant identified using its displayed index from TrackAScholar.
 */
public class UnPinCommand extends Command {
    public static final String COMMAND_WORD = "unpin";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unpins the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_UNPIN_APPLICANT_SUCCESS = "Pinned Applicant: %1$s";

    private final Index index;

    public UnPinCommand(Index targetIndex) {
        this.index = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToPin = lastShownList.get(index.getZeroBased());
        Applicant pinnedApplicant = createUnPinnedApplicant(applicantToPin);

        model.setApplicant(applicantToPin, pinnedApplicant);
        return new CommandResult(String.format(MESSAGE_UNPIN_APPLICANT_SUCCESS, pinnedApplicant));
    }

    private static Applicant createUnPinnedApplicant(Applicant applicantToPin) {
        assert applicantToPin != null;
        Name name = applicantToPin.getName();
        Phone phone = applicantToPin.getPhone();
        Email email = applicantToPin.getEmail();
        Scholarship scholarship = applicantToPin.getScholarship();
        ApplicationStatus applicationStatus = applicantToPin.getApplicationStatus();
        Set<Tag> tags = applicantToPin.getTags();
        Pin pin = new Pin(false);

        return new Applicant(name, phone, email, scholarship, applicationStatus, tags, pin);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnPinCommand // instanceof handles nulls
                && index.equals(((UnPinCommand) other).index)); // state check
    }
}
