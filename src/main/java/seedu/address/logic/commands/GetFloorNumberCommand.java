package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FloorNumberContainsKeywordsPredicate;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all patients within all given floor numbers separated by a whitespace.
 * Integers must be positive.
 */
public class GetFloorNumberCommand extends Command {
    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients within the same floor number"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: FLOOR_NUMBER (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " /fn 10 4 3";

    private final FloorNumberContainsKeywordsPredicate predicate;

    public GetFloorNumberCommand(FloorNumberContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        PatientTypePredicate patientTypePredicate = new PatientTypePredicate(PatientType.PatientTypes.INPATIENT);
        Predicate<Person> inPatientFloorNumberPredicate = patientTypePredicate.and(predicate);
        model.updateFilteredPersonList(inPatientFloorNumberPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetFloorNumberCommand // instanceof handles nulls
                && predicate.equals(((GetFloorNumberCommand) other).predicate)); // state check
    }
}
