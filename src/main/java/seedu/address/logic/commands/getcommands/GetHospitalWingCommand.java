package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all patients within a hospital wing.
 * Keyword matching is case insensitive.
 */
public class GetHospitalWingCommand extends GetCommand {

    public static final String HOSPITAL_WING_PREFIX = "/hw";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all patients within the same hospital wing "
            + "and displays them as a list with index numbers. The specified keywords are case-insensitive.\n"
            + "Parameters: "
            + HOSPITAL_WING_PREFIX + " HOSPITAL WING\n"
            + "Example: "
            + COMMAND_WORD + " "
            + HOSPITAL_WING_PREFIX
            + " south north";

    private final HospitalWingContainsKeywordsPredicate predicate;

    public GetHospitalWingCommand(HospitalWingContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        PatientTypePredicate inPatientPredicate = new PatientTypePredicate(PatientType.PatientTypes.INPATIENT);
        Predicate<Person> hospitalWingPredicate = inPatientPredicate.and(predicate);
        model.updateFilteredPersonList(hospitalWingPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetHospitalWingCommand // instanceof handles nulls
                && predicate.equals(((GetHospitalWingCommand) other).predicate)); // state check
    }
}
