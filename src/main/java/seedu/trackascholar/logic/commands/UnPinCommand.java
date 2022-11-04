package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Pin;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;

/**
 * Pins an applicant identified using its displayed index from TrackAScholar.
 */
public class UnPinCommand extends Command {

    public static final String COMMAND_WORD = "unpin";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unpins the applicant identified by the applicant's full name in the displayed pinned applicant list.\n"
            + "Parameters: FULL_NAME\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    public static final String MESSAGE_UNPIN_APPLICANT_SUCCESS = "Unpinned Applicant: %1$s";
    public static final String MESSAGE_NO_SUCH_APPLICANT_FOUND = "Given applicant does not exist.";
    public static final String MESSAGE_APPLICANT_ALREADY_UNPINNED = "Applicant is already unpinned.";

    private final Name name;

    public UnPinCommand(Name targetName) {
        this.name = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getAllApplicants();

        int indexOfApplicant = getApplicantIndex(lastShownList, name);

        Applicant applicantToUnPin = lastShownList.get(indexOfApplicant);
        if (!applicantToUnPin.getHasPinned()) {
            throw new CommandException(MESSAGE_APPLICANT_ALREADY_UNPINNED);
        }
        Applicant unPinnedApplicant = createUnPinnedApplicant(applicantToUnPin);

        model.setApplicant(applicantToUnPin, unPinnedApplicant);
        return new CommandResult(String.format(MESSAGE_UNPIN_APPLICANT_SUCCESS, unPinnedApplicant));
    }

    private static int getApplicantIndex(List<Applicant> list, Name nameToBeSearched) throws CommandException {
        for (int i = 0; i < list.size(); i++) {
            Applicant currApplicant = list.get(i);
            Name currApplicantName = currApplicant.getName();
            if (currApplicantName.equalsIgnoreCase(nameToBeSearched)) {
                return i;
            }
        }
        throw new CommandException(MESSAGE_NO_SUCH_APPLICANT_FOUND);
    }

    private static Applicant createUnPinnedApplicant(Applicant applicantToPin) {
        assert applicantToPin != null;
        Name name = applicantToPin.getName();
        Phone phone = applicantToPin.getPhone();
        Email email = applicantToPin.getEmail();
        Scholarship scholarship = applicantToPin.getScholarship();
        ApplicationStatus applicationStatus = applicantToPin.getApplicationStatus();
        Set<Major> tags = applicantToPin.getMajors();
        Pin pin = new Pin(false);

        return new Applicant(name, phone, email, scholarship, applicationStatus, tags, pin);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnPinCommand // instanceof handles nulls
                && name.equals(((UnPinCommand) other).name)); // state check
    }
}
