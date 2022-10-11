package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.WardNumberPredicate;

/**
 * Finds and lists all patients within a ward number.
 * Integers must be positive.
 */
public class GetWardNumberCommand extends GetCommand {

    public static final String WARD_NUMBER_PREFIX = "/wn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + WARD_NUMBER_PREFIX
            + ": Gets all patients within the same ward number and displays them as a list with index numbers. "
            + "The specified keywords are case-insensitive.\n"
            + "Parameters: "
            + WARD_NUMBER_PREFIX + " WARD NUMBER\n"
            + "Example: "
            + COMMAND_WORD + " "
            + WARD_NUMBER_PREFIX
            + " D312 T36";

    private final WardNumberPredicate predicate;

    public GetWardNumberCommand(WardNumberPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        PatientTypePredicate inPatientPredicate = new PatientTypePredicate(PatientType.PatientTypes.INPATIENT);
        Predicate<Person> wardNumberPredicate = inPatientPredicate.and(predicate);
        model.updateFilteredPersonList(wardNumberPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetWardNumberCommand // instanceof handles nulls
                && predicate.equals(((GetWardNumberCommand) other).predicate)); // state check
    }
}
