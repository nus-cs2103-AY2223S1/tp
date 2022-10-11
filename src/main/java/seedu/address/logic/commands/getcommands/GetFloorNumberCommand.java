package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.FloorNumberPredicate;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all patients within all given floor numbers separated by a whitespace.
 * Integers must be positive.
 */
public class GetFloorNumberCommand extends GetCommand {

    public static final String FLOOR_NUMBER_PREFIX = "/fn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all patients within the same floor number "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + FLOOR_NUMBER_PREFIX + " FLOOR NUMBER (must be a positive integer)\n"
            + "Example: "
            + COMMAND_WORD + " "
            + FLOOR_NUMBER_PREFIX
            + " 10 4 3";

    private final FloorNumberPredicate predicate;

    public GetFloorNumberCommand(FloorNumberPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        PatientTypePredicate inPatientPredicate = new PatientTypePredicate(PatientType.PatientTypes.INPATIENT);
        Predicate<Person> floorNumberPredicate = inPatientPredicate.and(predicate);
        model.updateFilteredPersonList(floorNumberPredicate);

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
